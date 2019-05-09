package top.piao888.wxdc.domain;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName ProductInfo.java
 * @Description TODO
 * @createTime 2019年03月13日 18:08:00
 */
@Entity
@Data
@DynamicUpdate
public class ProductInfo  {
    @Id
    private String productId;
    /*商品名称*/
    private String productName;
    /*单价*/
    private BigDecimal productPrice;
    /*库存*/
    private Integer productStock;
    /*描述*/
    private  String productDescription;
    /*小图*/
    private  String productIcon;
    /*商品状态,0正常1下架*/
    private Integer  productStatus;
    /*类目编号*/
    private Integer categoryType;

}
