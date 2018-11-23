package com.company.project.unit;

import java.util.Random;
import java.util.UUID;

public class UuidUtil {
	public static String init() {
		return UUID.randomUUID().toString().replaceAll("[-]", "");
	}

	final static char[] digits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f', 'g',
			'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B',
			'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W',
			'X', 'Y', 'Z' };

	public static String initShort() {
		Random random = new Random();
		char[] cs = new char[18];
		for (int i = 0; i < cs.length; i++) {
			cs[i] = digits[random.nextInt(digits.length)];
		}
		return new String(cs);
	}

	public static void main(String[] args) {
		System.out.println(init());
		System.out.println(init());
		System.out.println(init());
		System.out.println(init());
		System.out.println(init());
		System.out.println(init());
		System.out.println(init());
		System.out.println(init());
		System.out.println(init());
		System.out.println(init());
		System.out.println(init());
		System.out.println(init());
		System.out.println(init());
		System.out.println(init());
		System.out.println(init());
		System.out.println(init());
		System.out.println(init());
		System.out.println(init());
		System.out.println(init());
		System.out.println(init());
		System.out.println(init());
		System.out.println(init());
		System.out.println(init());
		System.out.println(init());
		System.out.println(initShort());
		System.out.println(initShort());
		System.out.println(System.currentTimeMillis());
	}
}
