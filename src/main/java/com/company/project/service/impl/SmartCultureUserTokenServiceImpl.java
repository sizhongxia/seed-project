package com.company.project.service.impl;

import com.company.project.dao.SmartCultureUserTokenMapper;
import com.company.project.model.SmartCultureUserToken;
import com.company.project.service.SmartCultureUserTokenService;
import com.company.project.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by SiZhongXia on 2018/12/20.
 */
@Service
@Transactional
public class SmartCultureUserTokenServiceImpl extends AbstractService<SmartCultureUserToken> implements SmartCultureUserTokenService {
    @Resource
    private SmartCultureUserTokenMapper smartCultureUserTokenMapper;

}
