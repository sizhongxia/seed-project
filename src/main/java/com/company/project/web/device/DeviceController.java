package com.company.project.web.device;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.project.annotation.TokenCheck;
import com.company.project.configurer.QiniuConstant;
import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.model.EquipmentAftersale;
import com.company.project.model.EquipmentBasics;
import com.company.project.model.UnitCompany;
import com.company.project.model.UnitProject;
import com.company.project.model.om.DeviceDustNoiseModel;
import com.company.project.model.param.DeviceSearchParam;
import com.company.project.model.param.ProjectDeviceParam;
import com.company.project.model.returns.Pagination;
import com.company.project.service.EquipmentAftersaleService;
import com.company.project.service.EquipmentBasicsService;
import com.company.project.service.UnitCompanyService;
import com.company.project.service.UnitProjectService;
import com.company.project.unit.UtcDateParseUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xiaoleilu.hutool.date.DateUtil;

import tk.mybatis.mapper.entity.Condition;
import tk.mybatis.mapper.entity.Example.Criteria;

@RestController
@RequestMapping("/apiv0/device")
public class DeviceController {
	private Logger logger = LoggerFactory.getLogger(DustNoiseController.class.getName());
	@Resource
	private EquipmentBasicsService equipmentBasicsService;
	@Resource
	private UnitCompanyService unitCompanyService;
	@Resource
	private UnitProjectService unitProjectService;
	@Resource
	private EquipmentAftersaleService equipmentAftersaleService;
	@Autowired
	private QiniuConstant qiniuConstant;

	@TokenCheck
	@PostMapping("/data")
	public Result<?> data(@RequestBody DeviceSearchParam param) {
		logger.info("device search param:{}", param.toString());

		Condition condition = new Condition(EquipmentBasics.class);
		Criteria criteria = condition.createCriteria();
		criteria.andEqualTo("state", 0);

		String type = param.getType();
		if (NumberUtils.isDigits(type)) {
			criteria.andEqualTo("type", Integer.parseInt(type.trim()));
		} else {
			return ResultGenerator.genFailResult("请传入类型参数");
		}

		String name = param.getName();
		if (StringUtils.isNotBlank(name)) {
			criteria.andLike("name", String.format("%%%s%%", name.trim()));
		}
		String equipmentNo = param.getEquipmentNo();
		if (StringUtils.isNotBlank(equipmentNo)) {
			criteria.andLike("equipmentno", String.format("%%%s%%", equipmentNo.trim()));
		}
		String[] lastOnLineTime = param.getLastOnLineTime();
		if (lastOnLineTime != null && lastOnLineTime.length == 2) {
			Date start = UtcDateParseUtil.parse(lastOnLineTime[0]);
			if (start != null) {
				criteria.andGreaterThanOrEqualTo("lastonlinetime", start.getTime());
			}
			Date end = UtcDateParseUtil.parse(lastOnLineTime[1]);
			if (end != null) {
				criteria.andLessThanOrEqualTo("lastonlinetime", end.getTime());
			}
		}

		Integer page = param.getPage();
		if (page == null || page.intValue() < 1) {
			page = 1;
		}
		Integer size = param.getSize();
		if (size == null || size.intValue() < 1) {
			size = 10;
		}

		condition.setOrderByClause("addtime asc, id asc");
		PageHelper.startPage(page, size);
		Page<EquipmentBasics> _list = (Page<EquipmentBasics>) equipmentBasicsService.findByCondition(condition);
		List<EquipmentBasics> result = _list.getResult();
		List<Map<String, Object>> list = new ArrayList<>();
		if (result != null && result.size() > 0) {
			Map<String, Object> item = null;
			UnitCompany company = null;
			for (EquipmentBasics i : result) {
				item = new HashMap<>();
				item.put("key", i.getUuid());
				item.put("name", i.getName());
				item.put("equipmentno", "-");
				if (StringUtils.isNotBlank(i.getEquipmentno())) {
					item.put("equipmentno", i.getEquipmentno());
				}
				item.put("agentcompany", "-");
				if (StringUtils.isNotBlank(i.getAgentcompanyuuid())) {
					company = unitCompanyService.findBy("uuid", i.getAgentcompanyuuid());
					if (company != null) {
						item.put("agentcompany", company.getCompanyname());
					}
				}
				item.put("suppliercompany", "-");
				if (StringUtils.isNotBlank(i.getSuppliercompanyuuid())) {
					company = unitCompanyService.findBy("uuid", i.getSuppliercompanyuuid());
					if (company != null) {
						item.put("suppliercompany", company.getCompanyname());
					}
				}
				item.put("proname", "-");
				UnitProject unitProject = unitProjectService.findBy("uuid", i.getProuuid());
				if (unitProject != null) {
					item.put("proname", unitProject.getName());
				}

				if (i.getLastonlinetime() != null && i.getLastonlinetime().longValue() > 0) {
					item.put("lastonlinetime", DateUtil.format(new Date(i.getLastonlinetime()), "yyyy-MM-dd HH:mm:ss"));
				} else {
					item.put("lastonlinetime", "-");
				}
				item.put("addtime", DateUtil.format(new Date(i.getAddtime()), "yyyy-MM-dd HH:mm"));
				item.put("updatetime", DateUtil.format(new Date(i.getUpdatetime()), "yyyy-MM-dd HH:mm"));
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
	@PostMapping("/getReceiptByUuid")
	public Result<?> getReceiptByUuid(@RequestBody DeviceDustNoiseModel model) {
		if (StringUtils.isBlank(model.getUuid())) {
			return ResultGenerator.genFailResult("无效的表单");
		}
		EquipmentAftersale aftersale = equipmentAftersaleService.findBy("uuid", model.getUuid());
		String receipt = "";
		if (aftersale != null) {
			receipt = StringUtils.isBlank(aftersale.getReceipt()) ? ""
					: String.format("%s%s", qiniuConstant.getPath(), aftersale.getReceipt());
		}
		return ResultGenerator.genSuccessResult(receipt);
	}

	@TokenCheck
	@PostMapping("/resetReceiptByUuid")
	public Result<?> resetReceiptByUuid(@RequestBody DeviceDustNoiseModel model) {
		if (StringUtils.isBlank(model.getUuid())) {
			return ResultGenerator.genFailResult("无效的表单");
		}
		EquipmentAftersale aftersale = equipmentAftersaleService.findBy("uuid", model.getUuid());
		aftersale.setReceipt("");
		equipmentAftersaleService.update(aftersale);
		return ResultGenerator.genSuccessResult();
	}

	@TokenCheck
	@PostMapping("/getProjectDeviceOptions")
	public Result<?> getProjectDeviceOptions(@RequestBody ProjectDeviceParam param) {
		List<Map<String, Object>> list = new ArrayList<>();
		if (StringUtils.isBlank(param.getProUuid())) {
			return ResultGenerator.genSuccessResult(list);
		}
		if (!NumberUtils.isParsable(param.getDeviceType())) {
			return ResultGenerator.genSuccessResult(list);
		}
		Condition condition = new Condition(EquipmentBasics.class);
		condition.createCriteria().andEqualTo("state", 0).andEqualTo("prouuid", param.getProUuid().trim())
				.andEqualTo("type", Integer.parseInt(param.getDeviceType().trim()));
		condition.setOrderByClause("name asc");
		List<EquipmentBasics> result = equipmentBasicsService.findByCondition(condition);
		if (result != null && result.size() > 0) {
			Map<String, Object> item = null;
			for (EquipmentBasics i : result) {
				item = new HashMap<>();
				item.put("key", i.getUuid());
				item.put("name", i.getName());
				list.add(item);
			}
		}
		return ResultGenerator.genSuccessResult(list);
	}
}
