package pages.youtube;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import pages.Page;
import util.DriverLoader;

import java.util.List;

/**
 * Created by Ramich on 08.04.2018.
 */
public abstract class YouTubeHeader extends Page {

    @FindBy(css = "div#container")
    protected WebElement head;

    @FindBy(css = "a[title='Главная страница YouTube']")
    protected WebElement logoLink;

    @FindBy(css = "input#search")
    protected WebElement searchInput;

    @FindBy(css = "button#search-icon-legacy")
    protected WebElement searchButton;

    @FindAll({
            @FindBy(css = "button#avatar-btn"),
            @FindBy(css = "button.yt-masthead-user-icon")
    })
    private WebElement avatarButton;

    @FindBy(xpath = "//*[@id='button' and contains(./*/text(), 'Войти')]")
    private WebElement loginButton;

    @FindAll({
            @FindBy(css = "button[aria-label='Создать']"),
            @FindBy(css = "button[aria-label='Добавить видео']")
    })
    private WebElement addVideoButton;

    @FindBy(xpath = "//yt-formatted-string[contains(text(), 'Творческая студия')]")
    private WebElement studioLink;

    //For Chrome only, I love you google.
    @FindAll({
            @FindBy(css = "button[aria-label='Создать видео или запись']"),
            @FindBy(id = "create-icon")
    })
    private WebElement addVideoChrome;

    @FindBy(css = "a[href='https://www.youtube.com/logout']")
    private WebElement logoutButton;

    public LoginPage login(){
        waitForElement(loginButton).click();
        return new LoginPage();
    }

    public YouTubeVideoUploadPage uploadVideo(){
        if(DriverLoader.getDriver() instanceof ChromeDriver) {
            waitForElement(addVideoChrome).click();
        } else {
            waitForElement(addVideoButton).click();
        }
        waitForElement(By.xpath("//yt-formatted-string[text()='Добавить видео']")).click();
        return new YouTubeVideoUploadPage();
    }

    public YouTubeMainPage openMainPage(){
        waitForElement(logoLink).click();
        return new YouTubeMainPage();
    }

    public YouTubeSearchResultsPage search(String keyword){
        waitForElement(searchInput).sendKeys(keyword);
        waitForElement(searchButton).click();
        return new YouTubeSearchResultsPage();
    }

    public YouTubeStudioPage goToStudioPage() {
        waitForElement(avatarButton).click();
        waitForElement(studioLink).click();
        return new YouTubeStudioPage();
    }

    public YouTubeMainPage logout() {
        waitForElement(avatarButton).click();
        waitForElement(logoutButton).click();
        return new YouTubeMainPage();
    }

    public void search(String keyword, String description, List<String> tags){
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(keyword).append(" ");
        stringBuilder.append("description:").append(description);
        for (String tag: tags) {
            stringBuilder.append("tag:").append(tag).append(" ");
        }

        waitForElement(searchInput).sendKeys(stringBuilder.toString());
        waitForElement(searchButton).click();
    }
}
