package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class SalesPersonAdmin {
    protected WebDriver driver;

    private By PlayerSelection = By.xpath("//*[@id=\"app\"]/app-admin/mat-drawer-container/mat-drawer-content/app-startingpage/div/div[1]");

    private By ListingList = By.xpath("//a[@ng-reflect-router-link=\"/admin/salespersonadmin/listli\"]");

    private By Add = By.xpath("//button[@ng-reflect-router-link=\"../addeditlisting\"]");

    private By BrandName = By.xpath("//input[@formcontrolname=\"companyName\"]");

    private By Email = By.xpath("//input[@formcontrolname=\"companyEmailId\"]");

    private By Mobile = By.xpath("//input[@formcontrolname=\"companyMobile\"]");

    private By Landline = By.xpath("//input[@formcontrolname=\"companyPhone\"]");

    private By Website = By.xpath("//input[@formcontrolname=\"companyWebSite\"]");

    private By BrandLogo = By.xpath("//input[@type=\"file\"]");

    private By Description = By.xpath("//textarea[@formcontrolname=\"description\"]");

    private By Position = By.xpath("//input[@formcontrolname=\"position\"]");

    private By ContactPersonName = By.xpath("//input[@formcontrolname=\"contactPersonName\"]");

    private By ContactPersonMobile = By.xpath("//input[@formcontrolname=\"contactPersonMobile\"]");

    private By ContactPersonEmail = By.xpath("//input[@formcontrolname=\"contactPersonEmailId\"]");

    private By Country = By.xpath("//mat-select[@formcontrolname=\"nationId\"]");

    private By CountryId = By.xpath("//*[contains(text(),'India')]");

    private By State = By.xpath("//mat-select[@formcontrolname=\"stateId\"]");

    private By StateId = By.xpath("//*[contains(text(),'Maharashtra')]");

    private By City = By.xpath("//mat-select[@formcontrolname=\"cityId\"]");

    private By CityId = By.xpath("//*[contains(text(),'Pune')]");

    private By StartDate = By.xpath("//input[@formcontrolname=\"startDate\"]");

    private By EndDate = By.xpath("//input[@formcontrolname=\"endDate\"]");

    private By FinalPrice = By.xpath("//input[@formcontrolname=\"finalPrice\"]");

    private By Submit = By.xpath("//button[@type=\"submit\"]");

    private By SaveActionMsg = By.xpath("//*[contains(text(),'Listing Details Added Successfully!')]");

    private By OkSaveAction = By.xpath("//button[@class=\"crbtn conf2\"]");

    private By BrandNameDelete = By.xpath("//*[@id=\"app\"]/app-admin/mat-drawer-container/mat-drawer-content/app-listlisting/div/mat-card/div[2]/div[2]/div[9]/button[2]");

    private By ConfirmActionYes = By.xpath("//button[@class=\"crbtn conf2\"]");

    private By DeleteActionPopUpMsg = By.xpath("//*[contains(text(), 'Listing Deleted Successfully!')]");

    private By OkDelete = By.xpath("//button[@class=\"crbtn conf2\"]");

    private By AccountCircle = By.xpath("//button[@aria-haspopup=\"true\"]");

    private By LogOut = By.xpath("//a[@href=\"#/login\"]");

    private By LogOutMsg = By.xpath("//*[contains(text(),'LOGIN')]");

    public SalesPersonAdmin(WebDriver driver)
    {
        this.driver = driver;
    }

    public void clickPlayer(WebDriver driver)
    {
        driver.findElement(PlayerSelection).click();
    }

    public void selectListingList(WebDriver driver)
    {
        driver.findElement(ListingList).click();
    }

    public void clickAdd(WebDriver driver)
    {
        driver.findElement(Add).click();
    }

    public void enterBrandName(WebDriver driver, String brandName)
    {
        driver.findElement(BrandName).sendKeys(brandName);
    }

    public void enterEmail(WebDriver driver, String email)
    {
        driver.findElement(Email).sendKeys(email);
    }

    public void enterMobile(WebDriver driver, String mobile)
    {
        driver.findElement(Mobile).sendKeys(mobile);
    }

    public void enterLandLine(WebDriver driver, String landline) {
        driver.findElement(Landline).sendKeys(landline);
    }

    public void enterWebsite(WebDriver driver, String website)
    {
        driver.findElement(Website).sendKeys(website);
    }

    public void selectBrandLogo(WebDriver driver, String brandLogo)
    {
        driver.findElement(BrandLogo).sendKeys(brandLogo);
    }

    public void enterDescription(WebDriver driver, String description)
    {
        driver.findElement(Description).sendKeys(description);
    }

    public void enterPosition(WebDriver driver, String position)
    {
        driver.findElement(Position).sendKeys(position);
    }

    public void enterContactPersonName(WebDriver driver, String contactPersonName)
    {
        driver.findElement(ContactPersonName).sendKeys(contactPersonName);
    }

    public void enterContactPersonMobile(WebDriver driver, String contactPersonMobile)
    {
        driver.findElement(ContactPersonMobile).sendKeys(contactPersonMobile);
    }

    public void enterContactPersonEmail(WebDriver driver, String contactPersonEmail)
    {
        driver.findElement(ContactPersonEmail).sendKeys(contactPersonEmail);
    }

    public void clickCountry(WebDriver driver)
    {
        driver.findElement(Country).click();
    }

    public void selectCountryId(WebDriver driver, String countryId) throws InterruptedException
    {
        List<WebElement> listOfCountry = driver.findElements(CountryId);

        for (WebElement countryIdSelect : listOfCountry)
        {
            String countryValue = countryIdSelect.getText();
            if (countryValue.equals(countryId))
            {
                countryIdSelect.click();
                Thread.sleep(1000);
                return;
            }
        }
    }

    public void clickState(WebDriver driver)
    {
        driver.findElement(State).click();
    }

    public void selectStateId(WebDriver driver, String stateId) throws InterruptedException
    {
        List<WebElement> listOfState = driver.findElements(StateId);

        for (WebElement stateIdSelect : listOfState)
        {
            String stateValue = stateIdSelect.getText();
            if (stateValue.equals(stateId))
            {
                stateIdSelect.click();
                Thread.sleep(1000);
                return;
            }
        }
    }

    public void clickCity(WebDriver driver)
    {
        driver.findElement(City).click();
    }

    public void selectCityId(WebDriver driver, String cityId) throws InterruptedException
    {
        List<WebElement> listOfCity = driver.findElements(CityId);

        for (WebElement cityIdSelect : listOfCity)
        {
            String cityValue = cityIdSelect.getText();
            if (cityValue.equals(cityId))
            {
                cityIdSelect.click();
                Thread.sleep(1000);
                return;
            }
        }
    }

    public void enterStartDate(WebDriver driver, String startDate)
    {
        driver.findElement(StartDate).sendKeys(startDate);
    }

    public void enterEndDate(WebDriver driver, String endDate)
    {
        driver.findElement(EndDate).sendKeys(endDate);
    }

    public void enterFinalPrice(WebDriver driver, String finalPrice)
    {
        driver.findElement(FinalPrice).sendKeys(finalPrice);
    }

    public void clickSubmit(WebDriver driver)
    {
        driver.findElement(Submit).click();
    }

    public String getSaveActionMsg(WebDriver driver)
    {
       return driver.findElement(SaveActionMsg).getText();
    }

    public void clickOk(WebDriver driver)
    {
        driver.findElement(OkSaveAction).click();
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

    public void selectPlayerDelete(WebDriver driver)
    {
        driver.findElement(PlayerSelection).click();
    }

    public void selectListingListForDelete(WebDriver driver)
    {
        driver.findElement(ListingList).click();
    }

    // Clicks the Delete button
    public void clickListingDelete(WebDriver driver)
    {
        driver.findElement(BrandNameDelete).click();
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
}
