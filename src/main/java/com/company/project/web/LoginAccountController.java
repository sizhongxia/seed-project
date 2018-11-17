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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.project.annotation.TokenCheck;
import com.company.project.configurer.QiniuConstant;
import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.model.UserLoginAccount;
import com.company.project.model.om.LoginAccountModel;
import com.company.project.model.param.LoginAccountSearchParam;
import com.company.project.model.returns.Pagination;
import com.company.project.service.UserLoginAccountService;
import com.company.project.unit.Md5Util;
import com.company.project.unit.UuidUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xiaoleilu.hutool.date.DateUtil;
import com.xiaoleilu.hutool.util.RandomUtil;

import tk.mybatis.mapper.entity.Condition;
import tk.mybatis.mapper.entity.Example.Criteria;

@RestController
@RequestMapping("/loginaccount")
public class LoginAccountController {
	private Logger logger = LoggerFactory.getLogger(LoginAccountController.class.getName());

	@Resource
	private UserLoginAccountService userLoginAccountService;
	@Autowired
	private QiniuConstant qiniuConstant;

	@TokenCheck
	@PostMapping("/data")
	public Result<?> data(@RequestBody LoginAccountSearchParam param) {
		logger.info("loginAccount search param:{}", param.toString());

		Condition condition = new Condition(UserLoginAccount.class);
		Criteria criteria = condition.createCriteria();

		String name = param.getName();
		if (StringUtils.isNotBlank(name)) {
			criteria.andLike("name", String.format("%%%s%%", name.trim()));
		}
		String userName = param.getUserName();
		if (StringUtils.isNotBlank(userName)) {
			criteria.andLike("username", String.format("%%%s%%", userName.trim()));
		}
		String phone = param.getPhone();
		if (StringUtils.isNotBlank(phone)) {
			criteria.andLike("phone", String.format("%%%s%%", phone.trim()));
		}
		String state = param.getState();
		if (NumberUtils.isDigits(state)) {
			criteria.andEqualTo("state", Integer.parseInt(state.trim()));
		}

		Integer page = param.getPage();
		if (page == null || page.intValue() < 1) {
			page = 1;
		}
		Integer size = param.getSize();
		if (size == null || size.intValue() < 1) {
			size = 10;
		}
		condition.setOrderByClause("state asc, id asc");
		PageHelper.startPage(page, size);
		Page<UserLoginAccount> _list = (Page<UserLoginAccount>) userLoginAccountService.findByCondition(condition);
		List<UserLoginAccount> result = _list.getResult();
		List<Map<String, Object>> list = new ArrayList<>();
		if (result != null && result.size() > 0) {
			Map<String, Object> item = null;
			for (UserLoginAccount i : result) {
				item = new HashMap<>();
				item.put("key", i.getUuid());
				item.put("idnumber", i.getIdnumber());
				item.put("username", i.getUsername());
				item.put("phone", i.getPhone());
				item.put("name", i.getName());
				String photo = StringUtils.isBlank(i.getPhoto())
						? "https://gw.alipayobjects.com/zos/rmsportal/WdGqmHpayyMjiEhcKoVE.png"
						: String.format("%s%s%s", qiniuConstant.getPath(), i.getPhoto(), "-200x200");
				item.put("photo", photo);
				item.put("sex", i.getSex().toString());
				item.put("state", i.getState().toString());
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
	public Result<?> getById(@RequestBody LoginAccountModel model) {

		if (StringUtils.isBlank(model.getUuid())) {
			return ResultGenerator.genFailResult("无效的表单");
		}
		UserLoginAccount loginAccount = userLoginAccountService.findBy("uuid", model.getUuid());
		if (loginAccount == null) {
			return ResultGenerator.genFailResult("无效的表单");
		}
		Map<String, Object> item = new HashMap<>();
		item.put("id", model.getUuid());
		item.put("idnumber", loginAccount.getIdnumber());
		item.put("username", loginAccount.getUsername());
		item.put("phone", loginAccount.getPhone());
		item.put("name", loginAccount.getName());
		String photo = StringUtils.isBlank(loginAccount.getPhoto())
				? "https://gw.alipayobjects.com/zos/rmsportal/WdGqmHpayyMjiEhcKoVE.png"
				: String.format("%s%s%s", qiniuConstant.getPath(), loginAccount.getPhoto(), "-200x200");
		item.put("photo", photo);
		item.put("sex", loginAccount.getSex().toString());
		item.put("state", loginAccount.getState().toString());
		item.put("addtime", DateUtil.format(new Date(loginAccount.getAddtime()), "yyyy-MM-dd HH:mm"));
		item.put("updatetime", DateUtil.format(new Date(loginAccount.getUpdatetime()), "yyyy-MM-dd HH:mm"));
		return ResultGenerator.genSuccessResult(item);
	}

	@TokenCheck
	@PostMapping("/changeState")
	public Result<?> changeState(@RequestBody LoginAccountModel model) {
		if (StringUtils.isBlank(model.getUuid())) {
			return ResultGenerator.genFailResult("无效的表单01");
		}
		if (!NumberUtils.isDigits(model.getState())) {
			return ResultGenerator.genFailResult("无效的表单02");
		}
		UserLoginAccount loginAccount = userLoginAccountService.findBy("uuid", model.getUuid());
		if (loginAccount == null) {
			return ResultGenerator.genFailResult("无效的表单03");
		}
		loginAccount.setState(Integer.parseInt(model.getState().trim()));
		loginAccount.setUpdatetime(System.currentTimeMillis());
		userLoginAccountService.update(loginAccount);
		return ResultGenerator.genSuccessResult();
	}

	@TokenCheck
	@PostMapping("/save")
	public Result<?> save(@Validated @RequestBody LoginAccountModel model, BindingResult bindingResult,
			HttpServletRequest request) {
		if (bindingResult.hasErrors()) {
			return ResultGenerator.genFailResult(bindingResult.getFieldError().getDefaultMessage());
		}
		UserLoginAccount loginAccount = userLoginAccountService.findBy("phone", model.getPhone());
		if (loginAccount != null) {
			return ResultGenerator.genFailResult("当前手机号已存在");
		}
		loginAccount = userLoginAccountService.findBy("username", model.getUsername());
		if (loginAccount != null) {
			return ResultGenerator.genFailResult("当前登陆名称已存在");
		}
		loginAccount = new UserLoginAccount();
		loginAccount.setUuid(UuidUtil.init());
		loginAccount.setIdnumber(model.getIdnumber());
		loginAccount.setUsername(model.getUsername());
		loginAccount.setPhone(model.getPhone());
		loginAccount.setName(model.getName());
		loginAccount.setPassword("");
		loginAccount.setSex(Integer.parseInt(model.getSex().trim()));
		loginAccount.setState(0);
		loginAccount.setAddtime(System.currentTimeMillis());
		loginAccount.setUpdatetime(System.currentTimeMillis());
		userLoginAccountService.save(loginAccount);
		return ResultGenerator.genSuccessResult();
	}

	@TokenCheck
	@PostMapping("/update")
	public Result<?> update(@Validated @RequestBody LoginAccountModel model, BindingResult bindingResult,
			HttpServletRequest request) {
		if (bindingResult.hasErrors()) {
			return ResultGenerator.genFailResult(bindingResult.getFieldError().getDefaultMessage());
		}

		if (StringUtils.isBlank(model.getUuid())) {
			return ResultGenerator.genFailResult("无效的表单");
		}

		UserLoginAccount loginAccount = userLoginAccountService.findBy("uuid", model.getUuid());
		if (loginAccount == null) {
			return ResultGenerator.genFailResult("无效的表单");
		}

		UserLoginAccount _loginAccount = userLoginAccountService.findBy("phone", model.getPhone());
		if (_loginAccount != null && !_loginAccount.getUuid().equals(loginAccount.getUuid())) {
			return ResultGenerator.genFailResult("当前手机号已存在");
		}
		_loginAccount = userLoginAccountService.findBy("username", model.getUsername());
		if (_loginAccount != null && !_loginAccount.getUuid().equals(loginAccount.getUuid())) {
			return ResultGenerator.genFailResult("当前登陆名称已存在");
		}

		loginAccount.setIdnumber(model.getIdnumber());
		loginAccount.setUsername(model.getUsername());
		loginAccount.setPhone(model.getPhone());
		loginAccount.setName(model.getName());
		loginAccount.setSex(Integer.parseInt(model.getSex().trim()));
		loginAccount.setUpdatetime(System.currentTimeMillis());

		userLoginAccountService.update(loginAccount);
		return ResultGenerator.genSuccessResult();
	}

	@TokenCheck
	@PostMapping("/resetPwd")
	public Result<?> resetPwd(@RequestBody LoginAccountModel model) {
		UserLoginAccount loginAccount = userLoginAccountService.findBy("uuid", model.getUuid());
		if (loginAccount == null) {
			return ResultGenerator.genFailResult("无效的表单");
		}
		String password = RandomUtil.randomNumbers(6);
		loginAccount.setPassword(Md5Util.md5(password, loginAccount.getUsername()));
		loginAccount.setUpdatetime(System.currentTimeMillis());
		userLoginAccountService.update(loginAccount);
		return ResultGenerator.genSuccessResult(password);
	}

}
