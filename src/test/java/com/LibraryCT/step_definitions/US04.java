package com.LibraryCT.step_definitions;

import com.LibraryCT.utilities.DB_Util;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class US04 {

    @When("I execute query to find most popular user")
    public void i_execute_query_to_find_most_popular_user() {

        DB_Util.runQuery("select full_name, count(*) from users u\n" +
                "join book_borrow bb on u.id = bb.user_id\n" +
                "group by full_name\n" +
                "order by 2 desc");
        System.out.println("most popular user = " + DB_Util.getFirstData());
    }
    @Then("verify {string} is the user who reads the most")
    public void verify_is_the_user_who_reads_the_most(String string) {
        Assert.assertEquals("NAME IS NOT SAME",string,DB_Util.getFirstData());

    }


}
