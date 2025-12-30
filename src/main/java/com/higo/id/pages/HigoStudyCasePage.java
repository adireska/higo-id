package com.higo.id.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class HigoStudyCasePage {
    private WebDriver driver;
    private WebDriverWait wait;

    public enum StudyCaseTab {
        SEMUA("Semua"),
        HIGO_SPOT("HIGOspot"),
        WIFI_ADVERTISING("WiFi Advertising"),
        INTEGRATED_DIGITAL_AGENCY("Integrated Digital Agency");

        private final String tabName;

        StudyCaseTab(String tabName) {
            this.tabName = tabName;
        }

        public String getTabName() {
            return tabName;
        }
    }

    public HigoStudyCasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void clickTab(StudyCaseTab tab) {
        By tabLocator = By.xpath(
                "//ul[contains(@class, 'flex-nowrap')]//li[contains(normalize-space(), '" + tab.getTabName() + "')]");
        WebElement tabElement = wait.until(ExpectedConditions.elementToBeClickable(tabLocator));
        try {
            tabElement.click();
        } catch (Exception e) {
            new org.openqa.selenium.interactions.Actions(driver).moveToElement(tabElement).click().perform();
        }
    }

    public HigoStudyCaseDetailPage clickCard(String cardName) {
        // User requested: //a[.//div[text()='...']]
        By cardLocator = By.xpath("//a[.//div[normalize-space()='" + cardName + "']]");

        WebElement card = wait.until(ExpectedConditions.presenceOfElementLocated(cardLocator));
        ((org.openqa.selenium.JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView({block: 'center'});", card);

        try {
            wait.until(ExpectedConditions.elementToBeClickable(card)).click();
        } catch (Exception e) {
            new org.openqa.selenium.interactions.Actions(driver).moveToElement(card).click().perform();
        }

        // Wait for URL to change or title to update implies navigation
        return new HigoStudyCaseDetailPage(driver);
    }
}
