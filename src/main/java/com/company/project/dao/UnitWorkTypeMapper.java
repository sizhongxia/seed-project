package com.company.project.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.company.project.core.Mapper;
import com.company.project.model.UnitWorkType;

public interface UnitWorkTypeMapper extends Mapper<UnitWorkType> {
	public List<UnitWorkType> selectAllWorktype(@Param("proUuid") String proUuid);
}