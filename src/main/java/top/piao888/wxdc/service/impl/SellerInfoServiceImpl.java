package top.piao888.wxdc.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.piao888.wxdc.domain.SellerInfo;
import top.piao888.wxdc.repository.SellerInfoRepository;
import top.piao888.wxdc.service.SellerInfoService;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName SellerInfoServiceImpl.java
 * @Description TODO
 * @createTime 2019年03月15日 15:48:00
 */
@Service
public class SellerInfoServiceImpl implements SellerInfoService {
    @Autowired
    private SellerInfoRepository sellerInfoRepository;
    @Override
    public SellerInfo login(String username, String password) {
        return sellerInfoRepository.getOneByUsernameAndPassword(username,password);
    }
}
