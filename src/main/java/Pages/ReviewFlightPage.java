package Pages;

import CommonLibrary.CommonLibrary;
import TestBase.TestBase;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ReviewFlightPage extends TestBase {
    CommonLibrary cl = new CommonLibrary();
    public ReviewFlightPage(){
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//h2[text()='Review your itinerary']")
    WebElement Label_ReviewYourItinerary;

    @FindBy(xpath = "((//div[@class='flex flex-between flex-bottom'])[1]//span)[1]")
    WebElement Label_Source1;

    @FindBy(xpath = "((//div[@class='flex flex-between flex-bottom'])[1]//span)[2]")
    WebElement Label_Destination1;

    @FindBy(xpath = "((//div[@class='flex flex-between flex-bottom'])[2]//span)[1]")
    WebElement Label_Source2;

    @FindBy(xpath = "((//div[@class='flex flex-between flex-bottom'])[2]//span)[2]")
    WebElement Label_Destination2;

    @FindBy(xpath = "//button[text()='Continue']")
    WebElement Button_Continue;

    public void ClickContinueButton(){
        Actions act = new Actions(driver);
        act.moveToElement(Button_Continue).build().perform();
        cl.WaitForLoad();
        cl.ClickElement(Button_Continue);
    }

    public boolean CheckReviewPage(){
        return cl.CheckIfElementExist(Label_ReviewYourItinerary);
    }

    public String GetSource1Data(){
        return cl.GetDataFromElement(Label_Source1);
    }

    public String GetSource2Data(){
        return cl.GetDataFromElement(Label_Source2);
    }

    public String GetDestination1Data(){
        return cl.GetDataFromElement(Label_Destination1);
    }

    public String GetDestination2Data(){
        return cl.GetDataFromElement(Label_Destination2);
    }

}
