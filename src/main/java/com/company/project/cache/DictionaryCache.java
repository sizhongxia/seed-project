//package com.company.project.cache;
//
//import java.util.List;
//import java.util.concurrent.ConcurrentHashMap;
//
//import javax.annotation.Resource;
//
//import org.apache.commons.lang3.StringUtils;
//import org.springframework.beans.factory.BeanFactory;
//import org.springframework.stereotype.Component;
//import org.springframework.web.context.support.WebApplicationContextUtils;
//
//import com.company.project.configurer.Audience;
//import com.company.project.model.SysDictionary;
//import com.company.project.service.SysDictionaryService;
//
//import tk.mybatis.mapper.entity.Condition;
//
//@Component
//public class DictionaryCache {
//	private static ConcurrentHashMap<String, ConcurrentHashMap<Integer, String>> data = new ConcurrentHashMap<>();
//
//	public DictionaryCache() {
//		super();
//		init();
//	}
//
//	public String getByTypeValue(String type, Integer value) {
//		ConcurrentHashMap<Integer, String> dicts = data.get(type);
//		if (dicts == null) {
//			Condition condition = new Condition(SysDictionary.class);
//			condition.createCriteria().andEqualTo("type", type).andEqualTo("value", value.intValue());
//			List<SysDictionary> dictLs = sysDictionaryService.findByCondition(condition);
//			if (dictLs != null && dictLs.size() > 0) {
//				SysDictionary d = dictLs.get(0);
//				dicts = new ConcurrentHashMap<>();
//				dicts.put(value, d.getName());
//				data.put(type, dicts);
//				return d.getName();
//			}
//		} else {
//			String name = dicts.get(value);
//			if (StringUtils.isNotBlank(name)) {
//				return name;
//			}
//			Condition condition = new Condition(SysDictionary.class);
//			condition.createCriteria().andEqualTo("type", type).andEqualTo("value", value.intValue());
//			List<SysDictionary> dictLs = sysDictionaryService.findByCondition(condition);
//			if (dictLs != null && dictLs.size() > 0) {
//				SysDictionary d = dictLs.get(0);
//				dicts.put(value, d.getName());
//				data.put(type, dicts);
//				return d.getName();
//			}
//		}
//		return "";
//	}
//
//	private void init() {
//		List<SysDictionary> sds = sysDictionaryService.findAll();
//		if (sds != null && sds.size() > 0) {
//			data.clear();
//			for (SysDictionary sd : sds) {
//				ConcurrentHashMap<Integer, String> item = data.get(sd.getType());
//				if (item == null) {
//					item = new ConcurrentHashMap<>();
//				}
//				item.put(sd.getValue(), sd.getName());
//				data.put(sd.getType(), item);
//			}
//		}
//	}
//}
