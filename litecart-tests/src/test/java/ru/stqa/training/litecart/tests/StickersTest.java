package ru.stqa.training.litecart.tests;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import ru.stqa.training.litecart.TestBase;

public class StickersTest extends TestBase {

    @Test
    public void testStickers() throws InterruptedException {
        driver.get("http://localhost/litecart/en/");
        Thread.sleep(1000);

        String[] sectionType = {"box-most-popular", "box-campaigns", "box-latest-products"};
        String locator, itemName, stickerName, sectionName;
        int itemsCount, stickerCount;
        WebElement item;

        for (String section : sectionType) {
            sectionName = driver.findElement(By.cssSelector("#" + section + " .title")).getText();
            System.out.println(sectionName);
            locator = "#" + section + " li.product";
            itemsCount = driver.findElements(By.cssSelector(locator)).size();
            if (itemsCount > 0) {
                for (int i = 1; i <= itemsCount; i++) {
                    item = driver.findElement(By.cssSelector(locator + ":nth-child(" + i + ")"));
                    stickerCount = item.findElements(By.cssSelector("div.sticker")).size();
                    itemName = item.findElement(By.cssSelector(".name")).getText();

                    if (stickerCount == 1) {
                        stickerName = item.findElement(By.cssSelector(" .sticker")).getText();
                        System.out.println("[PASS] - Item {" + itemName + "} has sticker {" + stickerName + "}");
                    }
                    else if (stickerCount > 1)
                        System.out.println("[FAIL] - Item {" + itemName + "} has stickers more than one");
                    else
                        System.out.println("[FAIL] - Item {" + itemName + "} doesn't have sticker");
                }
            }
            else
                System.out.println("[FAIL] - Items are absent");
        }
    }
}
