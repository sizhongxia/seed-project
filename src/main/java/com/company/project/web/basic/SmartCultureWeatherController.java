package com.company.project.web.basic;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.model.SmartCultureWeatherCity;
import com.company.project.model.SmartCultureWeatherHistory;
import com.company.project.model.SmartCultureWeatherNow;
import com.company.project.model.param.basic.BasicWeatherParam;
import com.company.project.service.SmartCultureCommonService;
import com.company.project.service.SmartCultureWeatherCityService;
import com.company.project.service.SmartCultureWeatherHistoryService;
import com.company.project.service.SmartCultureWeatherNowService;
import com.company.project.unit.HeFengWeather;

@RestController
@RequestMapping("/basic/api/weather")
public class SmartCultureWeatherController {
	final Logger logger = LoggerFactory.getLogger(SmartCultureWeatherController.class);

	@Resource
	SmartCultureWeatherCityService smartCultureWeatherCityService;
	@Resource
	SmartCultureWeatherNowService smartCultureWeatherNowService;
	@Resource
	SmartCultureWeatherHistoryService smartCultureWeatherHistoryService;
	@Resource
	SmartCultureCommonService smartCultureCommonService;

	@PostMapping("/now")
	public Result<?> now(HttpServletRequest request, @RequestBody BasicWeatherParam param) {
		if (StringUtils.isBlank(param.getCid())) {
			return ResultGenerator.genFailResult("E5001");
		}
		SmartCultureWeatherCity wc = smartCultureWeatherCityService.findBy("cityCode", param.getCid());
		if (wc == null) {
			return ResultGenerator.genFailResult("E5002");
		}
		SmartCultureWeatherNow wn = smartCultureWeatherNowService.findBy("basicCid", param.getCid());
		if (wn == null) {
			wn = HeFengWeather.queryNow(param.getCid());
			SmartCultureWeatherHistory wh = new SmartCultureWeatherHistory();
			BeanUtils.copyProperties(wn, wh);
			wh.setCreateAt(new Date());
			smartCultureWeatherNowService.save(wn);
			smartCultureWeatherHistoryService.save(wh);
		}
		Map<String, String> weather = new HashMap<>();
		weather.put("location", wn.getBasicLocation());
		weather.put("updateLoc", wn.getUpdateLoc());
		weather.put("tmp", String.format("%s℃", wn.getNowTmp()));
		weather.put("tmpFl", String.format("%s℃", wn.getNowFl()));
		weather.put("windInfo", String.format("%s风%s级", wn.getNowWindDir().replaceAll("风", ""), wn.getNowWindSc()));
		weather.put("weather", wn.getNowCondTxt());
		weather.put("pic",
				String.format("https://static.yeetong.cn/weather/hefeng/v1/%s.png-yeetong", wn.getNowCondCode()));
		weather.put("hum", String.format("%s%%", wn.getNowHum()));
		weather.put("pcpn", String.format("%smm", wn.getNowPcpn()));
		return ResultGenerator.genSuccessResult(weather);
	}

	@RequestMapping("/update")
	public Result<?> update(HttpServletRequest request) {
		List<String> wcs = smartCultureCommonService.getFarmWeatherCities();
		if (wcs == null) {
			return ResultGenerator.genFailResult("E5001");
		}
		int num = 0;
		for (String cid : wcs) {
			SmartCultureWeatherNow wn = smartCultureWeatherNowService.findBy("basicCid", cid);
			if (wn != null && System.currentTimeMillis() - wn.getUpdateAt().getTime() < ((3 * 60 * 60 - 1) * 1000)) {
				continue;
			}
			SmartCultureWeatherNow nwn = HeFengWeather.queryNow(cid);
			if (wn == null) {
				smartCultureWeatherNowService.save(nwn);
			} else {
				long id = wn.getId();
				if (nwn.getUpdateLoc().equals(wn.getUpdateLoc())) {
					continue;
				}
				BeanUtils.copyProperties(nwn, wn);
				wn.setId(id);
				smartCultureWeatherNowService.update(wn);
			}
			SmartCultureWeatherHistory wh = new SmartCultureWeatherHistory();
			BeanUtils.copyProperties(nwn, wh);
			wh.setCreateAt(new Date());
			smartCultureWeatherHistoryService.save(wh);
			num++;
		}
		return ResultGenerator.genSuccessResult(num);
	}

}
