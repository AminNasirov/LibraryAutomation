package com.LibraryCT.step_definitions;


import com.LibraryCT.utilities.ConfigurationReader;
import com.LibraryCT.utilities.DB_Util;
import com.LibraryCT.utilities.Driver;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.util.concurrent.TimeUnit;

public class Hooks {



    //for ui
    @Before("@ui")
    public void uiSetup() {
        Driver.getDriver().get(ConfigurationReader.getProperty("LibraryCTURL"));

    }

    //for ui
    @After("@ui")
    public void uiTearDown(Scenario scenario) {

        // check if scenario failed or not
        if (scenario.isFailed()) {
            //this is how we take screenshot in selenium
            TakesScreenshot ts = (TakesScreenshot) Driver.getDriver();
            byte[] screenshot = ts.getScreenshotAs(OutputType.BYTES);
            // scenario.attach(screenshot,"image/png","what ever we want");
            scenario.attach(screenshot, "image/png", "Image for failed step");
        }
        Driver.closeDriver();
    }

    //for DB
    @Before("@db")
    public void dbSetup() {
        String url = ConfigurationReader.getProperty("library2.db.url");
        String username = ConfigurationReader.getProperty("library2.db.username");
        String password = ConfigurationReader.getProperty("library2.db.password");
        DB_Util.createConnection(url, username, password);
    }

    //for DB
    @After("@db")
    public void dbTearDown() {
        DB_Util.destroy();
    }
}

