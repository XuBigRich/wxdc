package top.piao888.wxdc.service;

import top.piao888.wxdc.domain.SellerInfo;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName SellerInfoService.java
 * @Description TODO
 * @createTime 2019年03月15日 15:42:00
 */
public interface SellerInfoService {
    SellerInfo login(String username,String password);
}
