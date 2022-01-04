package com.arm.travel.service;

import com.arm.orm.mapper.SysUserMapper;
import com.arm.travel.pojo.SysUser;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author Arman
 * @date 2022/1/4 21:22
 */
@Service
@Slf4j
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService{

}
