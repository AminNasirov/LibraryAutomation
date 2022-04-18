package com.LibraryCT.step_definitions;

import com.LibraryCT.utilities.DB_Util;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.util.List;

public class US_01_step_definitions {

    @Given("Establish the database connection")
    public void establishTheDatabaseConnection() {

        String url ="jdbc:mysql://34.230.35.214:3306/library2";
        String username ="library2_client";
        String password = "6s2LQQTjBcGFfDhY";

        DB_Util.createConnection(url,username,password);

    }


    @When("Execute query to get all IDs from users")
    public void execute_query_to_get_all_i_ds_from_users() {

        DB_Util.runQuery("select id from users");

        System.out.println("DB_Util.getColumnDataAsList(\"id\") = "
                + DB_Util.getColumnDataAsList("id"));
    }
    @Then("verify all users has unique ID")
    public void verify_all_users_has_unique_id() {

        DB_Util.runQuery("select count(id) from users");

        int expectedID = Integer.parseInt(DB_Util.getFirstData());

        DB_Util.runQuery("select count(distinct id) from users");

        int uniqueID = Integer.parseInt(DB_Util.getFirstData());

        Assert.assertEquals(expectedID,uniqueID);


    }

    @When("Execute query to get all columns")
    public void execute_query_to_get_all_columns() {

        DB_Util.runQuery("select * from users");
        System.out.println("DB_Util.getColumnNames() = " +
                "" + DB_Util.getColumnNames());

    }
    @Then("verify the below columns are listed in result")
    public void verify_the_below_columns_are_listed_in_result(List<String> expectedTable) {

        DB_Util.runQuery("select * from users");
        List<String> actualTable = DB_Util.getColumnNames();

        Assert.assertEquals(actualTable,expectedTable);

    }

}
