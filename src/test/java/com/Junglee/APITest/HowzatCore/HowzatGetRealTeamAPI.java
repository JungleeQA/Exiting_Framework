package com.Junglee.APITest.HowzatCore;

import static io.restassured.RestAssured.given;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.HashMap;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.Junglee.Base.AllDrive;
import com.Junglee.Base.Base;
import com.Junglee.Base.Provider;
import com.Junglee.Utilities.ExtentTestManager;
import com.Junglee.Utilities.ReadPropertiesFiles;
import com.relevantcodes.extentreports.LogStatus;
import io.restassured.response.Response;
import java.io.File;

public class HowzatGetRealTeamAPI extends Base {

	@Test(dataProviderClass = Provider.class, dataProvider = "insertRest", description = "Howzat Get Real Team Data Displayed API - Positive Workflow Test")
	public void GetRealTeamDataDisplayedPositiveTest(HashMap<String, String> table) throws Exception {
		ReadPropertiesFiles RP = new ReadPropertiesFiles();

		String ConfigFilePath = "../APIAutoationFramework/src/test/resources/properties/Config.properties";
		String MatchAndLeagueCreationFilePath = RP.loadPropertiesFile(ConfigFilePath)
				.getProperty("MATCHANDLEAGUECREATIONFILEPATH");
		String GameType = RP.loadPropertiesFile(ConfigFilePath).getProperty("GAMETYPE");
		String TeamA = RP.loadPropertiesFile(MatchAndLeagueCreationFilePath).getProperty("TEAMA");
		String TeamB = RP.loadPropertiesFile(MatchAndLeagueCreationFilePath).getProperty("TEAMB");
		String RealTeamAPlayerDeatils = RP.loadPropertiesFile(ConfigFilePath).getProperty("REALTEAMAPLAYERDETAILS");
		String RealTeamBPlayerDeatils = RP.loadPropertiesFile(ConfigFilePath).getProperty("REALTEAMBPLAYERDETAILS");

		File file = new File(RealTeamAPlayerDeatils);
		boolean exists = file.exists();
		if (!exists) {
			file.createNewFile();
		} else {
			PrintWriter writer = new PrintWriter(RealTeamAPlayerDeatils);
			writer.print("");
			writer.close();
		}

		Response JsonResponse = given().log().all().header("" + "Content-Type", table.get("Hcontent-Type")).when()
				.get("/ls/api/leagueservice/team/" + TeamA).then().extract().response();

		int StatusCode = JsonResponse.getStatusCode();

		String JRes = JsonResponse.asString();
		JSONObject jobj = new JSONObject(JRes);
		System.out.println("JSon Object Srting is " + JRes);
		JSONArray jsonarr_1 = (JSONArray) jobj.get("players");
		System.out.println("JSOn Array is " + jsonarr_1);

		for (int j = 0; j < jsonarr_1.length(); j++) {

			JSONObject jsonobj_1 = (JSONObject) jsonarr_1.get(j);
			jsonobj_1.remove("countryName");
			jsonobj_1.remove("sportName");
			jsonobj_1.put("teamId",
					Integer.parseInt(RP.loadPropertiesFile(MatchAndLeagueCreationFilePath).getProperty("TEAMA")));
			jsonobj_1.put("seriesScore", Integer.parseInt("0"));
			jsonobj_1.put("jerseyUrl", jobj.get("jerseyUrl"));
			System.out.println(jsonobj_1.toString());

			FileWriter FileWrite = new FileWriter(RealTeamAPlayerDeatils, true);
			try {
				if (j == 0) {
					FileWrite.write(
							"{" + "\n" + "\"" + "players" + "\"" + ":[" + "\n" + jsonobj_1.toString() + "," + "\n");
				} else if (j == jsonarr_1.length() - 1) {
					FileWrite.write(jsonobj_1.toString() + "\n" + "]" + "\n" + "}");
				} else {
					FileWrite.write(jsonobj_1.toString() + "," + "\n");
				}
				System.out.println("Successfully copied JSON Object to File... ");
				System.out.println("\n JSON Object: " + jsonobj_1.toString());

			} finally {
				FileWrite.flush();
			}

		}

		file = new File(RealTeamBPlayerDeatils);
		exists = file.exists();
		if (!exists) {
			file.createNewFile();
		} else {
			PrintWriter writer = new PrintWriter(RealTeamBPlayerDeatils);
			writer.print("");
			writer.close();
		}

		JsonResponse = given().log().all().header("" + "Content-Type", table.get("Hcontent-Type")).when()
				.get("/ls/api/leagueservice/team/" + TeamB).then().extract().response();

		StatusCode = JsonResponse.getStatusCode();

		JRes = JsonResponse.asString();
		jobj = new JSONObject(JRes);
		System.out.println("JSon Object Srting is " + JRes);
		jsonarr_1 = (JSONArray) jobj.get("players");
		System.out.println("JSOn Array is " + jsonarr_1);

		for (int j = 0; j < jsonarr_1.length(); j++) {

			JSONObject jsonobj_1 = (JSONObject) jsonarr_1.get(j);
			jsonobj_1.remove("countryName");
			jsonobj_1.remove("sportName");
			jsonobj_1.put("teamId",
					Integer.parseInt(RP.loadPropertiesFile(MatchAndLeagueCreationFilePath).getProperty("TEAMB")));
			jsonobj_1.put("seriesScore", Integer.parseInt("0"));
			jsonobj_1.put("jerseyUrl", jobj.get("jerseyUrl"));
			System.out.println(jsonobj_1.toString());

			FileWriter FileWrite = new FileWriter(RealTeamBPlayerDeatils, true);
			try {
				if (j == 0) {
					FileWrite.write(
							"{" + "\n" + "\"" + "players" + "\"" + ":[" + "\n" + jsonobj_1.toString() + "," + "\n");
				} else if (j == jsonarr_1.length() - 1) {
					FileWrite.write(jsonobj_1.toString() + "\n" + "]" + "\n" + "}");
				} else {
					FileWrite.write(jsonobj_1.toString() + "," + "\n");
				}
				System.out.println("Successfully copied JSON Object to File... ");
				System.out.println("\n JSON Object: " + jsonobj_1.toString());

			} finally {
				FileWrite.flush();
			}

		}

		Assert.assertEquals(StatusCode, Integer.parseInt(table.get("TStatusCode")));

		String Request = AllDrive.getWriter().toString().split("HTTP/1.1")[0];
		// String Response = "HTTP/1.1 " +
		// AllDrive.getWriter().toString().split("HTTP/1.1")[1];
		String Response = "HTTP/1.1 " + AllDrive.getWriter().toString().split("HTTP/1.1");
		ExtentTestManager.getTest().log(LogStatus.INFO, "REQUEST", Request);
		System.err.println(AllDrive.getWriter().toString());
		ExtentTestManager.getTest().log(LogStatus.INFO, "RESPONSE", Response);

	}


	@Test(dataProviderClass = Provider.class, dataProvider = "insertRest", description = "Howzat Get Real Team Data With Invalid Team ID API - Positive Workflow Test - Negative Workflow Test",priority = 6)
	public void GetRealTeamDataDisplayedWithInvalidTeamIdNegativeTest(HashMap<String, String> table) throws Exception {
		ReadPropertiesFiles RP = new ReadPropertiesFiles();

		String ConfigFilePath = "../APIAutoationFramework/src/test/resources/properties/Config.properties";
		String MatchAndLeagueCreationFilePath = RP.loadPropertiesFile(ConfigFilePath)
				.getProperty("MATCHANDLEAGUECREATIONFILEPATH");
		String InvalidTeam = RP.loadPropertiesFile(MatchAndLeagueCreationFilePath).getProperty("INVALIDTEAMID");

		Response JsonResponse = given().log().all().header("" + "Content-Type", table.get("Hcontent-Type")).when()
				.get("/ls/api/leagueservice/team/" + InvalidTeam).then().extract().response();

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