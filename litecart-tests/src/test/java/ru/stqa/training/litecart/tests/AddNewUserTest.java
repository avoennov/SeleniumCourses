package ru.stqa.training.litecart.tests;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import ru.stqa.training.litecart.TestBase;

import java.sql.Timestamp;

public class AddNewUserTest extends TestBase {

    @Test
    public void testAddNewUser() throws InterruptedException {
        driver.get("http://localhost/litecart/en/");
        Thread.sleep(1000);

        driver.findElement(By.linkText("New customers click here")).click();
        Thread.sleep(1000);

        long timestamp = new Timestamp(System.currentTimeMillis()).getTime();

        String firstName = "Ghost_" + timestamp;
        String lastName = "Busters";
        String address = "14 North Moore Street";
        String postcode = "10013";
        String city = "New York";
        String country = "United States";
        String state = "NY";
        String email = firstName + "@mail.com";
        String phone = "1-800-555-2368";
        String password = "Slimer123";

        driver.findElement(By.name("firstname")).sendKeys(firstName);
        driver.findElement(By.name("lastname")).sendKeys(lastName);
        driver.findElement(By.name("address1")).sendKeys(address);
        driver.findElement(By.name("postcode")).sendKeys(postcode);
        driver.findElement(By.name("city")).sendKeys(city);
        driver.findElement(By.cssSelector("span[role=presentation]")).click();
        driver.findElement(By.cssSelector("span[class$=dropdown] input[role=textbox]")).sendKeys(country + Keys.ENTER);
        new Select(driver.findElement(By.cssSelector("select[name=zone_code]"))).selectByValue(state);
        driver.findElement(By.name("email")).sendKeys(email);
        driver.findElement(By.name("phone")).sendKeys(phone);
        driver.findElement(By.name("password")).sendKeys(password);
        driver.findElement(By.name("confirmed_password")).sendKeys(password);
        driver.findElement(By.name("create_account")).click();
        System.out.println("Email:\n" + email);
        Thread.sleep(1000);

        //Logout
        WebElement logoutBtn = driver.findElement(By.xpath("//div[@class='content']//a[contains(text(),'Logout')]"));
        logoutBtn.click();

        //Login by new user
        driver.findElement(By.name("email")).sendKeys(email);
        driver.findElement(By.name("password")).sendKeys(password);
        driver.findElement(By.name("login")).click();
        Thread.sleep(1000);

        //Logout
        WebElement logoutBtn2 = driver.findElement(By.xpath("//div[@class='content']//a[contains(text(),'Logout')]"));
        logoutBtn2.click();
    }
}
