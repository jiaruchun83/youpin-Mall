package jiaruchun.api.config;


import com.hmall.common.utils.ThreadLocalUtil;
import feign.Logger;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;

@Slf4j
public class DefaultFeignConfig {
    @Bean
    public Logger.Level fullFeignLoggerLevel(){
        return Logger.Level.FULL;
    }

    @Bean
    public RequestInterceptor userInterceptor(){
        return new RequestInterceptor(){
            @Override
            public void apply(RequestTemplate requestTemplate) {
                log.info("userId:{}", ThreadLocalUtil.get());
                requestTemplate.header("userId", String.valueOf(ThreadLocalUtil.get()));
            }
        };
    }
}
