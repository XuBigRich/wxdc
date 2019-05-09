package top.piao888.wxdc.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.piao888.wxdc.service.RedisLock;
import top.piao888.wxdc.service.SecKillService;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName SecKillController.java
 * @Description TODO
 * @createTime 2019年03月27日 18:35:00
 */
@RestController
@RequestMapping("/skill")
@Slf4j
public class SecKillController {
    @Autowired
    private SecKillService seckillService;
    /*
    * 查询秒杀活动特价商品的信息
    * */
    @GetMapping("/query/{productId}")
    public String query(@PathVariable String productId) throws Exception{
        return seckillService.querySecKillProductInfo(productId);
    }
    /*
    * 秒杀,没有抢到获得 一个 抱歉,强盗返回剩余的库存量
    * */
    @GetMapping("/order/{productId}")
    public String skill(@PathVariable String productId) throws Exception{
        log.info("@skill request,productId:"+productId);
        seckillService.orderProductMockDiffUser(productId);
        return seckillService.querySecKillProductInfo(productId);
    }
}
