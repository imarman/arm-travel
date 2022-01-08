package com.arm.travel.commons;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * 用来获取 springboot 创建好的工厂
 *
 * @author Arman
 * @date 2022/1/8 15:55
 */
@Component
public class ApplicationContextUtils implements ApplicationContextAware {
    /**
     * 用这个变量保留工厂
     */
    private static ApplicationContext applicationContext;

    /**
     * 将创建好的工厂 以参数的形式传递给这个类
     *
     * @param context 创建好的工厂
     */
    @Override
    public void setApplicationContext(ApplicationContext context) throws BeansException {
        applicationContext = context;
    }

    /**
     * 通过 bean 的名称从工厂中获取对象的方法
     *
     * @param beanName bean的名称
     *                 举例：getBean(redisTemplate)  就能获取 RedisTemplate 对象
     */
    public static Object getBean(String beanName) {
        return applicationContext.getBean(beanName);
    }
}