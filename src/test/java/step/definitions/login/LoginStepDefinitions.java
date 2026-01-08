//package step.definitions.login;
//
//import dependency.injection.UtilClass;
//import io.cucumber.java.After;
//import io.cucumber.java.Before;
//import io.cucumber.java.en.Given;
//import io.cucumber.java.en.Then;
//import io.cucumber.java.en.When;
//
//
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.WebDriverWait;
//
//
//import java.time.Duration;
//
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertTrue;
//
//public class LoginStepDefinitions   {
//    private UtilClass utilClass;
//    private WebDriver driver;
//    private final String  websiteUrl = "https://askomdch.com/";
//    private WebDriverWait wait;
//    public LoginStepDefinitions(UtilClass utilClass){
//        this.utilClass = utilClass;
//    }
//    @Before
//    public void setUp(){
//        driver =new ChromeDriver();
//    }
//    @Given("As I'm on the AskOmDch Landing page I navigate to account page")
//    public void clickAccount(){
//        driver.get(websiteUrl);
//        driver.manage().window().maximize();
//        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
//        WebElement AccountLink = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Account")));
//        AccountLink.click();
//        WebElement form = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".u-column1.col-1 form")));
//        assertTrue(form.isDisplayed());
//    }
//    @When("I enter valid credentials {string} and {string}")
//    public void enterCredentials(String username, String password){
//        WebElement name= wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("username")));
//        WebElement passcode= wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password")));
//        name.sendKeys(username);
//        passcode.sendKeys(password);
//        driver.findElement(By.cssSelector("button[value='Log in']")).click();
//        utilClass.username  = username;
//    }
//
//    @Then("I get redirected to Dashboard Page")
//    public void getDashboardAccess(){
//        WebElement loggedUser = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".woocommerce-MyAccount-content p")));
//        String expectedResult = "Hello "+utilClass.username+" (not "+utilClass.username+"? Log out)";
//        String actualResult = loggedUser.getText();
//        assertEquals("Something went wrong", expectedResult, actualResult );
//        assertTrue(loggedUser.isDisplayed());
//        System.out.println("Expected Username is: "+expectedResult+" Actual Username is: "+actualResult);
//
//    }
//    @After
//    public void tearDown(){
//        driver.quit();
//    }
//
//}
