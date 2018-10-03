package pages.youtube;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by Ramich on 09.04.2018.
 */
public class YouTubeStudioPage extends YouTubeHeader {

    @FindBy(css = "a#menu-item-1")
    private WebElement videoManagerLink;

    public YouTubeVideoManagerPage openVideoManager() {
        waitForElement(videoManagerLink).click();
        return new YouTubeVideoManagerPage();
    }
}
