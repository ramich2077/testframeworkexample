package pages.youtube;

import annotation.Element;
import annotation.PageTitle;
import exception.AutotestError;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import util.Utils;

/**
 * Created by Ramich on 08.04.2018.
 */

@PageTitle("Search results")
public class YouTubeSearchResultsPage extends YouTubeHeader {

    @FindBy(css = "div#container.ytd-search")
    private WebElement searchResultsContainer;

    @FindBy(css = "div#container.ytd-search a#video-title")
    @Element
    @Getter
    private WebElement videoTitle;

    public void waitForSearchResults() throws AutotestError {
        Utils.waitForCondition(() -> {
                        waitForElement(searchButton).click();
                        return !(waitForElement(searchResultsContainer)
                                .findElements(By.cssSelector("div#dismissable.ytd-video-renderer"))
                                .isEmpty());
                },600, 3);
    }
}
