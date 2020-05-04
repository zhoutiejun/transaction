package com.example.transation.aop;

import com.example.transation.entity.bean.User;
import com.example.transation.util.SessionUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Enumeration;

/**
 * @Author ：zxh
 * @Description: 增加所有controller 日志打印机制
 * @Date: 20:26 2018/5/29
 * @Modified by:
 */
@Aspect
@Slf4j
@Component
public class MvcMethodLogAdvice {

    @Pointcut("execution(* com.example.transation.controller..*.*(..))")
    public void controllerMethodPointcut() {
    }

    @Around("controllerMethodPointcut()")
    public Object interceptor(ProceedingJoinPoint pjp) throws Throwable {
        StringBuffer param = new StringBuffer("{");
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        Method method = signature.getMethod();
        String[] clazzName = method.getDeclaringClass().getName().split("\\.");
        String className = clazzName[clazzName.length - 1];
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        HttpServletRequest request = sra.getRequest();
        Enumeration<String> enumeration = request.getParameterNames();
        while (enumeration.hasMoreElements()) {
            String name = enumeration.nextElement();
            param.append(name).append(": ").append(request.getParameter(name)).append(",");
        }
        param.append("}");
        User user = SessionUtil.getSessionUser(request.getSession());
        log.info("【用户 = [{},{}],类名 = {},方法名 = {}, 参数为 = {}】", user == null ? "" : user.getUsername(), user == null ? "" : user.getUsername(), className, method.getName(), param.toString());
        return pjp.proceed();
    }
}
