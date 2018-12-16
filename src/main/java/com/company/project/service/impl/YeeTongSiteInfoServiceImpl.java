package com.company.project.service.impl;

import com.company.project.dao.YeeTongSiteInfoMapper;
import com.company.project.model.YeeTongSiteInfo;
import com.company.project.service.YeeTongSiteInfoService;
import com.company.project.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by SiZhongXia on 2018/12/16.
 */
@Service
@Transactional
public class YeeTongSiteInfoServiceImpl extends AbstractService<YeeTongSiteInfo> implements YeeTongSiteInfoService {
    @Resource
    private YeeTongSiteInfoMapper yeetongSiteinfoMapper;

}
