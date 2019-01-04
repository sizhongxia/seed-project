package com.company.project.service.impl;

import com.company.project.dao.SmartCultureUserFarmMapper;
import com.company.project.model.SmartCultureUserFarm;
import com.company.project.service.SmartCultureUserFarmService;
import com.company.project.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by SiZhongXia on 2019/01/04.
 */
@Service
@Transactional
public class SmartCultureUserFarmServiceImpl extends AbstractService<SmartCultureUserFarm> implements SmartCultureUserFarmService {
    @Resource
    private SmartCultureUserFarmMapper smartCultureUserFarmMapper;

}
