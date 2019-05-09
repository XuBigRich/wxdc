package top.piao888.wxdc.domain;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName SellerInfo.java
 * @Description TODO
 * @createTime 2019年03月15日 15:12:00
 */
@Entity
@Data
@DynamicUpdate
public class SellerInfo {
    @Id
    private String id;
    private String username;
    private String password;
    private String openid;
}
