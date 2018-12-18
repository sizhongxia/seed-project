package com.company.project.web.basic;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;

@RestController
@RequestMapping("/basic/api/")
public class BasicApiController {
	final Logger logger = LoggerFactory.getLogger(BasicApiController.class);

	@PostMapping("/demo")
	public Result<?> demo() {
		Map<String, Object> result = new HashMap<>();
		result.put("name", "12345");
		return ResultGenerator.genSuccessResult(result);
	}

}
