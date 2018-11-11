package com.company.project.web;

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
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.company.project.configurer.Audience;
import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.model.UserIdentity;
import com.company.project.model.UserLoginAccount;
import com.company.project.model.UserRole;
import com.company.project.model.UserTokenFlag;
import com.company.project.model.param.LoginParam;
import com.company.project.service.UserIdentityService;
import com.company.project.service.UserLoginAccountService;
import com.company.project.service.UserRoleService;
import com.company.project.service.UserTokenFlagService;
import com.company.project.unit.JwtHelper;
import com.company.project.unit.Md5Util;
import com.company.project.unit.SystemLocal;
import com.company.project.unit.UuidUtil;
import com.xiaoleilu.hutool.date.DateUtil;

import io.jsonwebtoken.Claims;
import tk.mybatis.mapper.entity.Condition;

/**
 * Created by SiZhongXia on 2018/11/06.
 */
@RestController
@RequestMapping("/user")
public class UserLoginAccountController {

	private Logger logger = LoggerFactory.getLogger(UserLoginAccountController.class.getName());

	@Resource
	private UserLoginAccountService userLoginAccountService;
	@Resource(name = "systemLocal")
	private SystemLocal systemLocal;
	@Autowired
	private Audience audience;
	@Autowired
	private UserIdentityService userIdentityService;
	@Autowired
	private UserRoleService userRoleService;
	@Autowired
	private UserTokenFlagService userTokenFlagService;

	@PostMapping("/login")
	public Result<?> login(@RequestBody LoginParam param) {
		logger.info("Login param:{}", param.toString());
		if (!NumberUtils.isDigits(param.getType())) {
			return ResultGenerator.genFailResult("无效的表单参数");
		}
		if (StringUtils.isBlank(param.getLoginName())) {
			return ResultGenerator.genFailResult("请输入登录账号");
		}
		if (StringUtils.isBlank(param.getLoginPwd())) {
			return ResultGenerator.genFailResult("请输入登录密码");
		}
		UserLoginAccount user = userLoginAccountService.findBy("username", param.getLoginName());
		if (user == null) {
			return ResultGenerator.genFailResult("登录失败，无效的账号");
		}
		if (!Md5Util.md5(param.getLoginPwd(), param.getLoginName()).equals(user.getPassword())) {
			return ResultGenerator.genFailResult("登录失败，账号密码不匹配");
		}
		Map<String, String> data = new HashMap<>();

		if (StringUtils.isNotBlank(user.getName())) {
			data.put("loginName", user.getName());
		} else {
			data.put("loginName", user.getUsername());
		}

		Condition condition = new Condition(UserIdentity.class);
		condition.createCriteria().andEqualTo("useruuid", user.getUuid()).andEqualTo("type", param.getType())
				.andEqualTo("state", 0);
		List<UserIdentity> userIdentitys = userIdentityService.findByCondition(condition);

		if (userIdentitys == null || userIdentitys.isEmpty()) {
			return ResultGenerator.genFailResult("未授权访问V1");
		}
		UserIdentity ui = userIdentitys.get(0);

		if (StringUtils.isBlank(ui.getRoleuuid())) {
			return ResultGenerator.genFailResult("未授权访问V2");
		}

		UserRole userRole = userRoleService.findBy("uuid", ui.getRoleuuid());
		if (userRole == null) {
			return ResultGenerator.genFailResult("未授权访问V3");
		}

		data.put("currentAuthority", userRole.getRolecode());

		final String flagKey = UuidUtil.init();
		String jwtToken = initJwt(user.getUsername(), user.getUuid(), userRole.getRolecode(), flagKey);

		UserTokenFlag tokenFlag = userTokenFlagService.findBy("useruuid", user.getUuid());
		if (tokenFlag == null) {
			tokenFlag = new UserTokenFlag();
			tokenFlag.setUseruuid(user.getUuid());
			tokenFlag.setFlagKey(flagKey);
			tokenFlag.setToken(jwtToken);
			tokenFlag.setCreateTime(System.currentTimeMillis());
			tokenFlag.setExpireTime(System.currentTimeMillis() + audience.getExpiresSecond() * 1000);
			userTokenFlagService.save(tokenFlag);
		} else {
			tokenFlag.setFlagKey(flagKey);
			tokenFlag.setToken(jwtToken);
			tokenFlag.setCreateTime(System.currentTimeMillis());
			tokenFlag.setExpireTime(System.currentTimeMillis() + audience.getExpiresSecond() * 1000);
			userTokenFlagService.update(tokenFlag);
		}

		data.put("token", String.format("YeeTong%s", jwtToken));

		return ResultGenerator.genSuccessResult(data);
	}

	@PostMapping("/refreshToken")
	public Result<?> refreshToken(HttpServletRequest request) {
		Map<String, String> data = new HashMap<>();
		// 等到请求头信息authorization信息
		final String authHeader = request.getHeader("token");
		if (authHeader == null || !authHeader.startsWith("YeeTong")) {
			data.put("success", "F");
			return ResultGenerator.genSuccessResult(data);
		}

		final String token = authHeader.substring(7);

		if (audience == null) {
			BeanFactory factory = WebApplicationContextUtils
					.getRequiredWebApplicationContext(request.getServletContext());
			audience = (Audience) factory.getBean("audience");
		}
		final Claims claims = JwtHelper.parseJWT(token, audience.getBase64Secret());
		if (claims == null) {
			data.put("success", "F");
			return ResultGenerator.genSuccessResult(data);
		}

		if (!"yeetong".equals(claims.getIssuer())) {
			data.put("success", "F");
			return ResultGenerator.genSuccessResult(data);
		}

		UserTokenFlag tokenFlag = userTokenFlagService.findBy("useruuid", claims.get("userid"));
		if (tokenFlag == null) {
			data.put("success", "F");
			return ResultGenerator.genSuccessResult(data);
		}

		if (!tokenFlag.getFlagKey().equals(claims.get("flag_key"))) {
			data.put("success", "F");
			return ResultGenerator.genSuccessResult(data);
		}

		if (claims.getExpiration().getTime() - System.currentTimeMillis() > 60 * 60 * 1000) {
			data.put("success", authHeader);
			data.put("expiration", DateUtil.format(claims.getExpiration(), "yyyyMMddHHmmss"));
			return ResultGenerator.genSuccessResult(data);
		}

		final String flagKey = UuidUtil.init();
		String jwtToken = initJwt(claims.get("unique_name").toString(), claims.get("userid").toString(),
				claims.get("role").toString(), flagKey);
		tokenFlag.setFlagKey(flagKey);
		tokenFlag.setToken(jwtToken);
		tokenFlag.setCreateTime(System.currentTimeMillis());
		tokenFlag.setExpireTime(System.currentTimeMillis() + audience.getExpiresSecond() * 1000);
		userTokenFlagService.update(tokenFlag);
		data.put("success", String.format("YeeTong%s", jwtToken));
		data.put("expiration", DateUtil.format(new Date(tokenFlag.getExpireTime()), "yyyyMMddHHmmss"));
		return ResultGenerator.genSuccessResult(data);
	}

	private String initJwt(String userName, String userId, String userRole, String flagKey) {
		return JwtHelper.createJWT(userName, userId, userRole, flagKey, audience.getClientId(), audience.getName(),
				audience.getExpiresSecond() * 1000, audience.getBase64Secret());
	}

}
