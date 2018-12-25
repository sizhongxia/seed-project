package com.company.project.service.impl;

import com.company.project.dao.SmartCultureWeatherCityMapper;
import com.company.project.model.SmartCultureWeatherCity;
import com.company.project.service.SmartCultureWeatherCityService;
import com.company.project.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by SiZhongXia on 2018/12/26.
 */
@Service
@Transactional
public class SmartCultureWeatherCityServiceImpl extends AbstractService<SmartCultureWeatherCity> implements SmartCultureWeatherCityService {
    @Resource
    private SmartCultureWeatherCityMapper smartCultureWeatherCityMapper;

}
