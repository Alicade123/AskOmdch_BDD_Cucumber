package steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import utils.DriverManager;

public class Hooks {
    
    private DriverManager driverManager;
    
    public Hooks() {
        this.driverManager = DriverManager.getInstance();
    }
    
    @Before
    public void setUp(Scenario scenario) {
        System.out.println("Starting scenario: " + scenario.getName());
        driverManager.setup();
    }
    
    @After
    public void tearDown(Scenario scenario) {
        // Take screenshot if scenario fails
        if (scenario.isFailed()) {
            byte[] screenshot = ((TakesScreenshot) driverManager.getDriver())
                    .getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", scenario.getName());
        }
        
        System.out.println("Scenario " + scenario.getName() + " - Status: " + scenario.getStatus());
        driverManager.quitBrowser();
    }
}