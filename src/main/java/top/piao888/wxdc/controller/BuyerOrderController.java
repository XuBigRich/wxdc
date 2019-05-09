package top.piao888.wxdc.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import top.piao888.wxdc.converter.OrderForm2OrderDTOConverter;
import top.piao888.wxdc.dto.OrderDTO;
import top.piao888.wxdc.enums.ResultEnum;
import top.piao888.wxdc.exception.SellException;
import top.piao888.wxdc.form.OrderForm;
import top.piao888.wxdc.form.XXX;
import top.piao888.wxdc.service.OrderService;
import top.piao888.wxdc.util.ResultVOUtil;
import top.piao888.wxdc.vo.ResultVO;

import javax.validation.Valid;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName BuyerOrderController.java
 * @Description TODO
 * @createTime 2019年03月22日 15:17:00
 */
@RestController
@RequestMapping("/buyer/order")
@Slf4j
public class BuyerOrderController {
    @Autowired
    private OrderService orderService;
    //创建订单
    @PostMapping("/create")
    public ResultVO<Map<String,String>> create(@Valid OrderForm orderForm, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            log.error("【创建订单表单错误】 Message={}",bindingResult.getFieldError().getDefaultMessage());
            throw new SellException(ResultEnum.PARAM_ERROR.getCode(),bindingResult.getFieldError().getDefaultMessage());
        }
        OrderDTO orderDTO=OrderForm2OrderDTOConverter.convert(orderForm);
        if(CollectionUtils.isEmpty(orderDTO.getOrderDetailList())){
            log.error("【创建订单】 购物车不能为空");
            throw new SellException(ResultEnum.CART_EMPTY);
        }
        OrderDTO createResult=orderService.create(orderDTO);
        Map<String,String> map=new HashMap<>();
        map.put("orderId",createResult.getOrderId());

        return ResultVOUtil.success(map);
    }
    //订单列表
    @GetMapping("/list")
    public ResultVO<List<OrderDTO>> list(@Valid XXX xxx, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            log.error("【查询订单列表错误】 Message={}",bindingResult.getFieldError().getDefaultMessage());
            throw new SellException(ResultEnum.PARAM_ERROR.getCode(),bindingResult.getFieldError().getDefaultMessage());
        }
        PageRequest pageRequest=PageRequest.of(xxx.getPage(),xxx.getSize());
        Page<OrderDTO> orderDTOPage =orderService.findList(xxx.getOpenid(),pageRequest);
        return ResultVOUtil.success(orderDTOPage.getContent());
    }
    //订单详情
    @GetMapping("/detail")
    public ResultVO<OrderDTO> detail(@RequestParam("openid") String openid,@RequestParam("orderId")String orderId){
        //TODO 不安全的做法 ，改进
        OrderDTO orderDTO= orderService.findOne(orderId);
        return ResultVOUtil.success(orderDTO);
    }
    //取消订单
    @PostMapping("/cancel")
    public ResultVO cancel(@RequestParam("openid") String openid,@RequestParam("orderId") String orderId){
        //TODO 不安全的做法 ，改进
        OrderDTO orderDTO=orderService.findOne(orderId);
        orderService.cancel(orderDTO);
        return ResultVOUtil.success();
    }
}
