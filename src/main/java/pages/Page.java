package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.DriverLoader;

import java.util.Collections;
import java.util.List;

/**
 * Created by Ramich on 07.04.2018.
 */
public abstract class Page {

    protected static final Logger log = LoggerFactory.getLogger(Page.class);
    private String title;
    private WebDriverWait wait;

    public Page() {
        this.init();
    }

    public void init() {
        try {
            PageFactory.initElements(DriverLoader.getDriver(), this);
            wait = new WebDriverWait(DriverLoader.getDriver(), DriverLoader.getTimeout());
            title = DriverLoader.getDriver().getTitle();
        } catch (NoSuchElementException e) {
            log.error("The element wasn't found: {}", e.getMessage());
            throw e;
        }
    }

    protected WebElement waitForElement(WebElement element) {
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    protected List<WebElement> waitForElementsList(List<WebElement> element) {
        try{
            return wait.until(ExpectedConditions.visibilityOfAllElements(element));
        } catch (WebDriverException e) {
            return Collections.emptyList();
        }
    }

    protected WebElement waitForElement(By locatedBy) {
        return wait.until(ExpectedConditions.presenceOfElementLocated(locatedBy));
    }

    protected void waitForText(WebElement element, String text, int timeout) {
        WebDriverWait waitForText = new WebDriverWait(DriverLoader.getDriver(), timeout);
        waitForText.until(ExpectedConditions.textToBePresentInElement(element, text));
    }

    protected void setAttribute(WebElement element, String attribute, String value) {
        JavascriptExecutor js = (JavascriptExecutor) DriverLoader.getDriver();
        js.executeScript("arguments[0].setAttribute('" + attribute + "', '" + value + "')", element);
    }

    protected WebDriverWait getWait(int timeout) {
        return new WebDriverWait(DriverLoader.getDriver(), timeout);
    }

    public String getTitle() {
        return title;
    }
}
