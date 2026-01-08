package step.definitions.login;

import dependency.injection.UtilClass;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.LoginFunctionality;

import java.time.Duration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;



public class LoginUsingPOMStructure   {
    private UtilClass utilClass;
    private LoginFunctionality loginFunctionality;
    public LoginUsingPOMStructure(UtilClass utilClass, LoginFunctionality loginFunctionality){
        this.utilClass = utilClass;
        this.loginFunctionality = loginFunctionality;
    }
    @Before
    public void starting(){
        loginFunctionality.setUp();
    }
    @Given("As I'm on the AskOmDch Landing page I navigate to account page")
    public void clickAccount(){
        loginFunctionality.goToAccountPage();
    }
    @When("I enter valid credentials {string} and {string}")
    public void enterCredentials(String username, String password){
       loginFunctionality.enterCredentials(username,password);
       utilClass.username = username;
    }

    @Then("I get redirected to Dashboard Page")
    public void getDashboardAccess(){
        loginFunctionality.accessDashboard(utilClass.username);
    }
    @After
    public void close(){
     loginFunctionality.tearDown().quit();
    }

}
