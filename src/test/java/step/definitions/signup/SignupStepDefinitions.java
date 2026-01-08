package step.definitions.signup;

import dependency.injection.UtilClass;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SignupStepDefinitions {
    private WebDriver driver;
    private WebDriverWait wait;
    private UtilClass utilClass;

    public SignupStepDefinitions(UtilClass utilClass){
        this.utilClass = utilClass;
    }

    private String WebsiteUrl= "https://askomdch.com/";
    @Before
    public void setUp(){
        driver = new ChromeDriver();
    }
    @Given("I am on any page of AskomDch website")
    public void i_am_on_any_page_of_askom_dch_website() {
        driver.get(WebsiteUrl);
        driver.manage().window().maximize();

    }
    @When("I click on the {string} link in the navigation bar")
    public void i_click_on_the_link_in_the_navigation_bar(String string) {
        driver.findElement(By.linkText("Account")).click();
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement form = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".u-column2.col-2 form ")));
        assertTrue(form.isDisplayed());
    }
    @And("I enter {string} {string} and {string} in the registration form")
    public void i_enter_and_in_the_registration_form(String username, String email, String password) {
        UtilClass.username = username;
        driver.findElement(By.id("reg_username")).sendKeys(username);
        driver.findElement(By.id("reg_email")).sendKeys(email);
        driver.findElement(By.id("reg_password")).sendKeys(password);
    }
    @When("I click the {string} button")
    public void i_click_the_button(String string) {
        driver.findElement(By.name("register")).click();
    }
    @Then("I should be directed to Dashboard")
    public void i_should_be_directed_to_dashboard() {
        WebElement loggedUser = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".woocommerce-MyAccount-content p")));

        String expectedResult = "Hello "+utilClass.username+" (not "+utilClass.username+"? Log out)";
        String actualResult = loggedUser.getText();
        assertEquals("Something went wrong", expectedResult, actualResult );
        assertTrue(loggedUser.isDisplayed());
        System.out.println("Expected Username is: "+expectedResult+" Actual Username is: "+actualResult);
    }
    @After
    public void tearDown(){
        driver.quit();
    }
}