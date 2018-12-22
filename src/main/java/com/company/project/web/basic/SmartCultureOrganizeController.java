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
import com.company.project.model.SmartCultureOrganize;
import com.company.project.model.param.basic.BasicOrganizeParam;
import com.company.project.model.param.basic.BasicRequestParam;
import com.company.project.model.returns.basic.BasicPageResult;
import com.company.project.service.SmartCultureOrganizeService;
import com.company.project.unit.IdUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import cn.hutool.core.date.DateUtil;
import tk.mybatis.mapper.entity.Condition;
import tk.mybatis.mapper.entity.Example.Criteria;
import tk.mybatis.mapper.entity.Example.OrderBy;

@RestController
@RequestMapping("/basic/api/organize")
public class SmartCultureOrganizeController {
	final Logger logger = LoggerFactory.getLogger(SmartCultureOrganizeController.class);

	@Resource
	SmartCultureOrganizeService smartCultureOrganizeService;

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

		Condition condition = new Condition(SmartCultureOrganize.class);
		Criteria cr = condition.createCriteria();
		if (StringUtils.isNotBlank(sv)) {
			cr.andLike("organizeName", String.format("%%%s%%", sv.trim()));
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
		List<SmartCultureOrganize> objs = smartCultureOrganizeService.findByCondition(condition);
		if (objs != null && objs.size() > 0) {
			Map<String, Object> map = null;
			for (SmartCultureOrganize obj : objs) {
				map = new HashMap<>();
				map.put("organizeId", obj.getOrganizeId());
				map.put("organizeName", obj.getOrganizeName());
				map.put("createAt", DateUtil.format(obj.getCreateAt(), "yyyy-MM-dd HH:mm:ss"));
				map.put("updateAt", DateUtil.format(obj.getUpdateAt(), "yyyy-MM-dd HH:mm:ss"));
				list.add(map);
			}
			Page<SmartCultureOrganize> pageInfo = (Page<SmartCultureOrganize>) objs;
			total = pageInfo.getTotal();
		}

		result.setTotal(total);
		result.setList(list);
		return ResultGenerator.genSuccessResult(result);
	}

	@SmartCultureTokenCheck
	@PostMapping("/upinsert")
	public Result<?> upinsert(HttpServletRequest request, @RequestBody BasicOrganizeParam param) {
		String organizeId = param.getOrganizeId();
		if (StringUtils.isBlank(organizeId)) {
			// 新增
			Condition condition = new Condition(SmartCultureOrganize.class);
			condition.createCriteria().andEqualTo("organizeName", param.getOrganizeName());
			List<SmartCultureOrganize> scos = smartCultureOrganizeService.findByCondition(condition);
			if (scos != null && scos.size() > 0) {
				return ResultGenerator.genFailResult("E5001");
			}
			SmartCultureOrganize organize = new SmartCultureOrganize();
			organize.setOrganizeId(IdUtils.initObjectId());
			organize.setOrganizeName(param.getOrganizeName());
			organize.setVersion(1L);
			organize.setCreateAt(new Date());
			organize.setUpdateAt(new Date());
			smartCultureOrganizeService.save(organize);
		} else {
			SmartCultureOrganize organize = smartCultureOrganizeService.findBy("organizeId", param.getOrganizeId());
			if (organize == null) {
				return ResultGenerator.genFailResult("E5002");
			}
			Condition condition = new Condition(SmartCultureOrganize.class);
			condition.createCriteria().andEqualTo("organizeName", param.getOrganizeName());
			List<SmartCultureOrganize> scos = smartCultureOrganizeService.findByCondition(condition);
			if (scos != null && scos.size() > 0) {
				for (SmartCultureOrganize _i : scos) {
					if (!_i.getOrganizeId().equals(param.getOrganizeId())) {
						return ResultGenerator.genFailResult("E5003");
					}
				}
			}
			organize.setOrganizeName(param.getOrganizeName());
			organize.setVersion(organize.getVersion() + 1);
			organize.setUpdateAt(new Date());
			smartCultureOrganizeService.update(organize);
		}
		return ResultGenerator.genSuccessResult();
	}

	@SmartCultureTokenCheck
	@PostMapping("/delete")
	public Result<?> delete(HttpServletRequest request, @RequestBody BasicRequestParam param) {
		if (StringUtils.isBlank(param.getResultId())) {
			return ResultGenerator.genFailResult("E5001");
		}
		SmartCultureOrganize organize = smartCultureOrganizeService.findBy("organizeId", param.getResultId());
		if (organize == null) {
			return ResultGenerator.genFailResult("E5002");
		}
		smartCultureOrganizeService.deleteById(organize.getId());
		return ResultGenerator.genSuccessResult();
	}

	@SmartCultureTokenCheck
	@PostMapping("/all")
	public Result<?> all(HttpServletRequest request) {
		List<Map<String, Object>> list = new ArrayList<>();
		Condition condition = new Condition(SmartCultureOrganize.class);
		condition.orderBy("organizeName").asc();
		List<SmartCultureOrganize> objs = smartCultureOrganizeService.findByCondition(condition);
		if (objs != null && objs.size() > 0) {
			Map<String, Object> map = null;
			for (SmartCultureOrganize obj : objs) {
				map = new HashMap<>();
				map.put("organizeId", obj.getOrganizeId());
				map.put("organizeName", obj.getOrganizeName());
				list.add(map);
			}
		}
		return ResultGenerator.genSuccessResult(list);
	}
}
