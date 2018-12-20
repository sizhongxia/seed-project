package com.company.project.service.impl;

import com.company.project.dao.SmartCultureUserMapper;
import com.company.project.model.SmartCultureUser;
import com.company.project.service.SmartCultureUserService;
import com.company.project.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by SiZhongXia on 2018/12/20.
 */
@Service
@Transactional
public class SmartCultureUserServiceImpl extends AbstractService<SmartCultureUser> implements SmartCultureUserService {
    @Resource
    private SmartCultureUserMapper smartCultureUserMapper;

}
