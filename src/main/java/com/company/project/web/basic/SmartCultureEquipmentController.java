package com.company.project.web.basic;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.project.annotation.SmartCultureTokenCheck;
import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.model.SmartCultureEquipment;
import com.company.project.model.SmartCultureEquipmentModel;
import com.company.project.model.SmartCultureFarm;
import com.company.project.model.SmartCultureFarmArea;
import com.company.project.model.param.basic.BasicEquipmentParam;
import com.company.project.model.param.basic.BasicRequestParam;
import com.company.project.model.returns.basic.BasicPageResult;
import com.company.project.service.SmartCultureEquipmentModelService;
import com.company.project.service.SmartCultureEquipmentService;
import com.company.project.service.SmartCultureEquipmentTypeService;
import com.company.project.service.SmartCultureFarmAreaService;
import com.company.project.service.SmartCultureFarmService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import tk.mybatis.mapper.entity.Condition;
import tk.mybatis.mapper.entity.Example.Criteria;
import tk.mybatis.mapper.entity.Example.OrderBy;

@RestController
@RequestMapping("/basic/api/equipment")
public class SmartCultureEquipmentController {
	final Logger logger = LoggerFactory.getLogger(SmartCultureEquipmentController.class);

	@Resource
	SmartCultureEquipmentService smartCultureEquipmentService;
	@Resource
	SmartCultureFarmService smartCultureFarmService;
	@Resource
	SmartCultureEquipmentTypeService smartCultureEquipmentTypeService;
	@Resource
	SmartCultureEquipmentModelService smartCultureEquipmentModelService;
	@Resource
	SmartCultureFarmAreaService smartCultureFarmAreaService;

	@SmartCultureTokenCheck
	@PostMapping("/list")
	public Result<?> list(HttpServletRequest request, @RequestBody BasicRequestParam param) {
		BasicPageResult result = new BasicPageResult();
		List<Map<String, Object>> list = new ArrayList<>();
		String page = param.getPage();
		int current = 1;
		if (NumberUtils.isParsable(page)) {
			current = Integer.parseInt(page);
		}
		result.setCurrent(current);
		String size = param.getSize();
		int sizeNum = 10;
		if (NumberUtils.isParsable(size)) {
			sizeNum = Integer.parseInt(size);
		}
		result.setSize(sizeNum);

		Condition condition = new Condition(SmartCultureEquipment.class);
		Criteria cr = condition.createCriteria();
		String name = param.getName();
		if (StringUtils.isNotBlank(name)) {
			cr.andLike("equipmentName", String.format("%%%s%%", name.trim()));
		}
		String code = param.getCode();
		if (StringUtils.isNotBlank(code)) {
			cr.andLike("equipmentCode", String.format("%%%s%%", code.trim()));
		}
		String sn = param.getSn();
		if (StringUtils.isNotBlank(sn)) {
			cr.andLike("equipmentSn", String.format("%%%s%%", sn.trim()));
		}
		if (StringUtils.isNotBlank(param.getOrderField())) {
			OrderBy ob = condition.orderBy(param.getOrderField());
			if ("desc".equals(param.getOrderType())) {
				ob.desc();
			} else {
				ob.asc();
			}
		}
		long total = 0;
		PageHelper.startPage(current, sizeNum, true);
		List<SmartCultureEquipment> objs = smartCultureEquipmentService.findByCondition(condition);
		if (objs != null && objs.size() > 0) {
			Map<String, Object> map = null;
			for (SmartCultureEquipment obj : objs) {
				map = new HashMap<>();
				map.put("equipmentId", obj.getEquipmentId());
				map.put("equipmentName", obj.getEquipmentName());
				map.put("equipmentCode", obj.getEquipmentCode());
				map.put("equipmentSn", obj.getEquipmentSn());
				map.put("typeName", obj.getEquipmentTypeName());
				map.put("modelName", obj.getEquipmentModelName());
				map.put("operator", obj.getOperator());
				map.put("remark", obj.getRemark());
				map.put("createAt", DateUtil.format(obj.getCreateAt(), "yyyy-MM-dd HH:mm:ss"));
				map.put("updateAt", DateUtil.format(obj.getUpdateAt(), "yyyy-MM-dd HH:mm:ss"));
				list.add(map);
			}
			Page<SmartCultureEquipment> pageInfo = (Page<SmartCultureEquipment>) objs;
			total = pageInfo.getTotal();
		}

		result.setTotal(total);
		result.setList(list);
		return ResultGenerator.genSuccessResult(result);
	}

	@SmartCultureTokenCheck
	@PostMapping("/farmAll")
	public Result<?> farmAll(HttpServletRequest request, @RequestBody BasicRequestParam param) {
		List<Map<String, Object>> list = new ArrayList<>();
		if (StringUtils.isBlank(param.getFarmId())) {
			return ResultGenerator.genSuccessResult(list);
		}
		Condition condition = new Condition(SmartCultureEquipment.class);
		condition.createCriteria().andEqualTo("farmId", param.getFarmId());
		condition.orderBy("equipmentName").asc();
		List<SmartCultureEquipment> objs = smartCultureEquipmentService.findByCondition(condition);
		if (objs != null && objs.size() > 0) {
			Map<String, Object> map = null;
			for (SmartCultureEquipment obj : objs) {
				map = new HashMap<>();
				map.put("equipmentId", obj.getEquipmentId());
				map.put("equipmentName", obj.getEquipmentName());
				map.put("equipmentCode", obj.getEquipmentCode());
				map.put("equipmentSn", obj.getEquipmentSn());
				map.put("typeId", obj.getEquipmentTypeId());
				map.put("typeName", obj.getEquipmentTypeName());
				map.put("modelId", obj.getEquipmentModelId());
				map.put("modelName", obj.getEquipmentModelName());
				list.add(map);
			}
		}
		return ResultGenerator.genSuccessResult(list);
	}

	@SmartCultureTokenCheck
	@PostMapping("/upinsert")
	public Result<?> upinsert(HttpServletRequest request, @RequestBody BasicEquipmentParam param) {
		if (StringUtils.isBlank(param.getFarmId())) {
			return ResultGenerator.genFailResult("E5000");
		}
		if (StringUtils.isBlank(param.getEquipmentName())) {
			return ResultGenerator.genFailResult("E5001");
		}
		if (StringUtils.isBlank(param.getEquipmentSn())) {
			return ResultGenerator.genFailResult("E5002");
		}
		if (StringUtils.isBlank(param.getEquipmentCode())) {
			return ResultGenerator.genFailResult("E5003");
		}
		String equipmentId = param.getEquipmentId();
		SmartCultureEquipment equipment = smartCultureEquipmentService.findBy("equipmentCode",
				param.getEquipmentCode().trim());
		if (equipment != null && !equipment.getEquipmentId().equals(equipmentId)) {
			return ResultGenerator.genFailResult("E5004");
		}
		// equipment = smartCultureEquipmentService.findBy("equipmentSn",
		// param.getEquipmentSn().trim());
		// if (equipment != null && !equipment.getEquipmentId().equals(equipmentId)) {
		// return ResultGenerator.genFailResult("E5005");
		// }
		if (StringUtils.isBlank(equipmentId)) {
			// 新增
			equipment = new SmartCultureEquipment();
			equipment.setEquipmentId(IdUtil.objectId());
			SmartCultureFarm cf = smartCultureFarmService.findBy("farmId", param.getFarmId().trim());
			if (cf == null) {
				return ResultGenerator.genFailResult("E5003");
			}
			equipment.setFarmId(cf.getFarmId());
			equipment.setVersion(1L);
			equipment.setCreateAt(new Date());
			equipment.setUpdateAt(new Date());
		} else {
			equipment = smartCultureEquipmentService.findBy("equipmentId", param.getEquipmentId());
			if (equipment == null) {
				return ResultGenerator.genFailResult("E5006");
			}
			equipment.setVersion(equipment.getVersion() + 1);
			equipment.setUpdateAt(new Date());
		}
		equipment.setFarmAreaId(param.getFarmAreaId() == null ? "" : param.getFarmAreaId().trim());
		equipment.setEquipmentName(param.getEquipmentName().trim());
		equipment.setEquipmentCode(param.getEquipmentCode().trim());
		equipment.setEquipmentSn(param.getEquipmentSn().trim());

		SmartCultureEquipmentModel ems = smartCultureEquipmentModelService.findBy("modelId", param.getModelId());
		if (ems == null) {
			return ResultGenerator.genFailResult("E5007");
		}

		equipment.setEquipmentTypeId(ems.getEquipmentTypeId());
		equipment.setEquipmentTypeName(ems.getEquipmentTypeName());
		equipment.setEquipmentModelId(ems.getModelId());
		equipment.setEquipmentModelName(ems.getModelName());
		equipment.setOperator(param.getOperator() == null ? "" : param.getOperator().trim());
		equipment.setRemark(param.getRemark() == null ? "" : param.getRemark().trim());

		if (equipment.getId() != null && equipment.getId().longValue() > 0) {
			smartCultureEquipmentService.update(equipment);
		} else {
			smartCultureEquipmentService.save(equipment);
		}
		return ResultGenerator.genSuccessResult();
	}

	@SmartCultureTokenCheck
	@PostMapping("/detail")
	public Result<?> detail(HttpServletRequest request, @RequestBody BasicRequestParam param) {
		if (StringUtils.isBlank(param.getResultId())) {
			return ResultGenerator.genFailResult("E5001");
		}
		SmartCultureEquipment equipment = smartCultureEquipmentService.findBy("equipmentId", param.getResultId());
		if (equipment == null) {
			return ResultGenerator.genFailResult("E5002");
		}
		Map<String, Object> map = new HashMap<>();
		map.put("equipmentId", equipment.getEquipmentId());
		map.put("equipmentName", equipment.getEquipmentName());
		map.put("equipmentCode", equipment.getEquipmentCode());
		map.put("equipmentSn", equipment.getEquipmentSn());
		map.put("typeId", equipment.getEquipmentTypeId());
		map.put("typeName", equipment.getEquipmentTypeName());
		map.put("modelId", equipment.getEquipmentModelId());
		map.put("modelName", equipment.getEquipmentModelName());
		SmartCultureFarm cf = smartCultureFarmService.findBy("farmId", equipment.getFarmId());
		if (cf == null) {
			return ResultGenerator.genFailResult("E5003");
		}
		map.put("farmId", cf.getFarmId());
		map.put("farmName", cf.getFarmName());
		map.put("farmAreaId", "");
		map.put("farmAreaName", "");
		if (StringUtils.isNotBlank(equipment.getFarmAreaId())) {
			SmartCultureFarmArea fa = smartCultureFarmAreaService.findBy("areaId", equipment.getFarmAreaId());
			if (fa == null) {
				return ResultGenerator.genFailResult("E5004");
			}
			map.put("farmAreaId", fa.getAreaId());
			map.put("farmAreaName", fa.getAreaName());
		}
		map.put("operator", equipment.getOperator());
		map.put("remark", equipment.getRemark());
		return ResultGenerator.genSuccessResult(map);
	}

	@SmartCultureTokenCheck
	@PostMapping("/delete")
	public Result<?> delete(HttpServletRequest request, @RequestBody BasicRequestParam param) {
		if (StringUtils.isBlank(param.getResultId())) {
			return ResultGenerator.genFailResult("E5001");
		}
		SmartCultureEquipment equipment = smartCultureEquipmentService.findBy("equipmentId", param.getResultId());
		if (equipment == null) {
			return ResultGenerator.genFailResult("E5002");
		}
		smartCultureEquipmentService.deleteById(equipment.getId());
		return ResultGenerator.genSuccessResult();
	}
}
