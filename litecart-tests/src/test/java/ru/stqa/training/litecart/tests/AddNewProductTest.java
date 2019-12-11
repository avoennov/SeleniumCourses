package ru.stqa.training.litecart.tests;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.ui.Select;
import ru.stqa.training.litecart.LoginHelper;
import ru.stqa.training.litecart.TestBase;

import java.io.File;

public class AddNewProductTest extends TestBase {

    @Test
    public void testAddNewProduct () throws InterruptedException {
        LoginHelper.login("http://localhost/litecart/admin/", "admin", "admin@123$");
        Thread.sleep(1000);

        //Open "Add a new product" page
        driver.findElement(By.xpath("//span[text()='Catalog']")).click();
        driver.findElement(By.xpath("//a[contains(text(),'Add New Product')]")).click();

        //General tab
        driver.findElement(By.xpath("//label[contains(text(),'Enabled')]")).click();
        driver.findElement(By.name("name[en]")).sendKeys("Meow pizza");
        driver.findElement(By.name("code")).sendKeys("yummy001");
        driver.findElement(By.name("quantity")).sendKeys("7");
        driver.findElement(By.name("new_images[]")).sendKeys(new File("src/test/resources/kitten-pizza.jpg").getAbsolutePath());
        driver.findElement(By.name("date_valid_from")).sendKeys("01-01-2018");
        driver.findElement(By.name("date_valid_to")).sendKeys("31-12-2020");

        //Information tab
        driver.findElement(By.xpath("//a[contains(text(),'Information')]")).click();
        new Select(driver.findElement(By.name("manufacturer_id"))).selectByValue("1");
        driver.findElement(By.name("keywords")).sendKeys("cat pizza catizza");
        driver.findElement(By.name("short_description[en]")).sendKeys("Very cute pizza with mushrooms");
        driver.findElement(By.className("trumbowyg-editor")).sendKeys("A long time ago, in a galaxy far, far away... " +
                "It is a period of civil war. Rebel spaceships, striking from a hidden base, have won their first victory against the evil " +
                "Galactic Empire. During the battle, Rebel spies managed to steal secret plans to the Empire's ultimate weapon, the Death Star," +
                " anarmored space station with enough power to destroy an entire planet. Pursued by the Empire's sinister agents, Princess Leia" +
                " races home aboard her starship, custodian of the stolen plans that can save her people and restore freedom to the galaxy....");

        //Prices tab
        driver.findElement(By.xpath("//a[contains(text(),'Prices')]")).click();
        driver.findElement(By.name("purchase_price")).sendKeys("9,99");
        new Select(driver.findElement(By.name("purchase_price_currency_code"))).selectByValue("USD");
        driver.findElement(By.name("prices[USD]")).sendKeys("1");
        driver.findElement(By.name("save")).click();
        Thread.sleep(1000);

        //Check that product shown in Catalog
        try {
            driver.findElement(By.linkText("Meow pizza")).isDisplayed();
            System.out.println("[PASS] - Product successfully added to Catalog");
        } catch (NoSuchElementException ex) {
            System.out.println("[FAIL] - Product is absent in Catalog");
        }
    }
}
