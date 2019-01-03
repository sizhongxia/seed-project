package com.company.project.unit;

import java.net.SocketTimeoutException;
import java.util.Date;

import org.apache.http.conn.ConnectTimeoutException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.company.project.model.SmartCultureWeatherNow;

public class HeFengWeather {
	static final Logger logger = LoggerFactory.getLogger(HeFengWeather.class);
	// https://www.heweather.com/
	// 1217491317@qq.com
	private static final String[] KEYS = { "83ad6e357a724fc1b5d6900081dd6fbe", "a6417b1a1d674b3899ab6ad33910fba6",
			"013e5bc12873403481186e64beff514a", "d734d55871a2474aa28b661773c1bdde",
			"0ce45a2aa1954b74920c79ac1fc3ce09" };

	public static SmartCultureWeatherNow queryNow(String cid) {
		SmartCultureWeatherNow wn = null;
		int index = 0;
		do {
			String key = KEYS[index++];
			logger.info("use key {} get weather info...", key);
			wn = analysis(reqWeatherInfo(cid, key));
			if (index >= KEYS.length) {
				return null;
			}
		} while (wn == null);
		return wn;
	}

	private static String reqWeatherInfo(String location, String key) {
		try {
			return HttpClientUtils.post("https://free-api.heweather.net/s6/weather/now",
					String.format("location=%s&key=%s", location, key), "application/x-www-form-urlencoded", "UTF-8",
					6000, 6000);
		} catch (ConnectTimeoutException e) {
			e.printStackTrace();
		} catch (SocketTimeoutException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private static SmartCultureWeatherNow analysis(String jsonStr) {
		// String jsonStr =
		// "{\"HeWeather6\":[{\"basic\":{\"cid\":\"CN101010100\",\"location\":\"北京\",\"parent_city\":\"北京\",\"admin_area\":\"北京\",\"cnty\":\"中国\",\"lat\":\"39.90498734\",\"lon\":\"116.4052887\",\"tz\":\"+8.00\"},\"update\":{\"loc\":\"2018-12-26
		// 20:56\",\"utc\":\"2018-12-26
		// 12:56\"},\"status\":\"ok\",\"now\":{\"cloud\":\"0\",\"cond_code\":\"100\",\"cond_txt\":\"晴\",\"fl\":\"-11\",\"hum\":\"50\",\"pcpn\":\"0.0\",\"pres\":\"1036\",\"tmp\":\"-8\",\"vis\":\"11\",\"wind_deg\":\"237\",\"wind_dir\":\"西南风\",\"wind_sc\":\"1\",\"wind_spd\":\"3\"}}]}";
		logger.info("req data: {}", jsonStr);
		JSONObject jo = JSON.parseObject(jsonStr);
		if (jo == null) {
			logger.error("parse weather info error");
			return null;
		}
		JSONArray heWeather6 = jo.getJSONArray("HeWeather6");
		if (heWeather6 == null || heWeather6.size() < 1) {
			logger.error("parse weather version error");
			return null;
		}
		JSONObject weatherInfo = heWeather6.getJSONObject(0);
		if (weatherInfo == null) {
			logger.error("parse weather no data");
			return null;
		}
		if (!"ok".equals(weatherInfo.getString("status"))) {
			logger.error("parse weather is {}", weatherInfo.getString("status"));
			return null;
		}
		JSONObject basic = weatherInfo.getJSONObject("basic");
		JSONObject update = weatherInfo.getJSONObject("update");
		JSONObject now = weatherInfo.getJSONObject("now");
		if (basic == null || update == null || now == null) {
			logger.error("parse weather format error");
			return null;
		}
		SmartCultureWeatherNow wn = new SmartCultureWeatherNow();
		wn.setBasicCid(basic.getString("cid"));
		wn.setBasicLocation(basic.getString("location"));
		wn.setBasicParentCity(basic.getString("parent_city"));
		wn.setBasicAdminArea(basic.getString("admin_area"));
		wn.setBasicCnty(basic.getString("cnty"));
		wn.setBasicLat(basic.getString("lat"));
		wn.setBasicLon(basic.getString("lon"));
		wn.setBasicTz(basic.getString("tz"));
		wn.setUpdateLoc(update.getString("loc"));
		wn.setUpdateUtc(update.getString("utc"));
		wn.setNowCloud(now.getString("cloud"));
		wn.setNowCondCode(now.getString("cond_code"));
		wn.setNowCondTxt(now.getString("cond_txt"));
		wn.setNowFl(now.getString("fl"));
		wn.setNowHum(now.getString("hum"));
		wn.setNowPcpn(now.getString("pcpn"));
		wn.setNowPres(now.getString("pres"));
		wn.setNowTmp(now.getString("tmp"));
		wn.setNowVis(now.getString("vis"));
		wn.setNowWindDeg(now.getString("wind_deg"));
		wn.setNowWindDir(now.getString("wind_dir"));
		wn.setNowWindSc(now.getString("wind_sc"));
		wn.setNowWindSpd(now.getString("wind_spd"));
		wn.setUpdateAt(new Date());
		return wn;
	}
}
