package com.LibraryCT.step_definitions;

import com.LibraryCT.pages.CommonAreaPage;
import com.LibraryCT.pages.LoginPage;
import com.LibraryCT.utilities.ConfigurationReader;
import com.LibraryCT.utilities.DB_Util;
import com.LibraryCT.utilities.Driver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class US02 {

    int actualNumberOfBorrowedBooks=0;

    LoginPage loginPage = new LoginPage();


    @Given("I am in the homepage of the library app as a librarian")
    public void i_am_in_the_homepage_of_the_library_app() {

         loginPage.loginAsLibrarian();

    }
    @When("I take borrowed books number")
    public void i_take_borrowed_books_number() {

        actualNumberOfBorrowedBooks = Integer.parseInt(new CommonAreaPage().getCountBorrowedBooks.getText());
        System.out.println("actualNumberOfBorrowedBooks = " + actualNumberOfBorrowedBooks);

    }
    @Then("borrowed books number information must match with DB")
    public void borrowed_books_number_information_must_match_with_db() {

        DB_Util.runQuery("select count(*) from book_borrow where is_returned=0");

       int expectedNumberOfBorrowedBooks = Integer.parseInt(DB_Util.getFirstData());
        System.out.println("expectedNumberOfBorrowedBooks = " + expectedNumberOfBorrowedBooks);

        Assert.assertEquals(expectedNumberOfBorrowedBooks,actualNumberOfBorrowedBooks);

    }


}
