package com.company.project.service.impl;

import com.company.project.dao.SmartCultureUserIdentityMapper;
import com.company.project.model.SmartCultureUserIdentity;
import com.company.project.service.SmartCultureUserIdentityService;
import com.company.project.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by SiZhongXia on 2018/12/21.
 */
@Service
@Transactional
public class SmartCultureUserIdentityServiceImpl extends AbstractService<SmartCultureUserIdentity> implements SmartCultureUserIdentityService {
    @Resource
    private SmartCultureUserIdentityMapper smartCultureUserIdentityMapper;

}
