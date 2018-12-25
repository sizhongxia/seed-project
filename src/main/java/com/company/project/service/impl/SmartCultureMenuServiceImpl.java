package com.company.project.service.impl;

import com.company.project.dao.SmartCultureMenuMapper;
import com.company.project.model.SmartCultureMenu;
import com.company.project.service.SmartCultureMenuService;
import com.company.project.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by SiZhongXia on 2018/12/25.
 */
@Service
@Transactional
public class SmartCultureMenuServiceImpl extends AbstractService<SmartCultureMenu> implements SmartCultureMenuService {
    @Resource
    private SmartCultureMenuMapper smartCultureMenuMapper;

}
