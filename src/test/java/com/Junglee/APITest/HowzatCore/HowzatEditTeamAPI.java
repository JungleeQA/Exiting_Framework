//
//package com.Junglee.APITest.HowzatCore;
//
//import static io.restassured.RestAssured.given;
//import java.util.HashMap;
//import org.hamcrest.Matchers;
//import static org.hamcrest.Matchers.*;
//import org.testng.annotations.Test;
//import com.Junglee.Base.AllDrive;
//import com.Junglee.Base.Base;
//import com.Junglee.Base.Provider;
//import com.Junglee.Utilities.ExtentTestManager;
//import com.relevantcodes.extentreports.LogStatus;
//
//public class HowzatEditTeamAPI extends Base {
//
//@Test(dataProviderClass = Provider.class, dataProvider = "insertRest", description = "Howzat Edit Team API - Positive Workflow Test")
//public void EditTeamPositiveTest(HashMap<String, String> table) {
//
//given().log().all().header("Content-Type", table.get("Hcontent-Type")).body(table.get("Bjson")).when()
//.put("ls/api/lobby/v2/fanteam/" + table.get("TFanTeamId")).then().log().all().and().assertThat()
//.body(containsString(table.get("TSuccess_Message"))).body(containsString(table.get("TFanTeamId")))
//.statusCode(200);
//
//String Request = AllDrive.getWriter().toString().split("HTTP/1.1")[0];
//String Response = "HTTP/1.1 " + AllDrive.getWriter().toString().split("HTTP/1.1")[1];
//ExtentTestManager.getTest().log(LogStatus.INFO, "REQUEST", Request);
//System.err.println(AllDrive.getWriter().toString());
//ExtentTestManager.getTest().log(LogStatus.INFO, "RESPONSE", Response);
//
//}
//
//@Test(dataProviderClass = Provider.class, dataProvider = "insertRest", description = "Howzat Edit Team Without Keeper API - Negative Workflow Test")
//public void EditTeamWithoutKeeperNegativeTest(HashMap<String, String> table) {
//
//given().log().all().header("Content-Type", table.get("Hcontent-Type")).body(table.get("Bjson")).when()
//.put("ls/api/lobby/v2/fanteam/" + table.get("TFanTeamId")).then().log().all().and().assertThat()
//.body(containsString(table.get("TMessage"))).body(containsString(table.get("TStatus"))).statusCode(400);
//
//String Request = AllDrive.getWriter().toString().split("HTTP/1.1")[0];
//String Response = "HTTP/1.1 " + AllDrive.getWriter().toString().split("HTTP/1.1")[1];
//ExtentTestManager.getTest().log(LogStatus.INFO, "REQUEST", Request);
//System.err.println(AllDrive.getWriter().toString());
//ExtentTestManager.getTest().log(LogStatus.INFO, "RESPONSE", Response);
//
//}
//
//@Test(dataProviderClass = Provider.class, dataProvider = "insertRest", description = "Howzat Edit Team Without Batsmen API - Negative Workflow Test")
//public void EditTeamWithoutBatsmenNegativeTest(HashMap<String, String> table) {
//
//given().log().all().header("Content-Type", table.get("Hcontent-Type")).body(table.get("Bjson")).when()
//.put("ls/api/lobby/v2/fanteam/" + table.get("TFanTeamId")).then().log().all().and().assertThat()
//.body(containsString(table.get("TMessage"))).body(containsString(table.get("TStatus"))).statusCode(400);
//
//String Request = AllDrive.getWriter().toString().split("HTTP/1.1")[0];
//String Response = "HTTP/1.1 " + AllDrive.getWriter().toString().split("HTTP/1.1")[1];
//ExtentTestManager.getTest().log(LogStatus.INFO, "REQUEST", Request);
//System.err.println(AllDrive.getWriter().toString());
//ExtentTestManager.getTest().log(LogStatus.INFO, "RESPONSE", Response);
//
//}
//
//@Test(dataProviderClass = Provider.class, dataProvider = "insertRest", description = "Howzat Edit Team Without Bowler API - Negative Workflow Test")
//public void EditTeamWithoutBowlerNegativeTest(HashMap<String, String> table) {
//
//given().log().all().header("Content-Type", table.get("Hcontent-Type")).body(table.get("Bjson")).when()
//.put("ls/api/lobby/v2/fanteam/" + table.get("TFanTeamId")).then().log().all().and().assertThat()
//.body(containsString(table.get("TMessage"))).body(containsString(table.get("TStatus"))).statusCode(400);
//
//String Request = AllDrive.getWriter().toString().split("HTTP/1.1")[0];
//String Response = "HTTP/1.1 " + AllDrive.getWriter().toString().split("HTTP/1.1")[1];
//ExtentTestManager.getTest().log(LogStatus.INFO, "REQUEST", Request);
//System.err.println(AllDrive.getWriter().toString());
//ExtentTestManager.getTest().log(LogStatus.INFO, "RESPONSE", Response);
//
//}
//
//@Test(dataProviderClass = Provider.class, dataProvider = "insertRest", description = "Howzat Edit Team Without All Rounder API - Negative WorkflowTest")
//public void EditTeamWithoutAllRounderNegativeTest(HashMap<String, String> table) {
//
//given().log().all().header("Content-Type", table.get("Hcontent-Type")).body(table.get("Bjson")).when()
//.put("ls/api/lobby/v2/fanteam/" + table.get("TFanTeamId")).then().log().all().and().assertThat()
//.body(containsString(table.get("TMessage"))).body(containsString(table.get("TStatus"))).statusCode(400);
//
//String Request = AllDrive.getWriter().toString().split("HTTP/1.1")[0];
//String Response = "HTTP/1.1 " + AllDrive.getWriter().toString().split("HTTP/1.1")[1];
//ExtentTestManager.getTest().log(LogStatus.INFO, "REQUEST", Request);
//System.err.println(AllDrive.getWriter().toString());
//ExtentTestManager.getTest().log(LogStatus.INFO, "RESPONSE", Response);
//
//}
//
//@Test(dataProviderClass = Provider.class, dataProvider = "insertRest", description = "Howzat Edit Team With Minimum One Wicket Keeper API - PositiveWorkflow Test")
//public void EditTeamWithMinOneWicketKeeperPositiveTest(HashMap<String, String> table) {
//
//given().log().all().header("Content-Type", table.get("Hcontent-Type")).body(table.get("Bjson")).when()
//.put("ls/api/lobby/v2/fanteam/" + table.get("TFanTeamId")).then().log().all().and().assertThat()
//.body(containsString(table.get("TSuccess_Message"))).body(containsString(table.get("TFanTeamId")))
//.statusCode(200);
//
//String Request = AllDrive.getWriter().toString().split("HTTP/1.1")[0];
//String Response = "HTTP/1.1 " + AllDrive.getWriter().toString().split("HTTP/1.1")[1];
//ExtentTestManager.getTest().log(LogStatus.INFO, "REQUEST", Request);
//System.err.println(AllDrive.getWriter().toString());
//ExtentTestManager.getTest().log(LogStatus.INFO, "RESPONSE", Response);
//
//}
//
//@Test(dataProviderClass = Provider.class, dataProvider = "insertRest", description = "Howzat Edit Team With Maximum Four Wicket Keeper API -Positive Workflow Test")
//
//public void EditTeamWithMaxFourWicketKeeperPositiveTest(HashMap<String, String> table) {
//
//given().log().all().header("Content-Type", table.get("Hcontent-Type")).body(table.get("Bjson")).when()
//.put("ls/api/lobby/v2/fanteam/" + table.get("TFanTeamId")).then().log().all().and().assertThat()
//.body(containsString(table.get("TSuccess_Message"))).body(containsString(table.get("TFanTeamId")))
//.statusCode(200);
//
//String Request = AllDrive.getWriter().toString().split("HTTP/1.1")[0];
//String Response = "HTTP/1.1 " + AllDrive.getWriter().toString().split("HTTP/1.1")[1];
//ExtentTestManager.getTest().log(LogStatus.INFO, "REQUEST", Request);
//System.err.println(AllDrive.getWriter().toString());
//ExtentTestManager.getTest().log(LogStatus.INFO, "RESPONSE", Response);
//
//}
//
//@Test(dataProviderClass = Provider.class, dataProvider = "insertRest", description = "Howzat Edit Team With Minimum Three Batsman API - PositiveWorkflow Test")
//public void EditTeamWithMinThreeBatsmanPositiveTest(HashMap<String, String> table) {
//
//given().log().all().header("Content-Type", table.get("Hcontent-Type")).body(table.get("Bjson")).when()
//.put("ls/api/lobby/v2/fanteam/" + table.get("TFanTeamId")).then().log().all().and().assertThat()
//.body(containsString(table.get("TSuccess_Message"))).body(containsString(table.get("TFanTeamId")))
//.statusCode(200);
//
//String Request = AllDrive.getWriter().toString().split("HTTP/1.1")[0];
//String Response = "HTTP/1.1 " + AllDrive.getWriter().toString().split("HTTP/1.1")[1];
//ExtentTestManager.getTest().log(LogStatus.INFO, "REQUEST", Request);
//System.err.println(AllDrive.getWriter().toString());
//ExtentTestManager.getTest().log(LogStatus.INFO, "RESPONSE", Response);
//
//}
//
//@Test(dataProviderClass = Provider.class, dataProvider = "insertRest", description = "Howzat Edit Team With Minimum Three Bowler API - PositiveWorkflow Test")
//public void EditTeamWithMinThreeBowlerPositiveTest(HashMap<String, String> table) {
//
//given().log().all().header("Content-Type", table.get("Hcontent-Type")).body(table.get("Bjson")).when()
//.put("ls/api/lobby/v2/fanteam/" + table.get("TFanTeamId")).then().log().all().and().assertThat()
//.body(containsString(table.get("TSuccess_Message"))).body(containsString(table.get("TFanTeamId")))
//.statusCode(200);
//
//String Request = AllDrive.getWriter().toString().split("HTTP/1.1")[0];
//String Response = "HTTP/1.1 " + AllDrive.getWriter().toString().split("HTTP/1.1")[1];
//ExtentTestManager.getTest().log(LogStatus.INFO, "REQUEST", Request);
//System.err.println(AllDrive.getWriter().toString());
//ExtentTestManager.getTest().log(LogStatus.INFO, "RESPONSE", Response);
//
//}
//
//@Test(dataProviderClass = Provider.class, dataProvider = "insertRest", description = "Howzat Edit Team With Minimum One All Rounder API - PositiveWorkflow Test")
//public void EditTeamWithMinOneAllRounderPositiveTest(HashMap<String, String> table) {
//
//given().log().all().header("Content-Type", table.get("Hcontent-Type")).body(table.get("Bjson")).when()
//.put("ls/api/lobby/v2/fanteam/" + table.get("TFanTeamId")).then().log().all().and().assertThat()
//.body(containsString(table.get("TSuccess_Message"))).body(containsString(table.get("TFanTeamId")))
//.statusCode(200);
//
//String Request = AllDrive.getWriter().toString().split("HTTP/1.1")[0];
//String Response = "HTTP/1.1 " + AllDrive.getWriter().toString().split("HTTP/1.1")[1];
//ExtentTestManager.getTest().log(LogStatus.INFO, "REQUEST", Request);
//System.err.println(AllDrive.getWriter().toString());
//ExtentTestManager.getTest().log(LogStatus.INFO, "RESPONSE", Response);
//
//}
//
//@Test(dataProviderClass = Provider.class, dataProvider = "insertRest", description = "Howzat Edit Team With Maximum Six Batsman API - PositiveWorkflow Test")
//public void EditTeamWithMaxSixBatsmanPositiveTest(HashMap<String, String> table) {
//
//given().log().all().header("Content-Type", table.get("Hcontent-Type")).body(table.get("Bjson")).when()
//.put("ls/api/lobby/v2/fanteam/" + table.get("TFanTeamId")).then().log().all().and().assertThat()
//.body(containsString(table.get("TSuccess_Message"))).body(containsString(table.get("TFanTeamId")))
//.statusCode(200);
//
//String Request = AllDrive.getWriter().toString().split("HTTP/1.1")[0];
//String Response = "HTTP/1.1 " + AllDrive.getWriter().toString().split("HTTP/1.1")[1];
//ExtentTestManager.getTest().log(LogStatus.INFO, "REQUEST", Request);
//System.err.println(AllDrive.getWriter().toString());
//ExtentTestManager.getTest().log(LogStatus.INFO, "RESPONSE", Response);
//
//}
//
//@Test(dataProviderClass = Provider.class, dataProvider = "insertRest", description = "Howzat Edit Team With Maximum Six Bowler API - PositiveWorkflow Test")
//public void EditTeamWithMaxSixBowlerPositiveTest(HashMap<String, String> table) {
//
//given().log().all().header("Content-Type", table.get("Hcontent-Type")).body(table.get("Bjson")).when()
//.put("ls/api/lobby/v2/fanteam/" + table.get("TFanTeamId")).then().log().all().and().assertThat()
//.body(containsString(table.get("TSuccess_Message"))).body(containsString(table.get("TFanTeamId")))
//.statusCode(200);
//
//String Request = AllDrive.getWriter().toString().split("HTTP/1.1")[0];
//String Response = "HTTP/1.1 " + AllDrive.getWriter().toString().split("HTTP/1.1")[1];
//ExtentTestManager.getTest().log(LogStatus.INFO, "REQUEST", Request);
//System.err.println(AllDrive.getWriter().toString());
//ExtentTestManager.getTest().log(LogStatus.INFO, "RESPONSE", Response);
//
//}
//
//@Test(dataProviderClass = Provider.class, dataProvider = "insertRest", description = "Howzat Edit Team With Maximum Four All Rounder API - PositiveWorkflow Test")
//public void EditTeamWithMaxFourAllRounderPositiveTest(HashMap<String, String> table) {
//
//given().log().all().header("Content-Type", table.get("Hcontent-Type")).body(table.get("Bjson")).when()
//.put("ls/api/lobby/v2/fanteam/" + table.get("TFanTeamId")).then().log().all().and().assertThat()
//.body(containsString(table.get("TSuccess_Message"))).body(containsString(table.get("TFanTeamId")))
//.statusCode(200);
//
//String Request = AllDrive.getWriter().toString().split("HTTP/1.1")[0];
//String Response = "HTTP/1.1 " + AllDrive.getWriter().toString().split("HTTP/1.1")[1];
//ExtentTestManager.getTest().log(LogStatus.INFO, "REQUEST", Request);
//System.err.println(AllDrive.getWriter().toString());
//ExtentTestManager.getTest().log(LogStatus.INFO, "RESPONSE", Response);
//
//}
//
//@Test(dataProviderClass = Provider.class, dataProvider = "insertRest", description = "Howzat Edit Team With Selecting Players from Three DifferentTeams API-Negative Workflow Test")
//public void EditTeamWithSelectPlayerFromThreeDifferentTeamNegativeTest(HashMap<String, String> table) {
//
//given().log().all().header("Content-Type", table.get("Hcontent-Type")).body(table.get("Bjson")).when()
//.put("ls/api/lobby/v2/fanteam/" + table.get("TFanTeamId")).then().log().all().and().assertThat()
//.body(containsString(table.get("TResultCode").split(".0")[0])).statusCode(200);
//
//String Request = AllDrive.getWriter().toString().split("HTTP/1.1")[0];
//String Response = "HTTP/1.1 " + AllDrive.getWriter().toString().split("HTTP/1.1")[1];
//ExtentTestManager.getTest().log(LogStatus.INFO, "REQUEST", Request);
//System.err.println(AllDrive.getWriter().toString());
//ExtentTestManager.getTest().log(LogStatus.INFO, "RESPONSE", Response);
//
//}
//
//@Test(dataProviderClass = Provider.class, dataProvider = "insertRest", description = "Howzat Edit Team With Captain and Vice Captain Same API - Negative Workflow Test")
//public void EditTeamWithCaptainAndViceCaptainSameNegativeTest(HashMap<String, String> table) {
//
//given().log().all().header("Content-Type", table.get("Hcontent-Type")).body(table.get("Bjson")).when()
//.put("ls/api/lobby/v2/fanteam/" + table.get("TFanTeamId")).then().log().all().and().assertThat()
//.body(Matchers.not(Matchers.containsString(table.get("TSuccess_Message"))))
//.body(Matchers.not(Matchers.containsString(table.get("TFanTeamId")))).statusCode(Matchers.not(200));
//
//String Request = AllDrive.getWriter().toString().split("HTTP/1.1")[0];
//String Response = "HTTP/1.1 " + AllDrive.getWriter().toString().split("HTTP/1.1")[1];
//ExtentTestManager.getTest().log(LogStatus.INFO, "REQUEST", Request);
//System.err.println(AllDrive.getWriter().toString());
//ExtentTestManager.getTest().log(LogStatus.INFO, "RESPONSE", Response);
//
//}
//
//
//}