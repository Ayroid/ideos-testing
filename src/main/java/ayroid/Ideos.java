package ayroid;

import org.openqa.selenium.*;

public class Ideos {

    UtilMethods utils = new UtilMethods();

    public void createIdeo(WebDriver driver, String ideosTitle, String ideosDescription,
            String ideosPriority) {
        utils.goToUrl(driver, "http://localhost:5173/ideos");

        utils.customTimedDelay(2);

        WebElement addIdeosButton = driver.findElement(By.cssSelector("#root > div > div.pageContainer > button"));
        addIdeosButton.click();

        utils.customTimedDelay(2);

        WebElement titleField = driver.findElement(By.cssSelector("#ideosTitle"));
        titleField.sendKeys(ideosTitle);

        WebElement descriptionField = driver.findElement(By.cssSelector("#ideosDesc"));
        descriptionField.sendKeys(ideosDescription);
        WebElement priorityField = driver.findElement(By.cssSelector("#" + ideosPriority));
        priorityField.click();

        WebElement nextButton = driver
                .findElement(By.cssSelector("#addIdeosForm > div._ideosButtons_uneah_85 > button:nth-child(1)"));
        nextButton.click();

        utils.customTimedDelay(2);
    }

    public void deleteIdeo(WebDriver driver, String ideosDivSelector, String deleteButtonSelector) {
        utils.goToUrl(driver, "http://localhost:5173/ideos");

        utils.customTimedDelay(2);

        WebElement deleteButton = driver.findElement(By.cssSelector(ideosDivSelector));
        deleteButton.click();

        utils.customTimedDelay(2);

        WebElement delteButton = driver
                .findElement(By.cssSelector(deleteButtonSelector));
        delteButton.click();

        utils.refreshPage(driver);

        utils.customTimedDelay(2);
    }

    public static void main(String[] args) {
        Ideos ideos = new Ideos();
        Authentication auth = new Authentication();
        UtilMethods utils = new UtilMethods();
        WebDriver driver = utils.setUpWebDriver();

        auth.login(driver, "Ayroid", "ayroid--");
        utils.customTimedDelay(2);
        ideos.createIdeo(driver, "Test Ideo", "This is a test ideos", "highPriority");
        ideos.deleteIdeo(driver, "#\\36 628d1e4d9f1d378419a9064 > img",
                "#dropDownMenu6628d1e4d9f1d378419a9064 > ul > li:nth-child(3)");
        utils.tearDown(driver);
    }
}
