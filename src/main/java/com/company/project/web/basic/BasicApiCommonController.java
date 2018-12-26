package com.company.project.web.basic;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
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
import com.company.project.model.SmartCultureUser;
import com.company.project.model.SmartCultureUserIdentity;
import com.company.project.model.SmartCultureUserMenu;
import com.company.project.model.SmartCultureUserToken;
import com.company.project.model.SmartCultureWeatherCity;
import com.company.project.model.param.basic.BasicLoginParam;
import com.company.project.model.param.basic.BasicRequestParam;
import com.company.project.service.SmartCultureBasicCityService;
import com.company.project.service.SmartCultureUserIdentityService;
import com.company.project.service.SmartCultureUserMenuService;
import com.company.project.service.SmartCultureUserService;
import com.company.project.service.SmartCultureUserTokenService;
import com.company.project.service.SmartCultureWeatherCityService;
import com.company.project.unit.IdUtils;
import com.company.project.unit.Md5Util;
import com.github.pagehelper.PageHelper;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateUtil;
import tk.mybatis.mapper.entity.Condition;

@RestController
@RequestMapping("/basic/api/")
public class BasicApiCommonController {
	final Logger logger = LoggerFactory.getLogger(BasicApiCommonController.class);

	// token有效时长
	public final static int TOKEN_VALID_TIME = 2;

	@Resource
	SmartCultureUserService smartCultureUserService;
	@Resource
	SmartCultureUserIdentityService smartCultureUserIdentityService;
	@Resource
	SmartCultureUserTokenService smartCultureUserTokenService;
	@Resource
	SmartCultureBasicCityService smartCultureBasicCityService;
	@Resource
	SmartCultureUserMenuService smartCultureUserMenuService;
	@Resource
	SmartCultureWeatherCityService smartCultureWeatherCityService;

	// PC登陆
	@PostMapping("/login")
	public Result<?> login(@RequestBody BasicLoginParam param) {
		if (StringUtils.isBlank(param.getUserName())) {
			return ResultGenerator.genFailResult("E5001");
		}
		if (StringUtils.isBlank(param.getPassword())) {
			return ResultGenerator.genFailResult("E5002");
		}
		SmartCultureUser user = smartCultureUserService.findBy("userName", param.getUserName());
		if (user == null) {
			return ResultGenerator.genFailResult("E5003");
		}
		if (user.getAccountState().intValue() != 0) {
			return ResultGenerator.genFailResult("EState" + user.getAccountState().intValue());
		}
		String password = Md5Util.md5(param.getPassword(), user.getUserName());
		if (!user.getPassword().equals(password)) {
			return ResultGenerator.genFailResult("E5004");
		}
		Date now = new Date();
		Condition condition = new Condition(SmartCultureUserToken.class);
		condition.createCriteria().andEqualTo("userId", user.getUserId()).andEqualTo("sourceType", "PC")
				.andEqualTo("isForbidden", 0).andGreaterThan("overdueAt", now);
		List<SmartCultureUserToken> userTokens = smartCultureUserTokenService.findByCondition(condition);
		SmartCultureUserToken userToken = null;
		if (userTokens == null || userTokens.isEmpty()) {
			userToken = new SmartCultureUserToken();
			userToken.setUserId(user.getUserId());
			userToken.setToken(IdUtils.initUuid() + System.currentTimeMillis());
			userToken.setCreateAt(now);
			userToken.setLastVisitAt(now);
			userToken.setOverdueAt(DateUtil.offset(now, DateField.HOUR, TOKEN_VALID_TIME));
			userToken.setIsForbidden(0);
			smartCultureUserTokenService.save(userToken);
		} else {
			userToken = userTokens.get(0);
			userToken.setLastVisitAt(now);
			userToken.setOverdueAt(DateUtil.offset(now, DateField.HOUR, TOKEN_VALID_TIME));
			smartCultureUserTokenService.update(userToken);
		}
		Map<String, Object> result = new HashMap<>();
		result.put("token", userToken.getToken());
		result.put("avator", user.getUserAvator());
		result.put("userName", user.getUserName());
		result.put("access", getUserIndentities(user.getUserId()));
		return ResultGenerator.genSuccessResult(result);
	}

	private List<String> getUserIndentities(String userId) {
		List<String> accesses = new ArrayList<String>();
		Condition systemUserIdentityCondition = new Condition(SmartCultureUserIdentity.class);
		systemUserIdentityCondition.createCriteria().andEqualTo("userId", userId).andEqualTo("useState", 1);
		List<SmartCultureUserIdentity> identities = smartCultureUserIdentityService
				.findByCondition(systemUserIdentityCondition);
		if (identities != null && identities.size() > 0) {
			for (SmartCultureUserIdentity identity : identities) {
				accesses.add(identity.getIdentity());
			}
		}
		Condition userMenuCondition = new Condition(SmartCultureUserMenu.class);
		userMenuCondition.createCriteria().andEqualTo("userId", userId).andEqualTo("validState", 1);
		List<SmartCultureUserMenu> userMenus = smartCultureUserMenuService.findByCondition(userMenuCondition);
		if (userMenus != null && userMenus.size() > 0) {
			for (SmartCultureUserMenu um : userMenus) {
				accesses.add(um.getMenuAccessKey());
			}
		}

		return accesses;
	}

	@SmartCultureTokenCheck
	@PostMapping("/userInfo")
	public Result<?> userInfo(HttpServletRequest request) {
		String userId = request.getAttribute("userId").toString();
		SmartCultureUser user = smartCultureUserService.findBy("userId", userId);
		if (user == null) {
			return ResultGenerator.genFailResult("E5003");
		}
		Map<String, Object> result = new HashMap<>();
		result.put("avator", user.getUserAvator());
		result.put("userName", user.getUserName());
		result.put("access", getUserIndentities(user.getUserId()));
		return ResultGenerator.genSuccessResult(result);
	}

	@SmartCultureTokenCheck
	@PostMapping("/logout")
	public Result<?> logout(HttpServletRequest request) {
		return ResultGenerator.genSuccessResult("suc");
	}

	// @SmartCultureTokenCheck
	// @PostMapping("/areaRoots")
	// public Result<?> areas(HttpServletRequest request) {
	// List<AreaResult> roots = new ArrayList<>();
	// Condition condition = new Condition(SmartCultureBasicCity.class);
	// condition.setOrderByClause("code asc");
	// List<SmartCultureBasicCity> alls =
	// smartCultureBasicCityService.findByCondition(condition);
	// if (alls != null && alls.size() > 0) {
	// // for (SmartCultureBasicCity a : alls) {
	// // String pinyin = "";
	// // try {
	// // pinyin = PinyinHelper.convertToPinyinString(a.getName(), "",
	// // PinyinFormat.WITHOUT_TONE);
	// // } catch (PinyinException e1) {
	// // e1.printStackTrace();
	// // }
	// // a.setPinyin(pinyin);
	// // try {
	// // a.setJianpin(PinyinHelper.getShortPinyin(a.getName()));
	// // } catch (PinyinException e1) {
	// // a.setJianpin("");
	// // }
	// // smartCultureBasicCityService.update(a);
	// // }
	// AreaResult e = null;
	// Iterator<SmartCultureBasicCity> it = alls.iterator();
	// while (it.hasNext()) {
	// SmartCultureBasicCity x = it.next();
	// if (x.getPcode() == null || x.getPcode().equals("86")) {
	// e = new AreaResult();
	// e.setValue(x.getCode().toString());
	// e.setLabel(x.getName());
	// e.setChildren(new ArrayList<>());
	// e.setLeaf(false);
	// roots.add(e);
	// it.remove();
	// }
	// }
	// if (roots.size() > 0) {
	// for (AreaResult alm : roots) {
	// Iterator<SmartCultureBasicCity> it2 = alls.iterator();
	// while (it2.hasNext()) {
	// SmartCultureBasicCity x = it2.next();
	// if (alm.getValue().equals(x.getPcode().toString())) {
	// e = new AreaResult();
	// e.setValue(x.getCode().toString());
	// e.setLabel(x.getName());
	// e.setChildren(new ArrayList<>());
	// e.setLeaf(false);
	// alm.getChildren().add(e);
	// }
	// }
	// List<AreaResult> almLasts = alm.getChildren();
	// for (AreaResult almLast : almLasts) {
	// Iterator<SmartCultureBasicCity> it3 = alls.iterator();
	// while (it3.hasNext()) {
	// SmartCultureBasicCity x = it3.next();
	// if (almLast.getValue().equals(x.getPcode().toString())) {
	// e = new AreaResult();
	// e.setValue(x.getCode().toString());
	// e.setLabel(x.getName());
	// e.setChildren(new ArrayList<>());
	// e.setLeaf(true);
	// almLast.getChildren().add(e);
	// }
	// }
	// }
	// }
	// }
	// }
	// return ResultGenerator.genSuccessResult(roots);
	// }

	@SmartCultureTokenCheck
	@PostMapping("/areaLevels")
	public Result<?> areaLevels(HttpServletRequest request, @RequestBody BasicRequestParam param) {
		List<Map<String, Object>> roots = new ArrayList<>();
		Condition condition = new Condition(SmartCultureBasicCity.class);
		if (StringUtils.isNotBlank(param.getSearchValue())) {
			condition.createCriteria().andEqualTo("pcode", param.getSearchValue());
		} else {
			condition.createCriteria().andEqualTo("pcode", 86);
		}
		condition.setOrderByClause("code asc");
		List<SmartCultureBasicCity> bcs = smartCultureBasicCityService.findByCondition(condition);
		if (bcs != null && bcs.size() > 0) {
			Map<String, Object> e = null;
			for (SmartCultureBasicCity x : bcs) {
				e = new HashMap<>();
				e.put("value", x.getCode());
				e.put("label", x.getName());
				if (x.getLevelNo() < 3) {
					e.put("isLeaf", false);
					e.put("loading", false);
					e.put("children", new ArrayList<>());
				} else {
					e.put("isLeaf", true);
				}
				roots.add(e);
			}
		}
		return ResultGenerator.genSuccessResult(roots);
	}

	// HE1608112120041912 ecea64c0a5d74ae5aabd36f1af33920a 通用
	// HE1812252357101084 64b71686e5014d5cb3f6cc4a6581e984 Web
	@SmartCultureTokenCheck
	@PostMapping("/weatherCities")
	public Result<?> weatherCities(HttpServletRequest request, @RequestBody BasicRequestParam param) {
		List<Map<String, String>> cities = new ArrayList<>();
		Condition condition = new Condition(SmartCultureWeatherCity.class);
		if (StringUtils.isNotBlank(param.getSearchValue())) {
			String kw = String.format("%%%s%%", param.getSearchValue().trim());
			condition.createCriteria().orLike("cityCode", kw).orLike("cityName", kw).orLike("cityPinyin", kw)
					.orLike("provinceName", kw).orLike("provincePinyin", kw);
		}
		condition.orderBy("cityCode").asc();
		PageHelper.startPage(1, 20);
		List<SmartCultureWeatherCity> wcs = smartCultureWeatherCityService.findByCondition(condition);
		if (wcs != null && wcs.size() > 0) {
			Map<String, String> item = null;
			for (SmartCultureWeatherCity wc : wcs) {
				item = new HashMap<>();
				item.put("value", wc.getCityCode());
				item.put("label", String.format("%s/%s (%s/%s)", wc.getProvinceName(), wc.getCityName(),
						wc.getProvincePinyin(), wc.getCityPinyin()));
				item.put("cityName", wc.getCityName());
				item.put("provinceName", wc.getProvinceName());
				cities.add(item);
			}
		}
		return ResultGenerator.genSuccessResult(cities);
	}

	// public static void main(String[] args) {
	// System.out.println(Md5Util.md5("123456", "sizhongxia"));
	// // 5c1b4f857ba94f15347cc6e6
	// // 5c1b4f8b7ba96cf92a2fe7ba
	// }

}
