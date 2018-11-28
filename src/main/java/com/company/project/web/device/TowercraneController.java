package com.company.project.web.device;

import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.project.annotation.TokenCheck;
import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.model.EquipmentAftersale;
import com.company.project.model.EquipmentBasics;
import com.company.project.model.EquipmentTowercraneParameter;
import com.company.project.model.om.DeviceTowercraneModel;
import com.company.project.service.EquipmentAftersaleService;
import com.company.project.service.EquipmentBasicsService;
import com.company.project.service.EquipmentTowercraneParameterService;
import com.company.project.service.UnitProjectService;
import com.company.project.unit.UtcDateParseUtil;
import com.company.project.unit.IdUtils;

import cn.hutool.core.date.DateUtil;

@RestController
@RequestMapping("/apiv0/device/towercrane")
public class TowercraneController {
	private Logger logger = LoggerFactory.getLogger(TowercraneController.class.getName());

	@Resource
	private EquipmentBasicsService equipmentBasicsService;
	@Resource
	private UnitProjectService unitProjectService;
	@Resource
	private EquipmentAftersaleService equipmentAftersaleService;
	@Resource
	private EquipmentTowercraneParameterService equipmentTowercraneParameterService;

	@TokenCheck
	@PostMapping("/getById")
	public Result<?> getById(@RequestBody DeviceTowercraneModel model) {
		if (StringUtils.isBlank(model.getUuid())) {
			return ResultGenerator.genFailResult("无效的表单");
		}
		EquipmentBasics equipmentBasics = equipmentBasicsService.findBy("uuid", model.getUuid());
		if (equipmentBasics == null) {
			return ResultGenerator.genFailResult("无效的表单");
		}
		DeviceTowercraneModel res = new DeviceTowercraneModel();
		BeanUtils.copyProperties(equipmentBasics, res);

		EquipmentAftersale aftersale = equipmentAftersaleService.findBy("uuid", model.getUuid());
		if (aftersale != null) {
			res.setPersonincharge(aftersale.getPersonincharge() == null ? "" : aftersale.getPersonincharge());
			res.setPersoninchargephone(
					aftersale.getPersoninchargephone() == null ? "" : aftersale.getPersoninchargephone());
			res.setInstallperson(aftersale.getInstallperson() == null ? "" : aftersale.getInstallperson());
			res.setInstallpersonphone(
					aftersale.getInstallpersonphone() == null ? "" : aftersale.getInstallpersonphone());
			res.setHost(aftersale.getHost() == null ? "" : aftersale.getHost());
			Long installtime = aftersale.getInstalltime();
			if (installtime == null || installtime.longValue() < 1) {
				res.setInstalltime("");
			} else {
				res.setInstalltime(DateUtil.format(new Date(installtime), "yyyy-MM-dd"));
			}
		}

		return ResultGenerator.genSuccessResult(res);
	}

	@TokenCheck
	@PostMapping("/deleteById")
	public Result<?> deleteById(@RequestBody DeviceTowercraneModel model) {
		if (StringUtils.isBlank(model.getUuid())) {
			return ResultGenerator.genFailResult("无效的表单01");
		}
		EquipmentBasics equipmentBasics = equipmentBasicsService.findBy("uuid", model.getUuid());
		if (equipmentBasics == null) {
			return ResultGenerator.genFailResult("无效的表单02");
		}
		equipmentBasics.setState(1);
		equipmentBasics.setUpdatetime(System.currentTimeMillis());
		equipmentBasicsService.update(equipmentBasics);
		return ResultGenerator.genSuccessResult();
	}

	@TokenCheck
	@PostMapping("/save")
	public Result<?> save(@Validated @RequestBody DeviceTowercraneModel model, BindingResult bindingResult,
			HttpServletRequest request) {
		if (bindingResult.hasErrors()) {
			return ResultGenerator.genFailResult(bindingResult.getFieldError().getDefaultMessage());
		}
		logger.debug("save dustnoise:" + model.toString());
		if (StringUtils.isBlank(model.getCompanyuuid()) && StringUtils.isBlank(model.getProuuid())) {
			return ResultGenerator.genFailResult("请选择一个设备所属单位或工地");
		}
		EquipmentBasics equipmentBasics = equipmentBasicsService.findBy("equipmentno", model.getEquipmentno());
		if (equipmentBasics == null) {
			equipmentBasics = new EquipmentBasics();
			equipmentBasics.setUuid(IdUtils.initUuid());
			equipmentBasics.setCompanyuuid(model.getCompanyuuid());
			equipmentBasics.setProuuid(model.getProuuid());
			equipmentBasics.setName(model.getName());
			equipmentBasics.setEquipmentno(model.getEquipmentno());
			equipmentBasics.setSuppliercompanyuuid(model.getSuppliercompanyuuid());
			equipmentBasics.setAgentcompanyuuid(model.getAgentcompanyuuid());
			equipmentBasics.setLng(model.getLng());
			equipmentBasics.setLat(model.getLat());
			equipmentBasics.setIpaddressport(model.getIpaddressport());
			equipmentBasics.setType(1);
			equipmentBasics.setState(0);
			equipmentBasics.setAddtime(System.currentTimeMillis());
			equipmentBasics.setUpdatetime(System.currentTimeMillis());
			equipmentBasics.setLastonlinetime(0L);
			equipmentBasics.setIpportissuestatus(0);
			equipmentBasics.setWorktype("1");
			equipmentBasicsService.save(equipmentBasics);
		} else {
			equipmentBasics.setState(0);
			equipmentBasics.setUpdatetime(System.currentTimeMillis());
			equipmentBasicsService.update(equipmentBasics);
			return ResultGenerator.genFailResult("设备编码已存在");
		}

		EquipmentAftersale equipmentAftersale = new EquipmentAftersale();
		equipmentAftersale.setUuid(equipmentBasics.getUuid());
		equipmentAftersale.setPersonincharge(model.getPersonincharge());
		equipmentAftersale.setPersoninchargephone(model.getPersoninchargephone());
		equipmentAftersale.setInstallperson(model.getInstallperson());
		equipmentAftersale.setInstallpersonphone(model.getInstallpersonphone());

		if (StringUtils.isNotBlank(model.getInstalltime())) {
			Date installtime = UtcDateParseUtil.parse(model.getInstalltime());
			equipmentAftersale.setInstalltime(installtime.getTime());
		}
		equipmentAftersale.setHost(model.getHost());
		equipmentAftersale.setState(0);
		equipmentAftersaleService.save(equipmentAftersale);

		EquipmentTowercraneParameter equipmentTowercraneParameter = new EquipmentTowercraneParameter();
		equipmentTowercraneParameter.setUuid(equipmentBasics.getUuid());
		equipmentTowercraneParameter.setEquipmentno(equipmentBasics.getEquipmentno());
		equipmentTowercraneParameter.setCreatTime(System.currentTimeMillis());
		equipmentTowercraneParameterService.save(equipmentTowercraneParameter);

		return ResultGenerator.genSuccessResult();
	}

	@TokenCheck
	@PostMapping("/update")
	public Result<?> update(@Validated @RequestBody DeviceTowercraneModel model, BindingResult bindingResult,
			HttpServletRequest request) {
		if (bindingResult.hasErrors()) {
			return ResultGenerator.genFailResult(bindingResult.getFieldError().getDefaultMessage());
		}
		EquipmentBasics equipmentBasics = equipmentBasicsService.findBy("uuid", model.getUuid());
		if (equipmentBasics == null) {
			return ResultGenerator.genFailResult("无效的记录ID");
		}

		EquipmentBasics _equipmentBasics = equipmentBasicsService.findBy("equipmentno", model.getEquipmentno());
		if (_equipmentBasics != null && !_equipmentBasics.getUuid().equals(equipmentBasics.getUuid())) {
			return ResultGenerator.genFailResult("设备编码已存在");
		}

		equipmentBasics.setProuuid(model.getProuuid());
		equipmentBasics.setName(model.getName());
		equipmentBasics.setEquipmentno(model.getEquipmentno());
		equipmentBasics.setSuppliercompanyuuid(model.getSuppliercompanyuuid());
		equipmentBasics.setAgentcompanyuuid(model.getAgentcompanyuuid());
		equipmentBasics.setLng(model.getLng());
		equipmentBasics.setLat(model.getLat());
		equipmentBasics.setIpaddressport(model.getIpaddressport());
		equipmentBasics.setUpdatetime(System.currentTimeMillis());
		equipmentBasicsService.update(equipmentBasics);

		EquipmentAftersale equipmentAftersale = equipmentAftersaleService.findBy("uuid", equipmentBasics.getUuid());
		equipmentAftersale.setPersonincharge(model.getPersonincharge());
		equipmentAftersale.setPersoninchargephone(model.getPersoninchargephone());
		equipmentAftersale.setInstallperson(model.getInstallperson());
		equipmentAftersale.setInstallpersonphone(model.getInstallpersonphone());
		if (StringUtils.isNotBlank(model.getInstalltime())) {
			Date installtime = UtcDateParseUtil.parse(model.getInstalltime());
			equipmentAftersale.setInstalltime(installtime.getTime());
		} else {
			equipmentAftersale.setInstalltime(0L);
		}
		equipmentAftersale.setHost(model.getHost());
		equipmentAftersaleService.update(equipmentAftersale);

		EquipmentTowercraneParameter equipmentTowercraneParameter = equipmentTowercraneParameterService.findBy("uuid",
				equipmentBasics.getUuid());
		if (equipmentTowercraneParameter == null) {
			equipmentTowercraneParameter = new EquipmentTowercraneParameter();
			equipmentTowercraneParameter.setUuid(equipmentBasics.getUuid());
			equipmentTowercraneParameter.setEquipmentno(equipmentBasics.getEquipmentno());
			equipmentTowercraneParameter.setCreatTime(System.currentTimeMillis());
			equipmentTowercraneParameterService.save(equipmentTowercraneParameter);
		}

		return ResultGenerator.genSuccessResult();
	}

}
