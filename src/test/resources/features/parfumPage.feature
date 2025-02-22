Feature: Verify the Parfum Product Based on Filter

  Background:
    Given I navigate to the website
    And I accept the cookie consent

  Scenario Outline: Verify the "<FilterOption>" filter option in Aktionen dropdown
    When I click on "<Tab>" tab
    Then I verify that I was landed on the parfum page
    When I select the "<Dropdown>" dropdown
    Then I select the "<FilterOption>" filter option from the dropdown
    Then I verify the filter option from dropdown

    Examples:
      | FilterOption | Tab    | Dropdown |
      | Sale         | PARFUM | Aktionen |
      | NEU          | PARFUM | Aktionen |
      | Limitiert    | PARFUM | Aktionen |

  Scenario: Verify the title on parfum page
    When I click on "PARFUM" tab
    Then I verify that I was landed on parfum page