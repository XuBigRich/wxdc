package top.piao888.wxdc.service;

import top.piao888.wxdc.domain.ProductCategory;

import java.util.List;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName CategoryService.java
 * @Description TODO
 * @createTime 2019年03月04日 19:49:00
 */
public interface CategoryService {
    ProductCategory findOne(Integer categoryId);
    List<ProductCategory> findAll();
    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);
    ProductCategory save(ProductCategory productCategory);
}
