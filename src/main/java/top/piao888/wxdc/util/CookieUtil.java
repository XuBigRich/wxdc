package top.piao888.wxdc.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName CookieUtil.java
 * @Description TODO
 * @createTime 2019年03月28日 16:56:00
 */
public class CookieUtil {
    public static void set(HttpServletResponse response,String name,String value,int maxAge){
        Cookie cookie=new Cookie(name,value);  // 实例化Cookie的 name 与 value
        cookie.setPath("/");  //路径
        cookie.setMaxAge(maxAge);   // 超时时间
//        cookie.setDomain("www.baidu.com");  //作用范围
        response.addCookie(cookie);
    }
    public static Cookie get(HttpServletRequest request, String name){
        Map<String,Cookie> cookieMap=readCookieMap(request);
        if(cookieMap.containsKey(name)){
            return cookieMap.get(name);
        }else {
            return null;
        }
    }
    private static Map<String,Cookie> readCookieMap(HttpServletRequest request){
        Map<String,Cookie> cookieMap=new HashMap<>();
        Cookie[] cookies=request.getCookies();
        if(cookies!=null){
            for(Cookie cookie:cookies){
                cookieMap.put(cookie.getName(),cookie);
            }
        }
        return cookieMap;
    }
}
