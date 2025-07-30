package com.hmall.common.interceptor;

import cn.hutool.core.util.StrUtil;
import com.hmall.common.utils.ThreadLocalUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Slf4j
@Component
@NoArgsConstructor
public class UserIdInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("拦截到请求：{}", request.getRequestURI());
        String userIdHeader = request.getHeader("userId");
        if (StrUtil.isNotBlank(userIdHeader)) {
            Long userId = Long.valueOf(userIdHeader);
            log.info("common包的各个微服务统一的UserIdInterceptor拦截器取到用户id：{}", userId);
            ThreadLocalUtil.set(userId);
        }
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        log.info("清理 ThreadLocal");
        ThreadLocalUtil.remove();
    }
}
