package top.piao888.wxdc.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import top.piao888.wxdc.converter.OrderMaster2OrerDTOConverter;
import top.piao888.wxdc.domain.OrderDetail;
import top.piao888.wxdc.domain.OrderMaster;
import top.piao888.wxdc.domain.ProductInfo;
import top.piao888.wxdc.dto.CartDTO;
import top.piao888.wxdc.dto.OrderDTO;
import top.piao888.wxdc.enums.OrderStatusEnum;
import top.piao888.wxdc.enums.PayStatusEnum;
import top.piao888.wxdc.enums.ResultEnum;
import top.piao888.wxdc.exception.SellException;
import top.piao888.wxdc.repository.OrderDetailRepository;
import top.piao888.wxdc.repository.OrderMasterRepository;
import top.piao888.wxdc.repository.ProductInfoRepository;
import top.piao888.wxdc.service.OrderService;
import top.piao888.wxdc.service.ProductService;
import top.piao888.wxdc.util.KeyUtil;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName OrderServiceImpl.java
 * @Description TODO
 * @createTime 2019年03月20日 12:42:00
 */
@Service
@Slf4j
//Service层 除了 需要接收 前端 传来的必要 数据 剩下的 必须要通过自己计算，尤其是 商品单价  要通过 后台数据库 价格 确定
public class OrderServiceImpl implements OrderService {
    @Autowired
    private ProductInfoRepository productInfoRepository;
    @Autowired
    private OrderDetailRepository orderDetailRepository;
    @Autowired
    private OrderMasterRepository orderMasterRepository;
    @Autowired
    private ProductService productService;
    @Override
    @Transactional
    public OrderDTO create(OrderDTO orderDTO) {
        String orderId=KeyUtil.genUniqueKey();
//        List<CartDTO> cartDTOList=new ArrayList<>();
        // 1.查询商品（数量,价格）
        BigDecimal orderAmount=new BigDecimal(0);
        for(OrderDetail orderDetail:orderDTO.getOrderDetailList()) {
            ProductInfo productInfo = (ProductInfo) productInfoRepository.findById(orderDetail.getProductId()).get();
            if (productInfo == null) {
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            // 2.计算总价
            orderAmount = orderAmount.add(productInfo.getProductPrice().multiply(new BigDecimal(orderDetail.getProductQuantity())));

            //订单详情入库
            orderDetail.setDetailId(KeyUtil.genUniqueKey());
            orderDetail.setOrderId(orderId);
            BeanUtils.copyProperties(productInfo,orderDetail);
            orderDetailRepository.save(orderDetail);

//            CartDTO cartDTO=new CartDTO(orderDetail.getProductId(),orderDetail.getProductQuantity());
//            cartDTOList.add(cartDTO);
        }
        //3.写入订单数据库(orderMaster和orderDetail)
        OrderMaster orderMaster=new OrderMaster();
        //一定要先拷贝 再 设值  要不然 设值完成后  新的 值 会把 旧值覆盖掉  即使为null
        orderDTO.setOrderId(orderId);
        BeanUtils.copyProperties(orderDTO,orderMaster);
        orderMaster.setOrderAmount(orderAmount);
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());
        orderMasterRepository.save(orderMaster);

        //4.扣库存
        List<CartDTO> cartDTOList= orderDTO.getOrderDetailList().stream().map(e->new CartDTO(e.getProductId(),e.getProductQuantity())).collect(Collectors.toList());
        productService.decreaseStock(cartDTOList);
        return orderDTO;
    }

    @Override
    public OrderDTO findOne(String orderId) {
        OrderMaster orderMaster=(OrderMaster)orderMasterRepository.findById(orderId).get();
        if(orderMaster==null){
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }
        List<OrderDetail> orderDetail=orderDetailRepository.findByOrderId(orderMaster.getOrderId());
        if(orderDetail==null){
            throw new SellException(ResultEnum.ORDERDETAIL_NOT_EXIST);
        }
        OrderDTO orderDTO=new OrderDTO();
        BeanUtils.copyProperties(orderMaster,orderDTO);
        orderDTO.setOrderDetailList(orderDetail);
        return orderDTO;
    }

    @Override
    public Page<OrderDTO> findList(String buyerOpenid, Pageable pageable) {
        Page<OrderMaster> orderMasterPage=orderMasterRepository.findAllByBuyerOpenid(buyerOpenid,pageable);
        List<OrderDTO> orderDTOList=OrderMaster2OrerDTOConverter.convert(orderMasterPage.getContent());

        return   new PageImpl<OrderDTO>(orderDTOList,pageable,orderMasterPage.getTotalElements());
    }

    @Override
    @Transactional
    public OrderDTO cancel(OrderDTO orderDTO) {
        OrderMaster orderMaster=new OrderMaster();
        //判断订单状态
        if(!orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())){
            log.error("【取消订单】 订单状态不正确,orderId={},orderStatus={}",orderDTO.getOrderId(),orderDTO.getOrderStatus());
            throw  new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }
        //修改订单状态
        orderDTO.setOrderStatus(OrderStatusEnum.CANCEL.getCode());
        BeanUtils.copyProperties(orderDTO,orderMaster);
        OrderMaster updateResult=orderMasterRepository.save(orderMaster);
        if(updateResult==null){
            log.error("【取消订单】 更新失败,orderMaster={}",orderMaster);
            throw  new SellException(ResultEnum.ORDER_UPDATE_FAIL);
        }
        //返还库存
        /*List<OrderDetail> orderDetailList=orderDTO.getOrderDetailList();
        for(OrderDetail orderDetail:orderDetailList){
            if(productInfoRepository.findById(orderDetail.getProductId()).get()==null){
                throw new SellException(ResultEnum.)
            }
        }*/
        if(CollectionUtils.isEmpty(orderDTO.getOrderDetailList())){
            log.error("【取消订单】 订单中无商品详情, orderDTO={}",orderDTO);
            throw  new SellException(ResultEnum.ORDER_DETAIL_EMPTY);
        }
        List<CartDTO> cartDTOList=orderDTO.getOrderDetailList().stream().map(e->new CartDTO(e.getProductId(),e.getProductQuantity())).collect(Collectors.toList());
        productService.increaseStock(cartDTOList);
        //如果已支付,需要退款
        if(orderDTO.getPayStatus().equals(PayStatusEnum.SUCCESS.getCode())){
            //TODO
        }
        return orderDTO;
    }

    @Override
    @Transactional
    public OrderDTO finish(OrderDTO orderDTO) {
        //判断订单状态
        if(orderDTO.getOrderStatus().equals(OrderStatusEnum.FINISHED.getCode())||orderDTO.getOrderStatus()==OrderStatusEnum.CANCEL.getCode()){
            log.error("【完结订单】 订单状态不正确,orderId={},orderStatus={}",orderDTO.getOrderId(),orderDTO.getOrderStatus());
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }
        //修改订单状态
        OrderMaster orderMaster=new OrderMaster();
        orderDTO.setOrderStatus(OrderStatusEnum.FINISHED.getCode());
        BeanUtils.copyProperties(orderDTO,orderMaster);
        OrderMaster UpdateResult=orderMasterRepository.save(orderMaster);
        if(UpdateResult==null){
                log.error("【完结订单】 更新失败,orderMaster={}",orderMaster);
                throw  new SellException(ResultEnum.ORDER_UPDATE_FAIL);
        }
        return orderDTO;
    }

    @Override
    public OrderDTO paid(OrderDTO orderDTO) {
        //判断订单状态
        if(!orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())){
            log.error("【支付订单】 订单状态不正确,orderId={},orderStatus={}",orderDTO.getOrderId(),orderDTO.getOrderStatus());
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }
        //判断支付状态
        if(!orderDTO.getPayStatus().equals(PayStatusEnum.WAIT.getCode())){
            log.error("【支付订单】 支付状态不正确,orderDTO={}",orderDTO);
            throw new SellException(ResultEnum.ORDER_PAY_STATUS_ERROR);
        }
        //修改支付状态
        OrderMaster orderMaster=new OrderMaster();
        orderDTO.setPayStatus(PayStatusEnum.SUCCESS.getCode());
        BeanUtils.copyProperties(orderDTO,orderMaster);
        OrderMaster UpdateResult=orderMasterRepository.save(orderMaster);
        if(UpdateResult==null){
            log.error("【完结订单】 更新失败,orderMaster={}",orderMaster);
            throw  new SellException(ResultEnum.ORDER_UPDATE_FAIL);
        }
        return orderDTO;
    }
}
