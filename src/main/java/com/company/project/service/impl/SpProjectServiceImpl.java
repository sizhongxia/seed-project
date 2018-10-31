package com.company.project.service.impl;

import com.company.project.dao.SpProjectMapper;
import com.company.project.model.SpProject;
import com.company.project.service.SpProjectService;
import com.company.project.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by SiZhongXia on 2018/10/31.
 */
@Service
@Transactional
public class SpProjectServiceImpl extends AbstractService<SpProject> implements SpProjectService {
    @Resource
    private SpProjectMapper spProjectMapper;

}
