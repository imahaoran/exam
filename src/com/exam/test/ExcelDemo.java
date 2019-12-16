package com.exam.test;

import java.io.File;
import java.io.FileInputStream;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelDemo {
	public static void main(String[] args){
		try {
			//创建工作簿
            XSSFWorkbook xssfWorkbook = new XSSFWorkbook(new FileInputStream("D:\\test.xlsx"));
            System.out.println("xssfWorkbook对象：" + xssfWorkbook);
            //读取第一个工作表
            XSSFSheet sheet = xssfWorkbook.getSheetAt(0);
            System.out.println("sheet对象：" + sheet);
            //获取最后一行的num，即总行数。此处从0开始计数
            int maxRow = sheet.getLastRowNum();
            System.out.println("总行数为：" + maxRow);
            for (int row = 0; row <= maxRow; row++) {
                //获取最后单元格num，即总单元格数 ***注意：此处从1开始计数***
                int maxRol = sheet.getRow(row).getLastCellNum();
                System.out.println("--------第" + row + "行的数据如下--------");
                for (int rol = 0; rol < maxRol; rol++){
                    System.out.print(sheet.getRow(row).getCell(rol) + "  ");
                }
                System.out.println();
            }
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
