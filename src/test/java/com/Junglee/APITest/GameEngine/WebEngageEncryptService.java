//package com.Junglee.APITest.GameEngine;
//
//import static io.restassured.RestAssured.given;
//import static org.hamcrest.Matchers.containsString;
//
//import java.util.HashMap;
//
//import org.testng.annotations.Test;
//
//import com.Junglee.Base.AllDrive;
//import com.Junglee.Base.Provider;
//import com.Junglee.Utilities.ExtentTestManager;
//import com.relevantcodes.extentreports.LogStatus;
//
//public class WebEngageEncryptService {
//	
//	@Test(dataProviderClass=Provider.class,dataProvider="insertRest",description = "Game Engine Generic API's - WebEngage Encrypt service Positive Workflow.")
//	public void WebEngagePositiveCase(HashMap<String,String> table)
//	{
//		given().log().all()
//		.header("Content-Type",table.get("HContent-Type"))
//		.header("Token",table.get("HToken"))
//		.header("accept-encoding","gzip, deflate")
//		.header("Connection:","keep-alive")
//		.queryParam("text", table.get("QText"))
//		.when()
//		.get("/encrypt?")
//		.then().log().all().and().assertThat()
//		.body(containsString(table.get("Tencrypteddata")))
//		.body(containsString(table.get("Tcode"))).statusCode(200);
//		
////		String Request = AllDrive.getWriter().toString().split("HTTP/1.1")[0];
////		String Response = "HTTP/1.1 "+AllDrive.getWriter().toString().split("HTTP/1.1")[1];
////		ExtentTestManager.getTest().log(LogStatus.INFO,"REQUEST",Request);
////		System.err.println(AllDrive.getWriter().toString());
////		ExtentTestManager.getTest().log(LogStatus.INFO,"RESPONSE",Response);
//		
//	}
//
//}
