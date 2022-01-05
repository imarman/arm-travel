package com.arm.logs.config;

import com.arm.logs.aop.ArmLogAspect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Arman
 * @date 2022/1/5 20:27
 */
@Configuration
public class ArmLogsConfiguration {

    @Bean
    ArmLogAspect armLogAspect() {
        return new ArmLogAspect();
    }
}
