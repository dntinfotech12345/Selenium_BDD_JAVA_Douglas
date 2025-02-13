Feature: Verify the parfum page

  Scenario Outline: Verify the filter option in Highlights dropdown
    Given User navigates to the application
    When User click on "PARFUM" tab
    Then Verify user on the parfum page
    When I select the "Aktionen" dropdown
    Then I select the "<FilterOption>" filter option from the dropdown
    Then Verify the "<FilterOption>" filter is applied

    Examples:
      | FilterOption |
      | Sale         |
      | NEU          |