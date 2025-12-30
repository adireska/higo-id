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
    private By nameInput = By.xpath("//input[@placeholder='ex.Clara']");
    private By emailInput = By.xpath("//input[@placeholder='clara@gmail.com']");
    private By phoneInput = By.xpath("//input[@placeholder='+628******']");
    private By companyInput = By.xpath("//input[@placeholder='ex.HIGO']");
    private By selectLayanan = By.xpath("//select[@name='service']");
    private By messageInput = By.xpath("//textarea[@placeholder='Tulis pesan kamu']");
    private By submitButton = By.xpath("//button[normalize-space()='Submit']");
    private By errorMessages = By.xpath("//form//p[contains(@class, 'text-red-500')]");

    public HigoContactPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void fillName(String name) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(nameInput)).sendKeys(name);
    }

    public void fillEmail(String email) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(emailInput)).sendKeys(email);
    }

    public void fillPhone(String phone) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(phoneInput)).sendKeys(phone);
    }

    public void fillCompany(String company) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(companyInput)).sendKeys(company);
    }

    public void selectService(String service) {
        WebElement selectElement = wait.until(ExpectedConditions.visibilityOfElementLocated(selectLayanan));
        org.openqa.selenium.support.ui.Select select = new org.openqa.selenium.support.ui.Select(selectElement);
        select.selectByVisibleText(service);
    }

    public void fillMessage(String message) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(messageInput)).sendKeys(message);
    }

    public void clickSubmit() {
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(submitButton));
        ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", button);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        button.click();
    }

    public java.util.List<String> getErrorMessages() {
        java.util.List<WebElement> errors = driver.findElements(errorMessages);
        java.util.ArrayList<String> errorTexts = new java.util.ArrayList<>();
        for (WebElement error : errors) {
            if (error.isDisplayed()) {
                errorTexts.add(error.getText());
            }
        }
        return errorTexts;
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
