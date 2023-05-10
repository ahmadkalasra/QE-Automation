Feature: Contact Page

  @VerifyErrorMessages
  Scenario: Verify errors are displayed and also removed when populated mandatory fields
    Given The user navigates to Jupitor Toys application
    When The user navigates to contact page
    And The user click submit button
    Then The user verifies error messages are displayed

  @VerifyErrorsRemoved
  Scenario: Verify errors are gone when populated mandatory fields
    Given The user navigates to Jupitor Toys application
    When The user navigates to contact page
    And The user click submit button
    And The user populates mandatory fields
    Then The user validates errors messages are gone

  @Test1
  Scenario: Verify errors are displayed and also removed when populated mandatory fields
    Given The user navigates to Jupitor Toys application
    When The user navigates to contact page
    And The user click submit button
    Then The user verifies error messages are displayed
    When The user populates mandatory fields
    Then The user validates errors messages are gone

  @Test2
  Scenario: Validate Successful submission message when submit contact form
    Given The user navigates to Jupitor Toys application
    When The user navigates to contact page
    And The user populates mandatory fields
    And The user click submit button
    Then The user validates successful submission message