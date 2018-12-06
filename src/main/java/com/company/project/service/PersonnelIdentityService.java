package com.company.project.service;

import java.util.List;

import com.company.project.core.Service;
import com.company.project.model.PersonnelIdentity;
import com.company.project.model.returns.GroupStatisticsResult;

/**
 * Created by SiZhongXia on 2018/11/22.
 */
public interface PersonnelIdentityService extends Service<PersonnelIdentity> {
	public List<GroupStatisticsResult> selectWorkTypeStatistics(String proUuid);
}
