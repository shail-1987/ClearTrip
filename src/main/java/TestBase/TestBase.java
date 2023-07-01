package TestBase;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class TestBase {

    public static WebDriver driver;

    public Properties prop;

    public TestBase(){
        String configPath = System.getProperty("user.dir") + "/src/main/java/ConfigurationManager/Config.properties";

        try{
            FileInputStream fis = new FileInputStream(configPath);
            prop = new Properties();
            prop.load(fis);
        } catch(IOException io){
            System.out.println("Error while reading config file: " + io.getMessage());
        }
    }

    public void LaunchBrowser(){
        driver = new ChromeDriver();
    }

    public void NavigateToURL(){
        driver.get(prop.getProperty("AppURL"));
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(45));
    }
}
