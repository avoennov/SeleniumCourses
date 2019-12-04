package ru.stqa.training.litecart;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.concurrent.TimeUnit;
import static org.openqa.selenium.support.ui.ExpectedConditions.urlToBe;

public class LoginTest {

    private WebDriver driver;
    private WebDriverWait wait;
    private FirefoxOptions options;
    private final String browser = BrowserType.CHROME;  //Also possible set FIREFOX or IE for launch test in according browser
    //private final String browser = "FirefoxESR";
    //private final String browser = "FirefoxNightly";

    @Before
    public void start () {
        if (browser.equals(BrowserType.FIREFOX)) {
            driver = new FirefoxDriver();
        } else if (browser.equals(BrowserType.CHROME)) {
            driver = new ChromeDriver();
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
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get("http://localhost/litecart/admin/");
    }

    @Test
    public void testLogin(){
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin@123$");
        driver.findElement(By.name("login")).click();
        wait.until(urlToBe("http://localhost/litecart/admin/"));
    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }
}
