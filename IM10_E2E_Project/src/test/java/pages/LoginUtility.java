package pages;

import org.openqa.selenium.WebDriver;
import static java.lang.Thread.sleep;

public class LoginUtility {

    public static void login(WebDriver driver, String user) throws InterruptedException {
        switch (user) {
            case "SUPER_ADMIN":
                IM10LoginPage.setEmail(driver, "tufanpowar@gmail.com");
                IM10LoginPage.setPassword(driver, "sA#Sa@sA$1");
                break;

            case "CONTENT_ADMIN":
                IM10LoginPage.setEmail(driver, "testCA@gmail.com");
                IM10LoginPage.setPassword(driver, "123456");
                break;

            case "IM10_SALES_ADMIN":
                IM10LoginPage.setEmail(driver, "testIM10SA@gmail.com");
                IM10LoginPage.setPassword(driver, "123456");
                break;

            case "SALES_PERSON_ADMIN":
                IM10LoginPage.setEmail(driver, "testSPA@gmail.com");
                IM10LoginPage.setPassword(driver, "123456");
                break;

            case "ENDORSEMENT_MANAGER_ADMIN":
                IM10LoginPage.setEmail(driver, "testEMA@gmail.com");
                IM10LoginPage.setPassword(driver, "123456");
                break;

            case "QUALITY_ASSURANCE_ADMIN":
                IM10LoginPage.setEmail(driver, "testQA@gmail.com");
                IM10LoginPage.setPassword(driver, "123456");
                break;

            case "IM10_MARKETING_CAMPAIGNS_ADMIN":
                IM10LoginPage.setEmail(driver, "testIM10MAC@gmail.com");
                IM10LoginPage.setPassword(driver, "123456");
                break;

            default:
                throw new IllegalArgumentException("Unsupported user type: " + user);
        }
        // Click submit button
        IM10LoginPage.clickSubmitLink();
        sleep(2000);
    }
}

