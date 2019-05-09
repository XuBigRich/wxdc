package top.piao888.wxdc.enums;

import lombok.Getter;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName PayStatusEnum.java
 * @Description TODO
 * @createTime 2019年03月15日 16:23:00
 */
@Getter
public enum PayStatusEnum {
    WAIT(0,"等待支付"),SUCCESS(1,"支付成功");
    private Integer code;
    private String message;

    PayStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
