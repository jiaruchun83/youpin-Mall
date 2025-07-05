package com.jiaruchun.gateawy.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@AllArgsConstructor
@NoArgsConstructor
@ConfigurationProperties(prefix = "jwt")
public class JwtProperties {
    private String secretKey;
    private String tokenName;
    private String mapKeyName;
    private Long tokenTtl;
}
