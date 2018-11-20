package com.company.project.service;
import com.company.project.core.Service;
import com.company.project.model.UnitProject;
import com.company.project.model.om.ProjectModel;


/**
 * Created by SiZhongXia on 2018/11/13.
 */
public interface UnitProjectService extends Service<UnitProject> {

	public void saveByTransactional(ProjectModel model);
	
}
