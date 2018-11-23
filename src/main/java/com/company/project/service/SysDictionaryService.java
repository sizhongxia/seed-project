package com.company.project.service;
import com.company.project.model.SysDictionary;

import java.util.List;

import com.company.project.core.Service;


/**
 * Created by SiZhongXia on 2018/11/12.
 */
public interface SysDictionaryService extends Service<SysDictionary> {
	public List<String> selectAllTypes();

	public String selectTypeName(String type);

	public String selectValueName(String type, Integer value);
}
