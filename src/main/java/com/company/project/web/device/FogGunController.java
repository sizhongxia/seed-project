package com.company.project.web.device;

import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
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
import com.company.project.model.om.DeviceFogGunModel;
import com.company.project.service.EquipmentAftersaleService;
import com.company.project.service.EquipmentBasicsService;
import com.company.project.service.UnitProjectService;
import com.company.project.unit.UtcDateParseUtil;
import com.company.project.unit.UuidUtil;
import com.xiaoleilu.hutool.date.DateUtil;

@RestController
@RequestMapping("/apiv0/device/fogGun")
public class FogGunController {
	private Logger logger = LoggerFactory.getLogger(FogGunController.class.getName());

	@Resource
	private EquipmentBasicsService equipmentBasicsService;
	@Resource
	private UnitProjectService unitProjectService;
	@Resource
	private EquipmentAftersaleService equipmentAftersaleService;

	@TokenCheck
	@PostMapping("/getById")
	public Result<?> getById(@RequestBody DeviceFogGunModel model) {
		if (StringUtils.isBlank(model.getUuid())) {
			return ResultGenerator.genFailResult("无效的表单");
		}
		EquipmentBasics equipmentBasics = equipmentBasicsService.findBy("uuid", model.getUuid());
		if (equipmentBasics == null) {
			return ResultGenerator.genFailResult("无效的表单");
		}
		DeviceFogGunModel res = new DeviceFogGunModel();
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
			if (aftersale.getSpraytype() != null) {
				res.setSpraytype(aftersale.getSpraytype().toString());
			} else {
				res.setSpraytype("");
			}
		}

		return ResultGenerator.genSuccessResult(res);
	}

	@TokenCheck
	@PostMapping("/deleteById")
	public Result<?> deleteById(@RequestBody DeviceFogGunModel model) {
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
	public Result<?> save(@Validated @RequestBody DeviceFogGunModel model, BindingResult bindingResult,
			HttpServletRequest request) {
		if (bindingResult.hasErrors()) {
			return ResultGenerator.genFailResult(bindingResult.getFieldError().getDefaultMessage());
		}
		logger.debug("save foggun:" + model.toString());
		if (StringUtils.isBlank(model.getCompanyuuid()) && StringUtils.isBlank(model.getProuuid())) {
			return ResultGenerator.genFailResult("请选择一个设备所属单位或工地");
		}
		EquipmentBasics equipmentBasics = equipmentBasicsService.findBy("equipmentno", model.getEquipmentno());
		if (equipmentBasics == null) {
			equipmentBasics = new EquipmentBasics();
			equipmentBasics.setUuid(UuidUtil.init());
			equipmentBasics.setCompanyuuid(model.getCompanyuuid());
			equipmentBasics.setProuuid(model.getProuuid());
			equipmentBasics.setName(model.getName());
			equipmentBasics.setEquipmentno(model.getEquipmentno());
			equipmentBasics.setSuppliercompanyuuid(model.getSuppliercompanyuuid());
			equipmentBasics.setAgentcompanyuuid(model.getAgentcompanyuuid());
			equipmentBasics.setLng(model.getLng());
			equipmentBasics.setLat(model.getLat());
			equipmentBasics.setIpaddressport(model.getIpaddressport());
			equipmentBasics.setType(4);
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
		if (NumberUtils.isParsable(model.getSpraytype())) {
			equipmentAftersale.setSpraytype(Integer.parseInt(model.getSpraytype().trim()));
		} else {
			equipmentAftersale.setSpraytype(0);
		}
		equipmentAftersale.setState(0);
		equipmentAftersaleService.save(equipmentAftersale);
		return ResultGenerator.genSuccessResult();
	}

	@TokenCheck
	@PostMapping("/update")
	public Result<?> update(@Validated @RequestBody DeviceFogGunModel model, BindingResult bindingResult,
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
		if (NumberUtils.isParsable(model.getSpraytype())) {
			equipmentAftersale.setSpraytype(Integer.parseInt(model.getSpraytype().trim()));
		} else {
			equipmentAftersale.setSpraytype(0);
		}
		equipmentAftersale.setHost(model.getHost());
		equipmentAftersaleService.update(equipmentAftersale);
		return ResultGenerator.genSuccessResult();
	}

}