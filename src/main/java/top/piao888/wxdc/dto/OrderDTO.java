package top.piao888.wxdc.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import top.piao888.wxdc.domain.OrderDetail;
import top.piao888.wxdc.enums.OrderStatusEnum;
import top.piao888.wxdc.enums.PayStatusEnum;
import top.piao888.wxdc.util.serializer.Date2LongSerializer;

import javax.persistence.Id;
import javax.persistence.Transient;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName OrderDTO.java
 * @Description TODO
 * @createTime 2019年03月20日 11:52:00
 */
@Data
//不返回 null值 的注解
//@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
//@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderDTO {
    private String orderId;
    /*买家名字*/
    private String buyerName;
    /*买家电话*/
    private String buyerPhone;
    /*买家地址*/
    private String buyerAddress;
    /*买家微信openid*/
    private String buyerOpenid;
    /*订单总金额*/
    private BigDecimal orderAmount;
    /*订单状态 默认支付*/
    private Integer orderStatus;
    /*支付状态 默认未支付*/
    private Integer payStatus;
    /*创建时间*/
    @JsonSerialize(using = Date2LongSerializer.class)
    private Date createTime;
    /* 更新时间*/
    @JsonSerialize(using = Date2LongSerializer.class)
    private Date updateTime;

    private List<OrderDetail> orderDetailList;

}
