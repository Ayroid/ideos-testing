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

    public void register(WebDriver driver, String username, String email, String password) {
        WebElement usernameField = driver.findElement(By.cssSelector("#signUpUsername"));
        usernameField.sendKeys(username);

        WebElement emailField = driver.findElement(By.cssSelector("#signUpEmail"));
        emailField.sendKeys(email);

        WebElement passwordField = driver.findElement(By.cssSelector("#signUpPassword"));
        passwordField.sendKeys(password);

        WebElement nextButton = driver.findElement(By.cssSelector("#signUpAuthDiv > form > button"));
        nextButton.click();
    }

    public void logout(WebDriver driver) {
        WebElement logoutButton = driver.findElement(
                By.xpath("//*[@id=\"root\"]/div/div[1]/div[2]/button"));
        logoutButton.click();
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
