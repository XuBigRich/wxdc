package top.piao888.wxdc.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
/*
*
 * @author admin
 * @version 1.0.0
 * @ClassName RedisLock.java
 * @Description TODO
 * @createTime 2019年03月27日 16:41:00
 */
/*
* key是 productid  为 单个商品 上锁
* value是 当前时间+超时时间
* */
@Component
@Slf4j
public class RedisLock {
    @Autowired
    private StringRedisTemplate redisTemplate;
    // key是 redis key value 是当前时间+超时时间
    public boolean lock(String key,String value){
       if(redisTemplate.opsForValue().setIfAbsent(key,value)){
           return true;  //刚刚被锁住  拿到了锁
       }
       //下面 如果在代码执行过程中  出现异常 未能及时解锁 造成 锁被占用 （ 死锁）
        // 下面是 被死锁后 被锁住的处理方法
        //先查询 锁的过期时间      /*同一时间 进来的 数据  只有两种可能 要么 获取到 当前锁锁的时间  要么 获取到 自己相同 锁时间（先进来的 线程执行完 了 设置时间）*/
       String currentValue=redisTemplate.opsForValue().get(key);
       //判断 这个锁是否为空  是否 过期      /* 获取过期锁后 判断  当前锁是否过期  */
       if(!StringUtils.isEmpty(currentValue)&&Long.parseLong(currentValue)<System.currentTimeMillis()){
           //如果这个锁过期了
           //获取上一个锁的时间（并发状态下 不一定是 过期锁） 并设置这个锁为最新锁（更新过期时间）
           String oldValue=redisTemplate.opsForValue().getAndSet(key,value);
           //判断过期 的锁 是否为空  并判断上一个锁 与过期的锁是否相等/*判断是否为第一个更新锁的线程 是 就更新锁  不是 就继续锁着*/
           if(!StringUtils.isEmpty(oldValue)&&oldValue.equals(currentValue)){
               return true;//刚刚被锁住  拿到了锁
           }
       }
       return false;//早就被锁住 锁没有过期  没拿到锁
    }
    public void unlock(String key,String value){
        try {
            String currentValue = redisTemplate.opsForValue().get(key);
            if (!StringUtils.isEmpty(currentValue) && currentValue.equals(value)) {
                redisTemplate.opsForValue().getOperations().delete(key);
            }
        }catch (Exception e){
            log.error("【redis分布式解锁】 解锁异常,{}",e);
        }
    }
}
