package com.company.project.service.impl;

import com.company.project.dao.YeeTongBannerMapper;
import com.company.project.model.YeeTongBanner;
import com.company.project.service.YeeTongBannerService;
import com.company.project.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by SiZhongXia on 2018/12/15.
 */
@Service
@Transactional
public class YeeTongBannerServiceImpl extends AbstractService<YeeTongBanner> implements YeeTongBannerService {
    @Resource
    private YeeTongBannerMapper yeetongBannerMapper;

}
