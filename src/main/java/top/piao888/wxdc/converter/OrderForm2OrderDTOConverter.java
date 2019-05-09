package top.piao888.wxdc.converter;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import top.piao888.wxdc.domain.OrderDetail;
import top.piao888.wxdc.dto.CartDTO;
import top.piao888.wxdc.dto.OrderDTO;
import top.piao888.wxdc.enums.ResultEnum;
import top.piao888.wxdc.exception.SellException;
import top.piao888.wxdc.form.OrderForm;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName OrderForm2OrderDTOConverter.java
 * @Description TODO
 * @createTime 2019年03月25日 11:12:00
 */
@Slf4j
public class OrderForm2OrderDTOConverter {


    public static OrderDTO convert(OrderForm orderForm){
        List<OrderDetail> orderDetailList;
        OrderDTO orderDTO=new OrderDTO();
        orderDTO.setBuyerName(orderForm.getName());
        orderDTO.setBuyerPhone(orderForm.getPhone());
        orderDTO.setBuyerOpenid(orderForm.getOpenid());
        orderDTO.setBuyerAddress(orderForm.getAddress());

        // json -> list
        orderDetailList=jack(orderForm);

        //徒手 撸
//        orderDetailList=tsl(orderForm);
        orderDTO.setOrderDetailList(orderDetailList);
        System.out.println(orderDTO);
        return orderDTO;
    }
    public static List<OrderDetail> jack(OrderForm orderForm){
        ObjectMapper mapper = new ObjectMapper();
        List<OrderDetail> orderDetailList;
        try {
            JavaType javatype = mapper.getTypeFactory().constructParametricType(
                    List.class, OrderDetail.class);
            System.out.println(orderForm.getItems());
            orderDetailList= mapper.readValue(orderForm.getItems(), javatype);
        }catch (Exception e){
            e.printStackTrace();
            log.error("【对象转换】 错误,string={}",orderForm.getItems());
            throw new SellException(ResultEnum.PARAM_ERROR);

        }
        return orderDetailList;
    }

    public static List<OrderDetail> tsl(OrderForm orderForm){
        List<String> bb;
        String request=orderForm.getItems();
        request=request.replace("[{","");
        //System.out.println(request);
        request=request.replace("}]","");
        //System.out.println(request);
        if(request.contains("},{")) {
            String[] aa = request.split("\\},\\{");
            System.out.println(aa.toString());
            bb= Arrays.asList(aa);
        }else {
            bb =Arrays.asList(request);
        }
        List<OrderDetail> orderDetailList= bb.stream().map(e->convert(e)).collect(Collectors.toList());
        return orderDetailList;
    }
    private static OrderDetail convert(String string){
         OrderDetail orderDetail=new OrderDetail();
         String[] x=  string.split(",");
         String[] a=x[0].split(":");
         String[] b=x[1].split(":");
         String productId=a[1].replace("\"","");
         orderDetail.setProductId(productId);
         orderDetail.setProductQuantity(new Integer(b[1]));

        return orderDetail;
    }
}
