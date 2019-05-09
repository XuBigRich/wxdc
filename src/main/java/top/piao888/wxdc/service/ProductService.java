package top.piao888.wxdc.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import top.piao888.wxdc.domain.ProductCategory;
import top.piao888.wxdc.domain.ProductInfo;
import top.piao888.wxdc.dto.CartDTO;

import java.util.List;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName ProductService.java
 * @Description TODO
 * @createTime 2019年03月13日 18:41:00
 */
public interface ProductService {
    ProductInfo findOne(String productId);
    List<ProductInfo> findUpAll();
    Page<ProductInfo> findAll(Pageable pageable);
    ProductInfo save(ProductInfo productCategory);
    //加库存
    void increaseStock(List<CartDTO> cartDTOList);
    //减库存
    void decreaseStock(List<CartDTO> cartDTOList);
}
