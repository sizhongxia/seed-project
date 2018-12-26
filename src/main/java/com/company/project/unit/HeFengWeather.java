package com.company.project.unit;

import java.net.SocketTimeoutException;
import java.util.Date;

import org.apache.http.conn.ConnectTimeoutException;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.company.project.model.SmartCultureWeatherNow;

public class HeFengWeather {
	private static final String KEY = "83ad6e357a724fc1b5d6900081dd6fbe";

	public static SmartCultureWeatherNow queryNow(String cid) {
		String jsonStr = reqWeatherInfo(cid);
		if (jsonStr == null) {
			return null;
		}
		return analysis(jsonStr);
	}

	private static String reqWeatherInfo(String location) {
		try {
			return HttpClientUtils.post("https://free-api.heweather.net/s6/weather/now",
					String.format("location=%s&key=%s", location, KEY), "application/x-www-form-urlencoded", "UTF-8",
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
		JSONObject jo = JSON.parseObject(jsonStr);
		if (jo == null) {
			return null;
		}
		JSONArray heWeather6 = jo.getJSONArray("HeWeather6");
		if (heWeather6 == null || heWeather6.size() < 1) {
			return null;
		}
		JSONObject weatherInfo = heWeather6.getJSONObject(0);
		if (weatherInfo == null) {
			return null;
		}
		if (!"ok".equals(weatherInfo.getString("status"))) {
			return null;
		}
		JSONObject basic = weatherInfo.getJSONObject("basic");
		JSONObject update = weatherInfo.getJSONObject("update");
		JSONObject now = weatherInfo.getJSONObject("now");
		if (basic == null || update == null || now == null) {
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
