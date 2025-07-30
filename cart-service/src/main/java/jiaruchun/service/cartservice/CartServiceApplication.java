package jiaruchun.service.cartservice;

import jiaruchun.api.config.DefaultFeignConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@MapperScan("jiaruchun.service.cartservice.mapper")
@SpringBootApplication(scanBasePackages = {"com.hmall.common", "jiaruchun.service.cartservice"})
@EnableFeignClients(basePackages = "jiaruchun.api",defaultConfiguration = DefaultFeignConfig.class)
public class CartServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CartServiceApplication.class, args);
    }

}
