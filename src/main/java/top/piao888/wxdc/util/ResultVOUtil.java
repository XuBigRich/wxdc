package top.piao888.wxdc.util;

import top.piao888.wxdc.vo.ResultVO;

import java.util.List;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName ResultVOUtil.java
 * @Description TODO
 * @createTime 2019年03月14日 18:14:00
 */
public class ResultVOUtil {
    public static ResultVO success(Object object){
        ResultVO<Object> resultVO=new ResultVO();
        resultVO.setCode(0);
        resultVO.setMsg("成功");
        resultVO.setData(object);
        return resultVO;
    }
    public static ResultVO success(){
        return success(null);
    }
    public static ResultVO error(Integer code,String msg){
        ResultVO<Object> resultVO=new ResultVO();
        resultVO.setCode(code);
        resultVO.setMsg(msg);
        return resultVO;
    }
}
