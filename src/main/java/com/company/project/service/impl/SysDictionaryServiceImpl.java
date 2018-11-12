package com.company.project.service.impl;

import com.company.project.dao.SysDictionaryMapper;
import com.company.project.model.SysDictionary;
import com.company.project.service.SysDictionaryService;
import com.company.project.core.AbstractService;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import javax.annotation.Resource;

/**
 * Created by SiZhongXia on 2018/11/12.
 */
@Service
@Transactional
public class SysDictionaryServiceImpl extends AbstractService<SysDictionary> implements SysDictionaryService {
	@Resource
	private SysDictionaryMapper sysDictionaryMapper;

	@Override
	public List<String> selectAllTypes() {
		return sysDictionaryMapper.selectAllTypes();
	}

	@Override
	public String selectTypeName(String type) {
		if (StringUtils.isBlank(type)) {
			return "";
		}
		String name = sysDictionaryMapper.selectTypeName(type);
		if (name == null) {
			return "";
		}
		return name;
	}

}
