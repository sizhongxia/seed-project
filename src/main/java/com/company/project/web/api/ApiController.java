package com.company.project.web.api;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.project.annotation.TokenCheck;
import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.model.param.api.UserLoginParam;
import com.company.project.service.UserLoginAccountService;
import com.xiaoleilu.hutool.date.DateUtil;

@RestController
@RequestMapping("/apiv1/")
public class ApiController {
	
	@Resource
	private UserLoginAccountService userLoginAccountService;

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
		return ResultGenerator.genSuccessResult(param.getUsername() + "@" + param.getPassword());
	}

	@TokenCheck
	@PostMapping("/user/menus")
	public Result<?> userMenus() {
		Map<String, Object> data = new HashMap<>();
		data.put("time", DateUtil.format(new Date(), "yyyyMMddHHmmss"));
		return ResultGenerator.genSuccessResult(data);
	}

}
