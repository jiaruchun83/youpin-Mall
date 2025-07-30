package jiaruchun.service.itemservice;

import jiaruchun.api.config.DefaultFeignConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@MapperScan("jiaruchun.service.itemservice.mapper")
@EnableFeignClients(basePackages = "jiaruchun.api",defaultConfiguration = DefaultFeignConfig.class)
@SpringBootApplication
public class ItemServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ItemServiceApplication.class, args);
    }

}
