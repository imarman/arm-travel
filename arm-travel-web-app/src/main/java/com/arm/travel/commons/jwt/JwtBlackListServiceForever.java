package com.arm.travel.commons.jwt;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * 永久黑名单: 一般不会用到，如果使用也不能存入 token，因为 token 会一直变化
 *
 * @author Arman
 * @date 2022/1/12 22:40
 */
@Slf4j
@Service
public class JwtBlackListServiceForever implements iJwtBlackListService {

    private final String BLACK_LIST_KEY = "blacklist:string";

    @Autowired
    RedisTemplate<String, String> redisTemplate;

    /**
     * 添加黑名单
     *
     * @param token 用户 token
     */
    @Override
    public void addBlackList(String token) {
        log.warn("[添加 JWT 黑名单] --- token:{}", token);
        // 如果用户的 token 不在黑名单，就加入黑名单
        if (Boolean.FALSE.equals(this.redisTemplate.opsForSet().isMember(BLACK_LIST_KEY, token))) {
            redisTemplate.opsForSet().add(BLACK_LIST_KEY, token);
        }
    }

    @Override
    public boolean isBlackList(String token) {
        return false;
    }

    @Override
    public boolean removeBlackList(String token) {
        log.info("[移出 JWT 黑名单] --- token:{}", token);
        return false;
    }

}
