//package com.Junglee.APITest.HowzatCore;
//
//import static io.restassured.RestAssured.given;
//import java.sql.Connection;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import org.json.JSONArray;
//import org.json.JSONObject;
//import org.testng.Assert;
//import org.testng.annotations.Test;
//import com.relevantcodes.extentreports.LogStatus;
//import io.restassured.response.Response;
//import com.Junglee.Base.AllDrive;
//import com.Junglee.Base.Base;
//import com.Junglee.Base.Provider;
//import com.Junglee.Utilities.DBManager;
//import com.Junglee.Utilities.ExtentTestManager;
//import com.mongodb.BasicDBObject;
//import com.mongodb.DB;
//import com.mongodb.DBCollection;
//import com.mongodb.DBCursor;
//import com.mongodb.MongoClient;
//
//public class HowzatGetContestTeamsAPI extends Base {
//	DBManager DB = new DBManager();
//	Connection Conn = null;
//	BasicDBObject DBObj = null;
//	HashMap<String, String> ResponseDataMap = new HashMap<String, String>();
//	HashMap<String, String> QueryResponseDataMap = new HashMap<String, String>();
//
//	@Test(dataProviderClass = Provider.class, dataProvider = "insertRest", description = "Howzat Get Contest Teams API Before Starting A Match - Positive Workflow Test")
//	public void GetContestTeamPreviewBeforeMatchPositiveTest(HashMap<String, String> table) throws Exception {
//
//		BasicDBObject obj = null;
//
//		Response JsonResponse = given().log().all().header("" + "Content-Type", table.get("Hcontent-Type")).when()
//				.get("ls/api/lobby/contest/" + table.get("TContestID") + "/teams/?offset=1&limit=100").then().extract()
//				.response();
//
//		int StatusCode = JsonResponse.getStatusCode();
//
//		String JRes = "{" + "\n" + "\"Object\":" + JsonResponse.asString() + "\n" + "}";
//		JSONObject jobj = new JSONObject(JRes);
//		System.out.println("JSon Object Srting is " + JRes);
//		JSONArray jsonarr_1 = (JSONArray) jobj.get("Object");
//		System.out.println("JSOn Array is " + jsonarr_1);
//
//		for (int j = 0; j < jsonarr_1.length(); j++) {
//
//			ArrayList<String> Ids = new ArrayList<String>();
//			ArrayList<String> TeamIds = new ArrayList<String>();
//			ArrayList<String> Names = new ArrayList<String>();
//			ArrayList<String> PlayingStyleDescs = new ArrayList<String>();
//			ArrayList<String> Scores = new ArrayList<String>();
//
//			JSONObject jsonobj_1 = (JSONObject) jsonarr_1.get(j);
//			ResponseDataMap.put("_id".trim(), jsonobj_1.get("_id").toString().trim());
//			ResponseDataMap.put("userId".trim(), jsonobj_1.get("userId").toString().trim());
//			ResponseDataMap.put("contestId".trim(), jsonobj_1.get("contestId").toString().trim());
//			ResponseDataMap.put("leagueId".trim(), jsonobj_1.get("leagueId").toString().trim());
//			ResponseDataMap.put("matchId".trim(), jsonobj_1.get("matchId").toString().trim());
//			ResponseDataMap.put("prize".trim(), jsonobj_1.get("prize").toString().trim());
//			ResponseDataMap.put("rank".trim(), jsonobj_1.get("rank").toString().trim());
//			ResponseDataMap.put("inningsId".trim(), jsonobj_1.get("inningsId").toString().trim());
//			ResponseDataMap.put("channelId".trim(), jsonobj_1.get("inningsId").toString().trim());
//			ResponseDataMap.put("id".trim(), jsonobj_1.get("id").toString().trim());
//			ResponseDataMap.put("name".trim(), jsonobj_1.get("name").toString().trim());
//			ResponseDataMap.put("score".trim(), jsonobj_1.get("score").toString().trim());
//
//			JSONArray JSONArrayPlayerinfo = jsonobj_1.getJSONArray("players");
//			for (int i = 0; i < JSONArrayPlayerinfo.length(); i++) {
//				jsonobj_1 = (JSONObject) JSONArrayPlayerinfo.get(i);
//
//				Ids.add(jsonobj_1.get("id").toString());
//				Names.add(jsonobj_1.get("name").toString());
//				PlayingStyleDescs.add(jsonobj_1.get("playingStyleDesc").toString());
//				// Scores.add(jsonobj_1.get("score").toString());
//				TeamIds.add(jsonobj_1.get("realTeamId").toString());
//
//			}
//
//			ResponseDataMap.forEach((key, value) -> System.out.println(key + " : " + value));
//			System.out.println("Ids are : " + Ids);
//			System.out.println("Team Ids are :" + TeamIds);
//			System.out.println("Names are : " + Names);
//			System.out.println("Playing Style Descriptions are : " + PlayingStyleDescs);
//			System.out.println("Scores are : " + Scores);
//
//			MongoClient Mongo = null;
//			Mongo = new MongoClient("10.90.125.60", 27017);
//			DB db = Mongo.getDB("playfantasy");
//			DBCollection Coll = db.getCollection("contest_reg_info");
//			BasicDBObject allQuery = new BasicDBObject();
//			// allQuery.put("contestId", 11633);
//			// DBCursor cursor = Coll.find(allQuery);
//
//			List<BasicDBObject> AndCondition = new ArrayList<BasicDBObject>();
//			AndCondition.add(new BasicDBObject("contestId", 11633));
//			AndCondition.add(new BasicDBObject("teamName", ResponseDataMap.get("name")));
//			allQuery.put("$and", AndCondition);
//			DBCursor cursor = Coll.find(allQuery);
//
//			while (cursor.hasNext()) {
//				obj = (BasicDBObject) cursor.next();
//				QueryResponseDataMap.put("_id".trim(), obj.getString("_id"));
//				QueryResponseDataMap.put("id".trim(), obj.getString("teamId"));
//				QueryResponseDataMap.put("name".trim(), obj.getString("teamName"));
//				QueryResponseDataMap.put("userId".trim().trim(), obj.get("userId").toString().trim());
//				QueryResponseDataMap.put("contestId".trim(), obj.get("contestId").toString().trim());
//				QueryResponseDataMap.put("leagueId".trim(), obj.get("leagueId").toString().trim());
//				QueryResponseDataMap.put("matchId".trim(), obj.get("matchId").toString().trim());
//				QueryResponseDataMap.put("prize".trim(), obj.get("prize").toString().trim());
//				QueryResponseDataMap.put("rank".trim(), obj.get("rank").toString().trim());
//				QueryResponseDataMap.put("score".trim(), obj.getString("totalScore").trim());
//				QueryResponseDataMap.put("inningsId".trim(), obj.get("inningsId").toString().trim());
//				QueryResponseDataMap.put("channelId".trim(), obj.get("channelId").toString().trim());
//
//			}
//			QueryResponseDataMap.forEach((key, value) -> System.out.println(key + " : " + value));
//			Coll = db.getCollection("fan_team");
//			allQuery = new BasicDBObject();
//			allQuery.put("teamId", obj.get("teamId"));
//			cursor = Coll.find(allQuery);
//
//			ArrayList<String> IdsQuery = new ArrayList<String>();
//			ArrayList<String> TeamIdsQuery = new ArrayList<String>();
//			ArrayList<String> NamesQuery = new ArrayList<String>();
//			ArrayList<String> PlayingStyleDescsQuery = new ArrayList<String>();
//			// ArrayList<String> ScoresQuery = new ArrayList<String>();
//
//			while (cursor.hasNext()) {
//				obj = (BasicDBObject) cursor.next();
//				JSONObject jobj1 = new JSONObject(obj);
//				System.out.println("JSon Object Srting is " + obj);
//				JSONArray jsonarr_2 = (JSONArray) jobj1.get("players");
//				System.out.println("JSOn Array is " + jsonarr_2);
//
//				for (int k = 0; k < jsonarr_2.length(); k++) {
//					String[] temp = jsonarr_2.get(k).toString().split(",");
//					IdsQuery.add(temp[3].split(":")[1]);
//					TeamIdsQuery.add(temp[4].split(":")[1].replace("}", ""));
//					NamesQuery.add(temp[0].split(":")[1].replace("\"", ""));
//					PlayingStyleDescsQuery.add(temp[1].split(":")[1].replace("\"", ""));
//
//					// if (temp[2].split(":")[1].contains("."))
//					// ScoresQuery.add(temp[2].split(":")[1]);
//					// else
//					// ScoresQuery.add(temp[2].split(":")[1] + ".0");
//
//				}
//
//			}
//
//			Mongo.close();
//
//			// }
//
//			QueryResponseDataMap.forEach((key, value) -> System.out.println(key + " : " + value));
//			System.out.println("Ids in query are: " + IdsQuery);
//			System.out.println("Team Ids in query are : " + TeamIdsQuery);
//			System.out.println("Names in query are :" + NamesQuery);
//			System.out.println("Playing Style Descriptions in query are :" + PlayingStyleDescsQuery);
//			// System.out.println("Scores in query are: " + ScoresQuery);
//
//			if (ResponseDataMap.equals(QueryResponseDataMap))
//
//			{
//				Assert.assertEquals(ResponseDataMap, QueryResponseDataMap);
//			}
//
//			if (Ids.equals(IdsQuery))
//
//			{
//				Assert.assertEquals(Ids, IdsQuery);
//				Ids = null;
//				IdsQuery = null;
//			}
//
//			if (TeamIds.equals(TeamIdsQuery))
//
//			{
//				Assert.assertEquals(TeamIds, TeamIdsQuery);
//				TeamIds = null;
//				TeamIdsQuery = null;
//			}
//
//			if (Names.equals(NamesQuery))
//
//			{
//				Assert.assertEquals(Names, NamesQuery);
//				Names = null;
//				NamesQuery = null;
//			}
//
//			// if (Scores.equals(ScoresQuery))
//			//
//			// {
//			// Assert.assertEquals(Scores, ScoresQuery);
//			// Scores = null;
//			// ScoresQuery = null;
//			// }
//
//			if (PlayingStyleDescs.equals(PlayingStyleDescsQuery))
//
//			{
//				Assert.assertEquals(PlayingStyleDescs, PlayingStyleDescsQuery);
//				PlayingStyleDescs = null;
//				PlayingStyleDescsQuery = null;
//			}
//		}
//
//		assert StatusCode == 200 : "Valid Response Code";
//
//		String Request = AllDrive.getWriter().toString().split("HTTP/1.1")[0];
//		// String Response = "HTTP/1.1 " +
//		// AllDrive.getWriter().toString().split("HTTP/1.1")[1];
//		String Response = "HTTP/1.1 " + AllDrive.getWriter().toString().split("HTTP/1.1");
//		ExtentTestManager.getTest().log(LogStatus.INFO, "REQUEST", Request);
//		System.err.println(AllDrive.getWriter().toString());
//		ExtentTestManager.getTest().log(LogStatus.INFO, "RESPONSE", Response);
//
//	}
//
//}