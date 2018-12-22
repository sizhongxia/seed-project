package com.company.project.service.impl;

import com.company.project.dao.SmartCultureOrganizeMapper;
import com.company.project.model.SmartCultureOrganize;
import com.company.project.service.SmartCultureOrganizeService;
import com.company.project.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by SiZhongXia on 2018/12/21.
 */
@Service
@Transactional
public class SmartCultureOrganizeServiceImpl extends AbstractService<SmartCultureOrganize> implements SmartCultureOrganizeService {
    @Resource
    private SmartCultureOrganizeMapper smartCultureOrganizeMapper;

}
