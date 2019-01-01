package com.company.project.service.impl;

import com.company.project.dao.SmartCultureFarmMapper;
import com.company.project.model.SmartCultureFarm;
import com.company.project.service.SmartCultureFarmService;
import com.company.project.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by SiZhongXia on 2019/01/01.
 */
@Service
@Transactional
public class SmartCultureFarmServiceImpl extends AbstractService<SmartCultureFarm> implements SmartCultureFarmService {
    @Resource
    private SmartCultureFarmMapper smartCultureFarmMapper;

}
