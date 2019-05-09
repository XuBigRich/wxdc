package top.piao888.wxdc.domain;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;
import top.piao888.wxdc.enums.OrderStatusEnum;
import top.piao888.wxdc.enums.PayStatusEnum;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName OrderMaster.java
 * @Description TODO
 * @createTime 2019年03月15日 12:01:00
 */
@Entity
@DynamicUpdate
@Data
public class OrderMaster {
    @Id
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
    private Integer orderStatus= OrderStatusEnum.NEW.getCode();
    /*支付状态 默认未支付*/
    private Integer payStatus=PayStatusEnum.WAIT.getCode();
/*    @Transient
    private List<OrderDetail> orderDetailList;*/
    /*创建时间*/
    private Date createTime;
   /* 更新时间*/
    private Date updateTime;
}
