package com.LibraryCT.pages;

import com.LibraryCT.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class BookPage {

    public BookPage(){
        PageFactory.initElements(Driver.getDriver(),this);
    }

    @FindBy(xpath = "//span[.='Books']")
    public WebElement booksButton;

    @FindBy(xpath = "//input[@type='search']")
    public WebElement search;

    @FindBy(xpath = "(//table/tbody//td[3])[1]")
    public WebElement bookName;

    @FindBy(xpath = "(//table/tbody//td[4])[1]")
    public WebElement authorName;

    @FindBy(xpath = "(//table/tbody//td[6])[1]")
    public WebElement year;

    public void clickButton(String stringText){
        WebElement button = Driver.getDriver().findElement(By.xpath("//span[.='"+stringText+"']"));
        button.click();
    }

    public List<String> getList(){
        List<String> list=new ArrayList<>();
        list.add(bookName.getText());
        list.add(authorName.getText());
        list.add(year.getText());

        return list;
    }







}
