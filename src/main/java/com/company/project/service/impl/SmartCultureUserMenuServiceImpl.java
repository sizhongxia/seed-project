package com.company.project.service.impl;

import com.company.project.dao.SmartCultureUserMenuMapper;
import com.company.project.model.SmartCultureUserMenu;
import com.company.project.service.SmartCultureUserMenuService;
import com.company.project.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by SiZhongXia on 2018/12/25.
 */
@Service
@Transactional
public class SmartCultureUserMenuServiceImpl extends AbstractService<SmartCultureUserMenu> implements SmartCultureUserMenuService {
    @Resource
    private SmartCultureUserMenuMapper smartCultureUserMenuMapper;

}
