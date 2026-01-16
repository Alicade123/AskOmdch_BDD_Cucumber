package step.definitions.navigation;

import dependency.injection.DriverFactory;
import dependency.injection.UtilClass;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.time.Duration;

import static org.junit.Assert.assertEquals;


public class NavigationStepDefinition {
    private UtilClass utilClass;
    private WebDriver driver  = DriverFactory.getDriver();
    private WebDriverWait wait;
    public NavigationStepDefinition (UtilClass utilClass){
        this.utilClass = utilClass;
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }


    @Given("I'm On any page of the AskOmDch website")
    public void openSite(){
        driver.get(UtilClass.SITEURL);
        driver.manage().window().maximize();
    }
    @When("I click on {string}")
    public void clickLink(String link){
    wait.until(ExpectedConditions.elementToBeClickable(By.linkText(link))).click();
    }
    @Then("I should be directed to the page with {string}")
    public void checkPage(String pageTitle){
    assertEquals(pageTitle, driver.getTitle());
    }



}
