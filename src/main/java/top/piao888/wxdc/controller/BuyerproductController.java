package top.piao888.wxdc.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.piao888.wxdc.domain.ProductCategory;
import top.piao888.wxdc.domain.ProductInfo;
import top.piao888.wxdc.service.CategoryService;
import top.piao888.wxdc.service.ProductService;
import top.piao888.wxdc.util.ResultVOUtil;
import top.piao888.wxdc.vo.ProductInfoVO;
import top.piao888.wxdc.vo.ProductVO;
import top.piao888.wxdc.vo.ResultVO;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName controller.java
 * @Description 买家商品
 * @createTime 2019年03月14日 12:01:00
 */
@RestController
@RequestMapping("/buyer/product")

/*
缓存 要实现序列化
@Cacheable //调用时  没有缓存  进行缓存   有缓存 在缓存中查询
@Cacheput //执行完毕后 把返回的缓存更新
@CacheEvict //执行完毕后 删除 缓存
*/

//@CacheConfig(cacheNames = "product")  //加他 可以代替@Cacheable中 cacheNames属性
public class BuyerproductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/list")
//    @Cacheable(cacheNames="product",key="123")
//    @Cacheable(key="123")
 //   @Cacheable(key="#sfhc",condition = "#sfhc.contains('S')",unless = "#result.getCode()!=0")//动态 key  条件 如果sfhc包含S 就 缓存    条件 如果 不  result.code不等于0就缓存
    public ResultVO list(@RequestParam("sfhc") String sfhc){

        //1. 查询所有上架商品
        List<ProductInfo> productInfos=productService.findUpAll();
        //2.查询类目（一次性查询）
        List<Integer> categoryTypeList=productInfos.stream().map(ProductInfo::getCategoryType).collect(Collectors.toList());
        List<ProductCategory> productCategories=categoryService.findByCategoryTypeIn(categoryTypeList);
        //3.数据拼接
        List<ProductVO> productVOList=new ArrayList<>();
        for(ProductCategory productCategory:productCategories){
            ProductVO productVO=new ProductVO();
            productVO.setCategoryType(productCategory.getCategoryType());
            productVO.setCategoryName(productCategory.getCategoryName());
            List<ProductInfoVO> productInfoVOList=new ArrayList<>();
            for(ProductInfo productInfo:productInfos){
                if(productInfo.getCategoryType().equals(productVO.getCategoryType())){
                    ProductInfoVO productInfoVO=new ProductInfoVO();
                    /*将一个值的属性复制到 另一个值中*/
                    BeanUtils.copyProperties(productInfo,productInfoVO);
                    productInfoVOList.add(productInfoVO);
                }
            }
            productVO.setProductInfoVOList(productInfoVOList);
            productVOList.add(productVO);
        }

        return ResultVOUtil.success(productVOList);
    }
}
