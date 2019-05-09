package top.piao888.wxdc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import top.piao888.wxdc.domain.SellerInfo;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName SellerInfoRepository.java
 * @Description TODO
 * @createTime 2019年03月15日 15:19:00
 */
public interface SellerInfoRepository extends JpaRepository<SellerInfo,String> {
    SellerInfo findByOpenid(String openid);
    SellerInfo getOneByUsernameAndPassword(String username,String password);
}
