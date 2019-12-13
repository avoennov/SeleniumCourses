package ru.stqa.training.litecart.tests;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import ru.stqa.training.litecart.TestBase;

public class CartTest extends TestBase {

    @Test
    public void testCart() throws InterruptedException {
        driver.get("http://localhost/litecart/en/");
        wait.until(ExpectedConditions.elementToBeClickable(By.id("box-most-popular")));

        //Add to cart a first product from Most Popular section
        for (int i = 1; i <= 3; i++) {
            driver.findElement(By.cssSelector("div#box-most-popular ul li")).click();
            wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Information")));
            if (driver.findElements(By.name("options[Size]")).size() == 1) {
                new Select(driver.findElement(By.name("options[Size]"))).selectByValue("Small");
            }
            driver.findElement(By.name("add_cart_product")).click();
            wait.until(ExpectedConditions.textToBe(By.className("quantity"), "" + i));
            driver.navigate().back();
        }

        //Remove all products from the cart
        driver.findElement(By.linkText("Checkout »")).click();
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("box-checkout-cart"))));
        driver.findElement(By.id("box-checkout-cart")).click();
        WebElement products = driver.findElement(By.className("shortcuts"));
        String productCount = products.getAttribute("childElementCount");
        int intPoductCount = Integer.parseInt(productCount);
        for (int j = intPoductCount; j > 0; j--) {
            Thread.sleep(500);
            wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.name("remove_cart_item"))));
            driver.findElement(By.name("remove_cart_item")).click();
            wait.until(ExpectedConditions.numberOfElementsToBeLessThan(By.cssSelector("td.item"), j));
        }
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("<< Back")));
    }
}
