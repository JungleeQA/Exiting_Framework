package com.Junglee.APITest.HowzatCore;

import static io.restassured.RestAssured.given;
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

public class HowzatCreatePrizeStructureAPI extends Base {

	ReadPropertiesFiles RP = new ReadPropertiesFiles();
	WritePropertiesFiles WP = new WritePropertiesFiles();

	String ConfigFilePath = "../APIAutoationFramework/src/test/resources/properties/Config.properties";
	String PrizeStructureCreationFilePath = RP.loadPropertiesFile(ConfigFilePath)
			.getProperty("PRIZESTRUCTURECREATIONFILEPATH");

	@Test(dataProviderClass = Provider.class, dataProvider = "insertRest", description = "Howzat Prize Structure Creation API - Positive Workflow Test")
	public void CreatePrizeStructurePositiveTest(HashMap<String, String> table) throws Exception {

		String Bjson = "{" + "\"" + "name" + "\"" + " : " + "\""
				+ RP.loadPropertiesFile(PrizeStructureCreationFilePath).get("NAME") + "\"" + "," + "\""
				+ "prizePoolType" + "\"" + " : "
				+ RP.loadPropertiesFile(PrizeStructureCreationFilePath).get("PRIZEPOOLTYPE") + "," + "\""
				+ "contestSize" + "\"" + " : "
				+ RP.loadPropertiesFile(PrizeStructureCreationFilePath).get("CONTESTSIZE") + "," + "\"" + "prizeType"
				+ "\"" + " : " + RP.loadPropertiesFile(PrizeStructureCreationFilePath).get("PRIZETYPE") + "," + "\""
				+ "serviceFeeType" + "\"" + " : "
				+ RP.loadPropertiesFile(PrizeStructureCreationFilePath).get("SERVICEFEETYPE") + "," + "\"" + "entryFee"
				+ "\"" + " : " + RP.loadPropertiesFile(PrizeStructureCreationFilePath).get("ENTRYFEE") + "," + "\""
				+ "createdBy" + "\"" + " : " + RP.loadPropertiesFile(PrizeStructureCreationFilePath).get("CREATEDBY")
				+ "," + "\"" + "updatedBy" + "\"" + " : "
				+ RP.loadPropertiesFile(PrizeStructureCreationFilePath).get("UPDATEDBY") + "," + "\"" + "serviceFee"
				+ "\"" + " : " + RP.loadPropertiesFile(PrizeStructureCreationFilePath).get("SERVICEFEE") + "," + "\""
				+ "minGuaranteedPot" + "\"" + " : "
				+ RP.loadPropertiesFile(PrizeStructureCreationFilePath).get("MINGUARANTEEDPOT") + "}";

		Response JsonResponse = given().log().all().header("Content-Type", table.get("Hcontent-Type")).body(Bjson)
				.when().post("/ls/api/leagueservice/prizestructure").then().extract().response();

		int StatusCode = JsonResponse.getStatusCode();

		JSONObject jobj = new JSONObject(JsonResponse.asString());
		WP.WritePropertiesFile(PrizeStructureCreationFilePath, "PRIZESTRUCTUREID", jobj.get("id").toString());

		Assert.assertEquals(StatusCode, Integer.parseInt(table.get("TStatusCode")));

		String Request = AllDrive.getWriter().toString().split("HTTP/1.1")[0];
		// String Response = "HTTP/1.1 " +
		// AllDrive.getWriter().toString().split("HTTP/1.1")[1];
		String Response = "HTTP/1.1 " + AllDrive.getWriter().toString().split("HTTP/1.1");
		ExtentTestManager.getTest().log(LogStatus.INFO, "REQUEST", Request);
		System.err.println(AllDrive.getWriter().toString());
		ExtentTestManager.getTest().log(LogStatus.INFO, "RESPONSE", Response);

	}

	@Test(dataProviderClass = Provider.class, dataProvider = "insertRest", description = "Howzat Prize Structure Creation With Blank Data Set API - Negative Workflow Test",priority = 4)
	public void CreatePrizeStructureWithBlankDataSetNegativeTest(HashMap<String, String> table) throws Exception {

		String Bjson = "{" + "\"" + "name" + "\"" + " : " + "\"" + "\"" + "," + "\"" + "prizePoolType" + "\"" + " : "
				+ "," + "\"" + "contestSize" + "\"" + " : " + "," + "\"" + "prizeType" + "\"" + " : " + "," + "\""
				+ "serviceFeeType" + "\"" + " : " + "," + "\"" + "entryFee" + "\"" + " : " + "," + "\"" + "createdBy"
				+ "\"" + " : " + "," + "\"" + "updatedBy" + "\"" + " : " + "," + "\"" + "serviceFee" + "\"" + " : "
				+ "," + "\"" + "minGuaranteedPot" + "\"" + " : " + "}";

		given().log().all().header("Content-Type", table.get("Hcontent-Type")).body(Bjson).when()
				.post("/ls/api/leagueservice/prizestructure").then().log().all().and().assertThat()
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