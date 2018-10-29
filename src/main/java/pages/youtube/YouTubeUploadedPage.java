package pages.youtube;

import annotation.Element;
import annotation.PageTitle;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by Ramich on 08.04.2018.
 */

@Slf4j
@PageTitle("Finish video upload")
public class YouTubeUploadedPage extends YouTubeHeader {

    @FindBy(className = "upload-status-text")
    @Element
    @Getter
    private WebElement uploadStatus;

    @FindBy(css = "textarea.video-settings-description")
    @Element
    @Getter
    private WebElement descriptionInput;

    @FindBy(css = "input.video-settings-add-tag")
    @Element
    @Getter
    private WebElement tagsInput;

    @FindBy(css = "input.video-settings-title")
    @Element
    @Getter
    private WebElement titleInput;

    @FindBy(css = "button.save-changes-button")
    @Element
    @Getter
    private WebElement publishButton;

    @FindBy(css = "div.save-error-message")
    @Element
    @Getter
    private WebElement alertMessage;

    @FindBy(css = "#upload-item-0 button.return-to-editing-button span")
    @Element
    @Getter
    private WebElement returnToEditButton;

    @FindBy(css = "input.share-panel-url")
    @Element
    @Getter
    private WebElement shareLink;

}
