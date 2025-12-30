package com.higo.id.tests;

import com.higo.id.base.BaseTest;
import com.higo.id.pages.HigoContactPage;
import com.higo.id.pages.HigoHomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class HigoContactTest extends BaseTest {

    @Test
    public void testNavigateToContactUs() {
        HigoHomePage homePage = new HigoHomePage(getDriver());

        // Navigate to Contact Page
        HigoContactPage contactPage = homePage.clickHubungiHigo();

        // Verify URL
        Assert.assertTrue(contactPage.isPageLoaded(), "Contact page URL should contain 'contact-us'");
        System.out.println("Contact Page URL verified: " + contactPage.getPageUrl());

        // Verify Form Visibility
        Assert.assertTrue(contactPage.isContactFormDisplayed(), "Contact form (Name Input) should be displayed");
    }
}
