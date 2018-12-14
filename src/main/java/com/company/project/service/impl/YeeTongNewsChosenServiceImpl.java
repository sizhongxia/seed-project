package com.company.project.service.impl;

import com.company.project.dao.YeeTongNewsChosenMapper;
import com.company.project.model.YeeTongNewsChosen;
import com.company.project.service.YeeTongNewsChosenService;
import com.company.project.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by SiZhongXia on 2018/12/14.
 */
@Service
@Transactional
public class YeeTongNewsChosenServiceImpl extends AbstractService<YeeTongNewsChosen> implements YeeTongNewsChosenService {
    @Resource
    private YeeTongNewsChosenMapper yeetongNewsChosenMapper;

}
