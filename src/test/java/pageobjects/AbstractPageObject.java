package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AbstractPageObject {
    private final static int DURATION_TO_WAIT_DEFAULT = 5;
    private final static String COLOUR_ATTRIBUTE_NAME = "color";
    protected WebDriver driver;

    public AbstractPageObject(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement getElement(By by, int waitForSeconds) {
        return new WebDriverWait(driver, Duration.ofSeconds(waitForSeconds))
                .until(ExpectedConditions.presenceOfElementLocated(by));
    }

    public WebElement getElement(final By by) {
        return new WebDriverWait(driver, Duration.ofSeconds(DURATION_TO_WAIT_DEFAULT))
                .until(ExpectedConditions.presenceOfElementLocated(by));
    }

    public List<WebElement> getElements(By by, int waitForSeconds) {
        return new WebDriverWait(driver, Duration.ofSeconds(waitForSeconds))
                .until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
    }

    public List<WebElement> getElements(By by) {
        return new WebDriverWait(driver, Duration.ofSeconds(DURATION_TO_WAIT_DEFAULT))
                .until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
    }

    public void waitTillAppears(By by, int waitForSeconds) {
        new WebDriverWait(driver, Duration.ofSeconds(waitForSeconds))
                .until(ExpectedConditions.presenceOfElementLocated(by));
    }

    public void waitTillAppears(By by) {
        new WebDriverWait(driver, Duration.ofSeconds(DURATION_TO_WAIT_DEFAULT))
                .until(ExpectedConditions.presenceOfElementLocated(by));
    }

    public void waitTillDisappear(By by, int waitForSeconds) {
        new WebDriverWait(driver, Duration.ofSeconds(waitForSeconds))
                .until(ExpectedConditions.invisibilityOfElementLocated(by));
    }

    public void waitTillDisappear(By by) {
        new WebDriverWait(driver, Duration.ofSeconds(DURATION_TO_WAIT_DEFAULT))
                .until(ExpectedConditions.invisibilityOfElementLocated(by));
    }

    public void clickOnElementWithJS(By by) {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", getElement(by));
    }

    public void changeElementColor(By by, String color) {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript(String.format("arguments[0].style.backgroundColor = '%s';", color)
                , getElement(by));
    }

    public Map<String, String> moveMouseToLink(int index, By by) {
        Actions actions = new Actions(driver);
        String colorWithoutMouse = getElement(by).getCssValue(COLOUR_ATTRIBUTE_NAME);
        actions.moveToElement(getElements(by).get(index)).perform();
        String colorWithMouse = getElement(by).getCssValue(COLOUR_ATTRIBUTE_NAME);

        return new HashMap<>(){{
            put("colorWithoutMouse", colorWithoutMouse);
            put("colorWithMouse", colorWithMouse);
        }};
    }

}
