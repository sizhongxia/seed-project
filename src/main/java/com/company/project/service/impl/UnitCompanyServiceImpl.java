package com.company.project.service.impl;

import com.company.project.dao.UnitCompanyMapper;
import com.company.project.model.UnitCompany;
import com.company.project.service.UnitCompanyService;
import com.company.project.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by SiZhongXia on 2018/11/11.
 */
@Service
@Transactional
public class UnitCompanyServiceImpl extends AbstractService<UnitCompany> implements UnitCompanyService {
    @Resource
    private UnitCompanyMapper unitCompanyMapper;

}
