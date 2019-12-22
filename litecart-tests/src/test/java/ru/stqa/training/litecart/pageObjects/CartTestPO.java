package ru.stqa.training.litecart.pageObjects;

import org.junit.Test;

public class CartTestPO extends TestBasePO {

    @Test
    public void testCart() {
        app.addProductsToCart(3);
        app.removeProductFromCart();
    }
}
