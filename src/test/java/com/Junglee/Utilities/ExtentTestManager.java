package com.Junglee.Utilities;

import java.util.HashMap;
import java.util.Map;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

public class ExtentTestManager {

	static ExtentReports report = ExtentManager.getInstance();

	static Map<Integer,ExtentTest> map = new HashMap<Integer,ExtentTest>();

	public static synchronized ExtentTest startTest(String testName)
	{
		report = ExtentManager.getInstance();
		return startTest(testName, "");
	}
	
	public static synchronized ExtentTest startTest(String testName,String description)
	{
		report = ExtentManager.getInstance();
		ExtentTest testRep = report.startTest(testName, description);
		map.put((int)Thread.currentThread().getId(), testRep);
		return testRep;
	}

	public static synchronized ExtentTest getTest()
	{
		report = ExtentManager.getInstance();
		return map.get((int)Thread.currentThread().getId());
	}

	public static synchronized void endTest()
	{
		report = ExtentManager.getInstance();
		report.endTest(map.get((int)Thread.currentThread().getId()));
	}






}
