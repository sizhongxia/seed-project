package com.company.project.service.impl;

import com.company.project.dao.UnitPostMapper;
import com.company.project.model.UnitPost;
import com.company.project.service.UnitPostService;
import com.company.project.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by SiZhongXia on 2018/11/13.
 */
@Service
@Transactional
public class UnitPostServiceImpl extends AbstractService<UnitPost> implements UnitPostService {
    @Resource
    private UnitPostMapper unitPostMapper;

}
