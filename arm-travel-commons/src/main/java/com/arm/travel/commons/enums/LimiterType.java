package com.arm.travel.commons.enums;

/**
 * @author Arman
 */
public enum LimiterType {

    /**
     * 默认策略全局限流
     */
    DEFAULT,

    /**
     * 根据请求者IP进行限流
     */
    IP;
}
