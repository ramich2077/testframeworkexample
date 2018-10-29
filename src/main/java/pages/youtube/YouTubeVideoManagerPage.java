package pages.youtube;

import annotation.PageTitle;
import exception.AutotestError;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import util.DriverManager;
import util.Utils;

import java.util.List;

/**
 * Created by Ramich on 09.04.2018.
 */
@PageTitle("Video manager")
public class YouTubeVideoManagerPage extends YouTubeHeader {

    @FindBy(css = "div#row-container")
    private List<WebElement> videos;

    public void deleteAllVideos() throws AutotestError {
        Utils.waitForCondition(() -> {
                if(!waitForElementsList(videos).isEmpty()) {
                    new Actions(DriverManager.getDriver())
                            .moveToElement(DriverManager.getDriver().findElement(By.cssSelector("ytcp-icon-button.open-menu-button")))
                            .click().perform();
                    waitForElement(By.cssSelector("paper-item[test-id='delete']")).click();
                    waitForElement(By.cssSelector("div.ytcp-video-delete-dialog div#checkboxContainer")).click();
                    waitForElement(By.cssSelector("ytcp-button#delete-confirm-button")).click();
                    return false;
                } else {
                    return true;
                }
            }, 300, 1);
    }

}
