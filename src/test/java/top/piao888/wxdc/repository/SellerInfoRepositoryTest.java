package top.piao888.wxdc.repository;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import top.piao888.wxdc.domain.SellerInfo;
import top.piao888.wxdc.util.KeyUtil;

import static org.junit.Assert.*;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName SellerInfoRepositoryTest.java
 * @Description TODO
 * @createTime 2019年03月28日 12:00:00
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SellerInfoRepositoryTest {
    @Autowired
    private SellerInfoRepository sellerInfoRepository;
    @Test
    public void save(){
        SellerInfo sellerInfo=new SellerInfo();
        sellerInfo.setId(KeyUtil.genUniqueKey());
        sellerInfo.setUsername("admin");
        sellerInfo.setPassword("admin");
        sellerInfo.setOpenid("abc");
        SellerInfo result= sellerInfoRepository.save(sellerInfo);
        Assert.assertNotNull(result);
    }

    @Test
    public void findByOpenid() {
        SellerInfo result=sellerInfoRepository.findByOpenid("abc");
        Assert.assertEquals("abc",result.getOpenid());
    }

    @Test
    public void getOneByUsernameAndPassword() {
    }
}