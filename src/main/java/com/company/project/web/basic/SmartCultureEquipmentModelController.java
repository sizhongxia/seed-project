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
import com.company.project.model.SmartCultureEquipmentType;
import com.company.project.model.param.basic.BasicEquipmentModelParam;
import com.company.project.model.param.basic.BasicRequestParam;
import com.company.project.model.returns.basic.BasicPageResult;
import com.company.project.service.SmartCultureEquipmentModelService;
import com.company.project.service.SmartCultureEquipmentService;
import com.company.project.service.SmartCultureEquipmentTypeService;
import com.company.project.unit.IdUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import cn.hutool.core.date.DateUtil;
import tk.mybatis.mapper.entity.Condition;
import tk.mybatis.mapper.entity.Example.Criteria;
import tk.mybatis.mapper.entity.Example.OrderBy;

@RestController
@RequestMapping("/basic/api/equipmentModel")
public class SmartCultureEquipmentModelController {
	final Logger logger = LoggerFactory.getLogger(SmartCultureEquipmentModelController.class);

	@Resource
	SmartCultureEquipmentModelService smartCultureEquipmentModelService;
	@Resource
	SmartCultureEquipmentTypeService smartCultureEquipmentTypeService;
	@Resource
	SmartCultureEquipmentService smartCultureEquipmentService;

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

		String sv = param.getSearchValue();

		Condition condition = new Condition(SmartCultureEquipmentModel.class);
		Criteria cr = condition.createCriteria();
		if (StringUtils.isNotBlank(sv)) {
			cr.andLike("modelName", String.format("%%%s%%", sv.trim()));
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
		List<SmartCultureEquipmentModel> objs = smartCultureEquipmentModelService.findByCondition(condition);
		if (objs != null && objs.size() > 0) {
			Map<String, Object> map = null;
			for (SmartCultureEquipmentModel obj : objs) {
				map = new HashMap<>();
				map.put("modelId", obj.getModelId());
				map.put("modelName", obj.getModelName());
				map.put("typeId", obj.getEquipmentTypeId());
				map.put("typeName", obj.getEquipmentTypeName());
				map.put("supplier", obj.getSupplier());
				map.put("remark", obj.getRemark());
				map.put("createAt", DateUtil.format(obj.getCreateAt(), "yyyy-MM-dd HH:mm:ss"));
				map.put("updateAt", DateUtil.format(obj.getUpdateAt(), "yyyy-MM-dd HH:mm:ss"));
				list.add(map);
			}
			Page<SmartCultureEquipmentModel> pageInfo = (Page<SmartCultureEquipmentModel>) objs;
			total = pageInfo.getTotal();
		}

		result.setTotal(total);
		result.setList(list);
		return ResultGenerator.genSuccessResult(result);
	}

	@SmartCultureTokenCheck
	@PostMapping("/upinsert")
	public Result<?> upinsert(HttpServletRequest request, @RequestBody BasicEquipmentModelParam param) {
		if (StringUtils.isBlank(param.getModelName())) {
			return ResultGenerator.genFailResult("E5001");
		}
		if (StringUtils.isBlank(param.getTypeId())) {
			return ResultGenerator.genFailResult("E5002");
		}
		SmartCultureEquipmentType typeObj = smartCultureEquipmentTypeService.findBy("typeId", param.getTypeId());
		if (typeObj == null) {
			return ResultGenerator.genFailResult("E5003");
		}
		String equipmentModelId = param.getModelId();
		SmartCultureEquipmentModel equipmentModel = null;
		if (StringUtils.isBlank(equipmentModelId)) {
			// 新增
			equipmentModel = new SmartCultureEquipmentModel();
			equipmentModel.setModelId(IdUtils.initObjectId());
			equipmentModel.setVersion(1L);
			equipmentModel.setCreateAt(new Date());
		} else {
			equipmentModel = smartCultureEquipmentModelService.findBy("modelId", param.getModelId());
			if (equipmentModel == null) {
				return ResultGenerator.genFailResult("E5004");
			}
			equipmentModel.setVersion(equipmentModel.getVersion() + 1);
		}
		equipmentModel.setModelName(param.getModelName().trim());
		equipmentModel.setEquipmentTypeId(typeObj.getTypeId());
		equipmentModel.setEquipmentTypeName(typeObj.getTypeName());
		equipmentModel.setSupplier(param.getSupplier() == null ? "" : param.getSupplier().trim());
		equipmentModel.setRemark(param.getRemark() == null ? "" : param.getRemark().trim());
		equipmentModel.setUpdateAt(new Date());
		if (equipmentModel.getId() != null && equipmentModel.getId().longValue() > 0) {
			smartCultureEquipmentModelService.update(equipmentModel);
		} else {
			smartCultureEquipmentModelService.save(equipmentModel);
		}
		return ResultGenerator.genSuccessResult();
	}

	@SmartCultureTokenCheck
	@PostMapping("/delete")
	public Result<?> delete(HttpServletRequest request, @RequestBody BasicRequestParam param) {
		if (StringUtils.isBlank(param.getResultId())) {
			return ResultGenerator.genFailResult("E5001");
		}
		SmartCultureEquipmentModel equipmentModel = smartCultureEquipmentModelService.findBy("modelId",
				param.getResultId());
		if (equipmentModel == null) {
			return ResultGenerator.genFailResult("E5002");
		}
		Condition condition = new Condition(SmartCultureEquipment.class);
		condition.createCriteria().andEqualTo("equipmentModelId", equipmentModel.getModelId());
		List<SmartCultureEquipment> ces = smartCultureEquipmentService.findByCondition(condition);
		if (ces != null && ces.size() > 0) {
			return ResultGenerator.genFailResult("E5003");
		}
		smartCultureEquipmentModelService.deleteById(equipmentModel.getId());
		return ResultGenerator.genSuccessResult();
	}

	@SmartCultureTokenCheck
	@PostMapping("/all")
	public Result<?> all(HttpServletRequest request, @RequestBody BasicEquipmentModelParam param) {
		List<Map<String, Object>> list = new ArrayList<>();
		Condition condition = new Condition(SmartCultureEquipmentModel.class);
		if (StringUtils.isNotBlank(param.getTypeId())) {
			condition.createCriteria().andEqualTo("equipmentTypeId", param.getTypeId().trim());
		}
		condition.orderBy("modelName").asc();
		List<SmartCultureEquipmentModel> objs = smartCultureEquipmentModelService.findByCondition(condition);
		if (objs != null && objs.size() > 0) {
			Map<String, Object> map = null;
			for (SmartCultureEquipmentModel obj : objs) {
				map = new HashMap<>();
				map.put("modelId", obj.getModelId());
				map.put("modelName", obj.getModelName());
				list.add(map);
			}
		}
		return ResultGenerator.genSuccessResult(list);
	}
}
