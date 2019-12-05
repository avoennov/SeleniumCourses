package ru.stqa.training.litecart.tests;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.stqa.training.litecart.LoginHelper;
import ru.stqa.training.litecart.TestBase;

public class AdminSectionTest extends TestBase {

    @Test
    public void testAdminSection() throws InterruptedException {
        LoginHelper.login("http://localhost/litecart/admin/", "admin", "admin@123$");
        Thread.sleep(1000);

        int mainMenuCount =  driver.findElements(By.cssSelector("ul#box-apps-menu li#app-")).size();
        int subMenuCount;
        String itemName;
        WebElement subMenu, mainMenu;
        for (int i = 1; i <= mainMenuCount; i++)  {
            mainMenu = afterRefreshPage(driver, i);
            itemName = mainMenu.findElement(By.xpath(".//span[@class='name']")).getText();
            mainMenu.click();
            mainMenu = afterRefreshPage(driver, i);
            subMenuCount = mainMenu.findElements(By.xpath("./ul[@class='docs']/li[@id]")).size();
            if (subMenuCount > 0) {
                for (int j = 1; j <= subMenuCount; j++) {
                    mainMenu = afterRefreshPage(driver, i);
                    subMenu = mainMenu.findElement(By.xpath("./ul[@class='docs']/li[@id][" + j + "]"));
                    itemName = subMenu.findElement(By.xpath(".//span[@class='name']")).getText();
                    subMenu.click();
                    checkHeader(driver, itemName);
                    Thread.sleep(300);
                }
            }
            else
                checkHeader(driver, itemName);
            Thread.sleep(200);
        }
    }

    private WebElement afterRefreshPage(WebDriver d, int i){
        WebElement mainMenu = driver.findElement(By.xpath("//ul[@id='box-apps-menu']/li[@id='app-'][" + i + "]"));
        return mainMenu;
    }

    private void checkHeader(WebDriver d, String itemName){
        String h1;
        if (isElementPresent(d, By.xpath(".//td[@id='content']/h1"))) {
            h1 = d.findElement(By.xpath(".//td[@id='content']/h1")).getText();
            System.out.println("[PASS] - Menu item {" + itemName + "} have header {" + h1 + "}");
        }
        else {
            System.out.println("[FAIL] - Menu item {" + itemName + "} haven't header");
        }
    }

    boolean isElementPresent(WebDriver driver, By locator) {
        try {
            driver.findElement(locator);
            return true;
        } catch (NoSuchElementException ex) {
            return false;
        }
    }
}
