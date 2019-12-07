package ru.stqa.training.litecart.tests;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import ru.stqa.training.litecart.LoginHelper;
import ru.stqa.training.litecart.TestBase;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.openqa.selenium.support.ui.ExpectedConditions.urlToBe;

public class CountriesAndGeoTests extends TestBase {

    @Test
    public void testCountriesSorting() {
        LoginHelper.login("http://localhost/litecart/admin/?app=countries&doc=countries","admin", "admin@123$");
        wait.until(urlToBe("http://localhost/litecart/admin/?app=countries&doc=countries"));

        List<String> countriesList = driver.findElements(By.cssSelector("table tr.row td:nth-child(5)"))
                .stream().map(WebElement::getText).collect(Collectors.toList());
        System.out.println("COUNTRIES");
        checkSorting(countriesList);
        for (String country : countriesList) {
            WebElement countryLink = driver.findElement(By.linkText(country));
            String zoneCount = countryLink.findElement(By.xpath("./../../td[6]")).getText();
            int zoneCountInt = Integer.parseInt(zoneCount);
            if (zoneCountInt > 0) {
                countryLink.click();
                List<String> zonesList = driver.findElements(By.cssSelector("#table-zones td:nth-of-type(3)"))
                        .stream().map(WebElement::getText).collect(Collectors.toList());
                System.out.println("\nGEO ZONES of {" + country + "}");
                checkSorting(zonesList);
                driver.navigate().back();
            } else if (zoneCountInt < 0) {
                System.out.println("[FAIL] - Count of geo zones can't less than '0'");
            }
        }
    }

    @Test
    public void testGeoZonesSorting() throws InterruptedException {
        LoginHelper.login("http://localhost/litecart/admin/?app=geo_zones&doc=geo_zones","admin", "admin@123$");
        wait.until(urlToBe("http://localhost/litecart/admin/?app=geo_zones&doc=geo_zones"));
        Thread.sleep(500);

        List<String> countriesList = driver.findElements(By.cssSelector("table tr.row td:nth-child(3)"))
                .stream().map(WebElement::getText).collect(Collectors.toList());
        for (String country : countriesList) {
            driver.findElement(By.linkText(country)).click();
            List<String> zonesList = driver.findElements(By.cssSelector("#table-zones td:nth-of-type(3) option[selected]"))
                    .stream().map(WebElement::getText).collect(Collectors.toList());
            System.out.println("\nGEO ZONES of {" + country + "}");
            checkSorting(zonesList);
            driver.navigate().back();
        }
    }

    private void checkSorting(List<String> actualList) {
        List<String> sortedList = actualList;
        Collections.sort(sortedList);
        Assert.assertEquals(actualList, sortedList);
        System.out.println("[PASS] - Items are sorted alphabetically");
        System.out.println("Actual list:\n" + actualList + "\nSorted list:\n" + sortedList);
    }
}
