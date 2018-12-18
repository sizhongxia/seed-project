package com.company.project.web.yeetong;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.model.YeeTongBanner;
import com.company.project.model.YeeTongNews;
import com.company.project.model.YeeTongNewsChosen;
import com.company.project.model.YeeTongProduct;
import com.company.project.model.YeeTongSiteInfo;
import com.company.project.model.param.yeetong.NewsParam;
import com.company.project.model.param.yeetong.PageParam;
import com.company.project.model.param.yeetong.ProductParam;
import com.company.project.service.YeeTongBannerService;
import com.company.project.service.YeeTongNewsChosenService;
import com.company.project.service.YeeTongNewsService;
import com.company.project.service.YeeTongProductService;
import com.company.project.service.YeeTongSiteInfoService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.NumberUtil;
import tk.mybatis.mapper.entity.Condition;

@RestController
@RequestMapping("/yeetong/api/")
public class YeeTongApiController {
	final Logger logger = LoggerFactory.getLogger(YeeTongApiController.class);

	@Resource
	private YeeTongSiteInfoService yeeTongSiteInfoService;
	@Resource
	private YeeTongNewsService yeeTongNewsService;
	@Resource
	private YeeTongBannerService yeeTongBannerService;
	@Resource
	private YeeTongNewsChosenService yeeTongNewsChosenService;
	@Resource
	private YeeTongProductService yeeTongProductService;

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

	@PostMapping("/banners")
	public Result<?> banners(@RequestBody PageParam param) {
		Condition condition = new Condition(YeeTongBanner.class);
		if (StringUtils.isNotBlank(param.getType())) {
			condition.createCriteria().andEqualTo("bannerType", param.getType());
		}
		condition.orderBy("weightNum").desc();
		int page = NumberUtil.parseInt(param.getPage());
		int size = NumberUtil.parseInt(param.getSize());
		PageHelper.startPage(page, size);
		List<YeeTongBanner> list = yeeTongBannerService.findByCondition(condition);

		Map<String, Object> data = new HashMap<>();
		if (list != null && list.size() > 0) {
			List<Map<String, Object>> listMap = new ArrayList<>();
			Map<String, Object> item = null;
			for (YeeTongBanner yt : list) {
				item = new HashMap<>();
				item.put("id", yt.getUniqueId());
				item.put("title", yt.getBannerTitle());
				item.put("picUrl", yt.getPicUrl());
				item.put("jumpLink", yt.getJumpLink());
				listMap.add(item);
			}
			data.put("list", listMap);
		} else {
			return ResultGenerator.genFailResult("未配置轮播图");
		}
		return ResultGenerator.genSuccessResult(data);
	}

	@PostMapping("/products")
	public Result<?> products(@RequestBody PageParam param) {
		Condition condition = new Condition(YeeTongProduct.class);
		if (StringUtils.isNotBlank(param.getType())) {
			condition.createCriteria().andEqualTo("releaseState", "Y");
		}
		condition.orderBy("weightNum").desc();
		int page = NumberUtil.parseInt(param.getPage());
		int size = NumberUtil.parseInt(param.getSize());
		PageHelper.startPage(page, size);
		List<YeeTongProduct> list = yeeTongProductService.findByCondition(condition);

		Map<String, Object> data = new HashMap<>();
		if (list != null && list.size() > 0) {
			List<Map<String, Object>> listMap = new ArrayList<>();
			Map<String, Object> item = null;
			for (YeeTongProduct yt : list) {
				item = new HashMap<>();
				item.put("id", yt.getUniqueId());
				item.put("name", yt.getProductName());
				item.put("shortName", yt.getProductShortName());
				item.put("coverPicUrl", yt.getProductCoverPic());
				listMap.add(item);
			}
			data.put("list", listMap);
		} else {
			return ResultGenerator.genFailResult("未配置产品信息");
		}
		return ResultGenerator.genSuccessResult(data);
	}

	@PostMapping("/product/detail")
	public Result<?> productDetail(@RequestBody ProductParam param) {
		YeeTongProduct product = yeeTongProductService.findBy("uniqueId", param.getId());
		if (product == null) {
			return ResultGenerator.genFailResult("未找到产品信息");
		}
		Map<String, Object> data = new HashMap<>();
		data.put("id", product.getUniqueId());
		data.put("name", product.getProductName());
		data.put("shortName", product.getProductShortName());
		data.put("coverPic", product.getProductCoverPic());
		data.put("introduce", product.getProductIntroduce());
		return ResultGenerator.genSuccessResult(data);
	}

	/***
	 * 新闻编辑地址 http://kindeditor.net/demo.php
	 * 
	 * 清除格式，一键排版，图片空行
	 * 
	 * @param param
	 * @return
	 */
	@PostMapping("/news")
	public Result<?> news(@RequestBody PageParam param) {
		Condition condition = new Condition(YeeTongNews.class);
		condition.createCriteria().andEqualTo("releaseState", "Y");
		condition.orderBy("releaseTime").desc();
		int page = NumberUtil.parseInt(param.getPage());
		int size = NumberUtil.parseInt(param.getSize());
		PageHelper.startPage(page, size);
		List<YeeTongNews> list = yeeTongNewsService.findByCondition(condition);

		Map<String, Object> data = new HashMap<>();
		List<Map<String, Object>> listMap = new ArrayList<>();

		if (list != null && list.size() > 0) {
			// https://github.com/pagehelper/Mybatis-PageHelper/blob/master/wikis/en/HowToUse.md
			PageInfo<YeeTongNews> pageInfo = new PageInfo<YeeTongNews>(list);
			Map<String, Object> item = null;
			for (YeeTongNews yt : list) {
				item = new HashMap<>();
				item.put("id", yt.getUniqueId());
				item.put("title", yt.getNewsTitle());
				item.put("coverPic", yt.getNewsCoverPic());
				item.put("abstract", yt.getNewsAbstract());
				item.put("time", DateUtil.format(yt.getReleaseTime(), "yyyy-MM-dd"));
				listMap.add(item);
			}
			data.put("count", pageInfo.getTotal());
			data.put("hasNextPage", pageInfo.isHasNextPage() ? page + 1 : 0);
		} else {
			data.put("count", 0);
			data.put("hasNextPage", 0);
		}

		data.put("size", size);
		data.put("list", listMap);
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
		data.put("time", DateUtil.format(news.getReleaseTime(), "yyyy-MM-dd"));
		return ResultGenerator.genSuccessResult(data);
	}

	@PostMapping("/news/detail")
	public Result<?> newsDetail(@RequestBody NewsParam param) {
		YeeTongNews news = yeeTongNewsService.findBy("uniqueId", param.getId());
		if (news == null) {
			return ResultGenerator.genFailResult("未找到新闻信息");
		}

		// 更新访问量
		news.setPageViewNum(news.getPageViewNum() + 1);
		yeeTongNewsService.update(news);

		Map<String, Object> data = new HashMap<>();
		data.put("id", news.getUniqueId());
		data.put("coverPic", news.getNewsCoverPic());
		data.put("title", news.getNewsTitle());
		data.put("time", DateUtil.format(news.getReleaseTime(), "yyyy-MM-dd"));
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
