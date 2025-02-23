package pages;

import org.openqa.selenium.*;

public class IM10LoginPage {
    protected static WebDriver driver;

    // Locators for email and password fields
    private static final By Email = By.xpath("//input[@formcontrolname=\"emailId\"]");
    private static final By Password = By.xpath("//input[@formcontrolname=\"password\"]");

    // Locator for the submit button
    static By SubmitLink = By.cssSelector("button");

    // Constructor to initialize WebDriver
    public IM10LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    // Clicks the submit button
    public static void clickSubmitLink() {
        driver.findElement(SubmitLink).click();
    }

    // Enters the email address
    public static void setEmail(WebDriver driver, String email) {
        driver.findElement(Email).sendKeys(email);
    }

    // Enters the password
    public static void setPassword(WebDriver driver, String password) {
        driver.findElement(Password).sendKeys(password);
    }
}