@Regression @Invalid @InvalidYear
Feature: Invalid Car Year Selection

  Background:
    Given a user navigates to the car registration form


  Scenario: Submit without selecting year
    When user enters car registration number "IHK876"
    And user clicks the submit button
    Then user should see the error message "There was an error!"


  Scenario: Submit with both fields empty
    When user leaves the car registration field empty
    And user clicks the submit button
    Then user should see the error message "There was an error!"
    And the success message should not be visible