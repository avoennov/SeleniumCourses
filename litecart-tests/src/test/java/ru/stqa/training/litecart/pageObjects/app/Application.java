package ru.stqa.training.litecart.pageObjects.app;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import ru.stqa.training.litecart.pageObjects.pages.CartPage;
import ru.stqa.training.litecart.pageObjects.pages.MainPage;
import ru.stqa.training.litecart.pageObjects.pages.ProductPage;

public class Application{

    private WebDriver driver;

    private MainPage mainPage;
    private ProductPage productPage;
    private CartPage cartPage;

    public Application() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        driver = new ChromeDriver(options);
        mainPage = new MainPage(driver);
        productPage = new ProductPage(driver);
        cartPage = new CartPage(driver);
    }

    public void quit() {
        driver.quit();
    }

    public void addProductsToCart(int productCount) {
        for (int i = 1; i <= productCount; i++) {
            mainPage.open();
            mainPage.firstProduct.click();
            productPage.selectSize("Small");
            productPage.addProductToCart();
        }
    }

    public void removeProductFromCart() {
        mainPage.checkoutLnk.click();
        cartPage.cartProductArea.click();
        cartPage.removeProducts();
    }
}
