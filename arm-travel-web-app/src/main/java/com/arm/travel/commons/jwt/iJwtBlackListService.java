package com.arm.travel.commons.jwt;

/**
 * @author Arman
 * @date 2022/1/12 22:57
 */
public interface iJwtBlackListService {

    /**
     * 添加黑名单
     * @param token 用户凭证
     */
    void addBlackList(String token);

    /**
     * 判断是否在黑名单
     * @param token 用户凭证
     */
    boolean isBlackList(String token);

    /**
     * 移除黑名单
     * @param token 用户凭证
     */
    boolean removeBlackList(String token);

}
