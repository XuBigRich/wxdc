package top.piao888.wxdc.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.piao888.wxdc.domain.SellerInfo;
import top.piao888.wxdc.repository.SellerInfoRepository;
import top.piao888.wxdc.service.SellerService;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName SellerServiceImpl.java
 * @Description TODO
 * @createTime 2019年03月28日 14:07:00
 */
@Service
public class SellerServiceImpl implements SellerService {
    @Autowired
    private SellerInfoRepository sellerInfoRepository;
    @Override
    public SellerInfo findSellerInfoByOpenid(String openid) {
        return sellerInfoRepository.findByOpenid(openid);
    }
}
