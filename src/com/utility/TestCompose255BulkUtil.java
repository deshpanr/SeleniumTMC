package com.utility;

import java.util.ArrayList;

public class TestCompose255BulkUtil {

	static Xls_Reader reader;
	
	public static ArrayList<Object[]> getTestData(){
		
		ArrayList<Object[]> myData=new ArrayList<Object[]>();
		reader=new Xls_Reader("C:\\Users\\deshpanr\\workspace\\SeleniumTMCSource\\SeleniumTMC\\TMC_Source_Test_Data.xlsx");
		int rowCount=reader.getRowCount("Compose255BulkLocationMode");
		for (int rowNo = 2; rowNo <= rowCount; rowNo++) {
			String mode=reader.getCellData("Compose255BulkLocationMode", "Mode",rowNo);
			String countryCode=reader.getCellData("Compose255BulkLocationMode", "Country Code", rowNo);
			String locationNumber=reader.getCellData("Compose255BulkLocationMode", "Location Table", rowNo);
			String fromProvider=reader.getCellData("Compose255BulkLocationMode", "From Provider", rowNo);
			String toProvider=reader.getCellData("Compose255BulkLocationMode", "To Provider", rowNo);
			String fromVersion=reader.getCellData("Compose255BulkLocationMode", "From Version", rowNo);
			String toVersion=reader.getCellData("Compose255BulkLocationMode", "To Version", rowNo);
			String expectedResult=reader.getCellData("Compose255BulkLocationMode", "Expected Result", rowNo);
			Object ob[]={mode,countryCode,locationNumber,fromProvider,toProvider,fromVersion,toVersion,expectedResult};
			myData.add(ob);
		}
		
		return myData;
		
	}
}
