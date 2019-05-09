package top.piao888.wxdc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import top.piao888.wxdc.domain.OrderDetail;

import java.util.List;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName OrderDetailRepository.java
 * @Description TODOpa
 * @createTime 2019年03月1515:28:00
 */
public interface OrderDetailRepository extends JpaRepository<OrderDetail,String> {
    List<OrderDetail> findByOrderId(String orderId);
}
