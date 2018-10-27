package stepdef;

import cucumber.api.java.After;
import cucumber.api.java.en.When;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.openqa.selenium.WebDriverException;
import pages.exception.AutotestError;
import util.DriverManager;
import util.PageManager;

/**
 * Created by Ramich on 07.04.2018.
 */
@Slf4j
public class GeneralSteps {

    @After
    public void dispose(){
        DriverManager.reset();
    }

    @When("^I open \"([^\"]*)\"$")
    public void iOpen(String url) {
        try {
            DriverManager.getDriver().get(url);
        } catch (WebDriverException error) {
            failOnError(new AutotestError("WebDriver error", error));
        }
    }

    @When("^I am on page \"([^\"]*)\"$")
    public void iAmOnPage(String title) {
        try {
            PageManager.getPageManager().setCurrentPageAs(title);
        } catch (AutotestError error) {
            failOnError(error);
        }
    }

    @When("^Press (?:button|element) \"([^\"]*)\"$")
    public void pressButton(String title) {
        try {
            PageManager.getPageManager().getCurrentPage().click(title);
        } catch (AutotestError error) {
            failOnError(error);
        }
    }

    @When("^Fill field \"([^\"]*)\" with text \"([^\"]*)\"$")
    public void fillField(String title, String text) {
        try {
            PageManager.getPageManager().getCurrentPage().fillField(title, text);
        } catch (AutotestError error) {
            failOnError(error);
        }
    }

    @When("^Clear field \"([^\"]*)\"$")
    public void clearField(String title) {
        try {
            PageManager.getPageManager().getCurrentPage().clearText(title);
        } catch (AutotestError error) {
            failOnError(error);
        }
    }

    @When("^I have to ([^\"]*)$")
    public void doMethod(String name) {
        try {
            PageManager.getPageManager().getCurrentPage().doMethod(name);
        } catch (AutotestError error) {
            failOnError(error);
        }
    }

    @When("^I select file \"([^\"]*)\" for upload to \"([^\"]*)\"$")
    public void iSelectFileForUpload(String filename, String elementTitle) {
        try {
            PageManager.getPageManager().getCurrentPage().uploadFile(elementTitle, filename);
        } catch (AutotestError error) {
            failOnError(error);
        }
    }

    @When("^Wait element \"([^\"]*)\" contains text \"([^\"]*)\" with timeout (\\d+)$")
    public void elementContainsText(String title, String text, int timeout) {
        try {
            PageManager.getPageManager().getCurrentPage()
                    .waitTextToBePresentInElement(title, text, timeout);
        } catch (AutotestError error) {
            failOnError(error);
        }
    }

    private void failOnError(AutotestError error) {
        log.error(error.getMessage(), error);
        Assert.fail(String.format("Test failed with error: %s", error.getMessage()));
    }
}
