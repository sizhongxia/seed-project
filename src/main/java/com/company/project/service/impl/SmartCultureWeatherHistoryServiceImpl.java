package com.company.project.service.impl;

import com.company.project.dao.SmartCultureWeatherHistoryMapper;
import com.company.project.model.SmartCultureWeatherHistory;
import com.company.project.service.SmartCultureWeatherHistoryService;
import com.company.project.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by SiZhongXia on 2018/12/26.
 */
@Service
@Transactional
public class SmartCultureWeatherHistoryServiceImpl extends AbstractService<SmartCultureWeatherHistory> implements SmartCultureWeatherHistoryService {
    @Resource
    private SmartCultureWeatherHistoryMapper smartCultureWeatherHistoryMapper;

}
