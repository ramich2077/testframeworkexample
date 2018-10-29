package stepdef;

import cucumber.api.java.After;
import exception.AutotestError;
import lombok.extern.slf4j.Slf4j;
import util.DriverManager;
import util.PageManager;

import static stepdef.GeneralSteps.failOnError;

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
            PageManager.getPageManager().getCurrentPage().fillField("password input", "T3st_3xampl3");
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
        } catch (AutotestError error) {
            failOnError(error);
        } finally {
            DriverManager.reset();
        }
    }

}
