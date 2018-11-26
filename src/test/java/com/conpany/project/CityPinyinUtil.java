package com.conpany.project;

import cn.hutool.core.util.PinyinUtil;

public class CityPinyinUtil {
	public static void main(String[] args) {
		System.out.println(PinyinUtil.getPinYin("积石山保安族东乡族撒拉族自治县"));
		System.out.println(PinyinUtil.getAllFirstLetter("积石山保安族东乡族撒拉族自治县"));
	}
}
