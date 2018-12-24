package com.company.project.service.impl;

import com.company.project.dao.SmartCultureEquipmentModelMapper;
import com.company.project.model.SmartCultureEquipmentModel;
import com.company.project.service.SmartCultureEquipmentModelService;
import com.company.project.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by SiZhongXia on 2018/12/24.
 */
@Service
@Transactional
public class SmartCultureEquipmentModelServiceImpl extends AbstractService<SmartCultureEquipmentModel> implements SmartCultureEquipmentModelService {
    @Resource
    private SmartCultureEquipmentModelMapper smartCultureEquipmentModelMapper;

}
