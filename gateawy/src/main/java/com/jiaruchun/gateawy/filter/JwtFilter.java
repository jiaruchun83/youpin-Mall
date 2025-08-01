package com.jiaruchun.gateawy.filter;

import com.jiaruchun.gateawy.config.ExcludePathsProperties;
import com.jiaruchun.gateawy.config.JwtProperties;
import com.jiaruchun.gateawy.utils.JwtUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Map;

@Component
public class JwtFilter implements GlobalFilter, Ordered {

    private static final Logger log = LoggerFactory.getLogger(JwtFilter.class);
    @Autowired
    private JwtProperties jwtProperties;
    @Autowired
    private ExcludePathsProperties excludePathsProperties;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.info("拦截到请求：{}", exchange.getRequest().getURI().getPath());
        //指定路径跳过拦截
        for (String excludePath : excludePathsProperties.getExcludePaths()) {
            if (exchange.getRequest().getURI().getPath().contains(excludePath)) {
                log.info("跳过拦截：{}", exchange.getRequest().getURI().getPath());
                return chain.filter(exchange);
            }
        }
        String token = exchange.getRequest().getHeaders().getFirst("authorization");
        if (token == null || token.isEmpty()) {
            exchange.getResponse().setStatusCode(org.springframework.http.HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }
        //解析token
        long userId;
        try {
            Map<String, Object> stringObjectMap = JwtUtil.parseJWT(jwtProperties.getSecretKey(), token);
            userId = Long.parseLong(stringObjectMap.get(jwtProperties.getMapKeyName()).toString());
        } catch (Exception e){
            exchange.getResponse().setStatusCode(org.springframework.http.HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }
        String string = Long.toString(userId);
        ServerWebExchange mutatedExchange = exchange.mutate()
                .request(builder -> builder.header("userId", string))
                .build();
        return chain.filter(mutatedExchange);
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
