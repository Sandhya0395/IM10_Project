package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class IM10ContentAdmin
{
    protected WebDriver driver;

    // Locators for elements on the page
    private By PlayerSelection = By.xpath("//*[@id=\"app\"]/app-admin/mat-drawer-container/mat-drawer-content/app-startingpage/div/div[1]");

    private By Content = By.xpath("//a[@id=\"menu-item-14\"]");

    private By Add = By.xpath("//*[@id=\"app\"]/app-admin/mat-drawer-container/mat-drawer-content/app-listcontent/div/mat-card/div[1]/table/tr/td[2]/button");

    private By Title = By.xpath("//input[@formcontrolname=\"title\"]");

    private By Description = By.xpath("//textarea[@formcontrolname=\"description\"]");

    private By ContentType = By.xpath("//mat-select[@formcontrolname=\"contentTypeId\"]");

    private By ContentTypeId = By.xpath("//span[@class='mat-option-text']//span[contains(text(),'Video')]");

    private By Thumbnail = By.xpath("//input[@id=\"fileInput\"]");

    private By ContentPart1 = By.xpath("//input[@id=\"contentFileInput\"]");

    private By ContentPart2 = By.xpath("//*[@id=\"app\"]/app-admin/mat-drawer-container/mat-drawer-content/app-addeditcontent/div/mat-card/form/mat-card-content/div/div[2]/div[3]/div/input");

    private By Category = (By.xpath("//mat-select[@formcontrolname=\"categoryId\"]"));

    private By CategoryId = (By.xpath("//span[@class='mat-option-text']//span[contains(text(),'Batting')]"));

    private By SubCategory = (By.xpath("//mat-select[@formcontrolname=\"subCategoryId\"]"));

    private By SubCategoryId = (By.xpath("//span[@class='mat-option-text']//span[contains(text(),'Front Foot Defence')]"));

    private By Language = (By.xpath("//mat-select[@formcontrolname=\"languageId\"]"));

    private By LanguageId = (By.xpath("//span[contains(text(),'English')]"));

    private By Submit = By.xpath("//button[@type=\"submit\"]");

    private By SaveActionMsg = By.xpath("//*[contains(text(),'Content Added Successfully!')]");

    private By OkSaveAction = By.xpath("//button[@class=\"crbtn conf2\"]");

    private By Delete = By.xpath("//*[@id=\"app\"]/app-admin/mat-drawer-container/mat-drawer-content/app-listcontent/div/mat-card/div[2]/div[2]/div[8]/button[2]");

    private By ConfirmYes = By.xpath("//button[@class=\"crbtn conf2\"]");

    private By DeleteActionMsg = By.xpath("//*[contains(text(),'Content Deleted Successfully!')]");

    private By OkDC = By.xpath("//button[@class=\"crbtn conf2\"]");

    private By AccountCircle = By.xpath("//button[@aria-haspopup=\"true\"]");

    private By LogOut = By.xpath("//a[@href=\"#/login\"]");

    private By LogOutMsg = By.xpath("//*[contains(text(),'LOGIN')]");

    public IM10ContentAdmin(WebDriver driver)
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

    public void clickAddButton(WebDriver driver)
    {
        driver.findElement(Add).click();
    }

    // Enters the first name
    public void enterTitle(WebDriver driver, String title)
    {
        driver.findElement(Title).sendKeys(title);
    }

    // Enters the last name
    public void enterDescription(WebDriver driver, String description)
    {
        driver.findElement(Description).sendKeys(description);
    }

    // Clicks on the Sport dropdown
    public void clickContentType(WebDriver driver)
    {
        driver.findElement(ContentType).click();
    }

    public void selectContentTypeId(WebDriver driver, String contentType) throws InterruptedException
    {
        List<WebElement> listOfContentID = driver.findElements(ContentTypeId);

        for (WebElement contentIDSelect : listOfContentID)
        {
            String contentValue = contentIDSelect.getText();
            if (contentValue.equals(contentType))
            {
                contentIDSelect.click();
                Thread.sleep(1000);
                return;
            }
        }
    }

    public void selectThumbnail(WebDriver driver, String testThumbnail)
    {
        driver.findElement(Thumbnail).sendKeys(testThumbnail);
    }

    public void selectContentPart1(WebDriver driver, String testContentPart1)
    {
        driver.findElement(ContentPart1).sendKeys(testContentPart1);
    }

    public void selectContentPart2(WebDriver driver, String testContentPart2)
    {
        driver.findElement(ContentPart2).sendKeys(testContentPart2);
    }

    public void clickCategory(WebDriver driver)
    {
        driver.findElement(Category).click();
    }

    public void selectCategoryId(WebDriver driver, String category) throws InterruptedException
    {
        List<WebElement> listOfCategory = driver.findElements(CategoryId);

        for (WebElement categoryIdSelect : listOfCategory)
        {
            String categoryValue = categoryIdSelect.getText();
            if (categoryValue.equals(category))
            {
                categoryIdSelect.click();
                Thread.sleep(1000);
                return;
            }
        }
    }

    public void clickSubCategory(WebDriver driver)
    {
        driver.findElement(SubCategory).click();
    }

    public void selectSubCategoryId(WebDriver driver, String subCategory) throws InterruptedException
    {
        List<WebElement> listOfSubCategory = driver.findElements(SubCategoryId);

        for (WebElement subCategoryIdSelect : listOfSubCategory)
        {
            String subCategoryValue = subCategoryIdSelect.getText();
            if (subCategoryValue.equals(subCategory))
            {
                subCategoryIdSelect.click();
                Thread.sleep(1000);
                return;
            }
        }
    }
    public void clickLanguage(WebDriver driver)
    {
        driver.findElement(Language).click();
    }

    public void selectLanguageId(WebDriver driver, String language) throws InterruptedException
    {
        List<WebElement> listOfLanguage = driver.findElements(LanguageId);

        for (WebElement languageIdSelect : listOfLanguage)
        {
            String languageValue = languageIdSelect.getText();
            if (languageValue.equals(language))
            {
                languageIdSelect.click();
                Thread.sleep(1000);
                return;
            }
        }
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
