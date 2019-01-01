package com.company.project.web.basic;

import java.util.ArrayList;
import java.util.Arrays;
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
import com.company.project.model.SmartCultureFarm;
import com.company.project.model.SmartCultureMenu;
import com.company.project.model.SmartCultureUser;
import com.company.project.model.SmartCultureUserFarm;
import com.company.project.model.SmartCultureUserIdentity;
import com.company.project.model.SmartCultureUserMenu;
import com.company.project.model.param.basic.BasicRequestParam;
import com.company.project.model.param.basic.BasicUserFarmAuthParam;
import com.company.project.model.param.basic.BasicUserMenuAuthParam;
import com.company.project.model.param.basic.BasicUserParam;
import com.company.project.model.returns.basic.BasicPageResult;
import com.company.project.service.SmartCultureFarmService;
import com.company.project.service.SmartCultureMenuService;
import com.company.project.service.SmartCultureUserFarmService;
import com.company.project.service.SmartCultureUserIdentityService;
import com.company.project.service.SmartCultureUserMenuService;
import com.company.project.service.SmartCultureUserService;
import com.company.project.unit.IdUtils;
import com.company.project.unit.Md5Util;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.RandomUtil;
import tk.mybatis.mapper.entity.Condition;
import tk.mybatis.mapper.entity.Example.Criteria;
import tk.mybatis.mapper.entity.Example.OrderBy;

@RestController
@RequestMapping("/basic/api/user")
public class SmartCultureUserController {

	final Logger logger = LoggerFactory.getLogger(SmartCultureUserController.class);

	@Resource
	SmartCultureUserService smartCultureUserService;
	@Resource
	SmartCultureUserIdentityService smartCultureUserIdentityService;
	@Resource
	SmartCultureUserFarmService smartCultureUserFarmService;
	@Resource
	SmartCultureFarmService smartCultureFarmService;
	@Resource
	SmartCultureMenuService smartCultureMenuService;
	@Resource
	SmartCultureUserMenuService smartCultureUserMenuService;

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

		Condition condition = new Condition(SmartCultureUser.class);
		Criteria cr = condition.createCriteria();
		if (StringUtils.isNotBlank(sv)) {
			cr.andLike("userName", String.format("%%%s%%", sv.trim()));
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
		List<SmartCultureUser> objs = smartCultureUserService.findByCondition(condition);
		if (objs != null && objs.size() > 0) {
			Map<String, Object> map = null;
			for (SmartCultureUser obj : objs) {
				map = new HashMap<>();
				map.put("userId", obj.getUserId());
				map.put("userName", obj.getUserName());
				map.put("password", obj.getPassword());
				map.put("userAvator", obj.getUserAvator());
				map.put("phoneNo", obj.getPhoneNo());
				map.put("email", obj.getEmail());
				map.put("accountState", obj.getAccountState());
				map.put("organizeId", obj.getOrganizeId() == null ? "" : obj.getOrganizeId());
				map.put("createAt", DateUtil.format(obj.getCreateAt(), "yyyy-MM-dd HH:mm:ss"));
				map.put("updateAt", DateUtil.format(obj.getUpdateAt(), "yyyy-MM-dd HH:mm:ss"));
				list.add(map);
			}
			Page<SmartCultureUser> pageInfo = (Page<SmartCultureUser>) objs;
			total = pageInfo.getTotal();
		}

		result.setTotal(total);
		result.setList(list);
		return ResultGenerator.genSuccessResult(result);
	}

	@SmartCultureTokenCheck
	@PostMapping("/upinsert")
	public Result<?> upinsert(HttpServletRequest request, @RequestBody BasicUserParam param) {
		if (StringUtils.isBlank(param.getUserName())) {
			return ResultGenerator.genFailResult("E5000");
		}
		String userId = param.getUserId();
		if (StringUtils.isBlank(userId)) {
			// 新增
			Condition condition = new Condition(SmartCultureUser.class);
			condition.createCriteria().andEqualTo("userName", param.getUserName());
			List<SmartCultureUser> scos = smartCultureUserService.findByCondition(condition);
			if (scos != null && scos.size() > 0) {
				return ResultGenerator.genFailResult("E5006");
			}
			condition = new Condition(SmartCultureUser.class);
			condition.createCriteria().andEqualTo("phoneNo", param.getPhoneNo());
			scos = smartCultureUserService.findByCondition(condition);
			if (scos != null && scos.size() > 0) {
				return ResultGenerator.genFailResult("E5001");
			}
			condition = new Condition(SmartCultureUser.class);
			condition.createCriteria().andEqualTo("email", param.getEmail());
			scos = smartCultureUserService.findByCondition(condition);
			if (scos != null && scos.size() > 0) {
				return ResultGenerator.genFailResult("E5002");
			}
			SmartCultureUser user = new SmartCultureUser();
			user.setUserId(IdUtils.initObjectId());
			user.setUserName(param.getUserName().trim());
			user.setPassword(Md5Util.md5("181121", param.getUserName().trim()));
			user.setUserAvator("http://static.yeetong.cn/defalut-avator.png-yeetong");
			user.setPhoneNo(param.getPhoneNo());
			user.setEmail(param.getEmail());
			user.setOrganizeId(param.getOrganizeId());
			user.setAccountState(0);
			user.setVersion(1L);
			user.setCreateAt(new Date());
			user.setUpdateAt(new Date());
			smartCultureUserService.save(user);
		} else {
			SmartCultureUser user = smartCultureUserService.findBy("userId", param.getUserId());
			if (user == null) {
				return ResultGenerator.genFailResult("E5003");
			}
			Condition condition = new Condition(SmartCultureUser.class);
			condition.createCriteria().andEqualTo("phoneNo", param.getPhoneNo());
			List<SmartCultureUser> scos = smartCultureUserService.findByCondition(condition);
			if (scos != null && scos.size() > 0) {
				for (SmartCultureUser _i : scos) {
					if (!_i.getUserId().equals(param.getUserId())) {
						return ResultGenerator.genFailResult("E5004");
					}
				}
			}
			condition = new Condition(SmartCultureUser.class);
			condition.createCriteria().andEqualTo("email", param.getEmail());
			scos = smartCultureUserService.findByCondition(condition);
			if (scos != null && scos.size() > 0) {
				for (SmartCultureUser _i : scos) {
					if (!_i.getUserId().equals(param.getUserId())) {
						return ResultGenerator.genFailResult("E5005");
					}
				}
			}
			user.setPhoneNo(param.getPhoneNo());
			user.setEmail(param.getEmail());
			user.setOrganizeId(param.getOrganizeId() == null ? "" : param.getOrganizeId().trim());
			user.setVersion(user.getVersion() + 1);
			user.setUpdateAt(new Date());
			smartCultureUserService.update(user);
		}
		return ResultGenerator.genSuccessResult();
	}

	@SmartCultureTokenCheck
	@PostMapping("/toggleState")
	public Result<?> toggleState(HttpServletRequest request, @RequestBody BasicRequestParam param) {
		if (StringUtils.isBlank(param.getResultId())) {
			return ResultGenerator.genFailResult("E5001");
		}
		SmartCultureUser user = smartCultureUserService.findBy("userId", param.getResultId());
		if (user == null) {
			return ResultGenerator.genFailResult("E5002");
		}
		user.setAccountState(user.getAccountState().intValue() == 1 ? 0 : 1);
		user.setVersion(user.getVersion() + 1);
		user.setUpdateAt(new Date());
		smartCultureUserService.update(user);
		return ResultGenerator.genSuccessResult();
	}

	@SmartCultureTokenCheck
	@PostMapping("/resetPwd")
	public Result<?> resetPwd(HttpServletRequest request, @RequestBody BasicRequestParam param) {
		if (StringUtils.isBlank(param.getResultId())) {
			return ResultGenerator.genFailResult("E5001");
		}
		SmartCultureUser user = smartCultureUserService.findBy("userId", param.getResultId());
		if (user == null) {
			return ResultGenerator.genFailResult("E5002");
		}
		String newPwd = RandomUtil.randomNumbers(6);
		user.setPassword(Md5Util.md5(newPwd, user.getUserName()));
		user.setVersion(user.getVersion() + 1);
		user.setUpdateAt(new Date());
		smartCultureUserService.update(user);
		return ResultGenerator.genSuccessResult(newPwd);
	}

	@SmartCultureTokenCheck
	@PostMapping("/identities")
	public Result<?> identities(HttpServletRequest request, @RequestBody BasicRequestParam param) {
		List<String> list = new ArrayList<String>();
		Condition condition = new Condition(SmartCultureUserIdentity.class);
		condition.createCriteria().andEqualTo("userId", param.getResultId()).andEqualTo("useState", 1);
		List<SmartCultureUserIdentity> identities = smartCultureUserIdentityService.findByCondition(condition);
		if (identities != null && identities.size() > 0) {
			for (SmartCultureUserIdentity identity : identities) {
				list.add(identity.getIdentity());
			}
		}
		return ResultGenerator.genSuccessResult(list);
	}

	@SmartCultureTokenCheck
	@PostMapping("/changeIdentity")
	public Result<?> changeIdentity(HttpServletRequest request, @RequestBody BasicUserParam param) {
		List<String> oldIndentities = new ArrayList<String>();
		Condition condition = new Condition(SmartCultureUserIdentity.class);
		condition.createCriteria().andEqualTo("userId", param.getUserId()).andEqualTo("useState", 1);
		List<SmartCultureUserIdentity> identities = smartCultureUserIdentityService.findByCondition(condition);
		Map<String, Long> id2id = new HashMap<>();
		if (identities != null && identities.size() > 0) {
			for (SmartCultureUserIdentity identity : identities) {
				oldIndentities.add(identity.getIdentity());
				id2id.put(identity.getIdentity(), identity.getId());
			}
		}

		List<String> newIndentities = new ArrayList<>();
		if (param.getIdentities() != null && param.getIdentities().length > 0) {
			newIndentities = Arrays.asList(param.getIdentities());
		}
		List<SmartCultureUserIdentity> adds = new ArrayList<>();
		for (String n : newIndentities) {
			boolean notInOld = true;
			for (String o : oldIndentities) {
				if (n.equals(o)) {
					notInOld = false;
				}
			}
			if (notInOld) {
				SmartCultureUserIdentity ui = new SmartCultureUserIdentity();
				ui.setCreateAt(new Date());
				ui.setIdentity(n);
				ui.setUpdateAt(new Date());
				ui.setUserId(param.getUserId());
				ui.setUseState(1);
				ui.setVersion(1L);
				adds.add(ui);
			}
		}
		if (adds.size() > 0) {
			smartCultureUserIdentityService.save(adds);
		}
		for (String o : oldIndentities) {
			boolean notInNew = true;
			for (String n : newIndentities) {
				if (o.equals(n)) {
					notInNew = false;
				}
			}
			if (notInNew) {
				smartCultureUserIdentityService.deleteById(id2id.get(o));
			}
		}
		return ResultGenerator.genSuccessResult();
	}

	@SmartCultureTokenCheck
	@PostMapping("/authFarm")
	public Result<?> authFarm(HttpServletRequest request, @RequestBody BasicUserFarmAuthParam param) {
		if (StringUtils.isBlank(param.getUserId())) {
			return ResultGenerator.genFailResult("E5001");
		}
		if (StringUtils.isBlank(param.getFarmId())) {
			return ResultGenerator.genFailResult("E5002");
		}
		if (StringUtils.isBlank(param.getIdentity())) {
			return ResultGenerator.genFailResult("E5003");
		}
		Condition condition = new Condition(SmartCultureUserFarm.class);
		condition.createCriteria().andEqualTo("userId", param.getUserId().trim())
				.andEqualTo("farmId", param.getFarmId().trim()).andNotEqualTo("applyState", "N");
		List<SmartCultureUserFarm> ufarms = smartCultureUserFarmService.findByCondition(condition);
		if (ufarms != null && ufarms.size() > 0) {
			return ResultGenerator.genFailResult("E5004");
		}
		SmartCultureUserFarm uf = new SmartCultureUserFarm();
		uf.setUserId(param.getUserId().trim());
		uf.setFarmId(param.getFarmId().trim());
		uf.setIdentity(param.getIdentity().trim());
		uf.setApplyAt(new Date());
		uf.setApplyRemark(param.getApplyRemark() == null ? "" : param.getApplyRemark().trim());
		uf.setApplyState("D");
		smartCultureUserFarmService.save(uf);
		return ResultGenerator.genSuccessResult();
	}

	@SmartCultureTokenCheck
	@PostMapping("/authFarms")
	public Result<?> authFarms(HttpServletRequest request, @RequestBody BasicRequestParam param) {
		List<Map<String, Object>> list = new ArrayList<>();
		Condition condition = new Condition(SmartCultureUserFarm.class);
		condition.createCriteria().andEqualTo("userId", param.getResultId());
		condition.orderBy("applyAt").desc();
		List<SmartCultureUserFarm> authFarms = smartCultureUserFarmService.findByCondition(condition);
		Set<String> farmIds = new HashSet<String>();
		if (authFarms != null && authFarms.size() > 0) {
			for (SmartCultureUserFarm af : authFarms) {
				farmIds.add(af.getFarmId());
			}
			Map<String, SmartCultureFarm> idMaps = new HashMap<>();
			if (farmIds.size() > 0) {
				condition = new Condition(SmartCultureFarm.class);
				condition.createCriteria().andIn("farmId", farmIds);
				List<SmartCultureFarm> farmls = smartCultureFarmService.findByCondition(condition);
				for (SmartCultureFarm f : farmls) {
					idMaps.put(f.getFarmId(), f);
				}
			}
			Map<String, Object> item = null;
			for (SmartCultureUserFarm af : authFarms) {
				SmartCultureFarm farm = idMaps.get(af.getFarmId());
				if (farm == null) {
					smartCultureUserFarmService.deleteById(af.getId());
					continue;
				}
				item = new HashMap<>();
				item.put("userId", af.getUserId());
				item.put("farmId", af.getFarmId());
				item.put("farmName", farm.getFarmName());
				item.put("farmCode", farm.getFarmCode());
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

	@SmartCultureTokenCheck
	@PostMapping("/query")
	public Result<?> query(HttpServletRequest request, @RequestBody BasicRequestParam param) {
		List<Map<String, Object>> list = new ArrayList<>();
		Condition condition = new Condition(SmartCultureUser.class);
		if (StringUtils.isNotBlank(param.getSearchValue())) {
			String kw = String.format("%%%s%%", param.getSearchValue().trim());
			condition.createCriteria().orLike("userName", kw).orLike("phoneNo", kw).orLike("email", kw);
		}
		condition.orderBy("userName").asc();
		PageHelper.startPage(1, 20);
		List<SmartCultureUser> users = smartCultureUserService.findByCondition(condition);
		if (users != null && users.size() > 0) {
			Map<String, Object> map = null;
			for (SmartCultureUser obj : users) {
				map = new HashMap<>();
				map.put("label", obj.getUserName());
				map.put("value", obj.getUserId());
				map.put("phoneNo", obj.getPhoneNo());
				map.put("email", obj.getEmail());
				list.add(map);
			}
		}
		return ResultGenerator.genSuccessResult(list);
	}

	@SmartCultureTokenCheck
	@PostMapping("/handleAuthApply")
	public Result<?> handleAuthApply(HttpServletRequest request, @RequestBody BasicUserFarmAuthParam param) {
		if (StringUtils.isBlank(param.getUserId())) {
			return ResultGenerator.genFailResult("E5001");
		}
		if (StringUtils.isBlank(param.getFarmId())) {
			return ResultGenerator.genFailResult("E5002");
		}
		if (StringUtils.isBlank(param.getApplyState())) {
			return ResultGenerator.genFailResult("E5003");
		}
		Condition condition = new Condition(SmartCultureUserFarm.class);
		condition.createCriteria().andEqualTo("userId", param.getUserId().trim())
				.andEqualTo("farmId", param.getFarmId().trim()).andEqualTo("applyState", "D");
		List<SmartCultureUserFarm> ufarms = smartCultureUserFarmService.findByCondition(condition);
		if (ufarms == null || ufarms.isEmpty()) {
			if (param.getApplyState().equals("N")) {
				condition = new Condition(SmartCultureUserFarm.class);
				condition.createCriteria().andEqualTo("userId", param.getUserId().trim())
						.andEqualTo("farmId", param.getFarmId().trim()).andEqualTo("applyState", "Y");
				ufarms = smartCultureUserFarmService.findByCondition(condition);
			}
			if (ufarms == null || ufarms.isEmpty()) {
				return ResultGenerator.genFailResult("E5004");
			}
		}
		SmartCultureUserFarm uf = ufarms.get(0);
		uf.setApplyState(param.getApplyState().trim());
		uf.setHandleAt(new Date());
		uf.setHandleUserId(request.getAttribute("userId").toString());
		smartCultureUserFarmService.update(uf);
		return ResultGenerator.genSuccessResult();
	}

	@SuppressWarnings("unchecked")
	@SmartCultureTokenCheck
	@PostMapping("/authMenus")
	public Result<?> authMenus(HttpServletRequest request, @RequestBody BasicUserMenuAuthParam param) {
		if (StringUtils.isBlank(param.getUserId())) {
			return ResultGenerator.genFailResult("E5001");
		}

		List<Map<String, Object>> data = new ArrayList<>();

		Condition userIdentityCondition = new Condition(SmartCultureUserIdentity.class);
		userIdentityCondition.createCriteria().andEqualTo("userId", param.getUserId().trim()).andEqualTo("useState", 1);
		List<SmartCultureUserIdentity> identities = smartCultureUserIdentityService
				.findByCondition(userIdentityCondition);
		if (identities != null && identities.size() > 0) {
			for (SmartCultureUserIdentity identity : identities) {
				if (identity.getIdentity().equals("super_admin")) {
					return ResultGenerator.genSuccessResult(data);
				}
			}
		}

		Condition condition = new Condition(SmartCultureMenu.class);
		condition.orderBy("sortNum").asc();
		List<SmartCultureMenu> menus = smartCultureMenuService.findByCondition(condition);

		Condition userMenuCondition = new Condition(SmartCultureUserMenu.class);
		userMenuCondition.createCriteria().andEqualTo("userId", param.getUserId().trim()).andEqualTo("validState", 1);
		List<SmartCultureUserMenu> userMenuAuths = smartCultureUserMenuService.findByCondition(userMenuCondition);

		Set<String> menuKeys = new HashSet<>();
		if (userMenuAuths != null && userMenuAuths.size() > 0) {
			for (SmartCultureUserMenu ma : userMenuAuths) {
				menuKeys.add(ma.getMenuAccessKey());
			}
		}

		if (menus != null && menus.size() > 0) {
			Map<String, Object> item = null;
			for (SmartCultureMenu menu : menus) {
				if (menu.getPid() == null || menu.getPid().longValue() < 1) {
					item = new HashMap<>();
					item.put("title", menu.getMenuName());
					item.put("key", menu.getMenuAccessKey());
					item.put("checked", menuKeys.contains(menu.getMenuAccessKey()));
					item.put("id", menu.getId());
					item.put("userId", param.getUserId());
					item.put("expand", true);
					// item.put("disabled", true);
					item.put("children", new ArrayList<>());
					data.add(item);
				}
			}
			if (data.size() > 0) {
				for (Map<String, Object> i : data) {
					for (SmartCultureMenu menu : menus) {
						if (menu.getPid() != null && menu.getPid().longValue() > 0) {
							if (i.get("id").toString().equals(menu.getPid().toString())) {
								item = new HashMap<>();
								item.put("title", menu.getMenuName());
								item.put("userId", param.getUserId());
								item.put("key", menu.getMenuAccessKey());
								item.put("checked", menuKeys.contains(menu.getMenuAccessKey()));
								((ArrayList<Map<String, Object>>) i.get("children")).add(item);
							}
						}
					}
				}
			}
		}
		return ResultGenerator.genSuccessResult(data);
	}

	@SmartCultureTokenCheck
	@PostMapping("/handleMenuAuth")
	public Result<?> handleMenuAuth(HttpServletRequest request, @RequestBody BasicUserMenuAuthParam param) {
		if (StringUtils.isBlank(param.getUserId())) {
			return ResultGenerator.genFailResult("E5001");
		}
		if (StringUtils.isBlank(param.getMenuAccessKey())) {
			return ResultGenerator.genFailResult("E5002");
		}
		if (StringUtils.isBlank(param.getChecked())) {
			return ResultGenerator.genFailResult("E5003");
		}

		SmartCultureMenu smartCultureMenu = smartCultureMenuService.findBy("menuAccessKey",
				param.getMenuAccessKey().trim());
		if (smartCultureMenu == null) {
			return ResultGenerator.genFailResult("E5006");
		}

		Condition condition = new Condition(SmartCultureUserMenu.class);
		condition.createCriteria().andEqualTo("userId", param.getUserId().trim())
				.andEqualTo("menuAccessKey", param.getMenuAccessKey().trim()).andEqualTo("validState", 1);
		List<SmartCultureUserMenu> uMenus = smartCultureUserMenuService.findByCondition(condition);
		if (uMenus == null || uMenus.isEmpty()) {
			if ("Y".equals(param.getChecked())) {
				SmartCultureUserMenu um = new SmartCultureUserMenu();
				um.setMenuAccessKey(param.getMenuAccessKey().trim());
				um.setUserId(param.getUserId().trim());
				um.setValidState(1);
				um.setCreateAt(new Date());
				um.setUpdateAt(new Date());
				smartCultureUserMenuService.save(um);
				if (smartCultureMenu.getPid() == null) {
					// 是一级菜单
					condition = new Condition(SmartCultureMenu.class);
					condition.createCriteria().andEqualTo("pid", smartCultureMenu.getId());
					List<SmartCultureMenu> childMenus = smartCultureMenuService.findByCondition(condition);
					if (childMenus != null && childMenus.size() > 0) {
						for (SmartCultureMenu cm : childMenus) {
							condition = new Condition(SmartCultureUserMenu.class);
							condition.createCriteria().andEqualTo("userId", param.getUserId().trim())
									.andEqualTo("menuAccessKey", cm.getMenuAccessKey()).andEqualTo("validState", 1);
							uMenus = smartCultureUserMenuService.findByCondition(condition);
							if (uMenus == null || uMenus.isEmpty()) {
								um = new SmartCultureUserMenu();
								um.setMenuAccessKey(cm.getMenuAccessKey());
								um.setUserId(param.getUserId().trim());
								um.setValidState(1);
								um.setCreateAt(new Date());
								um.setUpdateAt(new Date());
								smartCultureUserMenuService.save(um);
							}
						}
					}
				} else {
					// 不是一级菜单
					SmartCultureMenu scm = smartCultureMenuService.findById(smartCultureMenu.getPid());
					if (scm == null) {
						return ResultGenerator.genFailResult("E5007");
					}
					condition = new Condition(SmartCultureUserMenu.class);
					condition.createCriteria().andEqualTo("userId", param.getUserId().trim())
							.andEqualTo("menuAccessKey", scm.getMenuAccessKey()).andEqualTo("validState", 1);
					uMenus = smartCultureUserMenuService.findByCondition(condition);
					if (uMenus == null || uMenus.isEmpty()) {
						um = new SmartCultureUserMenu();
						um.setMenuAccessKey(scm.getMenuAccessKey());
						um.setUserId(param.getUserId().trim());
						um.setValidState(1);
						um.setCreateAt(new Date());
						um.setUpdateAt(new Date());
						smartCultureUserMenuService.save(um);
					}
				}
			} else {
				return ResultGenerator.genFailResult("E5004");
			}
		} else {
			if ("N".equals(param.getChecked())) {
				SmartCultureUserMenu um = uMenus.get(0);
				um.setValidState(0);
				um.setUpdateAt(new Date());
				smartCultureUserMenuService.update(um);
				if (smartCultureMenu.getPid() == null) {
					// 是一级菜单
					condition = new Condition(SmartCultureMenu.class);
					condition.createCriteria().andEqualTo("pid", smartCultureMenu.getId());
					List<SmartCultureMenu> childMenus = smartCultureMenuService.findByCondition(condition);
					if (childMenus != null && childMenus.size() > 0) {
						for (SmartCultureMenu cm : childMenus) {
							condition = new Condition(SmartCultureUserMenu.class);
							condition.createCriteria().andEqualTo("userId", param.getUserId().trim())
									.andEqualTo("menuAccessKey", cm.getMenuAccessKey()).andEqualTo("validState", 1);
							uMenus = smartCultureUserMenuService.findByCondition(condition);
							if (uMenus != null && uMenus.size() > 0) {
								um = uMenus.get(0);
								um.setValidState(0);
								um.setUpdateAt(new Date());
								smartCultureUserMenuService.update(um);
							}
						}
					}
				} else {
					// 不是一级菜单
					condition = new Condition(SmartCultureMenu.class);
					condition.createCriteria().andEqualTo("pid", smartCultureMenu.getPid());
					List<SmartCultureMenu> childMenus = smartCultureMenuService.findByCondition(condition);
					boolean noChild = true;
					if (childMenus != null && childMenus.size() > 0) {
						for (SmartCultureMenu ccm : childMenus) {
							condition = new Condition(SmartCultureUserMenu.class);
							condition.createCriteria().andEqualTo("userId", param.getUserId().trim())
									.andEqualTo("menuAccessKey", ccm.getMenuAccessKey()).andEqualTo("validState", 1);
							uMenus = smartCultureUserMenuService.findByCondition(condition);
							if (uMenus != null && uMenus.size() > 0) {
								noChild = false;
							}
						}
					}
					SmartCultureMenu scm = smartCultureMenuService.findById(smartCultureMenu.getPid());
					if (scm == null) {
						return ResultGenerator.genFailResult("E5007");
					}
					if (noChild) {
						condition = new Condition(SmartCultureUserMenu.class);
						condition.createCriteria().andEqualTo("userId", param.getUserId().trim())
								.andEqualTo("menuAccessKey", scm.getMenuAccessKey()).andEqualTo("validState", 1);
						uMenus = smartCultureUserMenuService.findByCondition(condition);
						if (uMenus != null && uMenus.size() > 0) {
							um = uMenus.get(0);
							um.setValidState(0);
							um.setUpdateAt(new Date());
							smartCultureUserMenuService.update(um);
						}
					}
				}
			} else {
				return ResultGenerator.genFailResult("E5005");
			}
		}
		return ResultGenerator.genSuccessResult();
	}
}
