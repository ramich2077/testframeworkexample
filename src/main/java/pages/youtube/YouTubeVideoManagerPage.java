package pages.youtube;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import util.DriverLoader;

import java.util.List;

/**
 * Created by Ramich on 09.04.2018.
 */
public class YouTubeVideoManagerPage extends YouTubeHeader {

    @FindBy(css = "span#creator-subheader-item-count")
    WebElement videoCounter;

    public void deleteAllVideos() {
        if(waitForElement(videoCounter).getText().equals("0")) return;

        List<WebElement> videos = DriverLoader.getDriver().findElements(By.cssSelector("#vm-playlist-video-list-ol li.ng-binding"));
        for (WebElement video: videos) {
            waitForElement(waitForElement(video).findElement(By.cssSelector("button.edit-expand-menu-button"))).click();
            waitForElement(By.cssSelector("li span.vm-video-edit-menu-delete")).click();
            waitForElement(By.cssSelector("button.vm-video-actions-delete-button-confirm")).click();
        }
    }

}
