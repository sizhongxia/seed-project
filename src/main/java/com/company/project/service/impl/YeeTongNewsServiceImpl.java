package com.company.project.service.impl;

import com.company.project.dao.YeeTongNewsMapper;
import com.company.project.model.YeeTongNews;
import com.company.project.service.YeeTongNewsService;
import com.company.project.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by SiZhongXia on 2018/12/14.
 */
@Service
@Transactional
public class YeeTongNewsServiceImpl extends AbstractService<YeeTongNews> implements YeeTongNewsService {
    @Resource
    private YeeTongNewsMapper yeetongNewsMapper;

}
