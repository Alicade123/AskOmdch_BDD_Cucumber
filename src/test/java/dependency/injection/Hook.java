package dependency.injection;

import io.cucumber.java.After;

public class Hook {
    @After
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
