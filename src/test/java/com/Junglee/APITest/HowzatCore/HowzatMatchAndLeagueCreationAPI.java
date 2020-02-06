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
import com.relevantcodes.extentreports.LogStatus;
import io.restassured.response.Response;
import com.Junglee.Utilities.*;

public class HowzatMatchAndLeagueCreationAPI extends Base {
	ReadPropertiesFiles RP = new ReadPropertiesFiles();
	WritePropertiesFiles WP = new WritePropertiesFiles();

	String ConfigFilePath = "../APIAutoationFramework/src/test/resources/properties/Config.properties";
	String MatchAndLeagueCreationFilePath = RP.loadPropertiesFile(ConfigFilePath)
			.getProperty("MATCHANDLEAGUECREATIONFILEPATH");
	String SeriesCreationFilePath = RP.loadPropertiesFile(ConfigFilePath).getProperty("SERIESCREATIONCONFIGFILEPATH");

	DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	Calendar cal = null;

	@Test(dataProviderClass = Provider.class, dataProvider = "insertRest", description = "Howzat Match And League Series API - Positive Workflow Test")
	public void CreateMatchAndLeaguePositiveTest(HashMap<String, String> table) throws Exception {

		cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, 1);
		String StartDate = dateFormat.format(cal.getTime());
		String Bjson = "";
		String GameType = RP.loadPropertiesFile(ConfigFilePath).getProperty("GAMETYPE");
		String PartialRequestURL = null;

		if (GameType.equalsIgnoreCase("CRICKET")) {
			PartialRequestURL = "?name=" + RP.loadPropertiesFile(MatchAndLeagueCreationFilePath).get("NAME")
					+ "&sportType=" + RP.loadPropertiesFile(MatchAndLeagueCreationFilePath).get("CRICKETSPORTTYPE")
					+ "&sportFormat="
					+ RP.loadPropertiesFile(MatchAndLeagueCreationFilePath).get("CRICKETT20SPORTFORMATTYPE") + "&teamA="
					+ RP.loadPropertiesFile(MatchAndLeagueCreationFilePath).get("TEAMA") + "&teamB="
					+ RP.loadPropertiesFile(MatchAndLeagueCreationFilePath).get("TEAMB") + "&seriesId="
					+ RP.loadPropertiesFile(SeriesCreationFilePath).get("DOMESTICSERIESID") + "&startTime=" + StartDate;
		}

		if (GameType.equalsIgnoreCase("FOOTBALL")) {
			PartialRequestURL = "?name=" + RP.loadPropertiesFile(MatchAndLeagueCreationFilePath).get("NAME")
					+ "&sportType=" + RP.loadPropertiesFile(MatchAndLeagueCreationFilePath).get("FOOTBALLSPORTTYPE")
					+ "&sportFormat="
					+ RP.loadPropertiesFile(MatchAndLeagueCreationFilePath).get("FOOTBALLSPORTFORMATTYPE") + "&teamA="
					+ RP.loadPropertiesFile(MatchAndLeagueCreationFilePath).get("TEAMA") + "&teamB="
					+ RP.loadPropertiesFile(MatchAndLeagueCreationFilePath).get("TEAMB") + "&seriesId="
					+ RP.loadPropertiesFile(SeriesCreationFilePath).get("DOMESTICSERIESID") + "&startTime=" + StartDate;
		}

		if (GameType.equalsIgnoreCase("KABADDI")) {
			PartialRequestURL = "?name=" + RP.loadPropertiesFile(MatchAndLeagueCreationFilePath).get("NAME")
					+ "&sportType=" + RP.loadPropertiesFile(MatchAndLeagueCreationFilePath).get("KABADDISPORTTYPE")
					+ "&sportFormat="
					+ RP.loadPropertiesFile(MatchAndLeagueCreationFilePath).get("KABADDISPORTFORMATTYPE") + "&teamA="
					+ RP.loadPropertiesFile(MatchAndLeagueCreationFilePath).get("TEAMA") + "&teamB="
					+ RP.loadPropertiesFile(MatchAndLeagueCreationFilePath).get("TEAMB") + "&seriesId="
					+ RP.loadPropertiesFile(SeriesCreationFilePath).get("DOMESTICSERIESID") + "&startTime=" + StartDate;
		}

		Response JsonResponse = given().log().all().header("Content-Type", table.get("Hcontent-Type")).body(Bjson)
				.when().post("/ls/api/leagueservice/league/createMatchAndLeague" + PartialRequestURL).then().extract()
				.response();

		int StatusCode = JsonResponse.getStatusCode();

		JSONObject jobj = new JSONObject(JsonResponse.asString());
		System.out.println(JsonResponse.asString());
		WP.WritePropertiesFile(MatchAndLeagueCreationFilePath, GameType + "INNINGS1ID",
				jobj.get("innings2").toString());
		WP.WritePropertiesFile(MatchAndLeagueCreationFilePath, GameType + "LEAGUEID", jobj.get("leagueId").toString());
		WP.WritePropertiesFile(MatchAndLeagueCreationFilePath, GameType + "MATCHID", jobj.get("matchId").toString());
		WP.WritePropertiesFile(MatchAndLeagueCreationFilePath, GameType + "INNINGS2ID",
				jobj.get("innings1").toString());

		Assert.assertEquals(StatusCode, Integer.parseInt(table.get("TStatusCode")));

		String Request = AllDrive.getWriter().toString().split("HTTP/1.1")[0];
		// String Response = "HTTP/1.1 " +
		// AllDrive.getWriter().toString().split("HTTP/1.1")[1];
		String Response = "HTTP/1.1 " + AllDrive.getWriter().toString().split("HTTP/1.1");
		ExtentTestManager.getTest().log(LogStatus.INFO, "REQUEST", Request);
		System.err.println(AllDrive.getWriter().toString());
		ExtentTestManager.getTest().log(LogStatus.INFO, "RESPONSE", Response);

	}

	/*@Test(dataProviderClass = Provider.class, dataProvider = "insertRest", description = "Howzat Create Match And League Series API Without Sports Type - Negative Workflow Test",priority = 2)
	public void CreateMatchAndLeagueWithoutSportsTypeNegativeTest(HashMap<String, String> table) throws Exception {

		cal = Calendar.getInstance();
		String StartDate = dateFormat.format(cal.getTime());
		String Bjson = "";
		String GameType = RP.loadPropertiesFile(ConfigFilePath).getProperty("GAMETYPE");
		String PartialRequestURL = null;

		if (GameType.equalsIgnoreCase("CRICKET")) {
			PartialRequestURL = "?name=" + RP.loadPropertiesFile(MatchAndLeagueCreationFilePath).get("NAME")
					+ "&sportType=" + "&sportFormat="
					+ RP.loadPropertiesFile(MatchAndLeagueCreationFilePath).get("CRICKETT20SPORTFORMATTYPE") + "&teamA="
					+ RP.loadPropertiesFile(MatchAndLeagueCreationFilePath).get("TEAMA") + "&teamB="
					+ RP.loadPropertiesFile(MatchAndLeagueCreationFilePath).get("TEAMB") + "&seriesId="
					+ RP.loadPropertiesFile(SeriesCreationFilePath).get("DOMESTICSERIESID") + "&startTime=" + StartDate;
		}

		if (GameType.equalsIgnoreCase("FOOTBALL")) {
			PartialRequestURL = "?name=" + RP.loadPropertiesFile(MatchAndLeagueCreationFilePath).get("NAME")
					+ "&sportType=" + "&sportFormat="
					+ RP.loadPropertiesFile(MatchAndLeagueCreationFilePath).get("FOOTBALLSPORTFORMATTYPE") + "&teamA="
					+ RP.loadPropertiesFile(MatchAndLeagueCreationFilePath).get("TEAMA") + "&teamB="
					+ RP.loadPropertiesFile(MatchAndLeagueCreationFilePath).get("TEAMB") + "&seriesId="
					+ RP.loadPropertiesFile(SeriesCreationFilePath).get("DOMESTICSERIESID") + "&startTime=" + StartDate;
		}

		if (GameType.equalsIgnoreCase("KABADDI")) {
			PartialRequestURL = "?name=" + RP.loadPropertiesFile(MatchAndLeagueCreationFilePath).get("NAME")
					+ "&sportType=" + "&sportFormat="
					+ RP.loadPropertiesFile(MatchAndLeagueCreationFilePath).get("KABADDISPORTFORMATTYPE") + "&teamA="
					+ RP.loadPropertiesFile(MatchAndLeagueCreationFilePath).get("TEAMA") + "&teamB="
					+ RP.loadPropertiesFile(MatchAndLeagueCreationFilePath).get("TEAMB") + "&seriesId="
					+ RP.loadPropertiesFile(SeriesCreationFilePath).get("DOMESTICSERIESID") + "&startTime=" + StartDate;
		}
		given().log().all().header("Content-Type", table.get("Hcontent-Type")).body(Bjson).when()
				.post("/ls/api/leagueservice/league/createMatchAndLeague" + PartialRequestURL).then().log().all().and()
				.assertThat().statusCode(Integer.parseInt(table.get("TStatusCode")));

		String Request = AllDrive.getWriter().toString().split("HTTP/1.1")[0];
		// String Response = "HTTP/1.1 " +
		// AllDrive.getWriter().toString().split("HTTP/1.1")[1];
		String Response = "HTTP/1.1 " + AllDrive.getWriter().toString().split("HTTP/1.1");
		ExtentTestManager.getTest().log(LogStatus.INFO, "REQUEST", Request);
		System.err.println(AllDrive.getWriter().toString());
		ExtentTestManager.getTest().log(LogStatus.INFO, "RESPONSE", Response);

	}*/

	/*@Test(dataProviderClass = Provider.class, dataProvider = "insertRest", description = "Howzat Create Match And League Series API Without Sports Format - Negative Workflow Test",priority = 2)
	public void CreateMatchAndLeagueWithoutSportsFormatNegativeTest(HashMap<String, String> table) throws Exception {

		cal = Calendar.getInstance();
		String StartDate = dateFormat.format(cal.getTime());
		String Bjson = "";
		String GameType = RP.loadPropertiesFile(ConfigFilePath).getProperty("GAMETYPE");
		String PartialRequestURL = null;

		if (GameType.equalsIgnoreCase("CRICKET")) {
			PartialRequestURL = "?name=" + RP.loadPropertiesFile(MatchAndLeagueCreationFilePath).get("NAME")
					+ "&sportType=" + RP.loadPropertiesFile(MatchAndLeagueCreationFilePath).get("CRICKETSPORTTYPE")
					+ "&sportFormat=" + "&teamA=" + RP.loadPropertiesFile(MatchAndLeagueCreationFilePath).get("TEAMA")
					+ "&teamB=" + RP.loadPropertiesFile(MatchAndLeagueCreationFilePath).get("TEAMB") + "&seriesId="
					+ RP.loadPropertiesFile(SeriesCreationFilePath).get("DOMESTICSERIESID") + "&startTime=" + StartDate;
		}

		if (GameType.equalsIgnoreCase("FOOTBALL")) {
			PartialRequestURL = "?name=" + RP.loadPropertiesFile(MatchAndLeagueCreationFilePath).get("NAME")
					+ "&sportType=" + RP.loadPropertiesFile(MatchAndLeagueCreationFilePath).get("FOOTBALLSPORTTYPE")
					+ "&sportFormat=" + "&teamA=" + RP.loadPropertiesFile(MatchAndLeagueCreationFilePath).get("TEAMA")
					+ "&teamB=" + RP.loadPropertiesFile(MatchAndLeagueCreationFilePath).get("TEAMB") + "&seriesId="
					+ RP.loadPropertiesFile(SeriesCreationFilePath).get("DOMESTICSERIESID") + "&startTime=" + StartDate;
		}

		if (GameType.equalsIgnoreCase("KABADDI")) {
			PartialRequestURL = "?name=" + RP.loadPropertiesFile(MatchAndLeagueCreationFilePath).get("NAME")
					+ "&sportType=" + RP.loadPropertiesFile(MatchAndLeagueCreationFilePath).get("KABADDISPORTTYPE")
					+ "&sportFormat=" + "&teamA=" + RP.loadPropertiesFile(MatchAndLeagueCreationFilePath).get("TEAMA")
					+ "&teamB=" + RP.loadPropertiesFile(MatchAndLeagueCreationFilePath).get("TEAMB") + "&seriesId="
					+ RP.loadPropertiesFile(SeriesCreationFilePath).get("DOMESTICSERIESID") + "&startTime=" + StartDate;
		}

		given().log().all().header("Content-Type", table.get("Hcontent-Type")).body(Bjson).when()
				.post("/ls/api/leagueservice/league/createMatchAndLeague" + PartialRequestURL).then().log().all().and()
				.assertThat().statusCode(Integer.parseInt(table.get("TStatusCode")));

		String Request = AllDrive.getWriter().toString().split("HTTP/1.1")[0];
		// String Response = "HTTP/1.1 " +
		// AllDrive.getWriter().toString().split("HTTP/1.1")[1];
		String Response = "HTTP/1.1 " + AllDrive.getWriter().toString().split("HTTP/1.1");
		ExtentTestManager.getTest().log(LogStatus.INFO, "REQUEST", Request);
		System.err.println(AllDrive.getWriter().toString());
		ExtentTestManager.getTest().log(LogStatus.INFO, "RESPONSE", Response);

	}
*/
	/*@Test(dataProviderClass = Provider.class, dataProvider = "insertRest", description = "Howzat Create Match And League Series API With Invalid Sports Type - Negative Workflow Test",priority = 2)
	public void CreateMatchAndLeagueWithInvalidSportsTypeNegativeTest(HashMap<String, String> table) throws Exception {

		cal = Calendar.getInstance();
		String StartDate = dateFormat.format(cal.getTime());
		String Bjson = "";
		String GameType = RP.loadPropertiesFile(ConfigFilePath).getProperty("GAMETYPE");
		String PartialRequestURL = null;

		if (GameType.equalsIgnoreCase("CRICKET")) {
			PartialRequestURL = "?name=" + RP.loadPropertiesFile(MatchAndLeagueCreationFilePath).get("NAME")
					+ "&sportType=" + RP.loadPropertiesFile(MatchAndLeagueCreationFilePath).get("INVALIDARGUMENT")
					+ "&sportFormat="
					+ RP.loadPropertiesFile(MatchAndLeagueCreationFilePath).get("CRICKETT20SPORTFORMATTYPE") + "&teamA="
					+ RP.loadPropertiesFile(MatchAndLeagueCreationFilePath).get("TEAMA") + "&teamB="
					+ RP.loadPropertiesFile(MatchAndLeagueCreationFilePath).get("TEAMB") + "&seriesId="
					+ RP.loadPropertiesFile(SeriesCreationFilePath).get("DOMESTICSERIESID") + "&startTime=" + StartDate;
		}

		if (GameType.equalsIgnoreCase("FOOTBALL")) {
			PartialRequestURL = "?name=" + RP.loadPropertiesFile(MatchAndLeagueCreationFilePath).get("NAME")
					+ "&sportType=" + RP.loadPropertiesFile(MatchAndLeagueCreationFilePath).get("INVALIDARGUMENT")
					+ "&sportFormat="
					+ RP.loadPropertiesFile(MatchAndLeagueCreationFilePath).get("FOOTBALLSPORTFORMATTYPE") + "&teamA="
					+ RP.loadPropertiesFile(MatchAndLeagueCreationFilePath).get("TEAMA") + "&teamB="
					+ RP.loadPropertiesFile(MatchAndLeagueCreationFilePath).get("TEAMB") + "&seriesId="
					+ RP.loadPropertiesFile(SeriesCreationFilePath).get("DOMESTICSERIESID") + "&startTime=" + StartDate;
		}

		if (GameType.equalsIgnoreCase("KABADDI")) {
			PartialRequestURL = "?name=" + RP.loadPropertiesFile(MatchAndLeagueCreationFilePath).get("NAME")
					+ "&sportType=" + RP.loadPropertiesFile(MatchAndLeagueCreationFilePath).get("INVALIDARGUMENT")
					+ "&sportFormat="
					+ RP.loadPropertiesFile(MatchAndLeagueCreationFilePath).get("KABADDISPORTFORMATTYPE") + "&teamA="
					+ RP.loadPropertiesFile(MatchAndLeagueCreationFilePath).get("TEAMA") + "&teamB="
					+ RP.loadPropertiesFile(MatchAndLeagueCreationFilePath).get("TEAMB") + "&seriesId="
					+ RP.loadPropertiesFile(SeriesCreationFilePath).get("DOMESTICSERIESID") + "&startTime=" + StartDate;
		}

		given().log().all().header("Content-Type", table.get("Hcontent-Type")).body(Bjson).when()
				.post("/ls/api/leagueservice/league/createMatchAndLeague" + PartialRequestURL).then().log().all().and()
				.assertThat().statusCode(Integer.parseInt(table.get("TStatusCode")));

		String Request = AllDrive.getWriter().toString().split("HTTP/1.1")[0];
		// String Response = "HTTP/1.1 " +
		// AllDrive.getWriter().toString().split("HTTP/1.1")[1];
		String Response = "HTTP/1.1 " + AllDrive.getWriter().toString().split("HTTP/1.1");
		ExtentTestManager.getTest().log(LogStatus.INFO, "REQUEST", Request);
		System.err.println(AllDrive.getWriter().toString());
		ExtentTestManager.getTest().log(LogStatus.INFO, "RESPONSE", Response);

	}
*/
	/*@Test(dataProviderClass = Provider.class, dataProvider = "insertRest", description = "Howzat Create Match And League Series API With Invalid Sports Format - Negative Workflow Test",priority = 2)
	public void CreateMatchAndLeagueWithInvalidSportsFormatNegativeTest(HashMap<String, String> table)
			throws Exception {

		cal = Calendar.getInstance();
		String StartDate = dateFormat.format(cal.getTime());
		String Bjson = "";
		String GameType = RP.loadPropertiesFile(ConfigFilePath).getProperty("GAMETYPE");
		String PartialRequestURL = null;

		if (GameType.equalsIgnoreCase("CRICKET")) {
			PartialRequestURL = "?name=" + RP.loadPropertiesFile(MatchAndLeagueCreationFilePath).get("NAME")
					+ "&sportType=" + RP.loadPropertiesFile(MatchAndLeagueCreationFilePath).get("CRICKETSPORTTYPE")
					+ "&sportFormat=" + RP.loadPropertiesFile(MatchAndLeagueCreationFilePath).get("INVALIDARGUMENT")
					+ "&teamA=" + RP.loadPropertiesFile(MatchAndLeagueCreationFilePath).get("TEAMA") + "&teamB="
					+ RP.loadPropertiesFile(MatchAndLeagueCreationFilePath).get("TEAMB") + "&seriesId="
					+ RP.loadPropertiesFile(SeriesCreationFilePath).get("DOMESTICSERIESID") + "&startTime=" + StartDate;
		}

		if (GameType.equalsIgnoreCase("FOOTBALL")) {
			PartialRequestURL = "?name=" + RP.loadPropertiesFile(MatchAndLeagueCreationFilePath).get("NAME")
					+ "&sportType=" + RP.loadPropertiesFile(MatchAndLeagueCreationFilePath).get("FOOTBALLSPORTTYPE")
					+ "&sportFormat=" + RP.loadPropertiesFile(MatchAndLeagueCreationFilePath).get("INVALIDARGUMENT")
					+ "&teamA=" + RP.loadPropertiesFile(MatchAndLeagueCreationFilePath).get("TEAMA") + "&teamB="
					+ RP.loadPropertiesFile(MatchAndLeagueCreationFilePath).get("TEAMB") + "&seriesId="
					+ RP.loadPropertiesFile(SeriesCreationFilePath).get("DOMESTICSERIESID") + "&startTime=" + StartDate;
		}

		if (GameType.equalsIgnoreCase("KABADDI")) {
			PartialRequestURL = "?name=" + RP.loadPropertiesFile(MatchAndLeagueCreationFilePath).get("NAME")
					+ "&sportType=" + RP.loadPropertiesFile(MatchAndLeagueCreationFilePath).get("KABADDISPORTTYPE")
					+ "&sportFormat=" + RP.loadPropertiesFile(MatchAndLeagueCreationFilePath).get("INVALIDARGUMENT")
					+ "&teamA=" + RP.loadPropertiesFile(MatchAndLeagueCreationFilePath).get("TEAMA") + "&teamB="
					+ RP.loadPropertiesFile(MatchAndLeagueCreationFilePath).get("TEAMB") + "&seriesId="
					+ RP.loadPropertiesFile(SeriesCreationFilePath).get("DOMESTICSERIESID") + "&startTime=" + StartDate;
		}

		given().log().all().header("Content-Type", table.get("Hcontent-Type")).body(Bjson).when()
				.post("/ls/api/leagueservice/league/createMatchAndLeague" + PartialRequestURL).then().log().all().and()
				.assertThat().statusCode(Integer.parseInt(table.get("TStatusCode")));

		String Request = AllDrive.getWriter().toString().split("HTTP/1.1")[0];
		// String Response = "HTTP/1.1 " +
		// AllDrive.getWriter().toString().split("HTTP/1.1")[1];
		String Response = "HTTP/1.1 " + AllDrive.getWriter().toString().split("HTTP/1.1");
		ExtentTestManager.getTest().log(LogStatus.INFO, "REQUEST", Request);
		System.err.println(AllDrive.getWriter().toString());
		ExtentTestManager.getTest().log(LogStatus.INFO, "RESPONSE", Response);

	}
*/
	/*@Test(dataProviderClass = Provider.class, dataProvider = "insertRest", description = "Howzat Create Match And League Series API Without Team A - Negative Workflow Test",priority = 2)
	public void CreateMatchAndLeagueWithoutTeamANegativeTest(HashMap<String, String> table) throws Exception {

		cal = Calendar.getInstance();
		String StartDate = dateFormat.format(cal.getTime());
		String Bjson = "";
		String GameType = RP.loadPropertiesFile(ConfigFilePath).getProperty("GAMETYPE");
		String PartialRequestURL = null;

		if (GameType.equalsIgnoreCase("CRICKET")) {
			PartialRequestURL = "?name=" + RP.loadPropertiesFile(MatchAndLeagueCreationFilePath).get("NAME")
					+ "&sportType=" + RP.loadPropertiesFile(MatchAndLeagueCreationFilePath).get("CRICKETSPORTTYPE")
					+ "&sportFormat=" + "&teamA=" + "&teamB="
					+ RP.loadPropertiesFile(MatchAndLeagueCreationFilePath).get("TEAMB") + "&seriesId="
					+ RP.loadPropertiesFile(SeriesCreationFilePath).get("DOMESTICSERIESID") + "&startTime=" + StartDate;
		}

		if (GameType.equalsIgnoreCase("FOOTBALL")) {
			PartialRequestURL = "?name=" + RP.loadPropertiesFile(MatchAndLeagueCreationFilePath).get("NAME")
					+ "&sportType=" + RP.loadPropertiesFile(MatchAndLeagueCreationFilePath).get("FOOTBALLSPORTTYPE")
					+ "&sportFormat=" + "&teamA=" + "&teamB="
					+ RP.loadPropertiesFile(MatchAndLeagueCreationFilePath).get("TEAMB") + "&seriesId="
					+ RP.loadPropertiesFile(SeriesCreationFilePath).get("DOMESTICSERIESID") + "&startTime=" + StartDate;
		}

		if (GameType.equalsIgnoreCase("KABADDI")) {
			PartialRequestURL = "?name=" + RP.loadPropertiesFile(MatchAndLeagueCreationFilePath).get("NAME")
					+ "&sportType=" + RP.loadPropertiesFile(MatchAndLeagueCreationFilePath).get("KABADDISPORTTYPE")
					+ "&sportFormat=" + "&teamA=" + "&teamB="
					+ RP.loadPropertiesFile(MatchAndLeagueCreationFilePath).get("TEAMB") + "&seriesId="
					+ RP.loadPropertiesFile(SeriesCreationFilePath).get("DOMESTICSERIESID") + "&startTime=" + StartDate;
		}

		given().log().all().header("Content-Type", table.get("Hcontent-Type")).body(Bjson).when()
				.post("/ls/api/leagueservice/league/createMatchAndLeague" + PartialRequestURL).then().log().all().and()
				.assertThat().statusCode(Integer.parseInt(table.get("TStatusCode")));

		String Request = AllDrive.getWriter().toString().split("HTTP/1.1")[0];
		// String Response = "HTTP/1.1 " +
		// AllDrive.getWriter().toString().split("HTTP/1.1")[1];
		String Response = "HTTP/1.1 " + AllDrive.getWriter().toString().split("HTTP/1.1");
		ExtentTestManager.getTest().log(LogStatus.INFO, "REQUEST", Request);
		System.err.println(AllDrive.getWriter().toString());
		ExtentTestManager.getTest().log(LogStatus.INFO, "RESPONSE", Response);

	}*/

	/*@Test(dataProviderClass = Provider.class, dataProvider = "insertRest", description = "Howzat Create Match And League Series API With Invalid Team A - Negative Workflow Test",priority = 2)
	public void CreateMatchAndLeagueWithInvalidTeamANegativeTest(HashMap<String, String> table) throws Exception {

		cal = Calendar.getInstance();
		String StartDate = dateFormat.format(cal.getTime());
		String Bjson = "";
		String GameType = RP.loadPropertiesFile(ConfigFilePath).getProperty("GAMETYPE");
		String PartialRequestURL = null;

		if (GameType.equalsIgnoreCase("CRICKET")) {
			PartialRequestURL = "?name=" + RP.loadPropertiesFile(MatchAndLeagueCreationFilePath).get("NAME")
					+ "&sportType=" + RP.loadPropertiesFile(MatchAndLeagueCreationFilePath).get("CRICKETSPORTTYPE")
					+ "&sportFormat="
					+ RP.loadPropertiesFile(MatchAndLeagueCreationFilePath).get("CRICKETT20SPORTFORMATTYPE") + "&teamA="
					+ RP.loadPropertiesFile(MatchAndLeagueCreationFilePath).get("INVALIDARGUMENT") + "&teamB="
					+ RP.loadPropertiesFile(MatchAndLeagueCreationFilePath).get("TEAMB") + "&seriesId="
					+ RP.loadPropertiesFile(SeriesCreationFilePath).get("DOMESTICSERIESID") + "&startTime=" + StartDate;
		}

		if (GameType.equalsIgnoreCase("FOOTBALL")) {
			PartialRequestURL = "?name=" + RP.loadPropertiesFile(MatchAndLeagueCreationFilePath).get("NAME")
					+ "&sportType=" + RP.loadPropertiesFile(MatchAndLeagueCreationFilePath).get("FOOTBALLSPORTTYPE")
					+ "&sportFormat="
					+ RP.loadPropertiesFile(MatchAndLeagueCreationFilePath).get("FOOTBALLSPORTFORMATTYPE") + "&teamA="
					+ RP.loadPropertiesFile(MatchAndLeagueCreationFilePath).get("INVALIDARGUMENT") + "&teamB="
					+ RP.loadPropertiesFile(MatchAndLeagueCreationFilePath).get("TEAMB") + "&seriesId="
					+ RP.loadPropertiesFile(SeriesCreationFilePath).get("DOMESTICSERIESID") + "&startTime=" + StartDate;
		}

		if (GameType.equalsIgnoreCase("KABADDI")) {
			PartialRequestURL = "?name=" + RP.loadPropertiesFile(MatchAndLeagueCreationFilePath).get("NAME")
					+ "&sportType=" + RP.loadPropertiesFile(MatchAndLeagueCreationFilePath).get("KABADDISPORTTYPE")
					+ "&sportFormat="
					+ RP.loadPropertiesFile(MatchAndLeagueCreationFilePath).get("KABADDISPORTFORMATTYPE") + "&teamA="
					+ RP.loadPropertiesFile(MatchAndLeagueCreationFilePath).get("INVALIDARGUMENT") + "&teamB="
					+ RP.loadPropertiesFile(MatchAndLeagueCreationFilePath).get("TEAMB") + "&seriesId="
					+ RP.loadPropertiesFile(SeriesCreationFilePath).get("DOMESTICSERIESID") + "&startTime=" + StartDate;
		}

		given().log().all().header("Content-Type", table.get("Hcontent-Type")).body(Bjson).when()
				.post("/ls/api/leagueservice/league/createMatchAndLeague" + PartialRequestURL).then().log().all().and()
				.assertThat().statusCode(Integer.parseInt(table.get("TStatusCode")));

		String Request = AllDrive.getWriter().toString().split("HTTP/1.1")[0];
		// String Response = "HTTP/1.1 " +
		// AllDrive.getWriter().toString().split("HTTP/1.1")[1];
		String Response = "HTTP/1.1 " + AllDrive.getWriter().toString().split("HTTP/1.1");
		ExtentTestManager.getTest().log(LogStatus.INFO, "REQUEST", Request);
		System.err.println(AllDrive.getWriter().toString());
		ExtentTestManager.getTest().log(LogStatus.INFO, "RESPONSE", Response);

	}
*/
	/*@Test(dataProviderClass = Provider.class, dataProvider = "insertRest", description = "Howzat Create Match And League Series API Without Team B - Negative Workflow Test",priority = 2)
	public void CreateMatchAndLeagueWithoutTeamBNegativeTest(HashMap<String, String> table) throws Exception {

		cal = Calendar.getInstance();
		String StartDate = dateFormat.format(cal.getTime());
		String Bjson = "";
		String GameType = RP.loadPropertiesFile(ConfigFilePath).getProperty("GAMETYPE");
		String PartialRequestURL = null;

		if (GameType.equalsIgnoreCase("CRICKET")) {
			PartialRequestURL = "?name=" + RP.loadPropertiesFile(MatchAndLeagueCreationFilePath).get("NAME")
					+ "&sportType=" + RP.loadPropertiesFile(MatchAndLeagueCreationFilePath).get("CRICKETSPORTTYPE")
					+ "&sportFormat="
					+ RP.loadPropertiesFile(MatchAndLeagueCreationFilePath).get("CRICKETT20SPORTFORMATTYPE") + "&teamA="
					+ RP.loadPropertiesFile(MatchAndLeagueCreationFilePath).get("TEAMA") + "&teamB=" + "&seriesId="
					+ RP.loadPropertiesFile(SeriesCreationFilePath).get("DOMESTICSERIESID") + "&startTime=" + StartDate;
		}

		if (GameType.equalsIgnoreCase("FOOTBALL")) {
			PartialRequestURL = "?name=" + RP.loadPropertiesFile(MatchAndLeagueCreationFilePath).get("NAME")
					+ "&sportType=" + RP.loadPropertiesFile(MatchAndLeagueCreationFilePath).get("FOOTBALLSPORTTYPE")
					+ "&sportFormat="
					+ RP.loadPropertiesFile(MatchAndLeagueCreationFilePath).get("FOOTBALLSPORTFORMATTYPE") + "&teamA="
					+ RP.loadPropertiesFile(MatchAndLeagueCreationFilePath).get("TEAMA") + "&teamB=" + "&seriesId="
					+ RP.loadPropertiesFile(SeriesCreationFilePath).get("DOMESTICSERIESID") + "&startTime=" + StartDate;
		}

		if (GameType.equalsIgnoreCase("KABADDI")) {
			PartialRequestURL = "?name=" + RP.loadPropertiesFile(MatchAndLeagueCreationFilePath).get("NAME")
					+ "&sportType=" + RP.loadPropertiesFile(MatchAndLeagueCreationFilePath).get("KABADDISPORTTYPE")
					+ "&sportFormat="
					+ RP.loadPropertiesFile(MatchAndLeagueCreationFilePath).get("KABADDISPORTFORMATTYPE") + "&teamA="
					+ RP.loadPropertiesFile(MatchAndLeagueCreationFilePath).get("TEAMA") + "&teamB=" + "&seriesId="
					+ RP.loadPropertiesFile(SeriesCreationFilePath).get("DOMESTICSERIESID") + "&startTime=" + StartDate;
		}

		given().log().all().header("Content-Type", table.get("Hcontent-Type")).body(Bjson).when()
				.post("/ls/api/leagueservice/league/createMatchAndLeague" + PartialRequestURL).then().log().all().and()
				.assertThat().statusCode(Integer.parseInt(table.get("TStatusCode")));

		String Request = AllDrive.getWriter().toString().split("HTTP/1.1")[0];
		// String Response = "HTTP/1.1 " +
		// AllDrive.getWriter().toString().split("HTTP/1.1")[1];
		String Response = "HTTP/1.1 " + AllDrive.getWriter().toString().split("HTTP/1.1");
		ExtentTestManager.getTest().log(LogStatus.INFO, "REQUEST", Request);
		System.err.println(AllDrive.getWriter().toString());
		ExtentTestManager.getTest().log(LogStatus.INFO, "RESPONSE", Response);

	}
*/
	/*@Test(dataProviderClass = Provider.class, dataProvider = "insertRest", description = "Howzat Create Match And League Series API With Invalid Team B - Negative Workflow Test",priority = 2)
	public void CreateMatchAndLeagueWithInvalidTeamBNegativeTest(HashMap<String, String> table) throws Exception {

		cal = Calendar.getInstance();
		String StartDate = dateFormat.format(cal.getTime());
		String Bjson = "";
		String GameType = RP.loadPropertiesFile(ConfigFilePath).getProperty("GAMETYPE");
		String PartialRequestURL = null;

		if (GameType.equalsIgnoreCase("CRICKET")) {
			PartialRequestURL = "?name=" + RP.loadPropertiesFile(MatchAndLeagueCreationFilePath).get("NAME")
					+ "&sportType=" + RP.loadPropertiesFile(MatchAndLeagueCreationFilePath).get("CRICKETSPORTTYPE")
					+ "&sportFormat="
					+ RP.loadPropertiesFile(MatchAndLeagueCreationFilePath).get("CRICKETT20SPORTFORMATTYPE") + "&teamA="
					+ RP.loadPropertiesFile(MatchAndLeagueCreationFilePath).get("TEAMA") + "&teamB="
					+ RP.loadPropertiesFile(MatchAndLeagueCreationFilePath).get("INVALIDARGUMENT") + "&seriesId="
					+ RP.loadPropertiesFile(SeriesCreationFilePath).get("DOMESTICSERIESID") + "&startTime=" + StartDate;
		}

		if (GameType.equalsIgnoreCase("FOOTBALL")) {
			PartialRequestURL = "?name=" + RP.loadPropertiesFile(MatchAndLeagueCreationFilePath).get("NAME")
					+ "&sportType=" + RP.loadPropertiesFile(MatchAndLeagueCreationFilePath).get("FOOTBALLSPORTTYPE")
					+ "&sportFormat="
					+ RP.loadPropertiesFile(MatchAndLeagueCreationFilePath).get("FOOTBALLSPORTFORMATTYPE") + "&teamA="
					+ RP.loadPropertiesFile(MatchAndLeagueCreationFilePath).get("TEAMA") + "&teamB="
					+ RP.loadPropertiesFile(MatchAndLeagueCreationFilePath).get("INVALIDARGUMENT") + "&seriesId="
					+ RP.loadPropertiesFile(SeriesCreationFilePath).get("DOMESTICSERIESID") + "&startTime=" + StartDate;
		}

		if (GameType.equalsIgnoreCase("KABADDI")) {
			PartialRequestURL = "?name=" + RP.loadPropertiesFile(MatchAndLeagueCreationFilePath).get("NAME")
					+ "&sportType=" + RP.loadPropertiesFile(MatchAndLeagueCreationFilePath).get("KABADDISPORTTYPE")
					+ "&sportFormat="
					+ RP.loadPropertiesFile(MatchAndLeagueCreationFilePath).get("KABADDISPORTFORMATTYPE") + "&teamA="
					+ RP.loadPropertiesFile(MatchAndLeagueCreationFilePath).get("TEAMA") + "&teamB="
					+ RP.loadPropertiesFile(MatchAndLeagueCreationFilePath).get("INVALIDARGUMENT") + "&seriesId="
					+ RP.loadPropertiesFile(SeriesCreationFilePath).get("DOMESTICSERIESID") + "&startTime=" + StartDate;
		}

		given().log().all().header("Content-Type", table.get("Hcontent-Type")).body(Bjson).when()
				.post("/ls/api/leagueservice/league/createMatchAndLeague" + PartialRequestURL).then().log().all().and()
				.assertThat().statusCode(Integer.parseInt(table.get("TStatusCode")));

		String Request = AllDrive.getWriter().toString().split("HTTP/1.1")[0];
		// String Response = "HTTP/1.1 " +
		// AllDrive.getWriter().toString().split("HTTP/1.1")[1];
		String Response = "HTTP/1.1 " + AllDrive.getWriter().toString().split("HTTP/1.1");
		ExtentTestManager.getTest().log(LogStatus.INFO, "REQUEST", Request);
		System.err.println(AllDrive.getWriter().toString());
		ExtentTestManager.getTest().log(LogStatus.INFO, "RESPONSE", Response);

	}
*/
	/*@Test(dataProviderClass = Provider.class, dataProvider = "insertRest", description = "Howzat Create Match And League Series API Without Series ID - Negative Workflow Test",priority = 2)
	public void CreateMatchAndLeagueWithoutSeriesIDNegativeTest(HashMap<String, String> table) throws Exception {

		cal = Calendar.getInstance();
		String StartDate = dateFormat.format(cal.getTime());
		String Bjson = "";
		String GameType = RP.loadPropertiesFile(ConfigFilePath).getProperty("GAMETYPE");
		String PartialRequestURL = null;

		if (GameType.equalsIgnoreCase("CRICKET")) {
			PartialRequestURL = "?name=" + RP.loadPropertiesFile(MatchAndLeagueCreationFilePath).get("NAME")
					+ "&sportType=" + RP.loadPropertiesFile(MatchAndLeagueCreationFilePath).get("CRICKETSPORTTYPE")
					+ "&sportFormat="
					+ RP.loadPropertiesFile(MatchAndLeagueCreationFilePath).get("CRICKETT20SPORTFORMATTYPE") + "&teamA="
					+ RP.loadPropertiesFile(MatchAndLeagueCreationFilePath).get("TEAMA") + "&teamB="
					+ RP.loadPropertiesFile(MatchAndLeagueCreationFilePath).get("TEAMB") + "&seriesId=" + "&startTime="
					+ StartDate;
		}

		if (GameType.equalsIgnoreCase("FOOTBALL")) {
			PartialRequestURL = "?name=" + RP.loadPropertiesFile(MatchAndLeagueCreationFilePath).get("NAME")
					+ "&sportType=" + RP.loadPropertiesFile(MatchAndLeagueCreationFilePath).get("FOOTBALLSPORTTYPE")
					+ "&sportFormat="
					+ RP.loadPropertiesFile(MatchAndLeagueCreationFilePath).get("FOOTBALLSPORTFORMATTYPE") + "&teamA="
					+ RP.loadPropertiesFile(MatchAndLeagueCreationFilePath).get("TEAMA") + "&teamB="
					+ RP.loadPropertiesFile(MatchAndLeagueCreationFilePath).get("TEAMB") + "&seriesId=" + "&startTime="
					+ StartDate;
		}

		if (GameType.equalsIgnoreCase("KABADDI")) {
			PartialRequestURL = "?name=" + RP.loadPropertiesFile(MatchAndLeagueCreationFilePath).get("NAME")
					+ "&sportType=" + RP.loadPropertiesFile(MatchAndLeagueCreationFilePath).get("KABADDISPORTTYPE")
					+ "&sportFormat="
					+ RP.loadPropertiesFile(MatchAndLeagueCreationFilePath).get("KABADDISPORTFORMATTYPE") + "&teamA="
					+ RP.loadPropertiesFile(MatchAndLeagueCreationFilePath).get("TEAMA") + "&teamB="
					+ RP.loadPropertiesFile(MatchAndLeagueCreationFilePath).get("TEAMB") + "&seriesId=" + "&startTime="
					+ StartDate;
		}

		given().log().all().header("Content-Type", table.get("Hcontent-Type")).body(Bjson).when()
				.post("/ls/api/leagueservice/league/createMatchAndLeague" + PartialRequestURL).then().log().all().and()
				.assertThat().statusCode(Integer.parseInt(table.get("TStatusCode")));

		String Request = AllDrive.getWriter().toString().split("HTTP/1.1")[0];
		// String Response = "HTTP/1.1 " +
		// AllDrive.getWriter().toString().split("HTTP/1.1")[1];
		String Response = "HTTP/1.1 " + AllDrive.getWriter().toString().split("HTTP/1.1");
		ExtentTestManager.getTest().log(LogStatus.INFO, "REQUEST", Request);
		System.err.println(AllDrive.getWriter().toString());
		ExtentTestManager.getTest().log(LogStatus.INFO, "RESPONSE", Response);

	}
*/
	/*@Test(dataProviderClass = Provider.class, dataProvider = "insertRest", description = "Howzat Create Match And League Series API With Invalid Series ID - Negative Workflow Test",priority = 2)
	public void CreateMatchAndLeagueWithInvalidSeriesIDNegativeTest(HashMap<String, String> table) throws Exception {

		cal = Calendar.getInstance();
		String StartDate = dateFormat.format(cal.getTime());
		String Bjson = "";
		String GameType = RP.loadPropertiesFile(ConfigFilePath).getProperty("GAMETYPE");
		String PartialRequestURL = null;

		if (GameType.equalsIgnoreCase("CRICKET")) {
			PartialRequestURL = "?name=" + RP.loadPropertiesFile(MatchAndLeagueCreationFilePath).get("NAME")
					+ "&sportType=" + RP.loadPropertiesFile(MatchAndLeagueCreationFilePath).get("CRICKETSPORTTYPE")
					+ "&sportFormat="
					+ RP.loadPropertiesFile(MatchAndLeagueCreationFilePath).get("CRICKETT20SPORTFORMATTYPE") + "&teamA="
					+ RP.loadPropertiesFile(MatchAndLeagueCreationFilePath).get("TEAMA") + "&teamB="
					+ RP.loadPropertiesFile(MatchAndLeagueCreationFilePath).get("TEAMB") + "&seriesId="
					+ RP.loadPropertiesFile(MatchAndLeagueCreationFilePath).get("INVALIDARGUMENT") + "&startTime=" + StartDate;
		}

		if (GameType.equalsIgnoreCase("FOOTBALL")) {
			PartialRequestURL = "?name=" + RP.loadPropertiesFile(MatchAndLeagueCreationFilePath).get("NAME")
					+ "&sportType=" + RP.loadPropertiesFile(MatchAndLeagueCreationFilePath).get("FOOTBALLSPORTTYPE")
					+ "&sportFormat="
					+ RP.loadPropertiesFile(MatchAndLeagueCreationFilePath).get("FOOTBALLSPORTFORMATTYPE") + "&teamA="
					+ RP.loadPropertiesFile(MatchAndLeagueCreationFilePath).get("TEAMA") + "&teamB="
					+ RP.loadPropertiesFile(MatchAndLeagueCreationFilePath).get("TEAMB") + "&seriesId="
					+ RP.loadPropertiesFile(MatchAndLeagueCreationFilePath).get("INVALIDARGUMENT") + "&startTime=" + StartDate;
		}

		if (GameType.equalsIgnoreCase("KABADDI")) {
			PartialRequestURL = "?name=" + RP.loadPropertiesFile(MatchAndLeagueCreationFilePath).get("NAME")
					+ "&sportType=" + RP.loadPropertiesFile(MatchAndLeagueCreationFilePath).get("KABADDISPORTTYPE")
					+ "&sportFormat="
					+ RP.loadPropertiesFile(MatchAndLeagueCreationFilePath).get("KABADDISPORTFORMATTYPE") + "&teamA="
					+ RP.loadPropertiesFile(MatchAndLeagueCreationFilePath).get("TEAMA") + "&teamB="
					+ RP.loadPropertiesFile(MatchAndLeagueCreationFilePath).get("TEAMB") + "&seriesId="
					+ RP.loadPropertiesFile(MatchAndLeagueCreationFilePath).get("INVALIDARGUMENT") + "&startTime=" + StartDate;
		}

		given().log().all().header("Content-Type", table.get("Hcontent-Type")).body(Bjson).when()
				.post("/ls/api/leagueservice/league/createMatchAndLeague" + PartialRequestURL).then().log().all().and()
				.assertThat().statusCode(Integer.parseInt(table.get("TStatusCode")));

		String Request = AllDrive.getWriter().toString().split("HTTP/1.1")[0];
		// String Response = "HTTP/1.1 " +
		// AllDrive.getWriter().toString().split("HTTP/1.1")[1];
		String Response = "HTTP/1.1 " + AllDrive.getWriter().toString().split("HTTP/1.1");
		ExtentTestManager.getTest().log(LogStatus.INFO, "REQUEST", Request);
		System.err.println(AllDrive.getWriter().toString());
		ExtentTestManager.getTest().log(LogStatus.INFO, "RESPONSE", Response);

	}
*/
	/*@Test(dataProviderClass = Provider.class, dataProvider = "insertRest", description = "Howzat Create Match And League Series API Without Name - Negative Workflow Test",priority = 2)
	public void CreateMatchAndLeagueWithoutNameNegativeTest(HashMap<String, String> table) throws Exception {

		cal = Calendar.getInstance();
		String StartDate = dateFormat.format(cal.getTime());
		String Bjson = "";
		String GameType = RP.loadPropertiesFile(ConfigFilePath).getProperty("GAMETYPE");
		String PartialRequestURL = null;

		if (GameType.equalsIgnoreCase("CRICKET")) {
			PartialRequestURL = "?name=" + "&sportType="
					+ RP.loadPropertiesFile(MatchAndLeagueCreationFilePath).get("CRICKETSPORTTYPE") + "&sportFormat="
					+ RP.loadPropertiesFile(MatchAndLeagueCreationFilePath).get("CRICKETT20SPORTFORMATTYPE") + "&teamA="
					+ RP.loadPropertiesFile(MatchAndLeagueCreationFilePath).get("TEAMA") + "&teamB="
					+ RP.loadPropertiesFile(MatchAndLeagueCreationFilePath).get("TEAMB") + "&seriesId="
					+ RP.loadPropertiesFile(SeriesCreationFilePath).get("DOMESTICSERIESID") + "&startTime=" + StartDate;
		}

		if (GameType.equalsIgnoreCase("FOOTBALL")) {
			PartialRequestURL = "?name=" + "&sportType="
					+ RP.loadPropertiesFile(MatchAndLeagueCreationFilePath).get("FOOTBALLSPORTTYPE") + "&sportFormat="
					+ RP.loadPropertiesFile(MatchAndLeagueCreationFilePath).get("FOOTBALLSPORTFORMATTYPE") + "&teamA="
					+ RP.loadPropertiesFile(MatchAndLeagueCreationFilePath).get("TEAMA") + "&teamB="
					+ RP.loadPropertiesFile(MatchAndLeagueCreationFilePath).get("TEAMB") + "&seriesId="
					+ RP.loadPropertiesFile(SeriesCreationFilePath).get("DOMESTICSERIESID") + "&startTime=" + StartDate;
		}

		if (GameType.equalsIgnoreCase("KABADDI")) {
			PartialRequestURL = "?name=" + "&sportType="
					+ RP.loadPropertiesFile(MatchAndLeagueCreationFilePath).get("KABADDISPORTTYPE") + "&sportFormat="
					+ RP.loadPropertiesFile(MatchAndLeagueCreationFilePath).get("KABADDISPORTFORMATTYPE") + "&teamA="
					+ RP.loadPropertiesFile(MatchAndLeagueCreationFilePath).get("TEAMA") + "&teamB="
					+ RP.loadPropertiesFile(MatchAndLeagueCreationFilePath).get("TEAMB") + "&seriesId="
					+ RP.loadPropertiesFile(SeriesCreationFilePath).get("DOMESTICSERIESID") + "&startTime=" + StartDate;
		}

		given().log().all().header("Content-Type", table.get("Hcontent-Type")).body(Bjson).when()
				.post("/ls/api/leagueservice/league/createMatchAndLeague" + PartialRequestURL).then().log().all().and()
				.assertThat().statusCode(Integer.parseInt(table.get("TStatusCode")));

		String Request = AllDrive.getWriter().toString().split("HTTP/1.1")[0];
		// String Response = "HTTP/1.1 " +
		// AllDrive.getWriter().toString().split("HTTP/1.1")[1];
		String Response = "HTTP/1.1 " + AllDrive.getWriter().toString().split("HTTP/1.1");
		ExtentTestManager.getTest().log(LogStatus.INFO, "REQUEST", Request);
		System.err.println(AllDrive.getWriter().toString());
		ExtentTestManager.getTest().log(LogStatus.INFO, "RESPONSE", Response);

	}
*/
	/*@Test(dataProviderClass = Provider.class, dataProvider = "insertRest", description = "Howzat Create Match And League Series API With Start Date < Current Date - Negative Workflow Test",priority = 2)
	public void CreateMatchAndLeagueWithStartDateLessThanCurrentDateNegativeTest(HashMap<String, String> table)
			throws Exception {

		cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, -2);
		String StartDate = dateFormat.format(cal.getTime());
		String Bjson = "";
		String GameType = RP.loadPropertiesFile(ConfigFilePath).getProperty("GAMETYPE");
		String PartialRequestURL = null;

		if (GameType.equalsIgnoreCase("CRICKET")) {
			PartialRequestURL = "?name=" + RP.loadPropertiesFile(MatchAndLeagueCreationFilePath).get("NAME")
					+ "&sportType=" + RP.loadPropertiesFile(MatchAndLeagueCreationFilePath).get("CRICKETSPORTTYPE")
					+ "&sportFormat="
					+ RP.loadPropertiesFile(MatchAndLeagueCreationFilePath).get("CRICKETT20SPORTFORMATTYPE") + "&teamA="
					+ RP.loadPropertiesFile(MatchAndLeagueCreationFilePath).get("TEAMA") + "&teamB="
					+ RP.loadPropertiesFile(MatchAndLeagueCreationFilePath).get("TEAMB") + "&seriesId="
					+ RP.loadPropertiesFile(SeriesCreationFilePath).get("DOMESTICSERIESID") + "&startTime=" + StartDate;
		}

		if (GameType.equalsIgnoreCase("FOOTBALL")) {
			PartialRequestURL = "?name=" + RP.loadPropertiesFile(MatchAndLeagueCreationFilePath).get("NAME")
					+ "&sportType=" + RP.loadPropertiesFile(MatchAndLeagueCreationFilePath).get("FOOTBALLSPORTTYPE")
					+ "&sportFormat="
					+ RP.loadPropertiesFile(MatchAndLeagueCreationFilePath).get("FOOTBALLSPORTFORMATTYPE") + "&teamA="
					+ RP.loadPropertiesFile(MatchAndLeagueCreationFilePath).get("TEAMA") + "&teamB="
					+ RP.loadPropertiesFile(MatchAndLeagueCreationFilePath).get("TEAMB") + "&seriesId="
					+ RP.loadPropertiesFile(SeriesCreationFilePath).get("DOMESTICSERIESID") + "&startTime=" + StartDate;
		}

		if (GameType.equalsIgnoreCase("KABADDI")) {
			PartialRequestURL = "?name=" + RP.loadPropertiesFile(MatchAndLeagueCreationFilePath).get("NAME")
					+ "&sportType=" + RP.loadPropertiesFile(MatchAndLeagueCreationFilePath).get("KABADDISPORTTYPE")
					+ "&sportFormat="
					+ RP.loadPropertiesFile(MatchAndLeagueCreationFilePath).get("KABADDISPORTFORMATTYPE") + "&teamA="
					+ RP.loadPropertiesFile(MatchAndLeagueCreationFilePath).get("TEAMA") + "&teamB="
					+ RP.loadPropertiesFile(MatchAndLeagueCreationFilePath).get("TEAMB") + "&seriesId="
					+ RP.loadPropertiesFile(SeriesCreationFilePath).get("DOMESTICSERIESID") + "&startTime=" + StartDate;
		}

		Response JsonResponse = given().log().all().header("Content-Type", table.get("Hcontent-Type")).body(Bjson)
				.when().post("/ls/api/leagueservice/league/createMatchAndLeague" + PartialRequestURL).then().extract()
				.response();

		int StatusCode = JsonResponse.getStatusCode();

		JSONObject jobj = new JSONObject(JsonResponse.asString());
		System.out.println(JsonResponse.asString());
//		WP.WritePropertiesFile(MatchAndLeagueCreationFilePath, GameType + "INNINGS1ID",
//				jobj.get("innings2").toString());
//		WP.WritePropertiesFile(MatchAndLeagueCreationFilePath, GameType + "LEAGUEID", jobj.get("leagueId").toString());
//		WP.WritePropertiesFile(MatchAndLeagueCreationFilePath, GameType + "MATCHID", jobj.get("matchId").toString());
//		WP.WritePropertiesFile(MatchAndLeagueCreationFilePath, GameType + "INNINGS2ID",
//				jobj.get("innings1").toString());

		Assert.assertEquals(StatusCode, Integer.parseInt(table.get("TStatusCode")));

		String Request = AllDrive.getWriter().toString().split("HTTP/1.1")[0];
		// String Response = "HTTP/1.1 " +
		// AllDrive.getWriter().toString().split("HTTP/1.1")[1];
		String Response = "HTTP/1.1 " + AllDrive.getWriter().toString().split("HTTP/1.1");
		ExtentTestManager.getTest().log(LogStatus.INFO, "REQUEST", Request);
		System.err.println(AllDrive.getWriter().toString());
		ExtentTestManager.getTest().log(LogStatus.INFO, "RESPONSE", Response);

	}
*/
}