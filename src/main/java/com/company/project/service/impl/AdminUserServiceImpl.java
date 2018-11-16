package com.company.project.service.impl;

import com.company.project.dao.AdminUserMapper;
import com.company.project.model.AdminUser;
import com.company.project.service.AdminUserService;
import com.company.project.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by SiZhongXia on 2018/11/16.
 */
@Service
@Transactional
public class AdminUserServiceImpl extends AbstractService<AdminUser> implements AdminUserService {
    @Resource
    private AdminUserMapper adminUserMapper;

}
