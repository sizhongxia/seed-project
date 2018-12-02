package com.company.project.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.company.project.core.Mapper;
import com.company.project.model.PersonnelRealnameSystem;
import com.company.project.model.returns.apiv1.PersonnelResult;

public interface PersonnelRealnameSystemMapper extends Mapper<PersonnelRealnameSystem> {

	public String selectMaxEmpNo();

	public List<PersonnelResult> selectProjectPersonnels(@Param("proUuid") String proUuid);
}