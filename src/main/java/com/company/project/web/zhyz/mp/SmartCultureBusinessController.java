package com.company.project.web.zhyz.mp;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.project.annotation.SmartCultureTokenCheck;
import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;

@RestController
@RequestMapping("/zhyz/miniapp/api/business")
public class SmartCultureBusinessController {

	@SmartCultureTokenCheck
	@RequestMapping("/index")
	public Result<?> index(HttpServletRequest request) {
		return ResultGenerator.genSuccessResult("SUCCESS");
	}

}
