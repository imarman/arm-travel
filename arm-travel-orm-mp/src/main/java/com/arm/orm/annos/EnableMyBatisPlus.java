package com.arm.orm.annos;

import com.arm.orm.config.MyBatisPlusConfig;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author Arman
 * @date 2022/1/4 21:49
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import(MyBatisPlusConfig.class)
public @interface EnableMyBatisPlus {
}
