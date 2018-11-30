package com.company.project.web.apiv1;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

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
import com.company.project.model.SysArea;
import com.company.project.model.UnitCompany;
import com.company.project.model.UnitDepartment;
import com.company.project.model.UnitGroup;
import com.company.project.model.UnitPost;
import com.company.project.model.UnitProject;
import com.company.project.model.UnitWorkType;
import com.company.project.model.param.apiv1.CommonParam;
import com.company.project.model.param.apiv1.GroupParam;
import com.company.project.model.param.apiv1.PostParam;
import com.company.project.model.returns.apiv1.KeyValueResult;
import com.company.project.model.returns.apiv1.LaborsubcontractorModel;
import com.company.project.model.returns.apiv1.ProjectResult;
import com.company.project.service.SysAreaService;
import com.company.project.service.SysDictionaryService;
import com.company.project.service.UnitCompanyService;
import com.company.project.service.UnitDepartmentService;
import com.company.project.service.UnitGroupService;
import com.company.project.service.UnitLaborsubcontractorService;
import com.company.project.service.UnitPostService;
import com.company.project.service.UnitProjectService;
import com.company.project.service.UnitWorkTypeService;
import com.company.project.unit.LongDataFormatUtil;

import tk.mybatis.mapper.entity.Condition;

@RestController
@RequestMapping("/apiv1/project")
public class ProjectController {

	final Logger logger = LoggerFactory.getLogger(ProjectController.class);

	@Resource
	private UnitProjectService unitProjectService;
	@Resource
	private SysDictionaryService sysDictionaryService;
	@Resource
	private SysAreaService sysAreaService;
	@Resource
	private UnitLaborsubcontractorService unitLaborsubcontractorService;
	@Resource
	private UnitCompanyService unitCompanyService;
	@Resource
	private UnitWorkTypeService unitWorkTypeService;
	@Resource
	private UnitGroupService unitGroupService;
	@Resource
	private UnitDepartmentService unitDepartmentService;
	@Resource
	private UnitPostService unitPostService;

	@TokenCheck
	@PostMapping("/detail")
	public Result<?> projectDetail(@Validated @RequestBody CommonParam param, BindingResult bindingResult,
			HttpServletRequest request) {
		if (bindingResult.hasErrors()) {
			return ResultGenerator.genFailResult(bindingResult.getFieldError().getDefaultMessage());
		}
		UnitProject project = unitProjectService.findBy("uuid", param.getPid());
		if (project == null) {
			return ResultGenerator.genFailResult("项目可能已被删除");
		}
		if (project.getState().intValue() != 0) {
			return ResultGenerator.genFailResult("项目已被禁止访问");
		}
		ProjectResult projectResult = new ProjectResult();
		BeanUtils.copyProperties(project, projectResult);

		projectResult.setPlanstarttime(LongDataFormatUtil.formatDate(project.getPlanstarttime()));
		projectResult.setPlanendtime(LongDataFormatUtil.formatDate(project.getPlanendtime()));
		Double measure = project.getMeasure();
		DecimalFormat df = new DecimalFormat("#0.00");
		if (measure != null) {
			projectResult.setMeasure(String.format("%s%s", df.format(new BigDecimal(measure)), "平方米"));
		}
		Double investment = project.getInvestment();
		if (investment != null) {
			projectResult.setInvestment(String.format("%s%s", df.format(new BigDecimal(investment)), "万元"));
		}
		if (NumberUtils.isParsable(project.getType())) {
			projectResult.setTypename(
					sysDictionaryService.selectValueName("proType", Integer.parseInt(project.getType().trim())));
		}
		if (NumberUtils.isParsable(project.getConstructionnature())) {
			projectResult.setConstructionnaturename(sysDictionaryService.selectValueName("proConstructionNature",
					Integer.parseInt(project.getConstructionnature().trim())));
		}

		if (project.getWidth() != null) {
			projectResult.setWidth(project.getWidth().toString() + "米");
		}
		if (project.getLength() != null) {
			projectResult.setLength(project.getLength().toString() + "米");
		}

		projectResult.setBuildingname("");
		if (StringUtils.isNotBlank(projectResult.getBuilding())) {
			UnitCompany unit = unitCompanyService.findBy("uuid", projectResult.getBuilding());
			if (unit != null) {
				projectResult.setBuildingname(unit.getCompanyname());
			}
		}

		projectResult.setConstructionname("");
		if (StringUtils.isNotBlank(projectResult.getConstruction())) {
			UnitCompany unit = unitCompanyService.findBy("uuid", projectResult.getConstruction());
			if (unit != null) {
				projectResult.setConstructionname(unit.getCompanyname());
			}
		}

		projectResult.setSurveyname("");
		if (StringUtils.isNotBlank(projectResult.getSurvey())) {
			UnitCompany unit = unitCompanyService.findBy("uuid", projectResult.getSurvey());
			if (unit != null) {
				projectResult.setSurveyname(unit.getCompanyname());
			}
		}

		projectResult.setSupervisionname("");
		if (StringUtils.isNotBlank(projectResult.getSupervision())) {
			UnitCompany unit = unitCompanyService.findBy("uuid", projectResult.getSupervision());
			if (unit != null) {
				projectResult.setSupervisionname(unit.getCompanyname());
			}
		}

		projectResult.setDesignname("");
		if (StringUtils.isNotBlank(projectResult.getDesign())) {
			UnitCompany unit = unitCompanyService.findBy("uuid", projectResult.getDesign());
			if (unit != null) {
				projectResult.setDesignname(unit.getCompanyname());
			}
		}

		if (StringUtils.isNotBlank(project.getMainstructuretype())) {
			String[] mainstructuretypes = project.getMainstructuretype().split("[,]");
			StringBuffer sb = new StringBuffer("");
			for (String mainstructuretype : mainstructuretypes) {
				if (sb.length() > 0) {
					sb.append(", ");
				}
				sb.append(sysDictionaryService.selectValueName("proMainStructureType",
						Integer.parseInt(mainstructuretype.trim())));
			}
			projectResult.setMainstructuretypenames(sb.toString());
		}

		if (StringUtils.isNotBlank(project.getFunctions())) {
			String[] functions = project.getFunctions().split("[,]");
			StringBuffer sb = new StringBuffer("");
			for (String function : functions) {
				if (sb.length() > 0) {
					sb.append(", ");
				}
				sb.append(sysDictionaryService.selectValueName("proFunction", Integer.parseInt(function.trim())));
			}
			projectResult.setFunctionname(sb.toString());
		}

		SysArea area = null;
		if (NumberUtils.isParsable(project.getProvince())) {
			area = sysAreaService.findBy("code", Integer.parseInt(project.getProvince().trim()));
			if (area != null) {
				projectResult.setProvince(area.getName());
			}
		}
		if (NumberUtils.isParsable(project.getCity())) {
			area = sysAreaService.findBy("code", Integer.parseInt(project.getCity().trim()));
			if (area != null) {
				projectResult.setCity(area.getName());
			}
		}
		if (NumberUtils.isParsable(project.getCounty())) {
			area = sysAreaService.findBy("code", Integer.parseInt(project.getCounty().trim()));
			if (area != null) {
				projectResult.setCounty(area.getName());
			}
		}

		projectResult.setLaborsubcontractors(
				unitLaborsubcontractorService.selectProjectLaborsubcontractors(project.getUuid()));
		return ResultGenerator.genSuccessResult(projectResult);
	}

	@TokenCheck
	@PostMapping("/laborsubcontractors")
	public Result<?> laborsubcontractors(@Validated @RequestBody CommonParam param, BindingResult bindingResult,
			HttpServletRequest request) {
		List<KeyValueResult> kvs = new ArrayList<>();
		if (bindingResult.hasErrors()) {
			logger.error(bindingResult.getFieldError().getDefaultMessage());
			return ResultGenerator.genSuccessResult(kvs);
		}

		UnitProject project = unitProjectService.findBy("uuid", param.getPid());
		if (project == null) {
			logger.error("项目可能已被删除");
			return ResultGenerator.genSuccessResult(kvs);
		}

		List<LaborsubcontractorModel> lsms = unitLaborsubcontractorService
				.selectProjectLaborsubcontractors(param.getPid());
		if (lsms != null && lsms.size() > 0) {
			KeyValueResult kv = null;
			for (LaborsubcontractorModel lsm : lsms) {
				kv = new KeyValueResult();
				kv.setKey(lsm.getCompanyname());
				kv.setValue(lsm.getCompanyid());
				kvs.add(kv);
			}
		}
		return ResultGenerator.genSuccessResult(kvs);
	}

	@TokenCheck
	@PostMapping("/constructions")
	public Result<?> constructions(@Validated @RequestBody CommonParam param, BindingResult bindingResult,
			HttpServletRequest request) {
		List<KeyValueResult> kvs = new ArrayList<>();
		if (bindingResult.hasErrors()) {
			logger.error(bindingResult.getFieldError().getDefaultMessage());
			return ResultGenerator.genSuccessResult(kvs);
		}

		UnitProject project = unitProjectService.findBy("uuid", param.getPid());
		if (project == null) {
			logger.error("项目可能已被删除");
			return ResultGenerator.genSuccessResult(kvs);
		}

		UnitCompany unit = unitCompanyService.findBy("uuid", project.getConstruction());
		if (unit != null) {
			KeyValueResult kv = new KeyValueResult();
			kv.setKey(unit.getCompanyname());
			kv.setValue(unit.getUuid());
			kvs.add(kv);
		}

		return ResultGenerator.genSuccessResult(kvs);
	}

	@TokenCheck
	@PostMapping("/worktypes")
	public Result<?> worktypes(@Validated @RequestBody CommonParam param, BindingResult bindingResult,
			HttpServletRequest request) {
		List<KeyValueResult> kvs = new ArrayList<>();
		if (bindingResult.hasErrors()) {
			logger.error(bindingResult.getFieldError().getDefaultMessage());
			return ResultGenerator.genSuccessResult(kvs);
		}

		UnitProject project = unitProjectService.findBy("uuid", param.getPid());
		if (project == null) {
			logger.error("项目可能已被删除");
			return ResultGenerator.genSuccessResult(kvs);
		}

		List<UnitWorkType> uwts = unitWorkTypeService.selectAllWorktype(project.getUuid());
		if (uwts != null && uwts.size() > 0) {
			KeyValueResult kv = null;
			for (UnitWorkType wt : uwts) {
				kv = new KeyValueResult();
				kv.setKey(wt.getName());
				kv.setValue(wt.getId().toString());
				kvs.add(kv);
			}
		}
		return ResultGenerator.genSuccessResult(kvs);
	}

	@TokenCheck
	@PostMapping("/groups")
	public Result<?> groups(@Validated @RequestBody GroupParam param, BindingResult bindingResult,
			HttpServletRequest request) {
		List<KeyValueResult> kvs = new ArrayList<>();
		if (bindingResult.hasErrors()) {
			logger.error(bindingResult.getFieldError().getDefaultMessage());
			return ResultGenerator.genSuccessResult(kvs);
		}

		UnitProject project = unitProjectService.findBy("uuid", param.getPid());
		if (project == null) {
			logger.error("项目可能已被删除");
			return ResultGenerator.genSuccessResult(kvs);
		}

		Condition condition = new Condition(UnitGroup.class);
		condition.createCriteria().andEqualTo("companyuuid", param.getCompanyId())
				.andEqualTo("prouuid", project.getUuid()).andEqualTo("state", 0);
		List<UnitGroup> ugs = unitGroupService.findByCondition(condition);
		if (ugs != null && ugs.size() > 0) {
			KeyValueResult kv = null;
			for (UnitGroup ug : ugs) {
				kv = new KeyValueResult();
				kv.setKey(ug.getName());
				kv.setValue(ug.getUuid());
				kvs.add(kv);
			}
		}
		return ResultGenerator.genSuccessResult(kvs);
	}

	@TokenCheck
	@PostMapping("/departments")
	public Result<?> departments(@Validated @RequestBody CommonParam param, BindingResult bindingResult,
			HttpServletRequest request) {
		List<KeyValueResult> kvs = new ArrayList<>();
		if (bindingResult.hasErrors()) {
			logger.error(bindingResult.getFieldError().getDefaultMessage());
			return ResultGenerator.genSuccessResult(kvs);
		}

		UnitProject project = unitProjectService.findBy("uuid", param.getPid());
		if (project == null) {
			logger.error("项目可能已被删除");
			return ResultGenerator.genSuccessResult(kvs);
		}

		Condition condition = new Condition(UnitDepartment.class);
		condition.createCriteria().andEqualTo("deptuuid", project.getUuid()).andEqualTo("state", 0);
		List<UnitDepartment> uds = unitDepartmentService.findByCondition(condition);
		if (uds != null && uds.size() > 0) {
			KeyValueResult kv = null;
			for (UnitDepartment ud : uds) {
				kv = new KeyValueResult();
				kv.setKey(ud.getDeptname());
				kv.setValue(ud.getUuid());
				kvs.add(kv);
			}
		}
		return ResultGenerator.genSuccessResult(kvs);
	}

	@TokenCheck
	@PostMapping("/posts")
	public Result<?> posts(@Validated @RequestBody PostParam param, BindingResult bindingResult,
			HttpServletRequest request) {
		List<KeyValueResult> kvs = new ArrayList<>();
		if (bindingResult.hasErrors()) {
			logger.error(bindingResult.getFieldError().getDefaultMessage());
			return ResultGenerator.genSuccessResult(kvs);
		}

		UnitProject project = unitProjectService.findBy("uuid", param.getPid());
		if (project == null) {
			logger.error("项目可能已被删除");
			return ResultGenerator.genSuccessResult(kvs);
		}

		Condition condition = new Condition(UnitPost.class);
		condition.createCriteria().andEqualTo("deptuuid", param.getDeptUuid()).andEqualTo("prouuid", project.getUuid())
				.andEqualTo("state", 0);
		List<UnitPost> ups = unitPostService.findByCondition(condition);
		if (ups != null && ups.size() > 0) {
			KeyValueResult kv = null;
			for (UnitPost up : ups) {
				kv = new KeyValueResult();
				kv.setKey(up.getName());
				kv.setValue(up.getUuid());
				kvs.add(kv);
			}
		}
		return ResultGenerator.genSuccessResult(kvs);
	}
}
