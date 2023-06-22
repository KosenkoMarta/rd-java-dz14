package tests;

import jdk.jfr.Description;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pageobjects.WebTablesPage;


public class WebTablesPageCRUDTest extends BaseTest{
    private final static String FIRST_NAME = "Marta";
    private final static String LAST_NAME = "Kosenko";
    private final static String EMAIL = "marta@test.com";
    private final static String AGE = "33";
    private final static String SALARY = "1234";
    private final static String DEPARTMENT = "QA";
    private final static String UPDATE_LAST_NAME = "Koko";


    @Test
    @Description("Adding of a new employee to the list")
    public void testNewElement(){
        WebTablesPage webTablesPage = new WebTablesPage(driver);
        SoftAssert softAssert = new SoftAssert();

        webTablesPage.AddNewElement(FIRST_NAME, LAST_NAME, EMAIL, AGE, SALARY, DEPARTMENT);

        softAssert.assertTrue(webTablesPage.isRecordAdded(LAST_NAME), "The record was not added");
        softAssert.assertEquals(webTablesPage.getLastNameFromRecord(LAST_NAME), "Kosenko", "The element with last name 'Kosenko' was created with the wrong name");

        webTablesPage.clickOnEditButton("Kosenko");
        webTablesPage.setLastName(UPDATE_LAST_NAME);

        softAssert.assertEquals(webTablesPage.getLastNameFromRecord(UPDATE_LAST_NAME), "Koko", "The element was not updated with the expected last name 'Koko'");

        webTablesPage.clickOnDeleteButton("Marta");

        softAssert.assertFalse(webTablesPage.isRecordAdded("Marta"), "The element was not deleted");
        softAssert.assertAll();
    }
}
