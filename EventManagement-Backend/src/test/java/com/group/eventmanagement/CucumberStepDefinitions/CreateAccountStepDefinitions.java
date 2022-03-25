package com.group.eventmanagement.CucumberStepDefinitions;

import com.group.eventmanagement.controller.UserController;
import com.group.eventmanagement.model.User;
import com.group.eventmanagement.repository.UserRepository;
import com.group.eventmanagement.service.UserService;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;

import static org.junit.Assert.*;

public class CreateAccountStepDefinitions {



    @Given("that {string} has not been selected by another user")
    public void that_has_not_been_selected_by_another_user(String string) {
//        UserRepository userRepository = new UserRepository();
//        UserService uService = new UserService();
//        UserController u = new UserController();
//        assertTrue(true);
//        throw new io.cucumber.java.PendingException();

    }

    @When("the user tries to create an account with {string}, {string}, {string}, {string}, {string}, {string}")
    public void the_user_tries_to_create_an_account_with(String string, String string2, String string3, String string4, String string5, String string6) {
        // Write code here that turns the phrase above into concrete actions
//        throw new io.cucumber.java.PendingException();
    }

    @Then("the account is created")
    public void the_account_is_created() throws UnsupportedEncodingException {
        // Write code here that turns the phrase above into concrete actions
        String path = "userprofile/testing?password=testPass&firstName=saba&lastName=fathi&birthday=2001-01-01&email=test@email.com, postDataBytes";
        StringBuilder postData = new StringBuilder();
//        Util.sendRequest("POST", "http://localhost:8080/userprofile/testing?password=testPass&firstName=saba&lastName=fathi&birthday=2001-01-01&email=test@email.com")
        byte[] postDataBytes = postData.toString().getBytes("UTF-8");
        Util.sendRequest("POST",
                "http://localhost:8080/",
                "userprofile/bunnies?password=testPass&firstName=saba&lastName=fathi&birthday=2001-01-01&email=test@email.com",
                postDataBytes);
        assertEquals(200, Util.getResponseCode("POST", "http://localhost:8080/", path, "UTF-8"));

//        String response= Util.sendRequest("POST",
//                "http://localhost:8080/",
//                "",
//                postDataBytes);
//        assertEquals("200", response);



////        assertNull(userService.getAllUsers());
//        userService.createUser("username", "password", "firstname", "lastname", new Timestamp(System.currentTimeMillis()), "email");
//        int newUserCount = userService.getAllUsers().size();


//        assertEquals(1, newUserCount);
    }

    @Then("the user can log in using the credentials {string} and {string}")
    public void the_user_can_log_in_using_the_credentials_and(String string, String string2) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Given("the following user exists in the system:")
    public void the_following_user_exists_in_the_system(io.cucumber.datatable.DataTable dataTable) {
        // Write code here that turns the phrase above into concrete actions
        // For automatic transformation, change DataTable to one of
        // E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
        // Map<K, List<V>>. E,K,V must be a String, Integer, Float,
        // Double, Byte, Short, Long, BigInteger or BigDecimal.
        //
        // For other transformations you can register a DataTableType.
        throw new io.cucumber.java.PendingException();
    }

    @When("the user tried to create an account with {string}, {string}, {string}, {string}, {string}, {string}")
    public void the_user_tried_to_create_an_account_with(String string, String string2, String string3, String string4, String string5, String string6) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Then("the error {string} should be displayed")
    public void the_error_should_be_displayed(String string) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
}
