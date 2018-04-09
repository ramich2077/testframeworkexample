package pages.youtube;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by Ramich on 09.04.2018.
 */
public class YouTubeStudioPage extends YouTubeHeader {

    @FindBy(css = "li#creator-sidebar-section-id-video-manager a")
    private WebElement videoManagerLink;

    public YouTubeVideoManagerPage openVideoManager() {
        waitForElement(videoManagerLink).click();
        return new YouTubeVideoManagerPage();
    }
}
