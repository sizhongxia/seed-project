package com.company.project.service.impl;

import com.company.project.dao.PersonnelIdentityMapper;
import com.company.project.model.PersonnelIdentity;
import com.company.project.model.returns.GroupStatisticsResult;
import com.company.project.service.PersonnelIdentityService;
import com.company.project.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import javax.annotation.Resource;

/**
 * Created by SiZhongXia on 2018/11/22.
 */
@Service
@Transactional
public class PersonnelIdentityServiceImpl extends AbstractService<PersonnelIdentity>
		implements PersonnelIdentityService {
	@Resource
	private PersonnelIdentityMapper personnelIdentityMapper;

	@Override
	public List<GroupStatisticsResult> selectWorkTypeStatistics(String proUuid) {
		return personnelIdentityMapper.selectWorkTypeStatistics(proUuid);
	}

}
