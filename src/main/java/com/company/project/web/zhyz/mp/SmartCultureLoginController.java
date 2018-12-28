package com.company.project.web.zhyz.mp;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.model.SmartCultureUser;
import com.company.project.model.SmartCultureUserToken;
import com.company.project.model.SmartCultureUserWeixin;
import com.company.project.model.param.basic.BasicWeiXinRequestParam;
import com.company.project.service.SmartCultureUserIdentityService;
import com.company.project.service.SmartCultureUserService;
import com.company.project.service.SmartCultureUserTokenService;
import com.company.project.service.SmartCultureUserWeixinService;
import com.company.project.unit.HttpClientUtils;
import com.company.project.unit.IdUtils;
import com.company.project.unit.Md5Util;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateUtil;
import tk.mybatis.mapper.entity.Condition;

@RestController
@RequestMapping("/zhyz/miniapp/api")
public class SmartCultureLoginController {

	@Resource
	SmartCultureUserWeixinService smartCultureUserWeixinService;
	@Resource
	SmartCultureUserService smartCultureUserService;
	@Resource
	SmartCultureUserIdentityService smartCultureUserIdentityService;
	@Resource
	SmartCultureUserTokenService smartCultureUserTokenService;

	@RequestMapping("/auth/wxlogin")
	public Result<?> authWxLogin(HttpServletRequest request, @RequestBody BasicWeiXinRequestParam param) {
		if (StringUtils.isBlank(param.getCode())) {
			return ResultGenerator.genFailResult("无效的微信'code'");
		}
		String resJson = null;
		try {
			resJson = HttpClientUtils.get("https://api.weixin.qq.com/sns/jscode2session?appid=" + MpConst.AppID
					+ "&secret=" + MpConst.AppSecret + "&js_code=" + param.getCode().trim()
					+ "&grant_type=authorization_code");
		} catch (Exception e) {
			return ResultGenerator.genFailResult("请求微信服务失败01");
		}
		if (resJson == null) {
			return ResultGenerator.genFailResult("请求微信服务失败02");
		}
		// {code: 200, count: 0, data:
		// "{"session_key":"flTLgQMNs8FmRvNu0rORMA==","openid":"ofQqQ4hfoeuyU9kdjVbVuev61JRQ"}",
		// message: "SUCCESS"}
		JSONObject json = JSONObject.parseObject(resJson);
		if (json.getIntValue("code") != 200) {
			return ResultGenerator.genFailResult(json.getString("message"));
		}
		JSONObject jData = json.getJSONObject("data");
		// String sessionKey = jData.getString("session_key");
		String openid = jData.getString("openid");

		SmartCultureUserWeixin uw = smartCultureUserWeixinService.findBy("openId", openid);
		if (uw == null) {
			return ResultGenerator.genFailResult("您暂未绑定平台账号");
		}

		SmartCultureUser user = smartCultureUserService.findBy("userId", uw.getUserId());
		if (user == null || user.getAccountState().intValue() != 0) {
			return ResultGenerator.genFailResult("当前账户已被禁止登陆");
		}

		Date now = new Date();
		Condition condition = new Condition(SmartCultureUserToken.class);
		condition.createCriteria().andEqualTo("userId", user.getUserId()).andEqualTo("sourceType", "MP")
				.andEqualTo("isForbidden", 0).andGreaterThan("overdueAt", now);
		List<SmartCultureUserToken> userTokens = smartCultureUserTokenService.findByCondition(condition);
		SmartCultureUserToken userToken = null;
		if (userTokens == null || userTokens.isEmpty()) {
			userToken = new SmartCultureUserToken();
			userToken.setUserId(user.getUserId());
			userToken.setToken(IdUtils.initUuid() + System.currentTimeMillis());
			userToken.setSourceType("MP");
			userToken.setCreateAt(now);
			userToken.setLastVisitAt(now);
			userToken.setOverdueAt(DateUtil.offset(now, DateField.MONTH, 1));
			userToken.setIsForbidden(0);
			smartCultureUserTokenService.save(userToken);
		} else {
			userToken = userTokens.get(0);
			userToken.setLastVisitAt(now);
			userToken.setOverdueAt(DateUtil.offset(now, DateField.MONTH, 1));
			smartCultureUserTokenService.update(userToken);
		}
		Map<String, Object> result = new HashMap<>();
		result.put("token", userToken.getToken());
		result.put("avator", user.getUserAvator());
		result.put("userName", user.getUserName());
		return ResultGenerator.genSuccessResult(result);
	}

	@RequestMapping("/auth/bind")
	public Result<?> authWxBind(HttpServletRequest request, @RequestBody BasicWeiXinRequestParam param) {
		if (StringUtils.isBlank(param.getUsername())) {
			return ResultGenerator.genFailResult("请输入用户名");
		}
		if (StringUtils.isBlank(param.getPassword())) {
			return ResultGenerator.genFailResult("请输入密码");
		}
		if (StringUtils.isBlank(param.getCode())) {
			return ResultGenerator.genFailResult("无效的微信'code'");
		}

		SmartCultureUser user = smartCultureUserService.findBy("userName", param.getUsername());
		if (user == null) {
			return ResultGenerator.genFailResult("无效的用户名称");
		}
		if (user.getAccountState().intValue() != 0) {
			return ResultGenerator.genFailResult("当前账户已被禁止登陆");
		}
		String password = Md5Util.md5(param.getPassword(), user.getUserName());
		if (!user.getPassword().equals(password)) {
			return ResultGenerator.genFailResult("账号密码不匹配");
		}

		String resJson = null;
		try {
			resJson = HttpClientUtils.get("https://api.weixin.qq.com/sns/jscode2session?appid=" + MpConst.AppID
					+ "&secret=" + MpConst.AppSecret + "&js_code=" + param.getCode().trim()
					+ "&grant_type=authorization_code");
		} catch (Exception e) {
			return ResultGenerator.genFailResult("请求微信服务失败01");
		}
		if (resJson == null) {
			return ResultGenerator.genFailResult("请求微信服务失败02");
		}
		// {code: 200, count: 0, data:
		// "{"session_key":"flTLgQMNs8FmRvNu0rORMA==","openid":"ofQqQ4hfoeuyU9kdjVbVuev61JRQ"}",
		// message: "SUCCESS"}
		JSONObject json = JSONObject.parseObject(resJson);
		if (json.getIntValue("code") != 200) {
			return ResultGenerator.genFailResult(json.getString("message"));
		}
		JSONObject jData = json.getJSONObject("data");
		String sessionKey = jData.getString("session_key");
		String openid = jData.getString("openid");

		SmartCultureUserWeixin uw = smartCultureUserWeixinService.findBy("openId", openid);
		if (uw != null) {
			if (!uw.getUserId().equals(user.getUserId())) {
				return ResultGenerator.genFailResult("当前微信号已绑定其他账号");
			}
			return ResultGenerator.genFailResult("您已成功绑定该账号");
		}
		Date now = new Date();

		uw = new SmartCultureUserWeixin();
		uw.setOpenId(openid);
		uw.setSessionKey(sessionKey);
		uw.setUnionId("");
		uw.setUserId(user.getUserId());
		uw.setWxAppId(MpConst.AppID);
		uw.setCreateAt(now);
		uw.setUpdateAt(now);
		smartCultureUserWeixinService.save(uw);

		Condition condition = new Condition(SmartCultureUserToken.class);
		condition.createCriteria().andEqualTo("userId", user.getUserId()).andEqualTo("sourceType", "MP")
				.andEqualTo("isForbidden", 0).andGreaterThan("overdueAt", now);
		List<SmartCultureUserToken> userTokens = smartCultureUserTokenService.findByCondition(condition);
		SmartCultureUserToken userToken = null;
		if (userTokens == null || userTokens.isEmpty()) {
			userToken = new SmartCultureUserToken();
			userToken.setUserId(user.getUserId());
			userToken.setToken(IdUtils.initUuid() + System.currentTimeMillis());
			userToken.setSourceType("MP");
			userToken.setCreateAt(now);
			userToken.setLastVisitAt(now);
			userToken.setOverdueAt(DateUtil.offset(now, DateField.MONTH, 1));
			userToken.setIsForbidden(0);
			smartCultureUserTokenService.save(userToken);
		} else {
			userToken = userTokens.get(0);
			userToken.setLastVisitAt(now);
			userToken.setOverdueAt(DateUtil.offset(now, DateField.MONTH, 1));
			smartCultureUserTokenService.update(userToken);
		}
		Map<String, Object> result = new HashMap<>();
		result.put("token", userToken.getToken());
		result.put("avator", user.getUserAvator());
		result.put("userName", user.getUserName());
		return ResultGenerator.genSuccessResult(result);
	}

}
