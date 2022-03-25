package com.group.eventmanagement.CucumberStepDefinitions;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class CreatePostStepDefinitions {

    @When("the user tries to create an post on the event with {string}, with {string}, {string}")
    public void the_user_tries_to_create_an_post_on_the_event_with_with(String string, String string2, String string3) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Then("the event with eventId {string} shall have a post with {string}, {string}")
    public void the_event_with_event_id_shall_have_a_post_with(String string, String string2, String string3) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
}
