package pages.youtube;

import lombok.Getter;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.WebElement;
import pages.annotation.ElementTitle;
import pages.Page;

import java.util.List;

/**
 * Created by Ramich on 08.04.2018.
 */
public abstract class YouTubeHeader extends Page {

    @FindBy(css = "div#container")
    protected WebElement head;

    @FindBy(css = "a[title='Главная страница YouTube']")
    @ElementTitle("Главная страница YouTube")
    @Getter
    public WebElement logoLink;

    @FindBy(css = "input#search")
    @ElementTitle("Поле 'Поиск'")
    @Getter
    public WebElement searchInput;

    @FindBy(css = "button#search-icon-legacy")
    @ElementTitle("Поиск")
    @Getter
    public WebElement searchButton;

    @FindAll({
            @FindBy(css = "button#avatar-btn"),
            @FindBy(css = "button.yt-masthead-user-icon")
    })
    @ElementTitle("Аккаунт")
    @Getter
    public WebElement avatarButton;

    @FindBy(xpath = "//*[@id='button' and contains(./*/text(), 'Войти')]")
    @ElementTitle("Войти")
    @Getter
    public WebElement loginButton;

    @FindAll({
            @FindBy(css = "button[aria-label='Создать']"),
            @FindBy(css = "button[aria-label='Добавить видео']"),
            @FindBy(css = "button[aria-label='Создать видео или запись']"),
            @FindBy(id = "create-icon")
    })
    @ElementTitle("Кнопка Добавить видео")
    @Getter
    public WebElement addVideoButton;

    @FindBy(xpath = "//yt-formatted-string[text()='Добавить видео']")
    @ElementTitle("Добавить видео")
    @Getter
    public WebElement addVideo;

    @FindBy(xpath = "//yt-formatted-string[contains(text(), 'Творческая студия')]")
    @ElementTitle("Творческая студия")
    @Getter
    public WebElement studioLink;

    @FindBy(css = "a[href='https://www.youtube.com/logout']")
    @ElementTitle("Выйти")
    @Getter
    public WebElement logoutButton;

    public LoginPage login(){
        waitForElement(loginButton).click();
        return new LoginPage();
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
