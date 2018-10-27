package pages.youtube;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.annotation.ElementTitle;
import pages.Page;
import pages.annotation.PageTitle;
import lombok.Getter;

/**
 * Created by Ramich on 07.04.2018.
 */
@PageTitle("Login page")
public class LoginPage extends Page {

    @ElementTitle("Логин")
    @FindBy(xpath = "//input[@id='identifierId']")
    @Getter
    public WebElement identifierInput;

    @ElementTitle("Логин - Далее")
    @FindBy(xpath = "//div[@id='identifierNext']")
    @Getter
    public WebElement identifierNextButton;

    @ElementTitle("Пароль")
    @FindBy(xpath = "//input[@name='password']")
    @Getter
    public WebElement passwordInput;

    @ElementTitle("Пароль - Далее")
    @FindBy(xpath = "//div[@id='passwordNext']")
    @Getter
    public WebElement passwordNextButton;

}
