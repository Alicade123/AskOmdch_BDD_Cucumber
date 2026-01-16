package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import pages.AccountPage;
import utils.DriverManager;

public class LoginSteps {
    
    private AccountPage accountPage;
    
    public LoginSteps() {
        this.accountPage = new AccountPage();
    }
    
    @When("I enter username {string} in login form")
    public void iEnterUsernameInLoginForm(String username) {
        accountPage.enterLoginUsername(username);
    }
    
    @And("I enter password {string} in login form")
    public void iEnterPasswordInLoginForm(String password) {
        accountPage.enterLoginPassword(password);
    }
    
    @And("I check Remember me checkbox")
    public void iCheckRememberMeCheckbox() {
        accountPage.checkRememberMe();
    }
    
    @And("I click on Login button")
    public void iClickOnLoginButton() {
        accountPage.clickLoginButton();
    }
    
    @Then("I should be logged in successfully")
    public void iShouldBeLoggedInSuccessfully() {
        Assert.assertTrue("User is not logged in", accountPage.isLoggedIn());
    }
    
//    @And("I should see {string} page")
//    public void iShouldSeePage(String pageName) {
//        if (pageName.equalsIgnoreCase("My account")) {
//            Assert.assertTrue("My Account page is not displayed", 
//                             accountPage.isMyAccountPageDisplayed());
//        }
//    }
    
    @And("Remember me cookie should be set")
    public void rememberMeCookieShouldBeSet() {
        Assert.assertTrue("Remember me cookie is not set", 
                         accountPage.isRememberMeCookieSet());
    }
    
    @Then("I should see login error {string}")
    public void iShouldSeeLoginError(String expectedError) {
        Assert.assertTrue("Login error message is not displayed", 
                         accountPage.isErrorMessageDisplayed());
//        String actualError = accountPage.getErrorMessage();
//        Assert.assertTrue("Expected error message not found. Expected: " + expectedError + 
//                         ", Actual: " + actualError,
//                         actualError.contains(expectedError));
    }
    
    @When("I click on {string} link")
    public void iClickOnLink(String linkText) {
        if (linkText.equalsIgnoreCase("Lost your password?")) {
            accountPage.clickLostPasswordLink();
        }
    }
    
    @Then("I should be on password reset page")
    public void iShouldBeOnPasswordResetPage() {
        String currentUrl = DriverManager.getInstance().getDriver().getCurrentUrl();
        Assert.assertTrue("Not on password reset page", 
                         currentUrl.contains("lost-password"));
    }
    
    @Given("I am logged in with username {string} and password {string}")
    public void iAmLoggedInWithUsernameAndPassword(String username, String password) {
        accountPage.navigateToAccountPage();
        accountPage.enterLoginUsername(username);
        accountPage.enterLoginPassword(password);
        accountPage.clickLoginButton();
        Assert.assertTrue("User is not logged in", accountPage.isLoggedIn());
    }
    
    @When("I click on Logout link")
    public void iClickOnLogoutLink() {
        accountPage.clickLogout();
    }
    
    @Then("I should be logged out successfully")
    public void iShouldBeLoggedOutSuccessfully() {
        Assert.assertTrue("User is still logged in", accountPage.isLoggedOut());
    }
    
//    @And("I should see Login form")
//    public void iShouldSeeLoginForm() {
//        Assert.assertTrue("Login form is not displayed", 
//                         accountPage.isLoginFormDisplayed());
//    }
}