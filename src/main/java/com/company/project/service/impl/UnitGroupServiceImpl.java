package com.company.project.service.impl;

import com.company.project.dao.UnitGroupMapper;
import com.company.project.model.UnitGroup;
import com.company.project.service.UnitGroupService;
import com.company.project.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by SiZhongXia on 2018/11/22.
 */
@Service
@Transactional
public class UnitGroupServiceImpl extends AbstractService<UnitGroup> implements UnitGroupService {
    @Resource
    private UnitGroupMapper unitGroupMapper;

}
