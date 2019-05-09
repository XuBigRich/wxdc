package top.piao888.wxdc.exception;

import top.piao888.wxdc.enums.ResultEnum;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName SellException.java
 * @Description TODO
 * @createTime 2019年03月20日 15:10:00
 */
public class SellException extends RuntimeException{
    private Integer code;
    private String message;
    public SellException(ResultEnum resultEnum){
        super(resultEnum.getMessage());
        this.code=resultEnum.getCode();
    }
    public SellException(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
