Feature: Youtube Test Task

  @Test_Youtube
  Scenario: Yotube Test
    Given I open "https://www.youtube.com/"
    And   I am on page "Main page"
    And   Press button "login button"
    And   I am on page "Login page"
    And   Fill field "identifier input" with text "test06042018"
    And   Press button "identifier next button"
    And   Fill field "password input" with text "sbt_test"
    And   Press button "password next button"
    And   I am on page "Main page"
    And   Press button "add video button"
    And   Press button "add video"
    And   I am on page "Add video"
    And   I select file "TestVideo.avi" for upload to "upload input"
    And   I am on page "Finish video upload"
    And   Wait element "upload status" contains text "Загрузка завершена!" with timeout 90
    And   Clear field "title input"
    And   Fill field "title input" with text "selenium_test_video_upload_06042018"
    And   Fill field "tags input" with text "selenium_test"
    And   Press element "description input"
    And   Fill field "tags input" with text "selenium_test_video_upload_06042018"
    And   Press element "description input"
    And   Fill field "tags input" with text "test_video"
    And   Press element "description input"
    And   Fill field "description input" with text "Selenium test task"
    And   Wait element "alert message" contains text "Предварительная версия сохранена." with timeout 30
    And   Press button "publish button"
    And   Wait element "return to edit button" contains text "Вернуться к редактированию" with timeout 30
    Given I open "https://www.youtube.com/"
    And   I am on page "Main page"
    And   Fill field "search input" with text "selenium_test_video_upload_06042018"
    And   Press element "search button"
    And   I am on page "Search results"
    And   I have to wait for search results
    And   Wait element "video title" contains text "selenium_test_video_upload_06042018" with timeout 30