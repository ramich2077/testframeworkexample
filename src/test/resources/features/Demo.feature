Feature: simple demo run

  @Test1
  Scenario: Unsuccessful run
    #I have an issue with access to google main page:)
    When I open "https://google.com/"
    Then I am on the "Google" page

  @Test2
  Scenario: Successful run
    When I open "https://yandex.ru/"
    And I am on the "Яндекс" page
    And I search for "stackoverflow"
    Then 1 result contains text "Stack Overflow - Where Developers Learn, Share, & Build..."