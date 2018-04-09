package pages.youtube;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import util.DriverLoader;

import java.util.List;

/**
 * Created by Ramich on 08.04.2018.
 */
public class YouTubeUploadedPage extends YouTubeHeader {

    @FindBy(className = "upload-status-text")
    private WebElement uploadStatus;

    @FindBy(css = "textarea.video-settings-description")
    private WebElement descriptionInput;

    @FindBy(css = "input.video-settings-add-tag")
    private WebElement tagsInput;

    @FindBy(css = "input.video-settings-title")
    private WebElement titleInput;

    @FindBy(css = "button.save-changes-button")
    private WebElement publishButton;

    @FindBy(css = "div.save-error-message")
    private WebElement alertMessage;

    @FindBy(css = "input.share-panel-url")
    private WebElement shareLink;

    public void waitForUpload() {
        waitForText(uploadStatus, "Загрузка завершена!", 600);
    }

    public void setTitle(String title) {
        waitForElement(titleInput).clear();
        waitForElement(titleInput).sendKeys(title);
    }

    public void addDescription(String description) {
        waitForElement(descriptionInput).sendKeys(description);
    }

    public void addTags(List<String> tags) {
        for(String tag: tags) {
            waitForElement(tagsInput).sendKeys(tag);
            waitForElement(descriptionInput).click();
        }
        waitForElement(descriptionInput).sendKeys(Keys.ESCAPE);
    }

    public void publish(){
        WebDriverWait wait = new WebDriverWait(DriverLoader.getDriver(), 30);
        wait.until(ExpectedConditions.textToBePresentInElement(alertMessage, "Предварительная версия сохранена."));
        waitForElement(publishButton).click();
        waitForElement(shareLink);
        log.debug("Uploaded video link: {}", shareLink.getAttribute("value"));
    }
}
