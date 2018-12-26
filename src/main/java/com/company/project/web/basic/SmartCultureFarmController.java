package com.company.project.web.basic;

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
import com.company.project.model.SmartCultureBasicCity;
import com.company.project.model.SmartCultureEquipment;
import com.company.project.model.SmartCultureFarm;
import com.company.project.model.SmartCultureFarmArea;
import com.company.project.model.SmartCultureUser;
import com.company.project.model.SmartCultureUserFarm;
import com.company.project.model.SmartCultureWeatherCity;
import com.company.project.model.param.basic.BasicFarmParam;
import com.company.project.model.param.basic.BasicRequestParam;
import com.company.project.model.returns.basic.BasicPageResult;
import com.company.project.service.SmartCultureBasicCityService;
import com.company.project.service.SmartCultureEquipmentService;
import com.company.project.service.SmartCultureFarmAreaService;
import com.company.project.service.SmartCultureFarmService;
import com.company.project.service.SmartCultureUserFarmService;
import com.company.project.service.SmartCultureUserService;
import com.company.project.service.SmartCultureWeatherCityService;
import com.company.project.unit.IdUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.RandomUtil;
import tk.mybatis.mapper.entity.Condition;
import tk.mybatis.mapper.entity.Example.Criteria;
import tk.mybatis.mapper.entity.Example.OrderBy;

@RestController
@RequestMapping("/basic/api/farm")
public class SmartCultureFarmController {
	final Logger logger = LoggerFactory.getLogger(SmartCultureFarmController.class);

	@Resource
	SmartCultureFarmService smartCultureFarmService;
	@Resource
	SmartCultureFarmAreaService smartCultureFarmAreaService;
	@Resource
	SmartCultureBasicCityService smartCultureBasicCityService;
	@Resource
	SmartCultureUserService smartCultureUserService;
	@Resource
	SmartCultureUserFarmService smartCultureUserFarmService;
	@Resource
	SmartCultureEquipmentService smartCultureEquipmentService;
	@Resource
	SmartCultureWeatherCityService smartCultureWeatherCityService;

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

		Condition condition = new Condition(SmartCultureFarm.class);
		Criteria cr = condition.createCriteria();
		String name = param.getName();
		if (StringUtils.isNotBlank(name)) {
			cr.andLike("farmName", String.format("%%%s%%", name.trim()));
		}
		String code = param.getCode();
		if (StringUtils.isNotBlank(code)) {
			cr.andLike("farmCode", String.format("%%%s%%", code.trim()));
		}
		if (StringUtils.isNotBlank(param.getOrderField())) {
			OrderBy ob = condition.orderBy(param.getOrderField());
			if ("desc".equals(param.getOrderType())) {
				ob.desc();
			} else {
				ob.asc();
			}
		} else {
			condition.orderBy("farmCode").desc();
		}
		long total = 0;
		PageHelper.startPage(current, sizeNum, true);
		List<SmartCultureFarm> objs = smartCultureFarmService.findByCondition(condition);
		if (objs != null && objs.size() > 0) {
			Map<String, Object> map = null;
			for (SmartCultureFarm obj : objs) {
				map = new HashMap<>();
				map.put("farmId", obj.getFarmId());
				map.put("farmName", obj.getFarmName());
				map.put("farmCode", obj.getFarmCode());
				map.put("provinceName", obj.getProvinceName());
				map.put("cityName", obj.getCityName());
				map.put("countyName", obj.getCountyName());
				map.put("weatherCityName", obj.getWeatherCityName());
				map.put("address", obj.getAddress());
				map.put("createAt", DateUtil.format(obj.getCreateAt(), "yyyy-MM-dd HH:mm:ss"));
				map.put("updateAt", DateUtil.format(obj.getUpdateAt(), "yyyy-MM-dd HH:mm:ss"));
				list.add(map);
			}
			Page<SmartCultureFarm> pageInfo = (Page<SmartCultureFarm>) objs;
			total = pageInfo.getTotal();
		}

		result.setTotal(total);
		result.setList(list);
		return ResultGenerator.genSuccessResult(result);
	}

	@SmartCultureTokenCheck
	@PostMapping("/upinsert")
	public Result<?> upinsert(HttpServletRequest request, @RequestBody BasicFarmParam param) {
		String farmId = param.getFarmId();
		Date now = new Date();
		SmartCultureFarm farm = null;
		if (StringUtils.isBlank(farmId)) {
			// 新增
			farm = new SmartCultureFarm();
			farm.setFarmId(IdUtils.initObjectId());
			String farmCode = "";
			SmartCultureFarm scf = null;
			do {
				farmCode = String.format("%s%s", DateUtil.format(now, "yyMMddHHmmSSSS"), RandomUtil.randomNumbers(4));
				scf = smartCultureFarmService.findBy("farmCode", farmCode);
			} while (scf != null);
			farm.setFarmCode(farmCode);
			farm.setOwnerUserId(param.getOwnerUserId() == null ? "" : param.getOwnerUserId().trim());
			farm.setLogo("http://static.yeetong.cn/default-farm.png-yeetong");
			farm.setVersion(1L);
			farm.setCreateAt(now);
			farm.setUpdateAt(now);
		} else {
			farm = smartCultureFarmService.findBy("farmId", param.getFarmId());
			if (farm == null) {
				return ResultGenerator.genFailResult("E5001");
			}
			farm.setVersion(farm.getVersion() + 1);
			farm.setUpdateAt(now);
		}

		farm.setFarmName(param.getFarmName());
		farm.setOrganizeId(param.getOrganizeId() == null ? "" : param.getOrganizeId().trim());
		if (StringUtils.isBlank(param.getWeatherCityCode())) {
			farm.setWeatherCityCode("");
			farm.setWeatherCityName("");
		} else {
			SmartCultureWeatherCity wc = smartCultureWeatherCityService.findBy("cityCode",
					param.getWeatherCityCode().trim());
			if (wc == null) {
				return ResultGenerator.genFailResult("E5002");
			}
			farm.setWeatherCityCode(wc.getCityCode());
			farm.setWeatherCityName(wc.getCityName());
		}
		farm.setProvinceCode("");
		farm.setProvinceName("");
		farm.setCityCode("");
		farm.setCityName("");
		farm.setCountyCode("");
		farm.setCountyName("");
		String[] areas = param.getFarmArea();
		if (areas.length > 0) {
			if (areas.length > 0) {
				SmartCultureBasicCity sc = smartCultureBasicCityService.findBy("code", areas[0]);
				if (sc != null) {
					farm.setProvinceCode(sc.getCode());
					farm.setProvinceName(sc.getName());
				}
			}
			if (areas.length > 1) {
				SmartCultureBasicCity sc = smartCultureBasicCityService.findBy("code", areas[1]);
				if (sc != null) {
					farm.setCityCode(sc.getCode());
					farm.setCityName(sc.getName());
				}
			}
			if (areas.length > 2) {
				SmartCultureBasicCity sc = smartCultureBasicCityService.findBy("code", areas[2]);
				if (sc != null) {
					farm.setCountyCode(sc.getCode());
					farm.setCountyName(sc.getName());
				}
			}
		}
		farm.setLongitude(param.getLongitude() == null ? "" : param.getLongitude().trim());
		farm.setLatitude(param.getLatitude() == null ? "" : param.getLatitude().trim());
		farm.setAddress(param.getFarmAddress() == null ? "" : param.getFarmAddress().trim());
		farm.setWebsite(param.getWebsite() == null ? "" : param.getWebsite().trim());
		farm.setRemark(param.getFarmRemark() == null ? "" : param.getFarmRemark().trim());

		if (farm.getId() != null && farm.getId().longValue() > 0) {
			smartCultureFarmService.update(farm);
		} else {
			smartCultureFarmService.save(farm);
		}
		return ResultGenerator.genSuccessResult();
	}

	// public static void main(String[] args) {
	// System.out.println(DateUtil.format(new Date(), "yyMMddHHmmSSSS") +
	// RandomUtil.randomNumbers(4));
	// }

	@SmartCultureTokenCheck
	@PostMapping("/delete")
	public Result<?> delete(HttpServletRequest request, @RequestBody BasicRequestParam param) {
		if (StringUtils.isBlank(param.getResultId())) {
			return ResultGenerator.genFailResult("E5001");
		}
		SmartCultureFarm farm = smartCultureFarmService.findBy("farmId", param.getResultId());
		if (farm == null) {
			return ResultGenerator.genFailResult("E5002");
		}
		Condition condition = new Condition(SmartCultureFarmArea.class);
		condition.createCriteria().andEqualTo("farmId", farm.getFarmId());
		List<SmartCultureFarmArea> cfas = smartCultureFarmAreaService.findByCondition(condition);
		if (cfas != null && cfas.size() > 0) {
			return ResultGenerator.genFailResult("E5003");
		}
		condition = new Condition(SmartCultureEquipment.class);
		condition.createCriteria().andEqualTo("farmId", farm.getFarmId());
		List<SmartCultureEquipment> ces = smartCultureEquipmentService.findByCondition(condition);
		if (ces != null && ces.size() > 0) {
			return ResultGenerator.genFailResult("E5004");
		}
		smartCultureFarmService.deleteById(farm.getId());
		return ResultGenerator.genSuccessResult();
	}

	@SmartCultureTokenCheck
	@PostMapping("/all")
	public Result<?> all(HttpServletRequest request) {
		List<Map<String, Object>> list = new ArrayList<>();
		Condition condition = new Condition(SmartCultureFarm.class);
		condition.orderBy("farmName").asc();
		List<SmartCultureFarm> objs = smartCultureFarmService.findByCondition(condition);
		if (objs != null && objs.size() > 0) {
			Map<String, Object> map = null;
			for (SmartCultureFarm obj : objs) {
				map = new HashMap<>();
				map.put("farmId", obj.getFarmId());
				map.put("farmName", obj.getFarmName());
				list.add(map);
			}
		}
		return ResultGenerator.genSuccessResult(list);
	}

	@SmartCultureTokenCheck
	@PostMapping("/detail")
	public Result<?> detail(HttpServletRequest request, @RequestBody BasicRequestParam param) {
		if (StringUtils.isBlank(param.getResultId())) {
			return ResultGenerator.genFailResult("E5001");
		}
		SmartCultureFarm farm = smartCultureFarmService.findBy("farmId", param.getResultId());
		if (farm == null) {
			return ResultGenerator.genFailResult("E5002");
		}
		Map<String, Object> map = new HashMap<>();
		map.put("farmId", farm.getFarmId());
		map.put("farmName", farm.getFarmName());
		map.put("farmCode", farm.getFarmCode());
		map.put("organizeId", farm.getOrganizeId());
		map.put("ownerUserId", farm.getOwnerUserId());
		List<String> areaCodes = new ArrayList<>();
		if (StringUtils.isNotBlank(farm.getProvinceCode())) {
			areaCodes.add(farm.getProvinceCode());
			if (StringUtils.isNotBlank(farm.getCityCode())) {
				areaCodes.add(farm.getCityCode());
				if (StringUtils.isNotBlank(farm.getCountyCode())) {
					areaCodes.add(farm.getCountyCode());
				}
			}
		}
		map.put("farmArea", areaCodes);
		map.put("provinceName", farm.getProvinceName());
		map.put("cityName", farm.getCityName());
		map.put("countyName", farm.getCountyName());
		map.put("weatherCityCode", farm.getWeatherCityCode());
		map.put("weatherCityName", farm.getWeatherCityName());
		map.put("logo", farm.getLogo());
		map.put("longitude", farm.getLongitude());
		map.put("latitude", farm.getLatitude());
		map.put("farmAddress", farm.getAddress());
		map.put("website", farm.getWebsite());
		map.put("farmRemark", farm.getRemark());
		map.put("createAt", DateUtil.format(farm.getCreateAt(), "yyyy-MM-dd HH:mm:ss"));
		map.put("updateAt", DateUtil.format(farm.getUpdateAt(), "yyyy-MM-dd HH:mm:ss"));
		return ResultGenerator.genSuccessResult(map);
	}

	@SmartCultureTokenCheck
	@PostMapping("/ownerInfo")
	public Result<?> ownerInfo(HttpServletRequest request, @RequestBody BasicRequestParam param) {
		if (StringUtils.isBlank(param.getResultId())) {
			return ResultGenerator.genFailResult("E5001");
		}
		SmartCultureFarm farm = smartCultureFarmService.findBy("farmId", param.getResultId());
		if (farm == null) {
			return ResultGenerator.genFailResult("E5002");
		}
		Map<String, Object> map = new HashMap<>();
		map.put("farmId", farm.getFarmId());
		map.put("ownerUserId", farm.getOwnerUserId());
		map.put("ownerUserName", "无");
		map.put("ownerUserPhone", "-");
		if (StringUtils.isNotBlank(farm.getOwnerUserId())) {
			SmartCultureUser user = smartCultureUserService.findBy("userId", farm.getOwnerUserId());
			if (user != null) {
				map.put("ownerUserName", user.getUserName());
				map.put("ownerUserPhone", StringUtils.isBlank(user.getPhoneNo()) ? "-" : user.getPhoneNo());
			}
		}
		return ResultGenerator.genSuccessResult(map);
	}

	@SmartCultureTokenCheck
	@PostMapping("/changeOwner")
	public Result<?> changeOwner(HttpServletRequest request, @RequestBody BasicFarmParam param) {
		if (StringUtils.isBlank(param.getFarmId())) {
			return ResultGenerator.genFailResult("E5001");
		}
		SmartCultureFarm farm = smartCultureFarmService.findBy("farmId", param.getFarmId());
		if (farm == null) {
			return ResultGenerator.genFailResult("E5002");
		}
		farm.setOwnerUserId(param.getOwnerUserId() == null ? "" : param.getOwnerUserId());
		smartCultureFarmService.update(farm);
		return ResultGenerator.genSuccessResult();
	}

	@SmartCultureTokenCheck
	@PostMapping("/authUsers")
	public Result<?> authUsers(HttpServletRequest request, @RequestBody BasicRequestParam param) {
		List<Map<String, Object>> list = new ArrayList<>();
		Condition condition = new Condition(SmartCultureUserFarm.class);
		condition.createCriteria().andEqualTo("farmId", param.getResultId());
		condition.orderBy("applyAt").desc();
		List<SmartCultureUserFarm> authFarms = smartCultureUserFarmService.findByCondition(condition);
		Set<String> userIds = new HashSet<String>();
		if (authFarms != null && authFarms.size() > 0) {
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
				item.put("userId", af.getUserId());
				item.put("farmId", af.getFarmId());
				item.put("userName", user.getUserName());
				item.put("userPhoneNo", user.getPhoneNo());
				item.put("identity", af.getIdentity());
				item.put("applyAt", DateUtil.format(af.getApplyAt(), "yyyy-MM-dd HH:mm:ss"));
				item.put("applyRemark", af.getApplyRemark());
				item.put("applyState", af.getApplyState());
				item.put("handleAt",
						af.getHandleAt() == null ? "" : DateUtil.format(af.getHandleAt(), "yyyy-MM-dd HH:mm:ss"));
				item.put("handleUserId", af.getHandleUserId() == null ? "" : af.getHandleUserId());
				list.add(item);
			}
		}
		return ResultGenerator.genSuccessResult(list);
	}
}
