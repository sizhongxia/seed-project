package com.company.project.web;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.model.User;
import com.company.project.service.UserService;
import com.company.project.unit.IdWorker;
import com.company.project.unit.SystemLocal;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * Created by SiZhongXia on 2018/11/06.
 */
@RestController
@RequestMapping("/user")
public class UserController {
	@Resource
	private UserService userService;

	@Resource(name = "idWorker")
	private IdWorker idWorker;
	@Resource(name = "systemLocal")
	private SystemLocal systemLocal;
	@Autowired
	private MessageSource messageSource;

	@PostMapping("/getByLoginName")
	public Result<?> getByLoginName(@RequestParam String loginName) {
		if (StringUtils.isBlank(loginName)) {
			return ResultGenerator.genFailResult(messageSource.getMessage(
					"UserController.getByLoginName.param.loginName.NoInput", null, systemLocal.getLocale()));
		}
		User user = userService.findBy("loginName", loginName);
		if (user == null) {
			return ResultGenerator.genFailResult(
					String.format(messageSource.getMessage("UserController.getByLoginName.param.loginName.Error",
							new String[] { loginName }, systemLocal.getLocale())));
		}
		return ResultGenerator.genSuccessResult(user);
	}

	@PostMapping("/register")
	public Result<?> register(@Validated User user, BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			return ResultGenerator.genFailResult(bindingResult.getFieldError().getDefaultMessage());
		}

		User _user = userService.findBy("loginName", user.getLoginName());
		if (_user != null) {
			return ResultGenerator.genFailResult(messageSource.getMessage(
					"UserController.register.field.loginName.Exist", null, systemLocal.getLocale()));
		}

		user.setId(idWorker.nextId());
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
