package top.piao888.wxdc.dto;

import lombok.Data;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName CartDTO.java
 * @Description TODO
 * @createTime 2019年03月20日 16:45:00
 */
@Data
public class CartDTO {
    private String productId;
    private Integer productQuantity;

    public CartDTO(String productId, Integer productQuantity) {
        this.productId = productId;
        this.productQuantity = productQuantity;
    }
}
