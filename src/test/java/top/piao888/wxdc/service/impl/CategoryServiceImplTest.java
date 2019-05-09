package top.piao888.wxdc.service.impl;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import top.piao888.wxdc.domain.ProductCategory;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName CategoryServiceImplTest.java
 * @Description TODO
 * @createTime 2019年03月13日 17:16:00
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CategoryServiceImplTest {
    @Autowired
    private CategoryServiceImpl categoryService;
    @Test
    public void findOne() {
       ProductCategory productCategory= categoryService.findOne(1);
       Assert.assertEquals(new Integer(1),productCategory.getCategoryId());
    }

    @Test
    public void findAll() {
        List<ProductCategory> productCategories= categoryService.findAll();
        Assert.assertNotEquals(0, productCategories.size());
    }

    @Test
    public void findByCategoryTypeIn() {
        List<ProductCategory> productCategories=categoryService.findByCategoryTypeIn(Arrays.asList(1,2,3));
        Assert.assertNotEquals(0, productCategories.size());
    }

    @Test
    public void save() {
        ProductCategory productCategory = new ProductCategory("男生专享", 10);
        ProductCategory result = categoryService.save(productCategory);
        Assert.assertNotNull(result);
    }
}