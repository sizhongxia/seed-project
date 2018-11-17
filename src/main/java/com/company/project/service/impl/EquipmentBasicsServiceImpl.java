package com.company.project.service.impl;

import com.company.project.dao.EquipmentBasicsMapper;
import com.company.project.model.EquipmentBasics;
import com.company.project.service.EquipmentBasicsService;
import com.company.project.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by SiZhongXia on 2018/11/16.
 */
@Service
@Transactional
public class EquipmentBasicsServiceImpl extends AbstractService<EquipmentBasics> implements EquipmentBasicsService {
    @Resource
    private EquipmentBasicsMapper equipmentBasicsMapper;

}
