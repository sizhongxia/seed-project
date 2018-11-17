package com.company.project.service.impl;

import com.company.project.dao.EquipmentDischargeParameterMapper;
import com.company.project.model.EquipmentDischargeParameter;
import com.company.project.service.EquipmentDischargeParameterService;
import com.company.project.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by SiZhongXia on 2018/11/17.
 */
@Service
@Transactional
public class EquipmentDischargeParameterServiceImpl extends AbstractService<EquipmentDischargeParameter> implements EquipmentDischargeParameterService {
    @Resource
    private EquipmentDischargeParameterMapper equipmentDischargeParameterMapper;

}
