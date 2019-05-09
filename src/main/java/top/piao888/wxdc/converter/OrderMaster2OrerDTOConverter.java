package top.piao888.wxdc.converter;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import top.piao888.wxdc.domain.OrderMaster;
import top.piao888.wxdc.dto.OrderDTO;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName OrderMaster2OrerDTOConverter.java
 * @Description TODO
 * @createTime 2019年03月21日 14:59:00
 */
public class OrderMaster2OrerDTOConverter {
    public static List convert(List<OrderMaster> orderMasterList){
        return  orderMasterList.stream().map(e->convert(e)).collect(Collectors.toList());
    }
    public static OrderDTO convert(OrderMaster orderMaster){
        OrderDTO orderDTO=new OrderDTO();
        BeanUtils.copyProperties(orderMaster,orderDTO);
        return orderDTO;
    }
}
