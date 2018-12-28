package com.company.project.web.zhyz.mp;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.model.param.basic.BasicWeiXinRequestParam;
import com.company.project.unit.HttpClientUtils;

@RestController
@RequestMapping("/zhyz/miniapp/api")
public class SmartCultureLoginController {

	@RequestMapping("/login")
	public Result<?> login(HttpServletRequest request, @RequestBody BasicWeiXinRequestParam param) {
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
		return ResultGenerator.genSuccessResult(resJson);
	}

}
