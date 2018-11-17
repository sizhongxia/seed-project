package com.company.project.service.impl;

import com.company.project.dao.EquipmentAftersaleMapper;
import com.company.project.model.EquipmentAftersale;
import com.company.project.service.EquipmentAftersaleService;
import com.company.project.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by SiZhongXia on 2018/11/16.
 */
@Service
@Transactional
public class EquipmentAftersaleServiceImpl extends AbstractService<EquipmentAftersale> implements EquipmentAftersaleService {
    @Resource
    private EquipmentAftersaleMapper equipmentAftersaleMapper;

}
