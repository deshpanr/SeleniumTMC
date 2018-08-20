package com.utility;

import java.util.ArrayList;

public class TestLoginUtil {

	static Xls_Reader reader;
	
	public static ArrayList<Object[]> getTestData(){
		
		ArrayList<Object[]> myData=new ArrayList<Object[]>();
		reader=new Xls_Reader("C:\\Selenium\\seleniumtmc\\TMC_Source_Test_Data.xlsx");
		int rowCount=reader.getRowCount("TMCSourceLogin");
		for (int rowNo = 2; rowNo <= rowCount; rowNo++) {
			String user=reader.getCellData("TMCSourceLogin", "username",rowNo);
			String password=reader.getCellData("TMCSourceLogin", "password", rowNo);
			Object ob[]={user,password};
			myData.add(ob);
		}
		
		return myData;
		
	}
}
