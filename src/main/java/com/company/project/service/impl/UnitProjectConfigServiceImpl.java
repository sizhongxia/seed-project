package com.company.project.service.impl;

import com.company.project.dao.UnitProjectConfigMapper;
import com.company.project.model.UnitProjectConfig;
import com.company.project.service.UnitProjectConfigService;
import com.company.project.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by SiZhongXia on 2018/11/15.
 */
@Service
@Transactional
public class UnitProjectConfigServiceImpl extends AbstractService<UnitProjectConfig> implements UnitProjectConfigService {
    @Resource
    private UnitProjectConfigMapper unitProjectConfigMapper;

}
