package top.piao888.wxdc.util;

import java.util.Random;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName KeyUtil.java
 * @Description TODO
 * @createTime 2019年03月20日 15:48:00
 */
public class KeyUtil {
    /*
    * 生成唯一主键
    * 格式: 时间+随机数
    * */
    public static synchronized String genUniqueKey(){
        Random random=new Random();
        Integer number=random.nextInt(900000)+100000;
        return  System.currentTimeMillis() +String.valueOf(number);
    }
}
