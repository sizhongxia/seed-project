package com.company.project.service;

import java.util.List;

import com.company.project.core.Service;
import com.company.project.model.UnitWorkType;

/**
 * Created by SiZhongXia on 2018/11/12.
 */
public interface UnitWorkTypeService extends Service<UnitWorkType> {
	public List<UnitWorkType> selectAllWorktype(String proUuid);
}
