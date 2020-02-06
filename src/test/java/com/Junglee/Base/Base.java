package com.Junglee.Base;

import java.io.File;
import java.io.IOException;
//import java.io.StringWriter;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

//import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
//import org.openqa.selenium.By;
//import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

//import com.Junglee.RestUtils.Cookies;
import com.Junglee.SeleniumUtils.SeleniumContext;
import com.Junglee.Utilities.ExtentManager;
import com.Junglee.Utilities.ExtentTestManager;
import com.Junglee.Utilities.ReadPropertiesFiles;
import com.relevantcodes.extentreports.LogStatus;
import com.Junglee.Utilities.*;
import io.restassured.RestAssured;
import io.restassured.config.LogConfig;

public class Base {

	String Scenario = "Test Mode";
	static int count = 0;
	// public static WebDriver driver;
	// public Properties config = new Properties();
	public static Logger log = Logger.getLogger("devpinoyLogger");
	/*
	 * String spreadsheetId="14-7PNS2RzGrdvvx5VMBvRlZoyX_oHJ_JJtiPidrgbEs";
	 * String range="'Sprint 20'"; GoogleSheet sheetAPI = new GoogleSheet();
	 * public List<List<Object>> values; public ExcelReader excel = new
	 * ExcelReader(System.getProperty("user.dir")+
	 * "//src//test//resources//excel//testData.xlsx");
	 */
	String ConfigFilePath = "../APIAutoationFramework/src/test/resources/properties/Config.properties";
	public Date date = new Date();
	// public StringWriter writer;
	// public PrintStream captor;
	// public LogConfig originalLogConfig;
	public static Map<String, String> acegic = new LinkedHashMap<>();
	public static Map<String, String> token = new LinkedHashMap<>();
	public static Map<String, Map<String, String>> completeCookieWeb = new LinkedHashMap<>();
	public static Map<String, Map<String, String>> completeCookieWap = new LinkedHashMap<>();
	ReadPropertiesFiles RP = new ReadPropertiesFiles();

	// public static Integer PrioritySeriesCration;
	// public static Integer PriorityForMatchAndLeagueCreation;
	// public static Integer PriorityForMatchAndLeagueVisibility;
	// public static Integer PriorityForPrizeStructureCreation;
	// public static Integer PriorityForSimpleContestCreation;
	// public static Integer PriorityToFetchRealTeamPlayerDetails;
	// public static Integer PriorityForFanTeamCreation;

	@BeforeSuite(alwaysRun = true)
	public void beforeSuite() throws IOException {
		System.out.println("Before Suite");
		// PrioritySeriesCration = Integer
		// .parseInt(RP.loadPropertiesFile(ConfigFilePath).getProperty("PRIORITYFORSERIESCREATION"));
		// PriorityForMatchAndLeagueCreation = Integer
		// .parseInt(RP.loadPropertiesFile(ConfigFilePath).getProperty("PRIORITYFORMATCHANDLEAGUECREATION"));
		// PriorityForMatchAndLeagueVisibility = Integer
		// .parseInt(RP.loadPropertiesFile(ConfigFilePath).getProperty("PRIORITYFORMATCHANDLEAGUEVISIBILITY"));
		// PriorityForPrizeStructureCreation = Integer
		// .parseInt(RP.loadPropertiesFile(ConfigFilePath).getProperty("PRIORITYFORPRIZESTRUCTURECREATION"));
		// PriorityForSimpleContestCreation = Integer
		// .parseInt(RP.loadPropertiesFile(ConfigFilePath).getProperty("PRIORITYFORSIMPLECONTESTCREATION"));
		// PriorityToFetchRealTeamPlayerDetails = Integer
		// .parseInt(RP.loadPropertiesFile(ConfigFilePath).getProperty("PRIORITYTOFETCHREALTEAMPLAYERDETAILS"));
		// PriorityForFanTeamCreation = Integer
		// .parseInt(RP.loadPropertiesFile(ConfigFilePath).getProperty("PRIORITYFORFANTEAMCREATION"));
		String ResultFilePath = RP.loadPropertiesFile(ConfigFilePath).getProperty("RESULTFILEPATH");
		File file = new File(ResultFilePath);

		boolean exists = file.exists();

		if (exists) {
			file.delete();
			System.out.println("File Deleted Succesfully");
		} else
			System.out.println("File does not exists");
	}

	@BeforeTest
	public void beforeTest(ITestContext context) {

		if (count < 1) {
			if (!Scenario.equals("Test Mode")) {
				SeleniumContext.context = context;
				String Web = SeleniumContext.getTestLevelDriverRequired();
				if (Web.equalsIgnoreCase("NO")) {
					try {
						// ExtentManager.reset();
						ExtentTestManager.startTest(
								SeleniumContext.getAllContext().getCurrentXmlTest().getName() + ".Before Test");
						ExtentTestManager.getTest().log(LogStatus.INFO, "@Before Test", "Data");
						// new Cookies().addCookies();
					} finally {
						ExtentTestManager.endTest();
						ExtentManager.getInstance().flush();
						RestReset();
					}
				}
			}
			count++;
		}
	}

	@BeforeMethod(alwaysRun = true)
	public void beforeMethod(Method m, ITestContext context) {
		SeleniumContext.context = context;
		System.out.println("Before Mehod Executing for the method :- " + m.getName());
		String URi = SeleniumContext.getTestLevelURL();
		System.out.println(URi);
		String Rest = SeleniumContext.getTestLevelDriverRequired();
		if (Rest.equals("NO")) {
			System.out.println("Rest Assured");
			RestAssured.baseURI = URi;
			restProxy();
			RestAssured.config = RestAssured.config().logConfig(
					LogConfig.logConfig().defaultStream(AllDrive.getPrintStream()).enablePrettyPrinting(true));
			RestAssured.useRelaxedHTTPSValidation();
		} else {
			System.out.println("Load Driver");
			WebDriver driver;
			driver = AllDrive.getWebDriver();
			driver.get(URi);
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		}

		Class<? extends Object> className = m.getDeclaringClass();
		Test test = m.getAnnotation(Test.class);
		ExtentTestManager.startTest(className.getSimpleName() + "." + m.getName().toUpperCase(), test.description());
		ExtentTestManager.getTest()
				.assignCategory(this.getClass().getPackage().getName() + "." + className.getSimpleName());
		ExtentTestManager.getTest().log(LogStatus.INFO, "@Before Method", m.getName());
	}

	@AfterMethod(alwaysRun = true)
	public void afterMethod() {
		String Rest = SeleniumContext.getTestLevelDriverRequired();
		if (Rest.equals("NO")) {
			RestReset();
		}
		System.out.println("After Method");
	}

	@AfterSuite(alwaysRun = true)
	public void afterSuite(ITestContext context) {
		System.out.println("AfterSuite");
		// AllDrive.cleanUp();
		/*
		 * if(driver!=null) driver.quit();
		 */
		// log.debug("END");
		// ExtentManager.getInstance().flush();
		// ExtentManager.move(context.getName());
		// try {
		// Thread.sleep(1);
		// //SendMail.mail(Constant.to,Constant.cc,Constant.username,Constant.password,Constant.filename,Constant.Extentfilename);
		// } catch (Exception e) {
		// e.printStackTrace();
		// }
		// System.out.println("Test Count"+"Total:
		// "+context.getAllTestMethods().length);
		// System.out.println("Test Count"+"Passed:
		// "+context.getPassedTests().size());
		// System.out.println("Test Count"+"Failed:
		// "+context.getFailedTests().size());
		// System.out.println("Test Count"+"Skipped:
		// "+context.getSkippedTests().size());
	}

	@AfterTest(alwaysRun = true)
	public void afterTest(ITestContext context) {
		System.out.println("AfterTest " + context.getName());
		AllDrive.cleanUp();
		// /*if(driver!=null)
		// driver.quit();*/
		log.debug("END");
		ExtentManager.getInstance().flush();
		ExtentManager.move(context.getName());
		ExtentTestManager.endTest();
		// ExtentManager.reset();
		try {
			Thread.sleep(1);
			// SendMail.mail(Constant.to,Constant.cc,Constant.username,Constant.password,Constant.filename,Constant.Extentfilename);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Test Count" + "Total:  " + context.getAllTestMethods().length);
		System.out.println("Test Count" + "Passed:  " + context.getPassedTests().size());
		System.out.println("Test Count" + "Failed:  " + context.getFailedTests().size());
		System.out.println("Test Count" + "Skipped: " + context.getSkippedTests().size());
	}

	public static void RestReset() {
		AllDrive.cleanWriter();
		AllDrive.cleanPrintStream();
		RestAssured.baseURI = null;
		RestAssured.reset();
	}

	public static void restProxy() {
		if (SeleniumContext.getTestLevelRestProxy().equalsIgnoreCase("Yes"))
			RestAssured.proxy(8080);
	}
}
