package ayroid;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.TimeoutException;

public class UtilMethods {

    public void customTimedDelay(int timeInSeconds) {
        try {
            Thread.sleep((long) timeInSeconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void timeDelay(WebDriver driver, int timeInSeconds, String cssSelector) {
        WebDriverWait wait = new WebDriverWait(driver, timeInSeconds);
        try {
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(cssSelector)));
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }

    public WebDriver setUpWebDriver() {
        System.setProperty("webdriver.chrome.driver", "/home/ayroid/Downloads/chromedriver-linux64/chromedriver");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        return driver;
    }

    public void tearDown(WebDriver driver) {
        driver.quit();
    }

    public void goToUrl(WebDriver driver, String url) {
        driver.get(url);
    }

    public void navigateBack(WebDriver driver) {
        driver.navigate().back();
    }

    public void navigateForward(WebDriver driver) {
        driver.navigate().forward();
    }

    public void refreshPage(WebDriver driver) {
        driver.navigate().refresh();
    }

}
