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
import org.springframework.web.bind.annotation.PostMapping;
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
import com.company.project.unit.IdUtils;
import com.github.pagehelper.PageHelper;

import cn.hutool.core.date.DateUtil;
import tk.mybatis.mapper.entity.Condition;
import tk.mybatis.mapper.entity.Example.Criteria;

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
			userFarm.setResId(IdUtils.initObjectId());
			userFarm.setApplyAt(new Date());
			userFarm.setApplyRemark("MP_SCAN");
			userFarm.setApplyState("D");
			userFarm.setFarmId(farmId);
			userFarm.setHandleAt(null);
			userFarm.setHandleUserId(null);
			// 默认访客
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
		return ResultGenerator.genSuccessResult();
	}

	@SmartCultureTokenCheck
	@RequestMapping("/auths")
	public Result<?> auths(HttpServletRequest request, @RequestBody BasicWeiXinRequestParam param) {
		String userId = request.getAttribute("userId").toString();
		String state = param.getState();
		if (StringUtils.isBlank(state)) {
			state = "Y";
		}
		Condition condition = new Condition(SmartCultureUserFarm.class);
		condition.createCriteria().andEqualTo("userId", userId).andEqualTo("applyState", state);
		condition.orderBy("applyAt").desc();
		List<SmartCultureUserFarm> ufs = smartCultureUserFarmService.findByCondition(condition);

		List<Map<String, Object>> list = new ArrayList<>();
		if (ufs == null || ufs.isEmpty()) {
			return ResultGenerator.genSuccessResult(list);
		}
		Set<String> farmIds = new HashSet<>();
		for (SmartCultureUserFarm uf : ufs) {
			farmIds.add(uf.getFarmId());
		}
		Map<String, SmartCultureFarm> idMaps = new HashMap<>();
		if (farmIds.size() > 0) {
			if (farmIds.size() > 0) {
				condition = new Condition(SmartCultureFarm.class);
				condition.createCriteria().andIn("farmId", farmIds);
				List<SmartCultureFarm> farmls = smartCultureFarmService.findByCondition(condition);
				for (SmartCultureFarm f : farmls) {
					idMaps.put(f.getFarmId(), f);
				}
			}
		}
		Map<String, Object> item = null;
		for (SmartCultureUserFarm af : ufs) {
			SmartCultureFarm farm = idMaps.get(af.getFarmId());
			if (farm == null) {
				smartCultureUserFarmService.deleteById(af.getId());
				continue;
			}
			item = new HashMap<>();
			item.put("resId", af.getResId());
			item.put("farmId", af.getFarmId());
			item.put("farmName", farm.getFarmName());
			item.put("farmCode", farm.getFarmCode());
			item.put("farmLogo", farm.getLogo());
			item.put("address", farm.getAddress());
			item.put("farmIdentity", af.getIdentity());
			item.put("farmIdentityTxt",
					af.getIdentity().equals("admin") ? "管理员" : af.getIdentity().equals("manager") ? "运维人员" : "访客");
			item.put("applyAt", DateUtil.format(af.getApplyAt(), "yyyy-MM-dd HH:mm:ss"));
			item.put("applyRemark", af.getApplyRemark());
			item.put("applyState", af.getApplyState());
			item.put("applyStateTxt",
					af.getApplyState().equals("Y") ? "已授权" : af.getApplyState().equals("D") ? "待审核" : "已拒绝");
			item.put("handleAt",
					af.getHandleAt() == null ? "" : DateUtil.format(af.getHandleAt(), "yyyy-MM-dd HH:mm:ss"));
			item.put("handleUserId", af.getHandleUserId() == null ? "" : af.getHandleUserId());
			list.add(item);
		}
		return ResultGenerator.genSuccessResult(list);
	}

	@SmartCultureTokenCheck
	@RequestMapping("/userApplys")
	public Result<?> userApplys(HttpServletRequest request, @RequestBody BasicWeiXinRequestParam param) {
		List<Map<String, Object>> list = new ArrayList<>();
		Condition condition = new Condition(SmartCultureUserFarm.class);
		Criteria criteria = condition.createCriteria().andEqualTo("farmId", param.getFarmId());
		String state = param.getState();
		if ("D".equals(state)) {
			criteria.andEqualTo("applyState", "D");
		} else {
			Set<String> values = new HashSet<>();
			values.add("Y");
			values.add("N");
			criteria.andIn("applyState", values);
		}
		condition.orderBy("applyAt").desc();
		List<SmartCultureUserFarm> authFarms = smartCultureUserFarmService.findByCondition(condition);
		Set<String> userIds = new HashSet<String>();
		if (authFarms != null && authFarms.size() > 0) {
			// 获取用户ID
			for (SmartCultureUserFarm af : authFarms) {
				userIds.add(af.getUserId());
			}
			Map<String, SmartCultureUser> idMaps = new HashMap<>();
			if (userIds.size() > 0) {
				condition = new Condition(SmartCultureUser.class);
				condition.createCriteria().andIn("userId", userIds);
				List<SmartCultureUser> users = smartCultureUserService.findByCondition(condition);
				for (SmartCultureUser u : users) {
					idMaps.put(u.getUserId(), u);
				}
			}
			Map<String, Object> item = null;
			for (SmartCultureUserFarm af : authFarms) {
				item = new HashMap<>();
				SmartCultureUser user = idMaps.get(af.getUserId());
				item.put("resId", af.getResId());
				item.put("userId", af.getUserId());
				item.put("userName", user.getUserName());
				item.put("userAvator", user.getUserAvator());
				item.put("userPhoneNo", user.getPhoneNo());
				item.put("applyRemark", af.getApplyRemark());
				item.put("farmIdentity", af.getIdentity());
				item.put("farmIdentityTxt",
						af.getIdentity().equals("admin") ? "管理员" : af.getIdentity().equals("manager") ? "运维人员" : "访客");
				item.put("applyAt", DateUtil.format(af.getApplyAt(), "yyyy-MM-dd HH:mm:ss"));
				item.put("applyRemark", af.getApplyRemark());
				item.put("applyState", af.getApplyState());
				item.put("applyStateTxt",
						af.getApplyState().equals("Y") ? "已授权" : af.getApplyState().equals("D") ? "待审核" : "已拒绝");
				item.put("handleAt",
						af.getHandleAt() == null ? "" : DateUtil.format(af.getHandleAt(), "yyyy-MM-dd HH:mm:ss"));
				list.add(item);
			}
		}
		return ResultGenerator.genSuccessResult(list);
	}

	@SmartCultureTokenCheck
	@PostMapping("/userApplyHandle")
	public Result<?> userApplyHandle(HttpServletRequest request, @RequestBody BasicWeiXinRequestParam param) {
		if (StringUtils.isBlank(param.getResId())) {
			return ResultGenerator.genFailResult("无效的记录");
		}
		if (StringUtils.isBlank(param.getState())) {
			return ResultGenerator.genFailResult("无效的数据");
		}
		Condition condition = new Condition(SmartCultureUserFarm.class);
		condition.createCriteria().andEqualTo("resId", param.getResId().trim());
		SmartCultureUserFarm ufarm = smartCultureUserFarmService.findBy("resId", param.getResId().trim());
		if (ufarm == null) {
			return ResultGenerator.genFailResult("无效的数据");
		}
		String cUserId = request.getAttribute("userId").toString();
		if (ufarm.getUserId().equals(cUserId)) {
			return ResultGenerator.genFailResult("暂不允许当前操作");
		}
		ufarm.setApplyState(param.getState().trim());
		ufarm.setHandleAt(new Date());
		ufarm.setHandleUserId(cUserId);
		smartCultureUserFarmService.update(ufarm);
		return ResultGenerator.genSuccessResult();
	}

	@SmartCultureTokenCheck
	@PostMapping("/mineBaseInfo")
	public Result<?> mineBaseInfo(HttpServletRequest request, @RequestBody BasicWeiXinRequestParam param) {
		if (StringUtils.isBlank(param.getFarmId())) {
			return ResultGenerator.genFailResult("无效的记录");
		}
		SmartCultureFarm farm = smartCultureFarmService.findBy("farmId", param.getFarmId());
		if (farm == null) {
			return ResultGenerator.genFailResult("无效的数据");
		}
		Map<String, Object> data = new HashMap<>();
		data.put("farmName", farm.getFarmName());

		Condition condition = new Condition(SmartCultureUserFarm.class);
		condition.createCriteria().andEqualTo("farmId", param.getFarmId()).andEqualTo("applyState", "D");
		List<SmartCultureUserFarm> authFarms = smartCultureUserFarmService.findByCondition(condition);
		int auditsNum = 0;
		if (authFarms != null && authFarms.size() > 0) {
			auditsNum = authFarms.size();
		}
		data.put("auditsNum", auditsNum);
		return ResultGenerator.genSuccessResult(data);
	}

}
