//package com.Junglee.APITest.HowzatCore;
//
//import static io.restassured.RestAssured.given;
//import static org.hamcrest.Matchers.*;
//import java.util.HashMap;
//import org.hamcrest.Matchers;
//import org.testng.annotations.Test;
//import com.Junglee.Base.AllDrive;
//import com.Junglee.Base.Base;
//import com.Junglee.Base.Provider;
//import com.Junglee.Utilities.ExtentTestManager;
//import com.relevantcodes.extentreports.LogStatus;
//
//public class HowzatCreateTeamAPI extends Base {
//
//	@Test(dataProviderClass = Provider.class, dataProvider = "insertRest", description = "Howzat Create Team API - Positive Workflow Test")
//	public void CreateTeamPositiveTest(HashMap<String, String> table) {
//
//		given().log().all().header("Content-Type", table.get("Hcontent-Type")).body(table.get("Bjson")).when()
//		.post("ls/api/lobby/v2/fanteam").then().log().all().and().assertThat()
//		.body(containsString(table.get("TSuccess_Message"))).body(containsString(table.get("TResultCode")))
//		.statusCode(201);
//
//		String Request = AllDrive.getWriter().toString().split("HTTP/1.1")[0];
//		String Response = "HTTP/1.1 " + AllDrive.getWriter().toString().split("HTTP/1.1")[1];
//		ExtentTestManager.getTest().log(LogStatus.INFO, "REQUEST", Request);
//		System.err.println(AllDrive.getWriter().toString());
//		ExtentTestManager.getTest().log(LogStatus.INFO, "RESPONSE", Response);
//
//	}
//
//	@Test(dataProviderClass = Provider.class, dataProvider = "insertRest", description = "Howzat Create Team Without Keeper API - Negative Workflow Test")
//	public void CreateTeamWithoutKeeperNegativeTest(HashMap<String, String> table) {
//
//		given().log().all().header("Content-Type", table.get("Hcontent-Type")).body(table.get("Bjson")).when()
//		.post("ls/api/lobby/v2/fanteam").then().log().all().and().assertThat()
//		.body(containsString(table.get("TMessage"))).body(containsString(table.get("TStatus"))).statusCode(400);
//
//		String Request = AllDrive.getWriter().toString().split("HTTP/1.1")[0];
//		String Response = "HTTP/1.1 " + AllDrive.getWriter().toString().split("HTTP/1.1")[1];
//		ExtentTestManager.getTest().log(LogStatus.INFO, "REQUEST", Request);
//		System.err.println(AllDrive.getWriter().toString());
//		ExtentTestManager.getTest().log(LogStatus.INFO, "RESPONSE", Response);
//
//	}
//
//	@Test(dataProviderClass = Provider.class, dataProvider = "insertRest", description = "Howzat Create Team Without Batsmen API - Negative Workflow Test")
//	public void CreateTeamWithoutBatsmenNegativeTest(HashMap<String, String> table) {
//
//		given().log().all().header("Content-Type", table.get("Hcontent-Type")).body(table.get("Bjson")).when()
//		.post("ls/api/lobby/v2/fanteam").then().log().all().and().assertThat()
//		.body(containsString(table.get("TMessage"))).body(containsString(table.get("TStatus"))).statusCode(400);
//
//		String Request = AllDrive.getWriter().toString().split("HTTP/1.1")[0];
//		String Response = "HTTP/1.1 " + AllDrive.getWriter().toString().split("HTTP/1.1")[1];
//		ExtentTestManager.getTest().log(LogStatus.INFO, "REQUEST", Request);
//		System.err.println(AllDrive.getWriter().toString());
//		ExtentTestManager.getTest().log(LogStatus.INFO, "RESPONSE", Response);
//
//	}
//
//	@Test(dataProviderClass = Provider.class, dataProvider = "insertRest", description = "Howzat Create Team Without Bowler API - Negative Workflow Test")
//	public void CreateTeamWithoutBowlerNegativeTest(HashMap<String, String> table) {
//
//		given().log().all().header("Content-Type", table.get("Hcontent-Type")).body(table.get("Bjson")).when()
//		.post("ls/api/lobby/v2/fanteam").then().log().all().and().assertThat()
//		.body(containsString(table.get("TMessage"))).body(containsString(table.get("TStatus"))).statusCode(400);
//
//		String Request = AllDrive.getWriter().toString().split("HTTP/1.1")[0];
//		String Response = "HTTP/1.1 " + AllDrive.getWriter().toString().split("HTTP/1.1")[1];
//		ExtentTestManager.getTest().log(LogStatus.INFO, "REQUEST", Request);
//		System.err.println(AllDrive.getWriter().toString());
//		ExtentTestManager.getTest().log(LogStatus.INFO, "RESPONSE", Response);
//
//	}
//
//	@Test(dataProviderClass = Provider.class, dataProvider = "insertRest", description = "Howzat Create Team Without All Rounder API - Negative Workflow Test")
//	public void CreateTeamWithoutAllRounderNegativeTest(HashMap<String, String> table) {
//
//		given().log().all().header("Content-Type", table.get("Hcontent-Type")).body(table.get("Bjson")).when()
//		.post("ls/api/lobby/v2/fanteam").then().log().all().and().assertThat()
//		.body(containsString(table.get("TMessage"))).body(containsString(table.get("TStatus"))).statusCode(400);
//
//		String Request = AllDrive.getWriter().toString().split("HTTP/1.1")[0];
//		String Response = "HTTP/1.1 " + AllDrive.getWriter().toString().split("HTTP/1.1")[1];
//		ExtentTestManager.getTest().log(LogStatus.INFO, "REQUEST", Request);
//		System.err.println(AllDrive.getWriter().toString());
//		ExtentTestManager.getTest().log(LogStatus.INFO, "RESPONSE", Response);
//
//	}
//
//	@Test(dataProviderClass = Provider.class, dataProvider = "insertRest", description = "Howzat Create Team With Minimum One Wicket Keeper API -Positive Workflow Test")
//	public void CreateTeamWithMinOneWicketKeeperPositiveTest(HashMap<String, String> table) {
//
//		given().log().all().header("Content-Type", table.get("Hcontent-Type")).body(table.get("Bjson")).when()
//		.post("ls/api/lobby/v2/fanteam").then().log().all().and().assertThat()
//		.body(containsString(table.get("TSuccess_Message"))).body(containsString(table.get("TResultCode")))
//		.statusCode(201);
//
//		String Request = AllDrive.getWriter().toString().split("HTTP/1.1")[0];
//		String Response = "HTTP/1.1 " + AllDrive.getWriter().toString().split("HTTP/1.1")[1];
//		ExtentTestManager.getTest().log(LogStatus.INFO, "REQUEST", Request);
//		System.err.println(AllDrive.getWriter().toString());
//		ExtentTestManager.getTest().log(LogStatus.INFO, "RESPONSE", Response);
//
//	}
//
//	@Test(dataProviderClass = Provider.class, dataProvider = "insertRest", description = "Howzat Create Team With Maximum Four Wicket Keeper API -Positive Workflow Test")
//	public void CreateTeamWithMaxFourWicketKeeperPositiveTest(HashMap<String, String> table) {
//
//		given().log().all().header("Content-Type", table.get("Hcontent-Type")).body(table.get("Bjson")).when()
//		.post("ls/api/lobby/v2/fanteam").then().log().all().and().assertThat()
//		.body(containsString(table.get("TSuccess_Message"))).body(containsString(table.get("TResultCode")))
//		.statusCode(201);
//
//		String Request = AllDrive.getWriter().toString().split("HTTP/1.1")[0];
//		String Response = "HTTP/1.1 " + AllDrive.getWriter().toString().split("HTTP/1.1")[1];
//		ExtentTestManager.getTest().log(LogStatus.INFO, "REQUEST", Request);
//		System.err.println(AllDrive.getWriter().toString());
//		ExtentTestManager.getTest().log(LogStatus.INFO, "RESPONSE", Response);
//
//	}
//
//	@Test(dataProviderClass = Provider.class, dataProvider = "insertRest", description = "Howzat Create Team With Minimum Three Batsman API -Positive Workflow Test")
//	public void CreateTeamWithMinThreeBatsmanPositiveTest(HashMap<String, String> table) {
//
//		given().log().all().header("Content-Type", table.get("Hcontent-Type")).body(table.get("Bjson")).when()
//		.post("ls/api/lobby/v2/fanteam").then().log().all().and().assertThat()
//		.body(containsString(table.get("TSuccess_Message"))).body(containsString(table.get("TResultCode")))
//		.statusCode(201);
//
//		String Request = AllDrive.getWriter().toString().split("HTTP/1.1")[0];
//		String Response = "HTTP/1.1 " + AllDrive.getWriter().toString().split("HTTP/1.1")[1];
//		ExtentTestManager.getTest().log(LogStatus.INFO, "REQUEST", Request);
//		System.err.println(AllDrive.getWriter().toString());
//		ExtentTestManager.getTest().log(LogStatus.INFO, "RESPONSE", Response);
//
//	}
//
//	@Test(dataProviderClass = Provider.class, dataProvider = "insertRest", description = "Howzat Create Team With Minimum Three Bowler API -Positive Workflow Test")
//	public void CreateTeamWithMinThreeBowlerPositiveTest(HashMap<String, String> table) {
//
//		given().log().all().header("Content-Type", table.get("Hcontent-Type")).body(table.get("Bjson")).when()
//		.post("ls/api/lobby/v2/fanteam").then().log().all().and().assertThat()
//		.body(containsString(table.get("TSuccess_Message"))).body(containsString(table.get("TResultCode")))
//		.statusCode(201);
//
//		String Request = AllDrive.getWriter().toString().split("HTTP/1.1")[0];
//		String Response = "HTTP/1.1 " + AllDrive.getWriter().toString().split("HTTP/1.1")[1];
//		ExtentTestManager.getTest().log(LogStatus.INFO, "REQUEST", Request);
//		System.err.println(AllDrive.getWriter().toString());
//		ExtentTestManager.getTest().log(LogStatus.INFO, "RESPONSE", Response);
//
//	}
//
//	@Test(dataProviderClass = Provider.class, dataProvider = "insertRest", description = "Howzat Create Team With Minimum One All Rounder API -Positive Workflow Test")
//	public void CreateTeamWithMinOneAllRounderPositiveTest(HashMap<String, String> table) {
//
//		given().log().all().header("Content-Type", table.get("Hcontent-Type")).body(table.get("Bjson")).when()
//		.post("ls/api/lobby/v2/fanteam").then().log().all().and().assertThat()
//		.body(containsString(table.get("TSuccess_Message"))).body(containsString(table.get("TResultCode")))
//		.statusCode(201);
//
//		String Request = AllDrive.getWriter().toString().split("HTTP/1.1")[0];
//		String Response = "HTTP/1.1 " + AllDrive.getWriter().toString().split("HTTP/1.1")[1];
//		ExtentTestManager.getTest().log(LogStatus.INFO, "REQUEST", Request);
//		System.err.println(AllDrive.getWriter().toString());
//		ExtentTestManager.getTest().log(LogStatus.INFO, "RESPONSE", Response);
//
//	}
//
//	@Test(dataProviderClass = Provider.class, dataProvider = "insertRest", description = "Howzat Create Team With Maximum Six Batsman API - Positive Workflow Test")
//	public void CreateTeamWithMaxSixBatsmanPositiveTest(HashMap<String, String> table) {
//
//		given().log().all().header("Content-Type", table.get("Hcontent-Type")).body(table.get("Bjson")).when()
//		.post("ls/api/lobby/v2/fanteam").then().log().all().and().assertThat()
//		.body(containsString(table.get("TSuccess_Message"))).body(containsString(table.get("TResultCode")))
//		.statusCode(201);
//
//		String Request = AllDrive.getWriter().toString().split("HTTP/1.1")[0];
//		String Response = "HTTP/1.1 " + AllDrive.getWriter().toString().split("HTTP/1.1")[1];
//		ExtentTestManager.getTest().log(LogStatus.INFO, "REQUEST", Request);
//		System.err.println(AllDrive.getWriter().toString());
//		ExtentTestManager.getTest().log(LogStatus.INFO, "RESPONSE", Response);
//
//	}
//
//	@Test(dataProviderClass = Provider.class, dataProvider = "insertRest", description = "Howzat Create Team With Maximum Six Bowler API - Positive Workflow Test")
//	public void CreateTeamWithMaxSixBowlerPositiveTest(HashMap<String, String> table) {
//
//		given().log().all().header("Content-Type", table.get("Hcontent-Type")).body(table.get("Bjson")).when()
//		.post("ls/api/lobby/v2/fanteam").then().log().all().and().assertThat()
//		.body(containsString(table.get("TSuccess_Message"))).body(containsString(table.get("TResultCode")))
//		.statusCode(201);
//
//		String Request = AllDrive.getWriter().toString().split("HTTP/1.1")[0];
//		String Response = "HTTP/1.1 " + AllDrive.getWriter().toString().split("HTTP/1.1")[1];
//		ExtentTestManager.getTest().log(LogStatus.INFO, "REQUEST", Request);
//		System.err.println(AllDrive.getWriter().toString());
//		ExtentTestManager.getTest().log(LogStatus.INFO, "RESPONSE", Response);
//
//	}
//
//	@Test(dataProviderClass = Provider.class, dataProvider = "insertRest", description = "Howzat Create Team With Maximum Four All Rounder API -Positive Workflow Test")
//	public void CreateTeamWithMaxFourAllRounderPositiveTest(HashMap<String, String> table) {
//
//		given().log().all().header("Content-Type", table.get("Hcontent-Type")).body(table.get("Bjson")).when()
//		.post("ls/api/lobby/v2/fanteam").then().log().all().and().assertThat()
//		.body(containsString(table.get("TSuccess_Message"))).body(containsString(table.get("TResultCode")))
//		.statusCode(201);
//
//		String Request = AllDrive.getWriter().toString().split("HTTP/1.1")[0];
//		String Response = "HTTP/1.1 " + AllDrive.getWriter().toString().split("HTTP/1.1")[1];
//		ExtentTestManager.getTest().log(LogStatus.INFO, "REQUEST", Request);
//		System.err.println(AllDrive.getWriter().toString());
//		ExtentTestManager.getTest().log(LogStatus.INFO, "RESPONSE", Response);
//
//	}
//
//	@Test(dataProviderClass = Provider.class, dataProvider = "insertRest", description = "Howzat Create Team With Selecting Players from Three Different Teams API - Negative Workflow Test")
//	public void CreateTeamWithSelectPlayerFromThreeDifferentTeamNegativeTest(HashMap<String, String> table) {
//
//		given().log().all().header("Content-Type", table.get("Hcontent-Type")).body(table.get("Bjson")).when()
//		.post("ls/api/lobby/v2/fanteam").then().log().all().and().assertThat()
//		.body(containsString(table.get("TResultCode").split(".0")[0])).statusCode(201);
//
//		String Request = AllDrive.getWriter().toString().split("HTTP/1.1")[0];
//		String Response = "HTTP/1.1 " + AllDrive.getWriter().toString().split("HTTP/1.1")[1];
//		ExtentTestManager.getTest().log(LogStatus.INFO, "REQUEST", Request);
//		System.err.println(AllDrive.getWriter().toString());
//		ExtentTestManager.getTest().log(LogStatus.INFO, "RESPONSE", Response);
//
//	}
//
//	@Test(dataProviderClass = Provider.class, dataProvider = "insertRest", description = "Howzat Create Team With Captain and Vice Captain Same API - Negative Workflow Test")
//	public void CreateTeamWithCaptainAndViceCaptainSameNegativeTest(HashMap<String, String> table) {
//
//		given().log().all().header("Content-Type", table.get("Hcontent-Type")).body(table.get("Bjson")).when()
//		.post("ls/api/lobby/v2/fanteam").then().log().all().and().assertThat()
//		// .body(containsString(table.get("TSuccess_Message"))).body(containsString(table.get("TResultCode")))
//		.body(Matchers.not(Matchers.containsString(table.get("TSuccess_Message"))))
//		.body(Matchers.not(Matchers.containsString(table.get("TResultCode")))).statusCode(Matchers.not(201));
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