package com.company.project.service.impl;

import com.company.project.dao.SmartCultureEquipmentTypeMapper;
import com.company.project.model.SmartCultureEquipmentType;
import com.company.project.service.SmartCultureEquipmentTypeService;
import com.company.project.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by SiZhongXia on 2018/12/24.
 */
@Service
@Transactional
public class SmartCultureEquipmentTypeServiceImpl extends AbstractService<SmartCultureEquipmentType> implements SmartCultureEquipmentTypeService {
    @Resource
    private SmartCultureEquipmentTypeMapper smartCultureEquipmentTypeMapper;

}
