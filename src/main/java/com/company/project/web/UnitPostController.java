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
import com.company.project.model.UnitDepartment;
import com.company.project.model.UnitPost;
import com.company.project.model.om.UnitPostModel;
import com.company.project.model.param.PostSearchParam;
import com.company.project.model.returns.Pagination;
import com.company.project.service.UnitDepartmentService;
import com.company.project.service.UnitPostService;
import com.company.project.unit.UuidUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import tk.mybatis.mapper.entity.Condition;
import tk.mybatis.mapper.entity.Example.Criteria;

@RestController
@RequestMapping("/unit/post")
public class UnitPostController {
	private Logger logger = LoggerFactory.getLogger(UnitPostController.class.getName());

	@Resource
	private UnitPostService unitPostService;
	@Resource
	private UnitDepartmentService unitDepartmentService;

	@TokenCheck
	@PostMapping("/data")
	public Result<?> data(@RequestBody PostSearchParam param) {
		logger.info("company search param:{}", param.toString());
		Condition condition = new Condition(UnitPost.class);
		Criteria criteria = condition.createCriteria().andEqualTo("state", 0);
		String keyword = param.getKeyword();
		if (StringUtils.isNotBlank(keyword)) {
			criteria.andLike("name", String.format("%%%s%%", keyword.trim()));
		}
		String deptUuid = param.getDeptUuid();
		String deptName = null;
		if (StringUtils.isNotBlank(deptUuid)) {
			criteria.andEqualTo("deptuuid", deptUuid.trim());
			UnitDepartment unitDepartment = unitDepartmentService.findBy("uuid", deptUuid.trim());
			if (unitDepartment == null) {
				return ResultGenerator.genFailResult("链接可能已失效");
			}
			deptName = unitDepartment.getDeptname();
		} else {
			return ResultGenerator.genFailResult("无效的链接");
		}

		Integer page = param.getPage();
		if (page == null || page.intValue() < 1) {
			page = 1;
		}
		Integer size = param.getSize();
		if (size == null || size.intValue() < 1) {
			size = 10;
		}
		PageHelper.startPage(page, size);
		Page<UnitPost> _list = (Page<UnitPost>) unitPostService.findByCondition(condition);

		List<Map<String, Object>> list = new ArrayList<>();
		List<UnitPost> result = _list.getResult();
		if (result != null && result.size() > 0) {
			Map<String, Object> item = null;
			for (UnitPost i : result) {
				item = new HashMap<>();
				item.put("id", i.getUuid());
				item.put("name", i.getName());
				list.add(item);
			}
		}

		Map<String, Object> data = new HashMap<>();
		data.put("deptName", deptName);
		data.put("list", list);
		Pagination pagination = new Pagination();
		pagination.setTotal(_list.getTotal());
		pagination.setCurrent(_list.getPageNum());
		pagination.setPageSize(_list.getPageSize());
		data.put("pagination", pagination);
		return ResultGenerator.genSuccessResult(data);
	}

	@TokenCheck
	@PostMapping("/getAllDeptPosts")
	public Result<?> getAllDeptPosts(@RequestBody PostSearchParam param) {
		logger.info("company search param:{}", param.toString());
		Condition condition = new Condition(UnitPost.class);
		Criteria criteria = condition.createCriteria().andEqualTo("state", 0);
		String deptUuid = param.getDeptUuid();
		List<Map<String, Object>> list = new ArrayList<>();
		if (StringUtils.isNotBlank(deptUuid)) {
			UnitDepartment unitDepartment = unitDepartmentService.findBy("uuid", deptUuid.trim());
			if (unitDepartment == null) {
				return ResultGenerator.genSuccessResult(list);
			}
			criteria.andEqualTo("deptuuid", deptUuid.trim());
		} else {
			return ResultGenerator.genSuccessResult(list);
		}
		List<UnitPost> result = unitPostService.findByCondition(condition);
		if (result != null && result.size() > 0) {
			Map<String, Object> item = null;
			for (UnitPost i : result) {
				item = new HashMap<>();
				item.put("key", i.getUuid());
				item.put("name", i.getName());
				list.add(item);
			}
		}
		return ResultGenerator.genSuccessResult(list);
	}

	@TokenCheck
	@PostMapping("/getById")
	public Result<?> getById(@RequestBody UnitPostModel model) {

		if (StringUtils.isBlank(model.getUuid())) {
			return ResultGenerator.genFailResult("无效的表单");
		}

		UnitPost unitPost = unitPostService.findBy("uuid", model.getUuid());
		if (unitPost == null) {
			return ResultGenerator.genFailResult("无效的表单");
		}

		Map<String, Object> item = new HashMap<>();
		item.put("id", unitPost.getUuid());
		item.put("name", unitPost.getName());
		return ResultGenerator.genSuccessResult(item);
	}

	@TokenCheck
	@PostMapping("/deleteById")
	public Result<?> deleteById(@RequestBody UnitPostModel model) {

		if (StringUtils.isBlank(model.getUuid())) {
			return ResultGenerator.genFailResult("无效的表单");
		}

		UnitPost unitPost = unitPostService.findBy("uuid", model.getUuid());
		if (unitPost == null) {
			return ResultGenerator.genFailResult("无效的表单");
		}

		unitPost.setState(1);
		unitPostService.update(unitPost);

		return ResultGenerator.genSuccessResult();
	}

	@TokenCheck
	@PostMapping("/save")
	public Result<?> save(@Validated @RequestBody UnitPostModel model, BindingResult bindingResult,
			HttpServletRequest request) {
		if (bindingResult.hasErrors()) {
			return ResultGenerator.genFailResult(bindingResult.getFieldError().getDefaultMessage());
		}

		UnitDepartment unitDepartment = unitDepartmentService.findBy("uuid", model.getDeptuuid());
		if (unitDepartment == null) {
			return ResultGenerator.genFailResult("链接可能已失效");
		}

		Condition condition = new Condition(UnitPost.class);
		condition.createCriteria().andEqualTo("deptuuid", model.getDeptuuid()).andEqualTo("name", model.getName());
		List<UnitPost> unitPosts = unitPostService.findByCondition(condition);
		UnitPost unitPost = null;
		if (unitPosts == null || unitPosts.isEmpty()) {
			unitPost = new UnitPost();
			unitPost.setUuid(UuidUtil.init());
			unitPost.setName(model.getName());
			unitPost.setDeptuuid(model.getDeptuuid());
			unitPost.setProuuid(unitDepartment.getUuid());
			unitPost.setState(0);
			unitPostService.save(unitPost);
		} else {
			unitPost = unitPosts.get(0);
			unitPost.setName(model.getName());
			unitPost.setDeptuuid(model.getDeptuuid());
			unitPost.setProuuid(unitDepartment.getUuid());
			unitPost.setState(0);
			unitPostService.update(unitPost);
		}
		return ResultGenerator.genSuccessResult();
	}

	@TokenCheck
	@PostMapping("/update")
	public Result<?> update(@Validated @RequestBody UnitPostModel model, BindingResult bindingResult,
			HttpServletRequest request) {
		if (bindingResult.hasErrors()) {
			return ResultGenerator.genFailResult(bindingResult.getFieldError().getDefaultMessage());
		}

		if (StringUtils.isBlank(model.getUuid())) {
			return ResultGenerator.genFailResult("无效的表单");
		}

		UnitPost unitPost = unitPostService.findBy("uuid", model.getUuid());
		if (unitPost == null) {
			return ResultGenerator.genFailResult("无效的表单");
		}

		UnitDepartment unitDepartment = unitDepartmentService.findBy("uuid", model.getDeptuuid());
		if (unitDepartment == null) {
			return ResultGenerator.genFailResult("链接可能已失效");
		}

		Condition condition = new Condition(UnitPost.class);
		condition.createCriteria().andEqualTo("deptuuid", model.getDeptuuid()).andEqualTo("name", model.getName());
		List<UnitPost> unitPosts = unitPostService.findByCondition(condition);

		if (unitPosts != null && unitPosts.size() > 0) {
			UnitPost _unitPost = unitPosts.get(0);
			if (_unitPost != null && !_unitPost.getUuid().equals(unitPost.getUuid())) {
				return ResultGenerator.genFailResult("当前岗位名称已存在");
			}
		}

		unitPost.setName(model.getName());
		unitPost.setDeptuuid(model.getDeptuuid());
		unitPost.setProuuid(unitDepartment.getUuid());
		unitPostService.update(unitPost);
		return ResultGenerator.genSuccessResult();
	}

}
