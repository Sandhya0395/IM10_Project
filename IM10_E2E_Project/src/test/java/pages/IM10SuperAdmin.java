package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

public class IM10SuperAdmin
{
    protected WebDriver driver;

    // Locators for elements on the page
    private By User = By.xpath("//a[@href=\"#/admin/superadmin/listuser\"]");

    private By Add = By.xpath("//button[@ng-reflect-router-link=\"../addedituser\"]");

    private By FirstName = By.xpath("//input[@formcontrolname=\"firstName\"]");

    private By LastName = By.xpath("//input[@formcontrolname=\"lastName\"]");

    private By EmailId = By.xpath("//input[@formcontrolname=\"emailId\"]");

    private By MobileNo = By.xpath("//input[@formcontrolname=\"mobileNo\"]");

    private By DOB = By.xpath("//input[@formcontrolname=\"dob\"]");

    private By Role = By.cssSelector(".mat-form-field-hide-placeholder .mat-select-placeholder");

    private By RoleId = By.xpath("//span[@class='mat-option-text']//span");

    private By Password = By.xpath("//input[@formcontrolname=\"password\"]");

    private By Submit = By.xpath("//button[@type=\"submit\"]");

    private By SaveActionPopUp = By.xpath("//*[contains(text(), 'User Added Successfully!')]");

    private By Ok = By.xpath("//button[@class=\"crbtn conf2\"]");

    public static By getDeleteXpath(String dynamicEmail)
    {
        return By.xpath(String.format("//div[@id='app']//div[contains(text(), '%s')]/parent::*//div[@class='mat-cell text-center']//button[@ng-reflect-message='Delete']", dynamicEmail));
    }

    private By ConfirmActionYes = By.xpath("//button[@class=\"crbtn conf2\"]");

    private By DeleteActionPopUpMsg = By.xpath("//*[contains(text(), 'User Deleted Successfully!')]");

    private By OkDelete = By.xpath("//button[@class=\"crbtn conf2\"]");

    private By AccountCircle = By.xpath("//button[@class=\"mat-focus-indicator mat-menu-trigger mat-icon-button mat-button-base\"]");

    private By LogOut = By.xpath("//*[contains(text(),'Log out')]");

    private By LogOutMsg = By.xpath("//*[contains(text(),'LOGIN')]");

    private By UserPlayer = By.xpath("//a[@id=\"menu-item-12\"]");

    private By UserPlayerAdd = By.xpath("//button[@ng-reflect-color=\"primary\"]");

    private By UserDropDown = By.xpath("//mat-select[@formcontrolname=\"username\"]");

    private By UserId = By.xpath("//span[@class=\"mat-option-text\"]");

    private By PlayerName = By.xpath("//mat-select[@formcontrolname=\"playername\"]");

    private By PlayerId = By.xpath("//mat-option[@role=\"option\"]");

    private By SubmitUserMap = By.xpath("//button[@type=\"submit\"]");

    private By UPSaveAction = By.xpath("//*[contains(text(),'User Player Added Successfully!')]");

    private By OkUP = By.xpath("//button[@class=\"crbtn conf2\"]");

    // Constructor to initialize the WebDriver
    public IM10SuperAdmin(WebDriver driver)
    {
        this.driver = driver;
    }

    // Clicks the User link
    public void clickUser(WebDriver driver)
    {
        driver.findElement(User).click();
    }

    // Clicks the Add button
    public void clickAdd(WebDriver driver)
    {
        driver.findElement(Add).click();
    }

    // Enters the first name
    public void enterFirstName(WebDriver driver, String firstNames)
    {
        driver.findElement(FirstName).sendKeys(firstNames);
    }

    // Enters the last name
    public void enterLastName(WebDriver driver, String lastNames)
    {
        driver.findElement(LastName).sendKeys(lastNames);
    }

    // Enters the email ID
    public void enterEmailId(WebDriver driver, String emails)
    {
        driver.findElement(EmailId).sendKeys(emails);
    }

    // Enters the mobile number
    public void enterMobileNo(WebDriver driver, String mobileNumbers)
    {
        driver.findElement(MobileNo).sendKeys(mobileNumbers);
    }

    // Enters the date of birth
    public void enterDOB(WebDriver driver)
    {
        driver.findElement(DOB).sendKeys("5/1/2024");
    }

    // Clicks the Role dropdown
    public void clickRole(WebDriver driver)
    {
        driver.findElement(Role).click();
    }

    // Selects the Role ID
    public void selectRoleId(WebDriver driver, String adminRoles) throws InterruptedException
    {
        List<WebElement> listOffRoleID = driver.findElements(RoleId);

        for (WebElement roleIDSelect : listOffRoleID)
        {
            String roleValue = roleIDSelect.getText();
            if (roleValue.equals(adminRoles))
            {
                roleIDSelect.click();
                Thread.sleep(1000);
                return;
            }

        }
    }

    // Enters the password
    public void enterPassword(WebDriver driver)
    {
        driver.findElement(Password).sendKeys("123456");
    }

    // Clicks the Submit button
    public void clickSubmit(WebDriver driver)
    {
        driver.findElement(Submit).click();
    }

    // Gets the save action message
    public String getSaveActionMsg(WebDriver driver)
    {
        return driver.findElement(SaveActionPopUp).getText();
    }

    // Clicks the Ok button
    public void clickOk(WebDriver driver)
    {
        driver.findElement(Ok).click();
    }

    public void clickUserPlayer(WebDriver driver)
    {
        driver.findElement(UserPlayer).click();
    }

    public void clickUserPlayerAdd(WebDriver driver)
    {
        driver.findElement(UserPlayerAdd).click();
    }

    public void clickUserDropDown(WebDriver driver)
    {
        driver.findElement(UserDropDown).click();
    }


    public void selectUserId(WebDriver driver, String userId) throws InterruptedException {
        List<WebElement> listOfUserID = driver.findElements(UserId);

        for (WebElement userIDSelect : listOfUserID) {
            String userValue = userIDSelect.getText();
            if (userValue.equals(userId)) {
                userIDSelect.click();
                Thread.sleep(1000);
                return;
            }
        }
    }


    public void clickPlayerName(WebDriver driver)
    {
        driver.findElement(PlayerName).click();
    }


    public void selectPlayerId(WebDriver driver, String playerId) {
        List<WebElement> checkboxes = driver.findElements(PlayerId);

        for (WebElement checkbox : checkboxes) {
            // Example: Assuming the checkbox text or value corresponds to playerId
            if (checkbox.getText().equals(playerId)) {
                if (!checkbox.isSelected()) {
                    checkbox.click();
                }
                break;
            }
        }
        Actions actions = new Actions(driver);
        actions.sendKeys(Keys.ESCAPE).build().perform();
    }

    public void clickSubmitUserMap(WebDriver driver)
    {
        driver.findElement(SubmitUserMap).click();
    }

    public String getUPSaveActionMsg(WebDriver driver)
    {
        return driver.findElement(UPSaveAction).getText();
    }

    public void clickOkUP(WebDriver driver)
    {
        driver.findElement(OkUP).click();
    }

    public void clickUserDelete(WebDriver driver, String users)
    {
        driver.findElement(User).click();
    }

    // Clicks the Delete button
    public void clickDelete(WebDriver driver,String dynamicUser)
    {
        driver.findElement(IM10SuperAdmin.getDeleteXpath(dynamicUser)).click();
    }

    // Clicks the Yes button to confirm action
    public void clickYes(WebDriver driver)
    {
        driver.findElement(ConfirmActionYes).click();
    }

    public String getDeleteActionMsg(WebDriver driver)
    {
       return driver.findElement(DeleteActionPopUpMsg).getText();
    }

    public void clickOkDelete(WebDriver driver)
    {
        driver.findElement(OkDelete).click();
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
