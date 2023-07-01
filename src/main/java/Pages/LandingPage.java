package Pages;

import CommonLibrary.CommonLibrary;
import TestBase.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.awt.*;
import java.util.List;

public class LandingPage extends TestBase {

    CommonLibrary cl = new CommonLibrary();

    @FindBy(xpath = "//div[contains(@class,'flex flex-middle nmx-1 pb-1')]//*[name()='svg']")
    WebElement Button_CancelOtp;

    @FindBy(xpath="//h1[text()='Search flights']")
    WebElement Label_SearchFlight;

    @FindBy(xpath="//button//span[text()='Get OTP']")
    WebElement Button_GetOtp;

    @FindBy(xpath="(//div[contains(@class,'home-search-banner')]//div[@class='flex flex-middle']/span)[1]")
    WebElement Label_Way;

    @FindBy(xpath="(//div[contains(@class,'home-search-banner')]//div[@class='flex flex-middle'])[2]")
    WebElement Button_SelectTravellerCount;

    @FindBy(xpath = "(//span[text()='Round trip'])[1]")
    WebElement Label_RoundTrip;

    @FindBy(xpath="//div[contains(@class,'0 flex flex-between')]//li[contains(@class,'center')]")
    WebElement Label_SelectedTravellersCount;

    @FindBy(xpath = "(//div[contains(@class,'0 flex flex-between')]//li[contains(@class,'flex-inline')])[3]")
    WebElement Button_PlusIconForAdult;

    @FindBy(xpath="(//div[contains(@class,'dropdown__item flex flex-between flex-middle')]//li[contains(@class,'flex-inline')])[2]")
    WebElement Label_SelectedChildrenCount;

    @FindBy(xpath = "(//div[contains(@class,'dropdown__item flex flex-between flex-middle')]//li[contains(@class,'flex-inline')])[3]")
    WebElement Button_PlusIconForChildren;

    @FindBy(xpath = "//div[contains(text(),'Economy')]")
    WebElement Button_Economy;

    @FindBy(xpath = "//span[contains(text(),'Economy')]")
    WebElement Label_SelectedSeatsAndType;

    @FindBy(xpath = "//input[@placeholder='Where from?']")
    WebElement TextBox_SourceCity;

    @FindBy(xpath = "//input[@placeholder='Where to?']")
    WebElement TextBox_DestinationCity;

    @FindBy(xpath = "(//div[contains(@class,'homeCalender')]/button)[1]")
    WebElement Button_FromCalendar;

    @FindBy(xpath = "(//div[contains(@class,'homeCalender')]/button)[2]")
    WebElement Button_ToCalendar;

    @FindBy(xpath = "//span[text()='Search flights']")
    WebElement Button_SearchFlight;

    public LandingPage(){
        PageFactory.initElements(driver, this);
    }

    public void ClickSearchFlightButton(){
        Actions act = new Actions(driver);
        act.moveToElement(Button_SearchFlight).build().perform();
        cl.ClickElement(Button_SearchFlight);
    }

    public boolean CheckOtpPopup(){
        return cl.CheckIfElementExist(Button_GetOtp);
    }

    public void ClickCancelOtp(){
        cl.ClickElement(Button_CancelOtp);
    }

    public boolean CheckSearchFlightLabel(){
        return cl.CheckIfElementExist(Label_SearchFlight);
    }

    public boolean VerifyRouteWay(){
        boolean bln = false;
        if (cl.GetDataFromElement(Label_Way).contains("One way")){
            cl.ClickElement(Label_Way);
            cl.WaitForLoad();
            cl.ClickElement(Label_RoundTrip);
            bln = true;
        }
        return bln;
    }

    public boolean AddTravellersCount(int adultCount, String TravellersType){
        boolean bln = false;

        switch (TravellersType.toLowerCase()){
            case "adult":
                cl.ClickElement(Button_SelectTravellerCount);
                if (Integer.parseInt(cl.GetDataFromElement(Label_SelectedTravellersCount)) < adultCount ){
                    for (int iStart=0; iStart < adultCount; iStart++){
                        cl.ClickElement(Button_PlusIconForAdult);
                    }
                }
                if (Integer.parseInt(cl.GetDataFromElement(Label_SelectedTravellersCount)) == adultCount){
                    bln = true;
                }
                break;
            case "children":
                cl.WaitForLoad();
                if (Integer.parseInt(cl.GetDataFromElement(Label_SelectedChildrenCount)) < adultCount ){
                    for (int iStart=0; iStart < adultCount; iStart++){
                        cl.ClickElement(Button_PlusIconForChildren);
                    }
                }else{
                    System.out.println("Value of child: " + Label_SelectedChildrenCount);
                    System.out.println("Converted Value of child: " + Integer.parseInt(cl.GetDataFromElement(Label_SelectedChildrenCount)));
                }

                if (Integer.parseInt(cl.GetDataFromElement(Label_SelectedChildrenCount)) == adultCount){
                    bln = true;
                }
                break;
        }
        return bln;
    }

    public void SelectSeatType(){
        cl.ClickElement(Button_Economy);
    }

    public String GetSelectedSeats(){
        return cl.GetDataFromElement(Label_SelectedSeatsAndType);
    }

    public void SelectSourceAndDestinationCity(String type, String sourceCity){
        if (type.contains("Source")){
            cl.ClickElement(TextBox_SourceCity);
            cl.EnterDataInTextBox(TextBox_SourceCity, sourceCity);
        }else{
            cl.ClickElement(TextBox_DestinationCity);
            cl.EnterDataInTextBox(TextBox_DestinationCity, sourceCity);
        }

        String cityXpath = "//div[text()='"+ cl.GetCityCode(sourceCity)  +"']";
        cl.WaitForLoad();

        driver.findElement(By.xpath(cityXpath)).click();
    }

    public void SelectTravelDate(String date, String fromTo){
        if (fromTo.equalsIgnoreCase("from")){
            cl.ClickElement(Button_FromCalendar);
        }else{
            //cl.ClickElement(Button_ToCalendar);
        }
        cl.ScrollDownInPage();
        cl.WaitForLoad();

        String monthName = date.split("/")[1];
        String dte = date.split("/")[0];
        String yr = date.split("/")[2];

        String xPath = "//div[@class='DayPicker-Month']";
        String monthSet = monthName + " " + yr;
        String dayXpath = "//div[text()='" + dte + "']";
        List<WebElement> monthLst = driver.findElements(By.xpath(xPath));
        for (WebElement ele: monthLst){
            if(ele.findElement(By.tagName("div")).getText().equalsIgnoreCase(monthSet)){
                ele.findElement(By.xpath(dayXpath)).click();
                break;
            }
        }
        cl.WaitForLoad();
    }
}
