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
import com.company.project.model.SysDictionary;
import com.company.project.model.om.DictionaryModel;
import com.company.project.model.param.DictionartSearchParam;
import com.company.project.model.returns.Pagination;
import com.company.project.service.SysDictionaryService;
import com.company.project.unit.AesUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import tk.mybatis.mapper.entity.Condition;
import tk.mybatis.mapper.entity.Example.Criteria;

@RestController
@RequestMapping("/apiv0/dictionary")
public class DictionaryController {
	private Logger logger = LoggerFactory.getLogger(DictionaryController.class.getName());

	@Resource
	private SysDictionaryService sysDictionaryService;

	@TokenCheck
	@PostMapping("/data")
	public Result<?> data(@RequestBody DictionartSearchParam param) {
		logger.info("company search param:{}", param.toString());

		Condition condition = new Condition(SysDictionary.class);
		Criteria criteria = condition.createCriteria();

		String keyword = param.getKeyword();
		if (StringUtils.isNotBlank(keyword)) {
			criteria.andLike("name", String.format("%%%s%%", keyword.trim()));
		}

		String type = param.getType();
		if (StringUtils.isNotBlank(type) && !"all".equals(type)) {
			criteria.andEqualTo("type", type.trim());
		}

		Integer page = param.getPage();
		if (page == null || page.intValue() < 1) {
			page = 1;
		}
		Integer size = param.getSize();
		if (size == null || size.intValue() < 1) {
			size = 10;
		}
		condition.setOrderByClause("type asc, value asc");
		PageHelper.startPage(page, size);
		Page<SysDictionary> _list = (Page<SysDictionary>) sysDictionaryService.findByCondition(condition);

		List<SysDictionary> result = _list.getResult();
		List<Map<String, Object>> list = new ArrayList<>();
		if (result != null && result.size() > 0) {
			Map<String, Object> item = null;
			for (SysDictionary i : result) {
				item = new HashMap<>();
				try {
					item.put("id", AesUtil.encrypt(i.getId().toString()));
				} catch (Exception e) {
					logger.error(e.getMessage());
					return ResultGenerator.genFailResult("系统错误");
				}
				item.put("name", i.getName());
				item.put("value", i.getValue());
				item.put("type", i.getType());
				item.put("remarks", i.getRemarks());
				list.add(item);
			}
		}

		Map<String, Object> data = new HashMap<>();
		data.put("list", list);
		Pagination pagination = new Pagination();
		pagination.setTotal(_list.getTotal());
		pagination.setCurrent(_list.getPageNum());
		pagination.setPageSize(_list.getPageSize());
		data.put("pagination", pagination);
		return ResultGenerator.genSuccessResult(data);
	}

	@TokenCheck
	@PostMapping("/getByType")
	public Result<?> getByType(@RequestBody DictionartSearchParam param) {
		logger.info("company search param:{}", param.toString());

		Condition condition = new Condition(SysDictionary.class);
		Criteria criteria = condition.createCriteria();
		List<Map<String, Object>> list = new ArrayList<>();
		String type = param.getType();
		if (StringUtils.isNotBlank(type) && !"all".equals(type)) {
			criteria.andEqualTo("type", type.trim());
		} else {
			return ResultGenerator.genSuccessResult(list);
		}
		condition.setOrderByClause("value asc");
		List<SysDictionary> result = sysDictionaryService.findByCondition(condition);
		if (result != null && result.size() > 0) {
			Map<String, Object> item = null;
			for (SysDictionary i : result) {
				item = new HashMap<>();
				item.put("key", i.getValue().toString());
				item.put("name", i.getName());
				list.add(item);
			}
		}
		return ResultGenerator.genSuccessResult(list);
	}

	@TokenCheck
	@PostMapping("/getById")
	public Result<?> getById(@RequestBody DictionaryModel model) {

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

		SysDictionary dictionary = sysDictionaryService.findBy("id", Integer.parseInt(id.trim()));
		if (dictionary == null) {
			return ResultGenerator.genFailResult("无效的表单");
		}

		Map<String, Object> item = new HashMap<>();
		item.put("id", model.getUuid());
		item.put("name", dictionary.getName());
		item.put("value", dictionary.getValue().toString());
		item.put("type", dictionary.getType());
		item.put("remarks", dictionary.getRemarks());
		return ResultGenerator.genSuccessResult(item);
	}

	@TokenCheck
	@PostMapping("/getAllTypes")
	public Result<?> getAllTypes() {
		List<String> types = sysDictionaryService.selectAllTypes();
		if (types == null) {
			types = new ArrayList<>();
		}
		List<Map<String, String>> list = new ArrayList<>();
		Map<String, String> item = new HashMap<>();
		item.put("key", "all");
		item.put("name", "所有类型");
		list.add(item);
		for (String type : types) {
			item = new HashMap<>();
			item.put("key", type);
			item.put("name", sysDictionaryService.selectTypeName(type));
			list.add(item);
		}
		return ResultGenerator.genSuccessResult(list);
	}

	@TokenCheck
	@PostMapping("/deleteById")
	public Result<?> deleteById(@RequestBody DictionaryModel model) {

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

		SysDictionary dictionary = sysDictionaryService.findBy("id", Integer.parseInt(id.trim()));
		if (dictionary == null) {
			return ResultGenerator.genFailResult("无效的表单");
		}

		sysDictionaryService.deleteById(dictionary.getId());

		return ResultGenerator.genSuccessResult();
	}

	@TokenCheck
	@PostMapping("/save")
	public Result<?> save(@Validated @RequestBody DictionaryModel model, BindingResult bindingResult,
			HttpServletRequest request) {
		if (bindingResult.hasErrors()) {
			return ResultGenerator.genFailResult(bindingResult.getFieldError().getDefaultMessage());
		}
		Condition condition = new Condition(SysDictionary.class);
		condition.createCriteria().andEqualTo("type", model.getType()).andEqualTo("value", model.getValue());
		List<SysDictionary> dictionarys = sysDictionaryService.findByCondition(condition);
		SysDictionary dictionary = null;
		if (dictionarys == null || dictionarys.isEmpty()) {
			dictionary = new SysDictionary();
			dictionary.setName(model.getName());
			dictionary.setType(model.getType());
			dictionary.setValue(new Integer(model.getValue()));
			dictionary.setRemarks(model.getRemarks());
			sysDictionaryService.save(dictionary);
			return ResultGenerator.genSuccessResult();
		} else {
			return ResultGenerator.genFailResult("当前字典类型值已存在");
		}
	}

	@TokenCheck
	@PostMapping("/update")
	public Result<?> update(@Validated @RequestBody DictionaryModel model, BindingResult bindingResult,
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

		SysDictionary dictionary = sysDictionaryService.findBy("id", Integer.parseInt(id.trim()));
		if (dictionary == null) {
			return ResultGenerator.genFailResult("无效的表单");
		}

		Condition condition = new Condition(SysDictionary.class);
		condition.createCriteria().andEqualTo("type", model.getType()).andEqualTo("value", model.getValue());
		List<SysDictionary> dictionarys = sysDictionaryService.findByCondition(condition);
		if (dictionarys != null && dictionarys.size() > 0) {
			if (!dictionarys.get(0).getId().equals(dictionary.getId())) {
				return ResultGenerator.genFailResult("当前字典类型值已存在");
			}
		}

		dictionary.setName(model.getName());
		dictionary.setType(model.getType());
		dictionary.setValue(new Integer(model.getValue()));
		dictionary.setRemarks(model.getRemarks());
		sysDictionaryService.update(dictionary);
		return ResultGenerator.genSuccessResult();
	}

}
