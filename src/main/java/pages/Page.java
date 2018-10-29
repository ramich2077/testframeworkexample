package pages;

import exception.AutotestError;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.Annotations;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import util.DriverManager;
import util.Utils;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.Collections;
import java.util.List;

/**
 * Created by Ramich on 07.04.2018.
 */
@Slf4j
public abstract class Page {

    private WebDriverWait wait;

    public Page() {
        this.init();
    }

    private void init() {
        try {
            PageFactory.initElements(DriverManager.getDriver(), this);
            wait = DriverManager.getWait(DriverManager.getTimeout());
        } catch (NoSuchElementException e) {
            log.error("The element wasn't found: {}", e.getMessage());
            throw e;
        }
    }

    private By getBy(String title) throws AutotestError {
        return new Annotations(Utils.getElementByTitle(this.getClass(), title)).buildBy();
    }

    private WebElement getElementByTitle(String title) throws AutotestError {
        try {
        return (WebElement) this.getClass().getMethod(
                "get" + StringUtils.capitalize(Utils.getElementByTitle(this.getClass(), title).getName()))
                .invoke(this);
        } catch (IllegalAccessException|InvocationTargetException|NoSuchMethodException e) {
            throw new AutotestError(String.format("Getter method isn't found for element %s", title), e);
        }
    }

    public void doMethod(@NonNull String name) throws AutotestError {
        try {
            this.getClass().getMethod(Utils.castToCamelCase(name)).invoke(this);
        } catch (IllegalAccessException|InvocationTargetException|NoSuchMethodException e) {
            throw new AutotestError(String.format("Method %s isn't found", name), e);
        }
    }

    public void fillField(String title, String text) throws AutotestError {
        waitForElement(getElementByTitle(title)).sendKeys(text);
    }

    public void click(String title) throws AutotestError {
        waitForElement(getElementByTitle(title)).click();
    }

    public void clearText(String title) throws AutotestError {
        waitForElement(getElementByTitle(title)).clear();
    }

    public void uploadFile(String title, String filename) throws AutotestError {

        File file = new File(filename);

        WebElement input = getElementByTitle(title);
        waitForElement(getBy(title));
        setAttribute(input, "visibility", "visible");
        setAttribute(input, "display", "block");
        input.sendKeys(file.getAbsolutePath());
    }

    public void waitForElementClickable(String title, int timeout) throws AutotestError {
        DriverManager.getWait(timeout).until(ExpectedConditions.elementToBeClickable(getElementByTitle(title)));
    }

    public void waitForElementPresence(String title, int timeout) throws AutotestError {
        DriverManager.getWait(timeout).until(ExpectedConditions.presenceOfElementLocated(getBy(title)));
    }

    public void waitTextToBePresentInElement(String title, String text, int timeout) throws AutotestError {
        DriverManager.getWait(timeout).until(ExpectedConditions
                .textToBePresentInElement(getElementByTitle(title), text));
    }

    protected WebElement waitForElement(WebElement element) {
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    protected WebElement waitForElement(By locatedBy) {
        return wait.until(ExpectedConditions.presenceOfElementLocated(locatedBy));
    }

    protected List<WebElement> waitForElementsList(List<WebElement> element) {
        try{
            return wait.until(ExpectedConditions.visibilityOfAllElements(element));
        } catch (WebDriverException e) {
            return Collections.emptyList();
        }
    }

    private void setAttribute(WebElement element, String attribute, String value) {
        JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
        js.executeScript("arguments[0].setAttribute('" + attribute + "', '" + value + "')", element);
    }
}
