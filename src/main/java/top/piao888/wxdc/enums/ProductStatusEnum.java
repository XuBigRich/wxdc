package top.piao888.wxdc.enums;

import lombok.Getter;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName ProductStatusEnum.java
 * @Description 商品状态
 * @createTime 2019年03月14日 10:36:00
 */
@Getter
public enum ProductStatusEnum {
    UP(0,"在架"),
    DOWN(1,"下架");
    private Integer code;
    private String message;

    ProductStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
