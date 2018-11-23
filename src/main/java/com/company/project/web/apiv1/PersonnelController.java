package com.company.project.web.apiv1;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
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
import com.company.project.model.PersonnelIdentity;
import com.company.project.model.PersonnelRealnameSystem;
import com.company.project.model.om.PersonnelBaseModel;
import com.company.project.model.om.PersonnelModel;
import com.company.project.model.param.apiv1.PersonnelParam;
import com.company.project.service.PersonnelIdentityService;
import com.company.project.service.PersonnelRealnameSystemService;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdcardUtil;
import tk.mybatis.mapper.entity.Condition;

@RestController
@RequestMapping("/apiv1/personnel")
public class PersonnelController {

	@Resource
	private PersonnelRealnameSystemService personnelRealnameSystemService;
	@Resource
	private PersonnelIdentityService personnelIdentityService;

	@TokenCheck
	@PostMapping("/save")
	public Result<?> save(@Validated @RequestBody PersonnelModel model, BindingResult bindingResult,
			HttpServletRequest request) {
		if (bindingResult.hasErrors()) {
			return ResultGenerator.genFailResult(bindingResult.getFieldError().getDefaultMessage());
		}

		String code = model.getCode();
		if (!IdcardUtil.isValidCard(code)) {
			return ResultGenerator.genFailResult("输入的身份证号无效");
		}

		String type = model.getType();
		if (!NumberUtils.isParsable(type)) {
			return ResultGenerator.genFailResult("无效的人员类型");
		}

		if (Integer.parseInt(type) == 2) {
			// 劳务
			if (StringUtils.isBlank(model.getGroupuuid())) {
				return ResultGenerator.genFailResult("请选择劳务人员班组");
			}
			if (StringUtils.isBlank(model.getWorktype())) {
				return ResultGenerator.genFailResult("请选择劳务人员工种");
			}
		} else if (Integer.parseInt(type) == 1) {
			// 管理
			if (StringUtils.isBlank(model.getDeptuuid())) {
				return ResultGenerator.genFailResult("请选择管理人员部门");
			}
			if (StringUtils.isBlank(model.getPost())) {
				return ResultGenerator.genFailResult("请选择管理人员岗位");
			}
		} else {
			return ResultGenerator.genFailResult("无效的人员类型");
		}

		personnelRealnameSystemService.saveByTransactional(model);
		return ResultGenerator.genSuccessResult("保存成功");
	}

	@TokenCheck
	@PostMapping("/updateBaseInfo")
	public Result<?> update(@Validated @RequestBody PersonnelBaseModel model, BindingResult bindingResult,
			HttpServletRequest request) {
		if (bindingResult.hasErrors()) {
			return ResultGenerator.genFailResult(bindingResult.getFieldError().getDefaultMessage());
		}
		PersonnelRealnameSystem personnel = personnelRealnameSystemService.findBy("empno", model.getEmpno());
		if (personnel == null) {
			return ResultGenerator.genFailResult("无效的记录编号");
		}
		personnel.setName(model.getName());
		personnel.setSex(Integer.parseInt(model.getAge().trim()));
		try {
			personnel.setBirthday(DateUtil.parse(model.getBirthday()));
		} catch (Exception e) {
		}
		personnel.setAge(Integer.parseInt(model.getAge().trim()));
		personnel.setPhoto(model.getPhoto());
		personnel.setNation(model.getNation());
		personnel.setHomeaddress(model.getHomeaddress());
		personnel.setTelephone(model.getTelephone());
		personnel.setManualinput(model.getManualinput());
		personnel.setDatein(model.getDatein());
		personnel.setUpdatetime(System.currentTimeMillis());
		personnelRealnameSystemService.update(personnel);
		return ResultGenerator.genSuccessResult("修改成功");
	}

	@TokenCheck
	@PostMapping("/detail")
	public Result<?> detail(@Validated @RequestBody PersonnelParam param, BindingResult bindingResult,
			HttpServletRequest request) {
		if (bindingResult.hasErrors()) {
			return ResultGenerator.genFailResult(bindingResult.getFieldError().getDefaultMessage());
		}

		String code = param.getCode();
		if (StringUtils.isBlank(code)) {
			return ResultGenerator.genFailResult("无效的人员身份证号");
		}
		PersonnelRealnameSystem personnel = personnelRealnameSystemService.findBy("code", param.getCode());
		if (personnel == null) {
			return ResultGenerator.genFailResult("未找到人员信息");
		}

		Map<String, Object> data = new HashMap<>();
		data.put("empNo", personnel.getEmpno());
		data.put("name", personnel.getName());
		data.put("code", personnel.getCode());
		data.put("sex", personnel.getSex());
		data.put("birthday", DateUtil.format(personnel.getBirthday(), "yyyy-MM-dd"));
		data.put("age", personnel.getAge());
		data.put("birthPlace", personnel.getBirthplace());
		data.put("photo", personnel.getPhoto());
		data.put("nation", personnel.getNation());
		data.put("homeAddress", personnel.getHomeaddress());
		data.put("telephone", personnel.getTelephone());

		Condition condition = new Condition(PersonnelIdentity.class);
		condition.createCriteria().andEqualTo("prouuid", param.getPid()).andEqualTo("empno", personnel.getEmpno())
				.andEqualTo("state", 0);
		List<PersonnelIdentity> identitys = personnelIdentityService.findByCondition(condition);

		data.put("identitys", identitys);

		return ResultGenerator.genSuccessResult(data);
	}
}
