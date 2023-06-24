package pageobjects;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class WebTablesPage extends AbstractPageObject{
    private final By addButton = By.xpath("//button[@id='addNewRecordButton']");
    private final By firstNameField = By.xpath("//input[@id='firstName']");
    private final By lastNameField = By.xpath("//input[@id='lastName']");
    private final By emailField = By.xpath("//input[@id='userEmail']");
    private final By ageField = By.xpath("//input[@id='age']");
    private final By salaryField = By.xpath("//input[@id='salary']");
    private final By departmentField = By.xpath("//input[@id='department']");
    private final By submitButton = By.xpath("//button[@id='submit']");
    private By newElement(String lastName) {
        return By.xpath(String.format("//div[@class='rt-table']//div[text()='%s']", lastName));
    }
    private By editButton(String lastName) {
        return By.xpath(String.format("//div[text()='%s']/following-sibling::div//span[contains(@id,'edit-record')]", lastName));
    }
    private By deleteButton(String firstName) {
        return By.xpath(String.format("//div[text()='%s']/following-sibling::div//span[contains(@id,'delete-record')]", firstName));
    }

    public WebTablesPage(WebDriver driver) {
        super(driver);
        driver.get("https://demoqa.com/webtables");
    }

    @Step("The user fills in the Registration form with [{0},{1}, {2}, {3}, {4}, {5}]")
    public void AddNewElement(String firstName, String lastName, String email, String age, String salary, String department){
        getElement(addButton).click();
        getElement(firstNameField).sendKeys(firstName);
        getElement(lastNameField).sendKeys(lastName);
        getElement(emailField).sendKeys(email);
        getElement(ageField).sendKeys(age);
        getElement(salaryField).sendKeys(salary);
        getElement(departmentField).sendKeys(department);
        getElement(submitButton).click();
    }

    public WebElement userRecord(String lastName) {
        return getElement(newElement(lastName));
    }
    public void clickOnEditButton(String lastName) {
        getElement(editButton(lastName)).click();
    }
    public void setLastName(String lastName){
        getElement(lastNameField).clear();
        getElement(lastNameField).sendKeys(lastName);
        getElement(submitButton).click();
    }

    public void clickOnDeleteButton(String firstName) {
        getElement(deleteButton(firstName)).click();
    }
    private boolean isElementPresent(By locator) {
        try {
            driver.findElement(locator);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }
    public boolean isRecordAdded(String lastName) {
        return isElementPresent(newElement(lastName));
    }

    public String getLastNameFromRecord(String lastName) {
        WebElement lastNameElement = userRecord(lastName);
        return lastNameElement.getText();
    }







}
