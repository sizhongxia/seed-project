package com.company.project.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.project.annotation.TokenCheck;
import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.model.SysArea;
import com.company.project.model.om.AreaLevelModel;
import com.company.project.model.om.AreaModel;
import com.company.project.model.param.AreaSearchParam;
import com.company.project.model.returns.Pagination;
import com.company.project.service.SysAreaService;
import com.company.project.unit.AesUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import tk.mybatis.mapper.entity.Condition;
import tk.mybatis.mapper.entity.Example.Criteria;

@RestController
@RequestMapping("/sys/area")
public class AreaController {
	private Logger logger = LoggerFactory.getLogger(AreaController.class.getName());

	@Resource
	private SysAreaService sysAreaService;

	@TokenCheck
	@PostMapping("/data")
	public Result<?> data(@RequestBody AreaSearchParam param) {
		logger.info("area search param:{}", param.toString());

		Condition condition = new Condition(SysArea.class);
		Criteria criteria = condition.createCriteria();

		String name = param.getName();
		if (StringUtils.isNotBlank(name)) {
			criteria.andLike("name", String.format("%%%s%%", name.trim()));
		}
		String pinyin = param.getPinyin();
		if (StringUtils.isNotBlank(pinyin)) {
			criteria.andLike("pinyin", String.format("%%%s%%", pinyin.trim()));
		}
		String jianpin = param.getJianpin();
		if (StringUtils.isNotBlank(jianpin)) {
			criteria.andLike("jianpin", String.format("%%%s%%", jianpin.trim()));
		}
		String code = param.getCode();
		if (StringUtils.isNotBlank(code)) {
			criteria.andEqualTo("code", Integer.parseInt(code.trim()));
		}
		String pcode = param.getPcode();
		if (StringUtils.isNotBlank(pcode)) {
			criteria.andEqualTo("pcode", Integer.parseInt(pcode.trim()));
		}

		Integer page = param.getPage();
		if (page == null || page.intValue() < 1) {
			page = 1;
		}
		Integer size = param.getSize();
		if (size == null || size.intValue() < 1) {
			size = 10;
		}
		condition.setOrderByClause("code asc");
		PageHelper.startPage(page, size);
		Page<SysArea> _list = (Page<SysArea>) sysAreaService.findByCondition(condition);
		List<SysArea> result = _list.getResult();
		List<Map<String, Object>> list = new ArrayList<>();
		if (result != null && result.size() > 0) {
			Map<String, Object> item = null;
			for (SysArea i : result) {
				item = new HashMap<>();
				try {
					item.put("key", AesUtil.encrypt(i.getId().toString()));
				} catch (Exception e) {
					logger.error(e.getMessage());
					return ResultGenerator.genFailResult("系统错误");
				}
				item.put("name", i.getName());
				item.put("code", i.getCode());
				item.put("pcode", i.getPcode());
				item.put("pinyin", i.getPinyin());
				item.put("jianpin", i.getJianpin());
				item.put("lng", i.getLng());
				item.put("lat", i.getLat());
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
	@PostMapping("/getById")
	public Result<?> getById(@RequestBody AreaModel model) {

		if (StringUtils.isBlank(model.getUuid())) {
			return ResultGenerator.genFailResult("无效的表单");
		}
		String id = null;
		try {
			id = AesUtil.desEncrypt(model.getUuid());
		} catch (Exception e) {
			logger.error(e.getMessage());
			return ResultGenerator.genFailResult("无效的表单");

		}

		SysArea area = sysAreaService.findBy("id", Integer.parseInt(id.trim()));
		if (area == null) {
			return ResultGenerator.genFailResult("无效的表单");
		}

		Map<String, Object> item = new HashMap<>();
		item.put("id", model.getUuid());
		item.put("name", area.getName());
		item.put("pinyin", area.getPinyin());
		item.put("jianpin", area.getJianpin());
		item.put("code", area.getCode());
		item.put("pcode", area.getPcode());
		item.put("lat", area.getLat());
		item.put("lng", area.getLng());
		return ResultGenerator.genSuccessResult(item);
	}

	@TokenCheck
	@PostMapping("/getAreaLevelModels")
	public Result<?> getAreaLevelModels() {
		List<AreaLevelModel> roots = new ArrayList<>();
		Condition condition = new Condition(SysArea.class);
		condition.setOrderByClause("code asc");
		List<SysArea> alls = sysAreaService.findByCondition(condition);

		if (alls != null && alls.size() > 0) {
			AreaLevelModel e = null;
			Iterator<SysArea> it = alls.iterator();
			while (it.hasNext()) {
				SysArea x = it.next();
				if (x.getPcode() == null || 0 == x.getPcode().intValue()) {
					e = new AreaLevelModel();
					e.setValue(x.getCode().toString());
					e.setLeaf(false);
					e.setLabel(x.getName());
					e.setChildren(new ArrayList<>());
					roots.add(e);
					it.remove();
				}
			}

			if (roots.size() > 0) {
				for (AreaLevelModel alm : roots) {
					Iterator<SysArea> it2 = alls.iterator();
					while (it2.hasNext()) {
						SysArea x = it2.next();
						if (alm.getValue().equals(x.getPcode().toString())) {
							e = new AreaLevelModel();
							e.setValue(x.getCode().toString());
							e.setLeaf(false);
							e.setLabel(x.getName());
							e.setChildren(new ArrayList<>());
							alm.getChildren().add(e);
						}
					}
					List<AreaLevelModel> almLasts = alm.getChildren();
					for (AreaLevelModel almLast : almLasts) {
						Iterator<SysArea> it3 = alls.iterator();
						while (it3.hasNext()) {
							SysArea x = it3.next();
							if (almLast.getValue().equals(x.getPcode().toString())) {
								e = new AreaLevelModel();
								e.setValue(x.getCode().toString());
								e.setLeaf(true);
								e.setLabel(x.getName());
								almLast.getChildren().add(e);
							}
						}
					}
				}
			}
		}
		return ResultGenerator.genSuccessResult(roots);
	}

	@TokenCheck
	@PostMapping("/getAreas")
	public Result<?> getAreas(@RequestBody AreaSearchParam param) {
		Condition condition = new Condition(SysArea.class);
		Criteria criteria = condition.createCriteria();

		String pcode = param.getPcode();
		if (StringUtils.isNotBlank(pcode)) {
			criteria.andEqualTo("pcode", Integer.parseInt(pcode.trim()));
		}

		condition.setOrderByClause("code asc");
		List<SysArea> result = sysAreaService.findByCondition(condition);

		List<Map<String, Object>> list = new ArrayList<>();
		if (result != null && result.size() > 0) {
			Map<String, Object> item = null;
			for (SysArea i : result) {
				item = new HashMap<>();
				item.put("value", i.getCode().toString());
				item.put("label", i.getName());
				list.add(item);
			}
		}
		return ResultGenerator.genSuccessResult(list);
	}

	@TokenCheck
	@PostMapping("/deleteById")
	public Result<?> deleteById(@RequestBody AreaModel model) {

		if (StringUtils.isNotBlank(model.getUuid())) {
			String id = null;
			try {
				id = AesUtil.desEncrypt(model.getUuid());
			} catch (Exception e) {
				logger.error(e.getMessage());
				return ResultGenerator.genFailResult("无效的表单01");
			}
			SysArea area = sysAreaService.findBy("id", Integer.parseInt(id.trim()));
			if (area == null) {
				return ResultGenerator.genFailResult("无效的表单02");
			}
			sysAreaService.deleteById(area.getId());
			return ResultGenerator.genSuccessResult();
		} else if (model.getIds() != null && model.getIds().length > 0) {
			String[] ids = model.getIds();
			for (String id : ids) {
				try {
					id = AesUtil.desEncrypt(id);
				} catch (Exception e) {
					logger.error(e.getMessage());
					return ResultGenerator.genFailResult("无效的表单03");
				}
				SysArea area = sysAreaService.findBy("id", Integer.parseInt(id.trim()));
				if (area == null) {
					return ResultGenerator.genFailResult("无效的表单04");
				}
				sysAreaService.deleteById(area.getId());
			}
			return ResultGenerator.genSuccessResult();
		}
		return ResultGenerator.genFailResult("无效的表单");
	}

	@TokenCheck
	@PostMapping("/save")
	public Result<?> save(@Validated @RequestBody AreaModel model, BindingResult bindingResult,
			HttpServletRequest request) {
		if (bindingResult.hasErrors()) {
			return ResultGenerator.genFailResult(bindingResult.getFieldError().getDefaultMessage());
		}
		int code = Integer.parseInt(model.getCode().trim());
		int pcode = Integer.parseInt(model.getPcode().trim());
		SysArea area = sysAreaService.findBy("code", code);
		if (area == null) {
			area = new SysArea();
			area.setName(model.getName());
			area.setCode(code);
			area.setPcode(pcode);
			area.setJianpin(model.getJianpin());
			area.setPinyin(model.getPinyin());
			area.setLat(model.getLat());
			area.setLng(model.getLng());
			sysAreaService.save(area);
			return ResultGenerator.genSuccessResult();
		} else {
			return ResultGenerator.genFailResult("当前区域已存在");
		}
	}

	@TokenCheck
	@PostMapping("/update")
	public Result<?> update(@Validated @RequestBody AreaModel model, BindingResult bindingResult,
			HttpServletRequest request) {
		if (bindingResult.hasErrors()) {
			return ResultGenerator.genFailResult(bindingResult.getFieldError().getDefaultMessage());
		}

		if (StringUtils.isBlank(model.getUuid())) {
			return ResultGenerator.genFailResult("无效的表单");
		}

		String id = null;
		try {
			id = AesUtil.desEncrypt(model.getUuid());
		} catch (Exception e) {
			logger.error(e.getMessage());
			return ResultGenerator.genFailResult("无效的表单");
		}

		SysArea area = sysAreaService.findBy("id", Integer.parseInt(id.trim()));
		if (area == null) {
			return ResultGenerator.genFailResult("无效的表单");
		}

		int code = Integer.parseInt(model.getCode().trim());
		int pcode = Integer.parseInt(model.getPcode().trim());
		SysArea _area = sysAreaService.findBy("code", code);
		if (_area != null && !_area.getId().equals(area.getId())) {
			return ResultGenerator.genFailResult("当前区域已存在");
		}
		area.setName(model.getName());
		area.setCode(code);
		area.setPcode(pcode);
		area.setJianpin(model.getJianpin());
		area.setPinyin(model.getPinyin());
		area.setLat(model.getLat());
		area.setLng(model.getLng());
		sysAreaService.update(area);
		return ResultGenerator.genSuccessResult();
	}

}
