package ru.stqa.training.litecart;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TestBase {

    public static WebDriver driver;
    public WebDriverWait wait;
    private FirefoxOptions options;
    private final String browser = BrowserType.CHROME;  //Also possible set FIREFOX or IE for launch test in according browser
    //private final String browser = "FirefoxESR";
    //private final String browser = "FirefoxNightly";

    @Before
    public void start () {
        if (browser.equals(BrowserType.FIREFOX)) {
            driver = new FirefoxDriver();
        } else if (browser.equals(BrowserType.CHROME)) {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("start-maximized");
            driver = new ChromeDriver(options);
        } else if (browser.equals(BrowserType.IE)) {
            driver = new InternetExplorerDriver();
        } else if (browser.equals("FirefoxESR")) {
            options = new FirefoxOptions().setLegacy(true);
            options.setBinary("C:\\Program Files\\Mozilla Firefox ESR\\firefox.exe");
            driver = new FirefoxDriver(options);
        } else if (browser.equals("FirefoxNightly")) {
            options = new FirefoxOptions();
            options.setBinary("C:\\Program Files\\Firefox Nightly\\firefox.exe");
            driver = new FirefoxDriver(options);
        }
        wait = new WebDriverWait(driver, 10);
        //driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }
}
