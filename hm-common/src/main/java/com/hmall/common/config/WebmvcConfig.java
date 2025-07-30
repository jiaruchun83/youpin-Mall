package com.hmall.common.config;

import com.hmall.common.interceptor.UserIdInterceptor;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Slf4j
@Configuration
@AllArgsConstructor
@ConditionalOnClass(DispatcherServlet.class)
public class WebmvcConfig implements WebMvcConfigurer {

    private final UserIdInterceptor userIdInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        log.info("注册userIdInterceptor拦截器");
        registry.addInterceptor(userIdInterceptor);
    }
}
