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
import com.company.project.model.EquipmentDustnoiseParameter;
import com.company.project.model.om.DeviceDustNoiseModel;
import com.company.project.service.EquipmentAftersaleService;
import com.company.project.service.EquipmentBasicsService;
import com.company.project.service.EquipmentDustnoiseParameterService;
import com.company.project.service.UnitProjectService;
import com.company.project.unit.UtcDateParseUtil;
import com.company.project.unit.UuidUtil;
import com.xiaoleilu.hutool.date.DateUtil;

@RestController
@RequestMapping("/device/dustNoise")
public class DustNoiseController {
	private Logger logger = LoggerFactory.getLogger(DustNoiseController.class.getName());

	@Resource
	private EquipmentBasicsService equipmentBasicsService;
	@Resource
	private EquipmentDustnoiseParameterService equipmentDustnoiseParameterService;
	@Resource
	private UnitProjectService unitProjectService;
	@Resource
	private EquipmentAftersaleService equipmentAftersaleService;

	@TokenCheck
	@PostMapping("/getById")
	public Result<?> getById(@RequestBody DeviceDustNoiseModel model) {
		if (StringUtils.isBlank(model.getUuid())) {
			return ResultGenerator.genFailResult("无效的表单");
		}
		EquipmentBasics equipmentBasics = equipmentBasicsService.findBy("uuid", model.getUuid());
		if (equipmentBasics == null) {
			return ResultGenerator.genFailResult("无效的表单");
		}
		DeviceDustNoiseModel res = new DeviceDustNoiseModel();
		BeanUtils.copyProperties(equipmentBasics, res);

		EquipmentDustnoiseParameter dustnoiseParameter = equipmentDustnoiseParameterService.findBy("uuid",
				model.getUuid());

		if (dustnoiseParameter != null) {
			if (dustnoiseParameter.getPm25Alarm() != null && dustnoiseParameter.getPm25Alarm().longValue() > 0) {
				res.setPm25Alarm(dustnoiseParameter.getPm25Alarm().toString());
			} else {
				res.setPm25Alarm("");
			}
			if (dustnoiseParameter.getPm10Alarm() != null && dustnoiseParameter.getPm10Alarm().longValue() > 0) {
				res.setPm10Alarm(dustnoiseParameter.getPm10Alarm().toString());
			} else {
				res.setPm10Alarm("");
			}
			if (dustnoiseParameter.getNoiseAlarm() != null && dustnoiseParameter.getNoiseAlarm().longValue() > 0) {
				res.setNoiseAlarm(dustnoiseParameter.getNoiseAlarm().toString());
			} else {
				res.setNoiseAlarm("");
			}
			if (dustnoiseParameter.getNoisePattern() != null && dustnoiseParameter.getNoisePattern().intValue() > 0) {
				res.setNoisePattern(dustnoiseParameter.getNoisePattern().toString());
			} else {
				res.setNoisePattern("");
			}
			if (dustnoiseParameter.getNoiseCycle() != null && dustnoiseParameter.getNoiseCycle().intValue() > 0) {
				res.setNoiseCycle(dustnoiseParameter.getNoiseCycle().toString());
			} else {
				res.setNoiseCycle("");
			}
			if (dustnoiseParameter.getNoiseOc() != null && dustnoiseParameter.getNoiseOc().intValue() > 0) {
				res.setNoiseOc(dustnoiseParameter.getNoiseOc().toString());
			} else {
				res.setNoiseOc("");
			}
		}

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
	public Result<?> deleteById(@RequestBody DeviceDustNoiseModel model) {
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
	public Result<?> save(@Validated @RequestBody DeviceDustNoiseModel model, BindingResult bindingResult,
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
			equipmentBasics.setType(5);
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
		EquipmentDustnoiseParameter dustnoiseParameter = new EquipmentDustnoiseParameter();
		dustnoiseParameter.setUuid(equipmentBasics.getUuid());
		dustnoiseParameter.setEquipmentno(equipmentBasics.getEquipmentno());
		dustnoiseParameter.setCompanyuuid(equipmentBasics.getSuppliercompanyuuid());

		if (NumberUtils.isParsable(model.getPm25Alarm())) {
			dustnoiseParameter.setPm25Alarm(Double.parseDouble(model.getPm25Alarm()));
		} else {
			dustnoiseParameter.setPm25Alarm(0.0);
		}
		if (NumberUtils.isParsable(model.getPm10Alarm())) {
			dustnoiseParameter.setPm10Alarm(Double.parseDouble(model.getPm10Alarm()));
		} else {
			dustnoiseParameter.setPm10Alarm(0.0);
		}
		if (NumberUtils.isParsable(model.getNoiseAlarm())) {
			dustnoiseParameter.setNoiseAlarm(Double.parseDouble(model.getNoiseAlarm()));
		} else {
			dustnoiseParameter.setNoiseAlarm(0.0);
		}
		if (NumberUtils.isParsable(model.getNoisePattern())) {
			dustnoiseParameter.setNoisePattern(Integer.parseInt(model.getNoisePattern()));
		} else {
			dustnoiseParameter.setNoisePattern(0);
		}
		if (NumberUtils.isParsable(model.getNoiseCycle())) {
			dustnoiseParameter.setNoiseCycle(Integer.parseInt(model.getNoiseCycle()));
		} else {
			dustnoiseParameter.setNoiseCycle(0);
		}
		if (NumberUtils.isParsable(model.getNoiseOc())) {
			dustnoiseParameter.setNoiseOc(Integer.parseInt(model.getNoiseOc()));
		} else {
			dustnoiseParameter.setNoiseOc(0);
		}
		dustnoiseParameter.setNoiseStatus(0);
		dustnoiseParameter.setAddtime(System.currentTimeMillis());
		dustnoiseParameter.setUpdatetime(System.currentTimeMillis());
		equipmentDustnoiseParameterService.save(dustnoiseParameter);

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
		return ResultGenerator.genSuccessResult();
	}

	@TokenCheck
	@PostMapping("/update")
	public Result<?> update(@Validated @RequestBody DeviceDustNoiseModel model, BindingResult bindingResult,
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

		EquipmentDustnoiseParameter dustnoiseParameter = equipmentDustnoiseParameterService.findBy("uuid",
				equipmentBasics.getUuid());
		dustnoiseParameter.setEquipmentno(equipmentBasics.getEquipmentno());
		dustnoiseParameter.setCompanyuuid(equipmentBasics.getSuppliercompanyuuid());

		if (NumberUtils.isParsable(model.getPm25Alarm())) {
			dustnoiseParameter.setPm25Alarm(Double.parseDouble(model.getPm25Alarm()));
		} else {
			dustnoiseParameter.setPm25Alarm(0.0);
		}
		if (NumberUtils.isParsable(model.getPm10Alarm())) {
			dustnoiseParameter.setPm10Alarm(Double.parseDouble(model.getPm10Alarm()));
		} else {
			dustnoiseParameter.setPm10Alarm(0.0);
		}
		if (NumberUtils.isParsable(model.getNoiseAlarm())) {
			dustnoiseParameter.setNoiseAlarm(Double.parseDouble(model.getNoiseAlarm()));
		} else {
			dustnoiseParameter.setNoiseAlarm(0.0);
		}
		if (NumberUtils.isParsable(model.getNoisePattern())) {
			dustnoiseParameter.setNoisePattern(Integer.parseInt(model.getNoisePattern()));
		} else {
			dustnoiseParameter.setNoisePattern(0);
		}
		if (NumberUtils.isParsable(model.getNoiseCycle())) {
			dustnoiseParameter.setNoiseCycle(Integer.parseInt(model.getNoiseCycle()));
		} else {
			dustnoiseParameter.setNoiseCycle(0);
		}
		if (NumberUtils.isParsable(model.getNoiseOc())) {
			dustnoiseParameter.setNoiseOc(Integer.parseInt(model.getNoiseOc()));
		} else {
			dustnoiseParameter.setNoiseOc(0);
		}
		dustnoiseParameter.setUpdatetime(System.currentTimeMillis());
		equipmentDustnoiseParameterService.update(dustnoiseParameter);

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
		return ResultGenerator.genSuccessResult();
	}

}
