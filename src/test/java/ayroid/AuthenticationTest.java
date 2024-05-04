package ayroid;

import org.testng.*;
import org.testng.annotations.*;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AuthenticationTest {

    private WebDriver driver;
    UtilMethods utils = new UtilMethods();
    Authentication auth = new Authentication();

    @BeforeTest
    public void setUp() {
        driver = utils.setUpWebDriver();
    }

    @Test(dataProvider = "loginData")
    public void loginSuccess(String username, String password, String expectedToastMessage) {
        utils.goToUrl(driver, "http://localhost:5173/auth/login");
        utils.customTimedDelay(3);
        auth.login(driver, username, password);
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement toastElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#\\31 ")));
        String toastText = toastElement.getText();
        if (toastText.contains(expectedToastMessage)) {
            Assert.assertTrue(toastElement.isDisplayed(), "Login Success!");
        } else {
            utils.customTimedDelay(3);
            Assert.fail("Login failed: " + toastText);
        }
    }

    @DataProvider(name = "loginData")
    public Object[][] getLoginData() {
        return new Object[][] {
                { "Ayroidd", "ayroid--", "Account Does Not Exist" },
                { "Ayroid", "ayroid---", "Invalid Password" },
                { "Ayroidd", "ayroid--", "Account Does Not Exist" },
                { "Ayroid", "ayroid--", "Login Successful" },
        };
    }

    @Test(dataProvider = "registerData")
    public void registerSuccess(String username, String password, String expectedToastMessage) {
        utils.goToUrl(driver, "http://localhost:5173/auth/signup");
        utils.customTimedDelay(3);
        auth.register(driver, username, password);
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement toastElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#\\31 ")));
        String toastText = toastElement.getText();
        if (toastText.contains(expectedToastMessage)) {
            Assert.assertTrue(toastElement.isDisplayed(), "Registration Success!");
        } else {
            utils.customTimedDelay(3);
            Assert.fail("Registration failed: " + toastText);
        }
    }

    @AfterTest
    public void tearDown() {
        utils.tearDown(driver);
    }
}
