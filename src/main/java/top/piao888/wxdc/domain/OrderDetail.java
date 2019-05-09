package top.piao888.wxdc.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName OrderDetail.java
 * @Description TODO
 * @createTime 2019年03月15日 15:01:00
 */
@Entity
@Data
@DynamicUpdate

//@JsonIgnoreProperties(ignoreUnknown = true)
// Jackson忽略属性
//场景：json串传来了20个属性，但是我只要其中的4个。
public class OrderDetail {
    @Id
    private String detailId;
    /*订单id*/
    private String orderId;
    /*商品id*/
    private String productId;
    /*商品名称*/
    private String productName;
    /*当前价格,单位分*/
    private BigDecimal productPrice;
    /*数量*/
    private Integer productQuantity;
    /*小图*/
    private String productIcon;
}
