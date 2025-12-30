package com.higo.id.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HigoAboutPage {
    private WebDriver driver;
    private WebDriverWait wait;

    public HigoAboutPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public boolean isPageLoaded() {
        try {
            return wait.until(ExpectedConditions.urlContains("about-us"));
        } catch (Exception e) {
            return false;
        }
    }
}
