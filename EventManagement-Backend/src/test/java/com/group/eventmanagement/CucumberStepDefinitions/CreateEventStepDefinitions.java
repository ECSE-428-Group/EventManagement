package com.group.eventmanagement.CucumberStepDefinitions;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class CreateEventStepDefinitions {

    @When("the user tries to create an event with {string}, {string}, {string}, {string}, {string}, and {string}")
    public void the_user_tries_to_create_an_event_with_and(String string, String string2, String string3, String string4, String string5, String string6) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Then("the account with username {string} shall be the organizer of the event with {string}, {string}, {string}, {string}, {string}, and {string}")
    public void the_account_with_username_shall_be_the_organizer_of_the_event_with_and(String string, String string2, String string3, String string4, String string5, String string6, String string7) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
}
