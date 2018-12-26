package com.company.project.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.company.project.dao.SmartCultureCommonMapper;
import com.company.project.service.SmartCultureCommonService;

@Service
@Transactional
public class SmartCultureCommonServiceImpl implements SmartCultureCommonService {
	@Resource
	private SmartCultureCommonMapper smartCultureCommonMapper;

	@Override
	public List<String> getFarmWeatherCities() {
		return smartCultureCommonMapper.getFarmWeatherCities();
	}

}
