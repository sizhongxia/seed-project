package com.company.project.web.zhyz.mp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.project.annotation.SmartCultureTokenCheck;
import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.model.SmartCultureFarm;
import com.company.project.model.SmartCultureUserFarm;
import com.company.project.service.SmartCultureFarmService;
import com.company.project.service.SmartCultureUserFarmService;

import tk.mybatis.mapper.entity.Condition;

@RestController
@RequestMapping("/zhyz/miniapp/api/farm")
public class SmartCultureMpFarmController {

	@Resource
	SmartCultureUserFarmService smartCultureUserFarmService;
	@Resource
	SmartCultureFarmService smartCultureFarmService;

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

}
