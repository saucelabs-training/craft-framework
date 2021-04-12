package test.java.com.saucelabs.advancedselenium.saucedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;

public class CheckoutPage {
    private RemoteWebDriver driver;

    private final By finishButton = By.id("finish");

    public CheckoutPage(RemoteWebDriver driver) {
        this.driver = driver;
    }

    public boolean isOnPage() {
        return driver.getCurrentUrl().equals("https://www.saucedemo.com/checkout-step-two.html");
    }

    public FinishPage finish() {
        driver.findElement(finishButton).click();
        return new FinishPage(driver);
    }
}