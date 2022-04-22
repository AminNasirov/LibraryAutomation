package com.LibraryCT.step_definitions;

import com.LibraryCT.pages.BookPage;
import com.LibraryCT.utilities.BrowserUtils;
import com.LibraryCT.utilities.DB_Util;
import com.LibraryCT.utilities.Driver;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.Keys;

import java.security.Key;

public class US05 {

    BookPage bookPage = new BookPage();

    @When("I navigate to {string} page")
    public void i_navigate_to_page(String string) {
        bookPage.clickButton(string);
    }
    @When("I open a book called {string}")
    public void i_open_a_book_called(String string) {

        bookPage.search.sendKeys(string+ Keys.ENTER);
        BrowserUtils.waitFor(2);

    }
    @When("I execute query to get the book information from books table")
    public void i_execute_query_to_get_the_book_information_from_books_table() {

        DB_Util.runQuery("select name, author,year from books where name='Chordeiles minor'");

        System.out.println(" expected information from DB = " + DB_Util.getRowDataAsList(1));


    }
    @Then("verify book DB and UI information must match")
    public void verify_book_db_and_ui_information_must_match() {

        System.out.println("actual information from UI " + bookPage.getList());

        Assert.assertEquals(DB_Util.getRowDataAsList(1),bookPage.getList());


    }


}
