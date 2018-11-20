package com.company.project.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.company.project.core.AbstractService;
import com.company.project.dao.UnitProjectMapper;
import com.company.project.model.UnitCompany;
import com.company.project.model.UnitLaborsubcontractor;
import com.company.project.model.UnitProject;
import com.company.project.model.UnitProjectConfig;
import com.company.project.model.UserIdentity;
import com.company.project.model.UserLoginAccount;
import com.company.project.model.om.ProjectModel;
import com.company.project.service.UnitCompanyService;
import com.company.project.service.UnitLaborsubcontractorService;
import com.company.project.service.UnitProjectConfigService;
import com.company.project.service.UnitProjectService;
import com.company.project.service.UserIdentityService;
import com.company.project.service.UserLoginAccountService;
import com.company.project.unit.Md5Util;
import com.company.project.unit.UtcDateParseUtil;
import com.company.project.unit.UuidUtil;
import com.xiaoleilu.hutool.date.DateUtil;
import com.xiaoleilu.hutool.util.RandomUtil;

import tk.mybatis.mapper.entity.Condition;

/**
 * Created by SiZhongXia on 2018/11/13.
 */
@Service
@Transactional
public class UnitProjectServiceImpl extends AbstractService<UnitProject> implements UnitProjectService {
	@Resource
	private UnitProjectMapper unitProjectMapper;
	@Resource
	private UnitCompanyService unitCompanyService;
	@Resource
	private UnitProjectConfigService unitProjectConfigService;
	@Resource
	private UserLoginAccountService userLoginAccountService;
	@Autowired
	private UserIdentityService userIdentityService;
	@Resource
	private UnitLaborsubcontractorService unitLaborsubcontractorService;

	@Override
	public void saveByTransactional(ProjectModel model) {
		String procode = DateUtil.format(new Date(), "yyyyMMdd");

		Condition unitProjectCondition = new Condition(UnitProject.class);
		unitProjectCondition.createCriteria().andLike("procode", procode + "%");
		List<UnitProject> upByCodes = this.findByCondition(unitProjectCondition);
		if (upByCodes == null) {
			procode = procode + "0001" + RandomUtil.randomNumbers(4);
		} else {
			String no = "0000" + upByCodes.size();
			procode = procode + no.substring(no.length() - 4, no.length()) + RandomUtil.randomNumbers(4);
		}

		UnitProject project = this.findBy("procode", procode);
		if (project != null) {
			throw new RuntimeException("当前项目编码已存在");
		}

		project = new UnitProject();
		BeanUtils.copyProperties(model, project);
		project.setProcode(procode);
		project.setUuid(UuidUtil.init());
		String[] areaInfo = model.getAreainfo();
		if (areaInfo.length > 0) {
			project.setProvince(areaInfo[0]);
			if (areaInfo.length > 1) {
				project.setCity(areaInfo[1]);
				if (areaInfo.length > 2) {
					project.setCounty(areaInfo[2]);
				}
			}
		}

		String[] functions = model.getFunction();
		if (functions != null && functions.length > 0) {
			project.setFunction(String.join(",", functions));
		}

		String[] mainstructuretypes = model.getMainstructuretype();
		if (mainstructuretypes != null && mainstructuretypes.length > 0) {
			project.setMainstructuretype(String.join(",", mainstructuretypes));
		}

		String planstarttime = model.getPlanstarttime();
		if (StringUtils.isNotBlank(planstarttime)) {
			try {
				Date time = UtcDateParseUtil.parse(planstarttime);
				if (time != null) {
					project.setPlanstarttime(time.getTime());
				}
			} catch (Exception e) {
			}
		}

		String planendttime = model.getPlanendtime();
		if (StringUtils.isNotBlank(planendttime)) {
			try {
				Date time = UtcDateParseUtil.parse(planendttime);
				if (time != null) {
					project.setPlanendtime(time.getTime());
				}
			} catch (Exception e) {
			}
		}

		if (NumberUtils.isDigits(model.getLength())) {
			project.setLength(Integer.parseInt(model.getLength().trim()));
		} else {
			project.setLength(null);
		}
		if (NumberUtils.isDigits(model.getWidth())) {
			project.setWidth(Integer.parseInt(model.getWidth().trim()));
		} else {
			project.setWidth(null);
		}

		String measure = model.getMeasure();
		if (NumberUtils.isParsable(measure)) {
			project.setMeasure(new Double(measure));
		}
		String investment = model.getInvestment();
		if (NumberUtils.isParsable(investment)) {
			project.setInvestment(new Double(investment));
		}
		project.setState(0);
		project.setAddtime(System.currentTimeMillis());
		project.setUpdatetime(System.currentTimeMillis());
		this.save(project);

		String[] contractorcompanys = model.getSubcontractorcompany();
		if (contractorcompanys != null && contractorcompanys.length > 0) {
			List<UnitLaborsubcontractor> uls = new ArrayList<>();
			for (String ulId : contractorcompanys) {
				if (StringUtils.isBlank(ulId)) {
					continue;
				}
				UnitCompany unitCompany = unitCompanyService.findBy("uuid", ulId);
				if (unitCompany == null) {
					continue;
				}
				UnitLaborsubcontractor laborsubcontractor = new UnitLaborsubcontractor();
				laborsubcontractor.setAddtime(System.currentTimeMillis());
				laborsubcontractor.setProuuid(project.getUuid());
				laborsubcontractor.setCompanyuuid(ulId);
				laborsubcontractor.setState(0);
				uls.add(laborsubcontractor);
			}
			unitLaborsubcontractorService.save(uls);
		}

		String personnelpositioning = model.getPersonnelpositioning();
		if (!NumberUtils.isDigits(personnelpositioning)) {
			personnelpositioning = "0";
		}

		UnitProjectConfig unitProjectConfig = new UnitProjectConfig();
		unitProjectConfig.setProuuid(project.getUuid());
		unitProjectConfig.setIspersonnelpositioning(Integer.parseInt(personnelpositioning.trim()));
		unitProjectConfig.setIsshowgov(0);
		unitProjectConfigService.save(unitProjectConfig);

		String username = model.getUsername().trim();
		UserLoginAccount ula = userLoginAccountService.findBy("username", username);
		if (ula == null) {
			ula = new UserLoginAccount();
			ula.setUuid(UuidUtil.init());
			ula.setUsername(username);
			ula.setPhone("");
			ula.setPassword(Md5Util.md5(model.getPassword().trim(), username));
			ula.setSex(1);
			ula.setState(0);
			ula.setAddtime(System.currentTimeMillis());
			ula.setUpdatetime(System.currentTimeMillis());
			userLoginAccountService.save(ula);
		} else {
			throw new RuntimeException("超管账号已存在，请更改为其他账号");
		}
		UserIdentity userIdentity = new UserIdentity();
		userIdentity.setUuid(UuidUtil.init());
		userIdentity.setUseruuid(ula.getUuid());
		userIdentity.setType(1);
		userIdentity.setIsdefault(1);
		userIdentity.setDeptuuid(project.getUuid());
		userIdentity.setRoleuuid("");// 超管
		userIdentity.setIsloging(0);
		userIdentity.setSinglesignon(0);
		userIdentity.setAddtime(System.currentTimeMillis());
		userIdentity.setUpdatetime(System.currentTimeMillis());
		userIdentity.setPasstime(System.currentTimeMillis());
		userIdentity.setState(0);
		userIdentity.setIssuper(1);
		userIdentity.setCompanyuuid(project.getCompanyuuid());
		userIdentityService.save(userIdentity);
	}
}
