package com.company.project.web;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;

@Controller
@RequestMapping("/")
public class AdminController {
	private Logger logger = LoggerFactory.getLogger(AdminController.class.getName());

	@ResponseBody
	@PostMapping("/apiv0/check")
	public Result<?> check() throws IOException {
		logger.debug("check status");
		return ResultGenerator.genSuccessResult();
	}
}
