package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageobjects.ButtonsPage;


public class ButtonsPageTest extends BaseTest {

    @Test
    public void testOpenPage(){
        ButtonsPage buttonsPage = new ButtonsPage(driver);
        buttonsPage.goToMenuButtons();
        buttonsPage.clickOnClickMeButton();
        String messageText = buttonsPage.getAppearedMessage();

        Assert.assertEquals(messageText, "You have done a dynamic click", "Received text does not match the expected value: 'You have done a dynamic click'");
        System.out.println(messageText);
    }
}
