package jiaruchun.service.payservice;

import jiaruchun.api.config.DefaultFeignConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@MapperScan("jiaruchun.service.payservice.mapper")
@EnableFeignClients(basePackages = "jiaruchun.api",defaultConfiguration = DefaultFeignConfig.class)
@SpringBootApplication
public class PayServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(PayServiceApplication.class, args);
    }

}
