package com.company.project.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.company.project.core.Mapper;
import com.company.project.model.PersonnelIdentity;
import com.company.project.model.returns.GroupStatisticsResult;

public interface PersonnelIdentityMapper extends Mapper<PersonnelIdentity> {
	public List<GroupStatisticsResult> selectWorkTypeStatistics(@Param("proUuid") String proUuid);
}