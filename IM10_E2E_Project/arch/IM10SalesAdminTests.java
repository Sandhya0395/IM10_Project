package test_cases;

import ExcelResults.ExcelReportGenerator;
import TestData.IM10SATestDataReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.ConfigFileRead;
import pages.IM10LoginPage;
import pages.IM10SalesAdmin;
import pages.LoginUtility;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;

import static pages.WaitUtility.sleep;

public class IM10SalesAdminTests {
    public WebDriver driver;
    public HashMap<Integer, Object[]> map;
    ConfigFileRead configReader;
    IM10LoginPage IM10Login;
    IM10SATestDataReader DataRead;
    IM10SalesAdmin IM10SalesAdmin;

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
        IM10SalesAdmin = new IM10SalesAdmin(driver);

        DataRead = new IM10SATestDataReader();

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

    /*
     * Test Case: 1
     *
     * Summary: Verify the Login for IM10SalesAdmin
     *
     * Prerequisite: 1. User must have valid URL
     *               2. Valid login credentials to perform the given test
     *
     * Steps: 1. Enter Email, Password
     *        2. Click on Submit button
     *
     * Expected Result: IM10 Super Admin must be logged In successfully
     *
     * */

    @Test(priority = 0)
    public void testLogin() throws IOException, InterruptedException {
        LoginUtility.login(driver,"SALES_ADMIN");

        // Reading test data and performing login test
        IM10SATestDataReader.readTestData("D:\\Automation Testing\\PageObjectModel\\test data\\AddPlayerDetails.xlsx",0);

        String getExpResultForLogin = configReader.getExpResultForLogin();

        String loginTitle = driver.getTitle();
        System.out.print(loginTitle);

        // Asserting login result and storing test result
        try
        {
            Assert.assertEquals(loginTitle, "IM 10");
            System.out.print("Login Successfully");
            map.put("2", new Object[] { 1, "test_login ", "Super Admin", "Login Title",loginTitle,getExpResultForLogin,"Pass"});
        }
        catch (Throwable e)
        {
            System.out.print("Login not done" + e);
            map.put("2", new Object[] { 1, "test_login ", "Super Admin", "Login Title",loginTitle,getExpResultForLogin,"Fail"});
        }
    }

    /*
     * Test Case: 2
     *
     * Summary: Verify the functionality of adding a player in the application.
     *
     * Prerequisite: 1. Valid IM10 Super Admin login credentials
     *               2. Logged In with valid credentials
     *
     *
     * Steps: 1. Click on Player List from sidebar menu
     *        2. Click on ADD button for adding new player
     *        3. Enter/ Select First Name, Last Name, Sport, Date of Birth, Address, ProfilePhoto , Aadhaar Card , PAN Card ,Voting Card , Driving License, Bank Account No., PAN Card No.
     *        4. Click on Submit button for new player details
     *        5. Check for Save Action pop-up message
     *        6. Check User Added Successfully message on Save Action pop-up window
     *        7. Click on OK button
     *
     * Expected Result: New player details must be added successfully
     *
     * */



    @Test(priority = 1)
    public void testAddPlayer() throws InterruptedException
    {
        // Performing test to add a player
        sleep(1000);
        IM10SalesAdmin.clickPlayerList(driver);
        IM10SalesAdmin.clickAddButton(driver);
        IM10SalesAdmin.enterFirstName(driver);
        IM10SalesAdmin.enterLastName(driver);
        sleep(1000);
        IM10SalesAdmin.clickSport(driver);
        sleep(1000);
        IM10SalesAdmin.selectSportId(driver);
        sleep(1000);
        IM10SalesAdmin.enterDOB(driver);
        sleep(1000);
        IM10SalesAdmin.enterAddress(driver);
        sleep(1000);
        IM10SalesAdmin.selectProfilePhoto(driver);
        sleep(1000);
        IM10SalesAdmin.selectCropProfilePic(driver);
        sleep(2000);
        IM10SalesAdmin.clickSaveCrop(driver);
        sleep(2000);
        IM10SalesAdmin.selectAadhaarCard(driver);
        sleep(1000);
        IM10SalesAdmin.selectPANCard(driver);
        sleep(1000);
        IM10SalesAdmin.selectVotingCard(driver);
        sleep(1000);
        IM10SalesAdmin.selectDrivingLicense(driver);
        sleep(1000);
        IM10SalesAdmin.enterBankAccNo(driver);
        sleep(1000);
        IM10SalesAdmin.enterPANCardNo(driver);
        sleep(1000);
        IM10SalesAdmin.clickSubmit(driver);
        sleep(1000);

        String saveActionMsg = IM10SalesAdmin.getSaveActionMsg(driver);
        String expectedMessage = "Player Added Successfully!";
        sleep(1000);

        // Asserting add player result and storing test result
        try
        {
            Assert.assertEquals(saveActionMsg, expectedMessage);
            map.put("3", new Object[] { 2, "test_add_player", "Sales Person Admin", "Save Action Message",saveActionMsg,saveActionMsg, "Pass"});
        }
        catch (Throwable e)
        {
            System.out.println("Fail to add player" + e);
            map.put("3", new Object[] { 2, "test_add_player", "Sales Person Admin", "Save Action Message",saveActionMsg,saveActionMsg, "Fail"});
        }
        IM10SalesAdmin.clickOkAP(driver);
    }

    @Test(priority = 2)
    public void testDeletePlayer()
    {
        IM10SalesAdmin.clickDelete(driver);
        sleep(1000);
        IM10SalesAdmin.clickYes(driver);
        sleep(1000);

        String deleteAction = IM10SalesAdmin.getDeleteActionMsg(driver);
        String expectedMessage = "Player Deleted Successfully!";

        try
        {
            Assert.assertEquals(deleteAction, expectedMessage);
            map.put("4", new Object[] { 3, "test_add_player", "Sales Person Admin", "Delete Action Message", deleteAction, deleteAction, "Pass"});
        }
        catch (Throwable e)
        {
            System.out.println("Fail to delete player" + e);
            map.put("4", new Object[] { 3, "test_add_player", "Sales Person Admin", "Save Action Message",deleteAction,deleteAction, "Fail"});
        }
        IM10SalesAdmin.clickOkDP(driver);
        sleep(2000);
    }
    @Test(priority = 3)
    public void testLogOut()
    {
        IM10SalesAdmin.clickAccountCircle(driver);
        sleep(2000);
        IM10SalesAdmin.clickLogOut(driver);
        sleep(2000);
    }

    // Closing all browser windows and generating test report
    @AfterClass
    public void testCleanup() throws IOException
    {
        driver.quit();
        ExcelReportGenerator.generateExcelReport("TestReport","IM10SalesAdminExcelReport.xls", "D:\\Automation Testing\\PageObjectModel", map);
    }
}
