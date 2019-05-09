package top.piao888.wxdc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class WxdcApplication {

    public static void main(String[] args) {
        SpringApplication.run(WxdcApplication.class, args);
    }

}
