package com.company.project.Interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.company.project.annotation.SmartCultureTokenCheck;
import com.company.project.annotation.TokenCheck;

public class SmartCultureTokenCheckInterceptor extends HandlerInterceptorAdapter {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		if (handler.getClass().isAssignableFrom(HandlerMethod.class)) {
			SmartCultureTokenCheck tokenCheck = (SmartCultureTokenCheck) ((HandlerMethod) handler)
					.getMethodAnnotation(TokenCheck.class);
			if (tokenCheck != null) {
				// 等到请求头信息authorization信息
				final String authHeader = request.getHeader("token");
				System.out.println(authHeader);
				if (StringUtils.isBlank(authHeader)) {
				}
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
