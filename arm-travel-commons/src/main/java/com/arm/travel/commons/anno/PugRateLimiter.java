package com.arm.travel.commons.anno;


import com.arm.travel.commons.enums.LimiterType;

import java.lang.annotation.*;

/**
 * @author Arman
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface PugRateLimiter {
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
