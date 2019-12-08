package ru.stqa.training.litecart.tests;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.Color;
import ru.stqa.training.litecart.TestBase;

public class ProductPageTest extends TestBase {

    @Test
    public void testProductPage () throws InterruptedException {
        driver.get("http://localhost/litecart/en/");
        Thread.sleep(1000);

        WebElement productMainPage = driver.findElement(By.cssSelector("#box-campaigns  li.product"));
        String productNameMainPage = productMainPage.findElement(By.cssSelector("div.name")).getText();
        WebElement regularPriceMainPage = productMainPage.findElement(By.cssSelector("div.price-wrapper s.regular-price"));
        String regularPriceValueMainPage = regularPriceMainPage.getText();
        WebElement discountPriceMainPage = productMainPage.findElement(By.cssSelector("div.price-wrapper strong.campaign-price"));
        String discountPriceValueMainPage = discountPriceMainPage.getText();

        //c) For Main page. Check that Regular price have GREY color and strikethrough font
        checkRegularPriceStyles(regularPriceMainPage);

        //d) For Main page. Check that Discount price have RED color and bold font
        checkDiscountPriceStyles(discountPriceMainPage);

        //e) For Main page. Check that Discount price font size is bigger than Regular
        System.out.println("Main page");
        compareRegularAndDiscountSizes(regularPriceMainPage, discountPriceMainPage);

        //Open Product page
        productMainPage.click();
        String productNameProdPage = driver.findElement(By.cssSelector("h1.title")).getText();
        WebElement regularPriceProdPage = driver.findElement(By.cssSelector("div.price-wrapper s.regular-price"));
        String regularPriceValueProdPage = regularPriceProdPage.getText();
        WebElement discountPriceProdPage = driver.findElement(By.cssSelector("div.price-wrapper strong.campaign-price"));
        String discountPriceValueProdPage = discountPriceProdPage.getText();

        //a) Check that Product name is same on Main and Product pages
        Assert.assertEquals(productNameMainPage, productNameProdPage);

        //b) Check that Regular and Discount prices are same on Main and Product pages
        Assert.assertEquals(regularPriceValueMainPage, regularPriceValueProdPage);
        Assert.assertEquals(discountPriceValueMainPage, discountPriceValueProdPage);

        //c) For Product page. Check that Regular price have GREY color and strikethrough font
        checkRegularPriceStyles(regularPriceProdPage);

        //d) For Product page. Check that Discount price have RED color and bold font
        checkDiscountPriceStyles(discountPriceProdPage);

        //e) For Product page. Check that Discount price font size is bigger than Regular
        System.out.println("Product page");
        compareRegularAndDiscountSizes(regularPriceProdPage, discountPriceProdPage);
    }

    private void checkRegularPriceStyles(WebElement el) {
        Assert.assertEquals("[FAIL] - Regular price is not strikethrough.", "line-through", el.getCssValue("text-decoration-line"));
        Color color = Color.fromString(el.getCssValue("color"));
        Assert.assertEquals("[FAIL] - Regular price is not GREY.", color.getColor().getRed(), color.getColor().getGreen());
        Assert.assertEquals("[FAIL] - Regular price is not GREY.", color.getColor().getGreen(), color.getColor().getBlue());
    }

    private void checkDiscountPriceStyles(WebElement el) {
        Assert.assertEquals("[FAIL] - Discount price is not bold.", "700", el.getCssValue("font-weight"));
        Color color = Color.fromString(el.getCssValue("color"));
        Assert.assertNotEquals("[FAIL] - Discount price is not RED.", color.getColor().getRed(), color.getColor().getGreen());
        Assert.assertEquals("[FAIL] - Discount price is not RED.", color.getColor().getGreen(), color.getColor().getBlue());
    }

    private void compareRegularAndDiscountSizes(WebElement regularPrice, WebElement discountPrice) {
        String regularSize = regularPrice.getCssValue("font-size");
        String discountSize = discountPrice.getCssValue("font-size");
        if (Float.parseFloat(regularSize.substring(0, regularSize.length() - 2)) < Float.parseFloat(discountSize.substring(0, discountSize.length() - 2)))
            System.out.print("[PASS] - Discount price font size is bigger than Regular\n");
        else Assert.fail("[FAIL] - Discount price font size is smaller than Regular\n");
    }
}
