package stepdef;

import cucumber.api.java.After;
import exception.AutotestError;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.openqa.selenium.WebDriverException;
import util.DriverManager;
import util.PageManager;

/**
 * Created by Ramich on 27.10.2018.
 */
@Slf4j
public class AfterSteps {

    @After("@Test_Youtube")
    public void afterYouTubeTest() {
        try {
            DriverManager.getDriver().get("https://www.youtube.com/");
            PageManager.getPageManager().setCurrentPageAs("Main page");
            PageManager.getPageManager().getCurrentPage().click("login button");
            PageManager.getPageManager().setCurrentPageAs("Login page");
            PageManager.getPageManager().getCurrentPage().fillField("identifier input", "test06042018");
            PageManager.getPageManager().getCurrentPage().click("identifier next button");
            PageManager.getPageManager().getCurrentPage().fillField("password input", "sbt_test");
            PageManager.getPageManager().getCurrentPage().click("password next button");
            PageManager.getPageManager().setCurrentPageAs("Main page");
            PageManager.getPageManager().getCurrentPage().click("avatar button");
            PageManager.getPageManager().getCurrentPage().click("studio link");
            PageManager.getPageManager().setCurrentPageAs("Studio");
            PageManager.getPageManager().getCurrentPage().click("video manager link");
            PageManager.getPageManager().setCurrentPageAs("Video manager");
            PageManager.getPageManager().getCurrentPage().doMethod("delete all videos");
            PageManager.getPageManager().getCurrentPage().click("avatar button");
            PageManager.getPageManager().getCurrentPage().click("logout button");
        } catch (AutotestError|WebDriverException error) {
            failOnError(error);
        } finally {
            DriverManager.reset();
        }
    }

    //FIXME Wrap WDException to Autotest error
    private void failOnError(Exception error) {
        log.error(error.getMessage(), error);
        Assert.fail(String.format("Test failed with error: %s", error.getMessage()));
    }
}
