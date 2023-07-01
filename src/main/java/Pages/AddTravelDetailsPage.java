package Pages;

import CommonLibrary.CommonLibrary;
import TestBase.TestBase;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AddTravelDetailsPage extends TestBase {
    CommonLibrary cl = new CommonLibrary();
    public AddTravelDetailsPage(){
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//h2[text()='Add traveller details']")
    WebElement Label_AddTravellerDetails;

    @FindBy(xpath = "(//input[@data-testid='firstName'])[1]")
    WebElement TextBox_AdultFirstName;

    @FindBy(xpath = "(//input[@data-testid='firstName'])[2]")
    WebElement TextBox_ChildFirstName;

    @FindBy(xpath = "(//input[@data-testid='lastName'])[2]")
    WebElement TextBox_ChildLastName;

    @FindBy(xpath = "(//input[@data-testid='lastName'])[1]")
    WebElement TextBox_AdultLastName;

    @FindBy(xpath = "(//div[text()='Gender'])[1]")
    WebElement Button_AdultGender;

    @FindBy(xpath = "(//div[text()='Gender'])[2]")
    WebElement Button_ChildGender;

    @FindBy(xpath = "//li[text()='Male']")
    WebElement Option_Male;

    @FindBy(xpath = "//li[text()='Female']")
    WebElement Option_Female;

    @FindBy(xpath = "(//div[@class=\"col-3\"]/select)[1]")
    WebElement DD_Dob_Day;

    @FindBy(xpath = "(//div[@class=\"col-3\"]/select)[2]")
    WebElement DD_Dob_Month;

    @FindBy(xpath = "(//div[@class=\"col-3\"]/select)[3]")
    WebElement DD_Dob_Year;
    public void SelectGender(String adultChild, String gnder){
        if (adultChild.toLowerCase().contains("adult")){
            cl.ClickElement(Button_AdultGender);
            cl.ClickElement(Option_Male);
        }else{
            cl.ClickElement(Button_ChildGender);
            cl.ClickElement(Option_Female);
        }
    }

    public void EnterTravellersDetails(){
        cl.EnterDataInTextBox(TextBox_AdultFirstName, prop.getProperty("TravellerAdultFirstName"));
        cl.EnterDataInTextBox(TextBox_AdultLastName, prop.getProperty("TravellerAdultLastName"));
        SelectGender("adult", prop.getProperty("TravellerAdultGender"));

        cl.EnterDataInTextBox(TextBox_ChildFirstName, prop.getProperty("TravellerChildFirstName"));
        cl.EnterDataInTextBox(TextBox_ChildLastName, prop.getProperty("TravellerChildLastName"));
        SelectGender("child", prop.getProperty("TravellerChildGender"));
    }

    public void SelectDoBOfChild(){
        cl.SelectInDropdown(DD_Dob_Day,"03");
        cl.SelectInDropdown(DD_Dob_Month,"May");
        cl.SelectInDropdown(DD_Dob_Year,"2019");
    }

    public boolean VerifyTravellersPage(){
        return cl.CheckIfElementExist(Label_AddTravellerDetails);
    }
}
