package com.arm.logs.aop;

import com.arm.travel.commons.anno.AppLogAuto;
import com.arm.travel.commons.utils.StringUtils;
import com.arm.travel.commons.utils.ip.IpUtils;
import com.arm.travel.commons.utils.req.ServletUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;

/**
 * @author Arman
 * @date 2022/1/5 20:27
 */
@Aspect
@Slf4j
@Order(1)
public class ArmLogAspect {


    // 2: 通知 （切点或者切点表达式）
    @Around("@annotation(appLogAuto)")
    public Object aroundLog(ProceedingJoinPoint proceedingJoinPoint, AppLogAuto appLogAuto) throws Throwable {
        // 1:方法执行的开始时间
        long startTime = System.currentTimeMillis();
        // 2:执行真实方法
        MethodSignature signature = (MethodSignature) proceedingJoinPoint.getSignature();
        // 获取参数
        Object[] args = proceedingJoinPoint.getArgs();
        // 方法名
        String methodName = signature.getMethod().getName();
        if (appLogAuto.title() != null && StringUtils.isNotEmpty(appLogAuto.title())) {
            methodName = appLogAuto.title();
        }
        if (args.length != 0) {
            log.info("{}方法,参数:{}", methodName, args);
        }
        // 3：核心代码，这个前后通知分界线，执行具体的方法
        Object methodReturnValue = proceedingJoinPoint.proceed();
        long endTime = System.currentTimeMillis();
        log.info("{}方法,返回值:{},执行时间:{}", methodName, methodReturnValue, endTime - startTime);
        return methodReturnValue;
    }

    // 2: 通知 （切点或者切点表达式）
    @AfterThrowing(value = "@annotation(appLogAuto)", throwing = "e")
    public void doAfterThrowing(JoinPoint joinPoint, AppLogAuto appLogAuto, Exception e) {
        // 1:方法执行的开始时间
        // long starttime = System.currentTimeMillis();
        // 2:执行真实方法
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        String methodName = signature.getMethod().getName();
        if (appLogAuto.title() != null && StringUtils.isNotEmpty(appLogAuto.title())) {
            methodName = appLogAuto.title();
        }
        log.error("方法:{}，异常信息:{}", methodName, e.getMessage());
    }

    protected void handleLog(final JoinPoint joinPoint, final Exception e, Object jsonResult) {
        try {
            // 获取当前的用户
            // SysLoginUser sysLoginUser = UserThrealLocal.get();
            // *========数据库日志=========*//
            // 请求的地址
            String ip = IpUtils.getIpAddr(ServletUtils.getRequest());
            // 设置方法名称
            String className = joinPoint.getTarget().getClass().getName();
            String methodName = joinPoint.getSignature().getName();

        } catch (Exception exp) {
            // 记录本地异常日志
            log.error("==前置通知异常==");
            log.error("异常信息:{}", exp.getMessage());
            exp.printStackTrace();
        }
    }
}
