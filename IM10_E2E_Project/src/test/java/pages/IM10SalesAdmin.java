package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class IM10SalesAdmin
{
    protected WebDriver driver;

    // Locators for elements on the page
    private By PlayerList = By.xpath("//*[@id=\"app\"]/app-admin/mat-drawer-container/mat-drawer/div/perfect-scrollbar/div/div[1]/app-admin-menu/div[2]");

    private By Add = By.xpath("//button[@ng-reflect-router-link=\"../addeditplayerdetails\"]");

    private By FirstName = By.xpath("//input[@formcontrolname=\"firstName\"]");

    private By LastName = By.xpath("//input[@formcontrolname=\"lastName\"]");

    private By Sport = By.xpath("//mat-select[@formcontrolname=\"sportId\"]");

    private By SportId = By.xpath("//span[contains(text(),'Cricket')]");

    private By DOB = By.xpath("//input[@formcontrolname=\"dob\"]");

    private By Address = By.xpath("//textarea[@formcontrolname=\"address\"]");

    private By ProfilePhoto = (By.xpath("(//input[@type='file'])[1]"));

    private By CropProfilePic = (By.xpath("//mat-dialog-container[@id=\"mat-dialog-3\"]"));

    private By SaveCrop = (By.xpath("/html/body/app-root/div/div/div[2]/div/mat-dialog-container/app-image-cropper-dialog-component/div/mat-dialog-actions/button[2]"));

    private By AadhaarCard = (By.xpath("(//input[@type='file'])[2]"));

    private By PANCard = (By.xpath("(//input[@type='file'])[3]"));

    private By VotingCard = (By.xpath("(//input[@type='file'])[4]"));

    private By DrivingLicense = (By.xpath("(//input[@type='file'])[5]"));

    private By BankAccNo = By.xpath("//input[@formcontrolname=\"bankAcountNo\"]");

    private By PANCardNo = By.xpath("//input[@formcontrolname=\"pancardNo\"]");

    private By Submit = By.xpath("//button[@type=\"submit\"]");

    private By SaveActionMsg = By.xpath("//*[contains(text(),'Player Added Successfully!')]");

    private By OkAD = By.xpath("//button[@class=\"crbtn conf2\"]");

    private By OkDP = By.xpath("//button[@class=\"crbtn conf2\"]");

    private By Delete = By.xpath("//*[@id=\"app\"]/app-admin/mat-drawer-container/mat-drawer-content/app-listplayerdetails/div/mat-card/div[2]/div[2]/div[9]/button");

    private By ConfirmActionYes = By.xpath("//button[@class=\"crbtn conf2\"]");

    private By DeleteActionMsg = By.xpath("//*[contains(text(),'Player Deleted Successfully!')]");

    private By AccountCircle = By.xpath("//button[@class=\"mat-focus-indicator mat-menu-trigger mat-icon-button mat-button-base\"]");

    private By LogOut = By.xpath("//a[@href=\"#/login\"]");

    private By LogOutMsg = By.xpath("//*[contains(text(),'LOGIN')]");

    // Constructor to initialize the WebDriver
    public IM10SalesAdmin(WebDriver driver)
    {
        this.driver = driver;
    }

    // Clicks the Player List button
    public void clickPlayerList(WebDriver driver)
    {
        driver.findElement(PlayerList).click();
    }

    // Clicks the Add button
    public void clickAddButton(WebDriver driver)
    {
        driver.findElement(Add).click();
    }

    // Enters the first name
    public void enterFirstName(WebDriver driver, String firstName)
    {
        driver.findElement(FirstName).sendKeys(firstName);
    }

    // Enters the last name
    public void enterLastName(WebDriver driver, String lastName)
    {
        driver.findElement(LastName).sendKeys(lastName);
    }

    // Clicks on the Sport dropdown
    public void clickSport(WebDriver driver)
    {
        driver.findElement(Sport).click();
    }

    // Selects the Sport ID
    public void selectSportId(WebDriver driver, String sportId) throws InterruptedException
    {
        List<WebElement> listOfSportID = driver.findElements(SportId);

        for (WebElement sportIDSelect : listOfSportID)
        {
            String sportValue = sportIDSelect.getText();
            if (sportValue.equals(sportId))
            {
                sportIDSelect.click();
                Thread.sleep(1000);
                return;
            }
        }
    }

    // Enters the Date of Birth
    public void enterDOB(WebDriver driver, String dob)
    {
        driver.findElement(DOB).sendKeys(dob);
    }

    // Enters the address
    public void enterAddress(WebDriver driver, String address)
    {
        driver.findElement(Address).sendKeys(address);
    }

    // Selects the profile photo
    public void selectProfilePhoto(WebDriver driver, String profileImageFileName)
    {
        driver.findElement(ProfilePhoto).sendKeys(profileImageFileName);
    }
    // Save cropped profile pic
    public void clickSaveCrop(WebDriver driver)
    {
        driver.findElement(SaveCrop).click();
    }
    // Selects the Aadhaar Card
    public void selectAadhaarCard(WebDriver driver, String aadhaarCardFileName)
    {
        driver.findElement(AadhaarCard).sendKeys(aadhaarCardFileName);
    }

    // Selects the PAN Card
    public void selectPANCard(WebDriver driver, String panCardFileName)
    {
        driver.findElement(PANCard).sendKeys(panCardFileName);
    }

    // Selects the Voting Card
    public void selectVotingCard(WebDriver driver, String votingCardFileName)
    {
        driver.findElement(VotingCard).sendKeys(votingCardFileName);
    }

    // Selects the Driving License
    public void selectDrivingLicense(WebDriver driver, String drivingLicenceFileName)
    {
        driver.findElement(DrivingLicense).sendKeys(drivingLicenceFileName);
    }

    // Enters the bank account number
    public void enterBankAccNo(WebDriver driver, String bankAccountNo)
    {
        driver.findElement(BankAccNo).sendKeys(bankAccountNo);
    }

    // Enters the PAN Card number
    public void enterPANCardNo(WebDriver driver, String panCardNo)
    {
        driver.findElement(PANCardNo).sendKeys(panCardNo);
    }

    // Clicks the Submit button
    public void clickSubmit(WebDriver driver)
    {
        driver.findElement(Submit).click();
    }

    // Gets the success message after saving
    public String getSaveActionMsg(WebDriver driver)
    {
        return driver.findElement(SaveActionMsg).getText();
    }

    // Clicks the Ok button
    public void clickOkAP(WebDriver driver)
    {
        driver.findElement(OkAD).click();
    }

    public void clickOkDP(WebDriver driver)
    {
        driver.findElement(OkDP).click();
    }

    // Clicks the Delete button
    public void clickDelete(WebDriver driver)
    {
        driver.findElement(Delete).click();
    }

    // Clicks the Yes button to confirm action
    public void clickYes(WebDriver driver)
    {
        driver.findElement(ConfirmActionYes).click();
    }

    public String getDeleteActionMsg(WebDriver driver)
    {
        return driver.findElement(DeleteActionMsg).getText();
    }

    public void clickAccountCircle(WebDriver driver)
    {
        driver.findElement(AccountCircle).click();
    }

    public void clickLogOut(WebDriver driver)
    {
        driver.findElement(LogOut).click();
    }

    public String getLogOutActionMsg(WebDriver driver)
    {
        return driver.findElement(LogOutMsg).getText();
    }
}
