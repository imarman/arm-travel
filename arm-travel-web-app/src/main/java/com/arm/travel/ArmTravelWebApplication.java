package com.arm.travel;

import com.arm.logs.config.EnableArmLogs;
import com.arm.redis.config.EnableArmLimiter;
import com.arm.orm.annos.EnableMyBatisPlus;
import com.travel.common.resultex.anno.EnableResultEx;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Arman
 * @date 2022/1/1 23:32
 */
@SpringBootApplication
@EnableResultEx // 使用全局异常处理的类
@EnableArmLimiter // 限流
@EnableMyBatisPlus // 使用 MyBatisPlus
@EnableArmLogs // 日志
public class ArmTravelWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(ArmTravelWebApplication.class, args);
    }

}
