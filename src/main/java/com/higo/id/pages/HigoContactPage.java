package com.higo.id.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HigoContactPage {
    private WebDriver driver;
    private WebDriverWait wait;

    // Locators
    // Based on subagent: name input is a good check
    private By nameInput = By.cssSelector("//input[@placeholder='ex.Clara']");

    public HigoContactPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public boolean isPageLoaded() {
        try {
            return wait.until(ExpectedConditions.urlContains("contact-us"));
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isContactFormDisplayed() {
        try {
            WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(nameInput));
            return input.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public String getPageUrl() {
        return driver.getCurrentUrl();
    }
}
