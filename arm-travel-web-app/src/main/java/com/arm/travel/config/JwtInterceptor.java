package com.arm.travel.config;

import com.arm.redis.config.ArmRedisCacheTemplate;
import com.arm.travel.commons.jwt.JwtTokenUtils;
import com.arm.travel.commons.utils.StringUtils;
import com.arm.travel.commons.utils.fn.asserts.Vsserts;
import com.arm.travel.pojo.SysUser;
import com.arm.travel.service.SysUserService;
import com.travel.common.resultex.enums.BizErrEnum;
import com.travel.common.resultex.ex.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.TimeUnit;

/**
 * @author Arman
 * @date 2022/1/8 15:03
 */
@Slf4j
public class JwtInterceptor implements HandlerInterceptor {

    @Autowired
    ArmRedisCacheTemplate redisCacheTemplate;

    @Autowired
    SysUserService sysUserService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 判断是否携带请求头 token
        String token = JwtTokenUtils.getJwtToken(request);
        if (StringUtils.isEmpty(token)) {
            throw new BusinessException(BizErrEnum.TOKEN_EMPTY);
        }
        // 校验 token 是否过期
        String tokenKey = "sys:user:token:" + token;
        if (!JwtTokenUtils.isVerify(token)) {
            token = redisCacheTemplate.getCacheObject(tokenKey);
            // 如果 token 过期了，就从 redis 中拿，所以存 token 时，redis 中的 value (token) 的过期时间 必须 >= token 的过期时间
            if (token == null || StringUtils.isBlank(token)) {
                log.error("token 过期");
                throw new BusinessException(BizErrEnum.TOKEN_NOT_VERIFY);
            }
        }
        // 做续期，如果过期时间小于某固定的时间，就做续期
        String userId = JwtTokenUtils.parseToken(token);
        SysUser user = sysUserService.getById(userId);
        if (Vsserts.isNull(user)) {
            throw new BusinessException(BizErrEnum.TOKEN_NOT_VERIFY);
        }
        // 判断是否需要续期，如果 redis 中的过期时间小于 10 分钟，就续期
        if (redisCacheTemplate.getExpireTime(tokenKey) < 60 * 10) {
            String newToken = JwtTokenUtils.createToken(user);
            redisCacheTemplate.setCacheObject(tokenKey, newToken, JwtTokenUtils.REDIS_TOKEN_EXPIRATION, TimeUnit.MILLISECONDS);
            log.info("update token info,new token: {}", newToken);
        }
        return true;
    }

}
