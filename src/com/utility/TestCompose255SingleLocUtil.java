package com.utility;

import java.util.ArrayList;

public class TestCompose255SingleLocUtil {

	static Xls_Reader reader;
	
	public static ArrayList<Object[]> getTestData(){
		
		ArrayList<Object[]> myData=new ArrayList<Object[]>();
		reader=new Xls_Reader("C:\\Selenium\\seleniumtmc\\TMC_Source_Test_Data.xlsx");
		int rowCount=reader.getRowCount("Compose255SingleLocationMode");
		for (int rowNo = 2; rowNo <= rowCount; rowNo++) {
			String mode=reader.getCellData("Compose255SingleLocationMode", "Mode",rowNo);
			String countryCode=reader.getCellData("Compose255SingleLocationMode", "Country Code", rowNo);
			String locationNumber=reader.getCellData("Compose255SingleLocationMode", "Location Table", rowNo);
			String Provider=reader.getCellData("Compose255SingleLocationMode", "Provider", rowNo);
			String version=reader.getCellData("Compose255SingleLocationMode", "Version", rowNo);
			String locCode=reader.getCellData("Compose255SingleLocationMode", "Location code", rowNo);
			String composeFlag=reader.getCellData("Compose255SingleLocationMode", "Compose Flag Value", rowNo);
			String comment=reader.getCellData("Compose255SingleLocationMode", "Compose Flag Comment", rowNo);
			String expectedResult=reader.getCellData("Compose255SingleLocationMode", "Expected Result", rowNo);
			Object ob[]={mode,countryCode,locationNumber,Provider,version,locCode,composeFlag,comment,expectedResult};
			myData.add(ob);
		}
		
		return myData;
		
	}
}
