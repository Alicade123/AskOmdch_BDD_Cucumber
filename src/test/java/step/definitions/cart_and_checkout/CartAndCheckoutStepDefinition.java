package step.definitions.cart_and_checkout;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class CartAndCheckoutStepDefinition {
    @Given("user is on the home page")
    public void goToHomePage(){

    }
    @When("user navigates to the Store page")
    public void goToStorePage(){

    }
    @And("user adds a product to the cart")
    public void addProductsToCart(){

    }
    @And ("user opens the cart page")
    public void goToCartPage(){

    }
    @Then("product should be listed in the cart")
    public void gocCheckProductsInCarts(){

    }

    @When ("user proceeds to checkout")
    public void clickProceed(){

    }
    @And ("user fills billing details")
    public void fillBillingDetails(){

    }
    @And ("user places the order")
    public void confirmOrderPlacement(){

    }
    @Then ("order should be placed successfully")
    public void checkOrderWheneverPlacedSuccessfully(){

    }
}
