Feature: Youtube Test Task

  @Test_Youtube
  Scenario: Yotube Test
    Given I open "https://www.youtube.com/"
    And   I am on page "Main page"
    And   Press button "Войти"
    And   I am on page "Login page"
    And   Fill field "Логин" with text "test06042018"
    And   Press button "Логин - Далее"
    And   Fill field "Пароль" with text "sbt_test"
    And   Press button "Пароль - Далее"
    And   I am on page "Main page"
    And   Press button "Кнопка Добавить видео"
    And   Press button "Добавить видео"
    And   I am on page "Add video"
    And   I select file "TestVideo.avi" for upload to "Поле добавления видео"
    And   I am on page "Finish video upload"
    And   Wait element "Статус загрузки" contains text "Загрузка завершена!" with timeout 600
    And   Clear field "Название видео"
    And   Fill field "Название видео" with text "selenium_test_video_upload_06042018"
    And   Fill field "Теги" with text "selenium_test"
    And   Press element "Описание видео"
    And   Fill field "Теги" with text "selenium_test_video_upload_06042018"
    And   Press element "Описание видео"
    And   Fill field "Теги" with text "test_video"
    And   Press element "Описание видео"
    And   Fill field "Описание видео" with text "Selenium test task"
    And   Wait element "Сообщение о загрузке" contains text "Предварительная версия сохранена." with timeout 30
    And   Press button "Опубликовать"
    And   Wait element "Вернуться к редактированию" contains text "Вернуться к редактированию" with timeout 30
    Given I open "https://www.youtube.com/"
    And   I am on page "Main page"
    And   Fill field "Поле 'Поиск'" with text "selenium_test_video_upload_06042018"
    And   Press element "Поиск"
    And   I am on page "Search results"
    And   I have to wait for search results
    And   Wait element "Название видео" contains text "selenium_test_video_upload_06042018" with timeout 30