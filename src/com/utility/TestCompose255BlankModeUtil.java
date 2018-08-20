package com.utility;

import java.util.ArrayList;

public class TestCompose255BlankModeUtil {

static Xls_Reader reader;
	
	public static ArrayList<Object[]> getTestData(){
		
		ArrayList<Object[]> myData=new ArrayList<Object[]>();
		reader=new Xls_Reader("C:\\Selenium\\seleniumtmc\\TMC_Source_Test_Data.xlsx");
		int rowCount=reader.getRowCount("Compose255BlankMode");
		for (int rowNo = 2; rowNo <= rowCount; rowNo++) {
			String mode=reader.getCellData("Compose255BlankMode", "Mode",rowNo);
			String expectedMsg=reader.getCellData("Compose255BlankMode", "Expected Result", rowNo);
			Object ob[]={mode,expectedMsg};
			myData.add(ob);
		}
		
		return myData;
		
	}
	
}
