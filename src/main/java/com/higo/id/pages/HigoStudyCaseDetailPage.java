package com.higo.id.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class HigoStudyCaseDetailPage {
    private WebDriver driver;
    private WebDriverWait wait;

    private By headerImage = By.xpath("//section[contains(@class, 'grid-content')]//img");

    private By articleTitle = By.xpath("//h2[contains(@class, 'font-accent')]");

    public HigoStudyCaseDetailPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public boolean isHeaderImageDisplayed() {
        try {
            WebElement image = wait.until(ExpectedConditions.visibilityOfElementLocated(headerImage));
            boolean isVisible = image.isDisplayed();
            if (isVisible) {
                Object result = ((org.openqa.selenium.JavascriptExecutor) driver).executeScript(
                        "return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0",
                        image);
                if (result instanceof Boolean) {
                    return (Boolean) result;
                }
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    public String getArticleTitle() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(articleTitle)).getText();
        } catch (Exception e) {
            return "";
        }
    }
}
