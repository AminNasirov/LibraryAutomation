package com.LibraryCT.step_definitions;

import com.LibraryCT.utilities.DB_Util;
import com.LibraryCT.utilities.Driver;
import io.cucumber.java.Scenario;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class Hooks {

   /* @Before
    public void setupDataBase(){
        String url ="jdbc:mysql://34.230.35.214:3306/library2";
        String username ="library2_client";
        String password = "6s2LQQTjBcGFfDhY";

        DB_Util.createConnection(url,username,password);
    }

    */


    @After
    public void tearDown(Scenario scenario){

        if(scenario.isFailed()){
            TakesScreenshot ts = (TakesScreenshot) Driver.getDriver();
            byte[] screenshot = ts.getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot,"image/png","failure_ss");
        }
        Driver.closeDriver();

       // DB_Util.destroy();



    }


}
