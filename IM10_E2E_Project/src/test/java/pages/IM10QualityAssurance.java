package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class IM10QualityAssurance {


    protected WebDriver driver;

    // Locators for elements on the page
    private By PlayerSelection = By.xpath("//*[@id=\"app\"]/app-admin/mat-drawer-container/mat-drawer-content/app-startingpage/div/div[1]");

    private By Content = By.xpath("//a[@id=\"menu-item-17\"]");

    private By ApproveAction  = By.xpath("//*[@id=\"app\"]/app-admin/mat-drawer-container/mat-drawer-content/app-listcontent/div/mat-card/div[2]/div[2]/div[8]/button[1]");

    private By ApproveActionMsg = By.xpath("//*[contains(text(),'Are you sure you want approve this content?')]");

    private By ApproveYes = By.xpath("//button[@class=\"crbtn conf2\"]");

    private By ResultMsg = By.xpath("//*[contains(text(),'Content Approved Successfully!')]");

    private By ResultOk = By.xpath("//button[@class=\"crbtn conf2\"]");

    private By AccountCircle = By.xpath("//button[@aria-haspopup=\"true\"]");

    private By LogOut = By.xpath("//a[@href=\"#/login\"]");

    private By LogOutMsg = By.xpath("//*[contains(text(),'LOGIN')]");

    private By DisapproveAction  = By.xpath("//*[@id=\"app\"]/app-admin/mat-drawer-container/mat-drawer-content/app-listcontent/div/mat-card/div[2]/div[2]/div[8]/button[2]");

    private By DisapproveActionMsg = By.xpath("//*[contains(text(),'Enter comment for why this not approve')]");

    private By EnterComment = By.xpath("//textarea[@name=\"comment\"]");

    private By Submit = By.xpath("//button[@class=\"crbtn conf2\"]");

    private By DisapproveSuccessfully = By.xpath("//*[contains(text(),' Content List')]");


    public IM10QualityAssurance(WebDriver driver)
    {
        this.driver = driver;
    }

    public void clickPlayer(WebDriver driver)
    {
        driver.findElement(PlayerSelection).click();
    }

    public void clickContent(WebDriver driver)
    {
        driver.findElement(Content).click();
    }

    public void clickApproveAction(WebDriver driver)
    {
        driver.findElement(ApproveAction).click();
    }

    public String getApproveActionMsg(WebDriver driver)
    {
        return driver.findElement(ApproveActionMsg).getText();
    }

    public void clickApproveYes(WebDriver driver)
    {
        driver.findElement(ApproveYes).click();
    }

    public String getResultMsg(WebDriver driver)
    {
        return driver.findElement(ResultMsg).getText();
    }

    public void clickResultOk(WebDriver driver)
    {
        driver.findElement(ResultOk).click();
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

    public void clickDisapproveAction(WebDriver driver)
    {
        driver.findElement(DisapproveAction).click();
    }

    public String getDisapproveActionMsg(WebDriver driver)
    {
        return driver.findElement(DisapproveActionMsg).getText();
    }

    public void enterComment(WebDriver driver)
    {
        driver.findElement(EnterComment).sendKeys("Comment");
    }

    public void clickSubmit(WebDriver driver)
    {
        driver.findElement(Submit).click();
    }

    public String getDisapproveMsg(WebDriver driver)
    {
        return driver.findElement(DisapproveSuccessfully).getText();
    }
}
