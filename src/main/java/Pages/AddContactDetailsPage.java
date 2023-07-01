package Pages;

import CommonLibrary.CommonLibrary;
import TestBase.TestBase;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AddContactDetailsPage extends TestBase {
    CommonLibrary cl = new CommonLibrary();
    public AddContactDetailsPage(){
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//h2[text()='Add contact details']")
    WebElement Label_AddContactDetails;

    @FindBy(xpath = "//input[@data-testid='mobileNumber']")
    WebElement TextBox_MobileNumber;

    @FindBy(xpath = "//input[@data-testid='email']")
    WebElement TextBox_EmailAddress;

    public boolean VerifyContactDetailsPage(){
        return cl.CheckIfElementExist(Label_AddContactDetails);
    }

    public void AddContactDetails(){
        cl.EnterDataInTextBox(TextBox_MobileNumber, prop.getProperty("MobileNumber"));
        cl.EnterDataInTextBox(TextBox_EmailAddress, prop.getProperty("EmailAddress"));
    }

}
