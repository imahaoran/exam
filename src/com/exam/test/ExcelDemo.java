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
			//����������
            XSSFWorkbook xssfWorkbook = new XSSFWorkbook(new FileInputStream("D:\\test.xlsx"));
            System.out.println("xssfWorkbook����" + xssfWorkbook);
            //��ȡ��һ��������
            XSSFSheet sheet = xssfWorkbook.getSheetAt(0);
            System.out.println("sheet����" + sheet);
            //��ȡ���һ�е�num�������������˴���0��ʼ����
            int maxRow = sheet.getLastRowNum();
            System.out.println("������Ϊ��" + maxRow);
            for (int row = 0; row <= maxRow; row++) {
                //��ȡ���Ԫ��num�����ܵ�Ԫ���� ***ע�⣺�˴���1��ʼ����***
                int maxRol = sheet.getRow(row).getLastCellNum();
                System.out.println("--------��" + row + "�е���������--------");
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
