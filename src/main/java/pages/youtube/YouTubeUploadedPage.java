package pages.youtube;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.annotation.ElementTitle;
import pages.annotation.PageTitle;

/**
 * Created by Ramich on 08.04.2018.
 */

@Slf4j
@PageTitle("Finish video upload")
public class YouTubeUploadedPage extends YouTubeHeader {

    @FindBy(className = "upload-status-text")
    @ElementTitle("Статус загрузки")
    @Getter
    public WebElement uploadStatus;

    @FindBy(css = "textarea.video-settings-description")
    @ElementTitle("Описание видео")
    @Getter
    public WebElement descriptionInput;

    @FindBy(css = "input.video-settings-add-tag")
    @ElementTitle("Теги")
    @Getter
    public WebElement tagsInput;

    @FindBy(css = "input.video-settings-title")
    @ElementTitle("Название видео")
    @Getter
    public WebElement titleInput;

    @FindBy(css = "button.save-changes-button")
    @ElementTitle("Опубликовать")
    @Getter
    public WebElement publishButton;

    @FindBy(css = "div.save-error-message")
    @ElementTitle("Сообщение о загрузке")
    @Getter
    public WebElement alertMessage;

    @FindBy(css = "#upload-item-0 button.return-to-editing-button span")
    @ElementTitle("Вернуться к редактированию")
    @Getter
    public WebElement returnToEditButton;

    @FindBy(css = "input.share-panel-url")
    @Getter
    public WebElement shareLink;

}
