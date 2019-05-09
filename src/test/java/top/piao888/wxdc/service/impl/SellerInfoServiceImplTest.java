package top.piao888.wxdc.service.impl;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.annotation.Async;
import org.springframework.test.context.junit4.SpringRunner;
import top.piao888.wxdc.domain.SellerInfo;

import static org.junit.Assert.*;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName SellerInfoServiceImplTest.java
 * @Description TODO
 * @createTime 2019年03月15日 15:51:00
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SellerInfoServiceImplTest {
    @Autowired
    private SellerInfoServiceImpl sellerInfoService;
    @Test
    public void login() {
        SellerInfo sellerInfo=sellerInfoService.login("admin","123456");
        System.out.println(sellerInfo);
        Assert.assertNotNull(sellerInfo);
    }
}