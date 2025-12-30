package com.higo.id.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class HigoBlogPage {
    private WebDriver driver;
    private WebDriverWait wait;
    private Actions actions;

    public HigoBlogPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.actions = new Actions(driver);
    }

    public String getTitle() {
        return driver.getTitle();
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    // Locators
    private By filterAll = By.xpath("//button[normalize-space()='All']");
    private By filterHigoesUpdate = By.xpath("//button[normalize-space()='HIGOes Update']");
    private By filterHangout = By.xpath("//button[normalize-space()='Hangout']");
    private By filterTechSocialMedia = By.xpath("//button[normalize-space()='Tech & Social Media']");

    // Locator for the main blog list container
    private By blogListContainer = By.xpath("//ul[contains(@class, 'grid-flow-row') and contains(@class, 'gap-6')]");

    // Methods
    public void filterBy(String category) {
        By locator;
        switch (category) {
            case "HIGOes Update":
                locator = filterHigoesUpdate;
                break;
            case "Hangout":
                locator = filterHangout;
                break;
            case "Tech & Social Media":
                locator = filterTechSocialMedia;
                break;
            case "All":
            default:
                locator = filterAll;
                break;
        }

        try {
            wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
            List<WebElement> filters = driver.findElements(locator);
            WebElement target = filters.stream().filter(WebElement::isDisplayed).findFirst().orElse(null);

            if (target != null) {
                wait.until(ExpectedConditions.elementToBeClickable(target));
                ((org.openqa.selenium.JavascriptExecutor) driver)
                        .executeScript("arguments[0].scrollIntoView({block: 'center'});", target);
                Thread.sleep(500);
                try {
                    actions.moveToElement(target).pause(200).click(target).perform();
                } catch (Exception e) {
                    ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("arguments[0].click();", target);
                }
            } else if (!filters.isEmpty()) {
                WebElement fallback = filters.get(0);
                ((org.openqa.selenium.JavascriptExecutor) driver)
                        .executeScript("arguments[0].scrollIntoView({block: 'center'});", fallback);
                ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("arguments[0].click();", fallback);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<String> getBlogCardCategories() {
        List<WebElement> lists = driver.findElements(blogListContainer);
        if (lists.isEmpty())
            return Collections.emptyList();

        WebElement mainList = lists.stream().filter(WebElement::isDisplayed).findFirst().orElse(null);
        if (mainList == null)
            return Collections.emptyList();

        List<WebElement> categoryElements = mainList.findElements(
                By.xpath(".//div[contains(@class, 'rounded-full') and contains(@class, 'text-primary')]"));
        return categoryElements.stream().map(WebElement::getText).collect(Collectors.toList());
    }

    public boolean waitForCategoriesToMatch(String category) {
        try {
            return wait.until(d -> {
                List<WebElement> lists = d.findElements(blogListContainer);
                if (lists.isEmpty())
                    return false;

                WebElement mainList = lists.stream().filter(WebElement::isDisplayed).findFirst().orElse(null);
                if (mainList == null)
                    return false;

                List<WebElement> elements = mainList.findElements(
                        By.xpath(".//div[contains(@class, 'rounded-full') and contains(@class, 'text-primary')]"));

                if (elements.isEmpty())
                    return false;

                List<String> visibleCategories = elements.stream()
                        .filter(WebElement::isDisplayed)
                        .map(e -> e.getText().trim())
                        .collect(Collectors.toList());

                if (visibleCategories.isEmpty())
                    return false;

                if (category.equals("All")) {
                    return !visibleCategories.isEmpty();
                }
                return visibleCategories.stream().allMatch(c -> c.equalsIgnoreCase(category));
            });
        } catch (Exception e) {
            return false;
        }
    }
}
