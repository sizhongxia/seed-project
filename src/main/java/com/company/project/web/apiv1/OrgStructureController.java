package com.company.project.web.apiv1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
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
import com.company.project.model.UnitGroup;
import com.company.project.model.UnitLaborsubcontractor;
import com.company.project.model.UnitPost;
import com.company.project.model.UnitProject;
import com.company.project.model.param.apiv1.CommonParam;
import com.company.project.model.param.apiv1.DepartmentParam;
import com.company.project.model.param.apiv1.GroupParam;
import com.company.project.model.param.apiv1.WorktypeParam;
import com.company.project.model.returns.apiv1.UnitDepartmentTree;
import com.company.project.service.UnitCompanyService;
import com.company.project.service.UnitDepartmentService;
import com.company.project.service.UnitGroupService;
import com.company.project.service.UnitLaborsubcontractorService;
import com.company.project.service.UnitPostService;
import com.company.project.service.UnitProjectService;
import com.company.project.unit.UuidUtil;

import tk.mybatis.mapper.entity.Condition;

@RestController
@RequestMapping("/apiv1/orgstructure")
public class OrgStructureController {

	@Resource
	private UnitProjectService unitProjectService;
	@Resource
	private UnitCompanyService unitCompanyService;
	@Resource
	private UnitDepartmentService unitDepartmentService;
	@Resource
	private UnitPostService unitPostService;
	@Resource
	private UnitGroupService unitGroupService;
	@Resource
	private UnitLaborsubcontractorService unitLaborsubcontractorService;

	@TokenCheck
	@PostMapping("/data")
	public Result<?> data(@Validated @RequestBody CommonParam param, BindingResult bindingResult,
			HttpServletRequest request) {
		if (bindingResult.hasErrors()) {
			return ResultGenerator.genFailResult(bindingResult.getFieldError().getDefaultMessage());
		}
		UnitProject unitProject = unitProjectService.findBy("uuid", param.getPid());
		if (unitProject == null) {
			return ResultGenerator.genFailResult("无效的项目");
		}

		Map<String, Object> data = new HashMap<>();

		data.put("constructionId", "");
		data.put("constructionName", "");

		String constructionId = unitProject.getConstruction();
		if (StringUtils.isNotBlank(constructionId)) {
			UnitCompany construction = unitCompanyService.findBy("uuid", constructionId);
			if (construction != null) {
				data.put("constructionId", construction.getUuid());
				data.put("constructionName", construction.getCompanyname());

				// tree.setId(construction.getUuid());
				// tree.setType("p");
				// tree.setName(construction.getCompanyname());
				// tree.setChildren(new ArrayList<>());

				// Condition unitDepartmentCondition = new Condition(UnitPost.class);
				// unitDepartmentCondition.createCriteria().andEqualTo("deptuuid",
				// param.getPid()).andEqualTo("state", 0);
				// List<UnitDepartment> unitDepartments =
				// unitDepartmentService.findByCondition(unitDepartmentCondition);
				//
				// if (unitDepartments != null && unitDepartments.size() > 0) {
				//
				// Condition unitPostCondition = new Condition(UnitPost.class);
				// unitPostCondition.createCriteria().andEqualTo("prouuid",
				// param.getPid()).andEqualTo("state", 0);
				// List<UnitPost> unitPosts =
				// unitPostService.findByCondition(unitPostCondition);
				//
				// List<UnitDepartmentTree> level2 = new ArrayList<>();
				//
				// UnitDepartmentTree item = null;
				// for (UnitDepartment ud : unitDepartments) {
				// if (StringUtils.isBlank(ud.getParentuuid())) {
				// item = new UnitDepartmentTree();
				// item.setId(ud.getUuid());
				// item.setName(ud.getDeptname());
				// item.setType("d");
				// level2.add(item);
				// }
				// }

				// }

			}
		}

		Condition condition = new Condition(UnitLaborsubcontractor.class);
		condition.createCriteria().andEqualTo("prouuid", param.getPid()).andEqualTo("state", 0);
		List<UnitLaborsubcontractor> laborsubcontractors = unitLaborsubcontractorService.findByCondition(condition);

		List<Map<String, Object>> ps = new ArrayList<>();
		if (laborsubcontractors != null && laborsubcontractors.size() > 0) {
			Map<String, Object> item = null;
			for (UnitLaborsubcontractor lsc : laborsubcontractors) {
				UnitCompany company = unitCompanyService.findBy("uuid", lsc.getCompanyuuid());
				if (company == null) {
					continue;
				}
				item = new HashMap<>();
				item.put("id", company.getUuid());
				item.put("name", company.getCompanyname());

				List<Map<String, Object>> groupList = new ArrayList<>();
				Condition unitGroupCondition = new Condition(UnitGroup.class);
				unitGroupCondition.createCriteria().andEqualTo("prouuid", param.getPid())
						.andEqualTo("companyuuid", company.getUuid()).andEqualTo("state", 0);
				List<UnitGroup> groups = unitGroupService.findByCondition(unitGroupCondition);
				if (groups != null && groups.size() > 0) {
					Map<String, Object> item2 = null;
					for (UnitGroup ug : groups) {
						item2 = new HashMap<>();
						item2.put("id", ug.getUuid());
						item2.put("name", ug.getName());
						groupList.add(item2);
					}
				}

				item.put("groups", groupList);
				ps.add(item);
			}
		}
		data.put("laborsubcontractors", ps);
		return ResultGenerator.genSuccessResult(data);
	}

	@TokenCheck
	@PostMapping("/treedata")
	public Result<?> treedata(@Validated @RequestBody CommonParam param, BindingResult bindingResult,
			HttpServletRequest request) {
		List<UnitDepartmentTree> tree = new ArrayList<>();
		if (bindingResult.hasErrors()) {
			return ResultGenerator.genFailResult(bindingResult.getFieldError().getDefaultMessage());
		}
		UnitProject unitProject = unitProjectService.findBy("uuid", param.getPid());
		if (unitProject == null) {
			return ResultGenerator.genFailResult("无效的项目");
		}
		String constructionId = unitProject.getConstruction();
		if (StringUtils.isBlank(constructionId)) {
			return ResultGenerator.genFailResult("未设置施工单位");
		}
		UnitCompany construction = unitCompanyService.findBy("uuid", constructionId);
		if (construction == null) {
			return ResultGenerator.genFailResult("无效的施工单位");
		}

		UnitDepartmentTree item = new UnitDepartmentTree();
		item.setId(construction.getUuid());
		item.setPid("0");
		item.setName(construction.getCompanyname());
		item.setType("project");
		item.setPosts("");
		tree.add(item);

		// 获取项目工地所有部门
		Condition unitDepartmentCondition = new Condition(UnitDepartment.class);
		unitDepartmentCondition.createCriteria().andEqualTo("deptuuid", param.getPid()).andEqualTo("state", 0);
		List<UnitDepartment> unitDepartments = unitDepartmentService.findByCondition(unitDepartmentCondition);

		if (unitDepartments == null || unitDepartments.size() == 0) {
			return ResultGenerator.genSuccessResult(tree);
		}

		// 获取项目工地所有岗位
		Condition unitPostCondition = new Condition(UnitPost.class);
		unitPostCondition.createCriteria().andEqualTo("prouuid", param.getPid()).andEqualTo("state", 0);
		List<UnitPost> unitPosts = unitPostService.findByCondition(unitPostCondition);
		if (unitPosts == null || unitPosts.size() == 0) {
			return ResultGenerator.genSuccessResult(tree);
		}

		for (UnitDepartment ud : unitDepartments) {
			item = new UnitDepartmentTree();
			item.setId(ud.getUuid());
			if (StringUtils.isBlank(ud.getParentuuid())) {
				item.setPid(construction.getUuid());
			} else {
				item.setPid(ud.getParentuuid());
			}
			item.setName(ud.getDeptname());
			item.setType("dept");
			Set<String> posts = new HashSet<>();
			for (UnitPost up : unitPosts) {
				if (ud.getUuid().equals(up.getDeptuuid())) {
					posts.add(up.getName());
				}
			}
			item.setPosts(String.join(", ", posts));
			tree.add(item);
		}

		Result<?> result = ResultGenerator.genSuccessResult(tree);
		result.setCount(tree.size());
		return result;
	}

	@TokenCheck
	@PostMapping("/laborsubcontractor/groups")
	public Result<?> laborsubcontractorGroups(@Validated @RequestBody WorktypeParam param, BindingResult bindingResult,
			HttpServletRequest request) {
		if (bindingResult.hasErrors()) {
			return ResultGenerator.genFailResult(bindingResult.getFieldError().getDefaultMessage());
		}
		if (StringUtils.isBlank(param.getId())) {
			return ResultGenerator.genFailResult("无效的表单ID");
		}
		UnitCompany laborsubcontractorCompany = unitCompanyService.findBy("uuid", param.getId());
		if (laborsubcontractorCompany == null) {
			return ResultGenerator.genFailResult("无效的表单ID");
		}

		List<Map<String, Object>> groupList = new ArrayList<>();
		Condition unitGroupCondition = new Condition(UnitGroup.class);
		unitGroupCondition.createCriteria().andEqualTo("prouuid", param.getPid())
				.andEqualTo("companyuuid", laborsubcontractorCompany.getUuid()).andEqualTo("state", 0);
		List<UnitGroup> groups = unitGroupService.findByCondition(unitGroupCondition);
		if (groups != null && groups.size() > 0) {
			Map<String, Object> item2 = null;
			for (UnitGroup ug : groups) {
				item2 = new HashMap<>();
				item2.put("id", ug.getUuid());
				item2.put("name", ug.getName());
				groupList.add(item2);
			}
		}

		Map<String, Object> data = new HashMap<>();
		data.put("id", laborsubcontractorCompany.getUuid());
		data.put("name", laborsubcontractorCompany.getCompanyname());
		data.put("groups", groupList);
		return ResultGenerator.genSuccessResult(data);
	}

	@TokenCheck
	@PostMapping("/save/group")
	public Result<?> saveGroup(@Validated @RequestBody GroupParam model, BindingResult bindingResult,
			HttpServletRequest request) {
		if (bindingResult.hasErrors()) {
			return ResultGenerator.genFailResult(bindingResult.getFieldError().getDefaultMessage());
		}
		String name = model.getName();
		if (StringUtils.isBlank(name)) {
			return ResultGenerator.genFailResult("请输入班组名称");
		}
		String companyId = model.getCompanyId();
		if (StringUtils.isBlank(companyId)) {
			return ResultGenerator.genFailResult("无效的表单");
		}
		Condition condition = new Condition(UnitGroup.class);
		condition.createCriteria().andEqualTo("name", name).andEqualTo("prouuid", model.getPid())
				.andEqualTo("companyuuid", companyId).andEqualTo("state", 0);
		List<UnitGroup> unitGroups = unitGroupService.findByCondition(condition);
		if (unitGroups != null && unitGroups.size() > 0) {
			return ResultGenerator.genFailResult("当前班组名称已存在");
		}

		UnitGroup unitGroup = new UnitGroup();
		unitGroup.setUuid(UuidUtil.init());
		unitGroup.setCompanyuuid(companyId);
		unitGroup.setProuuid(model.getPid());
		unitGroup.setName(name);
		unitGroup.setState(0);
		unitGroup.setAddtime(System.currentTimeMillis());
		unitGroup.setUpdatetime(System.currentTimeMillis());
		unitGroupService.save(unitGroup);
		return ResultGenerator.genSuccessResult("保存成功");
	}

	@TokenCheck
	@PostMapping("/update/group")
	public Result<?> updateGroup(@Validated @RequestBody GroupParam model, BindingResult bindingResult,
			HttpServletRequest request) {
		if (bindingResult.hasErrors()) {
			return ResultGenerator.genFailResult(bindingResult.getFieldError().getDefaultMessage());
		}

		if (StringUtils.isBlank(model.getId())) {
			return ResultGenerator.genFailResult("无效的表单ID");
		}
		String name = model.getName();
		if (StringUtils.isBlank(name)) {
			return ResultGenerator.genFailResult("请输入新班组名称");
		}

		UnitGroup _unitGroup = unitGroupService.findBy("uuid", model.getId());
		if (_unitGroup == null) {
			return ResultGenerator.genFailResult("无效的表单ID");
		}

		Condition condition = new Condition(UnitGroup.class);
		condition.createCriteria().andEqualTo("name", name).andEqualTo("prouuid", model.getPid())
				.andEqualTo("companyuuid", _unitGroup.getCompanyuuid()).andEqualTo("state", 0);
		List<UnitGroup> unitGroups = unitGroupService.findByCondition(condition);
		if (unitGroups != null && unitGroups.size() > 0) {
			UnitGroup ug = unitGroups.get(0);
			if (!_unitGroup.getUuid().equals(ug.getUuid())) {
				return ResultGenerator.genFailResult("当前班组名称已存在");
			}
		}

		_unitGroup.setName(name);
		_unitGroup.setUpdatetime(System.currentTimeMillis());
		unitGroupService.update(_unitGroup);
		return ResultGenerator.genSuccessResult("修改成功");
	}

	@TokenCheck
	@PostMapping("/delete/group")
	public Result<?> deleteGroup(@Validated @RequestBody GroupParam model, BindingResult bindingResult,
			HttpServletRequest request) {
		if (bindingResult.hasErrors()) {
			return ResultGenerator.genFailResult(bindingResult.getFieldError().getDefaultMessage());
		}

		if (StringUtils.isBlank(model.getId())) {
			return ResultGenerator.genFailResult("无效的表单ID");
		}

		UnitGroup _unitGroup = unitGroupService.findBy("uuid", model.getId());
		if (_unitGroup == null) {
			return ResultGenerator.genFailResult("无效的表单ID");
		}

		_unitGroup.setState(1);
		_unitGroup.setUpdatetime(System.currentTimeMillis());
		unitGroupService.update(_unitGroup);
		return ResultGenerator.genSuccessResult("删除成功");
	}

	@TokenCheck
	@PostMapping("/level1data")
	public Result<?> level1data(@Validated @RequestBody CommonParam param, BindingResult bindingResult,
			HttpServletRequest request) {
		List<UnitDepartmentTree> list = new ArrayList<>();
		if (bindingResult.hasErrors()) {
			return ResultGenerator.genFailResult(bindingResult.getFieldError().getDefaultMessage());
		}
		UnitProject unitProject = unitProjectService.findBy("uuid", param.getPid());
		if (unitProject == null) {
			return ResultGenerator.genFailResult("无效的项目");
		}
		String constructionId = unitProject.getConstruction();
		if (StringUtils.isBlank(constructionId)) {
			return ResultGenerator.genFailResult("未设置施工单位");
		}
		UnitCompany construction = unitCompanyService.findBy("uuid", constructionId);
		if (construction == null) {
			return ResultGenerator.genFailResult("无效的施工单位");
		}

		UnitDepartmentTree item = new UnitDepartmentTree();
		item.setId("p" + construction.getUuid());
		item.setName(construction.getCompanyname());
		list.add(item);

		// 获取项目工地所有一级部门
		Condition unitDepartmentCondition = new Condition(UnitDepartment.class);
		unitDepartmentCondition.createCriteria().andEqualTo("deptuuid", param.getPid()).andIsNull("parentuuid")
				.andEqualTo("state", 0);
		List<UnitDepartment> unitDepartments = unitDepartmentService.findByCondition(unitDepartmentCondition);

		if (unitDepartments != null && unitDepartments.size() > 0) {
			for (UnitDepartment ud : unitDepartments) {
				item = new UnitDepartmentTree();
				item.setId("d" + ud.getUuid());
				item.setName(ud.getDeptname());
				list.add(item);
			}
		}

		return ResultGenerator.genSuccessResult(list);
	}

	@TokenCheck
	@PostMapping("/save/department")
	public Result<?> saveDepartment(@Validated @RequestBody DepartmentParam model, BindingResult bindingResult,
			HttpServletRequest request) {
		if (bindingResult.hasErrors()) {
			return ResultGenerator.genFailResult(bindingResult.getFieldError().getDefaultMessage());
		}
		String name = model.getName();
		if (StringUtils.isBlank(name)) {
			return ResultGenerator.genFailResult("请输入部门名称");
		}
		name = name.trim();
		String parentId = model.getParentUuid();
		if (StringUtils.isBlank(parentId)) {
			return ResultGenerator.genFailResult("无效的表单");
		}
		if (parentId.startsWith("p")) {
			UnitCompany unitCompany = unitCompanyService.findBy("uuid", parentId.replaceFirst("p", ""));
			if (unitCompany == null) {
				return ResultGenerator.genFailResult("无效的信息");
			}
			parentId = null;
		} else {
			UnitDepartment unitDepartment = unitDepartmentService.findBy("uuid", parentId.replaceFirst("d", ""));
			if (unitDepartment == null) {
				return ResultGenerator.genFailResult("无效的信息");
			}
			parentId = unitDepartment.getUuid();
		}

//		Condition condition = new Condition(UnitDepartment.class);
//		condition.createCriteria().andEqualTo("deptuuid", model.getPid()).andEqualTo("deptname", name)
//				.andEqualTo("state", 0);
//		List<UnitDepartment> unitDepartments = unitDepartmentService.findByCondition(condition);
//		if (unitDepartments != null && unitDepartments.size() > 0) {
//			return ResultGenerator.genFailResult("当前部门名称已存在");
//		}

		UnitDepartment unitDepartment = new UnitDepartment();
		unitDepartment.setUuid(UuidUtil.init());
		unitDepartment.setParentuuid(parentId);
		unitDepartment.setDeptname(name);
		unitDepartment.setDeptuuid(model.getPid());
		unitDepartment.setState(0);
		unitDepartment.setAddtime(System.currentTimeMillis());
		unitDepartment.setUpdatetime(System.currentTimeMillis());
		unitDepartmentService.save(unitDepartment);

		String[] posts = model.getPosts();
		if (posts != null && posts.length > 0) {
			for (String post : posts) {
				if (StringUtils.isBlank(post)) {
					continue;
				}
				post.trim();
				Condition unitPostCondition = new Condition(UnitPost.class);
				unitPostCondition.createCriteria().andEqualTo("deptuuid", unitDepartment.getUuid())
						.andEqualTo("name", post).andEqualTo("state", 0);
				List<UnitPost> _unitPosts = unitPostService.findByCondition(unitPostCondition);
				if (_unitPosts != null && _unitPosts.size() > 0) {
					continue;
				}
				UnitPost up = new UnitPost();
				up.setUuid(UuidUtil.init());
				up.setName(post);
				up.setProuuid(model.getPid());
				up.setDeptuuid(unitDepartment.getUuid());
				up.setState(0);
				unitPostService.save(up);
			}
		}

		return ResultGenerator.genSuccessResult("保存成功");
	}

	@TokenCheck
	@PostMapping("/department/detail")
	public Result<?> departmentDetail(@Validated @RequestBody DepartmentParam param, BindingResult bindingResult,
			HttpServletRequest request) {
		if (bindingResult.hasErrors()) {
			return ResultGenerator.genFailResult(bindingResult.getFieldError().getDefaultMessage());
		}
		if (StringUtils.isBlank(param.getId())) {
			return ResultGenerator.genFailResult("无效的表单ID");
		}

		UnitDepartment unitDepartment = unitDepartmentService.findBy("uuid", param.getId());
		if (unitDepartment == null) {
			return ResultGenerator.genFailResult("无效的表单ID");
		}

		Condition unitPostCondition = new Condition(UnitPost.class);
		unitPostCondition.createCriteria().andEqualTo("prouuid", param.getPid()).andEqualTo("deptuuid", param.getId())
				.andEqualTo("state", 0);
		List<UnitPost> unitPosts = unitPostService.findByCondition(unitPostCondition);
		List<String> postNames = new ArrayList<>();
		if (unitPosts != null && unitPosts.size() > 0) {
			for (UnitPost up : unitPosts) {
				postNames.add(up.getName());
			}
		}
		Map<String, Object> data = new HashMap<>();
		data.put("id", unitDepartment.getUuid());
		data.put("name", unitDepartment.getDeptname());
		data.put("posts", postNames);
		return ResultGenerator.genSuccessResult(data);
	}

	@TokenCheck
	@PostMapping("/update/department")
	public Result<?> updateDepartment(@Validated @RequestBody DepartmentParam model, BindingResult bindingResult,
			HttpServletRequest request) {
		if (bindingResult.hasErrors()) {
			return ResultGenerator.genFailResult(bindingResult.getFieldError().getDefaultMessage());
		}
		String id = model.getId();
		if (StringUtils.isBlank(id)) {
			return ResultGenerator.genFailResult("无效的表单记录");
		}
		String name = model.getName();
		if (StringUtils.isBlank(name)) {
			return ResultGenerator.genFailResult("请输入部门名称");
		}
		name = name.trim();
		UnitDepartment unitDepartment = unitDepartmentService.findBy("uuid", id);
		if (unitDepartment == null) {
			return ResultGenerator.genFailResult("无效的表单记录");
		}

//		Condition condition = new Condition(UnitDepartment.class);
//		condition.createCriteria().andEqualTo("deptuuid", model.getPid()).andEqualTo("deptname", name)
//				.andEqualTo("state", 0);
//		List<UnitDepartment> unitDepartments = unitDepartmentService.findByCondition(condition);
//		if (unitDepartments != null && unitDepartments.size() > 0) {
//			UnitDepartment _unitDepartment = unitDepartments.get(0);
//			if (!_unitDepartment.getUuid().equals(unitDepartment.getUuid())) {
//				return ResultGenerator.genFailResult("当前部门名称已存在");
//			}
//		}

		unitDepartment.setDeptname(name);
		unitDepartment.setUpdatetime(System.currentTimeMillis());
		unitDepartmentService.update(unitDepartment);

		Condition unitPostCondition = new Condition(UnitPost.class);
		unitPostCondition.createCriteria().andEqualTo("deptuuid", unitDepartment.getUuid()).andEqualTo("state", 0);
		List<UnitPost> _unitPosts = unitPostService.findByCondition(unitPostCondition);
		if (_unitPosts == null) {
			_unitPosts = new ArrayList<>();
		}

		String[] posts = model.getPosts();

		if (posts != null && posts.length > 0) {
			Set<String> inserts = new HashSet<>();
			for (String p : posts) {
				if (StringUtils.isBlank(p)) {
					continue;
				}
				p.trim();
				boolean isExist = false;
				for (UnitPost up : _unitPosts) {
					if (up.getName().equals(p)) {
						isExist = true;
					}
				}
				if (!isExist) {
					inserts.add(p);
				}
			}
			if (inserts.size() > 0) {
				for (String post : inserts) {
					if (StringUtils.isBlank(post)) {
						continue;
					}
					post.trim();
					UnitPost up = new UnitPost();
					up.setUuid(UuidUtil.init());
					up.setName(post);
					up.setProuuid(model.getPid());
					up.setDeptuuid(unitDepartment.getUuid());
					up.setState(0);
					unitPostService.save(up);
				}
			}
		}
		Set<UnitPost> removes = new HashSet<>();
		for (UnitPost up : _unitPosts) {
			boolean isExist = false;
			if (posts != null && posts.length > 0) {
				for (String p : posts) {
					if (p.equals(up.getName())) {
						isExist = true;
					}
				}
			}
			if (!isExist) {
				removes.add(up);
			}
		}
		if (removes.size() > 0) {
			for (UnitPost _item : removes) {
				_item.setState(1);
				unitPostService.update(_item);
			}
		}
		return ResultGenerator.genSuccessResult("修改成功");
	}

	@TokenCheck
	@PostMapping("/delete/department")
	public Result<?> deleteDepartment(@Validated @RequestBody DepartmentParam model, BindingResult bindingResult,
			HttpServletRequest request) {
		if (bindingResult.hasErrors()) {
			return ResultGenerator.genFailResult(bindingResult.getFieldError().getDefaultMessage());
		}
		String id = model.getId();
		if (StringUtils.isBlank(id)) {
			return ResultGenerator.genFailResult("无效的表单记录");
		}

		UnitDepartment unitDepartment = unitDepartmentService.findBy("uuid", id);
		if (unitDepartment == null) {
			return ResultGenerator.genFailResult("无效的表单记录");
		}

		Condition condition = new Condition(UnitDepartment.class);
		condition.createCriteria().andEqualTo("parentuuid", unitDepartment.getUuid()).andEqualTo("state", 0);
		List<UnitDepartment> childs = unitDepartmentService.findByCondition(condition);
		if (childs != null && childs.size() > 0) {
			return ResultGenerator.genFailResult("请先删除下级部门");
		}

		unitDepartment.setState(1);
		unitDepartment.setUpdatetime(System.currentTimeMillis());
		unitDepartmentService.update(unitDepartment);

		Condition unitPostCondition = new Condition(UnitPost.class);
		unitPostCondition.createCriteria().andEqualTo("deptuuid", unitDepartment.getUuid()).andEqualTo("state", 0);
		List<UnitPost> _unitPosts = unitPostService.findByCondition(unitPostCondition);
		if (_unitPosts != null && _unitPosts.size() > 0) {
			for (UnitPost _item : _unitPosts) {
				_item.setState(1);
				unitPostService.update(_item);
			}
		}

		return ResultGenerator.genSuccessResult("删除成功");
	}

}
