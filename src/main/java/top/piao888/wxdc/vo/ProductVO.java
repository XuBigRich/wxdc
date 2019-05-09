package top.piao888.wxdc.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName ProductVO.java
 * @Description 商品（包含类目）
 * @createTime 2019年03月14日 14:41:00
 */
@Data
public class ProductVO implements Serializable {
    private static final long serialVersionUID = -2976798207442199506L;
    @JsonProperty("name")
    private String categoryName;
    @JsonProperty("type")
    private Integer categoryType;
    @JsonProperty("foods")
    private List<ProductInfoVO> productInfoVOList;
}
