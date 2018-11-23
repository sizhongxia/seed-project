package com.company.project.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.company.project.core.Mapper;
import com.company.project.model.UnitLaborsubcontractor;
import com.company.project.model.returns.apiv1.LaborsubcontractorModel;

public interface UnitLaborsubcontractorMapper extends Mapper<UnitLaborsubcontractor> {
	public List<LaborsubcontractorModel> selectProjectLaborsubcontractors(@Param("proUuid") String proUuid);
}