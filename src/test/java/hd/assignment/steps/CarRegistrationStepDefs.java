package hd.assignment.steps;

import hd.assignment.pages.CarRegistrationPage;

import hd.assignment.utils.TestContext;
import hd.assignment.utils.TestHelpers;
import io.cucumber.java.en.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;


public class CarRegistrationStepDefs {

    private final CarRegistrationPage carRegistrationPage;

    public CarRegistrationStepDefs(TestContext testContext) {
        this.carRegistrationPage = new CarRegistrationPage(testContext.getPage());
    }

    @Given("a user navigates to the car registration form")
    public void navigateToCarRegistrationPage() {
        carRegistrationPage.navigateToCarRegistrationPage();
    }

    @When("user enters car registration number {string}")
    @When("user changes the car registration to {string}")
    public void userEntersCarRegistrationNumberRegistrationNumber(String registrationNumber) {
        carRegistrationPage.clearCarRegistrationNumberField();
        carRegistrationPage.fillCarRegistrationNumber(registrationNumber);
    }

    @When("user selects year {string}")
    @And("user changes the year to {string}")
    public void userSelectsYearYear(String year) {
        carRegistrationPage.selectSpecificYearFromDropdown(year);
    }

    @When("user clicks the submit button")
    public void userClicksTheSubmitButton() {
        carRegistrationPage.clickSubmitButton();
    }

    @Then("user should see the success message {string}")
    public void userShouldSeeTheSuccessMessage(String successMessage) {
        Assert.assertEquals(carRegistrationPage.getSuccessMessage(),successMessage);
    }

    @Then("the error message should not be visible")
    public void errorMessageShouldNotBeVisible() {
        Assert.assertFalse(carRegistrationPage.isErrorMessageVisible());
    }

    @Then("user should see the error message {string}")
    public void errorMessageShouldBeVisible(String errorMessage) {
         Assert.assertEquals(carRegistrationPage.getErrorMessage(),errorMessage);
    }

    @Then("the success message should not be visible")
    public void successMessageShouldNotBeVisible() {
        Assert.assertFalse(carRegistrationPage.isSuccessMessageVisible());
    }

    @When("user leaves the car registration field empty")
    public void userLeavesTheCarRegistrationFieldEmpty() {
        carRegistrationPage.clearCarRegistrationNumberField();
    }

    @When("users presses Enter key")
    public void submitsFormPressingEnter() {
        carRegistrationPage.submitFormWithEnterKey();
    }
}
