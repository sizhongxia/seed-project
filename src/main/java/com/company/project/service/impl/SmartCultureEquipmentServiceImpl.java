package com.company.project.service.impl;

import com.company.project.dao.SmartCultureEquipmentMapper;
import com.company.project.model.SmartCultureEquipment;
import com.company.project.service.SmartCultureEquipmentService;
import com.company.project.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by SiZhongXia on 2019/01/04.
 */
@Service
@Transactional
public class SmartCultureEquipmentServiceImpl extends AbstractService<SmartCultureEquipment> implements SmartCultureEquipmentService {
    @Resource
    private SmartCultureEquipmentMapper smartCultureEquipmentMapper;

}
