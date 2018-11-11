package com.company.project.service.impl;

import com.company.project.dao.UnitDepartmentMapper;
import com.company.project.model.UnitDepartment;
import com.company.project.service.UnitDepartmentService;
import com.company.project.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by SiZhongXia on 2018/11/11.
 */
@Service
@Transactional
public class UnitDepartmentServiceImpl extends AbstractService<UnitDepartment> implements UnitDepartmentService {
    @Resource
    private UnitDepartmentMapper unitDepartmentMapper;

}
