package top.piao888.wxdc.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import top.piao888.wxdc.constant.CookieConstant;
import top.piao888.wxdc.constant.RedisConstant;
import top.piao888.wxdc.exception.SellerAuthorizeException;
import top.piao888.wxdc.util.CookieUtil;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName SellerAuthorizeAspect.java
 * @Description TODO
 * @createTime 2019年03月29日 10:36:00
 */
@Aspect
@Component
@Slf4j
public class SellerAuthorizeAspect {
    @Autowired
    private StringRedisTemplate redisTemplate;
    @Pointcut("execution(public * top.piao888.wxdc.controller.Seller*.*(..)) "+"&&!execution(public * top.piao888.wxdc.controller.SellerUserController.*(..))")
    public void verify(){};
    @Before("verify()")
    public void doVerify(){
        ServletRequestAttributes attributes= (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
        HttpServletRequest request=attributes.getRequest();
        //查询cookie
        Cookie cookie=CookieUtil.get(request,CookieConstant.TOKEN);
        if(cookie==null){
            log.warn("【登陆校验】 Cookie中查不到token");
            throw new SellerAuthorizeException();
        }
        //去redis中查询
        String result= redisTemplate.opsForValue().get(String.format(RedisConstant.TOKEN_PREFIX,cookie.getValue()));
        if(StringUtils.isEmpty(result)){
            log.warn("【登陆校验】 Redis中查不到token");
            throw new SellerAuthorizeException();
        }
    }
}
