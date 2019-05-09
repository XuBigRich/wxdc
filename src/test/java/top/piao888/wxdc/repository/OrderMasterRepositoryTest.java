package top.piao888.wxdc.repository;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;
import top.piao888.wxdc.domain.OrderMaster;

import java.math.BigDecimal;

import static org.junit.Assert.*;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName OrderMasterRepositoryTest.java
 * @Description TODO
 * @createTime 2019年03月15日 17:45:00
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderMasterRepositoryTest {
    @Autowired
    private OrderMasterRepository repository;
    @Test
    public void saveTest(){
        OrderMaster orderMaster=new OrderMaster();
        orderMaster.setOrderId("1234567");
        orderMaster.setBuyerName("师兄");
        orderMaster.setBuyerPhone("123456789123");
        orderMaster.setBuyerAddress("慕课网");
        orderMaster.setBuyerOpenid("110110");
        orderMaster.setOrderAmount(new BigDecimal(2.5));
        OrderMaster result=repository.save(orderMaster);
        Assert.assertNotNull(result);
    }
    @Test
    public void findAllByBuyerOpenid() {
        PageRequest pageRequest=PageRequest.of(0,1);
        Page<OrderMaster> request=repository.findAllByBuyerOpenid("110110",pageRequest);
        System.out.println(request.getContent().size());
        Assert.assertNotEquals(0,request.getTotalElements());
    }
}