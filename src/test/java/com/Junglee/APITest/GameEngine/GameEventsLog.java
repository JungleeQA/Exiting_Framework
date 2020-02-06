//package com.Junglee.APITest.GameEngine;
//
//import static io.restassured.RestAssured.given;
//import static org.hamcrest.Matchers.containsString;
//import static org.hamcrest.Matchers.equalTo;
//
//import java.util.HashMap;
//
//import org.testng.annotations.Test;
//
//import com.Junglee.Base.AllDrive;
//import com.Junglee.Base.Base;
//import com.Junglee.Base.Provider;
//import com.Junglee.Utilities.ExtentTestManager;
//import com.relevantcodes.extentreports.LogStatus;
//
//public class GameEventsLog extends Base{
//	
//	@Test(dataProviderClass = Provider.class,dataProvider="insertRest",description = "Game Engine Generic API's - Game Events Log test Positive Workflow.")
//	public void GameEventLogPositiveCase(HashMap<String,String> table) 
//	{	
//		String json="{"
//	    +"eventName:"+"\"" + table.get("BeventName")+"\","
//	    +"deviceId : "+"\"" + table.get("BdeviceId")+"\","
//	    +"userId : "+"\"" +table.get("BuserId")+"\","
//	    +"createDate : "+"\"" +table.get("BcreateDate")+"\","
//	    +"itemCode : "+"\"" +table.get("BitemCode")+"\","
//	    +"sessionId : "+"\"" +table.get("BsessionId")+"\","
//	    +"ip : "+"\""+table.get("Bip")+"\","
//	    +"description : "+"\"" +table.get("Bdescription")+"\","
//	    +"customData:"+
//		     "{"
//		     +"challengerId : "+"\""+table.get("BchallangerId")+"\","
//		     +"tableNo : "+"\"" +table.get("BtableNo")+"\","
//		     +"wagerType : "+"\""+table.get("BwagerType")
//		        +"}"
//		     +"}";
//		
//			
//		given().log().all()
//		.header("Content-Type",table.get("HContent-Type"))
//		.header("Token",table.get("HToken"))
//		.body(json)
//		.when()
//		.post("/publish")
//		.then().log().all().and().assertThat()
//		.body(containsString(table.get("Tmessage"))).statusCode(200);
//
//		String Request = AllDrive.getWriter().toString().split("HTTP/1.1")[0];
//		String Response = "HTTP/1.1 "+AllDrive.getWriter().toString().split("HTTP/1.1")[1];
//		ExtentTestManager.getTest().log(LogStatus.INFO,"REQUEST",Request);
//		System.err.println(AllDrive.getWriter().toString());
//		ExtentTestManager.getTest().log(LogStatus.INFO,"RESPONSE",Response);
//		
//	}
//
//	@Test(dataProviderClass = Provider.class,dataProvider="insertRest",description = "Game Engine Generic API's - Game Events Log test With Wrong Token Negative Test Workflow.")
//	public void GameEventLogWithWrongTokenCase(HashMap<String,String> table) 
//	{	
//		String json="{"
//			    +"eventName:"+"\"" + table.get("BeventName")+"\","
//			    +"deviceId : "+"\"" + table.get("BdeviceId")+"\","
//			    +"userId : "+"\"" +table.get("BuserId")+"\","
//			    +"createDate : "+"\"" +table.get("BcreateDate")+"\","
//			    +"itemCode : "+"\"" +table.get("BitemCode")+"\","
//			    +"sessionId : "+"\"" +table.get("BsessionId")+"\","
//			    +"ip : "+"\""+table.get("Bip")+"\","
//			    +"description : "+"\"" +table.get("Bdescription")+"\","
//			    +"customData:"+
//				     "{"
//				     +"challengerId : "+"\""+table.get("BchallangerId")+"\","
//				     +"tableNo : "+"\"" +table.get("BtableNo")+"\","
//				     +"wagerType : "+"\""+table.get("BwagerType")
//				        +"}"
//				     +"}";
//				
//		
//			
//		given().log().all()
//		.header("Content-Type",table.get("HContent-Type"))
//		.header("Token",table.get("HToken"))
//		.body(json)
//		.when()
//		.post("/publish")
//		.then().log().all().and().assertThat()
//		.body(containsString(table.get("Tmessage")))
//		.body(containsString(table.get("Tcode"))).statusCode(200);
//
//		String Request = AllDrive.getWriter().toString().split("HTTP/1.1")[0];
//		String Response = "HTTP/1.1 "+AllDrive.getWriter().toString().split("HTTP/1.1")[1];
//		ExtentTestManager.getTest().log(LogStatus.INFO,"REQUEST",Request);
//		System.err.println(AllDrive.getWriter().toString());
//		ExtentTestManager.getTest().log(LogStatus.INFO,"RESPONSE",Response);
//		
//	}
//	
//	@Test(dataProviderClass = Provider.class,dataProvider="insertRest",description = "Game Engine Generic API's - Game Events Log test With Empty Token Negative Test Workflow.")
//	public void GameEventLogWithEmptyTokenCase(HashMap<String,String> table) 
//	{		
//		String json="{"
//		    +"eventName:"+"\"" + table.get("BeventName")+"\","
//		    +"deviceId : "+"\"" + table.get("BdeviceId")+"\","
//		    +"userId : "+"\"" +table.get("BuserId")+"\","
//		    +"createDate : "+"\"" +table.get("BcreateDate")+"\","
//		    +"itemCode : "+"\"" +table.get("BitemCode")+"\","
//		    +"sessionId : "+"\"" +table.get("BsessionId")+"\","
//		    +"ip : "+"\""+table.get("Bip")+"\","
//		    +"description : "+"\"" +table.get("Bdescription")+"\","
//		    +"customData:"+
//			     "{"
//			     +"challengerId : "+"\""+table.get("BchallangerId")+"\","
//			     +"tableNo : "+"\"" +table.get("BtableNo")+"\","
//			     +"wagerType : "+"\""+table.get("BwagerType")
//			        +"}"
//			     +"}";
//			
//		
//			
//		given().log().all()
//		.header("Content-Type",table.get("HContent-Type"))
//		.header("Token",table.get("HToken"))
//		.body(json)
//		.when()
//		.post("/publish")
//		.then().log().all().and().assertThat()
//		.body(containsString(table.get("Tmessage")))
//		.body(containsString(table.get("Tcode"))).statusCode(200);
//
//		String Request = AllDrive.getWriter().toString().split("HTTP/1.1")[0];
//		String Response = "HTTP/1.1 "+AllDrive.getWriter().toString().split("HTTP/1.1")[1];
//		ExtentTestManager.getTest().log(LogStatus.INFO,"REQUEST",Request);
//		System.err.println(AllDrive.getWriter().toString());
//		ExtentTestManager.getTest().log(LogStatus.INFO,"RESPONSE",Response);
//		
//	}
//	
//	
//	@Test(dataProviderClass = Provider.class,dataProvider="insertRest",description = "Game Engine Generic API's - Game Events Log test Without Event Name Negative Test Workflow.")
//	public void GameEventLogWithoutEventNameInJsonCase(HashMap<String,String> table) 
//	{	
//		String json="{"
//			   
//			    +"deviceId : "+"\"" + table.get("BdeviceId")+"\","
//			    +"userId : "+"\"" +table.get("BuserId")+"\","
//			    +"createDate : "+"\"" +table.get("BcreateDate")+"\","
//			    +"itemCode : "+"\"" +table.get("BitemCode")+"\","
//			    +"sessionId : "+"\"" +table.get("BsessionId")+"\","
//			    +"ip : "+"\""+table.get("Bip")+"\","
//			    +"description : "+"\"" +table.get("Bdescription")+"\","
//			    +"customData:"+
//				     "{"
//				     +"challengerId : "+"\""+table.get("BchallangerId")+"\","
//				     +"tableNo : "+"\"" +table.get("BtableNo")+"\","
//				     +"wagerType : "+"\""+table.get("BwagerType")
//				        +"}"
//				     +"}";
//				
//		
//			
//		given().log().all()
//		.header("Content-Type",table.get("HContent-Type"))
//		.header("Token",table.get("HToken"))
//		.body(json)
//		.when()
//		.post("/publish")
//		.then().log().all().and().assertThat()
//		.body(containsString(table.get("Tmessage")))
//		.body(containsString(table.get("Tcode"))).statusCode(200);
//
//		String Request = AllDrive.getWriter().toString().split("HTTP/1.1")[0];
//		String Response = "HTTP/1.1 "+AllDrive.getWriter().toString().split("HTTP/1.1")[1];
//		ExtentTestManager.getTest().log(LogStatus.INFO,"REQUEST",Request);
//		System.err.println(AllDrive.getWriter().toString());
//		ExtentTestManager.getTest().log(LogStatus.INFO,"RESPONSE",Response);
//		
//	}
//	
//	
//	@Test(dataProviderClass = Provider.class,dataProvider="insertRest",description = "Game Engine Generic API's - Game Events Log test Without Device Id Negative Test Workflow.")
//	public void GameEventLogWithoutdeviceidInJsonCase(HashMap<String,String> table) 
//	{	
//		
//		String json="{"
//		    +"eventName:"+"\"" + table.get("BeventName")+"\","
//		   
//		    +"userId : "+"\"" +table.get("BuserId")+"\","
//		    +"createDate : "+"\"" +table.get("BcreateDate")+"\","
//		    +"itemCode : "+"\"" +table.get("BitemCode")+"\","
//		    +"sessionId : "+"\"" +table.get("BsessionId")+"\","
//		    +"ip : "+"\""+table.get("Bip")+"\","
//		    +"description : "+"\"" +table.get("Bdescription")+"\","
//		    +"customData:"+
//			     "{"
//			     +"challengerId : "+"\""+table.get("BchallangerId")+"\","
//			     +"tableNo : "+"\"" +table.get("BtableNo")+"\","
//			     +"wagerType : "+"\""+table.get("BwagerType")
//			        +"}"
//			     +"}";
//			
//		given().log().all()
//		.header("Content-Type",table.get("HContent-Type"))
//		.header("Token",table.get("HToken"))
//		.body(json)
//		.when()
//		.post("/publish")
//		.then().log().all().and().assertThat()
//		.body(containsString(table.get("Tmessage")))
//		.body(containsString(table.get("Tcode"))).statusCode(200);
//
//		String Request = AllDrive.getWriter().toString().split("HTTP/1.1")[0];
//		String Response = "HTTP/1.1 "+AllDrive.getWriter().toString().split("HTTP/1.1")[1];
//		ExtentTestManager.getTest().log(LogStatus.INFO,"REQUEST",Request);
//		System.err.println(AllDrive.getWriter().toString());
//		ExtentTestManager.getTest().log(LogStatus.INFO,"RESPONSE",Response);
//		
//	}
//	@Test(dataProviderClass = Provider.class,dataProvider="insertRest",description = "Game Engine Generic API's - Game Events Log test Without User Id Negative Test Workflow.")
//	public void GameEventLogWithoutUserIdInJsonCase(HashMap<String,String> table) 
//	{	
//		String json="{"
//			    +"eventName:"+"\"" + table.get("BeventName")+"\","
//			    +"deviceId : "+"\"" + table.get("BdeviceId")+"\","
//			   
//			    +"createDate : "+"\"" +table.get("BcreateDate")+"\","
//			    +"itemCode : "+"\"" +table.get("BitemCode")+"\","
//			    +"sessionId : "+"\"" +table.get("BsessionId")+"\","
//			    +"ip : "+"\""+table.get("Bip")+"\","
//			    +"description : "+"\"" +table.get("Bdescription")+"\","
//			    +"customData:"+
//				     "{"
//				     +"challengerId : "+"\""+table.get("BchallangerId")+"\","
//				     +"tableNo : "+"\"" +table.get("BtableNo")+"\","
//				     +"wagerType : "+"\""+table.get("BwagerType")
//				        +"}"
//				     +"}";
//			
//		given().log().all()
//		.header("Content-Type",table.get("HContent-Type"))
//		.header("Token",table.get("HToken"))
//		.body(json)
//		.when()
//		.post("/publish")
//		.then().log().all().and().assertThat()
//		.body(containsString(table.get("Tmessage")))
//		.body(containsString(table.get("Tcode"))).statusCode(200);
//
//		String Request = AllDrive.getWriter().toString().split("HTTP/1.1")[0];
//		String Response = "HTTP/1.1 "+AllDrive.getWriter().toString().split("HTTP/1.1")[1];
//		ExtentTestManager.getTest().log(LogStatus.INFO,"REQUEST",Request);
//		System.err.println(AllDrive.getWriter().toString());
//		ExtentTestManager.getTest().log(LogStatus.INFO,"RESPONSE",Response);
//		
//	}
//	@Test(dataProviderClass = Provider.class,dataProvider="insertRest",description = "Game Engine Generic API's - Game Events Log test Without Create Date Negative Test Workflow.")
//	public void GameEventLogWithoutCreateDateInJsonCase(HashMap<String,String> table) 
//	{	
//		String json="{"
//			    +"eventName:"+"\"" + table.get("BeventName")+"\","
//			    +"deviceId : "+"\"" + table.get("BdeviceId")+"\","
//			    +"userId : "+"\"" +table.get("BuserId")+"\","
//			    
//			    +"itemCode : "+"\"" +table.get("BitemCode")+"\","
//			    +"sessionId : "+"\"" +table.get("BsessionId")+"\","
//			    +"ip : "+"\""+table.get("Bip")+"\","
//			    +"description : "+"\"" +table.get("Bdescription")+"\","
//			    +"customData:"+
//				     "{"
//				     +"challengerId : "+"\""+table.get("BchallangerId")+"\","
//				     +"tableNo : "+"\"" +table.get("BtableNo")+"\","
//				     +"wagerType : "+"\""+table.get("BwagerType")
//				        +"}"
//				     +"}";
//		
//			
//		given().log().all()
//		.header("Content-Type",table.get("HContent-Type"))
//		.header("Token",table.get("HToken"))
//		.body(json)
//		.when()
//		.post("/publish")
//		.then().log().all().and().assertThat()
//		.body(containsString(table.get("Tmessage")))
//		.body(containsString(table.get("Tcode"))).statusCode(200);
//
//		String Request = AllDrive.getWriter().toString().split("HTTP/1.1")[0];
//		String Response = "HTTP/1.1 "+AllDrive.getWriter().toString().split("HTTP/1.1")[1];
//		ExtentTestManager.getTest().log(LogStatus.INFO,"REQUEST",Request);
//		System.err.println(AllDrive.getWriter().toString());
//		ExtentTestManager.getTest().log(LogStatus.INFO,"RESPONSE",Response);
//		
//	}
//	@Test(dataProviderClass = Provider.class,dataProvider="insertRest",description = "Game Engine Generic API's - Game Events Log test Without Session Id Negative Test Workflow.")
//	public void GameEventLogWithoutSessionIdInJsonCase(HashMap<String,String> table) 
//	{	
//		String json="{"
//			    +"eventName:"+"\"" + table.get("BeventName")+"\","
//			    +"deviceId : "+"\"" + table.get("BdeviceId")+"\","
//			    +"userId : "+"\"" +table.get("BuserId")+"\","
//			    +"createDate : "+"\"" +table.get("BcreateDate")+"\","
//			    +"itemCode : "+"\"" +table.get("BitemCode")+"\","
//			   
//			    +"ip : "+"\""+table.get("Bip")+"\","
//			    +"description : "+"\"" +table.get("Bdescription")+"\","
//			    +"customData:"+
//				     "{"
//				     +"challengerId : "+"\""+table.get("BchallangerId")+"\","
//				     +"tableNo : "+"\"" +table.get("BtableNo")+"\","
//				     +"wagerType : "+"\""+table.get("BwagerType")
//				        +"}"
//				     +"}";
//		
//			
//		given().log().all()
//		.header("Content-Type",table.get("HContent-Type"))
//		.header("Token",table.get("HToken"))
//		.body(json)
//		.when()
//		.post("/publish")
//		.then().log().all().and().assertThat()
//		.body(containsString(table.get("Tmessage")))
//		.body(containsString(table.get("Tcode"))).statusCode(200);
//
//		String Request = AllDrive.getWriter().toString().split("HTTP/1.1")[0];
//		String Response = "HTTP/1.1 "+AllDrive.getWriter().toString().split("HTTP/1.1")[1];
//		ExtentTestManager.getTest().log(LogStatus.INFO,"REQUEST",Request);
//		System.err.println(AllDrive.getWriter().toString());
//		ExtentTestManager.getTest().log(LogStatus.INFO,"RESPONSE",Response);
//		
//	}
//	
//	@Test(dataProviderClass = Provider.class,dataProvider="insertRest",description = "Game Engine Generic API's - Game Events Log test Without IP Negative Test Workflow.")
//	public void GameEventLogWithoutIPInJsonCase(HashMap<String,String> table) 
//	{	
//		String json="{"
//			    +"eventName:"+"\"" + table.get("BeventName")+"\","
//			    +"deviceId : "+"\"" + table.get("BdeviceId")+"\","
//			    +"userId : "+"\"" +table.get("BuserId")+"\","
//			    +"createDate : "+"\"" +table.get("BcreateDate")+"\","
//			    +"itemCode : "+"\"" +table.get("BitemCode")+"\","
//			    +"sessionId : "+"\"" +table.get("BsessionId")+"\","
//			  
//			    +"description : "+"\"" +table.get("Bdescription")+"\","
//			    +"customData:"+
//				     "{"
//				     +"challengerId : "+"\""+table.get("BchallangerId")+"\","
//				     +"tableNo : "+"\"" +table.get("BtableNo")+"\","
//				     +"wagerType : "+"\""+table.get("BwagerType")
//				        +"}"
//				     +"}";
//		
//			
//		given().log().all()
//		.header("Content-Type",table.get("HContent-Type"))
//		.header("Token",table.get("HToken"))
//		.body(json)
//		.when()
//		.post("/publish")
//		.then().log().all().and().assertThat()
//		.body(containsString(table.get("Tmessage")))
//		.body(containsString(table.get("Tcode"))).statusCode(200);
//
//		String Request = AllDrive.getWriter().toString().split("HTTP/1.1")[0];
//		String Response = "HTTP/1.1 "+AllDrive.getWriter().toString().split("HTTP/1.1")[1];
//		ExtentTestManager.getTest().log(LogStatus.INFO,"REQUEST",Request);
//		System.err.println(AllDrive.getWriter().toString());
//		ExtentTestManager.getTest().log(LogStatus.INFO,"RESPONSE",Response);
//		
//	}
//	
//	@Test(dataProviderClass = Provider.class,dataProvider="insertRest",description = "Game Engine Generic API's - Game Events Log test Without Inner Part in json Negative Test Workflow.")
//	public void GameEventLogWithoutWithoutInnerJsonCase(HashMap<String,String> table) 
//	{	
//		String json="{"
//			    +"eventName:"+"\"" + table.get("BeventName")+"\","
//			    +"deviceId : "+"\"" + table.get("BdeviceId")+"\","
//			    +"userId : "+"\"" +table.get("BuserId")+"\","
//			    +"createDate : "+"\"" +table.get("BcreateDate")+"\","
//			    +"itemCode : "+"\"" +table.get("BitemCode")+"\","
//			    +"sessionId : "+"\"" +table.get("BsessionId")+"\","
//			    +"ip : "+"\""+table.get("Bip")+"\","
//			    +"description : "+"\"" +table.get("Bdescription")+"\","
//			
//				     +"}";
//		
//			
//		given().log().all()
//		.header("Content-Type",table.get("HContent-Type"))
//		.header("Token",table.get("HToken"))
//		.body(json)
//		.when()
//		.post("/publish")
//		.then().log().all().and().assertThat()
//		.body(containsString(table.get("Tmessage")))
//		.body(containsString(table.get("Tcode"))).statusCode(200);
//
//		String Request = AllDrive.getWriter().toString().split("HTTP/1.1")[0];
//		String Response = "HTTP/1.1 "+AllDrive.getWriter().toString().split("HTTP/1.1")[1];
//		ExtentTestManager.getTest().log(LogStatus.INFO,"REQUEST",Request);
//		System.err.println(AllDrive.getWriter().toString());
//		ExtentTestManager.getTest().log(LogStatus.INFO,"RESPONSE",Response);
//		
//	}
//	
//	@Test(dataProviderClass = Provider.class,dataProvider="insertRest",description = "Game Engine Generic API's - Game Events Log test Without JSon Negative Test Workflow.")
//	public void GameEventLogWithoutWithoutJsonCase(HashMap<String,String> table) 
//	{	
//		
//		
//			
//		given().log().all()
//		.header("Content-Type",table.get("HContent-Type"))
//		.header("Token",table.get("HToken"))
//		
//		.when()
//		.post("/publish")
//		.then().log().all().and().assertThat()
//		.body(containsString(table.get("Tmessage")))
//		.body(containsString(table.get("Tstatus")))
//		.body(containsString(table.get("Terror")))
//		.body(containsString(table.get("Tpath"))).statusCode(400);
//
//		String Request = AllDrive.getWriter().toString().split("HTTP/1.1")[0];
//		String Response = "HTTP/1.1 "+AllDrive.getWriter().toString().split("HTTP/1.1")[1];
//		ExtentTestManager.getTest().log(LogStatus.INFO,"REQUEST",Request);
//		System.err.println(AllDrive.getWriter().toString());
//		ExtentTestManager.getTest().log(LogStatus.INFO,"RESPONSE",Response);
//		
//	}
//}
