package top.piao888.wxdc.service.impl;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;
import top.piao888.wxdc.domain.ProductInfo;
import top.piao888.wxdc.enums.ProductStatusEnum;

import javax.swing.*;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName ProductServiceInfoTest.java
 * @Description TODO
 * @createTime 2019年03月14日 10:51:00
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductServiceInfoTest {
@Autowired
private ProductServiceImpl productServiceImpl;
    @Test
    public void findOne() {
       ProductInfo productInfo= productServiceImpl.findOne("123456");
        Assert.assertEquals("123456",productInfo.getProductId());
    }

    @Test
    public void findUpAll() {
       List<ProductInfo> productInfos= productServiceImpl.findUpAll();
       Assert.assertNotEquals(0,productInfos);
    }

    @Test
    public void findAll() {
       PageRequest request= PageRequest.of(0,2);
       Page<ProductInfo> productInfoPage= productServiceImpl.findAll(request);
         System.out.println(productInfoPage.getTotalElements());
    }

    @Test
    public void save() {
        ProductInfo productInfo=new ProductInfo();
        productInfo.setProductId("123457");
        productInfo.setProductName("皮皮虾");
        productInfo.setProductPrice(new BigDecimal(3.2));
        productInfo.setProductStock(100);
        productInfo.setProductDescription("很好吃的虾");
        productInfo.setProductIcon("http://xxxx.jpg");
        productInfo.setProductStatus(ProductStatusEnum.DOWN.getCode());
        productInfo.setCategoryType(2);

        ProductInfo result=productServiceImpl.save(productInfo);
        Assert.assertNotNull(result);
    }
}