package Pages;

import CommonLibrary.CommonLibrary;
import TestBase.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class SelectFlightPage extends TestBase {
    CommonLibrary cl = new CommonLibrary();


    @FindBy(xpath = "//div[text()='Non-stop']")
    WebElement Button_NonStop;

    @FindBy(xpath = "//button/span[text()='Book now']")
    WebElement Button_Book;

    public SelectFlightPage(){
        PageFactory.initElements(driver, this);
    }

    public boolean CheckNonStopButton(){
        cl.WaitForLoad();
        return cl.CheckIfElementExist(Button_NonStop);
    }

    public void ClickNonStopButton(){
        cl.ClickElement(Button_NonStop);
    }

    public void ClickBookButton(){
        cl.ClickElement(Button_Book);
    }

    public boolean CheckFlightPriceIsLowest(){
        boolean bln = false;
        String xpath = "//div[contains(@class,'flex-right p-relative')]/p";

        List<WebElement> lst = driver.findElements(By.xpath(xpath));

        String firstVal = lst.get(0).getText();
        int firstValue = Integer.parseInt(firstVal.substring(1, firstVal.length()).replace(",",""));

        for (int iStart = 1; iStart < lst.size(); iStart++){
            String val = lst.get(iStart).getText();
            if (!val.contains("seats")){
                int valueToCheck = Integer.parseInt(val.substring(1, val.length()).replace(",",""));
                if(firstValue <= valueToCheck){
                    bln = true;
                }else{
                    bln = false;
                }
            }
        }

        return bln;
    }

    public void HandleWindow(){
        String mainWindowHandle = driver.getWindowHandle();
        Set<String> allWindowHandles = driver.getWindowHandles();
        Iterator<String> iterator = allWindowHandles.iterator();

        // Here we will check if child window has other child windows and will fetch the heading of the child window
        while (iterator.hasNext()) {
            String ChildWindow = iterator.next();
            if (!mainWindowHandle.equalsIgnoreCase(ChildWindow)) {
                driver.switchTo().window(ChildWindow);
                System.out.println("Heading of child window is " + driver.getTitle());
            }
        }
    }
}
