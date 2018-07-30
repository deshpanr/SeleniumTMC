package com.utility;

import java.util.ArrayList;

public class TestSourceStatusUtil {
static Xls_Reader reader;
	
	public static ArrayList<Object[]> getTestData(){
		
		ArrayList<Object[]> myData=new ArrayList<Object[]>();
		reader=new Xls_Reader("C:\\Users\\deshpanr\\workspace\\SeleniumTMCSource\\SeleniumTMC\\TMC_Source_Test_Data.xlsx");
		int rowCount=reader.getRowCount("SourceStatus");
		for (int rowNo = 2; rowNo <= rowCount; rowNo++) {
			String sourceStatus=reader.getCellData("SourceStatus", "SourceStatus",rowNo);
			Object ob[]={sourceStatus};
			myData.add(ob);
		}
		
		return myData;
		
	}
}
