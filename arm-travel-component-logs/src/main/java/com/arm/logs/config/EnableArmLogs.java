package com.arm.logs.config;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author Arman
 * @date 2022/1/5 20:28
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import(ArmLogsConfiguration.class)
// @Import(ArmLogAspect.class) // 也可以，但是为了拓展方便，用了配置类
public @interface EnableArmLogs {
}
