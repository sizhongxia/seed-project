package com.company.project.service.impl;

import com.company.project.dao.YeeTongProductMapper;
import com.company.project.model.YeeTongProduct;
import com.company.project.service.YeeTongProductService;
import com.company.project.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by SiZhongXia on 2018/12/15.
 */
@Service
@Transactional
public class YeeTongProductServiceImpl extends AbstractService<YeeTongProduct> implements YeeTongProductService {
    @Resource
    private YeeTongProductMapper yeetongProductMapper;

}
