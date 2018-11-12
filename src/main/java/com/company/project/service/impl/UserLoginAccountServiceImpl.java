package com.company.project.service.impl;

import com.company.project.dao.UserLoginAccountMapper;
import com.company.project.model.UserLoginAccount;
import com.company.project.service.UserLoginAccountService;
import com.company.project.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by SiZhongXia on 2018/11/10.
 */
@Service
@Transactional
public class UserLoginAccountServiceImpl extends AbstractService<UserLoginAccount> implements UserLoginAccountService {
    @Resource
    private UserLoginAccountMapper userLoginaccountMapper;

}