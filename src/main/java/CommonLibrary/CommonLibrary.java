package CommonLibrary;

import TestBase.TestBase;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;

public class CommonLibrary extends TestBase {

    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(45));

    public void ClickElement(WebElement element){
        try{
            wait.until(ExpectedConditions.visibilityOf(element)).click();
        }catch(NoSuchElementException n){
            System.out.println("Error from ClickElement method: " + n.getMessage());
        }
    }

    public void EnterDataInTextBox(WebElement element, String value){
        try{
            wait.until(ExpectedConditions.visibilityOf(element)).sendKeys(value);
        }catch(NoSuchElementException n){
            System.out.println("Error from EnterDataInTextBox method: " + n.getMessage());
        }
    }

    public boolean CheckIfElementExist(WebElement element){
        boolean bln = false;
        try{
            if(element.isDisplayed()){
                bln = true;
            }
        }catch(NoSuchElementException ex){
            bln = false;
            System.out.println("Error from checkIfElementExist method: " + ex.getMessage());
        }

        return bln;
    }

    public void WaitForLoad(){
        try{
            Thread.sleep(4000);
        }catch(Exception e){
            e.printStackTrace();
        }

    }

    public String GetDataFromElement(WebElement element){
        String ret = null;
        try{
            if(element.isDisplayed()){
                ret = element.getText();
            }
        }catch(NoSuchElementException n){
            System.out.println("Error from getDataFromElement method: " + n.getMessage());
        }
        return ret;
    }

    public String GetCityCode(String CityName){
        String code = "";
        switch (CityName.toLowerCase()){
            case "bangalore":
                code = "BLR";
                break;
            case "hyderabad":
                code = "HYD";
                break;
        }

        return code;
    }

    public void ScrollDownInPage(){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,250)", "");
    }

    public void SelectInDropdown(WebElement element, String option){
        Select sel = new Select(element);
        sel.selectByVisibleText(option);
    }

    public static void main(String[] args){
       DateUtility cl = new DateUtility();
       System.out.println(cl.GetTravelDate(140));
    }
}
