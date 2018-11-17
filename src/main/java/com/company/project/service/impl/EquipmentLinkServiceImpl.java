package com.company.project.service.impl;

import com.company.project.dao.EquipmentLinkMapper;
import com.company.project.model.EquipmentLink;
import com.company.project.service.EquipmentLinkService;
import com.company.project.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by SiZhongXia on 2018/11/17.
 */
@Service
@Transactional
public class EquipmentLinkServiceImpl extends AbstractService<EquipmentLink> implements EquipmentLinkService {
    @Resource
    private EquipmentLinkMapper equipmentLinkMapper;

}
