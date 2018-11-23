package com.company.project.service;

import java.util.List;

import com.company.project.core.Service;
import com.company.project.model.UnitLaborsubcontractor;
import com.company.project.model.returns.apiv1.LaborsubcontractorModel;

/**
 * Created by SiZhongXia on 2018/11/14.
 */
public interface UnitLaborsubcontractorService extends Service<UnitLaborsubcontractor> {
	public List<LaborsubcontractorModel> selectProjectLaborsubcontractors(String proUuid);
}
