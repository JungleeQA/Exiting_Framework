package com.Junglee.APITest.HowzatCore;

import static io.restassured.RestAssured.given;
import java.util.HashMap;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.Junglee.Base.AllDrive;
import com.Junglee.Base.Base;
import com.Junglee.Base.Provider;
import com.Junglee.Utilities.ExtentTestManager;
import com.relevantcodes.extentreports.LogStatus;
import io.restassured.response.Response;
import com.Junglee.Utilities.*;

public class HowzatGetGameRulesAPI extends Base {
	ReadPropertiesFiles RP = new ReadPropertiesFiles();
	WritePropertiesFiles WP = new WritePropertiesFiles();

	String ConfigFilePath = "../APIAutoationFramework/src/test/resources/properties/Config.properties";
	String CricketFilePath = RP.loadPropertiesFile(ConfigFilePath).getProperty("CRICKETCONFIGFILEPATH");
	String KabaddiFilePath = RP.loadPropertiesFile(ConfigFilePath).getProperty("KABADDICONFIGFILEPATH");
	String FootballFilePath = RP.loadPropertiesFile(ConfigFilePath).getProperty("FOOTBALLCONFIGFILEPATH");

	@Test(dataProviderClass = Provider.class, dataProvider = "insertRest", description = "Howzat Get Cricket Game Rules API - Positive Workflow Test")
	public void GetCricketRulesPositiveTest(HashMap<String, String> table) throws Exception {

		Response JsonResponse = given().log().all().header("Content-Type", table.get("Hcontent-Type")).when()
				.get("/ls/api/zk/getPropertyValue/" + RP.loadPropertiesFile(ConfigFilePath).getProperty("FANTEAMRULES"))
				.then().extract().response();

		int StatusCode = JsonResponse.getStatusCode();

		JSONObject jobj = new JSONObject(JsonResponse.asString());
		WP.WritePropertiesFile(CricketFilePath, "TOTALFOREIGNPLAYERS", jobj.get("TOTAL_FOREIGN_PLAYERS").toString());
		WP.WritePropertiesFile(CricketFilePath, "TOTALPLAYERSPERREALTEAM",
				jobj.get("TOTAL_PLAYERS_PER_REAL_TEAM").toString());
		WP.WritePropertiesFile(CricketFilePath, "TOTALPLAYERSINFANTEAM",
				jobj.get("TOTAL_PLAYERS_IN_FAN_TEAM").toString());
		WP.WritePropertiesFile(CricketFilePath, "TOTALCREDITS", jobj.get("TOTAL_CREDITS").toString());

		JSONArray jsonarr_1 = (JSONArray) jobj.get("styles");
		for (int i = 0; i < jsonarr_1.length(); i++) {
			JSONObject jsonobj_1 = (JSONObject) jsonarr_1.get(i);
			JSONArray jsonarr_2 = (JSONArray) jsonobj_1.get("rule");
			JSONObject jsonobj_2 = (JSONObject) jsonarr_1.get(i);
			if (jsonobj_2.get("rule").toString().split(",").length > 1) {
				WP.WritePropertiesFile(CricketFilePath, "MIN"
						+ jsonobj_1.get("label").toString().toUpperCase().replace(" ", "").replace("-", "") + "ALLOWED",
						jsonobj_2.get("rule").toString().split(",")[0].replace("[", ""));
				WP.WritePropertiesFile(CricketFilePath, "MAX"
						+ jsonobj_1.get("label").toString().toUpperCase().replace(" ", "").replace("-", "") + "ALLOWED",
						jsonobj_2.get("rule").toString().split(",")[1].replace("]", ""));
			} else {
			WP.WritePropertiesFile(CricketFilePath, "MIN"
						+ jsonobj_1.get("label").toString().toUpperCase().replace(" ", "").replace("-", "") + "ALLOWED",
						jsonobj_2.get("rule").toString().replace("[", "").replace("]", ""));
				WP.WritePropertiesFile(CricketFilePath, "MAX"
						+ jsonobj_1.get("label").toString().toUpperCase().replace(" ", "").replace("-", "") + "ALLOWED",
						jsonobj_2.get("rule").toString().replace("[", "").replace("]", ""));
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

	}

	@Test(dataProviderClass = Provider.class, dataProvider = "insertRest", description = "Howzat Get Football Game Rules API - Positive Workflow Test")
	public void GetFootballRulesPositiveTest(HashMap<String, String> table) throws Exception {

		Response JsonResponse = given().log().all().header("Content-Type", table.get("Hcontent-Type")).when()
				.get("/ls/api/zk/getPropertyValue/"
						+ RP.loadPropertiesFile(ConfigFilePath).getProperty("FANTEAMRULESFOOTBALL"))
				.then().extract().response();

		int StatusCode = JsonResponse.getStatusCode();

		JSONObject jobj = new JSONObject(JsonResponse.asString());
		WP.WritePropertiesFile(FootballFilePath, "TOTALFOREIGNPLAYERS", jobj.get("TOTAL_FOREIGN_PLAYERS").toString());
		WP.WritePropertiesFile(FootballFilePath, "TOTALPLAYERSPERREALTEAM",
				jobj.get("TOTAL_PLAYERS_PER_REAL_TEAM").toString());
		WP.WritePropertiesFile(FootballFilePath, "TOTALPLAYERSINFANTEAM",
				jobj.get("TOTAL_PLAYERS_IN_FAN_TEAM").toString());
		WP.WritePropertiesFile(FootballFilePath, "TOTALCREDITS", jobj.get("TOTAL_CREDITS").toString());

		JSONArray jsonarr_1 = (JSONArray) jobj.get("styles");
		for (int i = 0; i < jsonarr_1.length(); i++) {
			JSONObject jsonobj_1 = (JSONObject) jsonarr_1.get(i);
			JSONArray jsonarr_2 = (JSONArray) jsonobj_1.get("rule");
			JSONObject jsonobj_2 = (JSONObject) jsonarr_1.get(i);

			if (jsonobj_2.get("rule").toString().split(",").length > 1) {
				WP.WritePropertiesFile(FootballFilePath, "MIN"
						+ jsonobj_1.get("label").toString().toUpperCase().replace(" ", "").replace("-", "") + "ALLOWED",
						jsonobj_2.get("rule").toString().split(",")[0].replace("[", ""));
				WP.WritePropertiesFile(FootballFilePath, "MAX"
						+ jsonobj_1.get("label").toString().toUpperCase().replace(" ", "").replace("-", "") + "ALLOWED",
						jsonobj_2.get("rule").toString().split(",")[1].replace("]", ""));
			} else {
				WP.WritePropertiesFile(FootballFilePath, "MIN"
						+ jsonobj_1.get("label").toString().toUpperCase().replace(" ", "").replace("-", "") + "ALLOWED",
						jsonobj_2.get("rule").toString().replace("[", "").replace("]", ""));
				WP.WritePropertiesFile(FootballFilePath, "MAX"
						+ jsonobj_1.get("label").toString().toUpperCase().replace(" ", "").replace("-", "") + "ALLOWED",
						jsonobj_2.get("rule").toString().replace("[", "").replace("]", ""));
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

	}

	@Test(dataProviderClass = Provider.class, dataProvider = "insertRest", description = "Howzat Get Kabaddi Game Rules API - Positive Workflow Test")
	public void GetKabaddiRulesPositiveTest(HashMap<String, String> table) throws Exception {

		Response JsonResponse = given().log().all().header("Content-Type", table.get("Hcontent-Type")).when()
				.get("/ls/api/zk/getPropertyValue/"
						+ RP.loadPropertiesFile(ConfigFilePath).getProperty("FANTEAMRULESKABADDI"))
				.then().extract().response();

		int StatusCode = JsonResponse.getStatusCode();

		JSONObject jobj = new JSONObject(JsonResponse.asString());
		WP.WritePropertiesFile(KabaddiFilePath, "TOTALFOREIGNPLAYERS", jobj.get("TOTAL_FOREIGN_PLAYERS").toString());
		WP.WritePropertiesFile(KabaddiFilePath, "TOTALPLAYERSPERREALTEAM",
				jobj.get("TOTAL_PLAYERS_PER_REAL_TEAM").toString());
		WP.WritePropertiesFile(KabaddiFilePath, "TOTALPLAYERSINFANTEAM",
				jobj.get("TOTAL_PLAYERS_IN_FAN_TEAM").toString());
		WP.WritePropertiesFile(KabaddiFilePath, "TOTALCREDITS", jobj.get("TOTAL_CREDITS").toString());

		JSONArray jsonarr_1 = (JSONArray) jobj.get("styles");
		for (int i = 0; i < jsonarr_1.length(); i++) {
			JSONObject jsonobj_1 = (JSONObject) jsonarr_1.get(i);
			JSONArray jsonarr_2 = (JSONArray) jsonobj_1.get("rule");
			JSONObject jsonobj_2 = (JSONObject) jsonarr_1.get(i);

			if (jsonobj_2.get("rule").toString().split(",").length > 1) {
				WP.WritePropertiesFile(KabaddiFilePath, "MIN"
						+ jsonobj_1.get("label").toString().toUpperCase().replace(" ", "").replace("-", "") + "ALLOWED",
						jsonobj_2.get("rule").toString().split(",")[0].replace("[", ""));
				WP.WritePropertiesFile(KabaddiFilePath, "MAX"
						+ jsonobj_1.get("label").toString().toUpperCase().replace(" ", "").replace("-", "") + "ALLOWED",
						jsonobj_2.get("rule").toString().split(",")[1].replace("]", ""));
			} else {
				WP.WritePropertiesFile(KabaddiFilePath, "MIN"
						+ jsonobj_1.get("label").toString().toUpperCase().replace(" ", "").replace("-", "") + "ALLOWED",
						jsonobj_2.get("rule").toString().replace("[", "").replace("]", ""));
				WP.WritePropertiesFile(KabaddiFilePath, "MAX"
						+ jsonobj_1.get("label").toString().toUpperCase().replace(" ", "").replace("-", "") + "ALLOWED",
						jsonobj_2.get("rule").toString().replace("[", "").replace("]", ""));
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

	}

}