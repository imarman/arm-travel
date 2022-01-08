package com.arm.travel.controller;

import com.arm.redis.config.ArmRedisCacheTemplate;
import com.arm.travel.commons.anno.AppLogAuto;
import com.arm.travel.commons.jwt.JwtTokenUtils;
import com.arm.travel.pojo.SysUser;
import com.arm.travel.service.SysUserService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.travel.common.resultex.domain.R;
import com.travel.common.resultex.enums.BizErrEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.TimeUnit;

/**
 * @author Arman
 * @date 2022/1/8 14:36
 */
@RestController
@RequestMapping("/auth")
@Slf4j
public class LoginController {

    @Resource
    SysUserService sysUserService;

    @Resource
    ArmRedisCacheTemplate redisCacheTemplate;

    @AppLogAuto(title = "用户登录")
    @PostMapping("/login")
    public R login(String username) {
        LambdaQueryWrapper<SysUser> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(SysUser::getUsername, username);
        SysUser user = sysUserService.getOne(wrapper);
        if (user != null) {
            String token = JwtTokenUtils.createToken(user);
            user.setToken(token);
            user.setPassword(null);
            String tokenKey = "sys:user:token:" + token;
            redisCacheTemplate.setCacheObject(tokenKey, token, JwtTokenUtils.REDIS_TOKEN_EXPIRATION, TimeUnit.MILLISECONDS);
            return R.ok(user);
        }
        return R.error(BizErrEnum.USER_PWR_STATUS);
    }

    @GetMapping("/getUserInfo")
    public R getInfo(HttpServletRequest request) {
        return JwtTokenUtils.checkToken(request) ? R.ok() : R.error(BizErrEnum.USER_PWR_STATUS);
        // String token = JwtTokenUtils.getJwtToken(request);
        // if (JwtTokenUtils.isVerify(token)) {
        //     return R.ok();
        // }
        // return R.error(BizErrEnum.USER_PWR_STATUS);
    }

    @GetMapping("/getUserInfo2")
    public R getInfo2(HttpServletRequest request) {
        // return JwtTokenUtils.checkToken(request) ? R.ok() : R.error(BizErrEnum.USER_PWR_STATUS);
        // String token = JwtTokenUtils.getJwtToken(request);
        // if (JwtTokenUtils.isVerify(token)) {
        //     return R.ok();
        // }
        // return R.error(BizErrEnum.USER_PWR_STATUS);
        return R.ok();
    }
}
