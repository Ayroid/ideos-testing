package ayroid;

import static org.junit.Assert.*;

import org.testng.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

public class IdeosTest {

    private WebDriver driver;
    UtilMethods utils = new UtilMethods();
    Authentication auth = new Authentication();
    Ideos ideos = new Ideos();

    @BeforeTest
    public void setUp() {
        driver = utils.setUpWebDriver();
    }

    @Test
    public void testLogin() {
        auth.login(driver, "Ayroid", "ayroid--");
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement toastElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#\\31 ")));
        String toastText = toastElement.getText();
        if (toastText.contains("Login Successful")) {
            Assert.assertTrue(toastElement.isDisplayed(), "Login Success!");
        } else {
            Assert.fail("Login failed: " + toastText);
        }
    }

    @Test(dependsOnMethods = "testLogin")
    public void testCreateIdeo() {
        ideos.createIdeo(driver, "Test Ideo", "This is a test ideos description", "lowPriority");
        assertTrue(true);
    }

    @Test(dependsOnMethods = "testCreateIdeo")
    public void testDeleteIdeo() {
        ideos.deleteIdeo(driver, "#\\36 63676549174746861b1e812 > img",
                "#dropDownMenu663676549174746861b1e812 > ul > li:nth-child(3)");
        assertTrue(true);
    }

    @AfterTest
    public void tearDown() {
        auth.logout(driver);
        utils.tearDown(driver);
    }

}
