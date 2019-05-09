package top.piao888.wxdc.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.piao888.wxdc.domain.ProductInfo;
import top.piao888.wxdc.dto.CartDTO;
import top.piao888.wxdc.enums.ProductStatusEnum;
import top.piao888.wxdc.enums.ResultEnum;
import top.piao888.wxdc.exception.SellException;
import top.piao888.wxdc.repository.ProductInfoRepository;
import top.piao888.wxdc.service.ProductService;

import java.util.List;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName ProductServiceInfo.java
 * @Description TODO
 * @createTime 2019年03月13日 18:47:00
 */
@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductInfoRepository productInfoRepository;
    @Override
    public ProductInfo findOne(String productId) {
        return productInfoRepository.findById(productId).get();
    }

    @Override
    public List<ProductInfo> findUpAll() {
        return productInfoRepository.findByProductStatus(ProductStatusEnum.UP.getCode());
    }

    @Override  //带有分页 Pageable 是分页 的页
    public Page<ProductInfo> findAll(Pageable pageable) {
        return productInfoRepository.findAll(pageable);
    }

    @Override
    public ProductInfo save(ProductInfo productCategory) {
        return productInfoRepository.save(productCategory);
    }

    @Override
    @Transactional
    public void increaseStock(List<CartDTO> cartDTOList) {
        for(CartDTO cartDTO:cartDTOList){
            ProductInfo productInfo=(ProductInfo)productInfoRepository.findById(cartDTO.getProductId()).get();
            if(productInfo==null){
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            Integer result= productInfo.getProductStock()+cartDTO.getProductQuantity();
            productInfo.setProductStock(result);
            productInfoRepository.save(productInfo);
        }

    }

    @Override
    @Transactional
    public void decreaseStock(List<CartDTO> cartDTOList) {
        for(CartDTO cartDTO:cartDTOList){
            ProductInfo productInfo=(ProductInfo)productInfoRepository.findById(cartDTO.getProductId()).get();
            if (productInfo==null){
               throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            Integer result=productInfo.getProductStock()-cartDTO.getProductQuantity();
            if(result<0){
                throw new SellException(ResultEnum.PRODUCT_STOCK_ERROR);
            }
            productInfo.setProductStock(result);
            productInfoRepository.save(productInfo);
        }
    }
}
