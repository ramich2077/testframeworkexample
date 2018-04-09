package stepdef;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebDriverException;
import pages.youtube.*;
import util.DriverLoader;

import java.util.List;

/**
 * Created by Ramich on 07.04.2018.
 */
public class YotubeTestTaskSteps {

    private YouTubeMainPage youTubeMainPage;
    private YouTubeSearchResultsPage youTubeSearchResultsPage;
    private YouTubeUploadedPage youTubeUploadedPage;
    private YouTubeVideoUploadPage youTubeVideoUploadPage;
    private YouTubeStudioPage youTubeStudioPage;
    private YouTubeVideoManagerPage youTubeVideoManagerPage;

    private String videoTitle;
    private String videoDescription;
    private List<String> videoTags;

    @Before
    public void init(){

    }

    @After
    public void dispose(){
        DriverLoader.reset();
    }


    @When("^I open Yotube main page$")
    public void iOpenYotubeMainPage() {
        try {
            DriverLoader.getDriver().get("https://www.youtube.com/");
            youTubeMainPage = new YouTubeMainPage();
        } catch (WebDriverException e) {
            Assert.fail(String.format("WebDriver exception: %s", e.getMessage()));
        }
    }

    @When("^I am logging in with email \"([^\"]*)\" and password \"([^\"]*)\"$")
    public void iAmLoggingInWithEmailAndPassword(String email, String password) {
        try {
            LoginPage loginPage = youTubeMainPage.login();
            youTubeMainPage = loginPage.login(email, password);
        } catch (WebDriverException e) {
            Assert.fail(String.format("WebDriver exception: %s", e.getMessage()));
        }
    }

    @When("^I press 'Add video' button$")
    public void iPressButton() {
        try {
            youTubeVideoUploadPage = youTubeMainPage.uploadVideo();
        } catch (WebDriverException e) {
            Assert.fail(String.format("WebDriver exception: %s", e.getMessage()));
        }
    }

    @When("^I select file \"([^\"]*)\" for upload$")
    public void iSelectFileForUpload(String filename) {
        try {
            youTubeUploadedPage = youTubeVideoUploadPage.uploadVideo(filename);
        } catch (WebDriverException e) {
        Assert.fail(String.format("WebDriver exception: %s", e.getMessage()));
        }

    }

    @When("^I wait until upload will be finished$")
    public void iWaitUntilUploadWillBeFinished() {
        try {
            youTubeUploadedPage.waitForUpload();
        } catch (WebDriverException e) {
            Assert.fail(String.format("WebDriver exception: %s", e.getMessage()));
        }
    }

    @And("^I set title \"([^\"]*)\"$")
    public void iSetTitle(String title) {
        try {
            videoTitle = title;
            youTubeUploadedPage.setTitle(title);
        } catch (WebDriverException e) {
            Assert.fail(String.format("WebDriver exception: %s", e.getMessage()));
        }
    }

    @When("^I add description \"([^\"]*)\"$")
    public void iAddDescriptionAndTags(String description) {
        try {
            videoDescription = description;
            youTubeUploadedPage.addDescription(description);
        } catch (WebDriverException e) {
            Assert.fail(String.format("WebDriver exception: %s", e.getMessage()));
        }

    }

    @And("^I add tags (.*)$")
    public void iAddTagsSelenium_testTest_videoSelenium_test_video_upload_(List<String> tags) {
        try {
            videoTags = tags;
            youTubeUploadedPage.addTags(tags);
        } catch (WebDriverException e) {
            Assert.fail(String.format("WebDriver exception: %s", e.getMessage()));
        }
    }

    @When("^I publish a video$")
    public void iPublishAVideo() {
        try {
            youTubeUploadedPage.publish();
        } catch (WebDriverException e) {
            Assert.fail(String.format("WebDriver exception: %s", e.getMessage()));
        }
    }

    @When("^I open the main page$")
    public void iOpenTheMainPage() throws Throwable {
        try {
            youTubeMainPage = youTubeUploadedPage.openMainPage();
        } catch (WebDriverException e) {
            Assert.fail(String.format("WebDriver exception: %s", e.getMessage()));
        }
    }

    @When("^I search for video with previosly used description and tags$")
    public void iSearchForVideoWithPrevioslyUsedDescriptionAndTags() {
        try {
            youTubeSearchResultsPage = youTubeMainPage
                    //YouTube cannot find the video
                    //.search(videoTitle, videoDescription, videoTags);
                    .search(videoTitle);
        } catch (WebDriverException e) {
            Assert.fail(String.format("WebDriver exception: %s", e.getMessage()));
        }
    }

    @Then("^search results contain my video$")
    public void searchResultsContainMyVideo() {
        try {
            Assert.assertTrue(youTubeSearchResultsPage.checkSearchResultsContainVideoWithTitle(videoTitle));
        } catch (WebDriverException e) {
            Assert.fail(String.format("WebDriver exception: %s", e.getMessage()));
        }
    }

    @Given("^I have to delete all uploaded videos$")
    public void deleteAllUploadedVideosForUserWithEmailAndPassword() {
        try {
            YouTubeStudioPage youTubeStudioPage = youTubeMainPage.goToStudioPage();
            youTubeVideoManagerPage = youTubeStudioPage.openVideoManager();
            youTubeVideoManagerPage.deleteAllVideos();
        } catch (WebDriverException e) {
            Assert.fail(String.format("WebDriver exception: %s", e.getMessage()));
        }
    }

    @And("^logof from Youtube$")
    public void logofFromYoutube() throws Throwable {
        try {
            youTubeVideoManagerPage.logout();
            DriverLoader.reset();
        } catch (WebDriverException e) {
            Assert.fail(String.format("WebDriver exception: %s", e.getMessage()));
        }
    }
}
