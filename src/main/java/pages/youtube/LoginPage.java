package pages.youtube;

import annotation.Element;
import annotation.PageTitle;
import lombok.Getter;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.Page;

/**
 * Created by Ramich on 07.04.2018.
 */
@PageTitle("Login page")
public class LoginPage extends Page {

    @Element
    @FindBy(xpath = "//input[@id='identifierId']")
    @Getter
    private WebElement identifierInput;

    @Element
    @FindBy(xpath = "//div[@id='identifierNext']")
    @Getter
    private WebElement identifierNextButton;

    @Element
    @FindBy(xpath = "//input[@name='password']")
    @Getter
    private WebElement passwordInput;

    @Element
    @FindBy(xpath = "//div[@id='passwordNext']")
    @Getter
    private WebElement passwordNextButton;

}
