package com.LibraryCT.pages;

import com.LibraryCT.utilities.ConfigurationReader;
import com.LibraryCT.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    public LoginPage(){
        PageFactory.initElements(Driver.getDriver(),this);
    }


    @FindBy(css = "#inputEmail")
    public WebElement username;

    @FindBy(css = "#inputPassword")
    public WebElement password;

    @FindBy(css = ".btn")
    public WebElement signIn;

    public void loginAsStudent(){
        username.sendKeys(ConfigurationReader.getProperty("StudentEmail"));
        password.sendKeys(ConfigurationReader.getProperty("StudentPassword"));
        signIn.click();
    }

    public void loginAsLibrarian(){
        username.sendKeys(ConfigurationReader.getProperty("LibrarianEmail"));
        password.sendKeys(ConfigurationReader.getProperty("LibrarianPassword"));
        signIn.click();
    }






}
