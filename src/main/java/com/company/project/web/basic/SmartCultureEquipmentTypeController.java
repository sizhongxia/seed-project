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
import com.company.project.model.SmartCultureEquipmentModel;
import com.company.project.model.SmartCultureEquipmentType;
import com.company.project.model.param.basic.BasicEquipmentTypeParam;
import com.company.project.model.param.basic.BasicRequestParam;
import com.company.project.model.returns.basic.BasicPageResult;
import com.company.project.service.SmartCultureEquipmentModelService;
import com.company.project.service.SmartCultureEquipmentTypeService;
import com.company.project.unit.IdUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import cn.hutool.core.date.DateUtil;
import tk.mybatis.mapper.entity.Condition;
import tk.mybatis.mapper.entity.Example.Criteria;
import tk.mybatis.mapper.entity.Example.OrderBy;

@RestController
@RequestMapping("/basic/api/equipmentType")
public class SmartCultureEquipmentTypeController {
	final Logger logger = LoggerFactory.getLogger(SmartCultureEquipmentTypeController.class);

	@Resource
	SmartCultureEquipmentTypeService smartCultureEquipmentTypeService;
	@Resource
	SmartCultureEquipmentModelService smartCultureEquipmentModelService;

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

		Condition condition = new Condition(SmartCultureEquipmentType.class);
		Criteria cr = condition.createCriteria();
		if (StringUtils.isNotBlank(sv)) {
			cr.andLike("typeName", String.format("%%%s%%", sv.trim()));
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
		List<SmartCultureEquipmentType> objs = smartCultureEquipmentTypeService.findByCondition(condition);
		if (objs != null && objs.size() > 0) {
			Map<String, Object> map = null;
			for (SmartCultureEquipmentType obj : objs) {
				map = new HashMap<>();
				map.put("typeId", obj.getTypeId());
				map.put("typeName", obj.getTypeName());
				map.put("createAt", DateUtil.format(obj.getCreateAt(), "yyyy-MM-dd HH:mm:ss"));
				map.put("updateAt", DateUtil.format(obj.getUpdateAt(), "yyyy-MM-dd HH:mm:ss"));
				list.add(map);
			}
			Page<SmartCultureEquipmentType> pageInfo = (Page<SmartCultureEquipmentType>) objs;
			total = pageInfo.getTotal();
		}

		result.setTotal(total);
		result.setList(list);
		return ResultGenerator.genSuccessResult(result);
	}

	@SmartCultureTokenCheck
	@PostMapping("/upinsert")
	public Result<?> upinsert(HttpServletRequest request, @RequestBody BasicEquipmentTypeParam param) {
		String equipmentTypeId = param.getTypeId();
		if (StringUtils.isBlank(equipmentTypeId)) {
			// 新增
			Condition condition = new Condition(SmartCultureEquipmentType.class);
			condition.createCriteria().andEqualTo("typeName", param.getTypeName());
			List<SmartCultureEquipmentType> scos = smartCultureEquipmentTypeService.findByCondition(condition);
			if (scos != null && scos.size() > 0) {
				return ResultGenerator.genFailResult("E5001");
			}
			SmartCultureEquipmentType equipmentType = new SmartCultureEquipmentType();
			equipmentType.setTypeId(IdUtils.initObjectId());
			equipmentType.setTypeName(param.getTypeName());
			equipmentType.setVersion(1L);
			equipmentType.setCreateAt(new Date());
			equipmentType.setUpdateAt(new Date());
			smartCultureEquipmentTypeService.save(equipmentType);
		} else {
			SmartCultureEquipmentType equipmentType = smartCultureEquipmentTypeService.findBy("typeId",
					param.getTypeId());
			if (equipmentType == null) {
				return ResultGenerator.genFailResult("E5002");
			}
			Condition condition = new Condition(SmartCultureEquipmentType.class);
			condition.createCriteria().andEqualTo("typeName", param.getTypeName());
			List<SmartCultureEquipmentType> scos = smartCultureEquipmentTypeService.findByCondition(condition);
			if (scos != null && scos.size() > 0) {
				for (SmartCultureEquipmentType _i : scos) {
					if (!_i.getTypeId().equals(param.getTypeId())) {
						return ResultGenerator.genFailResult("E5003");
					}
				}
			}
			equipmentType.setTypeName(param.getTypeName());
			equipmentType.setVersion(equipmentType.getVersion() + 1);
			equipmentType.setUpdateAt(new Date());
			smartCultureEquipmentTypeService.update(equipmentType);
		}
		return ResultGenerator.genSuccessResult();
	}

	@SmartCultureTokenCheck
	@PostMapping("/delete")
	public Result<?> delete(HttpServletRequest request, @RequestBody BasicRequestParam param) {
		if (StringUtils.isBlank(param.getResultId())) {
			return ResultGenerator.genFailResult("E5001");
		}
		SmartCultureEquipmentType equipmentType = smartCultureEquipmentTypeService.findBy("typeId",
				param.getResultId());
		if (equipmentType == null) {
			return ResultGenerator.genFailResult("E5002");
		}

		Condition condition = new Condition(SmartCultureEquipmentModel.class);
		condition.createCriteria().andEqualTo("equipmentTypeId", equipmentType.getTypeId());
		List<SmartCultureEquipmentModel> ms = smartCultureEquipmentModelService.findByCondition(condition);
		if (ms != null && ms.size() > 0) {
			return ResultGenerator.genFailResult("E5003");
		}

		smartCultureEquipmentTypeService.deleteById(equipmentType.getId());
		return ResultGenerator.genSuccessResult();
	}

	@SmartCultureTokenCheck
	@PostMapping("/all")
	public Result<?> all(HttpServletRequest request) {
		List<Map<String, Object>> list = new ArrayList<>();
		Condition condition = new Condition(SmartCultureEquipmentType.class);
		condition.orderBy("typeName").asc();
		List<SmartCultureEquipmentType> objs = smartCultureEquipmentTypeService.findByCondition(condition);
		if (objs != null && objs.size() > 0) {
			Map<String, Object> map = null;
			for (SmartCultureEquipmentType obj : objs) {
				map = new HashMap<>();
				map.put("typeId", obj.getTypeId());
				map.put("typeName", obj.getTypeName());
				list.add(map);
			}
		}
		return ResultGenerator.genSuccessResult(list);
	}
}
