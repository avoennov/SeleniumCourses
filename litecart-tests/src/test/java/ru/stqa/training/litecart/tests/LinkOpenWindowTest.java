package ru.stqa.training.litecart.tests;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ru.stqa.training.litecart.LoginHelper;
import ru.stqa.training.litecart.TestBase;

import java.util.List;
import java.util.Set;

public class LinkOpenWindowTest extends TestBase {

    @Test
    public void testLinkOpenWindow () throws InterruptedException {
        LoginHelper.login("http://localhost/litecart/admin/", "admin", "admin@123$");
        Thread.sleep(1000);

        driver.findElement(By.linkText("Countries")).click();
        driver.findElement(By.linkText("Australia")).click();

        List<WebElement> extLinkList = driver.findElements(By.className("fa-external-link"));
        for (WebElement extLink : extLinkList) {
            String mainWindow = driver.getWindowHandle();
            extLink.click();
            wait.until(ExpectedConditions.numberOfWindowsToBe(2));
            Set<String> existingWindows = driver.getWindowHandles();
            for (String handle : existingWindows) {
                if (!handle.equals(mainWindow)) {
                    driver.switchTo().window(handle);
                    driver.close();
                }
                driver.switchTo().window(mainWindow);
            }
        }
    }
}
