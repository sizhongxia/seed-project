package com.company.project.web.apiv1;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.project.annotation.TokenCheck;
import com.company.project.configurer.QiniuConstant;
import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.model.BusinessMemorabilia;
import com.company.project.model.BusinessMemorabiliaImg;
import com.company.project.model.om.MemorabiliaModel;
import com.company.project.model.param.apiv1.CommonParam;
import com.company.project.model.param.apiv1.MemorabiliaParam;
import com.company.project.service.BusinessMemorabiliaImgService;
import com.company.project.service.BusinessMemorabiliaService;
import com.company.project.unit.AesUtil;
import com.company.project.unit.IdUtils;

import cn.hutool.core.date.DateUtil;
import tk.mybatis.mapper.entity.Condition;

@RestController
@RequestMapping("/apiv1/memorabilia")
public class MemorabiliaController {

	final Logger logger = LoggerFactory.getLogger(MemorabiliaController.class);

	@Resource
	private BusinessMemorabiliaService businessMemorabiliaService;
	@Resource
	private BusinessMemorabiliaImgService businessMemorabiliaImgService;
	@Autowired
	private QiniuConstant qiniuConstant;

	@TokenCheck
	@PostMapping("/data")
	public Result<?> data(@Validated @RequestBody CommonParam param, BindingResult bindingResult,
			HttpServletRequest request) {
		if (bindingResult.hasErrors()) {
			return ResultGenerator.genFailResult(bindingResult.getFieldError().getDefaultMessage());
		}
		Condition condition = new Condition(BusinessMemorabilia.class);
		condition.createCriteria().andEqualTo("prouuid", param.getPid()).andEqualTo("state", 0);
		condition.orderBy("happentime").desc();
		List<BusinessMemorabilia> businessMemorabilias = businessMemorabiliaService.findByCondition(condition);
		List<Map<String, Object>> list = new ArrayList<>();
		if (businessMemorabilias != null && businessMemorabilias.size() > 0) {
			Map<String, Object> item = null;
			for (BusinessMemorabilia bm : businessMemorabilias) {
				item = new HashMap<>();
				item.put("id", bm.getUuid());
				item.put("title", bm.getTitle());
				item.put("content", bm.getContent());
				item.put("happentime", DateUtil.format(bm.getHappentime(), "yyyy-MM-dd"));
				item.put("addtime", DateUtil.format(new Date(bm.getAddtime().longValue()), "yyyy-MM-dd HH:mm"));
				item.put("updatetime", DateUtil.format(new Date(bm.getUpdatetime().longValue()), "yyyy-MM-dd HH:mm"));
				list.add(item);
			}
		}
		return ResultGenerator.genSuccessResult(list);
	}

	@TokenCheck
	@PostMapping("/detail")
	public Result<?> detail(@Validated @RequestBody MemorabiliaParam param, BindingResult bindingResult,
			HttpServletRequest request) {
		if (bindingResult.hasErrors()) {
			return ResultGenerator.genFailResult(bindingResult.getFieldError().getDefaultMessage());
		}

		if (StringUtils.isBlank(param.getUuid())) {
			return ResultGenerator.genFailResult("无效的表单ID");
		}

		BusinessMemorabilia businessMemorabilia = businessMemorabiliaService.findBy("uuid", param.getUuid());
		if (businessMemorabilia == null) {
			return ResultGenerator.genFailResult("无效的表单ID");
		}

		Map<String, Object> data = new HashMap<>();
		data.put("id", businessMemorabilia.getUuid());
		data.put("title", businessMemorabilia.getTitle());
		data.put("content", businessMemorabilia.getContent());
		data.put("happentime", DateUtil.format(businessMemorabilia.getHappentime(), "yyyy-MM-dd"));
		return ResultGenerator.genSuccessResult(data);
	}

	@TokenCheck
	@PostMapping("/save")
	public Result<?> save(@Validated @RequestBody MemorabiliaModel model, BindingResult bindingResult,
			HttpServletRequest request) {
		if (bindingResult.hasErrors()) {
			return ResultGenerator.genFailResult(bindingResult.getFieldError().getDefaultMessage());
		}

		BusinessMemorabilia memorabilia = new BusinessMemorabilia();
		memorabilia.setUuid(IdUtils.initUuid());
		memorabilia.setProuuid(model.getProuuid());
		memorabilia.setTitle(model.getTitle());
		memorabilia.setContent(model.getContent());
		memorabilia.setHappentime(DateUtil.parse(model.getHappentime()));
		memorabilia.setState(0);
		memorabilia.setAddtime(System.currentTimeMillis());
		memorabilia.setUpdatetime(System.currentTimeMillis());

		businessMemorabiliaService.save(memorabilia);
		return ResultGenerator.genSuccessResult("保存成功");
	}

	@TokenCheck
	@PostMapping("/update")
	public Result<?> update(@Validated @RequestBody MemorabiliaModel model, BindingResult bindingResult,
			HttpServletRequest request) {
		if (bindingResult.hasErrors()) {
			return ResultGenerator.genFailResult(bindingResult.getFieldError().getDefaultMessage());
		}

		if (StringUtils.isBlank(model.getUuid())) {
			return ResultGenerator.genFailResult("无效的表单ID");
		}

		BusinessMemorabilia memorabilia = businessMemorabiliaService.findBy("uuid", model.getUuid());
		if (memorabilia == null) {
			return ResultGenerator.genFailResult("无效的表单ID");
		}

		memorabilia.setTitle(model.getTitle());
		memorabilia.setContent(model.getContent());
		memorabilia.setHappentime(DateUtil.parse(model.getHappentime()));
		memorabilia.setUpdatetime(System.currentTimeMillis());

		businessMemorabiliaService.update(memorabilia);
		return ResultGenerator.genSuccessResult("修改成功");
	}

	@TokenCheck
	@PostMapping("/delete")
	public Result<?> delete(@Validated @RequestBody MemorabiliaParam param, BindingResult bindingResult,
			HttpServletRequest request) {
		if (bindingResult.hasErrors()) {
			return ResultGenerator.genFailResult(bindingResult.getFieldError().getDefaultMessage());
		}

		if (StringUtils.isBlank(param.getUuid())) {
			return ResultGenerator.genFailResult("无效的表单ID");
		}

		BusinessMemorabilia businessMemorabilia = businessMemorabiliaService.findBy("uuid", param.getUuid());
		if (businessMemorabilia == null) {
			return ResultGenerator.genFailResult("无效的表单ID");
		}

		businessMemorabilia.setState(1);
		businessMemorabilia.setUpdatetime(System.currentTimeMillis());
		businessMemorabiliaService.update(businessMemorabilia);
		return ResultGenerator.genSuccessResult("删除成功");
	}

	@TokenCheck
	@PostMapping("/pics")
	public Result<?> pics(@Validated @RequestBody MemorabiliaParam param, BindingResult bindingResult,
			HttpServletRequest request) {
		if (bindingResult.hasErrors()) {
			return ResultGenerator.genFailResult(bindingResult.getFieldError().getDefaultMessage());
		}
		Condition condition = new Condition(BusinessMemorabiliaImg.class);
		condition.createCriteria().andEqualTo("uuid", param.getUuid()).andEqualTo("state", 0);
		condition.orderBy("sortnum").asc();
		List<BusinessMemorabiliaImg> businessMemorabiliaImgs = businessMemorabiliaImgService.findByCondition(condition);
		List<Map<String, Object>> list = new ArrayList<>();
		if (businessMemorabiliaImgs != null && businessMemorabiliaImgs.size() > 0) {
			Map<String, Object> item = null;
			for (BusinessMemorabiliaImg bmi : businessMemorabiliaImgs) {
				item = new HashMap<>();
				try {
					item.put("id", AesUtil.encrypt(bmi.getId().toString()));
				} catch (Exception e) {
					logger.error(e.getMessage());
					continue;
				}
				item.put("title", bmi.getTitle());
				item.put("src", String.format("%s%s", qiniuConstant.getPath(), bmi.getSrc()));
				list.add(item);
			}
		}
		return ResultGenerator.genSuccessResult(list);
	}
}
