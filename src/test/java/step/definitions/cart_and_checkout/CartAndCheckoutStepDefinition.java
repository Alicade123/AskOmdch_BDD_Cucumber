package step.definitions.cart_and_checkout;


import io.cucumber.datatable.DataTable;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Map;
public class CartAndCheckoutStepDefinition {

    private WebDriverWait wait ;
    private WebDriver driver;

    @Before
    public void setUp(){
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds((15)));
    }


    @Given("I have product number {int} in my cart")
    public void i_have_items_in_my_cart(int index) {
        driver.get("https://askomdch.com/");
        WebElement storeLink = driver.findElement(By.linkText("Store"));
        storeLink.click();
        List<WebElement> products = driver.findElements(By.cssSelector(".products .product"));
        WebElement chosenProduct = products.get(index - 1);
        WebElement addToCartBtn = chosenProduct.findElement(By.cssSelector("a.button"));
        addToCartBtn.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(@class,'added_to_cart') and text()='View cart']")));
        WebElement cartBadge = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[class='ast-cart-menu-wrap'] span")));
        cartBadge.click();
    }

    @And("I am on the checkout page")
    public void i_am_on_the_checkout_page() {
        WebElement proceedToCheckoutBtn = driver.findElement(By.cssSelector("a[class='checkout-button button alt wc-forward']"));
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView({block:'center'});",proceedToCheckoutBtn);
        proceedToCheckoutBtn.click();
    }

    @When("I provide valid shipping details")
    public void i_provide_valid_shipping_details(DataTable dataTable) {
        Map<String, String> shippingDetails = dataTable.asMap(String.class, String.class);

        String fName = shippingDetails.get("firstName");
        String lName = shippingDetails.get("lastName");
        String country = shippingDetails.get("country");
        String streetNum = shippingDetails.get("streetAddress");
        String town = shippingDetails.get("town");
        String state = shippingDetails.get("state");
        String zipCode = shippingDetails.get("postCode");
        String phoneNum = shippingDetails.get("phone");
        String emailAddress = shippingDetails.get("email");

        WebElement fNameField = driver.findElement(By.id("billing_first_name"));
        WebElement lNameField = driver.findElement(By.id("billing_last_name"));
        WebElement streetAddressField = driver.findElement(By.id("billing_address_1"));
        WebElement townField = driver.findElement(By.id("billing_city"));
        WebElement stateField = driver.findElement(By.id("billing_state"));
        WebElement zipCodeField = driver.findElement(By.id("billing_postcode"));
        WebElement phoneNumField = driver.findElement(By.id("billing_phone"));
        WebElement emailField = driver.findElement(By.id("billing_email"));

        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView({block:'center'});",fNameField);
        fNameField.sendKeys(fName);
        lNameField.sendKeys(lName);
        Select options = new Select(driver.findElement(By.id("billing_country")));
        options.selectByValue(country);
        streetAddressField.sendKeys(streetNum);
        townField.sendKeys(town);
        new Select(driver.findElement(By.id("billing_state"))).selectByValue(state);

        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView({block:'center'});",zipCodeField);
        zipCodeField.sendKeys(zipCode);
        phoneNumField.sendKeys(phoneNum);
        emailField.sendKeys(emailAddress);


    }
    @And("I select")
    public void paymentMethod(DataTable paymentMethod){
//        List<String> pmethod = paymentMethod.asList();
//        List<WebElement>labels = driver.findElements(By.cssSelector("ul[class='wc_payment_methods payment_methods methods'] label"));
//        for (WebElement label : labels){
//            System.out.println(label.getText());
//            if(label.getText().contains(pmethod.get(0))){
//                String forId =  label.getAttribute("for");
//                driver.findElement(By.id(forId)).click();
//            }
//        }

    }

    @When("I place the order")
    public void i_place_the_order() {
        WebElement placeOrderBtn = driver.findElement(By.id("place_order"));
        placeOrderBtn.click();

    }

    @Then("I should see an order confirmation message")
    public void i_should_see_an_order_confirmation_message() {

    }
//    @After
//    public void tearDown(){
//        if(driver!= null) driver.quit();
//    }

}
