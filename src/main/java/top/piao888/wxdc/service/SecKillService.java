package top.piao888.wxdc.service;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName SecKillService.java
 * @Description TODO
 * @createTime 2019年03月27日 18:39:00
 */
public interface SecKillService {
    String queryMap(String producId);
    String querySecKillProductInfo(String productId);
    void orderProductMockDiffUser(String productId);
}
