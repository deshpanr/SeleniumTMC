package com.utility;

import java.util.ArrayList;

public class TestValidLoginUtil {
	
static Xls_Reader reader;
	
	public static ArrayList<Object[]> getTestData(){
		
		ArrayList<Object[]> myData=new ArrayList<Object[]>();
		reader=new Xls_Reader("C:\\Users\\deshpanr\\workspace\\SeleniumTMCSource\\SeleniumTMC\\TMC_Source_Test_Data.xlsx");
		int rowCount=reader.getRowCount("TMCValidUser");
		for (int rowNo = 2; rowNo <= rowCount; rowNo++) {
			String user=reader.getCellData("TMCValidUser", "username",rowNo);
			String password=reader.getCellData("TMCValidUser", "password", rowNo);
			Object ob[]={user,password};
			myData.add(ob);
		}
		
		return myData;
		
	}
}

