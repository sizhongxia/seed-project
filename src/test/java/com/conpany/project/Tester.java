package com.conpany.project;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.company.project.Application;

import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;

/**
 * 单元测试继承该类即可
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@Transactional
@Rollback
public abstract class Tester {
	public static void main(String[] args) {
		ExcelReader excelReader = ExcelUtil.getReader(new File("d://CityCodeDatas.xlsx"));
		Sheet sheet = excelReader.getSheet();
		int firstRowNum = sheet.getFirstRowNum();
		int lastRowNum = sheet.getLastRowNum();
//		System.out.println(firstRowNum);
//		System.out.println(lastRowNum);

		String temp = "INSERT INTO `sys_area` (`name`, `pinyin`, `jianpin`, `pcode`, `code`) VALUES ('%s', '', '', '%s', '%s');";

		// Set<String> root = new HashSet<>();
		String l1 = null;
		String l1Code = null;
		String l2 = null;
		String l2Code = null;
		
		
		
		for (int rowNum = firstRowNum; rowNum <= lastRowNum; rowNum++) {
			if(rowNum < 2715) {
				continue;
			}
			Row row = sheet.getRow(rowNum);
			if (row == null) {
				continue;
			}
			Cell cell = row.getCell(0);
			if (cell == null) {
				continue;
			}
			String code = getCellValue(cell);

			cell = row.getCell(1);
			if (cell == null) {
				continue;
			}
			String name = getCellValue(cell);
			
//			if(code.endsWith("0000") && rowNum > 2700) {
//				System.out.println(rowNum);
//				break;
//			}

			if (code.endsWith("0000")) {
				// System.out.println("一级区域：" + code + " >> " + name);
				System.out.println(String.format(temp, name, '0', code));
				l1 = code.substring(0, 2);
				l1Code = code;
				continue;
			}
			
			
			if (code.endsWith("00")) {
				// System.out.println("二级区域：" + code + " >> " + name + " UP:" + l1Code);
				System.out.println(String.format(temp, name, l1Code, code));
				l2 = code.substring(0, 4);
				l2Code = code;
				continue;
			}
			if (l2 != null) {
				if (code.startsWith(l2)) {
					// System.out.println("三级区域：" + code + " >> " + name + " UP:" + l2Code);
					System.out.println(String.format(temp, name, l2Code, code));
					continue;
				}
			}
			if (code.startsWith(l1)) {
				// System.out.println("二级区域：" + code + " >> " + name + " UP:" + l1Code);
				System.out.println(String.format(temp, name, l1Code, code));
				continue;
			}
		}
	}

	public static String getCellValue(Cell cell) {
		String cellValue = "";
		if (cell == null) {
			return cellValue;
		}
		// 把数字当成String来读，避免出现1读成1.0的情况
		if (cell.getCellType() == CellType.NUMERIC) {
			cell.setCellType(CellType.STRING);
		}
		// 判断数据的类型
		switch (cell.getCellType()) {
		case NUMERIC: // 数字
			cellValue = String.valueOf(cell.getNumericCellValue());
			break;
		case STRING: // 字符串
			cellValue = String.valueOf(cell.getStringCellValue());
			break;
		case BOOLEAN: // Boolean
			cellValue = String.valueOf(cell.getBooleanCellValue());
			break;
		case FORMULA: // 公式
			cellValue = String.valueOf(cell.getCellFormula());
			break;
		case BLANK: // 空值
			cellValue = "";
			break;
		case ERROR: // 故障
			cellValue = "非法字符";
			break;
		default:
			cellValue = "未知类型";
			break;
		}
		return cellValue;
	}
}