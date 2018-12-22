package com.company.project.web.basic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.company.project.model.SmartCultureUser;
import com.company.project.model.SmartCultureUserIdentity;
import com.company.project.model.param.basic.BasicRequestParam;
import com.company.project.model.param.basic.BasicUserParam;
import com.company.project.model.returns.basic.BasicPageResult;
import com.company.project.service.SmartCultureUserIdentityService;
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
		String userId = param.getUserId();
		if (StringUtils.isBlank(userId)) {
			// 新增
			Condition condition = new Condition(SmartCultureUser.class);
			condition.createCriteria().andEqualTo("phoneNo", param.getPhoneNo());
			List<SmartCultureUser> scos = smartCultureUserService.findByCondition(condition);
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
			user.setUserName(param.getUserName());
			user.setPassword(Md5Util.md5("181121", param.getUserName()));
			user.setUserAvator("defalut-avator.png-yeetong");
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
			user.setUserName(param.getUserName());
			user.setPhoneNo(param.getPhoneNo());
			user.setEmail(param.getEmail());
			user.setOrganizeId(param.getOrganizeId() == null ? "" : param.getOrganizeId());
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
}
