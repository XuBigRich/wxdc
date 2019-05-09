package top.piao888.wxdc.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import top.piao888.wxdc.domain.OrderMaster;

import java.util.List;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName OrderMasterRepository.java
 * @Description TODO
 * @createTime 2019年03月15日 15:29:00
 */
public interface OrderMasterRepository extends JpaRepository<OrderMaster,String> {
    Page<OrderMaster> findAllByBuyerOpenid (String openid,Pageable pageable);
}
