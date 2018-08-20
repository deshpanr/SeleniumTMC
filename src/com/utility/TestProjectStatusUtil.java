package com.utility;

import java.util.ArrayList;

public class TestProjectStatusUtil {
static Xls_Reader reader;
	
	public static ArrayList<Object[]> getTestData(){
		
		ArrayList<Object[]> myData=new ArrayList<Object[]>();
		reader=new Xls_Reader("C:\\Selenium\\seleniumtmc\\TMC_Source_Test_Data.xlsx");
		int rowCount=reader.getRowCount("ProjectStatus");
		for (int rowNo = 2; rowNo <= rowCount; rowNo++) {
			String projectStatus=reader.getCellData("ProjectStatus", "Project Status",rowNo);
			Object ob[]={projectStatus};
			myData.add(ob);
		}
		
		return myData;
		
	}
}

