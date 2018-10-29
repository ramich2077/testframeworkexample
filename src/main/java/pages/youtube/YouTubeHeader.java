package pages.youtube;

import annotation.Element;
import lombok.Getter;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import pages.Page;

/**
 * Created by Ramich on 08.04.2018.
 */
public abstract class YouTubeHeader extends Page {

    @FindBy(css = "input#search")
    @Element
    @Getter
    protected WebElement searchInput;

    @FindBy(css = "button#search-icon-legacy")
    @Element
    @Getter
    protected WebElement searchButton;

    @FindAll({
            @FindBy(css = "button#avatar-btn"),
            @FindBy(css = "button.yt-masthead-user-icon")
    })
    @Element
    @Getter
    protected WebElement avatarButton;

    @FindBy(xpath = "//*[@id='button' and contains(./*/text(), 'Войти')]")
    @Element
    @Getter
    protected WebElement loginButton;

    @FindAll({
            @FindBy(css = "button[aria-label='Создать']"),
            @FindBy(css = "button[aria-label='Добавить видео']"),
            @FindBy(css = "button[aria-label='Создать видео или запись']"),
            @FindBy(id = "create-icon")
    })
    @Element
    @Getter
    protected WebElement addVideoButton;

    @FindBy(xpath = "//yt-formatted-string[text()='Добавить видео']")
    @Element
    @Getter
    protected WebElement addVideo;

    @FindBy(xpath = "//yt-formatted-string[contains(text(), 'Творческая студия')]")
    @Element
    @Getter
    protected WebElement studioLink;

    @FindBy(css = "a[href='https://www.youtube.com/logout']")
    @Element
    @Getter
    protected WebElement logoutButton;

}
