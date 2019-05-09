package top.piao888.wxdc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import top.piao888.wxdc.domain.ProductInfo;

import java.util.List;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName ProductInfoRepository.java
 * @Description TODO
 * @createTime 2019年03月13日 18:30:00
 */
public interface ProductInfoRepository extends JpaRepository<ProductInfo,String> {
    List<ProductInfo> findByProductStatus(Integer productStatus);
}
