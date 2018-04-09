Feature: Youtube Test Task

  @Test_Youtube
  Scenario: Yotube Test
    Given I open Yotube main page
    And   I am logging in with email "test06042018" and password "sbt_test"
    And   I have to delete all uploaded videos
    And   logof from Youtube
    When I open Yotube main page
    And  I am logging in with email "test06042018" and password "sbt_test"
    And  I press 'Add video' button
    And  I select file "TestVideo.avi" for upload
    And  I wait until upload will be finished
    And  I set title "selenium_test_video_upload_06042018"
    And  I add description "Selenium test for Youtube"
    And  I add tags selenium_test, test_video, selenium_test_video_upload_06042018
    And  I publish a video
    And  I open the main page
    And  I search for video with previosly used description and tags
    Then search results contain my video