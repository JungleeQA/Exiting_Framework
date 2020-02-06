//package com.Junglee.APITest.GameEngine;
//
//import static io.restassured.RestAssured.given;
//
//
//import static org.hamcrest.Matchers.*;
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
//public class GeoLocationAPI extends Base
//
//{
//	@Test(dataProviderClass = Provider.class,dataProvider="insertRest",description = "Game Engine Generic API's - Lat Long Token All Passed to test Positive Workflow.")
//	public void LatLongTokenPosWorkflow(HashMap<String,String> table) 
//	{		
//		given().log().all()
//		.header("Content-Type",table.get("HContent-Type"))
//		.header("Token",table.get("HToken"))
//		.queryParam("lat",table.get("QLat"))
//		.queryParam("long",table.get("QLong"))
//		.when()
//		.get("/geolocation/search")
//		.then().log().all()
//		.and().assertThat()
//		.statusCode(200)
//		.body(table.get("TcontinentNameKey"), equalTo(table.get("TcontinentNameValue")))
//		.body(table.get("TcountryNameKey"), equalTo(table.get("TcountryNameVal")))
//		.body(table.get("TstateKey"), equalTo(table.get("TstateVal")))
//		.body(table.get("TcityNameKey"), equalTo(table.get("TcityNameVal")))
//		.body(table.get("TerrorCodeKey"), equalTo(Integer.parseInt(table.get("TerrorCodeVal"))))
//		.body(table.get("TmessageKey"), equalTo(""));
//
//		String Request = AllDrive.getWriter().toString().split("HTTP/1.1")[0];
//		String Response = "HTTP/1.1 "+AllDrive.getWriter().toString().split("HTTP/1.1")[1];
//		ExtentTestManager.getTest().log(LogStatus.INFO,"REQUEST",Request);
//		System.err.println(AllDrive.getWriter().toString());
//		ExtentTestManager.getTest().log(LogStatus.INFO,"RESPONSE",Response);
//		
//	}
//}
