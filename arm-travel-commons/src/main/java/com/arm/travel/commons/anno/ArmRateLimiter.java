package com.arm.travel.commons.anno;


import com.arm.travel.commons.enums.LimiterType;

import java.lang.annotation.*;

/**
 * @author Arman
 * <p>
 * 放入 commons 工程是因为，以后就算把 limiter 工程去掉了，在 web 工程也不会有影响，这个注解只是会变成一个空的 没用的注解而已
 * 还是为了 component-limiter 的可插拔
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ArmRateLimiter {
    /**
     * 限流key
     */
    String key() default "rate_limiter:";

    /**
     * 限流类型
     */
    LimiterType limitType() default LimiterType.IP;

    /**
     * 限流次数
     * 每timeout限制请求的个数
     */
    int limit() default 10;

    /**
     * 限流时间,单位秒
     */
    int timeout() default 1;
}
