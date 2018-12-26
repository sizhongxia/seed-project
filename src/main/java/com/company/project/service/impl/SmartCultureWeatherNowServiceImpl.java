package com.company.project.service.impl;

import com.company.project.dao.SmartCultureWeatherNowMapper;
import com.company.project.model.SmartCultureWeatherNow;
import com.company.project.service.SmartCultureWeatherNowService;
import com.company.project.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by SiZhongXia on 2018/12/26.
 */
@Service
@Transactional
public class SmartCultureWeatherNowServiceImpl extends AbstractService<SmartCultureWeatherNow> implements SmartCultureWeatherNowService {
    @Resource
    private SmartCultureWeatherNowMapper smartCultureWeatherNowMapper;

}
