package com.company.project.service.impl;

import com.company.project.dao.SmartCultureFarmAreaMapper;
import com.company.project.model.SmartCultureFarmArea;
import com.company.project.service.SmartCultureFarmAreaService;
import com.company.project.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by SiZhongXia on 2018/12/23.
 */
@Service
@Transactional
public class SmartCultureFarmAreaServiceImpl extends AbstractService<SmartCultureFarmArea> implements SmartCultureFarmAreaService {
    @Resource
    private SmartCultureFarmAreaMapper smartCultureFarmAreaMapper;

}
