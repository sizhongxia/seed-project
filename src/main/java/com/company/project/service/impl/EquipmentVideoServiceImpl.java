package com.company.project.service.impl;

import com.company.project.dao.EquipmentVideoMapper;
import com.company.project.model.EquipmentVideo;
import com.company.project.service.EquipmentVideoService;
import com.company.project.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by SiZhongXia on 2018/11/17.
 */
@Service
@Transactional
public class EquipmentVideoServiceImpl extends AbstractService<EquipmentVideo> implements EquipmentVideoService {
    @Resource
    private EquipmentVideoMapper equipmentVideoMapper;

}
