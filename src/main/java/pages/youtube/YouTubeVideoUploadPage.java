package pages.youtube;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.annotation.ElementTitle;
import pages.annotation.PageTitle;

/**
 * Created by Ramich on 07.04.2018.
 */

@Slf4j
@PageTitle("Add video")
public class YouTubeVideoUploadPage extends YouTubeHeader {

    @FindBy(css = "div#upload-prompt-box div button")
    @ElementTitle("Кнопка Добавить видео")
    @Getter
    public WebElement uploadButton;

    @FindBy(css = "div#upload-prompt-box input:last-of-type")
    @ElementTitle("Поле добавления видео")
    @Getter
    public WebElement uploadInput;

}
