package ru.stqa.training.litecart;

import org.openqa.selenium.By;

public class LoginHelper extends TestBase {

    public static void login(String url, String username, String password) {
        driver.get(url);
        driver.findElement(By.name("username")).sendKeys(username);
        driver.findElement(By.name("password")).sendKeys(password);
        driver.findElement(By.name("login")).click();
    }
}
