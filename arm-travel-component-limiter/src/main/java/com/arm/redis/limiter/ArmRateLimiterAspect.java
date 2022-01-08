package com.arm.redis.limiter;

import com.arm.travel.commons.anno.ArmRateLimiter;
import com.arm.travel.commons.enums.LimiterType;
import com.arm.travel.commons.utils.ip.IpUtils;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.lang.reflect.Method;

/**
 * @author Arman
 */
@Aspect
@Slf4j
@Order(-1)
public class ArmRateLimiterAspect {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private DefaultRedisScript<Boolean> ipLimiterLuaScript;
    @Autowired
    private DefaultRedisScript<Boolean> ipLimitLua;

    // 1: 切入点
    @Pointcut("@annotation(com.arm.travel.commons.anno.ArmRateLimiter)")
    public void limiterPonicut() {
    }

    @Before("limiterPonicut()")
    public void limiter(JoinPoint joinPoint) {
        log.info("限流进来了.......");
        log.info("LimiterAspect-------------------------------------");
        // 1：获取方法的签名作为key
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        // 4: 读取方法的注解信息获取限流参数
        ArmRateLimiter annotation = method.getAnnotation(ArmRateLimiter.class);
        // 6：获取服务请求的对象
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        HttpServletResponse response = requestAttributes.getResponse();
        String methodNameKey = getRateLimiterKey(annotation, joinPoint, request);
        String userIp = IpUtils.getIpAddr(request);
        log.info("用户IP是：.......{}", userIp);
        // 7：通过方法反射获取注解的参数
        Integer limit = annotation.limit();
        Integer timeout = annotation.timeout();
        try {
            // 8: 请求lua脚本
            Boolean acquired = stringRedisTemplate.execute(ipLimitLua, Lists.newArrayList(methodNameKey), limit.toString(), timeout.toString());
            // 如果超过限流限制
            if (!acquired) {
                // 抛出异常，然后让全局异常去处理
                response.setCharacterEncoding("UTF-8");
                response.setContentType("text/html;charset=UTF-8");
                try (PrintWriter writer = response.getWriter();) {
                    writer.print("<h1>客官你慢点，请稍后在试一试!</h1>");
                    writer.flush();
                } catch (Exception ex) {
                    throw new RuntimeException("客官你慢点，请稍后在试一试!!!");
                }
            }
        } catch(Exception ex){
            log.info("redis服务器出问题了....");
        }
    }


    public String getRateLimiterKey(ArmRateLimiter rateLimiter, JoinPoint point, HttpServletRequest request) {
        StringBuffer stringBuffer = new StringBuffer(rateLimiter.key());
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();
        Class<?> targetClass = method.getDeclaringClass();
        // stringBuffer.append(targetClass.getName()).append("-").append(method.getName());
        stringBuffer.append(targetClass.getSimpleName()).append(":").append(method.getName());
        if (rateLimiter.limitType() == LimiterType.IP) {
            stringBuffer.append("-").append(IpUtils.getIpAddr(request));
        }
        return stringBuffer.toString();
    }
}
