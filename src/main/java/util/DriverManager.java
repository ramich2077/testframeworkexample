package util;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by Ramich on 06.04.2018.
 */
@Slf4j
public class DriverManager {
    private static DriverManager driverManager;

    private WebDriver driver;
    private int defaultTimeout;

    private DriverManager(){

        Properties config = new Properties();

        try {
            config.load(Thread.currentThread().getContextClassLoader()
                        .getResourceAsStream("config.properties"));
        } catch (FileNotFoundException e) {
            log.error("Cannot find config file");
            System.exit(1);
        } catch (IOException e) {
            log.error("Unexpected IO exception: {}", e.getMessage());
            System.exit(1);
        }

        System.setProperty("webdriver.chrome.driver", config.getProperty("chrome_executable_path"));
        System.setProperty("webdriver.gecko.driver", config.getProperty("firefox_executable_path"));

        defaultTimeout = Integer.valueOf(config.getProperty("default_timeout"));

        switch (config.getProperty("driver")) {
            case "chrome":
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--disable-notifications");
                driver = new ChromeDriver(options);
                break;
            case "firefox":
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.setCapability("marionette", true);
                firefoxOptions.addPreference("dom.webnotifications.enabled", false);
                driver = new FirefoxDriver(firefoxOptions);
                break;
            default:
                log.error("Invalid driver name");
                break;
        }

        driver.manage().window().maximize();
    }

    private static DriverManager getDriverManager() {
        if (driverManager == null) {
            driverManager = new DriverManager();
        }
        return driverManager;
    }

    public static WebDriver getDriver(){
        return getDriverManager().driver;
    }

    public static int getTimeout(){
        return getDriverManager().defaultTimeout;
    }

    public static WebDriverWait getWait(int timeout) {
        return new WebDriverWait(getDriver(), timeout);
    }

    public static void reset() {
        if (getDriverManager().driver != null) getDriverManager().driver.quit();
        driverManager = null;
    }
}
