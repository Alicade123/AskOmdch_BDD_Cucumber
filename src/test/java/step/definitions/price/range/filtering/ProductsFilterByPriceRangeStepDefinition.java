package step.definitions.price.range.filtering;

import dependency.injection.DriverFactory;
import dependency.injection.UtilClass;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class ProductsFilterByPriceRangeStepDefinition {
    public WebDriver driver = DriverFactory.getDriver();
    private WebDriverWait wait;
    private By storeLink = By.linkText("Store");
    private By rangeForm = By.id("woocommerce_price_filter-3");
    public ProductsFilterByPriceRangeStepDefinition(UtilClass utilClass){
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    @Given("I'm on the landing page of the AskOmDch Website")
    public void initializeSite(){
    driver.get(UtilClass.SITEURL);
    driver.manage().window().maximize();
    }
    @When("I click on Store tab I navigate to products page")
    public void navigateToProductsPage(){
    driver.findElement(storeLink).click();
    assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(rangeForm)).isDisplayed());
    }
    @And("I choose the {double} range and {double} range")
    public void setPriceRange(double minValue, double maxValue){
    By filterButton = By.cssSelector(".price_slider_amount button");

        WebElement priceForm = driver.findElement(rangeForm);
        JavascriptExecutor js =((JavascriptExecutor)driver);

        js.executeScript(
                "arguments[0].scrollIntoView({block:'center'});" +
                        "document.getElementById('min_price').value=arguments[1];" +
                        "document.getElementById('max_price').value=arguments[2];",
                priceForm, minValue, maxValue
        );
        driver.findElement(filterButton).click();

    }
    @Then("I get products that fall in the price range I chosen {int} and {int}.")
    public void checkRetrievedProductsPrices(int minValue, int maxValue){
        By filteredProductsPrices = By.cssSelector(".astra-shop-summary-wrap .woocommerce-Price-amount.amount bdi");
        List<WebElement> pricesFound = driver.findElements(filteredProductsPrices);
        List<Double> validatedPrices = new ArrayList<>();
        for(WebElement prc : pricesFound){
            validatedPrices.add(Double.parseDouble(prc.getText().replaceAll("[^0-9.]", "")));
        }
        System.out.print("Provided MinValue: "+minValue+" Provided MaxValue: "+maxValue+" Found Product Prices: "+validatedPrices);
        assertTrue(validatedPrices.stream().allMatch(e->e>=minValue&&e<=maxValue));
    }

}
