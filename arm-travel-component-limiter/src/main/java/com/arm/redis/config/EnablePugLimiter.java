package com.arm.redis.config;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author Arman
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import({PugRedisConfiguration.class})
public @interface EnablePugLimiter {
}
