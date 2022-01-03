package com.arm.travel.commons.init;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @author Arman
 * @date 2021-08-23 16:19
 */
@Component
public class ServerCommandLineRunner implements CommandLineRunner {
    @Override
    public void run(String... args) {
        // 逻辑代码
        System.out.println("CommandLineRunner--------------->");
    }
}