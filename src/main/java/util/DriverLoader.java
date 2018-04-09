package util;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by Ramich on 06.04.2018.
 */
public class DriverLoader {

    private static final Logger log = LoggerFactory.getLogger(DriverLoader.class);

    private static WebDriver driver;
    private static DriverLoader loader;
    private static int defaultTimeout;

    private DriverLoader(){

        Properties config = new Properties();

        try {
            config.load(Thread.currentThread().getContextClassLoader()
                    .getResourceAsStream("config.properties"));
        } catch (FileNotFoundException e) {
            log.error("Cannot find config file");
            System.exit(-1);
        } catch (IOException e) {
            log.error("Unexpected IO exception: {}", e.getMessage());
            System.exit(-1);
        }


        System.setProperty("webdriver.chrome.driver", config.getProperty("chrome_executable_path"));
        System.setProperty("webdriver.gecko.driver", config.getProperty("firefox_executable_path"));

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

        defaultTimeout = Integer.valueOf(config.getProperty("default_timeout"));

        driver.manage().window().maximize();
    }

    public static WebDriver getDriver(){
        if (DriverLoader.loader == null) DriverLoader.loader = new DriverLoader();
        return driver;
    }

    public static int getTimeout(){
        if (DriverLoader.loader == null) DriverLoader.loader = new DriverLoader();
        return defaultTimeout;
    }

    public static void reset() {
        if (driver != null) driver.quit();
        loader = null;
    }
}
