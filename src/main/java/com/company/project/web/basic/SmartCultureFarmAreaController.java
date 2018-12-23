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
import com.company.project.model.SmartCultureFarmArea;
import com.company.project.model.param.basic.BasicFarmAreaParam;
import com.company.project.model.param.basic.BasicRequestParam;
import com.company.project.model.returns.basic.BasicPageResult;
import com.company.project.service.SmartCultureBasicCityService;
import com.company.project.service.SmartCultureFarmAreaService;
import com.company.project.unit.IdUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import cn.hutool.core.date.DateUtil;
import tk.mybatis.mapper.entity.Condition;
import tk.mybatis.mapper.entity.Example.Criteria;
import tk.mybatis.mapper.entity.Example.OrderBy;

@RestController
@RequestMapping("/basic/api/farmArea")
public class SmartCultureFarmAreaController {
	final Logger logger = LoggerFactory.getLogger(SmartCultureFarmAreaController.class);

	@Resource
	SmartCultureFarmAreaService smartCultureFarmAreaService;
	@Resource
	SmartCultureBasicCityService smartCultureBasicCityService;

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

		Condition condition = new Condition(SmartCultureFarmArea.class);
		Criteria cr = condition.createCriteria().andEqualTo("farmId", param.getFarmId());
		String name = param.getName();
		if (StringUtils.isNotBlank(name)) {
			cr.andLike("areaName", String.format("%%%s%%", name.trim()));
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
		List<SmartCultureFarmArea> objs = smartCultureFarmAreaService.findByCondition(condition);
		if (objs != null && objs.size() > 0) {
			Map<String, Object> map = null;
			for (SmartCultureFarmArea obj : objs) {
				map = new HashMap<>();
				map.put("areaId", obj.getAreaId());
				map.put("areaName", obj.getAreaName());
				map.put("areaPosition", obj.getAreaPosition());
				map.put("acreage", obj.getAcreage());
				map.put("areaDescribe", obj.getAreaDescribe());
				map.put("createAt", DateUtil.format(obj.getCreateAt(), "yyyy-MM-dd HH:mm:ss"));
				map.put("updateAt", DateUtil.format(obj.getUpdateAt(), "yyyy-MM-dd HH:mm:ss"));
				list.add(map);
			}
			Page<SmartCultureFarmArea> pageInfo = (Page<SmartCultureFarmArea>) objs;
			total = pageInfo.getTotal();
		}

		result.setTotal(total);
		result.setList(list);
		return ResultGenerator.genSuccessResult(result);
	}

	@SmartCultureTokenCheck
	@PostMapping("/upinsert")
	public Result<?> upinsert(HttpServletRequest request, @RequestBody BasicFarmAreaParam param) {
		String areaId = param.getAreaId();
		Date now = new Date();
		SmartCultureFarmArea farmArea = null;
		if (StringUtils.isBlank(areaId)) {
			// 新增
			farmArea = new SmartCultureFarmArea();
			farmArea.setAreaId(IdUtils.initObjectId());
			farmArea.setFarmId(param.getFarmId());
			farmArea.setVersion(1L);
			farmArea.setCreateAt(now);
			farmArea.setUpdateAt(now);
		} else {
			farmArea = smartCultureFarmAreaService.findBy("areaId", param.getAreaId());
			if (farmArea == null) {
				return ResultGenerator.genFailResult("E5002");
			}
			farmArea.setVersion(farmArea.getVersion() + 1);
			farmArea.setUpdateAt(now);
		}

		farmArea.setAreaName(param.getAreaName() == null ? "" : param.getAreaName().trim());
		farmArea.setAreaPosition(param.getAreaPosition() == null ? "" : param.getAreaPosition().trim());
		farmArea.setAcreage(param.getAcreage() == null ? "" : param.getAcreage().trim());
		farmArea.setAreaDescribe(param.getAreaDescribe() == null ? "" : param.getAreaDescribe().trim());

		if (farmArea.getId() != null && farmArea.getId().longValue() > 0) {
			smartCultureFarmAreaService.update(farmArea);
		} else {
			smartCultureFarmAreaService.save(farmArea);
		}
		return ResultGenerator.genSuccessResult();
	}

	@SmartCultureTokenCheck
	@PostMapping("/delete")
	public Result<?> delete(HttpServletRequest request, @RequestBody BasicRequestParam param) {
		if (StringUtils.isBlank(param.getResultId())) {
			return ResultGenerator.genFailResult("E5001");
		}
		SmartCultureFarmArea farmArea = smartCultureFarmAreaService.findBy("areaId", param.getResultId());
		if (farmArea == null) {
			return ResultGenerator.genFailResult("E5002");
		}

		smartCultureFarmAreaService.deleteById(farmArea.getId());
		return ResultGenerator.genSuccessResult();
	}

	@SmartCultureTokenCheck
	@PostMapping("/farmAll")
	public Result<?> farmAll(HttpServletRequest request, @RequestBody BasicFarmAreaParam param) {
		List<Map<String, Object>> list = new ArrayList<>();
		Condition condition = new Condition(SmartCultureFarmArea.class);
		condition.createCriteria().andEqualTo("farmId", param.getFarmId());
		condition.orderBy("areaName").asc();
		List<SmartCultureFarmArea> objs = smartCultureFarmAreaService.findByCondition(condition);
		if (objs != null && objs.size() > 0) {
			Map<String, Object> map = null;
			for (SmartCultureFarmArea obj : objs) {
				map = new HashMap<>();
				map.put("areaId", obj.getAreaId());
				map.put("areaName", obj.getAreaName());
				list.add(map);
			}
		}
		return ResultGenerator.genSuccessResult(list);
	}
}
