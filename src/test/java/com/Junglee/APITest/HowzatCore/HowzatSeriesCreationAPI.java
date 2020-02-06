package com.Junglee.APITest.HowzatCore;

import static io.restassured.RestAssured.given;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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

public class HowzatSeriesCreationAPI extends Base {
	ReadPropertiesFiles RP = new ReadPropertiesFiles();
	WritePropertiesFiles WP = new WritePropertiesFiles();

	String ConfigFilePath = "../APIAutoationFramework/src/test/resources/properties/Config.properties";
	String SeriesCreationFilePath = RP.loadPropertiesFile(ConfigFilePath).getProperty("SERIESCREATIONCONFIGFILEPATH");

	DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
	// Calendar cal = Calendar.getInstance();
	Calendar cal = null;

	@Test(dataProviderClass = Provider.class, dataProvider = "insertRest", description = "Howzat Create Domestic Series API - Positive Workflow Test")
	public void CreateDomesticSeriesPositiveTest(HashMap<String, String> table) throws Exception {

		cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, 3);
		String EndDate = dateFormat.format(cal.getTime());

		cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, 1);
		String StartDate = dateFormat.format(cal.getTime());

		String Bjson = "{" + "\"" + "countryId" + "\"" + " : "
				+ RP.loadPropertiesFile(SeriesCreationFilePath).get("COUNTRYID") + "," + "\"" + "endDate" + "\"" + " : "
				+ "\"" + EndDate + "\"" + "," + "\"" + "info" + "\"" + " : " + "\""
				+ RP.loadPropertiesFile(SeriesCreationFilePath).get("INFO") + "\"" + "," + "\"" + "name" + "\"" + " : "
				+ "\"" + RP.loadPropertiesFile(SeriesCreationFilePath).get("NAME") + "\"" + "," + "\"" + "seriesType"
				+ "\"" + " : " + RP.loadPropertiesFile(SeriesCreationFilePath).get("DOMESTICSERIESTYPE") + "," + "\""
				+ "startDate" + "\"" + " : " + "\"" + StartDate + "\"" + "," + "\"" + "createdBy" + "\"" + " : "
				+ RP.loadPropertiesFile(SeriesCreationFilePath).get("CREATEDBY") + "," + "\"" + "updatedBy" + "\""
				+ " : " + RP.loadPropertiesFile(SeriesCreationFilePath).get("UPDATEDBY") + "," + "\"" + "priority"
				+ "\"" + " : " + RP.loadPropertiesFile(SeriesCreationFilePath).get("PRIORITY") + "}";

		Response JsonResponse = given().log().all().header("Content-Type", table.get("Hcontent-Type")).body(Bjson)
				.when().post("/ls/api/leagueservice/series").then().extract().response();

		int StatusCode = JsonResponse.getStatusCode();

		JSONObject jobj = new JSONObject(JsonResponse.asString());
		WP.WritePropertiesFile(SeriesCreationFilePath, "DOMESTICSERIESID", jobj.get("id").toString());

		Assert.assertEquals(StatusCode, Integer.parseInt(table.get("TStatusCode")));
		Assert.assertEquals(RP.loadPropertiesFile(SeriesCreationFilePath).get("COUNTRYID"),
				jobj.get("countryId").toString());
		Assert.assertEquals(RP.loadPropertiesFile(SeriesCreationFilePath).get("DOMESTICSERIESTYPE"),
				jobj.get("seriesType").toString());
		Assert.assertEquals(RP.loadPropertiesFile(SeriesCreationFilePath).get("CREATEDBY"),
				jobj.get("createdBy").toString());
		Assert.assertEquals(RP.loadPropertiesFile(SeriesCreationFilePath).get("UPDATEDBY"),
				jobj.get("updatedBy").toString());
		Assert.assertEquals(RP.loadPropertiesFile(SeriesCreationFilePath).get("PRIORITY"),
				jobj.get("priority").toString());
		Assert.assertEquals(RP.loadPropertiesFile(SeriesCreationFilePath).get("NAME"), jobj.get("name").toString());
		Assert.assertEquals(RP.loadPropertiesFile(SeriesCreationFilePath).get("INFO"), jobj.get("info").toString());

		String Request = AllDrive.getWriter().toString().split("HTTP/1.1")[0];
		// String Response = "HTTP/1.1 " +
		// AllDrive.getWriter().toString().split("HTTP/1.1")[1];
		String Response = "HTTP/1.1 " + AllDrive.getWriter().toString().split("HTTP/1.1");
		ExtentTestManager.getTest().log(LogStatus.INFO, "REQUEST", Request);
		System.err.println(AllDrive.getWriter().toString());
		ExtentTestManager.getTest().log(LogStatus.INFO, "RESPONSE", Response);

	}

	@Test(dataProviderClass = Provider.class, dataProvider = "insertRest", description = "Howzat Create Inernational Series API - Positive Workflow Test")
	public void CreateInternationalSeriesPositiveTest(HashMap<String, String> table) throws Exception {

		cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, 3);
		String EndDate = dateFormat.format(cal.getTime());

		cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, 1);
		String StartDate = dateFormat.format(cal.getTime());

		String Bjson = "{" + "\"" + "countryId" + "\"" + " : "
				+ RP.loadPropertiesFile(SeriesCreationFilePath).get("COUNTRYID") + "," + "\"" + "endDate" + "\"" + " : "
				+ "\"" + EndDate + "\"" + "," + "\"" + "info" + "\"" + " : " + "\""
				+ RP.loadPropertiesFile(SeriesCreationFilePath).get("INFO") + "\"" + "," + "\"" + "name" + "\"" + " : "
				+ "\"" + RP.loadPropertiesFile(SeriesCreationFilePath).get("NAME") + "\"" + "," + "\"" + "seriesType"
				+ "\"" + " : " + RP.loadPropertiesFile(SeriesCreationFilePath).get("INTERNATIONALSERIESTYPE") + ","
				+ "\"" + "startDate" + "\"" + " : " + "\"" + StartDate + "\"" + "," + "\"" + "createdBy" + "\"" + " : "
				+ RP.loadPropertiesFile(SeriesCreationFilePath).get("CREATEDBY") + "," + "\"" + "updatedBy" + "\""
				+ " : " + RP.loadPropertiesFile(SeriesCreationFilePath).get("UPDATEDBY") + "," + "\"" + "priority"
				+ "\"" + " : " + RP.loadPropertiesFile(SeriesCreationFilePath).get("PRIORITY") + "}";

		Response JsonResponse = given().log().all().header("Content-Type", table.get("Hcontent-Type")).body(Bjson)
				.when().post("/ls/api/leagueservice/series").then().extract().response();

		int StatusCode = JsonResponse.getStatusCode();

		JSONObject jobj = new JSONObject(JsonResponse.asString());
		WP.WritePropertiesFile(SeriesCreationFilePath, "INTERNATIONALSERIESID", jobj.get("id").toString());

		Assert.assertEquals(StatusCode, Integer.parseInt(table.get("TStatusCode")));
		Assert.assertEquals(RP.loadPropertiesFile(SeriesCreationFilePath).get("COUNTRYID"),
				jobj.get("countryId").toString());
		Assert.assertEquals(RP.loadPropertiesFile(SeriesCreationFilePath).get("INTERNATIONALSERIESTYPE"),
				jobj.get("seriesType").toString());
		Assert.assertEquals(RP.loadPropertiesFile(SeriesCreationFilePath).get("CREATEDBY"),
				jobj.get("createdBy").toString());
		Assert.assertEquals(RP.loadPropertiesFile(SeriesCreationFilePath).get("UPDATEDBY"),
				jobj.get("updatedBy").toString());
		Assert.assertEquals(RP.loadPropertiesFile(SeriesCreationFilePath).get("PRIORITY"),
				jobj.get("priority").toString());
		Assert.assertEquals(RP.loadPropertiesFile(SeriesCreationFilePath).get("NAME"), jobj.get("name").toString());
		Assert.assertEquals(RP.loadPropertiesFile(SeriesCreationFilePath).get("INFO"), jobj.get("info").toString());

		String Request = AllDrive.getWriter().toString().split("HTTP/1.1")[0];
		// String Response = "HTTP/1.1 " +
		// AllDrive.getWriter().toString().split("HTTP/1.1")[1];
		String Response = "HTTP/1.1 " + AllDrive.getWriter().toString().split("HTTP/1.1");
		ExtentTestManager.getTest().log(LogStatus.INFO, "REQUEST", Request);
		System.err.println(AllDrive.getWriter().toString());
		ExtentTestManager.getTest().log(LogStatus.INFO, "RESPONSE", Response);

	}

	/*@Test(dataProviderClass = Provider.class, dataProvider = "insertRest", description = "Howzat Create Series with Invalid Dataset API - Negative Workflow Test",priority = 1)
	public void CreateSeriesWithInvalidDatasetNegativeTest(HashMap<String, String> table) throws Exception {

		cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, 2);
		String EndDate = dateFormat.format(cal.getTime());

		String Bjson = "{" + "\"" + "countryId" + "\"" + " : "
				+ RP.loadPropertiesFile(SeriesCreationFilePath).get("INVALIDCOUNTRYID") + "," + "\"" + "endDate" + "\""
				+ " : " + "\"" + EndDate + "\"" + "," + "\"" + "info" + "\"" + " : " + "\""
				+ RP.loadPropertiesFile(SeriesCreationFilePath).get("INFO") + "\"" + "," + "\"" + "name" + "\"" + " : "
				+ "\"" + RP.loadPropertiesFile(SeriesCreationFilePath).get("NAME") + "\"" + "," + "\"" + "seriesType"
				+ "\"" + " : " + RP.loadPropertiesFile(SeriesCreationFilePath).get("INVALIDSERIESTYPE") + "," + "\""
				+ "startDate" + "\"" + " : " + "\""
				+ RP.loadPropertiesFile(SeriesCreationFilePath).get("INVALIDSTARTDATE") + "\"" + "," + "\""
				+ "createdBy" + "\"" + " : " + RP.loadPropertiesFile(SeriesCreationFilePath).get("INVALIDCREATEDBY")
				+ "," + "\"" + "updatedBy" + "\"" + " : "
				+ RP.loadPropertiesFile(SeriesCreationFilePath).get("INVALIDUPDATEDBY") + "," + "\"" + "priority" + "\""
				+ " : " + RP.loadPropertiesFile(SeriesCreationFilePath).get("PRIORITY") + "}";

		System.out.println(Bjson);
		Response JsonResponse = given().log().all().header("Content-Type", table.get("Hcontent-Type")).body(Bjson)
				.when().post("/ls/api/leagueservice/series").then().extract().response();

		int StatusCode = JsonResponse.getStatusCode();
		JSONObject jobj = new JSONObject(JsonResponse.asString());

		if (StatusCode != (Integer.parseInt(table.get("TStatusCode")))) {

			WP.WritePropertiesFile(SeriesCreationFilePath, "INVALIDSERIESID", jobj.get("id").toString());

			Assert.assertEquals(StatusCode, Integer.parseInt(table.get("TStatusCode")));
		}

		String Request = AllDrive.getWriter().toString().split("HTTP/1.1")[0];
//		String Response = "HTTP/1.1 " +
	//	 AllDrive.getWriter().toString().split("HTTP/1.1")[1];
		String Response = "HTTP/1.1 " + AllDrive.getWriter().toString().split("HTTP/1.1");
		ExtentTestManager.getTest().log(LogStatus.INFO, "REQUEST", Request);
		System.err.println(AllDrive.getWriter().toString());
		ExtentTestManager.getTest().log(LogStatus.INFO, "RESPONSE", Response);

	}
*/
	@Test(dataProviderClass = Provider.class, dataProvider = "insertRest", description = "Howzat Create Series When Start Date > End Date API - Negative Workflow Test",priority = 1)
	public void CreateSeriesStartDateGreaterThanEndDateNegativeTest(HashMap<String, String> table) throws Exception {

		cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, 2);
		String StartDate = dateFormat.format(cal.getTime());

		cal = Calendar.getInstance();
		String EndDate = dateFormat.format(cal.getTime());

		String Bjson = "{" + "\"" + "countryId" + "\"" + " : "
				+ RP.loadPropertiesFile(SeriesCreationFilePath).get("COUNTRYID") + "," + "\"" + "endDate" + "\"" + " : "
				+ "\"" + EndDate + "\"" + "," + "\"" + "info" + "\"" + " : " + "\""
				+ RP.loadPropertiesFile(SeriesCreationFilePath).get("INFO") + "\"" + "," + "\"" + "name" + "\"" + " : "
				+ "\"" + RP.loadPropertiesFile(SeriesCreationFilePath).get("NAME") + "\"" + "," + "\"" + "seriesType"
				+ "\"" + " : " + RP.loadPropertiesFile(SeriesCreationFilePath).get("INTERNATIONALSERIESTYPE") + ","
				+ "\"" + "startDate" + "\"" + " : " + "\"" + StartDate + "\"" + "," + "\"" + "createdBy" + "\"" + " : "
				+ RP.loadPropertiesFile(SeriesCreationFilePath).get("CREATEDBY") + "," + "\"" + "updatedBy" + "\""
				+ " : " + RP.loadPropertiesFile(SeriesCreationFilePath).get("UPDATEDBY") + "," + "\"" + "priority"
				+ "\"" + " : " + RP.loadPropertiesFile(SeriesCreationFilePath).get("PRIORITY") + "}";

		Response JsonResponse = given().log().all().header("Content-Type", table.get("Hcontent-Type")).body(Bjson)
				.when().post("/ls/api/leagueservice/series").then().extract().response();

		int StatusCode = JsonResponse.getStatusCode();

		JSONObject jobj = new JSONObject(JsonResponse.asString());
		if (StatusCode != (Integer.parseInt(table.get("TStatusCode")))) {

			JSONObject getNestedjson = jobj.getJSONObject("apierror");

			String Status = getNestedjson.get("status").toString();
			String Message = getNestedjson.get("message").toString();

			JSONArray jsonarr_1 = (JSONArray) getNestedjson.get("subErrors");
			JSONObject jsonobj_1 = (JSONObject) jsonarr_1.get(0);
			String SubErrorMessage = jsonobj_1.get("message").toString().trim();

			Assert.assertEquals(SubErrorMessage, table.get("TSubErrorMessage"));
			Assert.assertEquals(Status, table.get("TStatus"));
			Assert.assertEquals(Message, table.get("TMessage"));
			Assert.assertEquals(StatusCode, Integer.parseInt(table.get("TStatusCode")));
		}
		String Request = AllDrive.getWriter().toString().split("HTTP/1.1")[0];
		// String Response = "HTTP/1.1 " +
		// AllDrive.getWriter().toString().split("HTTP/1.1")[1];
		String Response = "HTTP/1.1 " + AllDrive.getWriter().toString().split("HTTP/1.1");
		ExtentTestManager.getTest().log(LogStatus.INFO, "REQUEST", Request);
		System.err.println(AllDrive.getWriter().toString());
		ExtentTestManager.getTest().log(LogStatus.INFO, "RESPONSE", Response);

	}

	/*@Test(dataProviderClass = Provider.class, dataProvider = "insertRest", description = "Howzat Create Series When Start Date and End Date API <Current Date-Negative Workflow Test",priority = 1)

	public void CreateSeriesStartDateAndEndDateLessThanCurrentDateNegativeTest(HashMap<String, String> table)
			throws Exception {

		cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, -2);
		String StartDate = dateFormat.format(cal.getTime());

		cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, -1);

		String EndDate = dateFormat.format(cal.getTime());

		String Bjson = "{" + "\"" + "countryId" + "\"" + " : "
				+ RP.loadPropertiesFile(SeriesCreationFilePath).get("COUNTRYID") + "," + "\"" + "endDate" + "\"" + " : "
				+ "\"" + EndDate + "\"" + "," + "\"" + "info" + "\"" + " : " + "\""

				+ RP.loadPropertiesFile(SeriesCreationFilePath).get("INFO") + "\"" + "," + "\"" + "name" + "\"" + " : "
				+ "\"" + RP.loadPropertiesFile(SeriesCreationFilePath).get("NAME") + "\"" + "," + "\"" + "seriesType"
				+ "\"" + " : " + RP.loadPropertiesFile(SeriesCreationFilePath).get("INTERNATIONALSERIESTYPE") + ","
				+ "\"" + "startDate" + "\"" + " : " + "\"" + StartDate + "\"" + "," + "\"" + "createdBy" + "\"" + " : "
				+ RP.loadPropertiesFile(SeriesCreationFilePath).get("CREATEDBY") + "," + "\"" + "updatedBy" + "\""
				+ " : " + RP.loadPropertiesFile(SeriesCreationFilePath).get("UPDATEDBY") + "," + "\"" + "priority"
				+ "\"" + " : " + RP.loadPropertiesFile(SeriesCreationFilePath).get("PRIORITY") + "}";
		System.out.println(Bjson);

		Response JsonResponse = given().log().all().header("Content-Type", table.get("Hcontent-Type")).body(Bjson)
				.when().post("/ls/api/leagueservice/series").then().extract().response();

		int StatusCode = JsonResponse.getStatusCode();

		JSONObject jobj = new JSONObject(JsonResponse.asString());
		if (StatusCode != (Integer.parseInt(table.get("TStatusCode")))) {

			WP.WritePropertiesFile(SeriesCreationFilePath, "INVALIDSERIESID", jobj.get("id").toString());

			Assert.assertEquals(StatusCode, Integer.parseInt(table.get("TStatusCode")));
		}

		String Request = AllDrive.getWriter().toString().split("HTTP/1.1")[0];
		// String Response = "HTTP/1.1 " +
		// AllDrive.getWriter().toString().split("HTTP/1.1")[1];
		String Response = "HTTP/1.1 " + AllDrive.getWriter().toString().split("HTTP/1.1");
		ExtentTestManager.getTest().log(LogStatus.INFO, "REQUEST", Request);
		System.err.println(AllDrive.getWriter().toString());
		ExtentTestManager.getTest().log(LogStatus.INFO, "RESPONSE", Response);

	}*/

}