package stepdef;

import cucumber.api.java.After;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import pages.exception.AutotestError;
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
            PageManager.getPageManager().getCurrentPage().click("Войти");
            PageManager.getPageManager().setCurrentPageAs("Login page");
            PageManager.getPageManager().getCurrentPage().fillField("Логин", "test06042018");
            PageManager.getPageManager().getCurrentPage().click("Логин - Далее");
            PageManager.getPageManager().getCurrentPage().fillField("Пароль", "sbt_test");
            PageManager.getPageManager().getCurrentPage().click("Пароль - Далее");
            PageManager.getPageManager().setCurrentPageAs("Main page");
            PageManager.getPageManager().getCurrentPage().click("Аккаунт");
            PageManager.getPageManager().getCurrentPage().click("Творческая студия");
            PageManager.getPageManager().setCurrentPageAs("Studio");
            PageManager.getPageManager().getCurrentPage().click("Менеджер видео");
            PageManager.getPageManager().setCurrentPageAs("Video manager");
            PageManager.getPageManager().getCurrentPage().doMethod("delete all videos");
            PageManager.getPageManager().getCurrentPage().click("Аккаунт");
            PageManager.getPageManager().getCurrentPage().click("Выйти");
        } catch (AutotestError error) {
            failOnError(error);
        } finally {
            DriverManager.reset();
        }
    }

    private void failOnError(AutotestError error) {
        log.error(error.getMessage(), error);
        Assert.fail(String.format("Test failed with error: %s", error.getMessage()));
    }
}
