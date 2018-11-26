package com.company.project.service.impl;

import com.company.project.dao.UnitWorkTypeMapper;
import com.company.project.model.UnitWorkType;
import com.company.project.service.UnitWorkTypeService;
import com.company.project.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import javax.annotation.Resource;

/**
 * Created by SiZhongXia on 2018/11/12.
 */
@Service
@Transactional
public class UnitWorkTypeServiceImpl extends AbstractService<UnitWorkType> implements UnitWorkTypeService {
	@Resource
	private UnitWorkTypeMapper unitWorktypeMapper;

	@Override
	public List<UnitWorkType> selectAllWorktype(String proUuid) {
		return unitWorktypeMapper.selectAllWorktype(proUuid);
	}

}
