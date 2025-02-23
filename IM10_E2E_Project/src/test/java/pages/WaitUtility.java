package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WaitUtility
{
    // Method to pause execution for a specified number of milliseconds
    public static void sleep(int ms)
    {
        try
        {
            Thread.sleep(ms);
        }
        catch (InterruptedException e)
        {
            throw new RuntimeException("Interrupted: " + e);
        }
    }

    // Static WebDriverWait instance
    public static WebDriverWait wait;

    // Method to wait until the specified element is visible on the page
    public static By waitElementToBeVisible(WebDriver driver, By element)
    {
        // Initializing WebDriverWait with a timeout of 120 seconds
        wait = new WebDriverWait(driver, Duration.ofSeconds(120));

        // Wait until the specified element is visible
        wait.until(ExpectedConditions.visibilityOfElementLocated(element));
        return element;
    }
}
