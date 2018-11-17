package com.company.project.service.impl;

import com.company.project.dao.EquipmentTowercraneParameterMapper;
import com.company.project.model.EquipmentTowercraneParameter;
import com.company.project.service.EquipmentTowercraneParameterService;
import com.company.project.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by SiZhongXia on 2018/11/17.
 */
@Service
@Transactional
public class EquipmentTowercraneParameterServiceImpl extends AbstractService<EquipmentTowercraneParameter> implements EquipmentTowercraneParameterService {
    @Resource
    private EquipmentTowercraneParameterMapper equipmentTowercraneParameterMapper;

}
