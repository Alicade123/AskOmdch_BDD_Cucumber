package step.definitions.signup;

import dependency.injection.DriverFactory;
import dependency.injection.UtilClass;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SignupStepDefinitions {

    private WebDriver driver = DriverFactory.getDriver();
    private WebDriverWait wait;
    private UtilClass utilClass;
    private String WebsiteUrl= UtilClass.SITEURL;
    public SignupStepDefinitions(UtilClass utilClass){
        this.utilClass = utilClass;
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

    // ===== ORIGINAL METHOD FOR "Registering successful" SCENARIO =====
    @When("I enter {string} {string} and {string} in the registration form")
    public void i_enter_and_in_the_registration_form(String username, String email, String password) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Store username for verification
        UtilClass.username = username;

        // Enter username
        WebElement usernameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("reg_username")));
        usernameField.clear();
        usernameField.sendKeys(username);

        // Enter email
        WebElement emailField = driver.findElement(By.id("reg_email"));
        emailField.clear();
        emailField.sendKeys(email);

        // Enter password
        WebElement passwordField = driver.findElement(By.id("reg_password"));
        passwordField.clear();
        passwordField.sendKeys(password);

        System.out.println("Entered credentials - Username: " + username + ", Email: " + email);
    }

    @When("I click the {string} button")
    public void i_click_the_button(String buttonName) {
        driver.findElement(By.name("register")).click();
        System.out.println("Clicked " + buttonName + " button");
    }

    @Then("I should be directed to Dashboard")
    public void i_should_be_directed_to_dashboard() {
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement loggedUser = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".woocommerce-MyAccount-content p")));
        String expectedResult = "Hello "+utilClass.username+" (not "+utilClass.username+"? Log out)";
        String actualResult = loggedUser.getText();
        assertEquals("Something went wrong", expectedResult, actualResult );
        assertTrue(loggedUser.isDisplayed());
        System.out.println("Expected Username is: "+expectedResult+" Actual Username is: "+actualResult);
    }

    @When("I enter username {string} in registration form")
    public void i_enter_username_in_registration_form(String username) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement usernameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("reg_username")));
        usernameField.clear();

        // Only enter value if it's not empty
        if (username != null && !username.isEmpty()) {
            usernameField.sendKeys(username);
            System.out.println("Entered username: " + username);
        } else {
            System.out.println("Username field left empty (intentionally)");
        }
    }

    @And("I enter email {string} in registration form")
    public void i_enter_email_in_registration_form(String email) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("reg_email")));
        emailField.clear();

        // Only enter value if it's not empty
        if (email != null && !email.isEmpty()) {
            emailField.sendKeys(email);
            System.out.println("Entered email: " + email);
        } else {
            System.out.println("Email field left empty (intentionally)");
        }
    }

    @And("I enter password {string} in registration form")
    public void i_enter_password_in_registration_form(String password) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("reg_password")));
        passwordField.clear();

        // Only enter value if it's not empty
        if (password != null && !password.isEmpty()) {
            passwordField.sendKeys(password);
            System.out.println("Entered password: " + password);
        } else {
            System.out.println("Password field left empty (intentionally)");
        }
    }

    @And("I click on Register button")
    public void i_click_on_register_button() {
        WebElement registerButton = driver.findElement(By.name("register"));
        registerButton.click();
        System.out.println("Clicked Register button");
    }

    @Then("I should see error {string}")
    public void i_should_see_error(String expectedError) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        String actualError = "";
        boolean errorFound = false;

        try {
            // Check username field for validation message
            WebElement usernameField = driver.findElement(By.id("reg_username"));
            String validationMsg = usernameField.getAttribute("validationMessage");
            if (validationMsg != null && !validationMsg.isEmpty()) {
                actualError = validationMsg;
                errorFound = true;
                System.out.println("Found HTML5 validation message on username field: " + actualError);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        // Check email field for validation message
        if (!errorFound) {
            try {
                WebElement emailField = driver.findElement(By.id("reg_email"));
                String validationMsg = emailField.getAttribute("validationMessage");
                if (validationMsg != null && !validationMsg.isEmpty()) {
                    actualError = validationMsg;
                    errorFound = true;
                    System.out.println("Found HTML5 validation message on email field: " + actualError);
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        // Check password field for validation message
        if (!errorFound) {
            try {
                WebElement passwordField = driver.findElement(By.id("reg_password"));
                String validationMsg = passwordField.getAttribute("validationMessage");
                if (validationMsg != null && !validationMsg.isEmpty()) {
                    actualError = validationMsg;
                    errorFound = true;
                    System.out.println("Found HTML5 validation message on password field: " + actualError);
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        if (!errorFound) {
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
        }

        assertTrue("No error message found (neither HTML5 validation nor WooCommerce error)", errorFound);

        System.out.println("Expected error: " + expectedError);
        System.out.println("Actual error: " + actualError);

        assertTrue("Expected error message not found. Expected: '" + expectedError +
                        "', but got: '" + actualError + "'",
                actualError.contains(expectedError));
    }


}