package top.piao888.wxdc.repository;

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
 * @ClassName ProductCategoryRepositoryTest.java
 * @Description TODO
 * @createTime 2019年03月04日 16:16:00
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryRepositoryTest {
    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    @Test
    public void findOneTest(){
        ProductCategory productCategory= (ProductCategory)productCategoryRepository.findById(1).get();
        System.out.println(productCategory);
    }
    @Test
    public void saveTest(){
        /*保存*/
        ProductCategory productCategory=new ProductCategory();
        /*更新 */
//        ProductCategory productCategory= (ProductCategory)productCategoryRepository.findById(3).get();
        productCategory.setCategoryName("女生最爱榜");
        productCategory.setCategoryType(3);
        ProductCategory productCategoryq=productCategoryRepository.save(productCategory);
        Assert.assertNotNull(productCategoryq);
//        Assert.assertNotEquals(null,productCategoryq);
    }
    @Test
    public void findByCategoryTypeInTest(){
        List<Integer> list = Arrays.asList(1,2,3);
        List<ProductCategory> result= productCategoryRepository.findByCategoryTypeIn(list);
        Assert.assertNotNull(result.size());
    }}