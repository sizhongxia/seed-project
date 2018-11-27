package com.company.project.web.apiv1;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
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
import com.company.project.model.SysMenu;
import com.company.project.model.UnitCompany;
import com.company.project.model.UnitProject;
import com.company.project.model.UserIdentity;
import com.company.project.model.UserLoginAccount;
import com.company.project.model.param.apiv1.CommonParam;
import com.company.project.model.param.apiv1.UserLoginParam;
import com.company.project.model.returns.apiv1.KeyValueResult;
import com.company.project.model.returns.apiv1.MenuResult;
import com.company.project.model.returns.apiv1.ProjectResult;
import com.company.project.service.SysAreaService;
import com.company.project.service.SysDictionaryService;
import com.company.project.service.SysMenuService;
import com.company.project.service.UnitCompanyService;
import com.company.project.service.UnitLaborsubcontractorService;
import com.company.project.service.UnitProjectService;
import com.company.project.service.UserIdentityService;
import com.company.project.service.UserLoginAccountService;
import com.company.project.unit.LongDataFormatUtil;
import com.company.project.unit.Md5Util;

import cn.hutool.core.date.DateUtil;
import tk.mybatis.mapper.entity.Condition;

@RestController
@RequestMapping("/apiv1/")
public class IndexController {

	@Resource
	private UserLoginAccountService userLoginAccountService;
	@Resource
	private UserIdentityService userIdentityService;
	@Resource
	private UnitProjectService unitProjectService;
	@Resource
	private SysMenuService sysMenuService;
	@Resource
	private SysDictionaryService sysDictionaryService;
	@Resource
	private SysAreaService sysAreaService;
	@Resource
	private UnitLaborsubcontractorService unitLaborsubcontractorService;
	@Resource
	private UnitCompanyService unitCompanyService;

	@TokenCheck
	@PostMapping("/probe")
	public Result<?> probe() {
		Map<String, Object> data = new HashMap<>();
		data.put("time", DateUtil.format(new Date(), "yyyyMMddHHmmss"));
		return ResultGenerator.genSuccessResult(data);
	}

	@TokenCheck
	@PostMapping("/user/login")
	public Result<?> userLogin(@Validated @RequestBody UserLoginParam param, BindingResult bindingResult,
			HttpServletRequest request) {
		if (bindingResult.hasErrors()) {
			return ResultGenerator.genFailResult(bindingResult.getFieldError().getDefaultMessage());
		}
		UserLoginAccount user = userLoginAccountService.findBy("username", param.getUsername());
		if (user == null) {
			return ResultGenerator.genFailResult("用户名和密码不匹配");
		}
		if (user.getState().intValue() != 0) {
			return ResultGenerator.genFailResult("账号已被禁止使用(E" + user.getState().intValue() + ")");
		}

		if (!Md5Util.md5(param.getPassword(), user.getUsername()).equals(user.getPassword())) {
			return ResultGenerator.genFailResult("用户名和密码不匹配");
		}

		Condition condition = new Condition(UserIdentity.class);
		condition.createCriteria().andEqualTo("useruuid", user.getUuid()).andEqualTo("type", 1)
				.andEqualTo("isloging", 0).andEqualTo("state", 0);
		List<UserIdentity> uis = userIdentityService.findByCondition(condition);

		if (uis == null || uis.isEmpty()) {
			return ResultGenerator.genFailResult("身份错误，登陆失败");
		}

		UserIdentity ui = uis.get(0);
		if (StringUtils.isBlank(ui.getDeptuuid())) {
			return ResultGenerator.genFailResult("身份错误，登陆失败");
		}

		UnitProject unitProject = unitProjectService.findBy("uuid", ui.getDeptuuid());
		if (unitProject.getState().intValue() != 0) {
			return ResultGenerator.genFailResult("工地信息已删除，当前账号已失效");
		}

		Map<String, String> data = new HashMap<>();
		data.put("uid", user.getUuid());
		data.put("uname", user.getName());
		data.put("pid", unitProject.getUuid());
		return ResultGenerator.genSuccessResult(data);
	}

	@TokenCheck
	@PostMapping("/user/menus")
	public Result<?> userMenus(@Validated @RequestBody CommonParam param, BindingResult bindingResult,
			HttpServletRequest request) {

		if (bindingResult.hasErrors()) {
			return ResultGenerator.genFailResult(bindingResult.getFieldError().getDefaultMessage());
		}

		Condition condition = new Condition(UserIdentity.class);
		condition.createCriteria().andEqualTo("useruuid", param.getUid()).andEqualTo("deptuuid", param.getPid())
				.andEqualTo("type", 1).andEqualTo("isloging", 0).andEqualTo("state", 0).andEqualTo("issuper", 1);
		List<UserIdentity> uis = userIdentityService.findByCondition(condition);

		List<MenuResult> menus = new ArrayList<>();
		if (uis == null || uis.isEmpty()) {
			return ResultGenerator.genSuccessResult(menus);
		}

		Condition menuCondition = new Condition(SysMenu.class);
		menuCondition.createCriteria().andEqualTo("type", 1).andEqualTo("state", 0).andIsNull("parentid");
		menuCondition.orderBy("sortnum asc");
		List<SysMenu> _menus = sysMenuService.findByCondition(menuCondition);
		if (_menus != null && _menus.size() > 0) {
			MenuResult mr = null;
			for (SysMenu sm : _menus) {
				mr = new MenuResult();
				BeanUtils.copyProperties(sm, mr);
				menus.add(mr);
			}
		}

		return ResultGenerator.genSuccessResult(menus);
	}

	@TokenCheck
	@PostMapping("/project/detail")
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
	@PostMapping("/nations")
	public Result<?> nations(HttpServletRequest request) {
		String[] ns = new String[] { "汉族", "壮族", "满族", "回族", "苗族", "维吾尔族", "土家族", "彝族", "蒙古族", "藏族", "布依族", "侗族", "瑶族",
				"朝鲜族", "白族", "哈尼族", "哈萨克族", "黎族", "傣族", "畲族", "傈僳族", "仡佬族", "东乡族", "高山族", "拉祜族", "水族", "佤族", "纳西族",
				"羌族", "土族", "仫佬族", "锡伯族", "柯尔克孜族", "达斡尔族", "景颇族", "毛南族", "撒拉族", "布朗族", "塔吉克族", "阿昌族", "普米族", "鄂温克族",
				"怒族", "京族", "基诺族", "德昂族", "保安族", "俄罗斯族", "裕固族", "乌孜别克族", "门巴族", "鄂伦春族", "独龙族", "塔塔尔族", "赫哲族", "珞巴族" };
		List<KeyValueResult> kvs = new ArrayList<>();
		KeyValueResult kv = null;
		for (String n : ns) {
			kv = new KeyValueResult();
			kv.setKey(n);
			kv.setValue(n);
			kvs.add(kv);
		}
		return ResultGenerator.genSuccessResult(kvs);
	}
}
