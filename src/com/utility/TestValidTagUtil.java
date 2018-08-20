package com.utility;

import java.util.ArrayList;

import com.utility.Xls_Reader;

public class TestValidTagUtil {

	static Xls_Reader reader;
	
	public static ArrayList<Object[]> getTestData(){
		ArrayList<Object[]> myData=new ArrayList<Object[]>();
		reader=new Xls_Reader("C:\\Selenium\\seleniumtmc\\TMC_Source_Test_Data.xlsx");
		int rowCount=reader.getRowCount("ValidTagData");
		for (int rowNo = 2; rowNo <= rowCount; rowNo++) {
			String createTagName=reader.getCellData("ValidTagData", "TagName",rowNo);
			String expectedalert=reader.getCellData("ValidTagData", "Expected Alert",rowNo);
			Object ob[]={createTagName,expectedalert};
			myData.add(ob);
		}
		
		return myData;
		
	}
		
	}
