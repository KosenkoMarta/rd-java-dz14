package tests;

import jdk.jfr.Description;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pageobjects.WebTablesPage;


public class WebTablesPageCRUDTest extends BaseTest{
    String firstName = "Marta";
    String lastName = "Kosenko";
    String email = "marta@test.com";
    String age = "33";
    String salary = "1234";
    String department = "QA";
    String updatedLastName = "Koko";


    @Test
    @Description("Adding of a new employee to the list")
    public void testNewElement(){
        WebTablesPage webTablesPage = new WebTablesPage(driver);
        SoftAssert softAssert = new SoftAssert();

        webTablesPage.AddNewElement(firstName, lastName, email, age, salary, department);

        softAssert.assertTrue(webTablesPage.isRecordAdded(lastName), "The record was not added");
        softAssert.assertEquals(webTablesPage.getLastNameFromRecord(lastName), "Kosenko", "The element with last name 'Kosenko' was created with the wrong name");

        webTablesPage.clickOnEditButton("Kosenko");
        webTablesPage.setLastName(updatedLastName);

        softAssert.assertEquals(webTablesPage.getLastNameFromRecord(updatedLastName), "Koko", "The element was not updated with the expected last name 'Koko'");

        webTablesPage.clickOnDeleteButton("Marta");

        softAssert.assertFalse(webTablesPage.isRecordAdded("Marta"), "The element was not deleted");
        softAssert.assertAll();
    }
}
