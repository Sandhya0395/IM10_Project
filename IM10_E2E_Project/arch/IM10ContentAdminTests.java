package test_cases;

import ExcelResults.ExcelReportGenerator;
import TestData.IM10CATestDataReader;
import TestData.IM10EMATestDataReader;
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
import java.util.NoSuchElementException;

import static pages.WaitUtility.sleep;

public class IM10ContentAdminTests {
    public WebDriver driver;
    public HashMap<String, Object[]> map;
    ConfigFileRead configReader;
    IM10LoginPage IM10Login;
    IM10CATestDataReader DataRead;
    IM10ContentAdmin IM10ContentAdmin;

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
        IM10ContentAdmin = new IM10ContentAdmin(driver);

        DataRead = new IM10CATestDataReader();

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
        LoginUtility.login(driver,"CONTENT_ADMIN");

        // Reading test data and performing login test
        IM10EMATestDataReader.readTestData("D:\\Automation Testing\\PageObjectModel\\test data\\AddContentDetails.xlsx",0);

        String getExpResultForLogin = configReader.getExpResultForLogin();

        String loginTitle = driver.getTitle();
        System.out.print(loginTitle);

        // Asserting login result and storing test result
        try
        {
            Assert.assertEquals(loginTitle, "IM 10");
            System.out.print("Login Successfully");
            map.put("2", new Object[] { 1, "test_login ", "Content Admin", "Login Title",loginTitle,getExpResultForLogin,"Pass"});
        }
        catch (Throwable e)
        {
            System.out.print("Login not done" + e);
            map.put("2", new Object[] { 1, "test_login ", "Content Admin", "Login Title",loginTitle,getExpResultForLogin,"Fail"});
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
    public void testAddContent() throws InterruptedException
    {
        // Performing test to add a player
        IM10ContentAdmin.clickPlayer(driver); 
        sleep(1000);
        IM10ContentAdmin.clickContent(driver);
        sleep(1000);
        IM10ContentAdmin.clickAddButton(driver);
        sleep(1000);
        IM10ContentAdmin.enterTitle(driver);
        sleep(1000);
        IM10ContentAdmin.enterDescription(driver);
        sleep(1000);
        IM10ContentAdmin.clickContentType(driver);
        sleep(1000);
        IM10ContentAdmin.selectContentTypeId(driver);
        sleep(2000);
        IM10ContentAdmin.selectThumbnail(driver);
        sleep(2000);
        IM10ContentAdmin.selectContentPart1(driver);
        sleep(2000);
        IM10ContentAdmin.clickCategory(driver);
        sleep(1000);
        IM10ContentAdmin.selectCategoryId(driver);
        sleep(2000);
        IM10ContentAdmin.clickSubCategory(driver);
        sleep(1000);
        IM10ContentAdmin.selectSubCategoryId(driver);
        sleep(2000);
        IM10ContentAdmin.clickLanguage(driver);
        sleep(1000);
        IM10ContentAdmin.selectLanguageId(driver);
        sleep(2000);
        IM10ContentAdmin.clickSubmit(driver);
        sleep(1000);

        String saveActionMsg = IM10ContentAdmin.getSaveActionMsg(driver);

        // Asserting add player result and storing test result
        try
        {
            Assert.assertEquals(saveActionMsg, "Content Added Successfully!");
            map.put("3", new Object[] { 2, "test_add_content", "Content Admin", "Save Action Message",saveActionMsg,saveActionMsg, "Pass"});
        } catch (Throwable e) {
            System.out.println(e);
            map.put("3", new Object[] { 2, "test_add_content", "Content Admin", "Save Action Message",saveActionMsg,saveActionMsg, "Fail"});
        }
        IM10ContentAdmin.clickOkSaveAction(driver);
    }

    @Test(priority = 2)
    public void testDeletePlayer()
    {
        IM10ContentAdmin.clickDelete(driver);
        sleep(1000);
        IM10ContentAdmin.clickYes(driver);
        sleep(1000);

        String deleteAction = IM10ContentAdmin.getDeleteActionMsg(driver);
        String expectedMessage = "Content Deleted Successfully!";

        try
        {
            Assert.assertEquals(deleteAction, expectedMessage);
            map.put("4", new Object[] { 3, "test_delete_content", "Content Admin", "Delete Action Message", deleteAction, deleteAction, "Pass"});
        }
        catch (Throwable e)
        {
            System.out.println("Fail to delete content" + e);
            map.put("4", new Object[] { 3, "test_delete_content", "Content Admin", "Delete Action Message",deleteAction,deleteAction, "Fail"});
        }
        IM10ContentAdmin.clickOkDC(driver);
    }
    @Test(priority = 3)
    public void testLogOut() throws NoSuchElementException
    {
        IM10ContentAdmin.clickAccountCircle(driver);
        sleep(2000);
        IM10ContentAdmin.clickLogOut(driver);
        sleep(2000);
    }

    // Closing all browser windows and generating test report
    @AfterClass
    public void testCleanup() throws IOException
    {
        driver.quit();
        ExcelReportGenerator.generateExcelReport("TestReport","IM10ContentAdminExcelReport.xls", "D:\\Automation Testing\\PageObjectModel", map);
    }
}
