package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import pages.AccountPage;

public class RegistrationSteps {
    
    private AccountPage accountPage;
    
    public RegistrationSteps() {
        this.accountPage = new AccountPage();
    }
    
    @Given("I am on the account page")
    public void iAmOnTheAccountPage() {
        accountPage.navigateToAccountPage();
    }
    
    @When("I enter username {string} in registration form")
    public void iEnterUsernameInRegistrationForm(String username) {
        accountPage.enterRegUsername(username);
    }
    
    @And("I enter email {string} in registration form")
    public void iEnterEmailInRegistrationForm(String email) {
        accountPage.enterRegEmail(email);
    }
    
    @And("I enter password {string} in registration form")
    public void iEnterPasswordInRegistrationForm(String password) {
        accountPage.enterRegPassword(password);
    }
    
    @And("I click on Register button")
    public void iClickOnRegisterButton() {
        accountPage.clickRegisterButton();
    }
    
    @Then("I should see registration success message")
    public void iShouldSeeRegistrationSuccessMessage() {
        Assert.assertTrue("Registration success message is not displayed", 
                         accountPage.isSuccessMessageDisplayed());
    }
    
    @And("I should be logged in")
    public void iShouldBeLoggedIn() {
        Assert.assertTrue("User is not logged in", accountPage.isLoggedIn());
    }
    
    @Then("I should see error {string}")
    public void iShouldSeeError(String expectedError) {
        Assert.assertTrue("Error message is not displayed", 
                         accountPage.isErrorMessageDisplayed());
        String actualError = accountPage.getErrorMessage();
        Assert.assertTrue("Expected error message not found. Expected: " + expectedError + 
                         ", Actual: " + actualError,
                         actualError.contains(expectedError));
    }
}