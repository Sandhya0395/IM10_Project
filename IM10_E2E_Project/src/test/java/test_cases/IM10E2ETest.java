package test_cases;

import ExcelResults.ExcelReportGenerator;
import TestData.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.*;
import pages.IM10MarketingCampaigns;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static pages.WaitUtility.sleep;

public class IM10E2ETest {

    public WebDriver driver; // Declaration of WebDriver instance for browser automation
    public HashMap<Integer, Object[]> testData;// HashMap to store test data rows indexed by integers
    public HashMap<Integer, Object[]> summaryData;// HashMap to summarize test execution results indexed by integers
    public List<String> failedSuperAdminTests;// List to keep track of failed test methods for SUPER_ADMIN user
    public List<String> failedContentAdminTests;
    public List<String> failedEndorsementManagerAdminTests;
    public List<String> failedIM10SalesAdminTests;
    public List<String> failedSalesPersonAdminTests;
    public List<String> failedQAAdminTests;
    public List<String> failedMarketingCampaignsTests;

    public List<String> passedSuperAdminTests;// List to keep track of passed test methods for SUPER_ADMIN user
    public List<String> passedContentAdminTests;
    public List<String> passedEndorsementManagerAdminTests;
    public List<String> passedIM10SalesAdminTests;
    public List<String> passedSalesPersonAdminTests;
    public List<String> passedQAAdminTests;
    public List<String> passedMarketingCampaignsTests;
    ConfigFileRead configReader;// Instance of configuration file reader to fetch test configuration data
    //LighthouseUtility lighthouse;

    // Instances of different data reader classes for various test data sources
    IM10LoginPage IM10Login;
    IM10CATestDataReader DataRead;
    IM10SATestDataReader DataRead1;
    IM10EMATestDataReader DataRead2;
    IM10SalesPersonAdminDataReader DataRead3;
    IM10MarketingCampaignsDataReader DataRead4;

    SendEmail sendEmail;
    ExcelRead sendSummaryReport;

    // Instances of various user role classes for testing
    IM10SuperAdmin IM10SuperAdmin;
    IM10ContentAdmin IM10ContentAdmin;
    IM10SalesAdmin IM10SalesAdmin;
    IM10EndorseManagerAdmin IM10EndorseManagerAdmin;
    SalesPersonAdmin SalesPersonAdmin;
    IM10QualityAssurance IM10QualityAssurance;
    IM10MarketingCampaigns IM10MarketingCampaigns;

    // Integer variables to track current row for reporting
    int reportRow;

    // Integer variable to track current row for summary data
    int summaryRow;

    // Integer variable to track the order of tests executed
    int testOrder;

    // Boolean flag to indicate the status of SUPER_ADMIN tests
    boolean flagSuperAdmin;
    boolean flagContentAdmin;
    boolean flagEndorsementManagerAdmin;
    boolean flagIM10SalesAdmin;
    boolean flagSalesPersonAdmin;
    boolean flagQAAdmin;
    boolean flagMarketingCampaigns;

    // String variable to store the name of the current test method
    String getMethodName;

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
     * */

    // BeforeClass setup method
    @BeforeClass
    public void testSetup() throws IOException
    {
        //LighthouseUtility.chromeDebug();
        // Setting up WebDriver and necessary objects
        ChromeOptions options = new ChromeOptions();
        // options.setExperimentalOption("debuggerAddress", "127.0.0.1:9222"); // Initiate chrome driver on port 9222

        options.addArguments("--incognito");
        //options.addArguments("--headless"); // Uncomment to run tests in headless mode
        options.addArguments("--disable-gpu"); // Corrected argument for disabling GPU

        // Initializing ChromeDriver with configured options
        driver = new ChromeDriver(options);

        // Initializing configuration reader
        configReader = new ConfigFileRead();

        // Initializing page objects for various roles
        IM10Login = new IM10LoginPage(driver);
        IM10SuperAdmin = new IM10SuperAdmin(driver);
        IM10ContentAdmin = new IM10ContentAdmin(driver);
        IM10SalesAdmin = new IM10SalesAdmin(driver);
        IM10EndorseManagerAdmin = new IM10EndorseManagerAdmin(driver);
        SalesPersonAdmin = new SalesPersonAdmin(driver);
        IM10QualityAssurance = new IM10QualityAssurance(driver);
        IM10MarketingCampaigns = new IM10MarketingCampaigns(driver);


        // Initializing data readers for different test data sources
        DataRead = new IM10CATestDataReader();
        DataRead1 = new IM10SATestDataReader();
        DataRead2 = new IM10EMATestDataReader();
        DataRead3 = new IM10SalesPersonAdminDataReader();
        DataRead4 = new IM10MarketingCampaignsDataReader();
        sendEmail = new SendEmail();
        sendSummaryReport = new ExcelRead();

        // Reading configuration data
        String url = configReader.getURL();
        long implicitWait = configReader.getImplicitlyWait();

        // Maximizing browser window
        driver.manage().window().maximize();

        // Setting implicit wait time
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(implicitWait));

        // Opening the IM10 site
        driver.get(url);

        //LighthouseUtility.lighthouseAudit(url,"Login");

        // Initializing variables for reporting and testing
        reportRow = 0;
        summaryRow = 0;
        testOrder = 1;

        // Flag to track overall status of SUPER_ADMIN tests
        flagSuperAdmin = true;
        flagContentAdmin = true;
        flagEndorsementManagerAdmin = true;
        flagIM10SalesAdmin = true;
        flagSalesPersonAdmin =true;
        flagQAAdmin = true;
        flagMarketingCampaigns = true;

        // Initializing data structures to store test results
        testData = new HashMap<>();
        testData.put(reportRow++, new Object[]{"Test_Order", "Test", "Admin_Name", "Pass_Criteria", "Actual_Result", "Expected_Result", "Result"});

        summaryData = new HashMap<>();

        // Initializing list to track failed SUPER_ADMIN tests
        failedSuperAdminTests = new ArrayList<>();
        failedContentAdminTests = new ArrayList<>();
        failedEndorsementManagerAdminTests = new ArrayList<>();
        failedIM10SalesAdminTests = new ArrayList<>();
        failedSalesPersonAdminTests = new ArrayList<>();
        failedQAAdminTests = new ArrayList<>();
        failedMarketingCampaignsTests = new ArrayList<>();

        passedSuperAdminTests = new ArrayList<>();
        passedContentAdminTests = new ArrayList<>();
        passedEndorsementManagerAdminTests = new ArrayList<>();
        passedIM10SalesAdminTests = new ArrayList<>();
        passedSalesPersonAdminTests = new ArrayList<>();
        passedQAAdminTests = new ArrayList<>();
        passedMarketingCampaignsTests = new ArrayList<>();
    }


    /*
     * Test Case: 1
     *
     * Summary: Verify the Login for Super Admin
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

    // Test case for Super Admin login functionality
    @Test(priority = 1)
    public void testSALogin() throws InterruptedException, IOException {
        //Perform login as SUPER_ADMIN
        LoginUtility.login(driver, "SUPER_ADMIN");

        //LighthouseUtility.lighthouseAudit(driver.getCurrentUrl(),"Dashboard");
        //Reading expected result for login from configuration
        String getExpResultForLogin = configReader.getExpSAResultForLogin();

        //Retrieve the title of the current web page
        String loginTitle = driver.getTitle();
        getMethodName = new Object() {}.getClass().getEnclosingMethod().getName();
        //Asserting the actual login title matches the expected login title
        try
        {
            Assert.assertEquals(loginTitle, configReader.getExpSAResultForLogin());
            System.out.print("Login Successfully");

            //Storing test data for reporting purposes
            testData.put(reportRow++, new Object[]{testOrder++, "test_super_Admin_login ", "Super Admin", "Login Title", loginTitle, getExpResultForLogin, "Pass"});

            passedSuperAdminTests.add(getMethodName);
        }
        catch (Throwable e)
        {
            //Handling failure scenario if assertion fails
            System.out.print("Login not done" + e);

            //Storing test data with failure status for reporting
            testData.put(reportRow++, new Object[]{testOrder++, "test_super_Admin_login", "Super Admin", "Login Title", loginTitle, getExpResultForLogin, "Fail"});

            //Adding the failed test method name to the list of failed tests

            failedSuperAdminTests.add(getMethodName);

            //Setting flag to indicate test failure
            flagSuperAdmin = false;
        }
    }


    /*
     * Test Case: 2
     *
     * Summary: Verify Add User functionality for Super Admin
     *
     * Prerequisite: 1. User must have valid URL
     *               2. Valid login credentials to perform the given test
     *
     * Steps: 1. Select User from sidebar menu
     *        2. Click on ADD button
     *        3. Enter First Name, Last Name, Email ID, Mobile No., Date of Birth, Password and select Role
     *        4. Click on Submit button
     *
     *
     * Expected Result: User must be added successfully
     *
     */

    @Test(priority = 2)
    public void testSaAddUser() throws InterruptedException
    {

        // Initialization of variables to track test results
        String saveActionMsg = "";
        String expSaveActionMsg;
        Set<String> resultOfTestAddUser = new HashSet<>();
        Boolean flagResultOfTestAddUser = false;

        // Arrays containing test data for iteration
        String[] firstNames = {configReader.getFirstNameCA(), configReader.getFirstNameIM10SA(), configReader.getFirstNameEMA(), "TestFirstNameSPA", "TestFirstNameQA", "TestFirstNameMAC"};
        String[] lastNames = {configReader.getLastNameCA(), configReader.getLastNameIM10SA(), configReader.getLastNameEMA(), "TestLastNameSPA", "TestLastNameQA", "TestLastNameMAC"};
        String[] emails = {"testCA@gmail.com", "testIM10SA@gmail.com", "testEMA@gmail.com", "testSPA@gmail.com", "testQA@gmail.com", "testIM10MAC@gmail.com"};
        String[] mobileNumbers = {"9404230928", "9404230928", "9404230928", "9404230928", "9404230928", "9404230928"};
        String[] adminRoles = {configReader.getAdminRoleCA(), configReader.getAdminRoleIM10SA(), configReader.getAdminRoleEMA(), "Sales Person Admin", "Quality Assurance", "IM10 Marketing Campaigns"};

        // Number of iterations based on the size of the arrays (assuming they are of the same length)
        int length = firstNames.length;

        // Loop through each set of test data
        for (int i = 0; i < length; i++) {
            String firstName = firstNames[i];
            String lastName = lastNames[i]; // Assuming the arrays are synchronized
            String email = emails[i];
            String mobileNumber = mobileNumbers[i];
            String adminRole = adminRoles[i];

            // Output information about the current test data being processed
            System.out.println("Processing user with firstName: " + firstName);

            // Call method to perform the test action of adding a user
            addUser(firstName, lastName, email, mobileNumber, adminRole);

            // Example interactions with page objects and actions
            IM10SuperAdmin.clickUser(driver);
            sleep(3000);
            IM10SuperAdmin.clickAdd(driver);
            sleep(1000);

            IM10SuperAdmin.enterFirstName(driver, firstNames[i]); // Pass the current data element
            sleep(1000);
            IM10SuperAdmin.enterLastName(driver, lastNames[i]); // Pass the current data element
            IM10SuperAdmin.enterEmailId(driver, emails[i]); // Pass the current data element
            IM10SuperAdmin.enterMobileNo(driver, mobileNumbers[i]); // Pass the current data element

            IM10SuperAdmin.enterDOB(driver); // Assuming this is constant for all iterations
            sleep(5000);

            IM10SuperAdmin.clickRole(driver);
            sleep(1000);
            IM10SuperAdmin.selectRoleId(driver, adminRoles[i]);
            IM10SuperAdmin.enterPassword(driver);
            IM10SuperAdmin.clickSubmit(driver);
            sleep(5000);

            // Retrieve the result message after the action and expected message
            saveActionMsg = IM10SuperAdmin.getSaveActionMsg(driver);
            expSaveActionMsg = configReader.getExpectedAddSA();

            // Asserting the result of adding user and storing test result
            try {
                Assert.assertEquals(saveActionMsg, expSaveActionMsg);
                flagResultOfTestAddUser = true;
                resultOfTestAddUser.add(flagResultOfTestAddUser.toString());


            } catch (Throwable e) {
                // Catching and handling assertion errors
                System.out.println(e);
                resultOfTestAddUser.add(flagResultOfTestAddUser.toString());

            }

            sleep(5000);
            IM10SuperAdmin.clickOk(driver);
        }

        // Checking overall test result and updating test data
        if (!(resultOfTestAddUser.contains("false"))) {
            testData.put(reportRow++, new Object[]{testOrder++, "test_add_user", "Super Admin", "Save Action Message", saveActionMsg, saveActionMsg, "Pass"});
            passedSuperAdminTests.add(getMethodName);
        }
        else
        {
            testData.put(reportRow++, new Object[]{testOrder++, "test_add_user", "Super Admin", "Save Action Message", saveActionMsg, saveActionMsg, "Fail"});
            getMethodName = new Object() {}.getClass().getEnclosingMethod().getName();
            failedSuperAdminTests.add(getMethodName);
            flagSuperAdmin = false;
        }
    }

    // Method to encapsulate the logic of adding a user
    private void addUser(String firstName, String lastName, String email, String mobileNumber, String adminRole) {
        // Placeholder for actual logic to add a user (e.g., interacting with UI elements, backend API calls)
        System.out.println("Adding user: " + firstName + " " + lastName + " " + email + " " + mobileNumber + " " + adminRole);
    }

    /*
     * Test Case: 3
     *
     * Summary: Verify Logout functionality for Super Admin
     *
     * Prerequisite: 1. User must have valid URL
     *               2. Valid login credentials to perform the given test
     *
     * Steps: 1. Click on Account Profile button
     *        2. Click Logout
     *
     *
     * Expected Result: Super Admin user must be logout successfully
     *
     */

    @Test(priority = 3)
    public void testSaLogOut() {

        sleep(2000);
        IM10SuperAdmin.clickAccountCircle(driver);
        sleep(5000);
        IM10SuperAdmin.clickLogOut(driver);
        sleep(2000);

        String logOutActionMsg = IM10SuperAdmin.getLogOutActionMsg(driver);

        String expLogOutActionMsg = configReader.getExpectedLogoutSA();

        try {
            Assert.assertEquals(logOutActionMsg, expLogOutActionMsg);
            testData.put(reportRow++, new Object[]{testOrder++, "test_super_admin_logout", "Super Admin", "Logout Action", logOutActionMsg, expLogOutActionMsg, "Pass"});
            passedSuperAdminTests.add(getMethodName);
        } catch (Throwable e) {
            System.out.println(e);
            testData.put(reportRow++, new Object[]{testOrder++, "test_super_admin_logout", "Super Admin", "Logout Action", logOutActionMsg, expLogOutActionMsg, "Fail"});
            getMethodName = new Object() {}.getClass().getEnclosingMethod().getName();
            failedSuperAdminTests.add(getMethodName);
            flagSuperAdmin = false;

        }
    }

    /*
     * Test Case: 4
     *
     * Summary: Verify the Login for IM10 Sales Admin
     *
     * Prerequisite: 1. User must have valid URL
     *               2. Valid login credentials to perform the given test
     *
     * Steps: 1. Enter Email, Password
     *        2. Click on Submit button
     *
     * Expected Result: IM10 Sales Admin must be logged In successfully
     *
     * */

    // Test case for IM10 Sales Admin login functionality
    @Test(priority = 4)
    public void testIM10SALogin() throws InterruptedException {
        LoginUtility.login(driver, "IM10_SALES_ADMIN");

        String loginTitle = driver.getTitle();
        String expLoginTitle = configReader.getExpIM10SALogin();

        // Asserting login result and storing test result
        try {
            Assert.assertEquals(loginTitle, expLoginTitle);
            System.out.print("Login Successfully");
            testData.put(reportRow++, new Object[]{testOrder++, "test_IM10_sales_admin_login", "IM10 Sales Admin", "Login Title", loginTitle, expLoginTitle, "Pass"});
            passedIM10SalesAdminTests.add(getMethodName);

        } catch (Throwable e) {
            System.out.print("Login not done" + e);
            testData.put(reportRow++, new Object[]{testOrder++, "test_IM10_sales_admin_login", "IM10 Sales Admin", "Login Title", loginTitle, expLoginTitle, "Fail"});
            getMethodName = new Object() {}.getClass().getEnclosingMethod().getName();
            failedIM10SalesAdminTests.add(getMethodName);
            flagIM10SalesAdmin = false;
        }
    }

    /*
     * Test Case: 5
     *
     * Summary: Verify Add Player functionality for IM10 Sales Admin
     *
     * Prerequisite: 1. User must have valid URL
     *               2. Valid login credentials to perform the given test
     *
     *
     *Steps:  1. Select Player List option from sidebar menu
     *        2. Click on ADD button
     *        3. Enter First Name, Last Name, Date of Birth, Address, Bank Account No., PAN Card No. and select Sport and upload Aadhaar Card, PAN Card, Voting Card, Driving Licence
     *        4. Click on Submit button
     *
     *
     * Expected Result: Player must be added successfully
     *
     */

    @Test(priority = 5)
    public void testIM10SAAddPlayer() throws InterruptedException, IOException
    {
        HashMap<String, String> playerAdd = IM10SATestDataReader.readTestData("D:\\Automation Testing\\PageObjectModel\\test data\\AddPlayerDetails.xlsx", 0);

        // Performing test to add a player
        sleep(3000);
        IM10SalesAdmin.clickPlayerList(driver);
        sleep(3000);
        IM10SalesAdmin.clickAddButton(driver);
        sleep(1000);
        IM10SalesAdmin.enterFirstName(driver, playerAdd.get("firstName"));
        sleep(1000);
        IM10SalesAdmin.enterLastName(driver, playerAdd.get("lastName"));
        sleep(3000);
        IM10SalesAdmin.clickSport(driver);
        sleep(2000);
        IM10SalesAdmin.selectSportId(driver, playerAdd.get("sportId"));
        sleep(1000);
        IM10SalesAdmin.enterDOB(driver, playerAdd.get("dob"));
        sleep(1000);
        IM10SalesAdmin.enterAddress(driver, playerAdd.get("address"));
        sleep(1000);
        IM10SalesAdmin.selectProfilePhoto(driver, playerAdd.get("profileImageFileName"));
        sleep(3000);
        IM10SalesAdmin.clickSaveCrop(driver);
        sleep(2000);
        IM10SalesAdmin.selectAadhaarCard(driver, playerAdd.get("aadhaarCardFileName"));
        sleep(1000);
        IM10SalesAdmin.selectPANCard(driver, playerAdd.get("panCardFileName"));
        sleep(1000);
        IM10SalesAdmin.selectVotingCard(driver, playerAdd.get("votingCardFileName"));
        sleep(1000);
        IM10SalesAdmin.selectDrivingLicense(driver, playerAdd.get("drivingLicenceFileName"));
        sleep(1000);
        IM10SalesAdmin.enterBankAccNo(driver, playerAdd.get("bankAccountNo"));
        sleep(1000);
        IM10SalesAdmin.enterPANCardNo(driver, playerAdd.get("panCardNo"));
        sleep(1000);
        IM10SalesAdmin.clickSubmit(driver);
        sleep(5000);

        String saveActionMsg = IM10SalesAdmin.getSaveActionMsg(driver);
        String expSaveActionMsg = configReader.getExpAddIM10SA();

        // Asserting add player result and storing test result
        try {
            Assert.assertEquals(saveActionMsg, expSaveActionMsg);
            testData.put(reportRow++, new Object[]{testOrder++, "test_add_player", "IM10 Sales Admin", "Save Action Message", saveActionMsg, expSaveActionMsg, "Pass"});
            passedIM10SalesAdminTests.add(getMethodName);

        } catch (Throwable e) {
            System.out.println("Fail to add player" + e);
            testData.put(reportRow++, new Object[]{testOrder++, "test_add_player", "IM10 Sales Admin", "Save Action Message", saveActionMsg, expSaveActionMsg, "Fail"});
            getMethodName = new Object() {}.getClass().getEnclosingMethod().getName();
            failedIM10SalesAdminTests.add(getMethodName);
            flagIM10SalesAdmin = false;
        }
        IM10SalesAdmin.clickOkAP(driver);
    }

    /*
     * Test Case: 6
     *
     * Summary: Verify Logout functionality for IM10 Sales Admin
     *
     * Prerequisite: 1. User must have valid URL
     *               2. Valid login credentials to perform the given test
     *
     * Steps: 1. Click on Account Profile button
     *        2. Click Logout
     *
     *
     * Expected Result: IM10 Sales Admin user must be logout successfully
     *
     */

    @Test(priority = 6)
    public void testIM10SALogOut() {
        IM10SalesAdmin.clickAccountCircle(driver);
        sleep(2000);
        IM10SalesAdmin.clickLogOut(driver);
        sleep(2000);
        String logOutActionMsg = IM10SalesAdmin.getLogOutActionMsg(driver);
        String expLogOutActionMsg = configReader.getExpectedLogoutIM10SA();

        // Asserting add user result and storing test result
        try {
            Assert.assertEquals(logOutActionMsg, expLogOutActionMsg);
            testData.put(reportRow++, new Object[]{testOrder++, "test_IM10_sales_admin_logout", "IM10 Sales Admin", "Logout Action", logOutActionMsg, expLogOutActionMsg, "Pass"});
            passedIM10SalesAdminTests.add(getMethodName);

        } catch (Throwable e) {
            System.out.println(e);
            testData.put(reportRow++, new Object[]{testOrder++, "test_IM10_sales_admin_logout", "IM10 Sales Admin", "Logout Action", logOutActionMsg, expLogOutActionMsg, "Fail"});
            getMethodName = new Object() {}.getClass().getEnclosingMethod().getName();
            failedIM10SalesAdminTests.add(getMethodName);
            flagIM10SalesAdmin = false;
        }
    }


    /*
     * Test Case: 7
     *
     * Summary: Verify the User Player Mapping in Super Admin
     *
     * Prerequisite: 1. User must have valid URL
     *               2. Valid login credentials to perform the given test
     *
     * Steps: 1. Select User Player from sidebar menu
     *        2. Click Add button
     *        3. Select User and PLayer for mapping
     *        4. Click on Submit button
     *
     * Expected Result: User and Player mapped successfully.
     *
     * */

    @Test(priority = 7)
    public void testSaUserPlayer() throws InterruptedException
    {
        LoginUtility.login(driver, "SUPER_ADMIN");
        String saveActionMsgUP = "";
        String expSaveActionMsgUP;
        Set<String> resultOfTestUserPlayerMap = new HashSet<>();
        boolean flagResultOfTestUserPlayerMap = false;

        // Array to hold user and player mappings
        String[][] userPlayerMappings = {
                {"TestFirstNameCA TestLastNameCA (Content Admin)", "PlayerFirstName PlayerLastName"},
                {"TestFirstNameEMA TestLastNameEMA (Endorsement Manager Admin)", "PlayerFirstName PlayerLastName"},
                {"TestFirstNameSPA TestLastNameSPA (Sales Person Admin)", "PlayerFirstName PlayerLastName"},
                {"TestFirstNameQA TestLastNameQA (Quality Assurance)", "PlayerFirstName PlayerLastName"},
                {"TestFirstNameMAC TestLastNameMAC (IM10 Marketing Campaigns)", "PlayerFirstName PlayerLastName"}
        };

        for (String[] mapping : userPlayerMappings) {
            String userId = mapping[0];
            String playerId = mapping[1];

            sleep(2000);
            IM10SuperAdmin.clickUserPlayer(driver);
            sleep(2000);
            IM10SuperAdmin.clickUserPlayerAdd(driver);
            sleep(4000);
            IM10SuperAdmin.clickUserDropDown(driver);
            sleep(4000);
            IM10SuperAdmin.selectUserId(driver, userId);
            sleep(5000);
            IM10SuperAdmin.clickPlayerName(driver);
            sleep(5000);
            IM10SuperAdmin.selectPlayerId(driver, playerId);
            sleep(3000);
            IM10SuperAdmin.clickSubmitUserMap(driver);
            sleep(2000);

            saveActionMsgUP = IM10SuperAdmin.getUPSaveActionMsg(driver);

            expSaveActionMsgUP = configReader.getUserPlayerMap();

            // Asserting add user result and storing test result
            try
            {
                Assert.assertEquals(saveActionMsgUP, expSaveActionMsgUP);
                flagResultOfTestUserPlayerMap = true;
                resultOfTestUserPlayerMap.add(Boolean.toString(flagResultOfTestUserPlayerMap));
            }
            catch (Throwable e)
            {
                System.out.println(e);
                resultOfTestUserPlayerMap.add(Boolean.toString(flagResultOfTestUserPlayerMap));
            }
            sleep(2000);
            IM10SuperAdmin.clickOkUP(driver);
        }

        if (!(resultOfTestUserPlayerMap.contains("false"))) {
            testData.put(reportRow++, new Object[]{testOrder++, "test_add_user_player_mapping", "Super Admin", "Save Action Message", saveActionMsgUP, saveActionMsgUP, "Pass"});
            passedSuperAdminTests.add(getMethodName);
        }
        else
        {
            testData.put(reportRow++, new Object[]{testOrder++, "test_add_user_player_mapping", "Super Admin", "Save Action Message", saveActionMsgUP, saveActionMsgUP, "Fail"});
            getMethodName = new Object() {}.getClass().getEnclosingMethod().getName();
            failedSuperAdminTests.add(getMethodName);
            flagSuperAdmin = false;

        }
    }

    /*
     * Test Case: 8
     *
     * Summary: Verify Logout functionality for Super Admin
     *
     * Prerequisite: 1. User must have valid URL
     *               2. Valid login credentials to perform the given test
     *
     * Steps: 1. Click on Account Profile button
     *        2. Click Logout
     *
     *
     * Expected Result: Super Admin user must be logout successfully
     *
     */

    @Test(priority = 8)
    public void testSaUPLogOut()
    {
        sleep(3000);
        IM10SuperAdmin.clickAccountCircle(driver);
        sleep(4000);
        IM10SuperAdmin.clickLogOut(driver);
        sleep(2000);

        String logOutActionMsg = IM10SuperAdmin.getLogOutActionMsg(driver);

        String expLogOutActionMsg = configReader.getExpectedLogoutSA();

        try {
            Assert.assertEquals(logOutActionMsg, expLogOutActionMsg);
            testData.put(reportRow++, new Object[]{testOrder++, "test_super_admin_logout", "Super Admin", "Logout Action", logOutActionMsg, expLogOutActionMsg, "Pass"});
            passedSuperAdminTests.add(getMethodName);
        } catch (Throwable e) {
            System.out.println(e);
            testData.put(reportRow++, new Object[]{testOrder++, "test_super_admin_logout", "Super Admin", "Logout Action", logOutActionMsg, expLogOutActionMsg, "Fail"});
            getMethodName = new Object() {}.getClass().getEnclosingMethod().getName();
            failedSuperAdminTests.add(getMethodName);
            flagSuperAdmin = false;

        }
    }

    /*
     * Test Case: 9
     *
     * Summary: Verify the Login for IM10 Content Admin
     *
     * Prerequisite: 1. User must have valid URL
     *               2. Valid login credentials to perform the given test
     *
     * Steps: 1. Enter Email, Password
     *        2. Click on Submit button
     *
     * Expected Result: IM10 Content Admin must be logged In successfully
     *
     * */

    // Test case for Content Admin login functionality
    @Test(priority = 9)
    public void testCALogin() throws InterruptedException {

        LoginUtility.login(driver, "CONTENT_ADMIN");

        String loginTitle = driver.getTitle();
        String expLoginTitle = configReader.getExpCALogin();

        // Asserting login result and storing test result
        try {
            Assert.assertEquals(loginTitle, expLoginTitle);
            System.out.print("Login Successfully");
            testData.put(reportRow++, new Object[]{testOrder++, "test_content_admin_login", "Content Admin", "Login Title", loginTitle, expLoginTitle, "Pass"});
            passedContentAdminTests.add(getMethodName);

        } catch (Throwable e) {
            System.out.print("Login not done" + e);
            testData.put(reportRow++, new Object[]{testOrder++, "test_content_admin_login", "Content Admin", "Login Title", loginTitle, expLoginTitle, "Fail"});

            //Adding the failed test method name to the list of failed tests
            getMethodName = new Object() {}.getClass().getEnclosingMethod().getName();
            failedContentAdminTests.add(getMethodName);

            //Setting flag to indicate test failure
            flagContentAdmin = false;

        }
    }

    /*
     * Test Case: 10
     *
     * Summary: Verify Add Content functionality for Content Admin
     *
     * Prerequisite: 1. User must have valid URL
     *               2. Valid login credentials to perform the given test
     *
     * Steps: 1. Select Player
     *        2. Select Content option from sidebar menu
     *        3. Click on ADD button
     *        4. Enter Title, Description and select Content Type, Category, Sub-Category, Language and upload Thumbnail, Content Part 1
     *        5. Click on Submit button
     *
     *
     * Expected Result: Content must be added successfully
     *
     */

    @Test(priority = 10)
    public void testCaAddContent() throws InterruptedException, IOException
    {
        HashMap<String, String> contentData = IM10CATestDataReader.readTestData("D:\\Automation Testing\\PageObjectModel\\test data\\AddContentDetails.xlsx", 0);

        // Performing test to add a player
        IM10ContentAdmin.clickPlayer(driver);
        sleep(1000);
        IM10ContentAdmin.clickContent(driver);
        sleep(1000);
        IM10ContentAdmin.clickAddButton(driver);
        sleep(1000);
        IM10ContentAdmin.enterTitle(driver, contentData.get("title"));
        sleep(1000);
        IM10ContentAdmin.enterDescription(driver, contentData.get("description"));
        sleep(3000);
        IM10ContentAdmin.clickContentType(driver);
        sleep(3000);
        IM10ContentAdmin.selectContentTypeId(driver, contentData.get("contentType"));
        sleep(4000);
        IM10ContentAdmin.selectThumbnail(driver, contentData.get("thumbnail"));
        sleep(4000);
        IM10ContentAdmin.selectContentPart1(driver, contentData.get("contentPart1"));
        sleep(4000);
        IM10ContentAdmin.selectContentPart2(driver, contentData.get("contentPart2"));
        sleep(2000);
        IM10ContentAdmin.clickCategory(driver);
        sleep(1000);
        IM10ContentAdmin.selectCategoryId(driver, contentData.get("category"));
        sleep(2000);
        IM10ContentAdmin.clickSubCategory(driver);
        sleep(1000);
        IM10ContentAdmin.selectSubCategoryId(driver, contentData.get("subCategory"));
        sleep(2000);
        IM10ContentAdmin.clickLanguage(driver);
        sleep(1000);
        IM10ContentAdmin.selectLanguageId(driver, contentData.get("language"));
        sleep(2000);
        IM10ContentAdmin.clickSubmit(driver);
        sleep(3000);

        String saveActionMsg = IM10ContentAdmin.getSaveActionMsg(driver);
        String expSaveActionMsg = configReader.getExpAddCA();

        // Asserting add player result and storing test result
        try
        {
            Assert.assertEquals(saveActionMsg,expSaveActionMsg);
            testData.put(reportRow++, new Object[]{testOrder++, "test_add_content", "Content Admin", "Save Action Message", saveActionMsg, expSaveActionMsg, "Pass"});
            passedContentAdminTests.add(getMethodName);
        }
        catch (Throwable e)
        {
            System.out.println(e);
            testData.put(reportRow++, new Object[]{testOrder++, "test_add_content", "Content Admin", "Save Action Message", saveActionMsg, expSaveActionMsg, "Fail"});
            getMethodName = new Object() {}.getClass().getEnclosingMethod().getName();
            failedContentAdminTests.add(getMethodName);
            flagContentAdmin = false;
        }
        sleep(5000);
        IM10ContentAdmin.clickOkSaveAction(driver);
    }

    /*
     * Test Case: 11
     *
     * Summary: Verify Logout functionality for Content Admin
     *
     * Prerequisite: 1. User must have valid URL
     *               2. Valid login credentials to perform the given test
     *
     * Steps: 1. Click on Account Profile button
     *        2. Click Logout
     *
     *
     * Expected Result: Content Admin user must be logout successfully
     *
     */

    @Test(priority = 11)
    public void testCaLogOutAfterAdd() throws NoSuchElementException
    {
        IM10ContentAdmin.clickAccountCircle(driver);
        sleep(2000);
        IM10ContentAdmin.clickLogOut(driver);
        sleep(2000);

        String logOutActionMsg = IM10ContentAdmin.getLogOutActionMsg(driver);
        String expLogOutActionMsg = configReader.getExpectedLogoutCA();

        // Asserting add user result and storing test result
        try {
            Assert.assertEquals(logOutActionMsg, expLogOutActionMsg);
            testData.put(reportRow++, new Object[]{testOrder++, "test_content_admin_logout", "Content Admin", "Logout Action", logOutActionMsg, expLogOutActionMsg, "Pass"});
            passedContentAdminTests.add(getMethodName);
        } catch (Throwable e) {
            System.out.println(e);
            testData.put(reportRow++, new Object[]{testOrder++, "test_content_admin_logout", "Content Admin", "Logout Action", logOutActionMsg, expLogOutActionMsg, "Fail"});
            getMethodName = new Object() {}.getClass().getEnclosingMethod().getName();
            failedContentAdminTests.add(getMethodName);
            flagContentAdmin = false;
        }
    }


    @Test(priority = 12)
    public void testQAApproveContent() throws InterruptedException
    {
        LoginUtility.login(driver, "QUALITY_ASSURANCE_ADMIN");
        sleep(3000);
        IM10QualityAssurance.clickPlayer(driver);
        sleep(1000);
        IM10QualityAssurance.clickContent(driver);
        sleep(3000);
        IM10QualityAssurance.clickApproveAction(driver);
        sleep(1000);
        IM10QualityAssurance.getApproveActionMsg(driver);
        sleep(1000);
        IM10QualityAssurance.clickApproveYes(driver);
        sleep(1000);

        String approveActionMsg = IM10QualityAssurance.getResultMsg(driver);
        String expApproveActionMsg = "Content Approved Successfully!";

        // Asserting add player result and storing test result
        try
        {
            Assert.assertEquals(approveActionMsg,expApproveActionMsg);
            testData.put(reportRow++, new Object[]{testOrder++, "test_approve_content", "Quality Assurance Admin", "Approved Action Message", approveActionMsg, expApproveActionMsg, "Pass"});
            passedQAAdminTests.add(getMethodName);
        }
        catch (Throwable e)
        {
            System.out.println(e);
            testData.put(reportRow++, new Object[]{testOrder++, "test_approve_content", "Quality Assurance Admin", "Approved Action Message", approveActionMsg, expApproveActionMsg, "Fail"});
            getMethodName = new Object() {}.getClass().getEnclosingMethod().getName();
            failedQAAdminTests.add(getMethodName);
            flagQAAdmin = false;
        }
        sleep(4000);
        IM10QualityAssurance.clickResultOk(driver);
    }

    @Test(priority = 13)
    public void testQALogOutAfterApproved() {

        sleep(4000);
        IM10QualityAssurance.clickAccountCircle(driver);
        sleep(5000);
        IM10QualityAssurance.clickLogOut(driver);
        sleep(2000);

        String logOutActionMsg = IM10QualityAssurance.getLogOutActionMsg(driver);

        String expLogOutActionMsg = "LOGIN";

        try {
            Assert.assertEquals(logOutActionMsg, expLogOutActionMsg);
            testData.put(reportRow++, new Object[]{testOrder++, "test_QA_admin_logout", "Quality Assurance Admin", "Logout Action", logOutActionMsg, expLogOutActionMsg, "Pass"});
            passedQAAdminTests.add(getMethodName);
        } catch (Throwable e) {
            System.out.println(e);
            testData.put(reportRow++, new Object[]{testOrder++, "test_QA_admin_logout", "Quality Assurance Admin", "Logout Action", logOutActionMsg, expLogOutActionMsg, "Fail"});
            getMethodName = new Object() {}.getClass().getEnclosingMethod().getName();
            failedQAAdminTests.add(getMethodName);
            flagQAAdmin = false;

        }
    }

    @Test(priority = 14)
    public void testMarketingCampaignsLogin() throws InterruptedException
    {
        LoginUtility.login(driver, "IM10_MARKETING_CAMPAIGNS_ADMIN");

        String loginTitle = driver.getTitle();
        String expLoginTitle = configReader.getExpCALogin();

        // Asserting login result and storing test result
        try {
            Assert.assertEquals(loginTitle, expLoginTitle);
            System.out.print("Login Successfully");
            testData.put(reportRow++, new Object[]{testOrder++, "test_marketing_campaigns_login", "IM10 Marketing Campaigns Admin", "Login Title", loginTitle, expLoginTitle, "Pass"});
            passedMarketingCampaignsTests.add(getMethodName);

        } catch (Throwable e) {
            System.out.print("Login not done" + e);
            testData.put(reportRow++, new Object[]{testOrder++, "test_marketing_campaigns_login", "IM10 Marketing Campaigns Admin", "Login Title", loginTitle, expLoginTitle, "Fail"});

            //Adding the failed test method name to the list of failed tests
            getMethodName = new Object() {}.getClass().getEnclosingMethod().getName();
            failedMarketingCampaignsTests.add(getMethodName);

            //Setting flag to indicate test failure
            flagMarketingCampaigns = false;

        }
    }

    @Test(priority = 15)
    public void testAddCampaign() throws InterruptedException, IOException
    {
        HashMap<String, String> campaignData = IM10MarketingCampaignsDataReader.readTestData("D:\\Automation Testing\\PageObjectModel\\test data\\AddCampaignDetails.xlsx", 0);

        // Performing test to add a player
        IM10MarketingCampaigns.clickPlayer(driver);
        sleep(4000);
        IM10MarketingCampaigns.clickCampaign(driver);
        sleep(4000);
        IM10MarketingCampaigns.clickAddButton(driver);
        sleep(2000);
        IM10MarketingCampaigns.enterTitle(driver, campaignData.get("title"));
        sleep(4000);
        IM10MarketingCampaigns.clickContentType(driver);
        sleep(4000);
        IM10MarketingCampaigns.selectContentTypeId(driver, campaignData.get("contentType"));
        sleep(4000);
        IM10MarketingCampaigns.clickContentTitle(driver);
        sleep(4000);
        IM10MarketingCampaigns.selectContentTitleId(driver, campaignData.get("contentTitle"));
        sleep(2000);
        IM10MarketingCampaigns.enterDescription(driver, campaignData.get("description"));
        sleep(2000);
        IM10MarketingCampaigns.clickSubmit(driver);
        sleep(5000);

        String saveActionMsg = IM10MarketingCampaigns.getSaveActionMsg(driver);
        String expSaveActionMsg = "Campaign Added Successfully!";

        // Asserting add player result and storing test result
        try
        {
            Assert.assertEquals(saveActionMsg,expSaveActionMsg);
            testData.put(reportRow++, new Object[]{testOrder++, "test_add_campaign", "IM10 Marketing Campaigns Admin", "Save Action Message", saveActionMsg, expSaveActionMsg, "Pass"});
            passedMarketingCampaignsTests.add(getMethodName);
        }
        catch (Throwable e)
        {
            System.out.println(e);
            testData.put(reportRow++, new Object[]{testOrder++, "test_add_campaign", "IM10 Marketing Campaigns Admin", "Save Action Message", saveActionMsg, expSaveActionMsg, "Fail"});
            getMethodName = new Object() {}.getClass().getEnclosingMethod().getName();
            failedMarketingCampaignsTests.add(getMethodName);
            flagMarketingCampaigns = false;
        }
        sleep(4000);
        IM10MarketingCampaigns.clickOkSaveAction(driver);
    }

    @Test(priority = 16)
    public void testDeleteCampaign()
    {
        sleep(2000);
        IM10MarketingCampaigns.clickDelete(driver);
        sleep(3000);
        IM10MarketingCampaigns.clickYes(driver);
        sleep(1000);

        String deleteAction = IM10MarketingCampaigns.getDeleteActionMsg(driver);
        String expDeleteAction = "Campaign Deleted Successfully!";

        try {
            Assert.assertEquals(deleteAction, expDeleteAction);
            testData.put(reportRow++, new Object[]{testOrder++, "test_delete_content", "IM10 Marketing Campaign Admin", "Delete Action Message", deleteAction, expDeleteAction, "Pass"});
            passedMarketingCampaignsTests.add(getMethodName);

        } catch (Throwable e) {
            System.out.println("Fail to delete content" + e);
            testData.put(reportRow++, new Object[]{testOrder++, "test_delete_content", "IM10 Marketing Campaign Admin", "Delete Action Message", deleteAction, expDeleteAction, "Fail"});
            getMethodName = new Object() {}.getClass().getEnclosingMethod().getName();
            failedMarketingCampaignsTests.add(getMethodName);
            flagContentAdmin = false;
        }
        IM10MarketingCampaigns.clickOkDC(driver);
    }


    @Test(priority = 17)
    public void testLogOutMarketingCampaigns() {

        sleep(3000);
        IM10MarketingCampaigns.clickAccountCircle(driver);
        sleep(5000);
        IM10MarketingCampaigns.clickLogOut(driver);
        sleep(2000);

        String logOutActionMsg = IM10MarketingCampaigns.getLogOutActionMsg(driver);

        String expLogOutActionMsg = "LOGIN";

        try {
            Assert.assertEquals(logOutActionMsg, expLogOutActionMsg);
            testData.put(reportRow++, new Object[]{testOrder++, "test_marketing_campaign_admin_logout", "IM10 Marketing Campaign Admin", "Logout Action", logOutActionMsg, expLogOutActionMsg, "Pass"});
            passedMarketingCampaignsTests.add(getMethodName);
        } catch (Throwable e) {
            System.out.println(e);
            testData.put(reportRow++, new Object[]{testOrder++, "test_marketing_campaign_admin_logout", "IM10 Marketing Campaign Admin", "Logout Action", logOutActionMsg, expLogOutActionMsg, "Fail"});
            getMethodName = new Object() {}.getClass().getEnclosingMethod().getName();
            failedMarketingCampaignsTests.add(getMethodName);
            flagQAAdmin = false;

        }
    }

    @Test(priority = 18)
    public void testQADisapproveContent() throws InterruptedException
    {
        LoginUtility.login(driver, "QUALITY_ASSURANCE_ADMIN");
        sleep(3000);
        IM10QualityAssurance.clickPlayer(driver);
        sleep(1000);
        IM10QualityAssurance.clickContent(driver);
        sleep(1000);
        IM10QualityAssurance.clickDisapproveAction(driver);
        sleep(1000);
        IM10QualityAssurance.getDisapproveActionMsg(driver);
        sleep(1000);
        IM10QualityAssurance.enterComment(driver);
        sleep(3000);
        IM10QualityAssurance.clickSubmit(driver);
        sleep(2000);

        String approveActionMsg = IM10QualityAssurance.getDisapproveMsg(driver);
        String expApproveActionMsg = "  Content List";

        // Asserting add player result and storing test result
        try
        {
            Assert.assertEquals(approveActionMsg,expApproveActionMsg);
            testData.put(reportRow++, new Object[]{testOrder++, "test_disapproved_content", "Quality Assurance Admin", "Approved Action Message", approveActionMsg, expApproveActionMsg, "Pass"});
            passedQAAdminTests.add(getMethodName);
        }
        catch (Throwable e)
        {
            System.out.println(e);
            testData.put(reportRow++, new Object[]{testOrder++, "test_disapproved_content", "Quality Assurance Admin", "Approved Action Message", approveActionMsg, expApproveActionMsg, "Fail"});
            getMethodName = new Object() {}.getClass().getEnclosingMethod().getName();
            failedQAAdminTests.add(getMethodName);
            flagQAAdmin = false;
        }
    }

    @Test(priority = 19)
    public void testQALogOutAfterDisapproved() {

        sleep(2000);
        IM10QualityAssurance.clickAccountCircle(driver);
        sleep(5000);
        IM10QualityAssurance.clickLogOut(driver);
        sleep(2000);

        String logOutActionMsg = IM10QualityAssurance.getLogOutActionMsg(driver);

        String expLogOutActionMsg = "LOGIN";

        try {
            Assert.assertEquals(logOutActionMsg, expLogOutActionMsg);
            testData.put(reportRow++, new Object[]{testOrder++, "test_QA_admin_logout", "Quality Assurance Admin", "Logout Action", logOutActionMsg, expLogOutActionMsg, "Pass"});
            passedQAAdminTests.add(getMethodName);
        } catch (Throwable e) {
            System.out.println(e);
            testData.put(reportRow++, new Object[]{testOrder++, "test_QA_admin_logout", "Quality Assurance Admin", "Logout Action", logOutActionMsg, expLogOutActionMsg, "Fail"});
            getMethodName = new Object() {}.getClass().getEnclosingMethod().getName();
            failedQAAdminTests.add(getMethodName);
            flagQAAdmin = false;
            sleep(2000);
        }
    }

    /*
     * Test Case: 20
     *
     * Summary: Verify Delete Content functionality for Content Admin
     *
     * Prerequisite: 1. User must have valid URL
     *               2. Valid login credentials to perform the given test
     *               3. Content must be added successfully
     *
     * Steps: 1. Click on Delete button
     *        2. Confirm delete action by clicking "Yes" button
     *
     *
     * Expected Result: Content must be Deleted successfully
     *
     */

    @Test(priority = 20)
    public void testCaDeleteContent() throws InterruptedException
    {
        LoginUtility.login(driver, "CONTENT_ADMIN");
        sleep(3000);
        IM10ContentAdmin.clickPlayer(driver);
        sleep(4000);
        IM10ContentAdmin.clickContent(driver);
        sleep(4000);
        IM10ContentAdmin.clickDelete(driver);
        sleep(3000);
        IM10ContentAdmin.clickYes(driver);
        sleep(1000);

        String deleteAction = IM10ContentAdmin.getDeleteActionMsg(driver);
        String expDeleteAction = configReader.getExpDeleteCA();

        try {
            Assert.assertEquals(deleteAction, expDeleteAction);
            testData.put(reportRow++, new Object[]{testOrder++, "test_delete_content", "Content Admin", "Delete Action Message", deleteAction, expDeleteAction, "Pass"});
            passedContentAdminTests.add(getMethodName);

        } catch (Throwable e) {
            System.out.println("Fail to delete content" + e);
            testData.put(reportRow++, new Object[]{testOrder++, "test_delete_content", "Content Admin", "Delete Action Message", deleteAction, expDeleteAction, "Fail"});
            getMethodName = new Object() {}.getClass().getEnclosingMethod().getName();
            failedContentAdminTests.add(getMethodName);
            flagContentAdmin = false;
        }
        IM10ContentAdmin.clickOkDC(driver);
    }

    /*
     * Test Case: 21
     *
     * Summary: Verify Logout functionality for Content Admin
     *
     * Prerequisite: 1. User must have valid URL
     *               2. Valid login credentials to perform the given test
     *
     * Steps: 1. Click on Account Profile button
     *        2. Click Logout
     *
     *
     * Expected Result: Content Admin user must be logout successfully
     *
     */

    @Test(priority = 21)
    public void testCaLogOut() throws NoSuchElementException {
        IM10ContentAdmin.clickAccountCircle(driver);
        sleep(2000);
        IM10ContentAdmin.clickLogOut(driver);
        sleep(2000);

        String logOutActionMsg = IM10ContentAdmin.getLogOutActionMsg(driver);
        String expLogOutActionMsg = configReader.getExpectedLogoutCA();

        // Asserting add user result and storing test result
        try {
            Assert.assertEquals(logOutActionMsg, expLogOutActionMsg);
            testData.put(reportRow++, new Object[]{testOrder++, "test_content_admin_logout", "Content Admin", "Logout Action", logOutActionMsg, expLogOutActionMsg, "Pass"});
            passedContentAdminTests.add(getMethodName);
        } catch (Throwable e) {
            System.out.println(e);
            testData.put(reportRow++, new Object[]{testOrder++, "test_content_admin_logout", "Content Admin", "Logout Action", logOutActionMsg, expLogOutActionMsg, "Fail"});
            getMethodName = new Object() {}.getClass().getEnclosingMethod().getName();
            failedContentAdminTests.add(getMethodName);
            flagContentAdmin = false;
        }
    }


    /*
     * Test Case: 22
     *
     * Summary: Verify the Login for IM10 Endorsement Manager Admin
     *
     * Prerequisite: 1. User must have valid URL
     *               2. Valid login credentials to perform the given test
     *
     * Steps: 1. Enter Email, Password
     *        2. Click on Submit button
     *
     * Expected Result: IM10 Endorsement Manager Admin must be logged In successfully
     *
     * */

    @Test(priority = 22)
    public void testSPALogin() throws InterruptedException {
        LoginUtility.login(driver, "SALES_PERSON_ADMIN");

        // Reading test data and performing login test
        // IM10SalesPersonAdminDataReader.readTestData("", 0);

        String loginTitle = driver.getTitle();
        String expLoginTitle = configReader.getExpSAResultForLogin();

        // Asserting login result and storing test result
        try {
            Assert.assertEquals(loginTitle, expLoginTitle);
            System.out.print("Login Successfully");
            testData.put(reportRow++, new Object[]{testOrder++, "test_sales_person_admin_login", "Sales Person Admin", "Login Title", loginTitle, expLoginTitle, "Pass"});
            passedSalesPersonAdminTests.add(getMethodName);

        } catch (Throwable e) {
            System.out.print("Login not done" + e);
            testData.put(reportRow++, new Object[]{testOrder++, "test_sales_person_admin_login", "Sales Person Admin", "Login Title", loginTitle, expLoginTitle, "Fail"});
            getMethodName = new Object() {}.getClass().getEnclosingMethod().getName();
            failedSalesPersonAdminTests.add(getMethodName);
            flagSalesPersonAdmin = false;
        }
    }

    /*
     * Test Case: 23
     *
     * Summary: Verify the Add Listing for Sales Person Admin
     *
     * Prerequisite: 1. User must have valid URL
     *               2. Valid login credentials
     *
     * Steps: 1. Select Player
     *        2. Select Listing List from sidebar menu
     *        3. Click Add button
     *        4. Add Listing details like BrandName, Email, Mobile, LandLine, Website, upload BrandLogo, enter Description
     *           Position, ContactPersonName, ContactPersonEmail, select Country, State, City, enter Start Date, End Date, Final Price
     *        5. Click Submit button
     *
     * Expected Result: Listing List must be added successfully
     *
     */

    @Test(priority = 23)
    public void testAddListing() throws InterruptedException, IOException {

        // Reading test data and performing login test
        HashMap<String,String>listingData = IM10SalesPersonAdminDataReader.readTestData("D:\\Automation Testing\\PageObjectModel\\test data\\AddListing.xlsx", 0);

        SalesPersonAdmin.clickPlayer(driver);
        sleep(4000);
        SalesPersonAdmin.selectListingList(driver);
        sleep(2000);
        SalesPersonAdmin.clickAdd(driver);
        sleep(2000);
        SalesPersonAdmin.enterBrandName(driver,listingData.get("testBrandName"));
        sleep(1000);
        SalesPersonAdmin.enterEmail(driver, listingData.get("testEmail"));
        sleep(1000);
        SalesPersonAdmin.enterMobile(driver, listingData.get("testMobile"));
        sleep(1000);
        SalesPersonAdmin.enterLandLine(driver, listingData.get("testLandline"));
        sleep(1000);
        SalesPersonAdmin.enterWebsite(driver, listingData.get("testWebsite"));
        sleep(3000);
        SalesPersonAdmin.selectBrandLogo(driver, listingData.get("testBrandLogo"));
        sleep(2000);
        SalesPersonAdmin.enterDescription(driver, listingData.get("testDescription"));
        sleep(1000);
        SalesPersonAdmin.enterPosition(driver, listingData.get("testPosition"));
        sleep(1000);
        SalesPersonAdmin.enterContactPersonName(driver, listingData.get("testContactPersonName"));
        sleep(1000);
        SalesPersonAdmin.enterContactPersonMobile(driver, listingData.get("testContactPersonMobile"));
        sleep(1000);
        SalesPersonAdmin.enterContactPersonEmail(driver, listingData.get("testContactPersonEmail"));
        sleep(4000);
        SalesPersonAdmin.clickCountry(driver);
        sleep(4000);
        SalesPersonAdmin.selectCountryId(driver, listingData.get("testCountry"));
        sleep(4000);
        SalesPersonAdmin.clickState(driver);
        sleep(4000);
        SalesPersonAdmin.selectStateId(driver, listingData.get("testState"));
        sleep(4000);
        SalesPersonAdmin.clickCity(driver);
        sleep(4000);
        SalesPersonAdmin.selectCityId(driver, listingData.get("testCity"));
        sleep(4000);
        SalesPersonAdmin.enterStartDate(driver, listingData.get("testStartDate"));
        sleep(1000);
        SalesPersonAdmin.enterEndDate(driver, listingData.get("testEndDate"));
        sleep(1000);
        SalesPersonAdmin.enterFinalPrice(driver, listingData.get("testFinalPrice"));
        sleep(4000);
        SalesPersonAdmin.clickSubmit(driver);
        sleep(2000);

        String saveActionMsg = SalesPersonAdmin.getSaveActionMsg(driver);
        String expSaveActionMsg = "Listing Details Added Successfully!";

        // Asserting add player result and storing test result
        try {
            Assert.assertEquals(saveActionMsg, expSaveActionMsg);
            testData.put(reportRow++, new Object[]{testOrder++, "test_add_Listing", "Sales Person Admin", "Save Action Message", saveActionMsg, expSaveActionMsg, "Pass"});
            passedSalesPersonAdminTests.add(getMethodName);

        } catch (Throwable e) {
            System.out.println("Fail to add player" + e);
            testData.put(reportRow++, new Object[]{testOrder++, "test_add_Listing", "Sales Person Admin", "Save Action Message", saveActionMsg, expSaveActionMsg, "Fail"});
            getMethodName = new Object() {}.getClass().getEnclosingMethod().getName();
            failedSalesPersonAdminTests.add(getMethodName);
            flagSalesPersonAdmin = false;
        }
        SalesPersonAdmin.clickOk(driver);

    }

    /*
     * Test Case: 24
     *
     * Summary: Verify Logout functionality for Sales Person Admin
     *
     * Prerequisite: 1. User must have valid URL
     *               2. Valid login credentials to perform the given test
     *
     * Steps: 1. Click on Account Profile button
     *        2. Click Logout
     *
     *
     * Expected Result: Sales Person Admin user must be logout successfully
     *
     */

    @Test(priority = 24)
    public void testSPALogOut() {
        SalesPersonAdmin.clickAccountCircle(driver);
        sleep(2000);
        SalesPersonAdmin.clickLogOut(driver);
        sleep(2000);

        String logOutActionMsg = SalesPersonAdmin.getLogOutActionMsg(driver);
        String expLogOutActionMsg = "LOGIN";

        // Asserting add user result and storing test result
        try {
            Assert.assertEquals(logOutActionMsg, expLogOutActionMsg);
            testData.put(reportRow++, new Object[]{testOrder++, "test_Sales_Person_Admin", "Sales Person Admin", "Logout Action", logOutActionMsg, expLogOutActionMsg, "Pass"});
            passedSalesPersonAdminTests.add(getMethodName);

        } catch (Throwable e) {
            System.out.println(e);
            testData.put(reportRow++, new Object[]{testOrder++, "test_Sales_Person_logout", "Sales Person Admin", "Logout Action", logOutActionMsg, expLogOutActionMsg, "Fail"});
            getMethodName = new Object() {}.getClass().getEnclosingMethod().getName();
            failedSalesPersonAdminTests.add(getMethodName);
            flagSalesPersonAdmin = false;
        }
    }

    /*
     * Test Case: 25
     *
     * Summary: Verify the Login for Endorsement Manager Admin
     *
     * Prerequisite: 1. User must have valid URL
     *               2. Valid login credentials to perform the given test
     *
     * Steps: 1. Enter Email, Password
     *        2. Click on Submit button
     *
     * Expected Result: Endorsement Manager Admin must be logged In successfully
     *
     * */

    // Test case for Endorsement Manager Admin login functionality
    @Test(priority = 25)
    public void testEMALogin() throws IOException, InterruptedException {
        LoginUtility.login(driver, "ENDORSEMENT_MANAGER_ADMIN");

        // Reading test data and performing login test
        IM10EMATestDataReader.readTestData("D:\\Automation Testing\\PageObjectModel\\test data\\AddPlayerEndorsementDetails.xlsx", 0);

        String loginTitle = driver.getTitle();
        String expLoginTitle = configReader.getExpEALogin();

        // Asserting login result and storing test result
        try {
            Assert.assertEquals(loginTitle, expLoginTitle);
            System.out.print("Login Successfully");
            testData.put(reportRow++, new Object[]{testOrder++, "test_EMA_login ", "Endorsement Manager Admin", "Login Title", loginTitle, expLoginTitle, "Pass"});
            passedEndorsementManagerAdminTests.add(getMethodName);

        } catch (Throwable e) {
            System.out.print("Login not done" + e);
            testData.put(reportRow++, new Object[]{testOrder++, "test_EMA_login ", "Endorsement Manager Admin", "Login Title", loginTitle, expLoginTitle, "Fail"});
            getMethodName = new Object() {}.getClass().getEnclosingMethod().getName();
            failedEndorsementManagerAdminTests.add(getMethodName);
            flagEndorsementManagerAdmin = false;
        }
    }

    /*
     * Test Case: 26
     *
     * Summary: Verify the Add Listing for Sales Person Admin
     *
     * Prerequisite: 1. User must have valid URL
     *               2. Valid login credentials
     *
     * Steps: 1. Select Player
     *        2. Select Player Endorsement from sidebar menu
     *        3. Click Add button
     *        4. Enter CompanyName, select EndorseType, StartDate, EndDate, enter FinalPrice, Notes
     *        5. click Submit button
     *
     * Expected Result: Player Endorsement must be added successfully
     */

    @Test(priority = 26)
    public void testEMAAddPlayerEndorse() throws InterruptedException {
        // Performing test to add a player
        IM10EndorseManagerAdmin.clickPlayer(driver);
        sleep(3000);
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
        String expSaveActionMsg = configReader.getExpAddEA();

        // Asserting add player result and storing test result
        try
        {
            Assert.assertEquals(saveActionMsg, expSaveActionMsg);
            testData.put(reportRow++, new Object[]{testOrder++, "test_add_player_endorsement", "Endorsement Manager Admin", "Save Action Message", saveActionMsg, expSaveActionMsg, "Pass"});
            passedEndorsementManagerAdminTests.add(getMethodName);

        } catch (Throwable e) {
            System.out.println("Fail to add player" + e);
            testData.put(reportRow++, new Object[]{testOrder++, "test_add_player_endorsement", "Endorsement Manager Admin", "Save Action Message", saveActionMsg, expSaveActionMsg, "Fail"});
            getMethodName = new Object() {}.getClass().getEnclosingMethod().getName();
            failedEndorsementManagerAdminTests.add(getMethodName);
            flagEndorsementManagerAdmin = false;
        }
        sleep(4000);
        IM10EndorseManagerAdmin.clickOk(driver);
    }

    /*
     * Test Case: 27
     *
     * Summary: Verify Delete Player Endorsement functionality for Endorsement Manager Admin
     *
     * Prerequisite: 1. User must have valid URL
     *               2. Valid login credentials to perform the given test
     *               3. Player Endorsement must be added successfully
     *
     * Steps: 1. Click on Delete button
     *        2. Confirm delete action by clicking "Yes" button
     *
     *
     * Expected Result: Player Endorsement must be Deleted successfully
     *
     */

    @Test(priority = 27)
    public void testEMADeletePlayerEndorsement()
    {
        sleep(2000);
        IM10EndorseManagerAdmin.clickDelete(driver);
        sleep(1000);
        IM10EndorseManagerAdmin.clickYes(driver);
        sleep(1000);

        String deleteAction = IM10EndorseManagerAdmin.getDeleteActionMsg(driver);
        String expDeleteAction = configReader.getExpDeleteEA();

        try {
            Assert.assertEquals(deleteAction, expDeleteAction);
            testData.put(reportRow++, new Object[]{testOrder++, "test_delete_player_endorsement", "Endorsement Manager Admin", "Delete Action Message", deleteAction, expDeleteAction, "Pass"});
            passedEndorsementManagerAdminTests.add(getMethodName);
        } catch (Throwable e) {
            System.out.println("Fail to delete player" + e);
            testData.put(reportRow++, new Object[]{testOrder++, "test_delete_player_endorsement", "Endorsement Manager Admin", "Delete Action Message", deleteAction, expDeleteAction, "Fail"});
            getMethodName = new Object() {}.getClass().getEnclosingMethod().getName();
            failedEndorsementManagerAdminTests.add(getMethodName);
            flagEndorsementManagerAdmin = false;
        }
        IM10EndorseManagerAdmin.clickOkDC(driver);
        sleep(2000);
    }

    /*
     * Test Case: 28
     *
     * Summary: Verify Logout functionality for Endorsement Manager Admin
     *
     * Prerequisite: 1. User must have valid URL
     *               2. Valid login credentials to perform the given test
     *
     * Steps: 1. Click on Account Profile button
     *        2. Click Logout
     *
     *
     * Expected Result: Endorsement Manager Admin user must be logout successfully
     *
     */

    @Test(priority = 28)
    public void testEMALogOut()
    {
        IM10EndorseManagerAdmin.clickAccountCircle(driver);
        sleep(2000);
        IM10EndorseManagerAdmin.clickLogOut(driver);
        sleep(2000);

        String logOutActionMsg = IM10EndorseManagerAdmin.getLogOutActionMsg(driver);
        String expLogOutActionMsg = configReader.getExpectedLogoutEA();

        // Asserting add user result and storing test result
        try {
            Assert.assertEquals(logOutActionMsg, expLogOutActionMsg);
            testData.put(reportRow++, new Object[]{testOrder++, "test_endorsement_admin_logout", "Endorsement Manager Admin", "Logout Action", logOutActionMsg, expLogOutActionMsg, "Pass"});
            passedEndorsementManagerAdminTests.add(getMethodName);

        } catch (Throwable e) {
            System.out.println(e);
            testData.put(reportRow++, new Object[]{testOrder++, "test_endorsement_admin_logout", "Endorsement Manager Admin", "Logout Action", logOutActionMsg, expLogOutActionMsg, "Fail"});
            getMethodName = new Object() {}.getClass().getEnclosingMethod().getName();
            failedEndorsementManagerAdminTests.add(getMethodName);
            flagEndorsementManagerAdmin = false;
        }
    }


    @Test(priority = 29)
    public void testSPADeleteListingList() throws InterruptedException {

        LoginUtility.login(driver, "SALES_PERSON_ADMIN");

        SalesPersonAdmin.selectPlayerDelete(driver);
        sleep(4000);
        SalesPersonAdmin.selectListingListForDelete(driver);
        sleep(4000);
        SalesPersonAdmin.clickListingDelete(driver);
        sleep(3000);
        SalesPersonAdmin.clickYes(driver);
        sleep(4000);

        String deleteAction = SalesPersonAdmin.getDeleteActionMsg(driver);
        String expDeleteAction = "Listing Deleted Successfully!";

        try {
            Assert.assertEquals(deleteAction, expDeleteAction);
            testData.put(reportRow++, new Object[]{testOrder++, "test_delete_Listing", "Sales Person Admin", "Delete Action Message", deleteAction, expDeleteAction, "Pass"});
            passedSalesPersonAdminTests.add(getMethodName);

        } catch (Throwable e) {
            System.out.println("Fail to delete content" + e);
            testData.put(reportRow++, new Object[]{testOrder++, "test_delete_Listing", "Sales Person Admin", "Delete Action Message", deleteAction, expDeleteAction, "Fail"});
            getMethodName = new Object() {}.getClass().getEnclosingMethod().getName();
            failedSalesPersonAdminTests.add(getMethodName);
            flagSalesPersonAdmin = false;
        }
        SalesPersonAdmin.clickOkDelete(driver);
    }


    @Test(priority = 30)
    public void testSPALogOutAfterDelete()
    {
        SalesPersonAdmin.clickAccountCircle(driver);
        sleep(2000);
        SalesPersonAdmin.clickLogOut(driver);
        sleep(2000);

        String logOutActionMsg = SalesPersonAdmin.getLogOutActionMsg(driver);
        String expLogOutActionMsg = "LOGIN";

        // Asserting add user result and storing test result
        try {
            Assert.assertEquals(logOutActionMsg, expLogOutActionMsg);
            testData.put(reportRow++, new Object[]{testOrder++, "test_Sales_Person_Admin", "Sales Person Admin", "Logout Action", logOutActionMsg, expLogOutActionMsg, "Pass"});
            passedSalesPersonAdminTests.add(getMethodName);

        } catch (Throwable e) {
            System.out.println(e);
            testData.put(reportRow++, new Object[]{testOrder++, "test_Sales_Person_logout", "Sales Person Admin", "Logout Action", logOutActionMsg, expLogOutActionMsg, "Fail"});
            getMethodName = new Object() {}.getClass().getEnclosingMethod().getName();
            failedSalesPersonAdminTests.add(getMethodName);
            flagSalesPersonAdmin = false;
        }
    }

    /*
     * Test Case: 31
     *
     * Summary: Verify Delete Player functionality for IM10 Sales Admin
     *
     * Prerequisite: 1. User must have valid URL
     *               2. Valid login credentials to perform the given test
     *               3. Player must be added successfully
     *
     * Steps: 1. Click on Delete button
     *        2. Confirm delete action by clicking "Yes" button
     *
     *
     * Expected Result: Player must be Deleted successfully
     *
     */

    @Test(priority = 31)
    public void testIM10SADeletePlayer() throws InterruptedException {

        LoginUtility.login(driver,"IM10_SALES_ADMIN");

        IM10SalesAdmin.clickPlayerList(driver);
        sleep(2000);
        IM10SalesAdmin.clickDelete(driver);
        sleep(1000);
        IM10SalesAdmin.clickYes(driver);
        sleep(1000);

        String deleteAction = IM10SalesAdmin.getDeleteActionMsg(driver);
        String expDeleteAction = configReader.getExpDeleteIM10SA();

        try {
            Assert.assertEquals(deleteAction, expDeleteAction);
            testData.put(reportRow++, new Object[]{testOrder++, "test_delete_player", "IM10 Sales Admin", "Delete Action Message", deleteAction, expDeleteAction, "Pass"});
            passedIM10SalesAdminTests.add(getMethodName);

        } catch (Throwable e) {
            System.out.println("Fail to delete player" + e);
            testData.put(reportRow++, new Object[]{testOrder++, "test_delete_player", "IM10 Sales Admin", "Delete Action Message", deleteAction, expDeleteAction, "Fail"});
            getMethodName = new Object() {}.getClass().getEnclosingMethod().getName();
            failedIM10SalesAdminTests.add(getMethodName);
            flagIM10SalesAdmin = false;
        }
        IM10SalesAdmin.clickOkDP(driver);
        sleep(4000);
    }

    @Test(priority = 32)
    public void testIM10SADeleteLogOut() {
        IM10SalesAdmin.clickAccountCircle(driver);
        sleep(2000);
        IM10SalesAdmin.clickLogOut(driver);
        sleep(2000);
        String logOutActionMsg = IM10SalesAdmin.getLogOutActionMsg(driver);
        String expLogOutActionMsg = configReader.getExpectedLogoutIM10SA();

        // Asserting add user result and storing test result
        try {
            Assert.assertEquals(logOutActionMsg, expLogOutActionMsg);
            testData.put(reportRow++, new Object[]{testOrder++, "test_IM10_sales_admin_logout", "IM10 Sales Admin", "Logout Action", logOutActionMsg, expLogOutActionMsg, "Pass"});
            passedIM10SalesAdminTests.add(getMethodName);

        } catch (Throwable e) {
            System.out.println(e);
            testData.put(reportRow++, new Object[]{testOrder++, "test_IM10_sales_admin_logout", "IM10 Sales Admin", "Logout Action", logOutActionMsg, expLogOutActionMsg, "Fail"});
            getMethodName = new Object() {}.getClass().getEnclosingMethod().getName();
            failedIM10SalesAdminTests.add(getMethodName);
            flagIM10SalesAdmin = false;
        }
    }

    /*
     * Test Case: 33
     *
     * Summary: Verify Delete User functionality for Super Admin
     *
     * Prerequisite: 1. User must have valid URL
     *               2. Valid login credentials to perform the given test
     *               3. User must be added successfully
     *
     * Steps: 1. Click on Delete button
     *        2. Confirm delete action by clicking "Yes" button
     *
     * Expected Result: User must be Deleted successfully
     *
     */



    @Test(priority = 33)
    public void testSaDeleteMultipleUsers() throws InterruptedException
    {
        LoginUtility.login(driver, "SUPER_ADMIN");

        // List of users to delete (example: assuming you have a list of usernames)
        List<String> usersToDelete = Arrays.asList("testCA@gmail.com", "testIM10SA@gmail.com", "testEMA@gmail.com", "testSPA@gmail.com", "testQA@gmail.com", "testIM10MAC@gmail.com");
        Set<String> resultOfTestDeleteUser = new HashSet<>();
        Boolean flagResultOfTestDeleteUser = false;
        String deleteActionMessage="";
        String expDeleteActionMsg="";

        for (String users : usersToDelete)
        {

            // Assuming IM10SuperAdmin.clickUser(driver, user) selects the specific user
            IM10SuperAdmin.clickUserDelete(driver, users);
            sleep(4000);
            IM10SuperAdmin.clickDelete(driver, users);
            sleep(4000);
            IM10SuperAdmin.clickYes(driver);
            sleep(1000);
            deleteActionMessage = IM10SuperAdmin.getDeleteActionMsg(driver);
            expDeleteActionMsg = "User Deleted Successfully!";
            getMethodName = new Object() {}.getClass().getEnclosingMethod().getName();

            try
            {
                Assert.assertEquals(deleteActionMessage, expDeleteActionMsg);
                flagResultOfTestDeleteUser = true;
                resultOfTestDeleteUser.add(flagResultOfTestDeleteUser.toString());

            }
            catch (Throwable e)
            {
                System.out.println(e);
                resultOfTestDeleteUser.add(flagResultOfTestDeleteUser.toString());
            }
            sleep(2000);
            IM10SuperAdmin.clickOkDelete(driver);

        }
        if (!(resultOfTestDeleteUser.contains("false")))
        {
            testData.put(reportRow++, new Object[]{testOrder++, "test_delete_user", "Super Admin", "User Delete Message", deleteActionMessage, expDeleteActionMsg, "Pass"});
            passedSuperAdminTests.add(getMethodName);
        }
        else
        {
            testData.put(reportRow++, new Object[]{testOrder++, "test_delete_user", "Super Admin", "User Delete Message", deleteActionMessage, expDeleteActionMsg, "Fail"});
            failedSuperAdminTests.add(getMethodName);
            flagSuperAdmin = false;
        }
    }


    @AfterClass
    public void testCleanup() throws IOException {
        driver.quit();
        String timestamp = getCurrentTimestamp();

        String endToEndReportFileName = "IM10E2EReport.xlsx".replace(".xlsx", "_" + timestamp + ".xlsx");

        String summaryReportFileName = "SummaryReport.xlsx".replace(".xlsx", "_" + timestamp + ".xlsx");

        String testReportdirectory = "D:\\Automation Testing\\PageObjectModel\\report\\testReport";

        String summaryReportdirectory = "D:\\Automation Testing\\PageObjectModel\\report\\summaryReport";

        // E2E Report Of IM10 UI Test Automation
        ExcelReportGenerator.generateExcelReport("TestReport", endToEndReportFileName, testReportdirectory, testData);

        if(flagSuperAdmin && flagContentAdmin && flagIM10SalesAdmin && flagEndorsementManagerAdmin && flagSalesPersonAdmin && flagQAAdmin && flagMarketingCampaigns)
        {
            summaryData.put(summaryRow++, new Object[]{"Admin_Test_Suite", "Result"});
        }
        else
        {
            summaryData.put(summaryRow++, new Object[]{"Admin_Test_Suite", "Result", "Failed_Test"});
        }

        getSummaryData(summaryData,summaryRow++,flagSuperAdmin,"Super Admin",failedSuperAdminTests, passedSuperAdminTests);
        getSummaryData(summaryData,summaryRow++,flagContentAdmin,"Content Admin",failedContentAdminTests, passedContentAdminTests);
        getSummaryData(summaryData,summaryRow++,flagEndorsementManagerAdmin,"Endorsement Manager Admin",failedEndorsementManagerAdminTests, passedEndorsementManagerAdminTests);
        getSummaryData(summaryData,summaryRow++,flagIM10SalesAdmin,"IM10Sales Admin",failedIM10SalesAdminTests, passedIM10SalesAdminTests);
        getSummaryData(summaryData,summaryRow++,flagSalesPersonAdmin,"Sales Person Admin",failedSalesPersonAdminTests, passedSalesPersonAdminTests);
        getSummaryData(summaryData,summaryRow++,flagQAAdmin,"Quality Assurance Admin",failedQAAdminTests, passedQAAdminTests);
        getSummaryData(summaryData,summaryRow++,flagMarketingCampaigns,"IM10 Marketing Campaigns Admin",failedMarketingCampaignsTests, passedMarketingCampaignsTests);

        // Summary Report Of IM10 UI Test Automation
        ExcelReportGenerator.generateExcelReport("SummaryReport", summaryReportFileName, "D:\\Automation Testing\\PageObjectModel\\report\\summaryReport", summaryData);

        sendEmail.sendEmail(
                "sandhya@meshbaco.com",
                "sandhya@meshbaco.com",
                "IM10 UI Test Automation Report",
                "Dear Dipali,\n\n" +
                        "Summary_Report_IM10_UI_Test_Automation:\n\n" +
                        sendSummaryReport.readExcel(summaryReportdirectory + "\\" + summaryReportFileName) + "\n" +
                        "Please find E2E report of IM 10 UI Test Automation.\n" +
                        "Please review it and provide your valuable feedback to generate more tests.\n\n" +
                        "Thanks and Regards,\n" +
                        "Sandhya Jadhao\n" +
                        "QA Test Engineer", testReportdirectory+"\\"+endToEndReportFileName);
        }

    private static String getCurrentTimestamp() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd_MMM_yyyy - hh_mm_ss_a");
        return now.format(formatter);
    }


    private void getSummaryData(HashMap<Integer, Object[]> summary, int rowNumber, boolean flag, String adminName, List<String> failedMethods, List<String> passedMethods)
    {
        if (!(passedMethods.isEmpty()) || !(failedMethods.isEmpty())) {

            if (flag) {
                summary.put(rowNumber, new Object[]{adminName, "Pass"});
            }
            else {
                summary.put(rowNumber, new Object[]{adminName, "Fail", failedMethods.toString()});
            }
        }
        else
        {
            System.out.println("Test run not done from test suite");
        }
    }

}
