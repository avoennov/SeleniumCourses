package ru.stqa.training.litecart.tests;

import org.junit.Test;
import ru.stqa.training.litecart.LoginHelper;
import ru.stqa.training.litecart.TestBase;
import static org.openqa.selenium.support.ui.ExpectedConditions.urlToBe;

public class LoginTest extends TestBase {

    @Test
    public void testLogin() {
        LoginHelper.login("http://localhost/litecart/admin/", "admin", "admin@123$");
        wait.until(urlToBe("http://localhost/litecart/admin/"));
    }
}
