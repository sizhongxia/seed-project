package com.company.project.service.impl;

import com.company.project.dao.UnitLaborsubcontractorMapper;
import com.company.project.model.UnitLaborsubcontractor;
import com.company.project.service.UnitLaborsubcontractorService;
import com.company.project.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by SiZhongXia on 2018/11/14.
 */
@Service
@Transactional
public class UnitLaborsubcontractorServiceImpl extends AbstractService<UnitLaborsubcontractor> implements UnitLaborsubcontractorService {
    @Resource
    private UnitLaborsubcontractorMapper unitLaborsubcontractorMapper;

}