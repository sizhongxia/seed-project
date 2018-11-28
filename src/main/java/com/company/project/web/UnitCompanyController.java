package com.company.project.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
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
import com.company.project.model.UnitCompany;
import com.company.project.model.om.UnitCompanyModel;
import com.company.project.model.param.CompanySearchParam;
import com.company.project.model.returns.Pagination;
import com.company.project.service.UnitCompanyService;
import com.company.project.unit.IdUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import tk.mybatis.mapper.entity.Condition;
import tk.mybatis.mapper.entity.Example.Criteria;

@RestController
@RequestMapping("/apiv0/unit/company")
public class UnitCompanyController {
	private Logger logger = LoggerFactory.getLogger(UnitCompanyController.class.getName());

	@Resource
	private UnitCompanyService unitCompanyService;
	@Autowired
	private QiniuConstant qiniuConstant;

	@TokenCheck
	@PostMapping("/data")
	public Result<?> data(@RequestBody CompanySearchParam param) {
		logger.info("company search param:{}", param.toString());
		Condition condition = new Condition(UnitCompany.class);
		Criteria criteria = condition.createCriteria().andEqualTo("state", 0);
		String keyword = param.getKeyword();
		if (StringUtils.isNotBlank(keyword)) {
			criteria.andLike("companyname", String.format("%%%s%%", keyword.trim()));
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
		Page<UnitCompany> _list = (Page<UnitCompany>) unitCompanyService.findByCondition(condition);

		List<UnitCompany> result = _list.getResult();
		List<Map<String, Object>> list = new ArrayList<>();
		if (result != null && result.size() > 0) {
			Map<String, Object> item = null;
			for (UnitCompany i : result) {
				item = new HashMap<>();
				item.put("id", i.getUuid());
				String logo = StringUtils.isBlank(i.getLogo())
						? "https://gw.alipayobjects.com/zos/rmsportal/WdGqmHpayyMjiEhcKoVE.png"
						: String.format("%s%s%s", qiniuConstant.getPath(), i.getLogo(), "-200x200");
				item.put("logo", logo);
				item.put("name", i.getCompanyname());
				item.put("shortName", i.getShortname());
				item.put("type", i.getType().toString());
				item.put("typeName", i.getTypename());
				item.put("description", i.getDescription());
				item.put("officialwebsite", i.getOfficialwebsite());
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
	@PostMapping("/getCompanyOptions")
	public Result<?> getCompanyOptions(@RequestBody CompanySearchParam param) {
		logger.info("company search param:{}", param.toString());
		Condition condition = new Condition(UnitCompany.class);
		Criteria criteria = condition.createCriteria().andEqualTo("state", 0);

		List<Map<String, Object>> list = new ArrayList<>();

		// 1 建设2施工3设计4监理5 勘察6劳务分包商7公司集团8设备供应商9设备代理商
		String type = param.getType();
		if (NumberUtils.isDigits(type)) {
			criteria.andEqualTo("type", Integer.parseInt(type.trim()));
		}

		condition.setOrderByClause("companyname asc");

		List<UnitCompany> result = unitCompanyService.findByCondition(condition);
		if (result != null && result.size() > 0) {
			Map<String, Object> item = null;
			for (UnitCompany i : result) {
				item = new HashMap<>();
				item.put("key", i.getUuid());
				item.put("name", i.getCompanyname());
				item.put("type", i.getType());
				item.put("typename", i.getTypename());
				list.add(item);
			}
		}
		return ResultGenerator.genSuccessResult(list);
	}

	@TokenCheck
	@PostMapping("/getById")
	public Result<?> getById(@RequestBody UnitCompanyModel model) {

		if (StringUtils.isBlank(model.getUuid())) {
			return ResultGenerator.genFailResult("无效的表单");
		}

		UnitCompany unitCompany = unitCompanyService.findBy("uuid", model.getUuid());
		if (unitCompany == null) {
			return ResultGenerator.genFailResult("无效的表单");
		}

		Map<String, Object> item = new HashMap<>();
		item.put("id", unitCompany.getUuid());
		String logo = StringUtils.isBlank(unitCompany.getLogo())
				? "https://gw.alipayobjects.com/zos/rmsportal/WdGqmHpayyMjiEhcKoVE.png"
				: String.format("%s%s%s", qiniuConstant.getPath(), unitCompany.getLogo(), "-200x200");
		item.put("logo", logo);
		item.put("name", unitCompany.getCompanyname());
		item.put("shortName", unitCompany.getShortname());
		item.put("type", unitCompany.getType().toString());
		item.put("typeName", unitCompany.getTypename());
		item.put("description", unitCompany.getDescription());
		item.put("officialwebsite", unitCompany.getOfficialwebsite());
		return ResultGenerator.genSuccessResult(item);
	}

	@TokenCheck
	@PostMapping("/deleteById")
	public Result<?> deleteById(@RequestBody UnitCompanyModel model) {

		if (StringUtils.isBlank(model.getUuid())) {
			return ResultGenerator.genFailResult("无效的表单");
		}

		UnitCompany unitCompany = unitCompanyService.findBy("uuid", model.getUuid());
		if (unitCompany == null) {
			return ResultGenerator.genFailResult("无效的表单");
		}

		unitCompany.setState(1);
		unitCompany.setUpdatetime(System.currentTimeMillis());
		unitCompanyService.update(unitCompany);

		return ResultGenerator.genSuccessResult();
	}

	@TokenCheck
	@PostMapping("/save")
	public Result<?> save(@Validated @RequestBody UnitCompanyModel model, BindingResult bindingResult,
			HttpServletRequest request) {
		if (bindingResult.hasErrors()) {
			return ResultGenerator.genFailResult(bindingResult.getFieldError().getDefaultMessage());
		}
		Condition condition = new Condition(UnitCompany.class);
		condition.createCriteria().andEqualTo("companyname", model.getCompanyname()).andEqualTo("type",
				Integer.parseInt(model.getType().trim()));
		List<UnitCompany> unitCompanys = unitCompanyService.findByCondition(condition);
		if (unitCompanys == null || unitCompanys.isEmpty()) {
			UnitCompany unitCompany = new UnitCompany();
			unitCompany.setAddtime(System.currentTimeMillis());
			unitCompany.setCompanyname(model.getCompanyname());
			unitCompany.setDescription(model.getDescription());
			unitCompany.setLogo("");
			unitCompany.setOfficialwebsite(model.getOfficialwebsite());
			unitCompany.setShortname(model.getShortname());
			unitCompany.setState(0);
			unitCompany.setSuperior(model.getSuperior());
			String typeName = getTypeName(model.getType());
			if (typeName == null) {
				return ResultGenerator.genFailResult("无效的企业类型");
			}
			unitCompany.setType(new Integer(model.getType()));
			unitCompany.setTypename(typeName);
			unitCompany.setUpdatetime(System.currentTimeMillis());
			unitCompany.setUuid(IdUtils.initUuid());
			unitCompanyService.save(unitCompany);
		} else {
			UnitCompany unitCompany = unitCompanys.get(0);
			unitCompany.setShortname(model.getShortname());
			unitCompany.setState(0);
			unitCompany.setUpdatetime(System.currentTimeMillis());
			unitCompanyService.update(unitCompany);
		}
		return ResultGenerator.genSuccessResult();
	}

	@TokenCheck
	@PostMapping("/update")
	public Result<?> update(@Validated @RequestBody UnitCompanyModel model, BindingResult bindingResult,
			HttpServletRequest request) {
		if (bindingResult.hasErrors()) {
			return ResultGenerator.genFailResult(bindingResult.getFieldError().getDefaultMessage());
		}

		if (StringUtils.isBlank(model.getUuid())) {
			return ResultGenerator.genFailResult("无效的表单");
		}

		UnitCompany unitCompany = unitCompanyService.findBy("uuid", model.getUuid());
		if (unitCompany == null) {
			return ResultGenerator.genFailResult("无效的表单");
		}

		Condition condition = new Condition(UnitCompany.class);
		condition.createCriteria().andEqualTo("companyname", model.getCompanyname()).andEqualTo("type",
				Integer.parseInt(model.getType().trim()));
		List<UnitCompany> unitCompanys = unitCompanyService.findByCondition(condition);

		if (unitCompanys != null && unitCompanys.size() > 0) {
			UnitCompany _unitCompany = unitCompanys.get(0);
			if (_unitCompany != null && !_unitCompany.getUuid().equals(unitCompany.getUuid())) {
				return ResultGenerator.genFailResult("当前公司名称已存在");
			}
		}

		unitCompany.setCompanyname(model.getCompanyname());
		unitCompany.setDescription(model.getDescription());
		unitCompany.setOfficialwebsite(model.getOfficialwebsite());
		unitCompany.setShortname(model.getShortname());
		unitCompany.setSuperior(model.getSuperior());
		String typeName = getTypeName(model.getType());
		if (typeName == null) {
			return ResultGenerator.genFailResult("无效的企业类型");
		}
		unitCompany.setType(new Integer(model.getType()));
		unitCompany.setTypename(typeName);
		unitCompany.setUpdatetime(System.currentTimeMillis());
		unitCompanyService.update(unitCompany);
		return ResultGenerator.genSuccessResult();
	}

	/**
	 * @param type
	 * @return
	 */
	private String getTypeName(String type) {
		switch (type) {
		case "1":
			return "建设单位";
		case "2":
			return "施工单位";
		case "3":
			return "设计单位";
		case "4":
			return "监理单位";
		case "5":
			return "勘察单位";
		case "6":
			return "劳务分包商";
		case "7":
			return "集团公司";
		case "8":
			return "设备供应商";
		case "9":
			return "设备代理商";
		default:
			return null;
		}
	}
}
