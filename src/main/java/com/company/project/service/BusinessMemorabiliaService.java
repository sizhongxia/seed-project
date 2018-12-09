package com.company.project.service;
import java.util.List;

import com.company.project.core.Service;
import com.company.project.model.BusinessMemorabilia;
import com.company.project.model.returns.apiv1.BusinessMemorabiliaResult;


/**
 * Created by SiZhongXia on 2018/12/03.
 */
public interface BusinessMemorabiliaService extends Service<BusinessMemorabilia> {

	public List<BusinessMemorabiliaResult> findProjectList(String pid);

}
