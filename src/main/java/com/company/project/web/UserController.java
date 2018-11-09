package com.company.project.web;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.company.project.configurer.Audience;
import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.model.User;
import com.company.project.model.param.LoginParam;
import com.company.project.service.UserService;
import com.company.project.unit.AesUtil;
import com.company.project.unit.IdWorker;
import com.company.project.unit.JwtHelper;
import com.company.project.unit.Md5Util;
import com.company.project.unit.SystemLocal;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * Created by SiZhongXia on 2018/11/06.
 */
@RestController
@RequestMapping("/user")
public class UserController {

	private Logger logger = LoggerFactory.getLogger(UserController.class.getName());

	@Resource
	private UserService userService;
	@Resource(name = "idWorker")
	private IdWorker idWorker;
	@Resource(name = "systemLocal")
	private SystemLocal systemLocal;
	@Autowired
	private MessageSource messageSource;
	@Autowired
	private Audience audience;

	@PostMapping("/login")
	public Result<?> login(@RequestBody LoginParam param) {
		logger.info("Login param:{}", param.toString());
		if (StringUtils.isBlank(param.getLoginName())) {
			return ResultGenerator.genFailResult(messageSource
					.getMessage("UserController.login.param.loginName.NoInput", null, systemLocal.getLocale()));
		}
		if (StringUtils.isBlank(param.getLoginPwd())) {
			return ResultGenerator.genFailResult(messageSource.getMessage("UserController.login.param.loginPwd.NoInput",
					null, systemLocal.getLocale()));
		}
		User user = userService.findBy("loginName", param.getLoginName());
		if (user == null) {
			return ResultGenerator.genFailResult(String
					.format(messageSource.getMessage("UserController.login.Error", null, systemLocal.getLocale())));
		}
		if (!Md5Util.md5(param.getLoginPwd(), param.getLoginName()).equals(user.getLoginPwd())) {
			return ResultGenerator.genFailResult(String
					.format(messageSource.getMessage("UserController.login.Error", null, systemLocal.getLocale())));
		}
		Map<String, String> data = new HashMap<>();
		data.put("loginName", user.getLoginName());
		// 默认角色
		data.put("currentAuthority", "admin");

		String jwtToken = "";
		try {
			jwtToken = JwtHelper.createJWT(user.getLoginName(), AesUtil.encrypt(user.getId().toString()), "admin",
					audience.getClientId(), audience.getName(), audience.getExpiresSecond() * 1000,
					audience.getBase64Secret());
		} catch (Exception e) {
			return ResultGenerator.genFailResult(String
					.format(messageSource.getMessage("System.Exception", null, systemLocal.getLocale())));
		}

		data.put("token", String.format("yeetong%s", jwtToken));

		return ResultGenerator.genSuccessResult(data);
	}

	@PostMapping("/register")
	public Result<?> register(@Validated User user, BindingResult bindingResult, HttpServletRequest request) {

		logger.info(request.getAttribute("userId").toString());

		if (bindingResult.hasErrors()) {
			return ResultGenerator.genFailResult(bindingResult.getFieldError().getDefaultMessage());
		}

		User _user = userService.findBy("loginName", user.getLoginName());
		if (_user != null) {
			return ResultGenerator.genFailResult(messageSource
					.getMessage("UserController.register.field.loginName.Exist", null, systemLocal.getLocale()));
		}
		user.setId(idWorker.nextId());
		user.setLoginPwd(Md5Util.md5(user.getLoginPwd(), user.getLoginName()));
		Date now = new Date();
		user.setCreateAt(now);
		user.setUpdateAt(now);
		userService.save(user);
		return ResultGenerator.genSuccessResult();
	}

	@PostMapping("/delete")
	public Result<?> delete(@RequestParam Long id) {
		userService.deleteById(id);
		return ResultGenerator.genSuccessResult();
	}

	@PostMapping("/update")
	public Result<?> update(User user) {
		userService.update(user);
		return ResultGenerator.genSuccessResult();
	}

	@PostMapping("/detail")
	public Result<?> detail(@RequestParam Long id) {
		System.out.println(idWorker.nextId());
		User user = userService.findById(id);
		return ResultGenerator.genSuccessResult(user);
	}

	@PostMapping("/list")
	public Result<?> list(@RequestParam(defaultValue = "0") Integer page,
			@RequestParam(defaultValue = "0") Integer size) {
		PageHelper.startPage(page, size);
		List<User> list = userService.findAll();
		PageInfo<User> pageInfo = new PageInfo<User>(list);
		return ResultGenerator.genSuccessResult(pageInfo);
	}
}
