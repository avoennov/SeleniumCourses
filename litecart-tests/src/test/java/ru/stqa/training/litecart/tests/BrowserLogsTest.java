package ru.stqa.training.litecart.tests;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.logging.LogEntry;
import ru.stqa.training.litecart.LoginHelper;
import ru.stqa.training.litecart.TestBase;

public class BrowserLogsTest extends TestBase {

    @Test
    public void testBrowserLogs() throws InterruptedException {
        LoginHelper.login("http://localhost/litecart/admin/", "admin", "admin@123$");
        Thread.sleep(1000);

        driver.findElement(By.linkText("Catalog")).click();
        driver.findElement(By.linkText("Rubber Ducks")).click();
        driver.findElement(By.linkText("Subcategory")).click();

        int size = driver.findElements(By.xpath("//tr[.//input[contains(@name, 'products')]]/td[3]/a")).size();
        for (int i = 1; i <= size; i++) {
            driver.findElement(By.xpath("//tr[.//input[contains(@name, 'products')]][" + i + "]/td[3]/a")).click();
            for (LogEntry l : driver.manage().logs().get("browser").getAll()) {
                System.out.println(l);
                Assert.assertNull(l);
            }
            driver.navigate().back();
        }
    }
}
