package top.piao888.wxdc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import top.piao888.wxdc.constant.CookieConstant;
import top.piao888.wxdc.constant.RedisConstant;
import top.piao888.wxdc.domain.SellerInfo;
import top.piao888.wxdc.enums.ResultEnum;
import top.piao888.wxdc.service.SellerService;
import top.piao888.wxdc.util.CookieUtil;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName SellerUserController.java
 * @Description 卖家用户
 * @createTime 2019年03月28日 14:36:00
 */
@Controller
@RequestMapping("/seller")
public class SellerUserController {
    @Autowired
    private SellerService sellerService;
    @Autowired
    private StringRedisTemplate redisTemplate;
//    @Autowired
//    private ProjectUrlConfig projectUrlConfig;
    @GetMapping("/login")
    public ModelAndView login(@RequestParam("openid") String openid, HttpServletResponse response, Map<String,Object> map){
        //1.openid去和数据库里数据匹配
        SellerInfo sellerInfo=sellerService.findSellerInfoByOpenid(openid);
        if(sellerInfo==null){
            map.put("msg",ResultEnum.LOGIN_FAIL.getMessage());
            map.put("url","sell/seller/order/list");
            return new ModelAndView("common/error");
        }
        //2.设置token至redis
        String token= UUID.randomUUID().toString();  //token的唯一 标识uuid
        Integer expire= RedisConstant.EXPIRE;  //超时时间
        redisTemplate.opsForValue().set(String.format(RedisConstant.TOKEN_PREFIX,token),openid,expire,TimeUnit.SECONDS);
        //3.设置token至cookie
        CookieUtil.set(response,CookieConstant.TOKEN,token,expire);  //response,cookie名字,cookie值
//        return new ModelAndView("redirect:"+projectUrlConfig.getSell()+"/sell/seller/order/list");
        return new ModelAndView("redirect:/seller/order/list");
    }
    @GetMapping("/logout")
    public ModelAndView logout(HttpServletRequest request,HttpServletResponse response,Map<String,Object> map){
        //1.从cookie里查询
          Cookie cookie=  CookieUtil.get(request,CookieConstant.TOKEN);
        if(cookie!=null){
            //2.清除redis
            redisTemplate.opsForValue().getOperations().delete(String.format(RedisConstant.TOKEN_PREFIX,cookie.getValue()));
            //3.清楚cookie
            CookieUtil.set(response,CookieConstant.TOKEN,null,0);
        }
        map.put("msg",ResultEnum.LOGOUT_SUCCESS.getMessage());
        map.put("url","sell/seller/order/list");
        return new ModelAndView("common/success",map);
    }
}
