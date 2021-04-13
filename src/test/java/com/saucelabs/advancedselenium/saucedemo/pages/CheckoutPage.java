package test.java.com.saucelabs.advancedselenium.saucedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import test.java.com.saucelabs.advancedselenium.resources.exceptions.PageValidationException;
import test.java.com.saucelabs.advancedselenium.resources.pages.BasePage;

public class CheckoutPage extends BasePage {
    private final By finishButton = By.id("finish");
    private final By errorElement = By.cssSelector("[data-test=error]");

    public CheckoutPage(RemoteWebDriver driver) {
        super(driver);
        this.pageUrl = "https://www.saucedemo.com/checkout-step-two.html";
    }

    public void finish() {
        driver.findElement(finishButton).click();
    }

    public void validateCheckoutSuccessful() {
        if (isOnPage()) {
            String error = driver.findElement(errorElement).getText();
            throw new PageValidationException("Information submission was not successful: " + error);
        }
    }
}
