package pages.youtube;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import util.DriverLoader;

import java.util.List;

/**
 * Created by Ramich on 09.04.2018.
 */
public class YouTubeVideoManagerPage extends YouTubeHeader {

    @FindBy(css = "div#row-container")
    List<WebElement> videos;

    public void deleteAllVideos() {
        if(waitForElementsList(videos).isEmpty()) return;

        for (WebElement video: videos) {
            new Actions(DriverLoader.getDriver())
                    .moveToElement(video.findElement(By.cssSelector("ytcp-icon-button.open-menu-button")))
                    .click().perform();
            waitForElement(By.cssSelector("paper-item[test-id='delete']")).click();
            waitForElement(By.cssSelector("ytcp-form-checkbox#delete-confirm-checkbox div#checkboxContainer")).click();
            waitForElement(By.cssSelector("ytcp-button#delete-confirm-button")).click();
        }
    }

}
