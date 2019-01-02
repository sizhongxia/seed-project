package com.company.project.web.zhyz.mp;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.company.project.core.Result;
import com.company.project.core.ResultCode;
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

	private final Logger logger = LoggerFactory.getLogger(SmartCultureLoginController.class);

	// token有效时长
	public final static int TOKEN_VALID_TIME = 24 * 7;

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
		Map<String, String> obj = jscode2session(param.getCode());
		if (obj == null) {
			return ResultGenerator.genFailResult("请求微信服务失败");
		}
		String openid = obj.get("openId");
		SmartCultureUserWeixin uw = smartCultureUserWeixinService.findBy("openId", openid);
		if (uw == null) {
			return ResultGenerator.genFailResult(ResultCode.TO_LOGIN, "您暂未绑定平台账号");
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
		return ResultGenerator.genSuccessResult(result);
	}

	private Map<String, String> jscode2session(String code) {
		String resJson = null;
		try {
			resJson = HttpClientUtils.get("https://api.weixin.qq.com/sns/jscode2session?appid=" + MpConst.AppID
					+ "&secret=" + MpConst.AppSecret + "&js_code=" + code + "&grant_type=authorization_code");
		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
		if (resJson == null) {
			logger.error("请求微信服务失败01");
			return null;
		}
		// {"session_key":"flTLgQMNs8FmRvNu0rORMA==","openid":"ofQqQ4hfoeuyU9kdjVbVuev61JRQ"}
		JSONObject jData = JSONObject.parseObject(resJson);
		if (jData == null) {
			logger.error("请求微信服务失败02");
			return null;
		}
		Map<String, String> map = new HashMap<>();
		map.put("sessionKey", jData.getString("session_key"));
		map.put("openId", jData.getString("openid"));
		return map;
	}

	@RequestMapping("/auth/bind")
	public Result<?> authWxBind(HttpServletRequest request, @RequestBody BasicWeiXinRequestParam param) {
		if (StringUtils.isBlank(param.getUsername())) {
			return ResultGenerator.genFailResult("请输入平台账号");
		}
		if (StringUtils.isBlank(param.getPassword())) {
			return ResultGenerator.genFailResult("请输入账号密码");
		}
		if (StringUtils.isBlank(param.getCode())) {
			return ResultGenerator.genFailResult("无效的微信'code'");
		}
		SmartCultureUser user = smartCultureUserService.findBy("userName", param.getUsername());
		if (user == null) {
			user = smartCultureUserService.findBy("phoneNo", param.getUsername());
			if (user == null) {
				return ResultGenerator.genFailResult("无效的平台账号");
			}
		}
		if (user.getAccountState().intValue() != 0) {
			return ResultGenerator.genFailResult("当前账户已被禁止登陆");
		}
		String password = Md5Util.md5(param.getPassword(), user.getUserName());
		if (!user.getPassword().equals(password)) {
			return ResultGenerator.genFailResult("账号密码不匹配");
		}
		Map<String, String> obj = jscode2session(param.getCode());
		if (obj == null) {
			return ResultGenerator.genFailResult("请求微信服务失败");
		}
		String openId = obj.get("openId");
		String sessionKey = obj.get("sessionKey");
		SmartCultureUserWeixin uw = smartCultureUserWeixinService.findBy("openId", openId);
		if (uw != null && !uw.getUserId().equals(user.getUserId())) {
			return ResultGenerator.genFailResult("当前微信号已绑定其他账号");
		}
		Date now = new Date();
		if (uw == null) {
			uw = new SmartCultureUserWeixin();
			uw.setWxAppId(MpConst.AppID);
			uw.setOpenId(openId);
			uw.setUserId(user.getUserId());
			uw.setUnionId("");
			uw.setCreateAt(now);
		}
		uw.setSessionKey(sessionKey);
		uw.setAvatarUrl(param.getAvatarUrl() == null ? "" : param.getAvatarUrl());
		uw.setCity(param.getCity() == null ? "" : param.getCity());
		uw.setCountry(param.getCountry() == null ? "" : param.getCountry());
		uw.setGender(NumberUtils.isParsable(param.getGender()) ? Integer.parseInt(param.getGender()) : 0);
		uw.setNickeName(filterEmoji(param.getNickName()));
		uw.setProvince(param.getProvince() == null ? "" : param.getProvince());
		uw.setUpdateAt(now);
		if (uw.getId() != null && uw.getId().longValue() > 0) {
			smartCultureUserWeixinService.update(uw);
		} else {
			smartCultureUserWeixinService.save(uw);
		}
		return ResultGenerator.genSuccessResult("绑定成功");
	}

	public String filterEmoji(String source) {
		if (source == null) {
			return "";
		}
		Pattern emoji = Pattern.compile("[\ud83c\udc00-\ud83c\udfff]|[\ud83d\udc00-\ud83d\udfff]|[\u2600-\u27ff]",
				Pattern.UNICODE_CASE | Pattern.CASE_INSENSITIVE);
		Matcher emojiMatcher = emoji.matcher(source);
		if (emojiMatcher.find()) {
			source = emojiMatcher.replaceAll("*");
			logger.info(String.format("emoji:%s => new:%s", emojiMatcher, source));
			return source;
		}
		return source;
	}
}
