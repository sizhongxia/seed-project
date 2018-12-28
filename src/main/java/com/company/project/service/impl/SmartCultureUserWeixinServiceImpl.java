package com.company.project.service.impl;

import com.company.project.dao.SmartCultureUserWeixinMapper;
import com.company.project.model.SmartCultureUserWeixin;
import com.company.project.service.SmartCultureUserWeixinService;
import com.company.project.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by SiZhongXia on 2018/12/28.
 */
@Service
@Transactional
public class SmartCultureUserWeixinServiceImpl extends AbstractService<SmartCultureUserWeixin> implements SmartCultureUserWeixinService {
    @Resource
    private SmartCultureUserWeixinMapper smartCultureUserWeixinMapper;

}
