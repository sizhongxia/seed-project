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
import com.company.project.model.EquipmentBasics;
import com.company.project.model.EquipmentVideo;
import com.company.project.model.om.DeviceVideoModel;
import com.company.project.service.EquipmentBasicsService;
import com.company.project.service.EquipmentVideoService;
import com.company.project.service.UnitProjectService;
import com.company.project.unit.UtcDateParseUtil;
import com.company.project.unit.UuidUtil;
import com.xiaoleilu.hutool.date.DateUtil;

@RestController
@RequestMapping("/device/video")
public class VideoController {
	private Logger logger = LoggerFactory.getLogger(VideoController.class.getName());

	@Resource
	private EquipmentBasicsService equipmentBasicsService;
	@Resource
	private UnitProjectService unitProjectService;
	@Resource
	private EquipmentVideoService equipmentVideoService;

	@TokenCheck
	@PostMapping("/getById")
	public Result<?> getById(@RequestBody DeviceVideoModel model) {
		if (StringUtils.isBlank(model.getUuid())) {
			return ResultGenerator.genFailResult("无效的表单");
		}
		EquipmentBasics equipmentBasics = equipmentBasicsService.findBy("uuid", model.getUuid());
		if (equipmentBasics == null) {
			return ResultGenerator.genFailResult("无效的表单");
		}
		DeviceVideoModel res = new DeviceVideoModel();
		BeanUtils.copyProperties(equipmentBasics, res);

		EquipmentVideo equipmentVideo = equipmentVideoService.findBy("uuid", equipmentBasics.getUuid());
		res.setName(equipmentVideo.getName());
		res.setBrandname(model.getBrandname());
		if (equipmentVideo.getDeploytime() != null && equipmentVideo.getDeploytime().longValue() > 0) {
			res.setDeploytime(DateUtil.format(new Date(equipmentVideo.getDeploytime()), "yyyy-MM-dd"));
		} else {
			res.setDeploytime("");
		}
		res.setType(equipmentVideo.getType());
		res.setEquipmentuuid(equipmentVideo.getEquipmentuuid());
		res.setIseyeclient(equipmentVideo.getIseyeclient().toString());
		if (StringUtils.isNotBlank(equipmentVideo.getVoperation())) {
			res.setVoperations(equipmentVideo.getVoperation().split("[,]"));
		} else {
			res.setVoperations(new String[] {});
		}
		res.setIstowereye(equipmentVideo.getIstowereye().toString());
		res.setPushflowmode(equipmentVideo.getPushflowmode());
		res.setIp(equipmentVideo.getIp());
		res.setPort(equipmentVideo.getPort());
		res.setUsername(equipmentVideo.getUsername());
		res.setPassword(equipmentVideo.getPassword());
		res.setFlowaddress(equipmentVideo.getFlowaddress());
		res.setStreamurl(equipmentVideo.getStreamurl());
		res.setPlayurl(equipmentVideo.getPlayurl());
		res.setUsername(equipmentVideo.getUsername());
		res.setPassword(equipmentVideo.getPassword());
		return ResultGenerator.genSuccessResult(res);
	}

	@TokenCheck
	@PostMapping("/deleteById")
	public Result<?> deleteById(@RequestBody DeviceVideoModel model) {
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
	public Result<?> save(@Validated @RequestBody DeviceVideoModel model, BindingResult bindingResult,
			HttpServletRequest request) {
		if (bindingResult.hasErrors()) {
			return ResultGenerator.genFailResult(bindingResult.getFieldError().getDefaultMessage());
		}
		logger.debug("save video:" + model.toString());
		if (StringUtils.isBlank(model.getCompanyuuid()) && StringUtils.isBlank(model.getProuuid())) {
			return ResultGenerator.genFailResult("请选择一个设备所属单位或工地");
		}
		EquipmentBasics equipmentBasics = new EquipmentBasics();
		equipmentBasics.setUuid(UuidUtil.init());
		equipmentBasics.setCompanyuuid(model.getCompanyuuid());
		equipmentBasics.setProuuid(model.getProuuid());
		equipmentBasics.setName(model.getName());
		if (StringUtils.isNotBlank(model.getEquipmentno())) {
			equipmentBasics.setEquipmentno(model.getEquipmentno());
		} else {
			equipmentBasics.setEquipmentno("");
		}
		equipmentBasics.setSuppliercompanyuuid(model.getSuppliercompanyuuid());
		equipmentBasics.setAgentcompanyuuid(model.getAgentcompanyuuid());
		equipmentBasics.setLng(model.getLng());
		equipmentBasics.setLat(model.getLat());
		equipmentBasics.setIpaddressport(model.getIpaddressport());
		equipmentBasics.setType(6);
		equipmentBasics.setState(0);
		equipmentBasics.setAddtime(System.currentTimeMillis());
		equipmentBasics.setUpdatetime(System.currentTimeMillis());
		equipmentBasics.setLastonlinetime(0L);
		equipmentBasics.setIpportissuestatus(0);
		equipmentBasics.setWorktype("1");
		equipmentBasicsService.save(equipmentBasics);

		EquipmentVideo equipmentVideo = new EquipmentVideo();
		equipmentVideo.setUuid(equipmentBasics.getUuid());
		equipmentVideo.setName(equipmentBasics.getName());
		equipmentVideo.setDeptuuid(equipmentBasics.getProuuid());
		equipmentVideo.setBrandname(model.getBrandname());
		if (StringUtils.isNotBlank(model.getDeploytime())) {
			Date deploytime = UtcDateParseUtil.parse(model.getDeploytime());
			equipmentVideo.setDeploytime(deploytime.getTime());
		} else {
			equipmentVideo.setDeploytime(0L);
		}

		equipmentVideo.setType(model.getType());
		if (NumberUtils.isParsable(model.getIseyeclient())) {
			equipmentVideo.setIseyeclient(Integer.parseInt(model.getIseyeclient().trim()));
			if (equipmentVideo.getIseyeclient().intValue() == 1) {
				equipmentVideo.setEquipmentuuid(model.getEquipmentuuid());
			}
		} else {
			equipmentVideo.setIseyeclient(2);
		}

		String[] voperations = model.getVoperations();
		if (voperations != null && voperations.length > 0) {
			equipmentVideo.setVoperation(String.join(",", voperations));
		}

		if (NumberUtils.isParsable(model.getIstowereye())) {
			equipmentVideo.setIstowereye(Integer.parseInt(model.getIstowereye().trim()));
		} else {
			equipmentVideo.setIstowereye(2);
		}

		equipmentVideo.setPushflowmode(model.getPushflowmode());
		equipmentVideo.setIp(model.getIp());
		equipmentVideo.setPort(model.getPort());
		equipmentVideo.setUsername(model.getUsername());
		equipmentVideo.setPassword(model.getPassword());

		equipmentVideo.setFlowaddress(model.getFlowaddress());
		equipmentVideo.setStreamurl(model.getStreamurl());
		equipmentVideo.setPlayurl(model.getPlayurl());

		equipmentVideoService.save(equipmentVideo);

		return ResultGenerator.genSuccessResult();
	}

	@TokenCheck
	@PostMapping("/update")
	public Result<?> update(@Validated @RequestBody DeviceVideoModel model, BindingResult bindingResult,
			HttpServletRequest request) {
		if (bindingResult.hasErrors()) {
			return ResultGenerator.genFailResult(bindingResult.getFieldError().getDefaultMessage());
		}
		EquipmentBasics equipmentBasics = equipmentBasicsService.findBy("uuid", model.getUuid());
		if (equipmentBasics == null) {
			return ResultGenerator.genFailResult("无效的记录ID");
		}
		EquipmentVideo equipmentVideo = equipmentVideoService.findBy("uuid", model.getUuid());
		if (equipmentVideo == null) {
			return ResultGenerator.genFailResult("无效的记录ID");
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

		equipmentVideo.setName(equipmentBasics.getName());
		equipmentVideo.setDeptuuid(equipmentBasics.getProuuid());
		equipmentVideo.setBrandname(model.getBrandname());
		if (StringUtils.isNotBlank(model.getDeploytime())) {
			Date deploytime = UtcDateParseUtil.parse(model.getDeploytime());
			equipmentVideo.setDeploytime(deploytime.getTime());
		} else {
			equipmentVideo.setDeploytime(0L);
		}

		equipmentVideo.setType(model.getType());
		if (NumberUtils.isParsable(model.getIseyeclient())) {
			equipmentVideo.setIseyeclient(Integer.parseInt(model.getIseyeclient().trim()));
			if (equipmentVideo.getIseyeclient().intValue() == 1) {
				equipmentVideo.setEquipmentuuid(model.getEquipmentuuid());
			}
		} else {
			equipmentVideo.setIseyeclient(2);
		}

		String[] voperations = model.getVoperations();
		if (voperations != null && voperations.length > 0) {
			equipmentVideo.setVoperation(String.join(",", voperations));
		}

		if (NumberUtils.isParsable(model.getIstowereye())) {
			equipmentVideo.setIstowereye(Integer.parseInt(model.getIstowereye().trim()));
		} else {
			equipmentVideo.setIstowereye(2);
		}

		equipmentVideo.setPushflowmode(model.getPushflowmode());
		equipmentVideo.setIp(model.getIp());
		equipmentVideo.setPort(model.getPort());
		equipmentVideo.setUsername(model.getUsername());
		equipmentVideo.setPassword(model.getPassword());

		equipmentVideo.setFlowaddress(model.getFlowaddress());
		equipmentVideo.setStreamurl(model.getStreamurl());
		equipmentVideo.setPlayurl(model.getPlayurl());

		equipmentVideoService.update(equipmentVideo);

		return ResultGenerator.genSuccessResult();
	}

}
