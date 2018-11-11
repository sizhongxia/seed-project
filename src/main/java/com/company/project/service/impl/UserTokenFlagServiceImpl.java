package com.company.project.service.impl;

import com.company.project.dao.UserTokenFlagMapper;
import com.company.project.model.UserTokenFlag;
import com.company.project.service.UserTokenFlagService;
import com.company.project.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by SiZhongXia on 2018/11/10.
 */
@Service
@Transactional
public class UserTokenFlagServiceImpl extends AbstractService<UserTokenFlag> implements UserTokenFlagService {
    @Resource
    private UserTokenFlagMapper userTokenFlagMapper;

}
