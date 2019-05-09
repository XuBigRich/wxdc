package top.piao888.wxdc.service.impl;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import top.piao888.wxdc.domain.SellerInfo;

import static org.junit.Assert.*;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName SellerServiceImplTest.java
 * @Description TODO
 * @createTime 2019年03月28日 14:10:00
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SellerServiceImplTest {
@Autowired
private SellerServiceImpl sellerService;
private static final String openid="abc";
    @Test
    public void findSellerInfoByOpenid() {
        SellerInfo sellerInfo=sellerService.findSellerInfoByOpenid(openid);
        Assert.assertEquals(openid ,sellerInfo.getOpenid());
    }
}