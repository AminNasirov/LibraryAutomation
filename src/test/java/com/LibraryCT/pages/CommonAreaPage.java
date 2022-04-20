package com.LibraryCT.pages;

import com.LibraryCT.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CommonAreaPage {

    public CommonAreaPage(){
        PageFactory.initElements(Driver.getDriver(),this);
    }


    @FindBy(xpath = "//h2[@id='borrowed_books']")
    public WebElement getCountBorrowedBooks;

    @FindBy(xpath = "//h2[@id='book_count']")
    public WebElement getCountBooks;

    @FindBy(xpath = "//h2[@id='user_count']")
    public WebElement getCountUsers;




}
