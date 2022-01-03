package com.travel.common.resultex.anno;

import com.travel.common.resultex.handler.GlobalExceptionControllerHandler;
import com.travel.common.resultex.handler.ResultResponseHandler;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * 开关全局异常统一处理的注解，此类是故意放入到 com.travel 包下，就是为了使用开关类注解，注入到 IoC 容器中（可插拔）
 * - 跟包名解耦合
 *
 * @author Arman
 * @date 2022/1/3 21:42
 * <p>
 * 这个注解的作用是，可以让里面的类不受包的控制，比如：
 * 此类的包是 com.travel.xxx，而 SpringBoot 启动类的包是 com.arm.travel.xxx, 所以就算在 pom 中添加依赖了，SpringBoot 还不能扫描到 com.travel 下的类，
 * 所以可以通过 @import() 在启动类上面，导入 GlobalExceptionControllerHandler.class, ResultResponseHandler.class。
 * 但是直接做成一个开关类，直接在 SpringBoot 启动类上使用开关注解比使用 @Import 好一点
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import({GlobalExceptionControllerHandler.class, ResultResponseHandler.class})
public @interface EnableResultEx {
}
