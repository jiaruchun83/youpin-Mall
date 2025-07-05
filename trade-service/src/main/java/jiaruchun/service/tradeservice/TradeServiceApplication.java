package jiaruchun.service.tradeservice;

import jiaruchun.api.config.DefaultFeignConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@MapperScan("jiaruchun.service.tradeservice.mapper")
@EnableFeignClients(basePackages = "jiaruchun.api",defaultConfiguration = DefaultFeignConfig.class)
//@ComponentScan(basePackages = {"jiaruchun.api", "jiaruchun.service.tradeservice"})
@SpringBootApplication
public class TradeServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(TradeServiceApplication.class, args);
    }

}
