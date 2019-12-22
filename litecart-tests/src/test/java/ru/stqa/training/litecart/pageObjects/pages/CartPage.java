package ru.stqa.training.litecart.pageObjects.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CartPage extends Page {

    public CartPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "box-checkout-cart")
    public WebElement cartProductArea;

    @FindBy(className = "shortcuts")
    public WebElement shortcuts;

    @FindBy(name = "remove_cart_item")
    public WebElement removeBtn;

    @FindBy(linkText = "<< Back")
    public WebElement backLnk;

    public void removeProducts() {
        wait.until(ExpectedConditions.elementToBeClickable(cartProductArea));
        cartProductArea.click();
        String productCount = shortcuts.getAttribute("childElementCount");
        int intPoductCount = Integer.parseInt(productCount);
        for (int j = intPoductCount; j > 0; j--) {
            wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.name("remove_cart_item"))));
            removeBtn.click();
            wait.until(ExpectedConditions.numberOfElementsToBeLessThan(By.cssSelector("td.item"), j));
        }
        wait.until(ExpectedConditions.elementToBeClickable(backLnk));
    }
}
