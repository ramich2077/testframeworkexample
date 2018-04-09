package pages.youtube;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.Page;

/**
 * Created by Ramich on 07.04.2018.
 */
public class LoginPage extends Page {

    @FindBy(xpath = "//input[@id='identifierId']")
    private WebElement identifierInput;

    @FindBy(xpath = "//div[@id='identifierNext']")
    private WebElement identifierNextButton;

    @FindBy(xpath = "//input[@name='password']")
    private WebElement passwordInput;

    @FindBy(xpath = "//div[@id='passwordNext']")
    private WebElement passwordNextButton;

    public YouTubeMainPage login(String login, String password) {
        waitForElement(identifierInput).sendKeys(login);
        waitForElement(identifierNextButton).click();
        waitForElement(passwordInput).sendKeys(password);
        waitForElement(passwordNextButton).click();
        return new YouTubeMainPage();
    }
}
