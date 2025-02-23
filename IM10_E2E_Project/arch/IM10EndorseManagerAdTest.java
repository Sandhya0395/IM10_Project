package test_cases;

import ExcelResults.ExcelReportGenerator;
import TestData.IM10EMATestDataReader;
import TestData.IM10SATestDataReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.*;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;

import static pages.WaitUtility.sleep;

public class IM10EndorseManagerAdTest {
    public WebDriver driver;
    public HashMap<String, Object[]> map;
    ConfigFileRead configReader;
    IM10LoginPage IM10Login;
    IM10EMATestDataReader DataRead;
    IM10EndorseManagerAdmin IM10EndorseManagerAdmin;

    /*
     * Summary: Environment Setup for the IM10 Super Admin
     *
     * Prerequisite: 1. Chrome browser must be available
     *               2. All dependencies must be installed
     *
     * Steps: 1. Maximize the browser window.
     *        2. Set implicit wait for 10 seconds.
     *        3. Navigate to the login page of the IM10 Web App.
     *
     *
     * Expected Result: Chrome browser must be open and maximize
     *                  IM10 Login page must open successfully
     *
     *
     * */

    @BeforeClass
    public void testSetup()
    {
        // Initializing WebDriver and other necessary objects
        driver = new ChromeDriver();

        configReader = new ConfigFileRead();

        IM10Login = new IM10LoginPage(driver);
        IM10EndorseManagerAdmin = new IM10EndorseManagerAdmin(driver);

        DataRead = new IM10EMATestDataReader();

        // Reading configuration data and setting up the initial environment
        String url = configReader.getURL();
        long implicitWait = configReader.getImplicitlyWait();

        // To maximize browser
        driver.manage().window().maximize();
        // Implicit wait
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(implicitWait));
        // To open IM10 site

        driver.get(url);

        // Initializing data structure to store test results
        map = new HashMap<>();
        map.put("1", new Object[] { "Test_Order", "Test", "Admin_Name", "Pass_Criteria", "Actual_Result", "Expected_Result", "Result"});

    }

    @Test(priority = 0)
    public void testLogin() throws IOException, InterruptedException {
        LoginUtility.login(driver,"ENDORSEMENT_MANAGER_ADMIN");

        // Reading test data and performing login test
        IM10EMATestDataReader.readTestData("D:\\Automation Testing\\PageObjectModel\\test data\\AddPlayerEndorsementDetails.xlsx",0);

        String getExpResultForLogin = configReader.getExpResultForLogin();

        String loginTitle = driver.getTitle();
        System.out.print(loginTitle);

        // Asserting login result and storing test result
        try
        {
            Assert.assertEquals(loginTitle, "IM 10");
            System.out.print("Login Successfully");
            map.put("2", new Object[] { 1, "test_login ", "Player Endorsement Admin", "Login Title",loginTitle,getExpResultForLogin,"Pass"});
        }
        catch (Throwable e)
        {
            System.out.print("Login not done" + e);
            map.put("2", new Object[] { 1, "test_login ", "Player Endorsement Admin", "Login Title",loginTitle,getExpResultForLogin,"Fail"});
        }
    }



    @Test(priority = 1)
    public void testAddPlayerEndorse() throws InterruptedException
    {
        // Performing test to add a player
        IM10EndorseManagerAdmin.clickPlayer(driver);
        sleep(1000);
        IM10EndorseManagerAdmin.clickPlayerEndorsement(driver);
        sleep(1000);
        IM10EndorseManagerAdmin.clickAdd(driver);
        sleep(1000);
        IM10EndorseManagerAdmin.clickCompanyName(driver);
        sleep(1000);
        IM10EndorseManagerAdmin.selectCompanyId(driver);
        sleep(2000);
        IM10EndorseManagerAdmin.clickEndorseType(driver);
        sleep(1000);
        IM10EndorseManagerAdmin.selectEndorseTypeId(driver);
        sleep(1000);
        IM10EndorseManagerAdmin.enterStartDate(driver);
        sleep(1000);
        IM10EndorseManagerAdmin.enterEndDate(driver);
        sleep(1000);
        IM10EndorseManagerAdmin.enterFinalPrice(driver);
        sleep(1000);
        IM10EndorseManagerAdmin.enterNotes(driver);
        sleep(1000);
        IM10EndorseManagerAdmin.clickSubmit(driver);
        sleep(1000);

        String saveActionMsg = IM10EndorseManagerAdmin.getSaveActionMsg(driver);
        String expectedMessage = "Player Endorsement Added Successfully!";
        sleep(1000);

        // Asserting add player result and storing test result
        try
        {
            Assert.assertEquals(saveActionMsg, expectedMessage);
            map.put("3", new Object[] { 2, "test_add_player_endorse", "Player Endorse Manager Admin", "Save Action Message",saveActionMsg,saveActionMsg, "Pass"});
        }
        catch (Throwable e)
        {
            System.out.println("Fail to add player" + e);
            map.put("3", new Object[] { 2, "test_add_player_endorse", "Player Endorse Manager Admin", "Save Action Message",saveActionMsg,saveActionMsg, "Fail"});
        }
        IM10EndorseManagerAdmin.clickOk(driver);
    }

    @Test(priority = 2)
    public void testDeletePlayer()
    {
        IM10EndorseManagerAdmin.clickDelete(driver);
        sleep(1000);
        IM10EndorseManagerAdmin.clickYes(driver);
        sleep(1000);

        String deleteAction = IM10EndorseManagerAdmin.getDeleteActionMsg(driver);
        String expectedMessage = "Player Endorsement Deleted Successfully!";

        try
        {
            Assert.assertEquals(deleteAction, expectedMessage);
            map.put("4", new Object[] { 3, "test_add_player_endorsement", "Player Endorsement Admin", "Delete Action Message", deleteAction, deleteAction, "Pass"});
        }
        catch (Throwable e)
        {
            System.out.println("Fail to delete player" + e);
            map.put("4", new Object[] { 3, "test_add_player_endorsement", "Player Endorsement Admin", "Save Action Message",deleteAction,deleteAction, "Fail"});
        }
        IM10EndorseManagerAdmin.clickOkDC(driver);
        sleep(2000);
    }
    @Test(priority = 3)
    public void testLogOut()
    {
        IM10EndorseManagerAdmin.clickAccountCircle(driver);
        sleep(2000);
        IM10EndorseManagerAdmin.clickLogOut(driver);
        sleep(2000);
    }

    // Closing all browser windows and generating test report
    @AfterClass
    public void testCleanup() throws IOException
    {
        driver.quit();
        ExcelReportGenerator.generateExcelReport("TestReport","IM10EndorsementManagerAdminExcelReport.xls", "D:\\Automation Testing\\PageObjectModel", map);
    }
}
