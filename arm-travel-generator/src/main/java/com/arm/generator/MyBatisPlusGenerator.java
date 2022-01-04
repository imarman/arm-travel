package com.arm.generator;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.Collections;

/**
 * @author Arman
 * @date 2022/1/5 00:01
 */
public class MyBatisPlusGenerator {
    public static void main(String[] args) {
        FastAutoGenerator.create("jdbc:mysql://47.97.18.175:3306/test-db?serverTimezone=UTC&useUnicode=true&characterEncoding=utf-8&useSSL=false", "test-user", "123456")
                .globalConfig(builder -> {
                    builder.author("Arman") // 设置作者
                            .enableSwagger() // 开启 swagger 模式
                            .fileOverride() // 覆盖已生成文件
                            .outputDir("/Users/arman/Desktop/学相伴/arm-travel"); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent("com.arm.travel") // 设置父包名
                            .moduleName("") // 设置父包模块名
                            .entity("pojo")
                            .service("service")
                            .serviceImpl("impl")
                            .controller("controller")
                            .pathInfo(Collections.singletonMap(OutputFile.mapperXml, "/Users/arman/Desktop/学相伴/arm-travel")); // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    builder.addInclude("sys_user") // 设置需要生成的表名
                            .addTablePrefix("t_", "c_"); // 设置过滤表前缀
                })
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();

    }
}
