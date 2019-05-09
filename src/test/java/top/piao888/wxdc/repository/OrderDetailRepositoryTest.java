package top.piao888.wxdc.repository;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import top.piao888.wxdc.domain.OrderDetail;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName OrderDetailRepositoryTest.java
 * @Description TODO
 * @createTime 2019年03月20日 11:14:00
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderDetailRepositoryTest {
    @Autowired
    private OrderDetailRepository orderDetailRepository;
    @Test
    public void saveTest(){
        OrderDetail orderDetail=new OrderDetail();
        orderDetail.setDetailId("1234567810");
        orderDetail.setOrderId("11111112");
        orderDetail.setProductIcon("http://xxxx.jpg");
        orderDetail.setProductId("11111112");
        orderDetail.setProductName("皮蛋粥");
        orderDetail.setProductPrice(new BigDecimal(2.2));
        orderDetail.setProductQuantity(3);
        OrderDetail result=orderDetailRepository.save(orderDetail);
        Assert.assertNotNull(result);
    }
    @Test
    public void findByOrderId() {
       List<OrderDetail> result= orderDetailRepository.findByOrderId("11111111");
       Assert.assertNotEquals(0,result.size());
    }

}