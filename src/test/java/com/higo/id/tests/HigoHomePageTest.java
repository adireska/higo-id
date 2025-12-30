package com.higo.id.tests;

import com.higo.id.base.BaseTest;
import com.higo.id.pages.HigoHomePage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class HigoHomePageTest extends BaseTest {

    private HigoHomePage homePage;

    @BeforeMethod
    public void setupPage() {
        homePage = new HigoHomePage(getDriver());
    }

    @Test(description = "Verify Home Page Load and Logo Visibility", priority = 1, enabled = true)
    public void testHomePageLoad() {
        String title = homePage.getTitle();
        System.out.println("Page Title: " + title);

        Assert.assertTrue(
                homePage.getTitle().contains("tech marketing ecosystem")
                        || homePage.getTitle().contains("LifetimeLoyalCustomers"),
                "Title mismatch. Actual title is: [" + homePage.getTitle() + "]");

        // Verify logo
        boolean isLogoVisible = homePage.isLogoDisplayed();
        Assert.assertTrue(isLogoVisible, "Logo should be displayed on the home page");
    }

    @Test(description = "Verify WiFi Advertising Submenu Navigation", priority = 2, enabled = true)
    public void testWifiAdvertisingSubMenu() {
        HigoHomePage.HigoServiceSubMenu service = HigoHomePage.HigoServiceSubMenu.WIFI_ADVERTISING;
        homePage.selectHigoServiceSubMenu(service);
        homePage.waitForServicePageToLoad(service);

        String currentUrl = homePage.getCurrentUrl();
        System.out.println("Testing service: " + service.getServiceName() + " | URL: " + currentUrl);

        Assert.assertTrue(currentUrl.contains(service.getExpectedUrlPart()),
                "URL mismatch for service " + service.getServiceName() +
                        ". Expected to contain: " + service.getExpectedUrlPart() +
                        " but got: " + currentUrl);
    }

    @Test(description = "Verify HIGOspot Submenu Navigation", priority = 3, enabled = true)
    public void testHigoSpotSubMenu() {
        HigoHomePage.HigoServiceSubMenu service = HigoHomePage.HigoServiceSubMenu.HIGO_SPOT;
        homePage.selectHigoServiceSubMenu(service);
        homePage.waitForServicePageToLoad(service);

        String currentUrl = homePage.getCurrentUrl();
        System.out.println("Testing service: " + service.getServiceName() + " | URL: " + currentUrl);

        Assert.assertTrue(currentUrl.contains(service.getExpectedUrlPart()),
                "URL mismatch for service " + service.getServiceName() +
                        ". Expected to contain: " + service.getExpectedUrlPart() +
                        " but got: " + currentUrl);
    }

    @Test(description = "Verify Integrated Digital Agency Submenu Navigation", priority = 4, enabled = true)
    public void testIntegratedDigitalAgencySubMenu() {
        HigoHomePage.HigoServiceSubMenu service = HigoHomePage.HigoServiceSubMenu.INTEGRATED_DIGITAL_AGENCY;
        homePage.selectHigoServiceSubMenu(service);
        homePage.waitForServicePageToLoad(service);

        String currentUrl = homePage.getCurrentUrl();
        System.out.println("Testing service: " + service.getServiceName() + " | URL: " + currentUrl);

        Assert.assertTrue(currentUrl.contains(service.getExpectedUrlPart()),
                "URL mismatch for service " + service.getServiceName() +
                        ". Expected to contain: " + service.getExpectedUrlPart() +
                        " but got: " + currentUrl);
    }

    @Test(description = "Verify Specio AI Submenu Navigation", priority = 5, enabled = true)
    public void testSpecioAiSubMenu() {
        HigoHomePage.HigoServiceSubMenu service = HigoHomePage.HigoServiceSubMenu.SPECIO_AI;
        String originalWindow = getDriver().getWindowHandle();
        homePage.selectHigoServiceSubMenu(service);
        switchToOtherWindow(originalWindow);
        homePage.waitForServicePageToLoad(service);

        String currentUrl = homePage.getCurrentUrl();
        System.out.println("Testing service: " + service.getServiceName() + " | URL: " + currentUrl);

        Assert.assertTrue(currentUrl.contains(service.getExpectedUrlPart()),
                "URL mismatch for service " + service.getServiceName() +
                        ". Expected to contain: " + service.getExpectedUrlPart() +
                        " but got: " + currentUrl);
        getDriver().close();
        getDriver().switchTo().window(originalWindow);
    }

    @Test(description = "Verify About Us Navigation", priority = 6)
    public void testAboutUsNavigation() {
        homePage.clickAboutUs();
        Assert.assertTrue(homePage.isAboutUsPageLoaded(), "Failed to navigate to About Us page");
    }
}
