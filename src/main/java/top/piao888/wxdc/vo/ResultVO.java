package top.piao888.wxdc.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName ResultVO.java
 * @Description http请求返回的最外层对象
 * @createTime 2019年03月14日 14:32:00
 */
@Data
public class ResultVO<T> implements Serializable {

    private static final long serialVersionUID = 268616920234673468L;
    /*错误码*/
    private Integer code;
    /*提示信息*/
    private String msg;
    /*具体内容*/
    private T data;
}
