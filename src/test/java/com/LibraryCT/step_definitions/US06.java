package com.LibraryCT.step_definitions;

import com.LibraryCT.pages.BookPage;
import com.LibraryCT.utilities.BrowserUtils;
import com.LibraryCT.utilities.DB_Util;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.util.List;

public class US06 {

    BookPage bookPage = new BookPage();
    List<String> actualBookCategories;

    @When("I take all book categories in UI")
    public void i_take_all_book_categories_in_ui() {



    }
    @When("I execute query to get book categories")
    public void i_execute_query_to_get_book_categories() {

        actualBookCategories = BrowserUtils.getAllSelectOptions(bookPage.mainCategoryElement);
        actualBookCategories.remove(0);
        System.out.println("actualBookCategories = " + actualBookCategories);

    }
    @Then("verify book categories must match book_categories table from DB")
    public void verify_book_categories_must_match_book_categories_table_from_db() {

        DB_Util.runQuery("select name from book_categories");

        List<String> expectedBooksCategories = DB_Util.getColumnDataAsList(1);

        System.out.println("expectedBooksCategories = " + expectedBooksCategories);

        Assert.assertEquals("couldn't match names",expectedBooksCategories,actualBookCategories);

    }

}
