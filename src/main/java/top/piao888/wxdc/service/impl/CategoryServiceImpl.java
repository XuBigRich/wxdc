package top.piao888.wxdc.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.piao888.wxdc.domain.ProductCategory;
import top.piao888.wxdc.repository.ProductCategoryRepository;
import top.piao888.wxdc.service.CategoryService;

import java.util.List;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName CategoryServiceImpl.java
 * @Description TODO
 * @createTime 2019年03月13日 16:51:00
 */
@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private ProductCategoryRepository repository;
    @Override
    public ProductCategory findOne(Integer categoryId) {
        return repository.findById(categoryId).get();
    }

    @Override
    public List<ProductCategory> findAll() {
        return repository.findAll();
    }

    @Override
    public List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList) {
        return repository.findByCategoryTypeIn(categoryTypeList);
    }

    @Override
    public ProductCategory save(ProductCategory productCategory) {
        return repository.save(productCategory);
    }
}
