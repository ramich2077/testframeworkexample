package pages.youtube;

import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.annotation.ElementTitle;
import pages.annotation.PageTitle;
import pages.exception.AutotestError;
import util.Utils;

/**
 * Created by Ramich on 08.04.2018.
 */

@PageTitle("Search results")
public class YouTubeSearchResultsPage extends YouTubeHeader {

    @FindBy(css = "div#container.ytd-search")
    private WebElement searchResultsContainer;

    @FindBy(css = "div#container.ytd-search a#video-title")
    @ElementTitle("Название видео")
    @Getter
    public WebElement videoTitle;

    private By searchResultsEntrySelector = By.cssSelector("div#dismissable.ytd-video-renderer");

    public void waitForSearchResults() throws AutotestError {
        Utils.waitForCondition(() -> {
                        waitForElement(searchButton).click();
                        return !waitForElement(searchResultsContainer).findElements(searchResultsEntrySelector).isEmpty();
                },600, 3);
    }
}
