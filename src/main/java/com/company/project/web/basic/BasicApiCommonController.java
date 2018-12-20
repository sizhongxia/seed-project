package com.company.project.web.basic;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.model.SmartCultureUser;
import com.company.project.model.SmartCultureUserToken;
import com.company.project.model.param.basic.BasicLoginParam;
import com.company.project.service.SmartCultureUserService;
import com.company.project.service.SmartCultureUserTokenService;
import com.company.project.unit.IdUtils;
import com.company.project.unit.Md5Util;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateUtil;
import tk.mybatis.mapper.entity.Condition;

@RestController
@RequestMapping("/basic/api/")
public class BasicApiCommonController {
	final Logger logger = LoggerFactory.getLogger(BasicApiCommonController.class);

	// token有效时长
	final int TOKEN_VALID_TIME = 2;

	@Resource
	SmartCultureUserService smartCultureUserService;
	@Resource
	SmartCultureUserTokenService smartCultureUserTokenService;

	// 登陆
	@PostMapping("/login")
	public Result<?> login(@RequestBody BasicLoginParam param) {
		if (StringUtils.isBlank(param.getUserName())) {
			return ResultGenerator.genFailResult("E5001");
		}
		if (StringUtils.isBlank(param.getPassword())) {
			return ResultGenerator.genFailResult("E5002");
		}
		SmartCultureUser user = smartCultureUserService.findBy("userName", param.getUserName());
		if (user == null) {
			return ResultGenerator.genFailResult("E5003");
		}
		String password = Md5Util.md5(param.getPassword(), user.getUserName());
		if (!user.getPassword().equals(password)) {
			return ResultGenerator.genFailResult("E5004");
		}
		Date now = new Date();
		Condition condition = new Condition(SmartCultureUserToken.class);
		condition.createCriteria().andEqualTo("userId", user.getUserId()).andEqualTo("isForbidden", 0)
				.andGreaterThan("overdueAt", now);
		List<SmartCultureUserToken> userTokens = smartCultureUserTokenService.findByCondition(condition);
		SmartCultureUserToken userToken = null;
		if (userTokens == null || userTokens.isEmpty()) {
			userToken = new SmartCultureUserToken();
			userToken.setUserId(user.getUserId());
			userToken.setToken(IdUtils.initUuid() + "," + System.currentTimeMillis());
			userToken.setCreateAt(now);
			userToken.setLastVisitAt(now);
			userToken.setOverdueAt(DateUtil.offset(now, DateField.HOUR, TOKEN_VALID_TIME));
			userToken.setIsForbidden(0);
			smartCultureUserTokenService.save(userToken);
		} else {
			userToken = userTokens.get(0);
			userToken.setLastVisitAt(now);
			userToken.setOverdueAt(DateUtil.offset(now, DateField.HOUR, TOKEN_VALID_TIME));
			smartCultureUserTokenService.update(userToken);
		}
		Map<String, Object> result = new HashMap<>();
		result.put("token", userToken.getToken());
		result.put("avator", user.getUserAvator());
		result.put("userName", user.getUserName());
		result.put("access", "admin");
		return ResultGenerator.genSuccessResult(result);
	}

	// @SmartCultureTokenCheck
	// @PostMapping("/user/info")
	// public Result<?> userInfo(HttpServletRequest request, @RequestBody
	// BasicCommonParam param) {
	// Map<String, Object> result = new HashMap<>();
	// result.put("avator", "http://static.yeetong.cn/defalut-avator.png-yeetong");
	// result.put("userName", "sizhongxia");
	// result.put("access", "admin");
	// return ResultGenerator.genSuccessResult(result);
	// }

	// public static void main(String[] args) {
	// System.out.println(Md5Util.md5("123456", "sizhongxia"));
	// // 5c1b4f857ba94f15347cc6e6
	// // 5c1b4f8b7ba96cf92a2fe7ba
	// }

}
