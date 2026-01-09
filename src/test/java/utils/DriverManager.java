package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import java.time.Duration;

public class DriverManager {

    private static DriverManager instance;
    private WebDriver driver;

    // Private constructor for Singleton pattern
    private DriverManager() {
    }

    // Get singleton instance
    public static DriverManager getInstance() {
        if (instance == null) {
            instance = new DriverManager();
        }
        return instance;
    }

    public void setup() {
        System.setProperty("webdriver.chrome.driver", 
            "C:\\Users\\hp\\eclipse-workspace\\askomdch-cucumber\\src\\test\\resources\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
    }

    public WebDriver getDriver() {
        return driver;
    }

    public void quitBrowser() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}