//package com.Junglee.APITest.HowzatCore;
//
//import static io.restassured.RestAssured.given;
//import static org.hamcrest.Matchers.*;
//import java.sql.Connection;
//import java.util.HashMap;
//import java.util.LinkedHashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.Scanner;
//import java.util.TreeMap;
//import org.hamcrest.Matchers;
//import org.json.JSONArray;
//import org.json.JSONObject;
//import org.testng.Assert;
//import org.testng.annotations.Test;
//import com.Junglee.Base.AllDrive;
//import com.Junglee.Base.Base;
//import com.Junglee.Base.Provider;
//import com.Junglee.Utilities.ExtentTestManager;
////import com.fasterxml.jackson.databind.ser.SerializerCache.TypeKey;
//import com.relevantcodes.extentreports.LogStatus;
//import io.restassured.http.ContentType;
//import io.restassured.response.Response;
//import net.minidev.json.parser.JSONParser;
//import net.minidev.json.parser.ParseException;
//
//import com.Junglee.Utilities.*;
//
//public class HowzatGetPrizeStructureAPI extends Base {
//	DBManager DB = new DBManager();
//	Connection Conn = null;
//	String Query1Result;
//	HashMap<String, String> ResponseDataMap = new HashMap<String, String>();
//	HashMap<String, String> Query2ResultMap = new HashMap<String, String>();
//	String TempTranch = null;
//	String Query1 = null;
//	String Query2 = null;
//	Map<String, String> Query1SingleRowResult = null;
//	List<LinkedHashMap<String, String>> QueryMultipleRowResult = null;
//	String TempTranchStart = null;
//	String TempTranchEnd = null;
//	String TempPrize = null;
//
//	@Test(dataProviderClass = Provider.class, dataProvider = "insertRest", description = "Howzat Get Prize Structure Before Match Start API - Positive Workflow Test")
//	public void GetPrizeStructureBeforeMatchStartPositiveTest(HashMap<String, String> table) throws Exception {
//
//		Response JsonResponse = given().log().all().header("Content-Type", table.get("Hcontent-Type")).when()
//				.get("ls/api/leagueservice/prizestructure/details/" + table.get("CcontestID")).then().extract()
//				.response();
//
//		int StatusCode = JsonResponse.getStatusCode();
//
//		String JRes = JsonResponse.asString().replace("\"", "");
//		JRes = JsonResponse.asString().replace("\"rank\"", "rank");
//		JRes = JRes.replace("\"amount\"", "amount");
//		JRes = JRes.replace("\"0\"", "0");
//		JSONObject jobj = new JSONObject(JRes);
//		System.out.println("JSon Object Srting is " + JRes);
//		JSONArray jsonarr_1 = (JSONArray) jobj.get("0");
//		System.out.println("JSOn Array is " + jsonarr_1);
//
//		for (int i = 0; i < jsonarr_1.length(); i++) {
//			// Store the JSON objects in an array
//			// Get the index of the JSON object and print the values as per the
//			// index
//			JSONObject jsonobj_1 = (JSONObject) jsonarr_1.get(i);
//			ResponseDataMap.put(jsonobj_1.get("rank").toString().trim(), jsonobj_1.get("amount").toString().trim());
//		}
//
//		ResponseDataMap.forEach((key, value) -> System.out.println(key + " : " + value));
//
//		Query1 = "Select * from contest where id=" + table.get("CcontestID");
//		Conn = DB.createConnectionForHowzat();
//		Query1SingleRowResult = DB.executeQuerySRow(Query1, Conn);
//		Query1Result = Query1SingleRowResult.get("prize_structure_id");
//		Query2 = "Select * from prize_structure_tranch_details where prize_structure_id=" + Query1Result
//				+ " order by tranch_start asc";
//		Conn = DB.createConnectionForHowzat();
//		QueryMultipleRowResult = DB.executeQueryAllRow(Query2, Conn);
//
//		for (int i = 0; i < QueryMultipleRowResult.size(); i++) {
//			TempTranchStart = QueryMultipleRowResult.get(i).toString().split(",")[0];
//			TempTranchStart = TempTranchStart.replace("{", "");
//			TempTranchStart = TempTranchStart.replace("tranch_start=", "");
//			TempTranchEnd = QueryMultipleRowResult.get(i).toString().split(",")[1];
//			TempTranchEnd = TempTranchEnd.replace(" ", "");
//			TempTranchEnd = TempTranchEnd.replace("tranch_end=", "");
//			String TempPrize = QueryMultipleRowResult.get(i).toString().split(",")[2];
//			TempPrize = TempPrize.replace(" ", "");
//			TempPrize = TempPrize.replace("prize=", "");
//
//			if (!TempTranchStart.equalsIgnoreCase(TempTranchEnd)) {
//				TempTranch = TempTranchStart + "-" + TempTranchEnd;
//				if (!TempPrize.contains(".")) {
//					TempPrize = TempPrize + ".0";
//				}
//				Query2ResultMap.put(TempTranch.trim(), TempPrize.trim());
//			} else {
//				TempTranch = TempTranchStart;
//				if (!TempPrize.contains(".")) {
//					TempPrize = TempPrize + ".0";
//				}
//				Query2ResultMap.put(TempTranch.trim(), TempPrize.trim());
//			}
//
//		}
//
//		Query2ResultMap.forEach((key, value) -> System.out.println(key + " : " + value));
//
//		if (ResponseDataMap.equals(Query2ResultMap)) {
//			Assert.assertEquals(ResponseDataMap, Query2ResultMap);
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
//	@Test(dataProviderClass = Provider.class, dataProvider = "insertRest", description = "Howzat Get Prize Structure Before Match Start And Contest is not full API - Positive Workflow Test")
//	public void GetPrizeStructureBeforeMatchStartAndContestNotFullPositiveTest(HashMap<String, String> table)
//			throws Exception {
//
//		Response JsonResponse = given().log().all().header("Content-Type", table.get("Hcontent-Type")).when()
//				.get("ls/api/leagueservice/prizestructure/details/" + table.get("CcontestID")).then().extract()
//				.response();
//
//		int StatusCode = JsonResponse.getStatusCode();
//
//		String JRes = JsonResponse.asString().replace("\"", "");
//		JRes = JsonResponse.asString().replace("\"rank\"", "rank");
//		JRes = JRes.replace("\"amount\"", "amount");
//		JRes = JRes.replace("\"0\"", "0");
//		JSONObject jobj = new JSONObject(JRes);
//		System.out.println("JSon Object Srting is " + JRes);
//		JSONArray jsonarr_1 = (JSONArray) jobj.get("0");
//		System.out.println("JSOn Array is " + jsonarr_1);
//
//		for (int i = 0; i < jsonarr_1.length(); i++) {
//			// Store the JSON objects in an array
//			// Get the index of the JSON object and print the values as per the
//			// index
//			JSONObject jsonobj_1 = (JSONObject) jsonarr_1.get(i);
//			ResponseDataMap.put(jsonobj_1.get("rank").toString().trim(), jsonobj_1.get("amount").toString().trim());
//		}
//
//		ResponseDataMap.forEach((key, value) -> System.out.println(key + " : " + value));
//
//		Query1 = "Select * from contest where id=" + table.get("CcontestID");
//		Conn = DB.createConnectionForHowzat();
//		Query1SingleRowResult = DB.executeQuerySRow(Query1, Conn);
//		Query1Result = Query1SingleRowResult.get("prize_structure_id");
//		Query2 = "Select * from prize_structure_tranch_details where prize_structure_id=" + Query1Result
//				+ " order by tranch_start asc";
//		Conn = DB.createConnectionForHowzat();
//		QueryMultipleRowResult = DB.executeQueryAllRow(Query2, Conn);
//
//		for (int i = 0; i < QueryMultipleRowResult.size(); i++) {
//			TempTranchStart = QueryMultipleRowResult.get(i).toString().split(",")[0];
//			TempTranchStart = TempTranchStart.replace("{", "");
//			TempTranchStart = TempTranchStart.replace("tranch_start=", "");
//			TempTranchEnd = QueryMultipleRowResult.get(i).toString().split(",")[1];
//			TempTranchEnd = TempTranchEnd.replace(" ", "");
//			TempTranchEnd = TempTranchEnd.replace("tranch_end=", "");
//			String TempPrize = QueryMultipleRowResult.get(i).toString().split(",")[2];
//			TempPrize = TempPrize.replace(" ", "");
//			TempPrize = TempPrize.replace("prize=", "");
//
//			if (!TempTranchStart.equalsIgnoreCase(TempTranchEnd)) {
//				TempTranch = TempTranchStart + "-" + TempTranchEnd;
//				if (!TempPrize.contains(".")) {
//					TempPrize = TempPrize + ".0";
//				}
//				Query2ResultMap.put(TempTranch.trim(), TempPrize.trim());
//			} else {
//				TempTranch = TempTranchStart;
//				if (!TempPrize.contains(".")) {
//					TempPrize = TempPrize + ".0";
//				}
//				Query2ResultMap.put(TempTranch.trim(), TempPrize.trim());
//			}
//
//		}
//
//		Query2ResultMap.forEach((key, value) -> System.out.println(key + " : " + value));
//
//		if (ResponseDataMap.equals(Query2ResultMap)) {
//			Assert.assertEquals(ResponseDataMap, Query2ResultMap);
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
//	@Test(dataProviderClass = Provider.class, dataProvider = "insertRest", description = "Howzat Get Prize Structure Before Match Start And Contest is full API - Positive Workflow Test")
//	public void GetPrizeStructureBeforeMatchStartAndContestIsFullPositiveTest(HashMap<String, String> table)
//			throws Exception {
//
//		Response JsonResponse = given().log().all().header("Content-Type", table.get("Hcontent-Type")).when()
//				.get("ls/api/leagueservice/prizestructure/details/" + table.get("CcontestID")).then().extract()
//				.response();
//
//		int StatusCode = JsonResponse.getStatusCode();
//
//		String JRes = JsonResponse.asString().replace("\"", "");
//		JRes = JsonResponse.asString().replace("\"rank\"", "rank");
//		JRes = JRes.replace("\"amount\"", "amount");
//		JRes = JRes.replace("\"0\"", "0");
//		JSONObject jobj = new JSONObject(JRes);
//		System.out.println("JSon Object Srting is " + JRes);
//		JSONArray jsonarr_1 = (JSONArray) jobj.get("0");
//		System.out.println("JSOn Array is " + jsonarr_1);
//
//		for (int i = 0; i < jsonarr_1.length(); i++) {
//			// Store the JSON objects in an array
//			// Get the index of the JSON object and print the values as per the
//			// index
//			JSONObject jsonobj_1 = (JSONObject) jsonarr_1.get(i);
//			ResponseDataMap.put(jsonobj_1.get("rank").toString().trim(), jsonobj_1.get("amount").toString().trim());
//		}
//
//		ResponseDataMap.forEach((key, value) -> System.out.println(key + " : " + value));
//
//		Query1 = "Select * from contest where id=" + table.get("CcontestID");
//		Conn = DB.createConnectionForHowzat();
//		Query1SingleRowResult = DB.executeQuerySRow(Query1, Conn);
//		Query1Result = Query1SingleRowResult.get("prize_structure_id");
//		Query2 = "Select * from prize_structure_tranch_details where prize_structure_id=" + Query1Result
//				+ " order by tranch_start asc";
//		Conn = DB.createConnectionForHowzat();
//		QueryMultipleRowResult = DB.executeQueryAllRow(Query2, Conn);
//
//		for (int i = 0; i < QueryMultipleRowResult.size(); i++) {
//			TempTranchStart = QueryMultipleRowResult.get(i).toString().split(",")[0];
//			TempTranchStart = TempTranchStart.replace("{", "");
//			TempTranchStart = TempTranchStart.replace("tranch_start=", "");
//			TempTranchEnd = QueryMultipleRowResult.get(i).toString().split(",")[1];
//			TempTranchEnd = TempTranchEnd.replace(" ", "");
//			TempTranchEnd = TempTranchEnd.replace("tranch_end=", "");
//			String TempPrize = QueryMultipleRowResult.get(i).toString().split(",")[2];
//			TempPrize = TempPrize.replace(" ", "");
//			TempPrize = TempPrize.replace("prize=", "");
//
//			if (!TempTranchStart.equalsIgnoreCase(TempTranchEnd)) {
//				TempTranch = TempTranchStart + "-" + TempTranchEnd;
//				if (!TempPrize.contains(".")) {
//					TempPrize = TempPrize + ".0";
//				}
//				Query2ResultMap.put(TempTranch.trim(), TempPrize.trim());
//			} else {
//				TempTranch = TempTranchStart;
//				if (!TempPrize.contains(".")) {
//					TempPrize = TempPrize + ".0";
//				}
//				Query2ResultMap.put(TempTranch.trim(), TempPrize.trim());
//			}
//
//		}
//
//		Query2ResultMap.forEach((key, value) -> System.out.println(key + " : " + value));
//
//		if (ResponseDataMap.equals(Query2ResultMap)) {
//			Assert.assertEquals(ResponseDataMap, Query2ResultMap);
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
//	}
//
//	@Test(dataProviderClass = Provider.class, dataProvider = "insertRest", description = "Howzat Get Prize Structure After Match Start And Contest is not full API - Positive Workflow Test")
//	public void GetPrizeStructureAfterMatchStartAndContestNotFullPositiveTest(HashMap<String, String> table)
//			throws Exception {
//
//		Response JsonResponse = given().log().all().header("Content-Type", table.get("Hcontent-Type")).when()
//				.get("ls/api/leagueservice/prizestructure/details/" + table.get("CcontestID")).then().extract()
//				.response();
//
//		int StatusCode = JsonResponse.getStatusCode();
//
//		String JRes = JsonResponse.asString().replace("\"", "");
//		JRes = JsonResponse.asString().replace("\"rank\"", "rank");
//		JRes = JRes.replace("\"amount\"", "amount");
//		JRes = JRes.replace("\"0\"", "0");
//		JSONObject jobj = new JSONObject(JRes);
//		System.out.println("JSon Object Srting is " + JRes);
//		JSONArray jsonarr_1 = (JSONArray) jobj.get("0");
//		System.out.println("JSOn Array is " + jsonarr_1);
//
//		for (int i = 0; i < jsonarr_1.length(); i++) {
//			// Store the JSON objects in an array
//			// Get the index of the JSON object and print the values as per the
//			// index
//			JSONObject jsonobj_1 = (JSONObject) jsonarr_1.get(i);
//			ResponseDataMap.put(jsonobj_1.get("rank").toString().trim(), jsonobj_1.get("amount").toString().trim());
//		}
//
//		ResponseDataMap.forEach((key, value) -> System.out.println(key + " : " + value));
//
//		Query1 = "Select * from contest where id=" + table.get("CcontestID");
//		Conn = DB.createConnectionForHowzat();
//		Query1SingleRowResult = DB.executeQuerySRow(Query1, Conn);
//		Query1Result = Query1SingleRowResult.get("prize_structure_id");
//		Query2 = "Select * from prize_structure_tranch_details where prize_structure_id=" + Query1Result
//				+ " order by tranch_start asc";
//		Conn = DB.createConnectionForHowzat();
//		QueryMultipleRowResult = DB.executeQueryAllRow(Query2, Conn);
//
//		for (int i = 0; i < QueryMultipleRowResult.size(); i++) {
//			TempTranchStart = QueryMultipleRowResult.get(i).toString().split(",")[0];
//			TempTranchStart = TempTranchStart.replace("{", "");
//			TempTranchStart = TempTranchStart.replace("tranch_start=", "");
//			TempTranchEnd = QueryMultipleRowResult.get(i).toString().split(",")[1];
//			TempTranchEnd = TempTranchEnd.replace(" ", "");
//			TempTranchEnd = TempTranchEnd.replace("tranch_end=", "");
//			String TempPrize = QueryMultipleRowResult.get(i).toString().split(",")[2];
//			TempPrize = TempPrize.replace(" ", "");
//			TempPrize = TempPrize.replace("prize=", "");
//
//			if (!TempTranchStart.equalsIgnoreCase(TempTranchEnd)) {
//				TempTranch = TempTranchStart + "-" + TempTranchEnd;
//				if (!TempPrize.contains(".")) {
//					TempPrize = TempPrize + ".0";
//				}
//				Query2ResultMap.put(TempTranch.trim(), TempPrize.trim());
//			} else {
//				TempTranch = TempTranchStart;
//				if (!TempPrize.contains(".")) {
//					TempPrize = TempPrize + ".0";
//				}
//				Query2ResultMap.put(TempTranch.trim(), TempPrize.trim());
//			}
//
//		}
//
//		Query2ResultMap.forEach((key, value) -> System.out.println(key + " : " + value));
//
//		if (ResponseDataMap.equals(Query2ResultMap)) {
//			Assert.assertEquals(ResponseDataMap, Query2ResultMap);
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
//	@Test(dataProviderClass = Provider.class, dataProvider = "insertRest", description = "Howzat Get Prize Structure After Match Start And Contest is full API - Positive Workflow Test")
//	public void GetPrizeStructureAfterMatchStartAndContestIsFullPositiveTest(HashMap<String, String> table)
//			throws Exception {
//
//		Response JsonResponse = given().log().all().header("Content-Type", table.get("Hcontent-Type")).when()
//				.get("ls/api/leagueservice/prizestructure/details/" + table.get("CcontestID")).then().extract()
//				.response();
//
//		int StatusCode = JsonResponse.getStatusCode();
//
//		String JRes = JsonResponse.asString().replace("\"", "");
//		JRes = JsonResponse.asString().replace("\"rank\"", "rank");
//		JRes = JRes.replace("\"amount\"", "amount");
//		JRes = JRes.replace("\"0\"", "0");
//		JSONObject jobj = new JSONObject(JRes);
//		System.out.println("JSon Object Srting is " + JRes);
//		JSONArray jsonarr_1 = (JSONArray) jobj.get("0");
//		System.out.println("JSOn Array is " + jsonarr_1);
//
//		for (int i = 0; i < jsonarr_1.length(); i++) {
//			// Store the JSON objects in an array
//			// Get the index of the JSON object and print the values as per the
//			// index
//			JSONObject jsonobj_1 = (JSONObject) jsonarr_1.get(i);
//			ResponseDataMap.put(jsonobj_1.get("rank").toString().trim(), jsonobj_1.get("amount").toString().trim());
//		}
//
//		ResponseDataMap.forEach((key, value) -> System.out.println(key + " : " + value));
//
//		Query1 = "Select * from contest where id=" + table.get("CcontestID");
//		Conn = DB.createConnectionForHowzat();
//		Query1SingleRowResult = DB.executeQuerySRow(Query1, Conn);
//		Query1Result = Query1SingleRowResult.get("prize_structure_id");
//		Query2 = "Select * from prize_structure_tranch_details where prize_structure_id=" + Query1Result
//				+ " order by tranch_start asc";
//		Conn = DB.createConnectionForHowzat();
//		QueryMultipleRowResult = DB.executeQueryAllRow(Query2, Conn);
//
//		for (int i = 0; i < QueryMultipleRowResult.size(); i++) {
//			TempTranchStart = QueryMultipleRowResult.get(i).toString().split(",")[0];
//			TempTranchStart = TempTranchStart.replace("{", "");
//			TempTranchStart = TempTranchStart.replace("tranch_start=", "");
//			TempTranchEnd = QueryMultipleRowResult.get(i).toString().split(",")[1];
//			TempTranchEnd = TempTranchEnd.replace(" ", "");
//			TempTranchEnd = TempTranchEnd.replace("tranch_end=", "");
//			String TempPrize = QueryMultipleRowResult.get(i).toString().split(",")[2];
//			TempPrize = TempPrize.replace(" ", "");
//			TempPrize = TempPrize.replace("prize=", "");
//
//			if (!TempTranchStart.equalsIgnoreCase(TempTranchEnd)) {
//				TempTranch = TempTranchStart + "-" + TempTranchEnd;
//				if (!TempPrize.contains(".")) {
//					TempPrize = TempPrize + ".0";
//				}
//				Query2ResultMap.put(TempTranch.trim(), TempPrize.trim());
//			} else {
//				TempTranch = TempTranchStart;
//				if (!TempPrize.contains(".")) {
//					TempPrize = TempPrize + ".0";
//				}
//				Query2ResultMap.put(TempTranch.trim(), TempPrize.trim());
//			}
//
//		}
//
//		Query2ResultMap.forEach((key, value) -> System.out.println(key + " : " + value));
//
//		if (ResponseDataMap.equals(Query2ResultMap)) {
//			Assert.assertEquals(ResponseDataMap, Query2ResultMap);
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
//}