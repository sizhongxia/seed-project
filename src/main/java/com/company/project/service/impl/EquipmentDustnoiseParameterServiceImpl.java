package com.company.project.service.impl;

import com.company.project.dao.EquipmentDustnoiseParameterMapper;
import com.company.project.model.EquipmentDustnoiseParameter;
import com.company.project.service.EquipmentDustnoiseParameterService;
import com.company.project.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by SiZhongXia on 2018/11/16.
 */
@Service
@Transactional
public class EquipmentDustnoiseParameterServiceImpl extends AbstractService<EquipmentDustnoiseParameter> implements EquipmentDustnoiseParameterService {
    @Resource
    private EquipmentDustnoiseParameterMapper equipmentDustnoiseParameterMapper;

}
