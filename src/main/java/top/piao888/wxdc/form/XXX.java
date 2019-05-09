package top.piao888.wxdc.form;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName XXX.java
 * @Description TODO
 * @createTime 2019年03月26日 15:29:00
 */
@Data
public class XXX {
    @NotEmpty(message="手机号必填")
    private String openid;
    private Integer page=0;
    private Integer size=10;
}
