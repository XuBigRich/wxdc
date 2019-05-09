package top.piao888.wxdc.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName ProductInfoVO.java
 * @Description TODO
 * @createTime 2019年03月14日 14:48:00
 */
@Data
public class ProductInfoVO implements Serializable {
    private static final long serialVersionUID = 2462627439227324220L;
    @JsonProperty("id")
    private String productId;
    @JsonProperty("name")
    private String productName;
    @JsonProperty("price")
    private BigDecimal productPrice;
    @JsonProperty("description")
    private String productDescription;
    @JsonProperty("icom")
    private String productIcom;
}
