package com.company.project.service.impl;

import com.company.project.dao.UserIdentityMapper;
import com.company.project.model.UserIdentity;
import com.company.project.service.UserIdentityService;
import com.company.project.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by SiZhongXia on 2018/11/10.
 */
@Service
@Transactional
public class UserIdentityServiceImpl extends AbstractService<UserIdentity> implements UserIdentityService {
    @Resource
    private UserIdentityMapper userIdentityMapper;

}
