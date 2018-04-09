package pages.youtube;

import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.FindBy;
import util.DriverLoader;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;

/**
 * Created by Ramich on 07.04.2018.
 */
public class YouTubeVideoUploadPage extends YouTubeHeader {

    @FindBy(css = "div#upload-prompt-box div button")
    private WebElement uploadButton;

    @FindBy(css = "div#upload-prompt-box input:last-of-type")
    private WebElement uploadInput;

    public YouTubeUploadedPage uploadVideo(String filename) {
        File file = null;

        try {
             file = new File(filename);
        } catch (NullPointerException e) {
            log.error("File not found: {}", filename, e);
        }

        //FIXME: WA
        if (DriverLoader.getDriver() instanceof FirefoxDriver) {
            try {
                waitForElement(uploadButton).click();
            } catch (ElementClickInterceptedException e) {
                setAttribute(uploadInput, "visibility", "visible");
                setAttribute(uploadInput, "display", "block");
                uploadInput.sendKeys(file.getAbsolutePath());
            }
            try {
                Robot robot = new Robot();
                waitForElement(uploadButton).click();
                Thread.sleep(3000);
                StringSelection stringSelection = new StringSelection(file.getAbsolutePath());
                Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                clipboard.setContents(stringSelection, stringSelection);
                robot.keyPress(KeyEvent.VK_CONTROL);
                robot.keyPress(KeyEvent.VK_V);
                robot.keyRelease(KeyEvent.VK_V);
                robot.keyRelease(KeyEvent.VK_CONTROL);
                Thread.sleep(1000);
                robot.keyPress(KeyEvent.VK_ENTER);
                robot.keyRelease(KeyEvent.VK_ENTER);
            } catch (AWTException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            waitForElement(uploadButton);
            setAttribute(uploadInput, "visibility", "visible");
            setAttribute(uploadInput, "display", "block");
            uploadInput.sendKeys(file.getAbsolutePath());
        }

        return new YouTubeUploadedPage();
    }
}
