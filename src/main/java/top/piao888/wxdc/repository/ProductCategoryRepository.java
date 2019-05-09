package top.piao888.wxdc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import top.piao888.wxdc.domain.ProductCategory;

import java.util.List;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName ProductCategoryRepository.java
 * @Description TODO
 * @createTime 2019年03月04日 16:15:00
 */
public interface ProductCategoryRepository extends JpaRepository<ProductCategory,Integer> {
    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);
}
