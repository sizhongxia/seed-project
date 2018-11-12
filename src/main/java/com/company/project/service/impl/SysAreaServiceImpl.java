package com.company.project.service.impl;

import com.company.project.dao.SysAreaMapper;
import com.company.project.model.SysArea;
import com.company.project.service.SysAreaService;
import com.company.project.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by SiZhongXia on 2018/11/12.
 */
@Service
@Transactional
public class SysAreaServiceImpl extends AbstractService<SysArea> implements SysAreaService {
    @Resource
    private SysAreaMapper sysAreaMapper;

}
