package com.arm.travel.commons.init;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * @author Arman
 * @date 2021-08-23 16:19
 */
@Component
public class ServerApplicationRunner implements ApplicationRunner {
    @Override
    public void run(ApplicationArguments args) {
        // 逻辑代码
        System.out.println("ApplicationRunner--------------->");
    }
}