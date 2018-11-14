package com.company.project.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.project.annotation.TokenCheck;
import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.model.UnitLaborsubcontractor;
import com.company.project.model.UnitProject;
import com.company.project.model.om.ProjectModel;
import com.company.project.model.param.ProjectSearchParam;
import com.company.project.model.returns.Pagination;
import com.company.project.service.UnitLaborsubcontractorService;
import com.company.project.service.UnitProjectService;
import com.company.project.unit.UtcDateParseUtil;
import com.company.project.unit.UuidUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xiaoleilu.hutool.date.DateUtil;

import tk.mybatis.mapper.entity.Condition;
import tk.mybatis.mapper.entity.Example.Criteria;

@RestController
@RequestMapping("/unit/project")
public class UnitProjectController {
	private Logger logger = LoggerFactory.getLogger(UnitProjectController.class.getName());

	@Resource
	private UnitProjectService unitProjectService;
	@Resource
	private UnitLaborsubcontractorService unitLaborsubcontractorService;

	@TokenCheck
	@PostMapping("/data")
	public Result<?> data(@RequestBody ProjectSearchParam param) {
		logger.info("project search param:{}", param.toString());

		Condition condition = new Condition(UnitProject.class);
		Criteria criteria = condition.createCriteria();

		String name = param.getName();
		if (StringUtils.isNotBlank(name)) {
			criteria.andLike("name", String.format("%%%s%%", name.trim()));
		}
		String proName = param.getProName();
		if (StringUtils.isNotBlank(proName)) {
			criteria.andLike("proname", String.format("%%%s%%", proName.trim()));
		}
		String proCode = param.getProCode();
		if (StringUtils.isNotBlank(proCode)) {
			criteria.andLike("procode", String.format("%%%s%%", proCode.trim()));
		}
		String[] addTime = param.getAddTime();
		if (addTime != null && addTime.length == 2) {
			Date start = UtcDateParseUtil.parse(addTime[0]);
			if (start != null) {
				criteria.andGreaterThanOrEqualTo("addtime", start.getTime());
			}
			Date end = UtcDateParseUtil.parse(addTime[1]);
			if (end != null) {
				criteria.andLessThanOrEqualTo("addtime", end.getTime());
			}
		}
		String province = param.getProvince();
		if (StringUtils.isNotBlank(province)) {
			criteria.andEqualTo("province", province);
		}
		String city = param.getCity();
		if (StringUtils.isNotBlank(city)) {
			criteria.andEqualTo("city", city);
		}
		String county = param.getCounty();
		if (StringUtils.isNotBlank(county)) {
			criteria.andEqualTo("county", county);
		}

		Integer page = param.getPage();
		if (page == null || page.intValue() < 1) {
			page = 1;
		}
		Integer size = param.getSize();
		if (size == null || size.intValue() < 1) {
			size = 10;
		}
		condition.setOrderByClause("addtime asc, id asc");
		PageHelper.startPage(page, size);
		Page<UnitProject> _list = (Page<UnitProject>) unitProjectService.findByCondition(condition);
		List<UnitProject> result = _list.getResult();
		List<Map<String, Object>> list = new ArrayList<>();
		if (result != null && result.size() > 0) {
			Map<String, Object> item = null;
			for (UnitProject i : result) {
				item = new HashMap<>();
				item.put("key", i.getUuid());
				item.put("name", i.getName());
				item.put("proname", i.getProname());
				item.put("procode", i.getProcode());
				// item.put("longitude", i.getLongitude());
				// item.put("latitude", i.getLatitude());
				// item.put("companyuuid", i.getCompanyuuid());
				// item.put("building", i.getBuilding());
				// item.put("supervision", i.getSupervision());
				// item.put("survey", i.getSurvey());
				// item.put("construction", i.getConstruction());
				// item.put("design", i.getDesign());
				// item.put("building", i.getWidth());
				// item.put("building", i.getLength());
				// item.put("building", i.getLocationmap());
				item.put("addtime", DateUtil.format(new Date(i.getAddtime()), "yyyy-MM-dd HH:mm"));
				item.put("updatetime", DateUtil.format(new Date(i.getUpdatetime()), "yyyy-MM-dd HH:mm"));
				list.add(item);
			}
		}

		Map<String, Object> data = new HashMap<>();
		data.put("list", list);
		Pagination pagination = new Pagination();
		pagination.setTotal(_list.getTotal());
		pagination.setCurrent(_list.getPageNum());
		pagination.setPageSize(_list.getPageSize());
		data.put("pagination", pagination);

		return ResultGenerator.genSuccessResult(data);
	}

	@TokenCheck
	@PostMapping("/getById")
	public Result<?> getById(@RequestBody ProjectModel model) {

		if (StringUtils.isBlank(model.getUuid())) {
			return ResultGenerator.genFailResult("无效的表单");
		}

		UnitProject project = unitProjectService.findBy("uuid", model.getUuid().trim());
		if (project == null) {
			return ResultGenerator.genFailResult("无效的表单");
		}
		BeanUtils.copyProperties(project, model);
		String[] areainfo = new String[] { project.getProvince(), project.getCity(), project.getCounty() };
		model.setAreainfo(areainfo);
		if (project.getPlanstarttime() != null && project.getPlanstarttime().longValue() > 0) {
			model.setPlanstarttime(DateUtil.format(new Date(project.getPlanstarttime()), "yyyy-MM-dd"));
		}
		if (project.getPlanendtime() != null && project.getPlanendtime().longValue() > 0) {
			model.setPlanendtime(DateUtil.format(new Date(project.getPlanendtime()), "yyyy-MM-dd"));
		}
		if (project.getInvestment() != null) {
			model.setInvestment(project.getInvestment().toString());
		}
		if (project.getMeasure() != null) {
			model.setMeasure(project.getMeasure().toString());
		}
		if (StringUtils.isNotBlank(project.getMainstructuretype())) {
			model.setMainstructuretype(project.getMainstructuretype().split("[,]"));
		}
		if (StringUtils.isNotBlank(project.getFunction())) {
			model.setFunction(project.getFunction().split("[,]"));
		}

		Condition condition = new Condition(UnitLaborsubcontractor.class);
		condition.createCriteria().andEqualTo("state", 0).andEqualTo("prouuid", project.getUuid());
		List<UnitLaborsubcontractor> uls = unitLaborsubcontractorService.findByCondition(condition);
		if (uls != null && uls.size() > 0) {
			List<String> cuids = new ArrayList<>();
			for (UnitLaborsubcontractor ul : uls) {
				cuids.add(ul.getCompanyuuid());
			}
			String[] strings = new String[cuids.size()];
			cuids.toArray(strings);
			model.setSubcontractorcompany(strings);
		}
		return ResultGenerator.genSuccessResult(model);
	}

	@TokenCheck
	@PostMapping("/deleteById")
	public Result<?> deleteById(@RequestBody ProjectModel model) {

		if (StringUtils.isNotBlank(model.getUuid())) {
			UnitProject project = unitProjectService.findBy("uuid", model.getUuid());
			if (project == null) {
				return ResultGenerator.genFailResult("无效的表单02");
			}
			project.setState(1);
			project.setUpdatetime(System.currentTimeMillis());
			unitProjectService.update(project);
			return ResultGenerator.genSuccessResult();
		} else if (model.getIds() != null && model.getIds().length > 0) {
			String[] ids = model.getIds();
			for (String id : ids) {
				UnitProject project = unitProjectService.findBy("uuid", id);
				if (project == null) {
					return ResultGenerator.genFailResult("无效的表单04");
				}
				project.setState(1);
				project.setUpdatetime(System.currentTimeMillis());
				unitProjectService.update(project);
			}
			return ResultGenerator.genSuccessResult();
		}
		return ResultGenerator.genFailResult("无效的表单");
	}

	@TokenCheck
	@PostMapping("/save")
	public Result<?> save(@Validated @RequestBody ProjectModel model, BindingResult bindingResult,
			HttpServletRequest request) {
		if (bindingResult.hasErrors()) {
			return ResultGenerator.genFailResult(bindingResult.getFieldError().getDefaultMessage());
		}
		UnitProject project = unitProjectService.findBy("procode", model.getProcode());
		if (project != null) {
			return ResultGenerator.genFailResult("当前工地编码已存在");
		}

		project = new UnitProject();
		BeanUtils.copyProperties(model, project);
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
		unitProjectService.save(project);

		String[] contractorcompanys = model.getSubcontractorcompany();
		if (contractorcompanys != null && contractorcompanys.length > 0) {
			List<UnitLaborsubcontractor> uls = new ArrayList<>();
			for (String ulId : contractorcompanys) {
				UnitLaborsubcontractor laborsubcontractor = new UnitLaborsubcontractor();
				laborsubcontractor.setAddtime(System.currentTimeMillis());
				laborsubcontractor.setProuuid(project.getUuid());
				laborsubcontractor.setCompanyuuid(ulId);
				laborsubcontractor.setState(0);
				uls.add(laborsubcontractor);
			}
			unitLaborsubcontractorService.save(uls);
		}

		return ResultGenerator.genSuccessResult();
	}

	@TokenCheck
	@PostMapping("/update")
	public Result<?> update(@Validated @RequestBody ProjectModel model, BindingResult bindingResult,
			HttpServletRequest request) {
		if (bindingResult.hasErrors()) {
			return ResultGenerator.genFailResult(bindingResult.getFieldError().getDefaultMessage());
		}

		UnitProject project = unitProjectService.findBy("uuid", model.getUuid());
		if (project == null) {
			return ResultGenerator.genFailResult("无效的工地ID");
		}

		UnitProject _project = unitProjectService.findBy("procode", model.getProcode());
		if (_project != null && !_project.getUuid().equals(project.getUuid())) {
			return ResultGenerator.genFailResult("当前工地编码已存在");
		}

		project.setProname(model.getProname());
		project.setName(model.getName());
		project.setProcode(model.getProcode());
		project.setCompanyuuid(model.getCompanyuuid());
		project.setNumberoflayers(model.getNumberoflayers());
		project.setLongitude(model.getLongitude());
		project.setLatitude(model.getLatitude());
		project.setType(model.getType());
		project.setConstructionnature(model.getConstructionnature());
		project.setConstruction(model.getConstruction());
		project.setBuilding(model.getBuilding());
		project.setDesign(model.getDesign());
		project.setSurvey(model.getSurvey());
		project.setSupervision(model.getSupervision());

		project.setPersonname(model.getPersonname());
		project.setPhone(model.getPhone());
		project.setSalesname(model.getSalesname());
		project.setSalesphone(model.getSalesphone());

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

		String measure = model.getMeasure();
		if (NumberUtils.isParsable(measure)) {
			project.setMeasure(new Double(measure));
		}
		String investment = model.getInvestment();
		if (NumberUtils.isParsable(investment)) {
			project.setInvestment(new Double(investment));
		}
		project.setUpdatetime(System.currentTimeMillis());
		unitProjectService.update(project);

		String[] contractorcompanys = model.getSubcontractorcompany();
		if (contractorcompanys != null && contractorcompanys.length > 0) {
			List<UnitLaborsubcontractor> uls = new ArrayList<>();
			for (String ulId : contractorcompanys) {
				UnitLaborsubcontractor laborsubcontractor = new UnitLaborsubcontractor();
				laborsubcontractor.setAddtime(System.currentTimeMillis());
				laborsubcontractor.setProuuid(project.getUuid());
				laborsubcontractor.setCompanyuuid(ulId);
				laborsubcontractor.setState(0);
				uls.add(laborsubcontractor);
			}
			//unitLaborsubcontractorService.save(uls);
		}

		return ResultGenerator.genSuccessResult();
	}

}
