package com.company.project.service.impl;

import com.company.project.dao.EquipmentLiftParameterMapper;
import com.company.project.model.EquipmentLiftParameter;
import com.company.project.service.EquipmentLiftParameterService;
import com.company.project.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by SiZhongXia on 2018/11/17.
 */
@Service
@Transactional
public class EquipmentLiftParameterServiceImpl extends AbstractService<EquipmentLiftParameter> implements EquipmentLiftParameterService {
    @Resource
    private EquipmentLiftParameterMapper equipmentLiftParameterMapper;

}
