package com.saucelabs.advancedselenium.saucedemo.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import com.saucelabs.advancedselenium.saucedemo.pages.CartPage;
import com.saucelabs.advancedselenium.saucedemo.pages.CheckoutPage;
import com.saucelabs.advancedselenium.saucedemo.pages.FinishPage;
import com.saucelabs.advancedselenium.saucedemo.pages.HomePage;
import com.saucelabs.advancedselenium.saucedemo.pages.InformationPage;
import com.saucelabs.advancedselenium.saucedemo.pages.InventoryPage;

public class CheckoutTest extends BaseTest {

    public InventoryPage login() {
        HomePage homePage = new HomePage(driver);
        return homePage.login("standard_user", "secret_sauce");
    }

    public void goToCheckoutWithItem() {
        InventoryPage inventoryPage = new InventoryPage(driver);
        inventoryPage.getAddOnesieButton().click();
        inventoryPage.getCartImageLink().click();

        CartPage cartPage = new CartPage(driver);
        cartPage.getCheckoutButton().click();
    }

    @Test
    public void goodInfo() {
        login();
        goToCheckoutWithItem();

        InformationPage informationPage = new InformationPage(driver);
        informationPage.getFirstNameElement().sendKeys("Luke");
        informationPage.getLastNameElement().sendKeys("Perry");
        informationPage.getPostalCodeElement().sendKeys("90210");
        informationPage.getContinueButton().click();

        Assertions.assertEquals(CheckoutPage.URL,
                driver.getCurrentUrl(),
                "Information Submission Unsuccessful");
    }

    @Test
    public void completeCheckout() {
        login();
        goToCheckoutWithItem();
        InformationPage informationPage = new InformationPage(driver);
        informationPage.getFirstNameElement().sendKeys("Luke");
        informationPage.getLastNameElement().sendKeys("Perry");
        informationPage.getPostalCodeElement().sendKeys("90210");
        informationPage.getContinueButton().click();

        CheckoutPage checkoutPage = new CheckoutPage(driver);
        checkoutPage.getFinishButton().click();

        Assertions.assertEquals(FinishPage.URL, driver.getCurrentUrl());

        FinishPage finishPage = new FinishPage(driver);
        Assertions.assertTrue(finishPage.getCompleteElement().isDisplayed());
    }
}
