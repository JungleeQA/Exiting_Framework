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

public class HowzatLeagueVisibilityAPI extends Base {
	ReadPropertiesFiles RP = new ReadPropertiesFiles();
	WritePropertiesFiles WP = new WritePropertiesFiles();

	String ConfigFilePath = "../APIAutoationFramework/src/test/resources/properties/Config.properties";
	String MatchAndLeagueCreationFilePath = RP.loadPropertiesFile(ConfigFilePath)
			.getProperty("MATCHANDLEAGUECREATIONFILEPATH");
	String GameType = RP.loadPropertiesFile(ConfigFilePath).getProperty("GAMETYPE");

	@Test(dataProviderClass = Provider.class, dataProvider = "insertRest", description = "Howzat League Visibilty To True API - Positive Workflow Test")
	public void LeagueVisibilityTruePositiveTest(HashMap<String, String> table) throws Exception {

		String Bjson = "";
		String PartialRequestURL = null;

		if (GameType.equalsIgnoreCase("CRICKET")) {
			PartialRequestURL = "/ls/api/leagueservice/league/"
					+ RP.loadPropertiesFile(MatchAndLeagueCreationFilePath).getProperty("CRICKETLEAGUEID")
					+ "/visibility?visibility=" + RP.loadPropertiesFile(MatchAndLeagueCreationFilePath)
							.getProperty("LEAGUEVISIBILITYTRUE").toLowerCase();
		}

		if (GameType.equalsIgnoreCase("FOOTBALL")) {
			PartialRequestURL = "ls/api/leagueservice/league/"
					+ RP.loadPropertiesFile(MatchAndLeagueCreationFilePath).getProperty("FOOTBALLLEAGUEID")
					+ "/visibility?visibility=" + RP.loadPropertiesFile(MatchAndLeagueCreationFilePath)
							.getProperty("LEAGUEVISIBILITYTRUE").toLowerCase();
		}

		if (GameType.equalsIgnoreCase("KABADDI")) {
			PartialRequestURL = "ls/api/leagueservice/league/"
					+ RP.loadPropertiesFile(MatchAndLeagueCreationFilePath).getProperty("KABADDILEAGUEID")
					+ "/visibility?visibility=" + RP.loadPropertiesFile(MatchAndLeagueCreationFilePath)
							.getProperty("LEAGUEVISIBILITYTRUE").toLowerCase();
		}

		given().log().all().header("Content-Type", table.get("Hcontent-Type")).body(Bjson).when()
				.post(PartialRequestURL).then().log().all().and().assertThat()
				.statusCode(Integer.parseInt(table.get("TStatusCode")));

		String Request = AllDrive.getWriter().toString().split("HTTP/1.1")[0];
		// String Response = "HTTP/1.1 " +
		// AllDrive.getWriter().toString().split("HTTP/1.1")[1];
		String Response = "HTTP/1.1 " + AllDrive.getWriter().toString().split("HTTP/1.1");
		ExtentTestManager.getTest().log(LogStatus.INFO, "REQUEST", Request);
		System.err.println(AllDrive.getWriter().toString());
		ExtentTestManager.getTest().log(LogStatus.INFO, "RESPONSE", Response);

	}

	@Test(dataProviderClass = Provider.class, dataProvider = "insertRest", description = "Howzat League Visibilty To False API - Positive Workflow Test")
	public void LeagueVisibilityFalsePositiveTest(HashMap<String, String> table) throws Exception {

		String Bjson = "";
		String PartialRequestURL = null;

		if (GameType.equalsIgnoreCase("CRICKET")) {
			PartialRequestURL = "/ls/api/leagueservice/league/"
					+ RP.loadPropertiesFile(MatchAndLeagueCreationFilePath).getProperty("CRICKETLEAGUEID")
					+ "/visibility?visibility=" + RP.loadPropertiesFile(MatchAndLeagueCreationFilePath)
							.getProperty("LEAGUEVISIBILITYFALSE").toLowerCase();
		}

		if (GameType.equalsIgnoreCase("FOOTBALL")) {
			PartialRequestURL = "ls/api/leagueservice/league/"
					+ RP.loadPropertiesFile(MatchAndLeagueCreationFilePath).getProperty("FOOTBALLLEAGUEID")
					+ "/visibility?visibility=" + RP.loadPropertiesFile(MatchAndLeagueCreationFilePath)
							.getProperty("LEAGUEVISIBILITYFALSE").toLowerCase();
		}

		if (GameType.equalsIgnoreCase("KABADDI")) {
			PartialRequestURL = "ls/api/leagueservice/league/"
					+ RP.loadPropertiesFile(MatchAndLeagueCreationFilePath).getProperty("KABADDILEAGUEID")
					+ "/visibility?visibility=" + RP.loadPropertiesFile(MatchAndLeagueCreationFilePath)
							.getProperty("LEAGUEVISIBILITYFALSE").toLowerCase();
		}

		given().log().all().header("Content-Type", table.get("Hcontent-Type")).body(Bjson).when()
				.post(PartialRequestURL).then().log().all().and().assertThat()
				.statusCode(Integer.parseInt(table.get("TStatusCode")));

		String Request = AllDrive.getWriter().toString().split("HTTP/1.1")[0];
		// String Response = "HTTP/1.1 " +
		// AllDrive.getWriter().toString().split("HTTP/1.1")[1];
		String Response = "HTTP/1.1 " + AllDrive.getWriter().toString().split("HTTP/1.1");
		ExtentTestManager.getTest().log(LogStatus.INFO, "REQUEST", Request);
		System.err.println(AllDrive.getWriter().toString());
		ExtentTestManager.getTest().log(LogStatus.INFO, "RESPONSE", Response);

	}

	@Test(dataProviderClass = Provider.class, dataProvider = "insertRest", description = "Howzat League Visibilty By Passing Invalid League ID API - Negative Workflow Test",priority = 3)
	public void LeagueVisibilityInvalidLeagueIDNegativeTest(HashMap<String, String> table) throws Exception {

		String Bjson = "";
		String PartialRequestURL = null;

		PartialRequestURL = "/ls/api/leagueservice/league/"
				+ RP.loadPropertiesFile(MatchAndLeagueCreationFilePath).getProperty("INVALIDARGUMENT")
				+ "/visibility?visibility=" + RP.loadPropertiesFile(MatchAndLeagueCreationFilePath)
						.getProperty("LEAGUEVISIBILITYTRUE").toLowerCase();

		given().log().all().header("Content-Type", table.get("Hcontent-Type")).body(Bjson).when()
				.post(PartialRequestURL).then().log().all().and().assertThat()
				.statusCode(Integer.parseInt(table.get("TStatusCode")));

		String Request = AllDrive.getWriter().toString().split("HTTP/1.1")[0];
		// String Response = "HTTP/1.1 " +
		// AllDrive.getWriter().toString().split("HTTP/1.1")[1];
		String Response = "HTTP/1.1 " + AllDrive.getWriter().toString().split("HTTP/1.1");
		ExtentTestManager.getTest().log(LogStatus.INFO, "REQUEST", Request);
		System.err.println(AllDrive.getWriter().toString());
		ExtentTestManager.getTest().log(LogStatus.INFO, "RESPONSE", Response);

	}

	@Test(dataProviderClass = Provider.class, dataProvider = "insertRest", description = "Howzat League Visibilty By Passing Blank League ID API - Negative Workflow Test",priority = 3)
	public void LeagueVisibilityBlankLeagueIDNegativeTest(HashMap<String, String> table) throws Exception {

		String Bjson = "";
		String PartialRequestURL = null;

		PartialRequestURL = "/ls/api/leagueservice/league/" + "/visibility?visibility=" + RP
				.loadPropertiesFile(MatchAndLeagueCreationFilePath).getProperty("LEAGUEVISIBILITYTRUE").toLowerCase();

		given().log().all().header("Content-Type", table.get("Hcontent-Type")).body(Bjson).when()
				.post(PartialRequestURL).then().log().all().and().assertThat()
				.statusCode(Integer.parseInt(table.get("TStatusCode")));

		String Request = AllDrive.getWriter().toString().split("HTTP/1.1")[0];
		// String Response = "HTTP/1.1 " +
		// AllDrive.getWriter().toString().split("HTTP/1.1")[1];
		String Response = "HTTP/1.1 " + AllDrive.getWriter().toString().split("HTTP/1.1");
		ExtentTestManager.getTest().log(LogStatus.INFO, "REQUEST", Request);
		System.err.println(AllDrive.getWriter().toString());
		ExtentTestManager.getTest().log(LogStatus.INFO, "RESPONSE", Response);

	}
	
	@Test(dataProviderClass = Provider.class, dataProvider = "insertRest", description = "Howzat League Visibilty By Passing Completed Match League ID API - Negative Workflow Test",priority = 3)
	public void LeagueVisibilityCompletedMatchLeagueIDNegativeTest(HashMap<String, String> table) throws Exception {

		String Bjson = "";
		String PartialRequestURL = null;

		PartialRequestURL = "/ls/api/leagueservice/league/"
				+ RP.loadPropertiesFile(MatchAndLeagueCreationFilePath).getProperty("COMPLETEDMATCHLEAGUEID")
				+ "/visibility?visibility=" + RP.loadPropertiesFile(MatchAndLeagueCreationFilePath)
						.getProperty("LEAGUEVISIBILITYTRUE").toLowerCase();

		given().log().all().header("Content-Type", table.get("Hcontent-Type")).body(Bjson).when()
				.post(PartialRequestURL).then().log().all().and().assertThat()
				.statusCode(Integer.parseInt(table.get("TStatusCode")));

		String Request = AllDrive.getWriter().toString().split("HTTP/1.1")[0];
		// String Response = "HTTP/1.1 " +
		// AllDrive.getWriter().toString().split("HTTP/1.1")[1];
		String Response = "HTTP/1.1 " + AllDrive.getWriter().toString().split("HTTP/1.1");
		ExtentTestManager.getTest().log(LogStatus.INFO, "REQUEST", Request);
		System.err.println(AllDrive.getWriter().toString());
		ExtentTestManager.getTest().log(LogStatus.INFO, "RESPONSE", Response);

	}

}