package com.company.project.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.project.annotation.TokenCheck;
import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.model.SysArea;
import com.company.project.model.om.AreaModel;
import com.company.project.model.param.AreaSearchParam;
import com.company.project.service.SysAreaService;
import com.company.project.unit.AesUtil;

import tk.mybatis.mapper.entity.Condition;
import tk.mybatis.mapper.entity.Example.Criteria;

@RestController
@RequestMapping("/sys/area")
public class AreaController {
	private Logger logger = LoggerFactory.getLogger(AreaController.class.getName());

	@Resource
	private SysAreaService sysAreaService;

	@TokenCheck
	@PostMapping("/data")
	public Result<?> data(@RequestBody AreaSearchParam param) {
		logger.info("area search param:{}", param.toString());

		Condition condition = new Condition(SysArea.class);
		Criteria criteria = condition.createCriteria();

		String name = param.getName();
		if (StringUtils.isNotBlank(name)) {
			criteria.andLike("name", String.format("%%%s%%", name));
		}
		String pinyin = param.getPinyin();
		if (StringUtils.isNotBlank(pinyin)) {
			criteria.andLike("pinyin", String.format("%%%s%%", pinyin)).orEqualTo("jianpin",
					String.format("%%%s%%", pinyin));
		}
		String code = param.getCode();
		if (StringUtils.isNotBlank(code)) {
			criteria.andEqualTo("code", code);
		}
		String pcode = param.getPcode();
		if (StringUtils.isNotBlank(pcode)) {
			criteria.andEqualTo("pcode", pcode);
		}
		condition.setOrderByClause("code asc");
		List<SysArea> result = sysAreaService.findByCondition(condition);
		List<Map<String, Object>> list = new ArrayList<>();
		if (result != null && result.size() > 0) {
			Map<String, Object> item = null;
			for (SysArea i : result) {
				item = new HashMap<>();
				try {
					item.put("id", AesUtil.encrypt(i.getId().toString()));
				} catch (Exception e) {
					logger.error(e.getMessage());
					return ResultGenerator.genFailResult("系统错误");
				}
				item.put("name", i.getName());
				item.put("code", i.getCode());
				item.put("pcode", i.getPcode());
				item.put("pinyin", i.getPinyin());
				item.put("jianpin", i.getJianpin());
				list.add(item);
			}
		}
		return ResultGenerator.genSuccessResult(list);
	}

	@TokenCheck
	@PostMapping("/getById")
	public Result<?> getById(@RequestBody AreaModel model) {

		if (StringUtils.isBlank(model.getUuid())) {
			return ResultGenerator.genFailResult("无效的表单");
		}
		String id = null;
		try {
			id = AesUtil.desEncrypt(model.getUuid());
		} catch (Exception e) {
			logger.error(e.getMessage());
			return ResultGenerator.genFailResult("无效的表单");

		}

		SysArea area = sysAreaService.findBy("id", Integer.parseInt(id.trim()));
		if (area == null) {
			return ResultGenerator.genFailResult("无效的表单");
		}

		Map<String, Object> item = new HashMap<>();
		item.put("id", model.getUuid());
		item.put("name", area.getName());
		item.put("pinyin", area.getPinyin());
		item.put("jianpin", area.getJianpin());
		item.put("code", area.getCode());
		item.put("pcode", area.getPcode());
		item.put("lat", area.getLat());
		item.put("lng", area.getLng());
		return ResultGenerator.genSuccessResult(item);
	}

	@TokenCheck
	@PostMapping("/deleteById")
	public Result<?> deleteById(@RequestBody AreaModel model) {

		if (StringUtils.isBlank(model.getUuid())) {
			return ResultGenerator.genFailResult("无效的表单");
		}
		String id = null;
		try {
			id = AesUtil.desEncrypt(model.getUuid());
		} catch (Exception e) {
			logger.error(e.getMessage());
			return ResultGenerator.genFailResult("无效的表单");
		}

		SysArea area = sysAreaService.findBy("id", Integer.parseInt(id.trim()));
		if (area == null) {
			return ResultGenerator.genFailResult("无效的表单");
		}

		sysAreaService.deleteById(area.getId());
		return ResultGenerator.genSuccessResult();
	}

	@TokenCheck
	@PostMapping("/save")
	public Result<?> save(@Validated @RequestBody AreaModel model, BindingResult bindingResult,
			HttpServletRequest request) {
		if (bindingResult.hasErrors()) {
			return ResultGenerator.genFailResult(bindingResult.getFieldError().getDefaultMessage());
		}
		int code = Integer.parseInt(model.getCode().trim());
		int pcode = Integer.parseInt(model.getPcode().trim());
		SysArea area = sysAreaService.findBy("code", code);
		if (area == null) {
			area = new SysArea();
			area.setName(model.getName());
			area.setCode(code);
			area.setPcode(pcode);
			area.setJianpin(model.getJianpin());
			area.setPinyin(model.getPinyin());
			area.setLat(model.getLat());
			area.setLng(model.getLng());
			sysAreaService.save(area);
			return ResultGenerator.genSuccessResult();
		} else {
			return ResultGenerator.genFailResult("当前区域已存在");
		}
	}

	@TokenCheck
	@PostMapping("/update")
	public Result<?> update(@Validated @RequestBody AreaModel model, BindingResult bindingResult,
			HttpServletRequest request) {
		if (bindingResult.hasErrors()) {
			return ResultGenerator.genFailResult(bindingResult.getFieldError().getDefaultMessage());
		}

		if (StringUtils.isBlank(model.getUuid())) {
			return ResultGenerator.genFailResult("无效的表单");
		}

		String id = null;
		try {
			id = AesUtil.desEncrypt(model.getUuid());
		} catch (Exception e) {
			logger.error(e.getMessage());
			return ResultGenerator.genFailResult("无效的表单");
		}

		SysArea area = sysAreaService.findBy("id", Integer.parseInt(id.trim()));
		if (area == null) {
			return ResultGenerator.genFailResult("无效的表单");
		}

		int code = Integer.parseInt(model.getCode().trim());
		int pcode = Integer.parseInt(model.getPcode().trim());
		SysArea _area = sysAreaService.findBy("code", code);
		if (_area != null && !_area.getId().equals(area.getId())) {
			return ResultGenerator.genFailResult("当前区域已存在");
		}
		area.setName(model.getName());
		area.setCode(code);
		area.setPcode(pcode);
		area.setJianpin(model.getJianpin());
		area.setPinyin(model.getPinyin());
		area.setLat(model.getLat());
		area.setLng(model.getLng());
		sysAreaService.update(area);
		return ResultGenerator.genSuccessResult();
	}

}
