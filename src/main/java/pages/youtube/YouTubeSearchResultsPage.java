package pages.youtube;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by Ramich on 08.04.2018.
 */
public class YouTubeSearchResultsPage extends YouTubeHeader {

    @FindBy(css = "div#container.ytd-search")
    private WebElement searchResultsContainer;

    private By searchResultsEntrySelector = By.cssSelector("div#dismissable.ytd-video-renderer");

    public boolean checkSearchResultsContainVideoWithTitle(String title){

        refreshSearchResultIfEmpty(600, 3);

        return waitForElement(searchResultsContainer)
                .findElements(searchResultsEntrySelector)
                .stream()
                .map(element -> element.findElement(By.cssSelector("a#video-title")).getText())
                .anyMatch(title::equals);
    }

    private void refreshSearchResultIfEmpty(int timeout, int pollingInterval) {
        int counter = timeout;
        while (waitForElement(searchResultsContainer)
                .findElements(searchResultsEntrySelector).isEmpty() && counter>=0) {
            try {
                Thread.sleep(pollingInterval * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            waitForElement(searchButton).click();
            counter -= pollingInterval;
        }
    }
}
