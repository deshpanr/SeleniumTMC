package com.utility;

import java.util.ArrayList;

public class TestRecommFlagStatus {

	static Xls_Reader reader;
	
	public static ArrayList<Object[]> getTestData(){
		
		ArrayList<Object[]> myData=new ArrayList<Object[]>();
		reader=new Xls_Reader("C:\\Users\\deshpanr\\workspace\\SeleniumTMCSource\\SeleniumTMC\\TMC_Source_Test_Data.xlsx");
		int rowCount=reader.getRowCount("RecommendedFlagStatus");
		for (int rowNo = 2; rowNo <= rowCount; rowNo++) {
			String currentRecommFlag1=reader.getCellData("RecommendedFlagStatus", "Current Recomm Flag Status1",rowNo);
			String changedRecommFlag1=reader.getCellData("RecommendedFlagStatus", "Changed Recomm Flag Status1", rowNo);
			String currentRecommFlag2=reader.getCellData("RecommendedFlagStatus", "Current Recomm Flag Status2",rowNo);
			String changedRecommFlag2=reader.getCellData("RecommendedFlagStatus", "Changed Recomm Flag Status2", rowNo);
			Object ob[]={currentRecommFlag1,changedRecommFlag1,currentRecommFlag2,changedRecommFlag2};
			myData.add(ob);
		}
		
		return myData;
		
	}
}
