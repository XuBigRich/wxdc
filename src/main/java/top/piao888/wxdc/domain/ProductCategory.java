package top.piao888.wxdc.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName ProductCategory.java
 * @Description TODO
 * @createTime 2019年03月04日 16:10:00
 */
@Entity
@DynamicUpdate //动态更新  会更新时间  需要搭配 数据库建表时ON UPDATE CURRENT_TIMESTAMP
@Data
public class ProductCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer categoryId;
    private String categoryName;
    private Integer categoryType;

    public ProductCategory() {
    }

    public ProductCategory(String categoryName, Integer categoryType) {
        this.categoryName = categoryName;
        this.categoryType = categoryType;
    }
}
