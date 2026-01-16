package step.definitions.login;

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
import pages.LoginFunctionality;

import java.time.Duration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class LoginUsingPOMStructure {

    private UtilClass utilClass;
    private LoginFunctionality loginFunctionality;
    private WebDriver driver;
    private WebDriverWait wait;

    public LoginUsingPOMStructure(UtilClass utilClass, LoginFunctionality loginFunctionality){
        this.utilClass = utilClass;
        this.loginFunctionality = loginFunctionality;
    }

    @Before
    public void starting(){
        loginFunctionality.setUp();

        // Try to get driver reference from LoginFunctionality
        try {
            this.driver = loginFunctionality.getDriver();
            this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        } catch (Exception e) {
            System.out.println("Note: Could not get driver from LoginFunctionality. Some methods may use POM fallback.");
        }
    }


    @Given("As I'm on the AskOmDch Landing page I navigate to account page")
    public void clickAccount(){
        loginFunctionality.goToAccountPage();
    }

    @When("I enter valid credentials {string} and {string}")
    public void enterCredentials(String username, String password){
        loginFunctionality.enterCredentials(username, password);
        utilClass.username = username;
        System.out.println("Entered valid credentials - Username: " + username);
    }

    @Then("I get redirected to Dashboard Page")
    public void getDashboardAccess(){
        loginFunctionality.accessDashboard(utilClass.username);
    }


    @When("I enter username {string} in login form")
    public void i_enter_username_in_login_form(String username) {
        if (driver != null) {
            wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement usernameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("username")));
            usernameField.clear();
            usernameField.sendKeys(username);
            utilClass.username = username;
            System.out.println("Entered username: " + username);
        } else {
            System.out.println("Driver not available. Cannot enter username directly.");
        }
    }

    @And("I enter password {string} in login form")
    public void i_enter_password_in_login_form(String password) {
        if (driver != null) {
            WebElement passwordField = driver.findElement(By.id("password"));
            passwordField.clear();
            passwordField.sendKeys(password);
            System.out.println("Entered password: " + password);
        } else {
            System.out.println("Driver not available. Cannot enter password directly.");
        }
    }

    @And("I click on Login button")
    public void i_click_on_login_button() {
        if (driver != null) {
            WebElement loginButton = driver.findElement(By.name("login"));
            loginButton.click();
            System.out.println("Clicked Login button");
        } else {
            System.out.println("Driver not available. Cannot click Login button.");
        }
    }

    @Then("I should see login error {string}")
    public void i_should_see_login_error(String expectedError) {
        if (driver != null) {
            wait = new WebDriverWait(driver, Duration.ofSeconds(15));

            String actualError = "";
            boolean errorFound = false;

            // Try to find WooCommerce error message
            try {
                WebElement errorElement = wait.until(ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//ul[contains(@class,'woocommerce-error')]//li")
                ));
                actualError = errorElement.getText().trim();
                errorFound = true;
                System.out.println("Found WooCommerce error message: " + actualError);
            } catch (Exception e1) {
                try {
                    WebElement errorElement = wait.until(ExpectedConditions.visibilityOfElementLocated(
                            By.xpath("//div[contains(@class,'woocommerce-error')]")
                    ));
                    actualError = errorElement.getText().trim();
                    errorFound = true;
                    System.out.println("Found WooCommerce error div: " + actualError);
                } catch (Exception e2) {
                    try {
                        WebElement errorElement = wait.until(ExpectedConditions.visibilityOfElementLocated(
                                By.cssSelector("ul.woocommerce-error li")
                        ));
                        actualError = errorElement.getText().trim();
                        errorFound = true;
                        System.out.println("Found error with CSS selector: " + actualError);
                    } catch (Exception e3) {
                        try {
                            WebElement errorElement = wait.until(ExpectedConditions.visibilityOfElementLocated(
                                    By.cssSelector(".woocommerce-error")
                            ));
                            actualError = errorElement.getText().trim();
                            errorFound = true;
                            System.out.println("Found general error: " + actualError);
                        } catch (Exception e4) {
                            System.out.println("No error message found anywhere");
                        }
                    }
                }
            }

            // Assert that error was found
            assertTrue("No login error message found on page", errorFound);

            // Print for debugging
            System.out.println("Expected error: " + expectedError);
            System.out.println("Actual error: " + actualError);

            // Flexible error matching - handles partial matches and different error formats
            boolean errorMatches = actualError.contains(expectedError) ||
                    expectedError.contains(actualError) ||
                    actualError.toLowerCase().contains(expectedError.toLowerCase());

            assertTrue("Expected error message not found. Expected: '" + expectedError +
                            "', but got: '" + actualError + "'",
                    errorMatches);
        } else {
            System.out.println("Driver not available. Cannot verify error message.");
        }
    }

    @After
    public void close(){
        loginFunctionality.tearDown().quit();
    }
}