//package com.Junglee.APITest.HowzatStage;
//
//import static io.restassured.RestAssured.given;
//import static org.hamcrest.Matchers.*;
//import java.util.HashMap;
//import org.testng.annotations.Test;
//import com.Junglee.Base.AllDrive;
//import com.Junglee.Base.Base;
//import com.Junglee.Base.Provider;
//import com.Junglee.Utilities.ExtentTestManager;
//import com.relevantcodes.extentreports.LogStatus;
//
//public class HowzatLoginAPI extends Base {
//
//	@Test(dataProviderClass = Provider.class, dataProvider = "insertRest", description = "Howzat Login API - Positive Workflow Test")
//	public void LoginPositiveTest(HashMap<String, String> table) {
//
//		given().log().all().header("Content-Type", table.get("Hcontent-Type")).body(table.get("Bjson")).when()
//				.post("api/ups/login").then().log().all().and().assertThat()
//				.body(containsString(table.get("TLoginName"))).body(containsString(table.get("TUserID")))
//				.body(containsString(table.get("TEmailID"))).body(containsString(table.get("TAuthStatus")))
//				.body(containsString(table.get("TIsNewUser"))).body(containsString(table.get("TLangID")))
//				.body(containsString(table.get("TMobileMarkedVerified"))).body(containsString(table.get("TChannelID")))
//				.body(containsString(table.get("TDepositBucket"))).body(containsString(table.get("TUnReleasedBonus")))
//				.body(containsString(table.get("Twithdrawable"))).body(containsString(table.get("TNonWithdrawable")))
//				.statusCode(200);
//
//		String Request = AllDrive.getWriter().toString().split("HTTP/1.1")[0];
//		String Response = "HTTP/1.1 " + AllDrive.getWriter().toString().split("HTTP/1.1")[1];
//		ExtentTestManager.getTest().log(LogStatus.INFO, "REQUEST", Request);
//		System.err.println(AllDrive.getWriter().toString());
//		ExtentTestManager.getTest().log(LogStatus.INFO, "RESPONSE", Response);
//
//	}
//
//	@Test(dataProviderClass = Provider.class, dataProvider = "insertRest", description = "Howzat Login With Email API - Positive Workflow Test")
//	public void EmailLoginPositiveTest(HashMap<String, String> table) {
//
//		given().log().all().header("Content-Type", table.get("Hcontent-Type")).body(table.get("Bjson")).when()
//				.post("api/ups/login").then().log().all().and().assertThat()
//				.body(containsString(table.get("TLoginName"))).body(containsString(table.get("TUserID")))
//				.body(containsString(table.get("TEmailID"))).body(containsString(table.get("TAuthStatus")))
//				.body(containsString(table.get("TIsNewUser"))).body(containsString(table.get("TLangID")))
//				.body(containsString(table.get("TMobileMarkedVerified"))).body(containsString(table.get("TChannelID")))
//				.body(containsString(table.get("TDepositBucket"))).body(containsString(table.get("TUnReleasedBonus")))
//				.body(containsString(table.get("Twithdrawable"))).body(containsString(table.get("TNonWithdrawable")))
//				.statusCode(200);
//
//		String Request = AllDrive.getWriter().toString().split("HTTP/1.1")[0];
//		String Response = "HTTP/1.1 " + AllDrive.getWriter().toString().split("HTTP/1.1")[1];
//		ExtentTestManager.getTest().log(LogStatus.INFO, "REQUEST", Request);
//		System.err.println(AllDrive.getWriter().toString());
//		ExtentTestManager.getTest().log(LogStatus.INFO, "RESPONSE", Response);
//
//	}
//
//	@Test(dataProviderClass = Provider.class, dataProvider = "insertRest", description = "Howzat Login With Username API - Positive Workflow Test")
//	public void UsernameLoginPositiveTest(HashMap<String, String> table) {
//
//		given().log().all().header("Content-Type", table.get("Hcontent-Type")).body(table.get("Bjson")).when()
//				.post("api/ups/login").then().log().all().and().assertThat()
//				.body(containsString(table.get("TLoginName"))).body(containsString(table.get("TUserID")))
//				.body(containsString(table.get("TEmailID"))).body(containsString(table.get("TAuthStatus")))
//				.body(containsString(table.get("TIsNewUser"))).body(containsString(table.get("TLangID")))
//				.body(containsString(table.get("TMobileMarkedVerified"))).body(containsString(table.get("TChannelID")))
//				.body(containsString(table.get("TDepositBucket"))).body(containsString(table.get("TUnReleasedBonus")))
//				.body(containsString(table.get("Twithdrawable"))).body(containsString(table.get("TNonWithdrawable")))
//				.statusCode(200);
//
//		String Request = AllDrive.getWriter().toString().split("HTTP/1.1")[0];
//		String Response = "HTTP/1.1 " + AllDrive.getWriter().toString().split("HTTP/1.1")[1];
//		ExtentTestManager.getTest().log(LogStatus.INFO, "REQUEST", Request);
//		System.err.println(AllDrive.getWriter().toString());
//		ExtentTestManager.getTest().log(LogStatus.INFO, "RESPONSE", Response);
//
//	}
//
//	@Test(dataProviderClass = Provider.class, dataProvider = "insertRest", description = "Howzat Login With Mobile No API - Positive Workflow Test")
//	public void MobileNoLoginPositiveTest(HashMap<String, String> table) {
//
//		given().log().all().header("Content-Type", table.get("Hcontent-Type")).body(table.get("Bjson")).when()
//				.post("api/ups/login").then().log().all().and().assertThat()
//				.body(containsString(table.get("TLoginName"))).body(containsString(table.get("TUserID")))
//				.body(containsString(table.get("TEmailID"))).body(containsString(table.get("TAuthStatus")))
//				.body(containsString(table.get("TIsNewUser"))).body(containsString(table.get("TLangID")))
//				.body(containsString(table.get("TMobileMarkedVerified"))).body(containsString(table.get("TChannelID")))
//				.body(containsString(table.get("TDepositBucket"))).body(containsString(table.get("TUnReleasedBonus")))
//				.body(containsString(table.get("Twithdrawable"))).body(containsString(table.get("TNonWithdrawable")))
//				.statusCode(200);
//
//		String Request = AllDrive.getWriter().toString().split("HTTP/1.1")[0];
//		String Response = "HTTP/1.1 " + AllDrive.getWriter().toString().split("HTTP/1.1")[1];
//		ExtentTestManager.getTest().log(LogStatus.INFO, "REQUEST", Request);
//		System.err.println(AllDrive.getWriter().toString());
//		ExtentTestManager.getTest().log(LogStatus.INFO, "RESPONSE", Response);
//
//	}
//
//	@Test(dataProviderClass = Provider.class, dataProvider = "insertRest", description = "Howzat Login With Blank Username And Blank Password - Negative Workflow Test")
//	public void LoginWithBlankUsernameAndBlankPasswordNegativeTest(HashMap<String, String> table) {
//
//		given().log().all().header("Content-Type", table.get("Hcontent-Type")).body(table.get("Bjson")).when()
//				.post("api/ups/login").then().log().all().and().assertThat()
//				.body(containsString(table.get("TErrorMessage"))).statusCode(400);
//
//		String Request = AllDrive.getWriter().toString().split("HTTP/1.1")[0];
//		String Response = "HTTP/1.1 " + AllDrive.getWriter().toString().split("HTTP/1.1")[1];
//		ExtentTestManager.getTest().log(LogStatus.INFO, "REQUEST", Request);
//		System.err.println(AllDrive.getWriter().toString());
//		ExtentTestManager.getTest().log(LogStatus.INFO, "RESPONSE", Response);
//
//	}
//
//	@Test(dataProviderClass = Provider.class, dataProvider = "insertRest", description = "Howzat Login With Incorrect Credentials - Negative Workflow Test")
//	public void LoginWithIncorrectCredentailsNegativeTest(HashMap<String, String> table) {
//
//		given().log().all().header("Content-Type", table.get("Hcontent-Type")).body(table.get("Bjson")).when()
//				.post("api/ups/login").then().log().all().and().assertThat()
//				.body(containsString(table.get("TErrorMessage"))).statusCode(401);
//
//		String Request = AllDrive.getWriter().toString().split("HTTP/1.1")[0];
//		String Response = "HTTP/1.1 " + AllDrive.getWriter().toString().split("HTTP/1.1")[1];
//		ExtentTestManager.getTest().log(LogStatus.INFO, "REQUEST", Request);
//		System.err.println(AllDrive.getWriter().toString());
//		ExtentTestManager.getTest().log(LogStatus.INFO, "RESPONSE", Response);
//
//	}
//
//}