@Regression @Invalid @InvalidCarNumber
Feature: Invalid Car Number Registration

  Background:
    Given a user navigates to the car registration form


  Scenario Outline: Submit invalid car registration number formats
    When user enters car registration number "<registrationNumber>"
    And user selects year "<year>"
    And user clicks the submit button
    Then user should see the error message "There was an error!"
    And the success message should not be visible

    Examples:
      | registrationNumber | year |
      | abc1234            | 2017 |
      | AB1234             | 2017 |
      | ABC123             | 2017 |
      | ABC12345           | 2017 |
      | ABCD2345           | 2017 |
      | 1234ABC            | 2017 |
      | ABC 1234           | 2017 |
      | AbC1234            | 2017 |
      | Ab@1234            | 2017 |
      | ABCDEFG            | 2017 |
      | 2345678            | 2017 |
      | ΓΔΕ4567            | 2017 |
      | ÁBĆ1234            | 2017 |

  Scenario: Car registration number with leading spaces
    When user enters car registration number "  ZKE456"
    And user selects year "2017"
    And user clicks the submit button
    Then user should see the error message "There was an error!"
    And the success message should not be visible

  Scenario: Car registration number with trailing spaces
    When user enters car registration number "ZKE456   "
    And user selects year "2017"
    And user clicks the submit button
    Then user should see the error message "There was an error!"
    And the success message should not be visible

  Scenario Outline: Empty car registration number field
    When user leaves the car registration field empty
    And user selects year "<year>"
    And user clicks the submit button
    Then user should see the error message "There was an error!"
    And the success message should not be visible

    Examples:
      | year |
      | 2015 |
      | 2016 |
      | 2017 |

  Scenario: Submit valid then invalid car registration number data
    When user enters car registration number "ABC1234"
    And user selects year "2016"
    And user clicks the submit button
    And user should see the success message "Success! Selected ABC1234 with year 2016"
    And the error message should not be visible
    And user changes the car registration to "XXY12345"
    And user clicks the submit button
    Then user should see the error message "There was an error!"
    And the success message should not be visible