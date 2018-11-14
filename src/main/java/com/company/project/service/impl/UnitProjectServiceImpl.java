package com.company.project.service.impl;

import com.company.project.dao.UnitProjectMapper;
import com.company.project.model.UnitProject;
import com.company.project.service.UnitProjectService;
import com.company.project.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by SiZhongXia on 2018/11/13.
 */
@Service
@Transactional
public class UnitProjectServiceImpl extends AbstractService<UnitProject> implements UnitProjectService {
    @Resource
    private UnitProjectMapper unitProjectMapper;

}
