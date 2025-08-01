package jiaruchun.service.userservice;

import jiaruchun.api.config.DefaultFeignConfig;
import jiaruchun.service.userservice.config.JwtProperties;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;


@MapperScan("jiaruchun.service.userservice.mapper")
@EnableConfigurationProperties({JwtProperties.class})
@EnableFeignClients(defaultConfiguration = DefaultFeignConfig.class)
@SpringBootApplication
public class UserServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserServiceApplication.class, args);
    }

}
