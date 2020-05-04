package com.example.transation.interceptor;

import com.example.transation.entity.bean.User;
import com.example.transation.util.SessionUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author： xzh
 * @Description： 登录和权限验证
 * @Date： 2020/1/16 上午10:04
 * @MOdified by:
 **/
@Slf4j
@Component
@Configuration
public class AuthSignCheckInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        try {
            User user = SessionUtil.getSessionUser(request.getSession());
            if (user == null) {
                response.sendRedirect("/page/login");
            }
            return true;
        } catch (Exception e) {
            response.sendRedirect("/page/login");
        }
        return false;
    }
}

