package com.company.project.web;

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
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.project.annotation.TokenCheck;
import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.model.UnitCompany;
import com.company.project.model.UnitDepartment;
import com.company.project.model.om.UnitDepartmentModel;
import com.company.project.model.param.DepartmentSearchParam;
import com.company.project.model.returns.Pagination;
import com.company.project.service.UnitCompanyService;
import com.company.project.service.UnitDepartmentService;
import com.company.project.unit.UuidUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xiaoleilu.hutool.date.DateUtil;

import tk.mybatis.mapper.entity.Condition;
import tk.mybatis.mapper.entity.Example.Criteria;

@RestController
@RequestMapping("/apiv0/unit/department")
public class UnitDepartmentController {
	private Logger logger = LoggerFactory.getLogger(UnitDepartmentController.class.getName());

	@Resource
	private UnitDepartmentService unitDepartmentService;
	@Resource
	private UnitCompanyService unitCompanyService;

	@TokenCheck
	@PostMapping("/data")
	public Result<?> data(@RequestBody DepartmentSearchParam param) {
		logger.info("company search param:{}", param.toString());

		Condition condition = new Condition(UnitDepartment.class);
		Criteria criteria = condition.createCriteria().andEqualTo("state", 0);

		String keyword = param.getKeyword();
		if (StringUtils.isNotBlank(keyword)) {
			criteria.andLike("deptname", String.format("%%%s%%", keyword.trim()));
		}
		String deptUuid = param.getDeptUuid();
		String deptName = null;
		if (StringUtils.isNotBlank(deptUuid)) {
			criteria.andEqualTo("deptuuid", deptUuid.trim());
			UnitCompany unitCompany = unitCompanyService.findBy("uuid", deptUuid.trim());
			if (unitCompany == null) {
				return ResultGenerator.genFailResult("链接可能已失效");
			}
			deptName = unitCompany.getCompanyname();
		} else {
			return ResultGenerator.genFailResult("无效的链接");
		}
		String parentUuid = param.getParentUuid();
		if (StringUtils.isNotBlank(parentUuid)) {
			criteria.andEqualTo("parentuuid", parentUuid.trim());
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
		Page<UnitDepartment> _list = (Page<UnitDepartment>) unitDepartmentService.findByCondition(condition);

		List<Map<String, Object>> list = new ArrayList<>();
		List<UnitDepartment> result = _list.getResult();
		if (result != null && result.size() > 0) {
			Map<String, Object> item = null;
			for (UnitDepartment i : result) {
				item = new HashMap<>();
				item.put("id", i.getUuid());
				item.put("name", i.getDeptname());
				item.put("addTime", DateUtil.format(new Date(i.getAddtime()), "yyyy-MM-dd HH:mm:ss"));
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
	@PostMapping("/getDeptsByCompany")
	public Result<?> getDeptsByCompany(@RequestBody DepartmentSearchParam param) {
		logger.info("company search param:{}", param.toString());
		Condition condition = new Condition(UnitDepartment.class);
		Criteria criteria = condition.createCriteria().andEqualTo("state", 0);
		List<Map<String, Object>> list = new ArrayList<>();
		String deptUuid = param.getDeptUuid();
		if (StringUtils.isNotBlank(deptUuid)) {
			criteria.andEqualTo("deptuuid", deptUuid.trim());
			UnitCompany unitCompany = unitCompanyService.findBy("uuid", deptUuid.trim());
			if (unitCompany == null) {
				return ResultGenerator.genSuccessResult(list);
			}
		} else {
			return ResultGenerator.genSuccessResult(list);
		}
		List<UnitDepartment> result = unitDepartmentService.findByCondition(condition);
		if (result != null && result.size() > 0) {
			Map<String, Object> item = null;
			for (UnitDepartment i : result) {
				item = new HashMap<>();
				item.put("key", i.getUuid());
				item.put("name", i.getDeptname());
				list.add(item);
			}
		}
		return ResultGenerator.genSuccessResult(list);
	}

	@TokenCheck
	@PostMapping("/getById")
	public Result<?> getById(@RequestBody UnitDepartmentModel model) {

		if (StringUtils.isBlank(model.getUuid())) {
			return ResultGenerator.genFailResult("无效的表单");
		}

		UnitDepartment unitDepartment = unitDepartmentService.findBy("uuid", model.getUuid());
		if (unitDepartment == null) {
			return ResultGenerator.genFailResult("无效的表单");
		}

		Map<String, Object> item = new HashMap<>();
		item.put("id", unitDepartment.getUuid());
		item.put("name", unitDepartment.getDeptname());
		item.put("belongedId", unitDepartment.getDeptuuid());
		item.put("parentId", unitDepartment.getParentuuid());
		return ResultGenerator.genSuccessResult(item);
	}

	@TokenCheck
	@PostMapping("/deleteById")
	public Result<?> deleteById(@RequestBody UnitDepartmentModel model) {

		if (StringUtils.isBlank(model.getUuid())) {
			return ResultGenerator.genFailResult("无效的表单");
		}

		UnitDepartment unitDepartment = unitDepartmentService.findBy("uuid", model.getUuid());
		if (unitDepartment == null) {
			return ResultGenerator.genFailResult("无效的表单");
		}

		unitDepartment.setState(1);
		unitDepartment.setUpdatetime(System.currentTimeMillis());
		unitDepartmentService.update(unitDepartment);

		return ResultGenerator.genSuccessResult();
	}

	@TokenCheck
	@PostMapping("/save")
	public Result<?> save(@Validated @RequestBody UnitDepartmentModel model, BindingResult bindingResult,
			HttpServletRequest request) {
		if (bindingResult.hasErrors()) {
			return ResultGenerator.genFailResult(bindingResult.getFieldError().getDefaultMessage());
		}
		Condition condition = new Condition(UnitDepartment.class);
		condition.createCriteria().andEqualTo("deptuuid", model.getDeptuuid()).andEqualTo("deptname",
				model.getDeptname());
		List<UnitDepartment> unitDepartments = unitDepartmentService.findByCondition(condition);
		UnitDepartment unitDepartment = null;
		if (unitDepartments == null || unitDepartments.isEmpty()) {
			unitDepartment = new UnitDepartment();
			unitDepartment.setUuid(UuidUtil.init());
			unitDepartment.setDeptname(model.getDeptname());
			unitDepartment.setDeptuuid(model.getDeptuuid());
			unitDepartment.setParentuuid(model.getParentuuid());
			unitDepartment.setAddtime(System.currentTimeMillis());
			unitDepartment.setUpdatetime(System.currentTimeMillis());
			unitDepartment.setState(0);
			unitDepartmentService.save(unitDepartment);
		} else {
			unitDepartment = unitDepartments.get(0);
			unitDepartment.setDeptname(model.getDeptname());
			unitDepartment.setDeptuuid(model.getDeptuuid());
			unitDepartment.setParentuuid(model.getParentuuid());
			unitDepartment.setState(0);
			unitDepartment.setUpdatetime(System.currentTimeMillis());
			unitDepartmentService.update(unitDepartment);
		}
		return ResultGenerator.genSuccessResult();
	}

	@TokenCheck
	@PostMapping("/update")
	public Result<?> update(@Validated @RequestBody UnitDepartmentModel model, BindingResult bindingResult,
			HttpServletRequest request) {
		if (bindingResult.hasErrors()) {
			return ResultGenerator.genFailResult(bindingResult.getFieldError().getDefaultMessage());
		}

		if (StringUtils.isBlank(model.getUuid())) {
			return ResultGenerator.genFailResult("无效的表单");
		}

		UnitDepartment unitDepartment = unitDepartmentService.findBy("uuid", model.getUuid());
		if (unitDepartment == null) {
			return ResultGenerator.genFailResult("无效的表单");
		}

		Condition condition = new Condition(UnitDepartment.class);
		condition.createCriteria().andEqualTo("deptuuid", model.getDeptuuid()).andEqualTo("deptname",
				model.getDeptname());
		List<UnitDepartment> unitDepartments = unitDepartmentService.findByCondition(condition);
		if (unitDepartments != null && unitDepartments.size() > 0) {
			UnitDepartment _unitDepartment = unitDepartments.get(0);
			if (_unitDepartment != null && !_unitDepartment.getUuid().equals(unitDepartment.getUuid())) {
				return ResultGenerator.genFailResult("当前部门名称已存在");
			}
		}

		unitDepartment.setDeptname(model.getDeptname());
		unitDepartment.setDeptuuid(model.getDeptuuid());
		unitDepartment.setUpdatetime(System.currentTimeMillis());
		unitDepartmentService.update(unitDepartment);
		return ResultGenerator.genSuccessResult();
	}

}
