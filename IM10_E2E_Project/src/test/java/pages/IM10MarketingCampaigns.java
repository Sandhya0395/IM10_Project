package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class IM10MarketingCampaigns
{
    protected WebDriver driver;

    // Locators for elements on the page
    private By PlayerSelection = By.xpath("//*[@id=\"app\"]/app-admin/mat-drawer-container/mat-drawer-content/app-startingpage/div/div[1]");

    private By Campaign = By.xpath("//a[@id=\"menu-item-31\"]");

    private By Add = By.xpath("//*[@id=\"app\"]/app-admin/mat-drawer-container/mat-drawer-content/app-listcampaign/div/mat-card/div[1]/table/tr/td[2]/button");

    private By Title = By.xpath("//input[@formcontrolname=\"title\"]");

    private By ContentTitle = By.xpath("//mat-select[@formcontrolname=\"contentId\"]");

    private By ContentTitleId = By.xpath("//span[@class='mat-option-text']//span[contains(text(),'TestTitle')]");

    private By ContentType = By.xpath("//mat-select[@formcontrolname=\"contentTypeId\"]");

    private By ContentTypeId = By.xpath("//span[@class='mat-option-text']//span[contains(text(),'Video')]");

    private By Description = By.xpath("//textarea[@formcontrolname=\"description\"]");

    private By Submit = By.xpath("//button[@type=\"submit\"]");

    private By SaveActionMsg = By.xpath("//*[contains(text(),'Campaign Added Successfully!')]");

    private By OkSaveAction = By.xpath("//button[@class=\"crbtn conf2\"]");

    private By Delete = By.xpath("//*[@id=\"app\"]/app-admin/mat-drawer-container/mat-drawer-content/app-listcampaign/div/mat-card/div[2]/div[2]/div[5]/button");

    private By ConfirmYes = By.xpath("//button[@class=\"crbtn conf2\"]");

    private By DeleteActionMsg = By.xpath("//*[contains(text(),'Campaign Deleted Successfully!')]");

    private By OkDC = By.xpath("//button[@class=\"crbtn conf2\"]");

    private By AccountCircle = By.xpath("//button[@aria-haspopup=\"true\"]");

    private By LogOut = By.xpath("//a[@href=\"#/login\"]");

    private By LogOutMsg = By.xpath("//*[contains(text(),'LOGIN')]");

    public IM10MarketingCampaigns(WebDriver driver)
    {
        this.driver = driver;
    }

    public void clickPlayer(WebDriver driver)
    {
        driver.findElement(PlayerSelection).click();
    }

    public void clickCampaign(WebDriver driver)
    {
        driver.findElement(Campaign).click();
    }

    public void clickAddButton(WebDriver driver)
    {
        driver.findElement(Add).click();
    }

    public void enterTitle(WebDriver driver, String title)
    {
        driver.findElement(Title).sendKeys(title);
    }

    public void clickContentTitle(WebDriver driver)
    {
        driver.findElement(ContentTitle).click();
    }

    public void selectContentTitleId(WebDriver driver, String contentTitle) throws InterruptedException
    {
        List<WebElement> listOfContentTitleID = driver.findElements(ContentTitleId);

        for (WebElement contentTitleIDSelect : listOfContentTitleID)
        {
            String contentTitleValue = contentTitleIDSelect.getText();
            if (contentTitleValue.equals(contentTitle))
            {
                contentTitleIDSelect.click();
                Thread.sleep(1000);
                return;
            }
        }
    }


    public void clickContentType(WebDriver driver)
    {
        driver.findElement(ContentType).click();
    }

    public void selectContentTypeId(WebDriver driver, String contentType) throws InterruptedException
    {
        List<WebElement> listOfContentTypeID = driver.findElements(ContentTypeId);

        for (WebElement contentTypeIDSelect : listOfContentTypeID)
        {
            String contentTypeValue = contentTypeIDSelect.getText();
            if (contentTypeValue.equals(contentType))
            {
                contentTypeIDSelect.click();
                Thread.sleep(1000);
                return;
            }
        }
    }

    public void enterDescription(WebDriver driver, String title)
    {
        driver.findElement(Description).sendKeys(title);
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
    public void clickOkSaveAction(WebDriver driver)
    {
        driver.findElement(OkSaveAction).click();
    }

    public void clickDelete(WebDriver driver)
    {
        driver.findElement(Delete).click();
    }

    public void clickYes(WebDriver driver)
    {
        driver.findElement(ConfirmYes).click();
    }

    public String getDeleteActionMsg(WebDriver driver)
    {
        return driver.findElement(DeleteActionMsg).getText();
    }

    public void clickOkDC(WebDriver driver)
    {
        driver.findElement(OkDC).click();
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
