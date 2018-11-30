package com.company.project.web.apiv1;

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
import com.company.project.model.SysArea;
import com.company.project.model.SysDictionary;
import com.company.project.model.SysMenu;
import com.company.project.model.UnitProject;
import com.company.project.model.UserIdentity;
import com.company.project.model.UserLoginAccount;
import com.company.project.model.param.apiv1.AreaParam;
import com.company.project.model.param.apiv1.CommonParam;
import com.company.project.model.param.apiv1.DictionaryParam;
import com.company.project.model.param.apiv1.UserLoginParam;
import com.company.project.model.returns.apiv1.KeyValueResult;
import com.company.project.model.returns.apiv1.MenuResult;
import com.company.project.service.SysAreaService;
import com.company.project.service.SysDictionaryService;
import com.company.project.service.SysMenuService;
import com.company.project.service.UnitCompanyService;
import com.company.project.service.UnitLaborsubcontractorService;
import com.company.project.service.UnitProjectService;
import com.company.project.service.UserIdentityService;
import com.company.project.service.UserLoginAccountService;
import com.company.project.unit.Md5Util;

import cn.hutool.core.date.DateUtil;
import tk.mybatis.mapper.entity.Condition;

@RestController
@RequestMapping("/apiv1/")
public class IndexController {

	final Logger logger = LoggerFactory.getLogger(IndexController.class);

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
		data.put("uaccount", user.getUsername());
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
		menuCondition.setOrderByClause("sortnum asc");
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
	@PostMapping("/area")
	public Result<?> area(@Validated @RequestBody AreaParam param, BindingResult bindingResult,
			HttpServletRequest request) {
		List<KeyValueResult> kvs = new ArrayList<>();
		if (bindingResult.hasErrors()) {
			logger.error(bindingResult.getFieldError().getDefaultMessage());
			return ResultGenerator.genSuccessResult(kvs);
		}
		if (!NumberUtils.isParsable(param.getPcode())) {
			logger.error("pcode param is invalid");
			return ResultGenerator.genSuccessResult(kvs);
		}

		Condition condition = new Condition(SysArea.class);
		condition.createCriteria().andEqualTo("pcode", param.getPcode());
		List<SysArea> areas = sysAreaService.findByCondition(condition);

		if (areas != null && areas.size() > 0) {
			KeyValueResult kv = null;
			for (SysArea sd : areas) {
				kv = new KeyValueResult();
				kv.setKey(sd.getName());
				kv.setValue(sd.getCode().toString());
				kvs.add(kv);
			}
		}

		return ResultGenerator.genSuccessResult(kvs);
	}

	@TokenCheck
	@PostMapping("/dictionary")
	public Result<?> dictionary(@Validated @RequestBody DictionaryParam param, BindingResult bindingResult,
			HttpServletRequest request) {
		List<KeyValueResult> kvs = new ArrayList<>();
		if (bindingResult.hasErrors()) {
			logger.error(bindingResult.getFieldError().getDefaultMessage());
			return ResultGenerator.genSuccessResult(kvs);
		}

		Condition condition = new Condition(SysDictionary.class);
		condition.createCriteria().andEqualTo("type", param.getType());
		List<SysDictionary> dicts = sysDictionaryService.findByCondition(condition);

		if (dicts != null && dicts.size() > 0) {
			KeyValueResult kv = null;
			for (SysDictionary sd : dicts) {
				kv = new KeyValueResult();
				kv.setKey(sd.getName());
				kv.setValue(sd.getValue().toString());
				kvs.add(kv);
			}
		}

		return ResultGenerator.genSuccessResult(kvs);
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
