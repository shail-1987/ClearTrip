package Pages;

import CommonLibrary.CommonLibrary;
import TestBase.TestBase;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ChooseAddOnPage extends TestBase {
    CommonLibrary cl = new CommonLibrary();

    public ChooseAddOnPage(){
        PageFactory.initElements(driver, this);
    }
    @FindBy(xpath="//button[text()='Skip this step']")
    WebElement Button_SkipThisStep;

    @FindBy(xpath = "//h2[text()='Choose add-ons']")
    WebElement Label_ChooseAddon;

    public boolean VerifyAddonPage(){
        return cl.CheckIfElementExist(Label_ChooseAddon);
    }

    public void ClickSkipThisStep(){
        cl.ClickElement(Button_SkipThisStep);
    }
}
