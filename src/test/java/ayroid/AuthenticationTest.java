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
    public void testLogin(String username, String password, String expectedToastMessage, Boolean expectedLoginSuccess) {
        utils.goToUrl(driver, "http://localhost:5173/auth/login");
        utils.customTimedDelay(3);
        auth.login(driver, username, password);
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement toastElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#\\31 ")));
        String toastText = toastElement.getText();
        if (toastText.contains(expectedToastMessage)) {
            Assert.assertTrue(toastElement.isDisplayed(), expectedLoginSuccess ? "Login Success!" : "Login Failed!");
        } else {
            Assert.fail("Login failed: " + toastText);
        }
    }

    @DataProvider(name = "loginData")
    public Object[][] getLoginData() {
        return new Object[][] {
                { "Ayroidd", "ayroid--", "Account Does Not Exist", false },
                { "Ayroid", "ayroid---", "Invalid Password", false },
                { "Ayroidd", "ayroid--", "Account Does Not Exist", false },
                { "Ayroid", "ayroid--", "Login Successful", true },
        };
    }

    @Test(dependsOnMethods = "testLogin")
    public void testLogOut() {
        utils.goToUrl(driver, "http://localhost:5173/settings");
        utils.customTimedDelay(3);
        auth.logout(driver);
    }

    @Test(dataProvider = "registerData", dependsOnMethods = "testLogOut")
    public void testRegister(String username, String email, String password, String expectedToastMessage) {
        utils.goToUrl(driver, "http://localhost:5173/auth/signup");
        utils.customTimedDelay(3);
        auth.register(driver, username, email, password);
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

    @DataProvider(name = "registerData")
    public Object[][] getRegisterData() {
        return new Object[][] {
                { "Ayroid", "ayroids@gmail.com", "ayroid--", "Username or Email already exists" },
                { "Test", "test@gmail.com", "testtest", "Account Created Successfully" },
        };
    }

    @AfterTest
    public void tearDown() {
        utils.tearDown(driver);
    }
}
