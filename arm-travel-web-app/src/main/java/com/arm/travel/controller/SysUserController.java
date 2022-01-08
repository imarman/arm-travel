package com.arm.travel.controller;

import com.arm.travel.commons.anno.AppLogAuto;
import com.arm.travel.pojo.SysUser;
import com.arm.travel.service.SysUserService;
import com.travel.common.resultex.domain.R;
import com.travel.common.resultex.enums.BizErrEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author Arman
 * @date 2022/1/4 21:24
 */
@RestController
@RequestMapping("/api/sys")
@Slf4j
public class SysUserController {

    @Resource
    SysUserService sysUserService;

    @GetMapping("/all")
    @AppLogAuto(title = "获取用户列表")
    public R getAll() {
        return R.ok(sysUserService.list());
    }

    @PostMapping("/save")
    @AppLogAuto()
    public R save(@RequestBody SysUser sysUser) {
        return sysUserService.save(sysUser) ? R.ok(null, "success") : R.error(BizErrEnum.USER_PWR_STATUS);
    }
}
