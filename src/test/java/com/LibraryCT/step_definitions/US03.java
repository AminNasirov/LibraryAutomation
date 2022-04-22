package com.LibraryCT.step_definitions;

import com.LibraryCT.utilities.DB_Util;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class US03 {

    @When("I execute query to find most popular book genre")
    public void i_execute_query_to_find_most_popular_book_genre() {

        DB_Util.runQuery("select bc.name,count(*) from book_borrow bb\n" +
                " inner join books b on bb.book_id = b.id\n" +
                " inner join book_categories bc on b.book_category_id=bc.id\n" +
                "group by name\n" +
                "order by 2 desc;");

        System.out.println("DB_Util.getFirstData() = " + DB_Util.getFirstData());


    }
    @Then("verify {string} is the most popular book genre.")
    public void verify_is_the_most_popular_book_genre(String string) {

        Assert.assertEquals(string,DB_Util.getFirstData());

    }

}
