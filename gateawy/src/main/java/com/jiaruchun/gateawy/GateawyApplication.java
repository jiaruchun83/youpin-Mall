package com.jiaruchun.gateawy;

import com.jiaruchun.gateawy.config.ExcludePathsProperties;
import com.jiaruchun.gateawy.config.JwtProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties(value = {JwtProperties.class, ExcludePathsProperties.class})
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class GateawyApplication {

	public static void main(String[] args) {
		SpringApplication.run(GateawyApplication.class, args);
	}

}
