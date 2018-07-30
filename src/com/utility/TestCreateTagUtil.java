package com.utility;

import java.util.ArrayList;

public class TestCreateTagUtil {

static Xls_Reader reader;
	
	public static ArrayList<Object[]> getTestData(){
		
		ArrayList<Object[]> myData=new ArrayList<Object[]>();
		reader=new Xls_Reader("C:\\Users\\deshpanr\\workspace\\SeleniumTMCSource\\SeleniumTMC\\TMC_Source_Test_Data.xlsx");
		int rowCount=reader.getRowCount("CreateTag");
		for (int rowNo = 2; rowNo <= rowCount; rowNo++) {
			String createTagName=reader.getCellData("CreateTag", "TagName",rowNo);
			String expectedMsg=reader.getCellData("CreateTag", "Expected popup message", rowNo);
			Object ob[]={createTagName,expectedMsg};
			myData.add(ob);
		}
		
		return myData;
		
	}
}
