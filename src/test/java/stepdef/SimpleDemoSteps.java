package stepdef;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import cucumber.api.PendingException;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import exception.TestException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;


/**
 * Created by Ramich on 24.12.2017.
 */
public class SimpleDemoSteps {

    static WebDriver driver;
    static WebDriverWait wait;

    @Before
    public void init(){
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 30);
        driver.manage().window().maximize();
    }

    @After
    public void dispose(){
        driver.close();
    }

    @Then("^I am on the \"([^\"]*)\" page$")
    public void iAmOnThePage(String pageTitle) {
        assertEquals(pageTitle, driver.getTitle());
    }

    @When("^I open \"([^\"]*)\"$")
    public void iOpen(String url) {
        driver.get(url);
    }

    @And("^I search for \"([^\"]*)\"$")
    public void iSearchFor(String text) {

        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#text"))).sendKeys(text);
        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[@class='search2__button']//button[@type='submit']"))).click();
    }

    @Then("^(\\d+) result contains text \"([^\"]*)\"$")
    public void resultContainsText(int rowNumber, String text) throws TestException {

        List<WebElement> elements = wait.until(
                ExpectedConditions.presenceOfAllElementsLocatedBy(
                        By.xpath("//div[@class='main__content']//h2/a")));
        try{
            String innerText = elements.get(rowNumber-1).getAttribute("innerText");
            assertEquals(text, innerText);
        } catch (IndexOutOfBoundsException e){
            throw new TestException();
        }

    }
}
