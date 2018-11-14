package com.company.project.configurer;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.company.project.Interceptor.TokenCheckInterceptor;
import com.company.project.core.Result;
import com.company.project.core.ResultCode;
import com.company.project.core.ServiceException;
import com.company.project.filter.JwtFilter;
import com.company.project.unit.IdWorker;
import com.company.project.unit.SystemLocal;
import com.google.common.collect.Lists;

/**
 * Spring MVC 配置
 */
@Configuration
public class WebMvcConfigurer extends WebMvcConfigurerAdapter {

	private final Logger logger = LoggerFactory.getLogger(WebMvcConfigurer.class);
	@Value("${spring.profiles.active}")
	private String env;// 当前激活的配置文件

	@Value("${IdWorker.workerId}")
	private Long workerId;
	@Value("${IdWorker.dataCenterId}")
	private Long dataCenterId;

	@Value("${System.local}")
	private String local;

	// 使用阿里 FastJson 作为JSON MessageConverter
	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		FastJsonHttpMessageConverter converter = new FastJsonHttpMessageConverter();
		FastJsonConfig config = new FastJsonConfig();
		config.setSerializerFeatures(SerializerFeature.WriteMapNullValue);// 保留空的字段
		// SerializerFeature.WriteNullStringAsEmpty,//String null -> ""
		// SerializerFeature.WriteNullNumberAsZero//Number null -> 0
		// 按需配置，更多参考FastJson文档哈

		converter.setFastJsonConfig(config);
		converter.setDefaultCharset(Charset.forName("UTF-8"));
		converter.setSupportedMediaTypes(Arrays.asList(MediaType.APPLICATION_JSON_UTF8));
		converters.add(converter);
	}

	// 统一异常处理
	@Override
	public void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> exceptionResolvers) {
		exceptionResolvers.add(new HandlerExceptionResolver() {
			public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response,
					Object handler, Exception e) {
				logger.info("request ip=" + getIpAddress(request));
				Result<?> result = new Result<>();
				if (e instanceof ServiceException) {// 业务失败的异常，如“账号或密码错误”
					result.setCode(ResultCode.FAIL).setMessage(e.getMessage());
				} else if (e instanceof NoHandlerFoundException) {
					result.setCode(ResultCode.NOT_FOUND).setMessage("接口 [" + request.getRequestURI() + "] 不存在");
				} else if (e instanceof ServletException) {
					result.setCode(ResultCode.FAIL).setMessage(e.getMessage());
				} else if (e instanceof HttpMessageNotReadableException) {
					result.setCode(ResultCode.FAIL).setMessage("未读取到请求数据");
				} else {
					result.setCode(ResultCode.INTERNAL_SERVER_ERROR)
							.setMessage("接口 [" + request.getRequestURI() + "] 内部错误，请联系管理员");
					String message;
					if (handler instanceof HandlerMethod) {
						HandlerMethod handlerMethod = (HandlerMethod) handler;
						message = String.format("接口 [%s] 出现异常，方法：%s.%s，异常摘要：%s", request.getRequestURI(),
								handlerMethod.getBean().getClass().getName(), handlerMethod.getMethod().getName(),
								e.getMessage());
					} else {
						message = e.getMessage();
					}
					logger.error(message, e);
				}
				responseResult(response, result);
				return new ModelAndView();
			}

		});
	}

	// 解决跨域问题
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**").allowedOrigins("*").allowedMethods("*").allowedHeaders("*").allowCredentials(true)
				.exposedHeaders(HttpHeaders.SET_COOKIE).maxAge(3600L);
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new TokenCheckInterceptor());
		super.addInterceptors(registry);
	}

	private void responseResult(HttpServletResponse response, Result<?> result) {
		response.setCharacterEncoding("UTF-8");
		response.setHeader("Content-type", "application/json;charset=UTF-8");
		response.setStatus(200);
		try {
			response.getWriter().write(JSON.toJSONString(result));
		} catch (IOException ex) {
			logger.error(ex.getMessage());
		}
	}

	private String getIpAddress(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_CLIENT_IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		// 如果是多级代理，那么取第一个ip为客户端ip
		if (ip != null && ip.indexOf(",") != -1) {
			ip = ip.substring(0, ip.indexOf(",")).trim();
		}

		return ip;
	}

	@Bean(name = "idWorker")
	public IdWorker initIdWorker() {
		logger.info("Init Id Worker {}, {}", workerId, dataCenterId);
		IdWorker worker = new IdWorker(workerId, dataCenterId);
		return worker;
	}

	@Bean(name = "systemLocal")
	public SystemLocal initSystemLocal() {
		SystemLocal systemLocal = new SystemLocal();
		systemLocal.init(local);
		return systemLocal;
	}

	@Bean
	public FilterRegistrationBean jwtFilter() {
		final FilterRegistrationBean registrationBean = new FilterRegistrationBean();
		registrationBean.setFilter(new JwtFilter());
		// 添加需要拦截的url
		List<String> urlPatterns = Lists.newArrayList();
		urlPatterns.add("/check");
		urlPatterns.add("/unit/**");
		urlPatterns.add("/dictionary/**");
		urlPatterns.add("/sys/**");
		urlPatterns.add("/loginaccount/**");
		registrationBean.addUrlPatterns(urlPatterns.toArray(new String[urlPatterns.size()]));
		return registrationBean;
	}

	public void MethodArgumentNotValidException(Exception ex, HttpServletRequest request,
			HttpServletResponse response) {
		MethodArgumentNotValidException c = (MethodArgumentNotValidException) ex;
		List<ObjectError> errors = c.getBindingResult().getAllErrors();
		StringBuffer errorMsg = new StringBuffer();
		errors.stream().forEach(x -> errorMsg.append(x.getDefaultMessage()).append(";"));
		pouplateExceptionResponse(response, HttpStatus.INTERNAL_SERVER_ERROR, errorMsg.toString());
	}

	private void pouplateExceptionResponse(HttpServletResponse response, HttpStatus errorCode, String errorMessage) {
		try {
			response.sendError(errorCode.value(), errorMessage);
		} catch (IOException e) {
			logger.error("failed to populate response error", e);
		}
	}
}
