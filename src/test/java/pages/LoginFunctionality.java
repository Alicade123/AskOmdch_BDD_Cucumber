package pages;

import org.junit.After;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class LoginFunctionality {
    private WebDriver driver;
    private final String  websiteUrl = "https://askomdch.com/";
    private WebDriverWait wait;

    public  void setUp(){
        driver =new ChromeDriver();
    }

    public void goToAccountPage(){
        driver.get(websiteUrl);
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement AccountLink = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Account")));
        AccountLink.click();
    }

    public void enterCredentials(String username, String password){
        WebElement name= wait.until(ExpectedConditions.elementToBeClickable(By.id("username")));
        WebElement passcode= wait.until(ExpectedConditions.elementToBeClickable(By.id("password")));
        name.sendKeys(username);
        passcode.sendKeys(password);
        driver.findElement(By.cssSelector("button[value='Log in']")).click();
    }

    public void accessDashboard(String loggedUsername){
        WebElement loggedUser = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".woocommerce-MyAccount-content p")));
        String expectedResult = "Hello "+loggedUsername+" (not "+loggedUsername+"? Log out)";
        String actualResult = loggedUser.getText();
        assertEquals("Something went wrong", expectedResult, actualResult );
        assertTrue(loggedUser.isDisplayed());
        System.out.println("Expected Username is: "+expectedResult+" Actual Username is: "+actualResult+" \nBye!!!");
    }

    public WebDriver tearDown(){
            return driver;
    }

}