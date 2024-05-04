package ayroid;

import org.openqa.selenium.*;

public class Authentication {

    UtilMethods utils = new UtilMethods();

    public void login(WebDriver driver, String username, String password) {

        utils.goToUrl(driver, "http://localhost:5173/auth/login");
        utils.customTimedDelay(3);

        WebElement usernameField = driver.findElement(By.cssSelector("#loginEmail"));
        usernameField.sendKeys(username);

        WebElement passwordField = driver.findElement(By.cssSelector("#loginPassword"));
        passwordField.sendKeys(password);

        WebElement nextButton = driver.findElement(By.cssSelector("#loginAuthDiv > form > button"));
        nextButton.click();
    }

    public void register(WebDriver driver, String username, String email, String password) {

        utils.goToUrl(driver, "http://localhost:5173/auth/signup");
        utils.customTimedDelay(3);

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

        utils.goToUrl(driver, "http://localhost:5173/settings");
        utils.customTimedDelay(3);

        WebElement logoutButton = driver.findElement(
                By.xpath("//*[@id=\"root\"]/div/div[1]/div[2]/button"));
        logoutButton.click();
    }

    public static void main(String[] args) {

        Authentication auth = new Authentication();
        UtilMethods utils = new UtilMethods();
        WebDriver driver = utils.setUpWebDriver();

        auth.login(driver, "Ayroid", "ayroid--");
        utils.tearDown(driver);
    }
}
