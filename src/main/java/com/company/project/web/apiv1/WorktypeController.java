package com.company.project.web.apiv1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.project.annotation.TokenCheck;
import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.model.UnitWorkType;
import com.company.project.model.om.WorktypeModel;
import com.company.project.model.param.apiv1.CommonParam;
import com.company.project.model.param.apiv1.WorktypeParam;
import com.company.project.service.UnitWorkTypeService;

import tk.mybatis.mapper.entity.Condition;

@RestController
@RequestMapping("/apiv1/worktype")
public class WorktypeController {

	@Resource
	private UnitWorkTypeService unitWorkTypeService;

	@TokenCheck
	@PostMapping("/data")
	public Result<?> data(@Validated @RequestBody CommonParam param, BindingResult bindingResult,
			HttpServletRequest request) {
		if (bindingResult.hasErrors()) {
			return ResultGenerator.genFailResult(bindingResult.getFieldError().getDefaultMessage());
		}
		List<UnitWorkType> worktypes = unitWorkTypeService.selectAllWorktype(param.getPid());
		List<Map<String, Object>> list = new ArrayList<>();

		if (worktypes != null && worktypes.size() > 0) {
			Map<String, Object> item = null;
			for (UnitWorkType wt : worktypes) {
				item = new HashMap<>();
				item.put("id", wt.getId());
				item.put("name", wt.getName());
				item.put("type", wt.getType());
				list.add(item);
			}
		}
		return ResultGenerator.genSuccessResult(list);
	}

	@TokenCheck
	@PostMapping("/detail")
	public Result<?> detail(@Validated @RequestBody WorktypeParam param, BindingResult bindingResult,
			HttpServletRequest request) {
		if (bindingResult.hasErrors()) {
			return ResultGenerator.genFailResult(bindingResult.getFieldError().getDefaultMessage());
		}
		if (!NumberUtils.isParsable(param.getId())) {
			return ResultGenerator.genFailResult("无效的表单ID");
		}
		UnitWorkType unitWorkType = unitWorkTypeService.findById(Long.parseLong(param.getId().trim()));
		if (unitWorkType == null) {
			return ResultGenerator.genFailResult("无效的表单ID");
		}
		Map<String, Object> data = new HashMap<>();
		data.put("id", unitWorkType.getId());
		data.put("name", unitWorkType.getName());
		return ResultGenerator.genSuccessResult(data);
	}

	@TokenCheck
	@PostMapping("/save")
	public Result<?> save(@Validated @RequestBody WorktypeModel model, BindingResult bindingResult,
			HttpServletRequest request) {
		if (bindingResult.hasErrors()) {
			return ResultGenerator.genFailResult(bindingResult.getFieldError().getDefaultMessage());
		}

		String name = model.getName();
		Condition condition = new Condition(UnitWorkType.class);
		condition.createCriteria().andEqualTo("name", name).andEqualTo("prouuid", model.getPid());
		List<UnitWorkType> unitWorktypes = unitWorkTypeService.findByCondition(condition);
		if (unitWorktypes != null && unitWorktypes.size() > 0) {
			UnitWorkType uwt = unitWorktypes.get(0);
			if (uwt.getState().intValue() != 0) {
				uwt.setState(0);
				uwt.setUpdatetime(System.currentTimeMillis());
				unitWorkTypeService.update(uwt);
				return ResultGenerator.genSuccessResult("保存成功");
			} else {
				return ResultGenerator.genFailResult("当前工种已存在");
			}
		}

		condition = new Condition(UnitWorkType.class);
		condition.createCriteria().andEqualTo("name", name).andEqualTo("type", 0).andEqualTo("state", 0);
		unitWorktypes = unitWorkTypeService.findByCondition(condition);
		if (unitWorktypes != null && unitWorktypes.size() > 0) {
			return ResultGenerator.genFailResult("当前工种已存在");
		}

		UnitWorkType unitWorktype = new UnitWorkType();
		unitWorktype.setName(name);
		unitWorktype.setProuuid(model.getPid());
		unitWorktype.setState(0);
		unitWorktype.setType(1);
		unitWorktype.setAddtime(System.currentTimeMillis());
		unitWorktype.setUpdatetime(System.currentTimeMillis());
		unitWorkTypeService.save(unitWorktype);
		return ResultGenerator.genSuccessResult("保存成功");
	}

	@TokenCheck
	@PostMapping("/update")
	public Result<?> update(@Validated @RequestBody WorktypeModel model, BindingResult bindingResult,
			HttpServletRequest request) {
		if (bindingResult.hasErrors()) {
			return ResultGenerator.genFailResult(bindingResult.getFieldError().getDefaultMessage());
		}

		if (!NumberUtils.isParsable(model.getId())) {
			return ResultGenerator.genFailResult("无效的表单ID");
		}

		UnitWorkType _unitWorkType = unitWorkTypeService.findById(Long.parseLong(model.getId().trim()));
		if (_unitWorkType == null) {
			return ResultGenerator.genFailResult("无效的表单ID");
		}

		if (_unitWorkType.getType().intValue() == 0) {
			return ResultGenerator.genFailResult("系统默认工种不允许修改");
		}

		String name = model.getName();
		Condition condition = new Condition(UnitWorkType.class);
		condition.createCriteria().andEqualTo("name", name).andEqualTo("prouuid", model.getPid()).andEqualTo("state", 0);
		List<UnitWorkType> unitWorktypes = unitWorkTypeService.findByCondition(condition);
		if (unitWorktypes != null && unitWorktypes.size() > 0) {
			UnitWorkType uwt = unitWorktypes.get(0);
			// if (uwt.getState().intValue() != 0) {
			// uwt.setState(0);
			// uwt.setUpdatetime(System.currentTimeMillis());
			// unitWorkTypeService.update(uwt);
			// _unitWorkType.setState(1);
			// unitWorkTypeService.update(_unitWorkType);
			// return ResultGenerator.genSuccessResult("修改成功");
			if (!_unitWorkType.getId().equals(uwt.getId())) {
				return ResultGenerator.genFailResult("当前工种名称已存在");
			}
		}

		condition = new Condition(UnitWorkType.class);
		condition.createCriteria().andEqualTo("name", name).andEqualTo("type", 0).andEqualTo("state", 0);
		unitWorktypes = unitWorkTypeService.findByCondition(condition);
		if (unitWorktypes != null && unitWorktypes.size() > 0) {
			return ResultGenerator.genFailResult("当前工种已存在");
		}

		_unitWorkType.setName(name);
		_unitWorkType.setUpdatetime(System.currentTimeMillis());
		unitWorkTypeService.update(_unitWorkType);
		return ResultGenerator.genSuccessResult("修改成功");
	}

	@TokenCheck
	@PostMapping("/delete")
	public Result<?> delete(@Validated @RequestBody WorktypeParam param, BindingResult bindingResult,
			HttpServletRequest request) {
		if (bindingResult.hasErrors()) {
			return ResultGenerator.genFailResult(bindingResult.getFieldError().getDefaultMessage());
		}
		if (!NumberUtils.isParsable(param.getId())) {
			return ResultGenerator.genFailResult("无效的表单ID");
		}

		UnitWorkType _unitWorkType = unitWorkTypeService.findById(Long.parseLong(param.getId().trim()));
		if (_unitWorkType == null) {
			return ResultGenerator.genFailResult("无效的表单ID");
		}

		if (_unitWorkType.getType().intValue() == 0) {
			return ResultGenerator.genFailResult("系统默认工种不允许删除");
		}

		_unitWorkType.setState(1);
		_unitWorkType.setUpdatetime(System.currentTimeMillis());
		unitWorkTypeService.update(_unitWorkType);
		return ResultGenerator.genSuccessResult("删除成功");
	}
}
