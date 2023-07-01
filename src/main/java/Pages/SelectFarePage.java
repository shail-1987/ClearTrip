package Pages;

import CommonLibrary.CommonLibrary;
import TestBase.TestBase;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import javax.xml.stream.events.StartDocument;

public class SelectFarePage extends TestBase {
    CommonLibrary cl = new CommonLibrary();
    public SelectFarePage(){
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//div[contains(@class,'fareBlock__title fareBlock__title_new')]")
    WebElement Standard_Fare;

    public boolean VerifyStandardFare(){
        return cl.CheckIfElementExist(Standard_Fare);
    }

    public void ClickStandardFare(){
        cl.ClickElement(Standard_Fare);
    }

}
