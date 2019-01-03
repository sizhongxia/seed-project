package com.company.project.web.zhyz.mp;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.project.annotation.SmartCultureTokenCheck;
import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.model.SmartCultureFarm;
import com.company.project.model.SmartCultureFarmPic;
import com.company.project.model.SmartCultureUser;
import com.company.project.model.SmartCultureUserFarm;
import com.company.project.model.SmartCultureWeatherNow;
import com.company.project.model.param.basic.BasicFarmParam;
import com.company.project.model.param.basic.BasicWeiXinRequestParam;
import com.company.project.service.SmartCultureFarmPicService;
import com.company.project.service.SmartCultureFarmService;
import com.company.project.service.SmartCultureUserFarmService;
import com.company.project.service.SmartCultureUserService;
import com.company.project.service.SmartCultureWeatherNowService;
import com.github.pagehelper.PageHelper;

import tk.mybatis.mapper.entity.Condition;

@RestController
@RequestMapping("/zhyz/miniapp/api/farm")
public class SmartCultureMpFarmController {

	@Resource
	SmartCultureUserFarmService smartCultureUserFarmService;
	@Resource
	SmartCultureFarmService smartCultureFarmService;
	@Resource
	SmartCultureUserService smartCultureUserService;
	@Resource
	SmartCultureFarmPicService smartCultureFarmPicService;
	@Resource
	SmartCultureWeatherNowService smartCultureWeatherNowService;

	@SmartCultureTokenCheck
	@RequestMapping("/detail")
	public Result<?> detail(HttpServletRequest request, @RequestBody BasicWeiXinRequestParam param) {
		String farmId = param.getFarmId();
		SmartCultureFarm farm = smartCultureFarmService.findBy("farmId", farmId);
		if (farm == null) {
			farm = smartCultureFarmService.findBy("farmCode", farmId);
			if (farm == null) {
				return ResultGenerator.genFailResult("无效的农场标识");
			}
		}
		Map<String, Object> map = new HashMap<>();
		map.put("farmId", farm.getFarmId());
		map.put("farmCode", farm.getFarmCode());
		map.put("farmName", farm.getFarmName());
		map.put("farmLogo", farm.getLogo());
		map.put("ownerName", "-");
		String ownerId = farm.getOwnerUserId();
		if (StringUtils.isNotBlank(ownerId)) {
			SmartCultureUser owner = smartCultureUserService.findBy("userId", ownerId);
			if (owner != null) {
				map.put("ownerName", owner.getUserName());
			}
		}
		map.put("address", farm.getAddress());
		map.put("weatherCityCode", farm.getWeatherCityCode());
		map.put("qrCodeUrl", farm.getQrCodeUrl());
		map.put("provinceName", farm.getProvinceName());
		map.put("cityName", farm.getCityName());
		map.put("countyName", farm.getCountyName());
		map.put("website", farm.getWebsite());
		map.put("acreage", farm.getAcreage());
		map.put("remark", farm.getRemark());
		return ResultGenerator.genSuccessResult(map);
	}

	@SmartCultureTokenCheck
	@RequestMapping("/banners")
	public Result<?> banners(HttpServletRequest request, @RequestBody BasicWeiXinRequestParam param) {
		String farmId = param.getFarmId();
		SmartCultureFarm farm = smartCultureFarmService.findBy("farmId", farmId);
		if (farm == null) {
			return ResultGenerator.genFailResult("无效的农场编码");
		}
		Condition condition = new Condition(SmartCultureFarmPic.class);
		condition.createCriteria().andEqualTo("farmId", farmId);
		condition.orderBy("sortNum").asc();
		PageHelper.startPage(1, 10);
		List<SmartCultureFarmPic> pics = smartCultureFarmPicService.findByCondition(condition);
		List<Map<String, Object>> list = new ArrayList<>();
		if (pics != null && pics.size() > 0) {
			Map<String, Object> item = null;
			for (SmartCultureFarmPic pic : pics) {
				item = new HashMap<>();
				item.put("id", pic.getPicId());
				item.put("picUrl", pic.getPicUrl());
				list.add(item);
			}
		}
		return ResultGenerator.genSuccessResult(list);
	}

	@SmartCultureTokenCheck
	@RequestMapping("/weather/baseTxt")
	public Result<?> weatherBaseTxt(HttpServletRequest request, @RequestBody BasicWeiXinRequestParam param) {
		String cityCode = param.getWeatherCityCode();
		StringBuffer sb = new StringBuffer("");
		if (StringUtils.isNotBlank(cityCode)) {
			SmartCultureWeatherNow wn = smartCultureWeatherNowService.findBy("basicCid", cityCode);
			if (wn != null) {
				sb.append(wn.getBasicLocation());
				sb.append(", ");
				sb.append(wn.getNowCondTxt());
				sb.append("/");
				sb.append(wn.getNowTmp());
				sb.append("℃");
			}
		}
		return ResultGenerator.genSuccessResult(sb.toString());
	}

	@SmartCultureTokenCheck
	@RequestMapping("/auths")
	public Result<?> auths(HttpServletRequest request) {
		String userId = request.getAttribute("userId").toString();
		Condition condition = new Condition(SmartCultureUserFarm.class);
		condition.createCriteria().andEqualTo("userId", userId).andEqualTo("applyState", "Y");
		List<SmartCultureUserFarm> ufs = smartCultureUserFarmService.findByCondition(condition);
		List<Map<String, Object>> list = new ArrayList<>();
		if (ufs == null || ufs.isEmpty()) {
			return ResultGenerator.genSuccessResult(list);
		}
		Set<String> farmIds = new HashSet<>();
		for (SmartCultureUserFarm uf : ufs) {
			farmIds.add(uf.getFarmId());
		}
		Condition farmCondition = new Condition(SmartCultureFarm.class);
		farmCondition.createCriteria().andIn("farmId", farmIds);
		List<SmartCultureFarm> farms = smartCultureFarmService.findByCondition(farmCondition);
		Map<String, Object> item = null;
		for (SmartCultureFarm farm : farms) {
			String identity = null;
			for (SmartCultureUserFarm uf : ufs) {
				if (uf.getFarmId().equals(farm.getFarmId())) {
					identity = uf.getIdentity();
				}
			}
			if (identity == null) {
				continue;
			}
			item = new HashMap<>();
			item.put("farmId", farm.getFarmId());
			item.put("farmName", farm.getFarmName());
			item.put("farmIdentity", identity);
			list.add(item);
		}
		return ResultGenerator.genSuccessResult(list);
	}

	@SmartCultureTokenCheck
	@RequestMapping("/toApply")
	public Result<?> toApply(HttpServletRequest request, @RequestBody BasicWeiXinRequestParam param) {
		String farmId = param.getFarmId();
		SmartCultureFarm farm = smartCultureFarmService.findBy("farmId", farmId);
		if (farm == null) {
			return ResultGenerator.genFailResult("无效的农场编码");
		}
		String userId = request.getAttribute("userId").toString();

		Condition condition = new Condition(SmartCultureUserFarm.class);
		condition.createCriteria().andEqualTo("userId", userId).andEqualTo("farmId", farmId);
		List<SmartCultureUserFarm> ufs = smartCultureUserFarmService.findByCondition(condition);
		String status = "";
		if (ufs != null && ufs.size() > 0) {
			for (SmartCultureUserFarm uf : ufs) {
				if (uf.getApplyState().equals("D")) {
					status = "D";
					break;
				}
				if (uf.getApplyState().equals("Y")) {
					status = "Y";
					break;
				}
			}
		}
		if (ufs == null || ufs.isEmpty() || StringUtils.isBlank(status)) {
			SmartCultureUserFarm userFarm = new SmartCultureUserFarm();
			userFarm.setApplyAt(new Date());
			userFarm.setApplyRemark("MP_SCAN");
			userFarm.setApplyState("D");
			userFarm.setFarmId(farmId);
			userFarm.setHandleAt(null);
			userFarm.setHandleUserId(null);
			userFarm.setIdentity("visitor");
			userFarm.setUserId(userId);
			smartCultureUserFarmService.save(userFarm);
			status = "SUC";
		}
		return ResultGenerator.genSuccessResult(status);
	}

	@SmartCultureTokenCheck
	@RequestMapping("/update")
	public Result<?> update(HttpServletRequest request, @RequestBody BasicFarmParam param) {
		String userId = request.getAttribute("userId").toString();
		String farmId = param.getFarmId();
		SmartCultureFarm farm = smartCultureFarmService.findBy("farmId", farmId);
		if (farm == null) {
			return ResultGenerator.genFailResult("无效的表单");
		}

		Condition condition = new Condition(SmartCultureUserFarm.class);
		condition.createCriteria().andEqualTo("userId", userId).andEqualTo("farmId", farmId).andEqualTo("applyState",
				"Y");
		List<SmartCultureUserFarm> ufs = smartCultureUserFarmService.findByCondition(condition);
		if (ufs == null || ufs.isEmpty()) {
			return ResultGenerator.genFailResult("暂未授权修改");
		}
		SmartCultureUserFarm uf = ufs.get(0);
		if ("visitor".equals(uf.getIdentity())) {
			return ResultGenerator.genFailResult("当前身份不允许修改");
		}
		farm.setFarmName(param.getFarmName());
		farm.setAcreage(param.getAcreage());
		farm.setAddress(param.getFarmAddress());
		farm.setRemark(param.getFarmRemark());
		farm.setWebsite(param.getWebsite());
		farm.setLogo(param.getFarmLogo());
		farm.setVersion(farm.getVersion() + 1);
		farm.setUpdateAt(new Date());
		smartCultureFarmService.update(farm);
		return ResultGenerator.genSuccessResult("SUC");
	}
}
