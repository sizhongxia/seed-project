package com.company.project.service;

import java.util.List;

import com.company.project.core.Service;
import com.company.project.model.PersonnelRealnameSystem;
import com.company.project.model.om.PersonnelModel;
import com.company.project.model.returns.apiv1.PersonnelResult;

/**
 * Created by SiZhongXia on 2018/11/22.
 */
public interface PersonnelRealnameSystemService extends Service<PersonnelRealnameSystem> {

	void saveByTransactional(PersonnelModel model);

	public List<PersonnelResult> selectProjectPersonnels(String proUuid);

}
