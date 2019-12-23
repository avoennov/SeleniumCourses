package ru.stqa.training.selenium;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;

import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

public class CloudGridTest {

    private WebDriver driver;
    private WebDriverWait wait;

    public static final String USERNAME = "alexey92";
    public static final String AUTOMATE_KEY = "r2tQwNpy679sf0HyFp8sOpvMpWrz";
    public static final String URL = "https://" + USERNAME + ":" + AUTOMATE_KEY + "@hub-cloud.browserstack.com/wd/hub";

    @Before
    public void start() throws Exception {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("browser", "Chrome");
        caps.setCapability("browser_version", "79.0");
        caps.setCapability("os", "Windows");
        caps.setCapability("os_version", "7");
        caps.setCapability("resolution", "1280x1024");
        caps.setCapability("name", "Bstack-[Java] Sample Test");
        driver = new RemoteWebDriver(new URL(URL), caps);
        wait = new WebDriverWait(driver, 10);
    }

    @Test
    public void cloudGridTest() {
        driver.get("http://www.google.com/");
        driver.findElement(By.name("q")).sendKeys("webdriver");
        driver.findElement(By.name("btnK")).click();
        wait.until(titleIs("webdriver - Google Search"));
    }

    @After
    public void stop() {
        driver.quit();
    }
}
