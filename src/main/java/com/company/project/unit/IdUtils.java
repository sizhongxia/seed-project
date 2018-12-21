package com.company.project.unit;

import cn.hutool.core.lang.ObjectId;
import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;

public class IdUtils {
	public static String initUuid() {
		return IdUtil.simpleUUID();
	}

	public static String initObjectId() {
		return ObjectId.next();
	}

	public static void main(String[] args) {
		System.out.println(initObjectId());
		System.out.println(initObjectId());
		System.out.println(initObjectId());
		System.out.println(initObjectId());
		System.out.println(initObjectId());
		System.out.println(initObjectId());
		System.out.println(initObjectId().length());
		Snowflake snowflake = IdUtil.createSnowflake(1, 1);
		long id = snowflake.nextId();
		System.out.println((id+"").length());
	}
}
