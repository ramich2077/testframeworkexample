package pages.youtube;

import lombok.Getter;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.annotation.ElementTitle;
import pages.annotation.PageTitle;

/**
 * Created by Ramich on 09.04.2018.
 */
@PageTitle("Studio")
public class YouTubeStudioPage extends YouTubeHeader {

    @FindBy(css = "a#menu-item-1")
    @ElementTitle("Менеджер видео")
    @Getter
    public WebElement videoManagerLink;

    public YouTubeVideoManagerPage openVideoManager() {
        waitForElement(videoManagerLink).click();
        return new YouTubeVideoManagerPage();
    }
}
 