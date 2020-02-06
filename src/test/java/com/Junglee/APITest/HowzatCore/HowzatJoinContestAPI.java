
package com.Junglee.APITest.HowzatCore;

import static io.restassured.RestAssured.given;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.Junglee.Base.AllDrive;
import com.Junglee.Base.Base;
import com.Junglee.Base.Provider;
import com.Junglee.Utilities.ExtentTestManager;
import com.Junglee.Utilities.ReadPropertiesFiles;
import com.Junglee.Utilities.WritePropertiesFiles;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import com.relevantcodes.extentreports.LogStatus;
import io.restassured.response.Response;

public class HowzatJoinContestAPI extends Base {
	ReadPropertiesFiles RP = new ReadPropertiesFiles();
	WritePropertiesFiles WP = new WritePropertiesFiles();

	String ConfigFilePath = "../APIAutoationFramework/src/test/resources/properties/Config.properties";
	String GameType = RP.loadPropertiesFile(ConfigFilePath).getProperty("GAMETYPE");
	String PrizeStructureCreationFilePath = RP.loadPropertiesFile(ConfigFilePath)
			.getProperty("PRIZESTRUCTURECREATIONFILEPATH");
	String ContestCreationFilePath = RP.loadPropertiesFile(ConfigFilePath).getProperty("CONTESTCREATIONFILEPATH");
	String MatchAndLeagueCreationFilePath = RP.loadPropertiesFile(ConfigFilePath)
			.getProperty("MATCHANDLEAGUECREATIONFILEPATH");

	@Test(dataProviderClass = Provider.class, dataProvider = "insertRest", description = "Howzat Join Contest API - Positive Workflow Test")
	public void JoinContestPositiveTest(HashMap<String, String> table) {
		String Bjson = "{" + "\"" + "teamId" + "\"" + " : " + "\""
				+ RP.loadPropertiesFile(ConfigFilePath).get("FANTEAMID") + "\"" + "," + "\"" + "contestId" + "\""
				+ " : " + RP.loadPropertiesFile(ContestCreationFilePath).get("CONTESTID") + "," + "\"" + "contestCode"
				+ "\"" + " : " + "\"" + RP.loadPropertiesFile(ContestCreationFilePath).get("CONTESTJOINCODE") + "\""
				+ "," + "\"" + "channel_id" + "\"" + " : "
				+ RP.loadPropertiesFile(ContestCreationFilePath).get("CHANNELIDS") + "," + "\"" + "leagueId" + "\""
				+ " : " + RP.loadPropertiesFile(MatchAndLeagueCreationFilePath).get(GameType + "LEAGUEID") + "," + "\""
				+ "inningsId" + "\"" + " : " + "0" + "," + "\"" + "matchId" + "\"" + " : "
				+ RP.loadPropertiesFile(MatchAndLeagueCreationFilePath).get(GameType + "MATCHID") + "," + "\""
				+ "userId" + "\"" + " : " + RP.loadPropertiesFile(ConfigFilePath).get("USERID") + "}";

		System.out.println("Text is" + Bjson);

		Response JsonResponse = given().log().all().header("Content-Type", table.get("Hcontent-Type")).body(Bjson)
				.when().post("/ls/api/lobby/contest/join").then().extract().response();

		int StatusCode = JsonResponse.getStatusCode();

		JSONObject jobj = new JSONObject(JsonResponse.asString());

		Assert.assertEquals(StatusCode, Integer.parseInt(table.get("TStatusCode")));

		BasicDBObject obj = null;
		MongoClient Mongo = null;
		Mongo = new MongoClient(RP.loadPropertiesFile(ConfigFilePath).get("DBIP").toString().trim(),
				Integer.parseInt((RP.loadPropertiesFile(ConfigFilePath).get("PORT").toString().trim())));
		@SuppressWarnings("deprecation")
		DB db = Mongo.getDB(RP.loadPropertiesFile(ConfigFilePath).get("DBNAME").toString().trim());
		DBCollection Coll = db
				.getCollection(RP.loadPropertiesFile(ConfigFilePath).get("COLLECTIONNAME").toString().trim());
		BasicDBObject allQuery = new BasicDBObject();
		List<BasicDBObject> AndCondition = new ArrayList<BasicDBObject>();

		AndCondition.add(new BasicDBObject("teamId",
				Long.parseLong(RP.loadPropertiesFile(ConfigFilePath).get("FANTEAMID").toString().trim())));
		AndCondition.add(new BasicDBObject("userId",
				Long.parseLong(RP.loadPropertiesFile(ConfigFilePath).get("USERID").toString().trim())));
		AndCondition.add(new BasicDBObject("contestId",
				Long.parseLong(RP.loadPropertiesFile(ContestCreationFilePath).get("CONTESTID").toString().trim())));
		AndCondition.add(new BasicDBObject("leagueId", Long.parseLong(
				RP.loadPropertiesFile(MatchAndLeagueCreationFilePath).get(GameType + "LEAGUEID").toString().trim())));
		AndCondition.add(new BasicDBObject("matchId", Long.parseLong(
				RP.loadPropertiesFile(MatchAndLeagueCreationFilePath).get(GameType + "MATCHID").toString().trim())));

		allQuery.put("$and", AndCondition);
		DBCursor cursor = Coll.find(allQuery);

		if (cursor.count() == 1) {
			// System.out.println(cursor.next());
			obj = (BasicDBObject) cursor.next();
			JSONObject jobj1 = new JSONObject(obj);
			System.out.println("JSon Object Srting is " + obj);
			Assert.assertEquals(jobj1.get("teamId").toString().trim(),
					RP.loadPropertiesFile(ConfigFilePath).get("FANTEAMID").toString().trim());
			Assert.assertEquals(jobj1.get("userId").toString().trim(),
					RP.loadPropertiesFile(ConfigFilePath).get("USERID").toString().trim());
			Assert.assertEquals(jobj1.get("contestId").toString().trim(),
					RP.loadPropertiesFile(ContestCreationFilePath).get("CONTESTID").toString().trim());
			Assert.assertEquals(jobj1.get("leagueId").toString().trim(),
					RP.loadPropertiesFile(MatchAndLeagueCreationFilePath).get(GameType + "LEAGUEID").toString().trim());
			Assert.assertEquals(jobj1.get("matchId").toString().trim(),
					RP.loadPropertiesFile(MatchAndLeagueCreationFilePath).get(GameType + "MATCHID").toString().trim());

		}

		if (cursor.count() == 0)
			Assert.fail("No record found");

		if (cursor.count() > 1)
			Assert.fail("More than one record found");

		Mongo.close();

		String Request = AllDrive.getWriter().toString().split("HTTP/1.1")[0];
		// String Response = "HTTP/1.1 " +
		// AllDrive.getWriter().toString().split("HTTP/1.1")[1];
		String Response = "HTTP/1.1 " + AllDrive.getWriter().toString().split("HTTP/1.1");
		ExtentTestManager.getTest().log(LogStatus.INFO, "REQUEST", Request);
		System.err.println(AllDrive.getWriter().toString());
		ExtentTestManager.getTest().log(LogStatus.INFO, "RESPONSE", Response);

	}

}