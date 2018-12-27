package com.company.project.service.impl;

import com.company.project.dao.SmartCultureFarmPicMapper;
import com.company.project.model.SmartCultureFarmPic;
import com.company.project.service.SmartCultureFarmPicService;
import com.company.project.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by SiZhongXia on 2018/12/27.
 */
@Service
@Transactional
public class SmartCultureFarmPicServiceImpl extends AbstractService<SmartCultureFarmPic> implements SmartCultureFarmPicService {
    @Resource
    private SmartCultureFarmPicMapper smartCultureFarmPicMapper;

}
