package pages.youtube;

import annotation.Element;
import annotation.PageTitle;
import lombok.Getter;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by Ramich on 09.04.2018.
 */
@PageTitle("Studio")
public class YouTubeStudioPage extends YouTubeHeader {

    @FindBy(css = "a#menu-item-1")
    @Element
    @Getter
    private WebElement videoManagerLink;
}
 