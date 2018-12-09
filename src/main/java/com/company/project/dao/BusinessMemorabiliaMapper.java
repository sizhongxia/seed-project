package com.company.project.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.company.project.core.Mapper;
import com.company.project.model.BusinessMemorabilia;
import com.company.project.model.returns.apiv1.BusinessMemorabiliaResult;

public interface BusinessMemorabiliaMapper extends Mapper<BusinessMemorabilia> {

	public List<BusinessMemorabiliaResult> findProjectList(@Param("pid") String pid);
}