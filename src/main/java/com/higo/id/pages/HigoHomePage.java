package com.higo.id.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HigoHomePage {
    private WebDriver driver;
    private WebDriverWait wait;

    // Locators
    private By logo = By.xpath("//img[contains(@src, 'higo') or contains(@alt, 'HIGO')]");
    private By MenucontactUs = By.xpath("//a[contains(@href, 'contact-us')]");
    private By menuAboutUs = By.xpath("//label[@class='peer-checked:hidden']//span[contains(text(),'Tentang HIGO')]");
    private By menuHoverServiceHigo = By
            .xpath("//label[contains(@class, 'peer-checked:hidden')]//span[contains(text(),'Layanan HIGO')]");
    private By menuHoverStudiKasus = By
            .xpath("//label[contains(@class, 'peer-checked:hidden')]//span[contains(text(),'Studi Kasus')]");
    private By menuBlog = By.xpath("//label[@class='peer-checked:hidden']//span[contains(text(),'Blog')]");

    // Constructor
    public HigoHomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Actions
    public HigoContactPage clickHubungiHigo() {
        wait.until(ExpectedConditions.elementToBeClickable(MenucontactUs)).click();
        return new HigoContactPage(driver);
    }

    public HigoAboutPage clickAboutUs() {
        wait.until(ExpectedConditions.elementToBeClickable(menuAboutUs)).click();
        return new HigoAboutPage(driver);
    }

    public HigoStudyCasePage clickStudyCase() {
        wait.until(ExpectedConditions.elementToBeClickable(menuHoverStudiKasus)).click();
        return new HigoStudyCasePage(driver);
    }

    public HigoBlogPage clickBlog() {
        wait.until(ExpectedConditions.elementToBeClickable(menuBlog)).click();
        return new HigoBlogPage(driver);
    }

    public boolean isAboutUsPageLoaded() {
        try {
            return wait.until(ExpectedConditions.urlContains("about-us"));
        } catch (Exception e) {
            return false;
        }
    }

    public String getTitle() {
        return driver.getTitle();
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    public boolean isLogoDisplayed() {
        try {
            WebElement logoElement = wait.until(ExpectedConditions.visibilityOfElementLocated(logo));
            return logoElement.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public enum HigoServiceSubMenu {
        WIFI_ADVERTISING("WiFi Advertising", "wifi-advertising", "WiFi Advertising"),
        HIGO_SPOT("HIGOspot", "higospot", "HIGOspot"),
        INTEGRATED_DIGITAL_AGENCY("Integrated Digital Agency", "integrated-digital-agency",
                "Integrated Digital Agency"),
        SPECIO_AI("Specio AI", "specio", "Marketing Superpowers, Delivered by AI");

        private final String serviceName;
        private final String expectedUrlPart;
        private final String expectedH1Text;

        HigoServiceSubMenu(String serviceName, String expectedUrlPart, String expectedH1Text) {
            this.serviceName = serviceName;
            this.expectedUrlPart = expectedUrlPart;
            this.expectedH1Text = expectedH1Text;
        }

        public String getServiceName() {
            return serviceName;
        }

        public String getExpectedUrlPart() {
            return expectedUrlPart;
        }

        public String getExpectedH1Text() {
            return expectedH1Text;
        }
    }

    public void selectHigoServiceSubMenu(HigoServiceSubMenu action) {
        try {
            WebElement moreMenu = wait.until(ExpectedConditions.visibilityOfElementLocated(menuHoverServiceHigo));
            new Actions(driver).moveToElement(moreMenu).perform();
            By submenuLocator = By.xpath("//label[contains(@class, 'peer-checked:hidden')]//span[contains(text(), '"
                    + action.getServiceName() + "')]");
            WebElement element = wait.until(ExpectedConditions.elementToBeClickable(submenuLocator));
            element.click();
        } catch (Exception e) {
            throw new RuntimeException("Failed to select action: " + action.getServiceName(), e);
        }
    }

    public void waitForServicePageToLoad(HigoServiceSubMenu service) {
        By h1Locator = By.xpath("//h1[normalize-space()='" + service.getExpectedH1Text() + "']");
        wait.until(ExpectedConditions.visibilityOfElementLocated(h1Locator));
    }
}
