package com.company.project.Interceptor;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.company.project.annotation.SmartCultureTokenCheck;
import com.company.project.model.SmartCultureUserToken;
import com.company.project.service.SmartCultureUserTokenService;
import com.company.project.web.basic.BasicApiCommonController;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateUtil;
import tk.mybatis.mapper.entity.Condition;

public class SmartCultureTokenCheckInterceptor extends HandlerInterceptorAdapter {

	@Resource
	SmartCultureUserTokenService smartCultureUserTokenService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		if (handler.getClass().isAssignableFrom(HandlerMethod.class)) {
			SmartCultureTokenCheck tokenCheck = (SmartCultureTokenCheck) ((HandlerMethod) handler)
					.getMethodAnnotation(SmartCultureTokenCheck.class);
			if (tokenCheck != null) {
				// 等到请求头信息authorization信息
				final String token = request.getHeader("token");
				if (StringUtils.isBlank(token)) {
					return false;
				}
				Date now = new Date();
				Condition condition = new Condition(SmartCultureUserToken.class);
				condition.createCriteria().andEqualTo("token", token).andEqualTo("isForbidden", 0)
						.andGreaterThan("overdueAt", now);
				List<SmartCultureUserToken> tokens = smartCultureUserTokenService.findByCondition(condition);
				if (tokens == null || tokens.isEmpty()) {
					return false;
				}
				
				SmartCultureUserToken userToken = tokens.get(0);
				userToken.setLastVisitAt(now);
				userToken.setOverdueAt(DateUtil.offset(now, DateField.HOUR, BasicApiCommonController.TOKEN_VALID_TIME));
				smartCultureUserTokenService.update(userToken);

				request.setAttribute("userId", userToken.getUserId());
			}
		}

		return super.preHandle(request, response, handler);
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		super.postHandle(request, response, handler, modelAndView);
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		super.afterCompletion(request, response, handler, ex);
	}
}
