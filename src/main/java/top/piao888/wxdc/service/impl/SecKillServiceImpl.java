package top.piao888.wxdc.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import top.piao888.wxdc.exception.SellException;
import top.piao888.wxdc.service.RedisLock;
import top.piao888.wxdc.service.SecKillService;
import top.piao888.wxdc.util.KeyUtil;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName SecKillServiceImpl.java
 * @Description TODO
 * @createTime 2019年03月27日 18:38:00
 */
@Component
public class SecKillServiceImpl implements SecKillService {
    static Map<String,Integer> products;
    static Map<String,Integer> stock;
    static Map<String,String> orders;
    @Autowired
    private RedisLock redisLock;
    private static final int TIMEOUT=10*1000;//超时时间 10秒钟 == 10*1000毫秒
    static {
        /*
        * 模拟多个表,商品信息表,库存表,秒杀成功订单表
        * */
        products=new HashMap<>();
        stock=new HashMap<>();
        orders=new HashMap<>();
        products.put("123456",100000);
        stock.put("123456",100000);
    }
    @Override
    public String queryMap(String producId) {
        return "国庆活动,皮蛋粥特价，限量份"
                +products.get(producId)
                +"还剩："+stock.get(producId)+" 份"
                +"该商品成功下单用户数目："
                + orders.size()+" 人";

    }

    @Override
    public String querySecKillProductInfo(String productId) {
        return queryMap(productId);
    }
//这段代码要保证单线程运行
    @Override
    public void orderProductMockDiffUser(String productId) {
        long time=System.currentTimeMillis()+TIMEOUT;
        //加锁
        if(!redisLock.lock(productId,String.valueOf(time))){
            throw new SellException(101,"哎呦喂,人也太多了,换个姿势试试");
        }

        //1.查询该商品库存,为0则活动结束。
        int stockNum=stock.get(productId);
        if(stockNum==0){
            throw new SellException(100,"活动结束");
        }else {
            //2.下单（模拟不同用户openid不同）
            orders.put(KeyUtil.genUniqueKey(),productId);
            //3.减库存
            stockNum=stockNum-1;
            try{
                Thread.sleep(100);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            stock.put(productId,stockNum);
        }
        //解锁
        redisLock.unlock(productId,String.valueOf(time));
    }
}
