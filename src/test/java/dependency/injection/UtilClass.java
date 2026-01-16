package dependency.injection;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class UtilClass {
    public static String username;
    public final String siteUrl = "https://askomdch.com/";
    public WebDriver driver;

    public WebDriver setUp(){
        return driver = new ChromeDriver();
    }

    public WebDriver tearDown(){
        return driver;
    }
}
