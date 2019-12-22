package ru.stqa.training.litecart.pageObjects.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MainPage extends Page{

    public MainPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(css="div#box-most-popular ul li")
    public WebElement firstProduct;

    @FindBy(css="#logotype-wrapper")
    public WebElement mainPageLogo;

    @FindBy(linkText = "Checkout »")
    public WebElement checkoutLnk;

    public void open() {
        driver.get("http://localhost/litecart/en/");
    }
}
