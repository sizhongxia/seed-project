package com.company.project.service.impl;

import com.company.project.dao.BusinessMemorabiliaMapper;
import com.company.project.model.BusinessMemorabilia;
import com.company.project.service.BusinessMemorabiliaService;
import com.company.project.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by SiZhongXia on 2018/12/03.
 */
@Service
@Transactional
public class BusinessMemorabiliaServiceImpl extends AbstractService<BusinessMemorabilia> implements BusinessMemorabiliaService {
    @Resource
    private BusinessMemorabiliaMapper businessMemorabiliaMapper;

}
