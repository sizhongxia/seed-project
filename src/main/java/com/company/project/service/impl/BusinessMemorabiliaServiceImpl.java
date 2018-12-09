package com.company.project.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.company.project.core.AbstractService;
import com.company.project.dao.BusinessMemorabiliaMapper;
import com.company.project.model.BusinessMemorabilia;
import com.company.project.model.returns.apiv1.BusinessMemorabiliaResult;
import com.company.project.service.BusinessMemorabiliaService;


/**
 * Created by SiZhongXia on 2018/12/03.
 */
@Service
@Transactional
public class BusinessMemorabiliaServiceImpl extends AbstractService<BusinessMemorabilia> implements BusinessMemorabiliaService {
    @Resource
    private BusinessMemorabiliaMapper businessMemorabiliaMapper;

	@Override
	public List<BusinessMemorabiliaResult> findProjectList(String pid) {
		return businessMemorabiliaMapper.findProjectList(pid);
	}

}
