package com.company.project.service.impl;

import com.company.project.dao.SmartCultureBasicCityMapper;
import com.company.project.model.SmartCultureBasicCity;
import com.company.project.service.SmartCultureBasicCityService;
import com.company.project.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by SiZhongXia on 2018/12/26.
 */
@Service
@Transactional
public class SmartCultureBasicCityServiceImpl extends AbstractService<SmartCultureBasicCity> implements SmartCultureBasicCityService {
    @Resource
    private SmartCultureBasicCityMapper smartCultureBasicCityMapper;

}
