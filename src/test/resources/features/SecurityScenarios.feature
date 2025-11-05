@Regression @Security
Feature: Car Registration Form - Security Testing


  Background:
    Given a user navigates to the car registration form


  Scenario: SQL injection attempt in registration field
    When user enters car registration number "ABC'; DROP TABLE car;"
    And user selects year "2017"
    And user clicks the submit button
    And user should see the error message "There was an error!"
    And the success message should not be visible


  Scenario: XSS attempt in registration field
    When user enters car registration number "<script>alert('XSS')</script>"
    And user selects year "2017"
    And user clicks the submit button
    And user should see the error message "There was an error!"
    And the success message should not be visible