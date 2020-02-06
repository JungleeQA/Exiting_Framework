package com.Junglee.APITest.HowzatCore;

import static io.restassured.RestAssured.given;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.Junglee.Base.AllDrive;
import com.Junglee.Base.Base;
import com.Junglee.Base.Provider;
import com.Junglee.Utilities.ExtentTestManager;
import com.Junglee.Utilities.ReadPropertiesFiles;
import com.Junglee.Utilities.WritePropertiesFiles;
import com.relevantcodes.extentreports.LogStatus;
import io.restassured.response.Response;

public class HowzatCreateSimpleContestAPI extends Base {

	ReadPropertiesFiles RP = new ReadPropertiesFiles();
	WritePropertiesFiles WP = new WritePropertiesFiles();

	String ConfigFilePath = "../APIAutoationFramework/src/test/resources/properties/Config.properties";
	String ContestCreationFilePath = RP.loadPropertiesFile(ConfigFilePath).getProperty("CONTESTCREATIONFILEPATH");
	String MatchAndLeagueCreationFilePath = RP.loadPropertiesFile(ConfigFilePath)
			.getProperty("MATCHANDLEAGUECREATIONFILEPATH");
	String PrizeStructureCreationFilePath = RP.loadPropertiesFile(ConfigFilePath)
			.getProperty("PRIZESTRUCTURECREATIONFILEPATH");

	@Test(dataProviderClass = Provider.class, dataProvider = "insertRest", description = "Howzat Contest Creation API - Positive Workflow Test")
	public void CreateContestPositiveTest(HashMap<String, String> table) throws Exception {

		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
		Calendar cal = null;
		cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, 1);
		String StartDate = dateFormat.format(cal.getTime());

		String Bjson = "{" + "\"" + "teamsAllowed" + "\"" + " : "
				+ RP.loadPropertiesFile(ContestCreationFilePath).get("TEAMSALLOWED") + "," + "\"" + "name" + "\""
				+ " : " + "\"" + RP.loadPropertiesFile(ContestCreationFilePath).get("NAME") + "\"" + "," + "\""
				+ "leagueId" + "\"" + " : "
				+ RP.loadPropertiesFile(MatchAndLeagueCreationFilePath)
						.get(RP.loadPropertiesFile(ConfigFilePath).getProperty("GAMETYPE") + "LEAGUEID")
				+ "," + "\"" + "isGuaranteed" + "\"" + " : "
				+ RP.loadPropertiesFile(ContestCreationFilePath).get("ISGUARANTEED").toString().toLowerCase() + ","
				+ "\"" + "isClonable" + "\"" + " : "
				+ RP.loadPropertiesFile(ContestCreationFilePath).get("ISCLONABLE").toString().toLowerCase() + "," + "\""
				+ "minPlayers" + "\"" + " : " + RP.loadPropertiesFile(ContestCreationFilePath).get("MINPLAYERS") + ","
				+ "\"" + "prizeStructureId" + "\"" + " : "
				+ RP.loadPropertiesFile(PrizeStructureCreationFilePath).get("PRIZESTRUCTUREID") + "," + "\""
				+ "isRecommended" + "\"" + " : "
				+ RP.loadPropertiesFile(ContestCreationFilePath).get("ISRECOMMENDED").toString().toLowerCase() + ","
				+ "\"" + "brandId" + "\"" + " : " + RP.loadPropertiesFile(ContestCreationFilePath).get("BRANDID") + ","
				+ "\"" + "releaseTime" + "\"" + " : " + "\"" + StartDate + "\"" + "," + "\"" + "channelIds" + "\""
				+ " : " + RP.loadPropertiesFile(ContestCreationFilePath).get("CHANNELIDS") + "}";
		System.out.println(Bjson);

		Response JsonResponse = given().log().all().header("Content-Type", table.get("Hcontent-Type")).body(Bjson)
				.when().post("/ls/api/leagueservice/contest/simpleContest").then().extract().response();

		int StatusCode = JsonResponse.getStatusCode();

		JSONObject jobj = new JSONObject(JsonResponse.asString());
		WP.WritePropertiesFile(ContestCreationFilePath, "CONTESTID", jobj.get("id").toString());
		WP.WritePropertiesFile(ContestCreationFilePath, "CONTESTJOINCODE", jobj.get("contestJoinCode").toString());
		

		Assert.assertEquals(StatusCode, Integer.parseInt(table.get("TStatusCode")));

		String Request = AllDrive.getWriter().toString().split("HTTP/1.1")[0];
		// String Response = "HTTP/1.1 " +
		// AllDrive.getWriter().toString().split("HTTP/1.1")[1];
		String Response = "HTTP/1.1 " + AllDrive.getWriter().toString().split("HTTP/1.1");
		ExtentTestManager.getTest().log(LogStatus.INFO, "REQUEST", Request);
		System.err.println(AllDrive.getWriter().toString());
		ExtentTestManager.getTest().log(LogStatus.INFO, "RESPONSE", Response);

	}

	@Test(dataProviderClass = Provider.class, dataProvider = "insertRest", description = "Howzat Contest Creation With Blank Dataset API - Negative Workflow Test",priority = 5)
	public void CreateContestWithBlankDatasetNegativeTest(HashMap<String, String> table) throws Exception {

		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar cal = null;
		cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, 1);
		String StartDate = dateFormat.format(cal.getTime());

		String Bjson = "{" + "\"" + "teamsAllowed" + "\"" + " : "
				+ RP.loadPropertiesFile(ContestCreationFilePath).get("TEAMSALLOWED") + "," + "\"" + "name" + "\""
				+ " : " + "\"" + RP.loadPropertiesFile(ContestCreationFilePath).get("NAME") + "\"" + "," + "\""
				+ "leagueId" + "\"" + " : " + "," + "\"" + "isGuaranteed" + "\"" + " : "
				+ RP.loadPropertiesFile(ContestCreationFilePath).get("ISGUARANTEED").toString().toLowerCase() + ","
				+ "\"" + "isClonable" + "\"" + " : "
				+ RP.loadPropertiesFile(ContestCreationFilePath).get("ISCLONABLE").toString().toLowerCase() + "," + "\""
				+ "minPlayers" + "\"" + " : " + RP.loadPropertiesFile(ContestCreationFilePath).get("MINPLAYERS") + ","
				+ "\"" + "prizeStructureId" + "\"" + " : " + "," + "\"" + "isRecommended" + "\"" + " : "
				+ RP.loadPropertiesFile(ContestCreationFilePath).get("ISRECOMMENDED").toString().toLowerCase() + ","
				+ "\"" + "brandId" + "\"" + " : " + RP.loadPropertiesFile(ContestCreationFilePath).get("BRANDID") + ","
				+ "\"" + "releaseTime" + "\"" + " : " + "\"" + StartDate + "\"" + "," + "\"" + "channelIds" + "\""
				+ " : " + RP.loadPropertiesFile(ContestCreationFilePath).get("CHANNELIDS") + "}";

		Response JsonResponse = given().log().all().header("Content-Type", table.get("Hcontent-Type")).body(Bjson)
				.when().post("/ls/api/leagueservice/contest/simpleContest").then().extract().response();

		int StatusCode = JsonResponse.getStatusCode();

		Assert.assertEquals(StatusCode, Integer.parseInt(table.get("TStatusCode")));

		String Request = AllDrive.getWriter().toString().split("HTTP/1.1")[0];
		// String Response = "HTTP/1.1 " +
		// AllDrive.getWriter().toString().split("HTTP/1.1")[1];
		String Response = "HTTP/1.1 " + AllDrive.getWriter().toString().split("HTTP/1.1");
		ExtentTestManager.getTest().log(LogStatus.INFO, "REQUEST", Request);
		System.err.println(AllDrive.getWriter().toString());
		ExtentTestManager.getTest().log(LogStatus.INFO, "RESPONSE", Response);

	}

}