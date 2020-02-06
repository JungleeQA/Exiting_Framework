package com.Junglee.APITest.HowzatCore;

import static io.restassured.RestAssured.given;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.Junglee.Base.AllDrive;
import com.Junglee.Base.Base;
import com.Junglee.Base.Provider;
import com.Junglee.Utilities.ExtentTestManager;
import com.Junglee.Utilities.ReadPropertiesFiles;
import com.Junglee.Utilities.SortHashMapByValue;
import com.Junglee.Utilities.WritePropertiesFiles;
import com.relevantcodes.extentreports.LogStatus;
import io.restassured.response.Response;
import java.io.File;

public class HowzatCreateFanTeamAPI extends Base {

	@Test(dataProviderClass = Provider.class, dataProvider = "insertRest", description = "Howzat Create Team API - Positive Workflow Test")
	public void CreateTeamPositiveTest(HashMap<String, String> table) throws Exception {
		ReadPropertiesFiles RP = new ReadPropertiesFiles();
		WritePropertiesFiles WP = new WritePropertiesFiles();
		SortHashMapByValue Sort = new SortHashMapByValue();

		HashMap<String, Float> UnsortedBowlers = new HashMap<String, Float>();
		HashMap<String, Float> UnsortedAllRounders = new HashMap<String, Float>();
		HashMap<String, Float> UnsortedBatsman = new HashMap<String, Float>();
		HashMap<String, Float> UnsortedWicketKeepers = new HashMap<String, Float>();

		HashMap<String, Float> SortedBowlers = new HashMap<String, Float>();
		HashMap<String, Float> SortedAllRounders = new HashMap<String, Float>();
		HashMap<String, Float> SortedBatsman = new HashMap<String, Float>();
		HashMap<String, Float> SortedWicketKeepers = new HashMap<String, Float>();

		HashMap<String, Float> UnsortedDefenders = new HashMap<String, Float>();
		HashMap<String, Float> UnsortedRaiders = new HashMap<String, Float>();

		HashMap<String, Float> SortedDefenders = new HashMap<String, Float>();
		HashMap<String, Float> SortedRaiders = new HashMap<String, Float>();

		HashMap<String, Float> UnsortedGoalKeepers = new HashMap<String, Float>();
		HashMap<String, Float> SortedGoalKeepers = new HashMap<String, Float>();

		HashMap<String, Float> UnsortedMidFielders = new HashMap<String, Float>();
		HashMap<String, Float> UnsortedForwards = new HashMap<String, Float>();

		HashMap<String, Float> SortedMidFielders = new HashMap<String, Float>();
		HashMap<String, Float> SortedForwards = new HashMap<String, Float>();

		String ConfigFilePath = "../APIAutoationFramework/src/test/resources/properties/Config.properties";
		String MatchAndLeagueCreationFilePath = RP.loadPropertiesFile(ConfigFilePath)
				.getProperty("MATCHANDLEAGUECREATIONFILEPATH");
		String GameType = RP.loadPropertiesFile(ConfigFilePath).getProperty("GAMETYPE");
		String TeamA = RP.loadPropertiesFile(MatchAndLeagueCreationFilePath).getProperty("TEAMA");
		String TeamB = RP.loadPropertiesFile(MatchAndLeagueCreationFilePath).getProperty("TEAMB");
		String RealTeamAPlayerDeatils = RP.loadPropertiesFile(ConfigFilePath).getProperty("REALTEAMAPLAYERDETAILS");
		String RealTeamBPlayerDeatils = RP.loadPropertiesFile(ConfigFilePath).getProperty("REALTEAMBPLAYERDETAILS");
		String CricketFilePath = RP.loadPropertiesFile(ConfigFilePath).getProperty("CRICKETCONFIGFILEPATH");
		String KabaddiFilePath = RP.loadPropertiesFile(ConfigFilePath).getProperty("KABADDICONFIGFILEPATH");
		String FootballFilePath = RP.loadPropertiesFile(ConfigFilePath).getProperty("FOOTBALLCONFIGFILEPATH");
		String FanTeamPlayerDeatils = RP.loadPropertiesFile(ConfigFilePath).getProperty("FANTEAMPLAYERDETAILS");
		String SeriesCreationFilePath = RP.loadPropertiesFile(ConfigFilePath)
				.getProperty("SERIESCREATIONCONFIGFILEPATH");

		String CaptainID = null;
		String ViceCaptainID = null;
		String Bjson = null;

		String text = "";
		try {
			text = new String(Files.readAllBytes(Paths.get(RealTeamAPlayerDeatils)));
		} catch (IOException e) {
			e.printStackTrace();
		}

		JSONObject jobj = new JSONObject(text);
		JSONArray jsonarr_1 = (JSONArray) jobj.get("players");

		if (GameType.equalsIgnoreCase("CRICKET")) {
			for (int j = 0; j < jsonarr_1.length(); j++) {

				JSONObject jsonobj_1 = (JSONObject) jsonarr_1.get(j);
				System.out.println(jsonobj_1.toString());

				// if (GameType.equalsIgnoreCase("CRICKET")) {
				if (jsonobj_1.get("playingStyleId").toString().equalsIgnoreCase(
						RP.loadPropertiesFile(CricketFilePath).get("BOWLERPLAYINGSTYLEID").toString())) {
					UnsortedBowlers.put(Integer.toString(j), Float.parseFloat(jsonobj_1.get("credit").toString()));
				}

				if (jsonobj_1.get("playingStyleId").toString().equalsIgnoreCase(
						RP.loadPropertiesFile(CricketFilePath).get("WICKETKEEPERPLAYINGSTYLEID").toString())) {
					UnsortedWicketKeepers.put(Integer.toString(j),
							Float.parseFloat(jsonobj_1.get("credit").toString()));
				}
				if (jsonobj_1.get("playingStyleId").toString().equalsIgnoreCase(
						RP.loadPropertiesFile(CricketFilePath).get("BATSMANPLAYINGSTYLEID").toString())) {
					UnsortedBatsman.put(Integer.toString(j), Float.parseFloat(jsonobj_1.get("credit").toString()));
				}
				if (jsonobj_1.get("playingStyleId").toString().equalsIgnoreCase(
						RP.loadPropertiesFile(CricketFilePath).get("ALLROUNDERPLAYINGSTYLEID").toString())) {
					UnsortedAllRounders.put(Integer.toString(j), Float.parseFloat(jsonobj_1.get("credit").toString()));
				}
			}
			SortedBowlers = Sort.sortHashMapByValues(UnsortedBowlers);
			SortedAllRounders = Sort.sortHashMapByValues(UnsortedAllRounders);
			SortedBatsman = Sort.sortHashMapByValues(UnsortedBatsman);
			SortedWicketKeepers = Sort.sortHashMapByValues(UnsortedWicketKeepers);

		}

		if (GameType.equalsIgnoreCase("KABADDI")) {
			for (int j = 0; j < jsonarr_1.length(); j++) {

				JSONObject jsonobj_1 = (JSONObject) jsonarr_1.get(j);
				System.out.println(jsonobj_1.toString());

				// if (GameType.equalsIgnoreCase("CRICKET")) {
				if (jsonobj_1.get("playingStyleId").toString().equalsIgnoreCase(
						RP.loadPropertiesFile(KabaddiFilePath).get("DEFENDERPLAYINGSTYLEID").toString())) {
					UnsortedDefenders.put(Integer.toString(j), Float.parseFloat(jsonobj_1.get("credit").toString()));
				}

				if (jsonobj_1.get("playingStyleId").toString().equalsIgnoreCase(
						RP.loadPropertiesFile(KabaddiFilePath).get("RAIDERPLAYINGSTYLEID").toString())) {
					UnsortedRaiders.put(Integer.toString(j), Float.parseFloat(jsonobj_1.get("credit").toString()));
				}
				if (jsonobj_1.get("playingStyleId").toString().equalsIgnoreCase(
						RP.loadPropertiesFile(KabaddiFilePath).get("ALLROUNDERPLAYINGSTYLEID").toString())) {
					UnsortedAllRounders.put(Integer.toString(j), Float.parseFloat(jsonobj_1.get("credit").toString()));
				}

			}
			SortedDefenders = Sort.sortHashMapByValues(UnsortedDefenders);
			SortedAllRounders = Sort.sortHashMapByValues(UnsortedAllRounders);
			SortedRaiders = Sort.sortHashMapByValues(UnsortedRaiders);

		}

		if (GameType.equalsIgnoreCase("FOOTBALL")) {
			for (int j = 0; j < jsonarr_1.length(); j++) {

				JSONObject jsonobj_1 = (JSONObject) jsonarr_1.get(j);
				System.out.println(jsonobj_1.toString());

				// if (GameType.equalsIgnoreCase("CRICKET")) {
				if (jsonobj_1.get("playingStyleId").toString().equalsIgnoreCase(
						RP.loadPropertiesFile(FootballFilePath).get("DEFENDERPLAYINGSTYLEID").toString())) {
					UnsortedDefenders.put(Integer.toString(j), Float.parseFloat(jsonobj_1.get("credit").toString()));
				}

				if (jsonobj_1.get("playingStyleId").toString().equalsIgnoreCase(
						RP.loadPropertiesFile(FootballFilePath).get("GOALKEEPERPLAYINGSTYLEID").toString())) {
					UnsortedGoalKeepers.put(Integer.toString(j), Float.parseFloat(jsonobj_1.get("credit").toString()));
				}
				if (jsonobj_1.get("playingStyleId").toString().equalsIgnoreCase(
						RP.loadPropertiesFile(FootballFilePath).get("MIDFIELDERPLAYINGSTYLEID").toString())) {
					UnsortedMidFielders.put(Integer.toString(j), Float.parseFloat(jsonobj_1.get("credit").toString()));
				}

				if (jsonobj_1.get("playingStyleId").toString().equalsIgnoreCase(
						RP.loadPropertiesFile(FootballFilePath).get("FORWARDPLAYINGSTYLEID").toString())) {
					UnsortedForwards.put(Integer.toString(j), Float.parseFloat(jsonobj_1.get("credit").toString()));
				}

			}
			SortedDefenders = Sort.sortHashMapByValues(UnsortedDefenders);
			SortedGoalKeepers = Sort.sortHashMapByValues(UnsortedGoalKeepers);
			SortedMidFielders = Sort.sortHashMapByValues(UnsortedMidFielders);
			SortedForwards = Sort.sortHashMapByValues(UnsortedForwards);

		}

		File file = new File(FanTeamPlayerDeatils);
		boolean exists = file.exists();
		if (!exists) {
			file.createNewFile();
		} else {
			PrintWriter writer = new PrintWriter(FanTeamPlayerDeatils);
			writer.print("");
			writer.close();
		}

		FileWriter FileWrite = new FileWriter(FanTeamPlayerDeatils, true);
		FileWrite.write("\n" + "\"" + "players" + "\"" + ":[" + "\n");

		if (GameType.equalsIgnoreCase("CRICKET")) {
			for (int k = 0; k < 1; k++) {
				Set<String> key = SortedWicketKeepers.keySet();
				String TempKey = (new ArrayList<String>(SortedWicketKeepers.keySet())).get(k);
				JSONObject jsonobj_1 = (JSONObject) jsonarr_1.get(Integer.parseInt(TempKey));
				System.out.println(jsonobj_1.toString());
				FileWrite.write(jsonobj_1.toString() + "," + "\n");

			}

			for (int k = 0; k < 2; k++) {
				Set<String> key = SortedBatsman.keySet();
				String TempKey = (new ArrayList<String>(SortedBatsman.keySet())).get(k);
				JSONObject jsonobj_1 = (JSONObject) jsonarr_1.get(Integer.parseInt(TempKey));
				FileWrite.write(jsonobj_1.toString() + "," + "\n");
				if (k == 1)
					CaptainID = jsonobj_1.get("id").toString();

			}

			for (int k = 0; k < 1; k++) {
				Set<String> key = SortedBowlers.keySet();
				String TempKey = (new ArrayList<String>(SortedBowlers.keySet())).get(k);
				JSONObject jsonobj_1 = (JSONObject) jsonarr_1.get(Integer.parseInt(TempKey));
				FileWrite.write(jsonobj_1.toString() + "," + "\n");

			}

			for (int k = 0; k < 2; k++) {
				Set<String> key = SortedAllRounders.keySet();
				String TempKey = (new ArrayList<String>(SortedAllRounders.keySet())).get(k);
				JSONObject jsonobj_1 = (JSONObject) jsonarr_1.get(Integer.parseInt(TempKey));
				FileWrite.write(jsonobj_1.toString() + "," + "\n");

			}
		}

		if (GameType.equalsIgnoreCase("KABADDI")) {
			for (int k = 0; k < 2; k++) {
				Set<String> key = SortedDefenders.keySet();
				String TempKey = (new ArrayList<String>(SortedDefenders.keySet())).get(k);
				JSONObject jsonobj_1 = (JSONObject) jsonarr_1.get(Integer.parseInt(TempKey));
				System.out.println(jsonobj_1.toString());
				FileWrite.write(jsonobj_1.toString() + "," + "\n");

			}

			for (int k = 0; k < 1; k++) {
				Set<String> key = SortedAllRounders.keySet();
				String TempKey = (new ArrayList<String>(SortedAllRounders.keySet())).get(k);
				JSONObject jsonobj_1 = (JSONObject) jsonarr_1.get(Integer.parseInt(TempKey));
				FileWrite.write(jsonobj_1.toString() + "," + "\n");
				if (k == 0)
					CaptainID = jsonobj_1.get("id").toString();

			}

			for (int k = 0; k < 1; k++) {
				Set<String> key = SortedRaiders.keySet();
				String TempKey = (new ArrayList<String>(SortedRaiders.keySet())).get(k);
				JSONObject jsonobj_1 = (JSONObject) jsonarr_1.get(Integer.parseInt(TempKey));
				FileWrite.write(jsonobj_1.toString() + "," + "\n");

			}

		}

		if (GameType.equalsIgnoreCase("FOOTBALL")) {
			for (int k = 0; k < 2; k++) {
				Set<String> key = SortedDefenders.keySet();
				String TempKey = (new ArrayList<String>(SortedDefenders.keySet())).get(k);
				JSONObject jsonobj_1 = (JSONObject) jsonarr_1.get(Integer.parseInt(TempKey));
				System.out.println(jsonobj_1.toString());
				FileWrite.write(jsonobj_1.toString() + "," + "\n");

			}

			for (int k = 0; k < 2; k++) {
				Set<String> key = SortedMidFielders.keySet();
				String TempKey = (new ArrayList<String>(SortedMidFielders.keySet())).get(k);
				JSONObject jsonobj_1 = (JSONObject) jsonarr_1.get(Integer.parseInt(TempKey));
				FileWrite.write(jsonobj_1.toString() + "," + "\n");

			}

			for (int k = 0; k < 1; k++) {
				Set<String> key = SortedGoalKeepers.keySet();
				String TempKey = (new ArrayList<String>(SortedGoalKeepers.keySet())).get(k);
				JSONObject jsonobj_1 = (JSONObject) jsonarr_1.get(Integer.parseInt(TempKey));
				FileWrite.write(jsonobj_1.toString() + "," + "\n");
				if (k == 0)
					CaptainID = jsonobj_1.get("id").toString();

			}

			for (int k = 0; k < 1; k++) {
				Set<String> key = SortedForwards.keySet();
				String TempKey = (new ArrayList<String>(SortedForwards.keySet())).get(k);
				JSONObject jsonobj_1 = (JSONObject) jsonarr_1.get(Integer.parseInt(TempKey));
				FileWrite.write(jsonobj_1.toString() + "," + "\n");

			}

		}

		try {
			text = new String(Files.readAllBytes(Paths.get(RealTeamBPlayerDeatils)));
		} catch (IOException e) {
			e.printStackTrace();
		}

		jobj = new JSONObject(text);
		jsonarr_1 = (JSONArray) jobj.get("players");

		if (GameType.equalsIgnoreCase("CRICKET")) {

			UnsortedBowlers = new HashMap<String, Float>();
			UnsortedWicketKeepers = new HashMap<String, Float>();
			UnsortedBatsman = new HashMap<String, Float>();
			UnsortedAllRounders = new HashMap<String, Float>();
			SortedBowlers = new HashMap<String, Float>();
			SortedAllRounders = new HashMap<String, Float>();
			SortedBatsman = new HashMap<String, Float>();
			SortedWicketKeepers = new HashMap<String, Float>();

			for (int j = 0; j < jsonarr_1.length(); j++) {

				JSONObject jsonobj_1 = (JSONObject) jsonarr_1.get(j);
				System.out.println(jsonobj_1.toString());

				if (jsonobj_1.get("playingStyleId").toString().equalsIgnoreCase(
						RP.loadPropertiesFile(CricketFilePath).get("BOWLERPLAYINGSTYLEID").toString())) {
					UnsortedBowlers.put(Integer.toString(j), Float.parseFloat(jsonobj_1.get("credit").toString()));
				}

				if (jsonobj_1.get("playingStyleId").toString().equalsIgnoreCase(
						RP.loadPropertiesFile(CricketFilePath).get("WICKETKEEPERPLAYINGSTYLEID").toString())) {
					UnsortedWicketKeepers.put(Integer.toString(j),
							Float.parseFloat(jsonobj_1.get("credit").toString()));
				}
				if (jsonobj_1.get("playingStyleId").toString().equalsIgnoreCase(
						RP.loadPropertiesFile(CricketFilePath).get("BATSMANPLAYINGSTYLEID").toString())) {
					UnsortedBatsman.put(Integer.toString(j), Float.parseFloat(jsonobj_1.get("credit").toString()));
				}
				if (jsonobj_1.get("playingStyleId").toString().equalsIgnoreCase(
						RP.loadPropertiesFile(CricketFilePath).get("ALLROUNDERPLAYINGSTYLEID").toString())) {
					UnsortedAllRounders.put(Integer.toString(j), Float.parseFloat(jsonobj_1.get("credit").toString()));
				}
			}
			SortedBowlers = Sort.sortHashMapByValues(UnsortedBowlers);
			SortedAllRounders = Sort.sortHashMapByValues(UnsortedAllRounders);
			SortedBatsman = Sort.sortHashMapByValues(UnsortedBatsman);
			SortedWicketKeepers = Sort.sortHashMapByValues(UnsortedWicketKeepers);
		}

		if (GameType.equalsIgnoreCase("CRICKET")) {
			for (int k = 0; k < 2; k++) {
				Set<String> key = SortedBatsman.keySet();
				String TempKey = (new ArrayList<String>(SortedBatsman.keySet())).get(k);
				JSONObject jsonobj_1 = (JSONObject) jsonarr_1.get(Integer.parseInt(TempKey));
				FileWrite.write(jsonobj_1.toString() + "," + "\n");

			}

			for (int k = 0; k < 2; k++) {
				Set<String> key = SortedBowlers.keySet();
				String TempKey = (new ArrayList<String>(SortedBowlers.keySet())).get(k);
				JSONObject jsonobj_1 = (JSONObject) jsonarr_1.get(Integer.parseInt(TempKey));
				FileWrite.write(jsonobj_1.toString() + "," + "\n");
				if (k == 0)
					ViceCaptainID = jsonobj_1.get("id").toString();

			}

			for (int k = 0; k < 1; k++) {
				Set<String> key = SortedAllRounders.keySet();
				String TempKey = (new ArrayList<String>(SortedAllRounders.keySet())).get(k);
				JSONObject jsonobj_1 = (JSONObject) jsonarr_1.get(Integer.parseInt(TempKey));
				if (k == 0)
					FileWrite.write(jsonobj_1.toString() + "\n" + "]" + "\n" + "}");
				else
					FileWrite.write(jsonobj_1.toString() + "," + "\n");

			}

		}

		if (GameType.equalsIgnoreCase("KABADDI")) {

			UnsortedDefenders = new HashMap<String, Float>();
			UnsortedRaiders = new HashMap<String, Float>();
			UnsortedAllRounders = new HashMap<String, Float>();
			SortedDefenders = new HashMap<String, Float>();
			SortedAllRounders = new HashMap<String, Float>();
			SortedRaiders = new HashMap<String, Float>();

			for (int j = 0; j < jsonarr_1.length(); j++) {

				JSONObject jsonobj_1 = (JSONObject) jsonarr_1.get(j);
				System.out.println(jsonobj_1.toString());

				if (jsonobj_1.get("playingStyleId").toString().equalsIgnoreCase(
						RP.loadPropertiesFile(KabaddiFilePath).get("DEFENDERPLAYINGSTYLEID").toString())) {
					UnsortedDefenders.put(Integer.toString(j), Float.parseFloat(jsonobj_1.get("credit").toString()));
				}

				if (jsonobj_1.get("playingStyleId").toString().equalsIgnoreCase(
						RP.loadPropertiesFile(KabaddiFilePath).get("RAIDERPLAYINGSTYLEID").toString())) {
					UnsortedRaiders.put(Integer.toString(j), Float.parseFloat(jsonobj_1.get("credit").toString()));
				}
				if (jsonobj_1.get("playingStyleId").toString().equalsIgnoreCase(
						RP.loadPropertiesFile(KabaddiFilePath).get("ALLROUNDERPLAYINGSTYLEID").toString())) {
					UnsortedAllRounders.put(Integer.toString(j), Float.parseFloat(jsonobj_1.get("credit").toString()));
				}

			}
			SortedDefenders = Sort.sortHashMapByValues(UnsortedDefenders);
			SortedAllRounders = Sort.sortHashMapByValues(UnsortedAllRounders);
			SortedRaiders = Sort.sortHashMapByValues(UnsortedRaiders);
		}

		if (GameType.equalsIgnoreCase("KABADDI")) {
			for (int k = 0; k < 1; k++) {
				Set<String> key = SortedDefenders.keySet();
				String TempKey = (new ArrayList<String>(SortedDefenders.keySet())).get(k);
				JSONObject jsonobj_1 = (JSONObject) jsonarr_1.get(Integer.parseInt(TempKey));
				FileWrite.write(jsonobj_1.toString() + "," + "\n");
				if (k == 0)
					ViceCaptainID = jsonobj_1.get("id").toString();

			}

			for (int k = 0; k < 1; k++) {
				Set<String> key = SortedAllRounders.keySet();
				String TempKey = (new ArrayList<String>(SortedAllRounders.keySet())).get(k);
				JSONObject jsonobj_1 = (JSONObject) jsonarr_1.get(Integer.parseInt(TempKey));
				FileWrite.write(jsonobj_1.toString() + "," + "\n");

			}

			for (int k = 0; k < 1; k++) {
				Set<String> key = SortedRaiders.keySet();
				String TempKey = (new ArrayList<String>(SortedRaiders.keySet())).get(k);
				JSONObject jsonobj_1 = (JSONObject) jsonarr_1.get(Integer.parseInt(TempKey));
				if (k == 0)
					FileWrite.write(jsonobj_1.toString() + "\n" + "]" + "\n" + "}");
				else
					FileWrite.write(jsonobj_1.toString() + "," + "\n");

			}

		}

		if (GameType.equalsIgnoreCase("FOOTBALL")) {

			UnsortedDefenders = new HashMap<String, Float>();
			UnsortedMidFielders = new HashMap<String, Float>();
			UnsortedForwards = new HashMap<String, Float>();
			SortedForwards = new HashMap<String, Float>();
			SortedMidFielders = new HashMap<String, Float>();
			SortedForwards = new HashMap<String, Float>();

			for (int j = 0; j < jsonarr_1.length(); j++) {

				JSONObject jsonobj_1 = (JSONObject) jsonarr_1.get(j);
				System.out.println(jsonobj_1.toString());

				if (jsonobj_1.get("playingStyleId").toString().equalsIgnoreCase(
						RP.loadPropertiesFile(FootballFilePath).get("DEFENDERPLAYINGSTYLEID").toString())) {
					UnsortedDefenders.put(Integer.toString(j), Float.parseFloat(jsonobj_1.get("credit").toString()));
				}

				if (jsonobj_1.get("playingStyleId").toString().equalsIgnoreCase(
						RP.loadPropertiesFile(FootballFilePath).get("MIDFIELDERPLAYINGSTYLEID").toString())) {
					UnsortedMidFielders.put(Integer.toString(j), Float.parseFloat(jsonobj_1.get("credit").toString()));
				}

				if (jsonobj_1.get("playingStyleId").toString().equalsIgnoreCase(
						RP.loadPropertiesFile(FootballFilePath).get("FORWARDPLAYINGSTYLEID").toString())) {
					UnsortedForwards.put(Integer.toString(j), Float.parseFloat(jsonobj_1.get("credit").toString()));
				}
			}
			SortedDefenders = Sort.sortHashMapByValues(UnsortedDefenders);
			SortedMidFielders = Sort.sortHashMapByValues(UnsortedMidFielders);
			SortedForwards = Sort.sortHashMapByValues(UnsortedForwards);
		}

		if (GameType.equalsIgnoreCase("FOOTBALL")) {
			for (int k = 0; k < 2; k++) {
				Set<String> key = SortedDefenders.keySet();
				String TempKey = (new ArrayList<String>(SortedDefenders.keySet())).get(k);
				JSONObject jsonobj_1 = (JSONObject) jsonarr_1.get(Integer.parseInt(TempKey));
				FileWrite.write(jsonobj_1.toString() + "," + "\n");

			}

			for (int k = 0; k < 2; k++) {
				Set<String> key = SortedMidFielders.keySet();
				String TempKey = (new ArrayList<String>(SortedMidFielders.keySet())).get(k);
				JSONObject jsonobj_1 = (JSONObject) jsonarr_1.get(Integer.parseInt(TempKey));
				FileWrite.write(jsonobj_1.toString() + "," + "\n");

			}

			for (int k = 0; k < 1; k++) {
				Set<String> key = SortedForwards.keySet();
				String TempKey = (new ArrayList<String>(SortedForwards.keySet())).get(k);
				JSONObject jsonobj_1 = (JSONObject) jsonarr_1.get(Integer.parseInt(TempKey));
				if (k == 0)
					ViceCaptainID = jsonobj_1.get("id").toString();
				if (k == 0)
					FileWrite.write(jsonobj_1.toString() + "\n" + "]" + "\n" + "}");
				else
					FileWrite.write(jsonobj_1.toString() + "," + "\n");

			}

		}

		FileWrite.flush();

		text = new String(Files.readAllBytes(Paths.get(FanTeamPlayerDeatils)));

		if (GameType.equalsIgnoreCase("CRICKET")) {
			Bjson = "{" + "\"" + "name" + "\"" + " : " + "\"" + "Test Fan Team" + "\"" + "," + "\"" + "matchId" + "\""
					+ " : " + RP.loadPropertiesFile(MatchAndLeagueCreationFilePath).get("CRICKETMATCHID") + "," + "\""
					+ "leagueId" + "\"" + " : "
					+ RP.loadPropertiesFile(MatchAndLeagueCreationFilePath).get("CRICKETLEAGUEID") + "," + "\""
					+ "seriesId" + "\"" + " : "
					+ RP.loadPropertiesFile(SeriesCreationFilePath).get("INTERNATIONALSERIESID") + "," + "\""
					+ "captain" + "\"" + " : " + CaptainID + "," + "\"" + "viceCaptain" + "\"" + " : " + ViceCaptainID
					+ "," + "\"" + "userId" + "\"" + " : " + RP.loadPropertiesFile(ConfigFilePath).get("USERID") + ","
					+ text;
		}

		if (GameType.equalsIgnoreCase("KABADDI")) {
			Bjson = "{" + "\"" + "name" + "\"" + " : " + "\"" + "Test Fan Team" + "\"" + "," + "\"" + "matchId" + "\""
					+ " : " + RP.loadPropertiesFile(MatchAndLeagueCreationFilePath).get("KABADDIMATCHID") + "," + "\""
					+ "leagueId" + "\"" + " : "
					+ RP.loadPropertiesFile(MatchAndLeagueCreationFilePath).get("KABADDILEAGUEID") + "," + "\""
					+ "seriesId" + "\"" + " : " + RP.loadPropertiesFile(SeriesCreationFilePath).get("DOMESTICSERIESID")
					+ "," + "\"" + "captain" + "\"" + " : " + CaptainID + "," + "\"" + "viceCaptain" + "\"" + " : "
					+ ViceCaptainID + "," + "\"" + "userId" + "\"" + " : "
					+ RP.loadPropertiesFile(ConfigFilePath).get("USERID") + "," + text;
		}

		if (GameType.equalsIgnoreCase("FOOTBALL")) {
			Bjson = "{" + "\"" + "name" + "\"" + " : " + "\"" + "Test Fan Team" + "\"" + "," + "\"" + "matchId" + "\""
					+ " : " + RP.loadPropertiesFile(MatchAndLeagueCreationFilePath).get("FOOTBALLMATCHID") + "," + "\""
					+ "leagueId" + "\"" + " : "
					+ RP.loadPropertiesFile(MatchAndLeagueCreationFilePath).get("FOOTBALLLEAGUEID") + "," + "\""
					+ "seriesId" + "\"" + " : "
					+ RP.loadPropertiesFile(SeriesCreationFilePath).get("INTERNATIONALSERIESID") + "," + "\""
					+ "captain" + "\"" + " : " + CaptainID + "," + "\"" + "viceCaptain" + "\"" + " : " + ViceCaptainID
					+ "," + "\"" + "userId" + "\"" + " : " + RP.loadPropertiesFile(ConfigFilePath).get("USERID") + ","
					+ text;
		}

		System.out.println("Test is " + Bjson);

		Response JsonResponse = given().log().all().header("Content-Type", table.get("Hcontent-Type")).body(Bjson)
				.when().post("/ls/api/lobby/fanteam").then().extract().response();

		int StatusCode = JsonResponse.getStatusCode();

		jobj = new JSONObject(JsonResponse.asString());
		WP.WritePropertiesFile(ConfigFilePath, "FANTEAMID", jobj.get("id").toString());
		WP.WritePropertiesFile(ConfigFilePath, "FANTEAMNAME", jobj.get("name").toString());

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