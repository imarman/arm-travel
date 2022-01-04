package com.arm.travel.controller;

import com.arm.travel.pojo.SysUser;
import com.arm.travel.service.SysUserService;
import com.travel.common.resultex.domain.R;
import com.travel.common.resultex.enums.ResultStatusEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author Arman
 * @date 2022/1/4 21:24
 */
@RestController
@RequestMapping("/sys")
@Slf4j
public class SysUserController {

    @Resource
    SysUserService sysUserService;

    @GetMapping("/all")
    public R getAll() {
        return R.success(sysUserService.list());
    }

    @PostMapping("/save")
    public R save(@RequestBody SysUser sysUser) {
        log.info("保存用户方法执行，参数：sysUser:{}", sysUser);
        return sysUserService.save(sysUser) ? R.success(null, "success") : R.error(ResultStatusEnum.USER_PWR_STATUS);
    }
}
