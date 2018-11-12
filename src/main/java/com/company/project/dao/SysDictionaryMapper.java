package com.company.project.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.company.project.core.Mapper;
import com.company.project.model.SysDictionary;

public interface SysDictionaryMapper extends Mapper<SysDictionary> {
	public List<String> selectAllTypes();

	public String selectTypeName(@Param("type") String type);
}