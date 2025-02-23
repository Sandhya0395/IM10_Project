package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class IM10EndorseManagerAdmin {
    protected WebDriver driver;

    // Locators for elements on the page
    private By PlayerSelection = By.xpath("//*[@id=\"app\"]/app-admin/mat-drawer-container/mat-drawer-content/app-startingpage/div/div[1]");

    private By PlayerEndorsement = By.xpath("//*[@id=\"menu-item-34\"]");

    private By Add = By.xpath("//button[@ng-reflect-router-link=\"../addeditplayerendorsement\"]");

    private By CompanyName = By.xpath("//mat-select[@formcontrolname=\"companyname\"]");

    private By CompanyId = By.xpath("//*[contains(text(),'testBrandName')]");

    private By EndorseType = By.xpath("//mat-select[@formcontrolname=\"Endosrsement\"]");

    private By EndorseTypeId = By.xpath("//*[contains(text(),'Event Inauguration')]");

    private By StartDate = By.xpath("//input[@formcontrolname=\"startDate\"]");

    private By EndDate = By.xpath("//input[@formcontrolname=\"endDate\"]");

    private By FinalPrice = By.xpath("//input[@formcontrolname=\"finalPrice\"]");

    private By Notes = By.xpath("//textarea[@ng-reflect-name=\"Notes\"]");

    private By Submit = By.xpath("//button[@type=\"submit\"]");

    private By SaveActionPopUp = By.xpath("//*[contains(text(),'Player Endorsement Added Successfully!')]");

    private By Ok = By.xpath("//button[@class=\"crbtn conf2\"]");

    private By Delete = By.xpath("//button[@ng-reflect-message=\"Delete\"]");

    private By ConfirmActionYes = By.xpath("//button[@class=\"crbtn conf2\"]");

    private By DeleteMsg = By.xpath("//*[contains(text(),'Player Endorsement Deleted Successfully!')]");

    private By OkDC = By.xpath("//button[@class=\"crbtn conf2\"]");

    private By AccountCircle = By.xpath("//button[@class=\"mat-focus-indicator mat-menu-trigger mat-icon-button mat-button-base\"]");

    private By LogOut = By.xpath("//a[@href=\"#/login\"]");

    private By LogOutMsg = By.xpath("//*[contains(text(),'LOGIN')]");



    // Constructor to initialize the WebDriver
    public IM10EndorseManagerAdmin(WebDriver driver)
    {
        this.driver = driver;
    }

    public void clickPlayer(WebDriver driver)
    {
        driver.findElement(PlayerSelection).click();
    }

    public void clickPlayerEndorsement(WebDriver driver)
    {
        driver.findElement(PlayerEndorsement).click();
    }

    // Clicks the Add button
    public void clickAdd(WebDriver driver)
    {
        driver.findElement(Add).click();
    }

    public void clickCompanyName(WebDriver driver)
    {
        driver.findElement(CompanyName).click();
    }

    // Selects the Role ID
    public void selectCompanyId(WebDriver driver) throws InterruptedException
    {
        List<WebElement> listOfCompanyId = driver.findElements(CompanyId);

        for (WebElement companyIDSelect : listOfCompanyId)
        {
            String companyValue = companyIDSelect.getText();
            if (companyValue.equals("testBrandName"))
            {
                companyIDSelect.click();
                Thread.sleep(1000);
                return;
            }
        }
    }

    public void clickEndorseType(WebDriver driver)
    {
        driver.findElement(EndorseType).click();
    }

    // Selects the Endorse Type ID
    public void selectEndorseTypeId(WebDriver driver) throws InterruptedException
    {
        List<WebElement> listOfEndorseTypeId = driver.findElements(EndorseTypeId);

        for (WebElement endorseTypeIDSelect : listOfEndorseTypeId)
        {
            String endorseTypeValue = endorseTypeIDSelect.getText();
            if (endorseTypeValue.equals("Event Inauguration"))
            {
                endorseTypeIDSelect.click();
                Thread.sleep(1000);
                return;
            }
        }
    }

    // Enters the start date
    public void enterStartDate(WebDriver driver)
    {
        driver.findElement(StartDate).sendKeys("6/15/2024");
    }

    // Enters the end date
    public void enterEndDate(WebDriver driver)
    {
        driver.findElement(EndDate).sendKeys("6/17/2024");
    }

    // Enters the password
    public void enterFinalPrice(WebDriver driver)
    {
        driver.findElement(FinalPrice).sendKeys("900000");
    }

    public void enterNotes(WebDriver driver)
    {
        driver.findElement(Notes).sendKeys("Not Applicable");
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

    // Clicks the Delete button
    public void clickDelete(WebDriver driver)
    {
        driver.findElement(Delete).click();
    }

    public String getDeleteActionMsg(WebDriver driver)
    {
        return driver.findElement(DeleteMsg).getText();
    }

    public void clickOkDC(WebDriver driver)
    {
        driver.findElement(OkDC).click();
    }

    // Clicks the Yes button to confirm action
    public void clickYes(WebDriver driver)
    {
        driver.findElement(ConfirmActionYes).click();
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
