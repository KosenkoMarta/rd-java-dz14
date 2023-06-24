package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class ButtonsPage extends AbstractPageObject{
    private final By menuButtons = By.xpath("//li[@id='item-4']");
    private final By clickMeButton = By.xpath("//button[text()='Click Me']");
    private final By message = By.xpath("//*[@id='dynamicClickMessage']");
    public ButtonsPage(WebDriver driver) {
        super(driver);
        driver.get("https://demoqa.com/elements");
    }

    public void goToMenuButtons(){
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("window.scrollTo(0, document.body.scrollHeight)");
        getElement(menuButtons).click();
    }
    public void clickOnClickMeButton() {
        getElement(clickMeButton).click();
    }

    public String getAppearedMessage(){
        return getElement(message).getText();
    }
}
