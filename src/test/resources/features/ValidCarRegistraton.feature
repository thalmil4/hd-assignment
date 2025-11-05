@Regression @Valid
Feature: Successful Car Registration

  Background:
    Given a user navigates to the car registration form

  Scenario Outline: Submit valid car registration for every year
    When user enters car registration number "<registrationNumber>"
    And user selects year "<year>"
    And user clicks the submit button
    Then user should see the success message "Success! Selected <registrationNumber> with year <year>"
    And the error message should not be visible

    Examples:
      | registrationNumber |  | year |
      | ABC1234            |  | 2015 |
      | AAA0000            |  | 2016 |
      | ZZZ9999            |  | 2017 |


  Scenario: Multiple consecutive valid submissions
    When user enters car registration number "ABC1234"
    And user selects year "2016"
    And user clicks the submit button
    And user should see the success message "Success! Selected ABC1234 with year 2016"
    And user changes the car registration to "XYZ5678"
    And user changes the year to "2017"
    And user clicks the submit button
    Then user should see the success message "Success! Selected XYZ5678 with year 2017"


  Scenario: Submit invalid then valid data
    When user enters car registration number "XXY12345"
    And user selects year "2016"
    And user clicks the submit button
    Then user should see the error message "There was an error!"
    And the success message should not be visible
    When user changes the car registration to "ABC1234"
    And user clicks the submit button
    Then user should see the success message "Success! Selected ABC1234 with year 2016"
    And the error message should not be visible


  Scenario: Submit form using enter
    When user enters car registration number "ABC1234"
    And user selects year "2016"
    And users presses Enter key
    Then user should see the success message "Success! Selected ABC1234 with year 2016"
    And the error message should not be visible

