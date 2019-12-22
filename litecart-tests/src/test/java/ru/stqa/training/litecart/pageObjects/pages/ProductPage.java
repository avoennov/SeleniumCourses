package ru.stqa.training.litecart.pageObjects.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class ProductPage extends Page {

    public ProductPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(name="add_cart_product")
    public WebElement addProduct;

    @FindBy(xpath = "//span[@class='quantity']")
    public WebElement productsCount;

    @FindBy(xpath = "//select[@name='options[Size]']")
    public List<WebElement> optionSize;

    public void addProductToCart() {
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Information")));
        int countBefore = Integer.parseInt(productsCount.getText());
        addProduct.click();
        wait.until(ExpectedConditions.attributeToBe(productsCount,"innerText", Integer.toString(countBefore + 1)));
    }

    public void selectSize(String size) {
        if (optionSize.size() > 0) {
            optionSize.get(0).sendKeys(size);
        }
    }
}
