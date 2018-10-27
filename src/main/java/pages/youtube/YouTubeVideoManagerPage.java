package pages.youtube;

import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import pages.annotation.ElementTitle;
import pages.annotation.PageTitle;
import util.DriverManager;

import java.util.List;

/**
 * Created by Ramich on 09.04.2018.
 */
@PageTitle("Video manager")
public class YouTubeVideoManagerPage extends YouTubeHeader {

    @FindBy(css = "div#row-container")
    @ElementTitle("Список видео")
    @Getter
    List<WebElement> videos;

    public void deleteAllVideos() {
        if(waitForElementsList(videos).isEmpty()) return;

        videos.forEach(video -> {
            new Actions(DriverManager.getDriver())
                    .moveToElement(DriverManager.getDriver().findElement(By.cssSelector("ytcp-icon-button.open-menu-button")))
                    .click().perform();
            waitForElement(By.cssSelector("paper-item[test-id='delete']")).click();
            waitForElement(By.cssSelector("div.ytcp-video-delete-dialog div#checkboxContainer")).click();
            waitForElement(By.cssSelector("ytcp-button#delete-confirm-button")).click();
        });
    }

}
