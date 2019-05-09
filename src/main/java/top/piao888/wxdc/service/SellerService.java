package top.piao888.wxdc.service;

import top.piao888.wxdc.domain.SellerInfo;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName SellerService.java
 * @Description TODO
 * @createTime 2019年03月28日 14:05:00
 */
public interface SellerService {
    /*
    * 通过openid查询卖家端信息
    * */
    SellerInfo findSellerInfoByOpenid(String openid);
}
