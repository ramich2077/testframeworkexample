package pages.youtube;

import annotation.Element;
import annotation.PageTitle;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by Ramich on 07.04.2018.
 */

@Slf4j
@PageTitle("Add video")
public class YouTubeVideoUploadPage extends YouTubeHeader {

    @FindBy(css = "div#upload-prompt-box input:last-of-type")
    @Element
    @Getter
    private WebElement uploadInput;

}
