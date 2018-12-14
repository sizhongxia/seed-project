package com.company.project.web.yeetong;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.model.YeeTongNews;
import com.company.project.model.YeeTongNewsChosen;
import com.company.project.model.YeeTongSiteInfo;
import com.company.project.model.param.yeetong.NewsParam;
import com.company.project.model.param.yeetong.PageParam;
import com.company.project.service.YeeTongNewsChosenService;
import com.company.project.service.YeeTongNewsService;
import com.company.project.service.YeeTongSiteInfoService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.NumberUtil;
import tk.mybatis.mapper.entity.Condition;

@RestController
@RequestMapping("/yeetong/api/")
public class ApiController {
	final Logger logger = LoggerFactory.getLogger(ApiController.class);

	@Resource
	private YeeTongSiteInfoService yeeTongSiteInfoService;
	@Resource
	private YeeTongNewsService yeeTongNewsService;
	@Resource
	private YeeTongNewsChosenService yeeTongNewsChosenService;

	@PostMapping("/siteinfo")
	public Result<?> siteinfo() {
		Condition condition = new Condition(YeeTongSiteInfo.class);
		condition.createCriteria().andEqualTo("useState", "Y");
		List<YeeTongSiteInfo> siteInfos = yeeTongSiteInfoService.findByCondition(condition);
		if (siteInfos == null || siteInfos.isEmpty()) {
			return ResultGenerator.genFailResult("未配置网站信息");
		}
		return ResultGenerator.genSuccessResult(siteInfos.get(0));
	}

	@PostMapping("/news")
	public Result<?> news(@RequestBody PageParam param) {
		Condition condition = new Condition(YeeTongNews.class);
		condition.createCriteria().andEqualTo("releaseState", "Y");
		int page = NumberUtil.parseInt(param.getPage());
		int size = NumberUtil.parseInt(param.getSize());
		PageHelper.startPage(page, size);
		List<YeeTongNews> newses = yeeTongNewsService.findByCondition(condition);

		Map<String, Object> data = new HashMap<>();
		List<Map<String, Object>> newsLs = new ArrayList<>();

		if (newses != null && newses.size() > 0) {
			// https://github.com/pagehelper/Mybatis-PageHelper/blob/master/wikis/en/HowToUse.md
			PageInfo<YeeTongNews> pageInfo = new PageInfo<YeeTongNews>(newses);
			Map<String, Object> item = null;
			for (YeeTongNews news : newses) {
				item = new HashMap<>();
				item.put("id", news.getUniqueId());
				item.put("title", news.getNewsTitle());
				item.put("abstract", news.getNewsAbstract());
				item.put("time", DateUtil.format(news.getReleaseTime(), "yyyy年MM月dd"));
				newsLs.add(item);
			}
			data.put("count", pageInfo.getTotal());
			data.put("hasNextPage", pageInfo.isHasNextPage() ? 1 : 0);
		} else {
			data.put("count", 0);
			data.put("hasNextPage", 0);
		}

		data.put("size", size);
		data.put("list", newsLs);
		return ResultGenerator.genSuccessResult(data);
	}

	@PostMapping("/news/chosen")
	public Result<?> newsChosen() {
		Condition condition = new Condition(YeeTongNewsChosen.class);
		condition.createCriteria().andEqualTo("useState", "Y");
		List<YeeTongNewsChosen> chosens = yeeTongNewsChosenService.findByCondition(condition);
		if (chosens == null || chosens.isEmpty()) {
			return ResultGenerator.genFailResult("未找到精选新闻信息");
		}
		YeeTongNewsChosen one = chosens.get(0);
		YeeTongNews news = yeeTongNewsService.findBy("uniqueId", one.getNewsId());
		if (news == null) {
			return ResultGenerator.genFailResult("未找到精选新闻信息");
		}

		Map<String, Object> data = new HashMap<>();
		data.put("id", one.getNewsId());
		data.put("coverPic", one.getNewsCoverPic());
		data.put("title", news.getNewsTitle());
		return ResultGenerator.genSuccessResult(data);
	}

	@PostMapping("/news/detail")
	public Result<?> newsDetail(@RequestBody NewsParam param) {
		YeeTongNews news = yeeTongNewsService.findBy("uniqueId", param.getId());
		if (news == null) {
			return ResultGenerator.genFailResult("未找到精选新闻信息");
		}
		
		// 更新访问量
		news.setPageViewNum(news.getPageViewNum() + 1);
		yeeTongNewsService.update(news);

		Map<String, Object> data = new HashMap<>();
		data.put("id", news.getUniqueId());
		data.put("coverPic", news.getNewsCoverPic());
		data.put("title", news.getNewsTitle());
		data.put("abstract", news.getNewsAbstract());
		data.put("content", news.getNewsContent());
		data.put("keywords", news.getNewsKeywords());
		data.put("pageViewNum", news.getPageViewNum());
		data.put("newsOrigin", news.getNewsOrigin());
		data.put("afterId", news.getAfterId());
		data.put("beforeId", news.getBeforeId());
		return ResultGenerator.genSuccessResult(data);
	}
}
