package com.company.project.service.impl;

import com.company.project.dao.BusinessMemorabiliaImgMapper;
import com.company.project.model.BusinessMemorabiliaImg;
import com.company.project.service.BusinessMemorabiliaImgService;
import com.company.project.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by SiZhongXia on 2018/12/02.
 */
@Service
@Transactional
public class BusinessMemorabiliaImgServiceImpl extends AbstractService<BusinessMemorabiliaImg> implements BusinessMemorabiliaImgService {
    @Resource
    private BusinessMemorabiliaImgMapper businessMemorabiliaImgMapper;

}
