package ayroid;

import org.openqa.selenium.*;

public class Authentication {

    public void login(WebDriver driver, String username, String password) {
        WebElement usernameField = driver.findElement(By.cssSelector("#loginEmail"));
        usernameField.sendKeys(username);

        WebElement passwordField = driver.findElement(By.cssSelector("#loginPassword"));
        passwordField.sendKeys(password);

        WebElement nextButton = driver.findElement(By.cssSelector("#loginAuthDiv > form > button"));
        nextButton.click();
    }

    public void register(WebDriver driver, String username, String password) {
        WebElement usernameField = driver.findElement(By.cssSelector("#registerEmail"));
        usernameField.sendKeys(username);

        WebElement passwordField = driver.findElement(By.cssSelector("#registerPassword"));
        passwordField.sendKeys(password);

        WebElement nextButton = driver.findElement(By.cssSelector("#registerAuthDiv > form > button"));
        nextButton.click();
    }

    public static void main(String[] args) {

        Authentication auth = new Authentication();
        UtilMethods utils = new UtilMethods();
        WebDriver driver = utils.setUpWebDriver();

        utils.goToUrl(driver, "http://localhost:5173");
        utils.customTimedDelay(3);
        auth.login(driver, "Ayroid", "ayroid---");
        utils.customTimedDelay(3);
        utils.tearDown(driver);
    }
}
