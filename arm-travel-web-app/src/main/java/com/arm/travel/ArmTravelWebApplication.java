package com.arm.travel;

import com.travel.common.resultex.EnableResultEx;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @date 2022/1/1 23:32
 */
@SpringBootApplication
@EnableResultEx // 使用全局异常处理的类
public class ArmTravelWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(ArmTravelWebApplication.class, args);
    }

}
