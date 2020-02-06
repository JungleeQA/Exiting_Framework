package com.Junglee.Utilities;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.Date;

import org.apache.commons.io.FileUtils;

import com.relevantcodes.extentreports.ExtentReports;

public class ExtentManager {

	static Date date =new Date();
	public static ExtentReports extent;
	public static String filename = "ExtentReport"+date.toString().replace(':','_').replace(' ','_')+".html";
	@SuppressWarnings("deprecation")
	public static String reportPath= "./Reports/extent_reports/"+ (date.getMonth()+1) +"/"+date.getDate()+"/"+filename;
	
	public static ExtentReports getInstance()
	{
		if(extent == null)
		{
			extent = new ExtentReports(reportPath,true);
		}
		return extent;
		
	}
	
	@SuppressWarnings("deprecation")
	public static void move(String SuiteName)
	{
		System.out.println("Bahooooo - "+ extent.toString() + " : " + reportPath);
		
	    File dest = new File("./Reports/");
	    File dest2 = new File("./");
	    File[] fileToDelete = dest.listFiles(new FilenameFilter() {
	        public boolean accept(File dir, String name) {
	            return name.toLowerCase().endsWith(".html");
	        }
	    }); 
	    
		File oldfile =new File(reportPath);
		File newfile =new File(reportPath.split("ExtentReport")[0]+SuiteName.replaceAll(" ", "")+"_"+filename);

		if(oldfile.renameTo(newfile))
		{
			System.out.println("Main Report File Rename succesful");
		}else{
			System.out.println("Main Report File Rename failed");
		}
	    
		filename=SuiteName.replaceAll(" ", "")+"_"+filename;
		reportPath=reportPath.split("ExtentReport")[0]+filename;
		File source = new File(reportPath);
	    
	    
	    for(File f:fileToDelete)
	    {
	    		f.delete();
	    }
	    File[] fileToDelete2 = dest2.listFiles(new FilenameFilter() {
	        public boolean accept(File dir, String name) {
	            return name.toLowerCase().equals(SuiteName.replaceAll(" ", "")+"Report.html");
	        }
	    }); 
	    
	    for(File f:fileToDelete2){
	    		f.delete();
	    }
	    
	    
	    try 
	    {
	    	FileUtils.copyFileToDirectory(source, dest);
	    	FileUtils.copyFileToDirectory(source, dest2);
	    	oldfile =new File("./"+filename);
	    	newfile =new File("./"+SuiteName.replaceAll(" ", "")+"Report.html");

	    	if(oldfile.renameTo(newfile))
	    	{
	    		System.out.println("Rename succesful");
	    	}
	    	else
	    	{
	    		System.out.println("Rename failed");
	    	}
	    } 
	    catch (IOException e) 
	    {
	    	e.printStackTrace();
	    }
	    
	    System.out.println("Yahooooo - "+ extent.toString() + " : " + reportPath);
		extent.close();
	    filename = "ExtentReport"+date.toString().replace(':','_').replace(' ','_')+".html";
		reportPath= "./Reports/extent_reports/"+ (date.getMonth()+1) +"/"+date.getDate()+"/"+filename;
//		@SuppressWarnings("unused")
//		File RepFile =new File(reportPath);
		
		extent = new ExtentReports(reportPath,true);

	}
	
	
	
	
}
