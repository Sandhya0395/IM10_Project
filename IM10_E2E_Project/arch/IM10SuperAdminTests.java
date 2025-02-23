package test_cases;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;

import ExcelResults.ExcelReportGenerator;
import TestData.TestDataReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.*;

import static pages.WaitUtility.sleep;

public class IM10SuperAdminTests {
    public WebDriver driver;
    public HashMap<String, Object[]> map;
    ConfigFileRead configReader;
    IM10LoginPage IM10Login;
    TestDataReader DataReader;
    IM10SuperAdmin IM10SuperAdmin;

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

    // BeforeClass setup method
    @BeforeClass
    public void testSetup()
    {
        // Setting up WebDriver and necessary objects
        driver = new ChromeDriver();

        configReader = new ConfigFileRead();

        IM10Login = new IM10LoginPage(driver);
        IM10SuperAdmin = new IM10SuperAdmin(driver);

        DataReader = new TestDataReader();


        // Reading configuration data and setting up initial environment
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

    // Test case for login functionality
    @Test(priority = 0)
    public void testLogin() throws IOException, InterruptedException {
         LoginUtility.login(driver,"SUPER_ADMIN");

        // Reading test data and performing login test
        DataReader.readTestData("D:\\Automation Testing\\PageObjectModel\\test data\\AddUsers.xlsx",0);


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

    // Test case for adding a user
    @Test(priority = 1)
    public void testAddUser() throws InterruptedException
    {
        IM10SuperAdmin.clickUser(driver);
        IM10SuperAdmin.clickAdd(driver);
        IM10SuperAdmin.enterFirstName(driver);
        IM10SuperAdmin.enterLastName(driver);
        IM10SuperAdmin.enterEmailId(driver);
        IM10SuperAdmin.enterMobileNo(driver);
        IM10SuperAdmin.enterDOB(driver);
        sleep(1000);
        IM10SuperAdmin.clickRole(driver);
        sleep(1000);
        IM10SuperAdmin.selectRoleId(driver);
        IM10SuperAdmin.enterPassword(driver);
        IM10SuperAdmin.clickSubmit(driver);
        sleep(1000);
        String saveActionMsg = IM10SuperAdmin.getSaveActionMsg(driver);

        // Asserting add user result and storing test result
        try
        {
            Assert.assertEquals(saveActionMsg, "User Added Successfully!");
            map.put("3", new Object[] { 2, "test_add_user", "Super Admin", "Save Action Message",saveActionMsg,saveActionMsg, "Pass"});
        } catch (Throwable e) {
            System.out.println(e);
            map.put("3", new Object[] { 2, "test_add_user", "Super Admin", "Save Action Message",saveActionMsg,saveActionMsg, "Fail"});
        }
        IM10SuperAdmin.clickOk(driver);
    }

    // Test case for deleting a user
    @Test(priority = 2)
    public void testDeleteUser()
    {
        IM10SuperAdmin IM10SuperAdmin = new IM10SuperAdmin(driver);
        sleep(1000);
        IM10SuperAdmin.clickDelete(driver);
        sleep(1000);
        IM10SuperAdmin.clickYes(driver);
        sleep(1000);

    }

    @Test(priority = 3)
    public void testLogOut()
    {
        IM10SuperAdmin.clickAccountCircle(driver);
        sleep(2000);
        IM10SuperAdmin.clickLogOut(driver);
        sleep(2000);
    }

    // AfterClass method to clean up and generate test report
    @AfterClass
    public void testCleanup() throws IOException
    {
        // Closing all browser windows and generating test report
        driver.quit();
        ExcelReportGenerator.generateExcelReport("TestReport","IM10SuperAdminExcelReport.xls", "D:\\Automation Testing\\PageObjectModel", map);
    }
}
