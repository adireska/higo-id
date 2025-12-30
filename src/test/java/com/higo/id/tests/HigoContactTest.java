package com.higo.id.tests;

import com.higo.id.base.BaseTest;
import com.higo.id.pages.HigoContactPage;
import com.higo.id.pages.HigoHomePage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class HigoContactTest extends BaseTest {

    private HigoContactPage contactPage;

    @BeforeMethod
    public void setupPage() {
        HigoHomePage homePage = new HigoHomePage(getDriver());
        contactPage = homePage.clickHubungiHigo();
    }

    @Test
    public void testContactFormNegative() {
        Assert.assertTrue(contactPage.isPageLoaded(), "Contact page did not load");
        contactPage.clickSubmit();
        List<String> errors = contactPage.getErrorMessages();
        Assert.assertTrue(errors.contains("Isi nama kamu"),
                "Expected error 'Isi nama kamu' not found. Actual: " + errors);
        Assert.assertTrue(errors.contains("Isi email kamu"),
                "Expected error 'Isi email kamu' not found. Actual: " + errors);
        Assert.assertTrue(errors.contains("Isi dengan nomor telpon kamu"),
                "Expected error 'Isi dengan nomor telpon kamu' not found. Actual: " + errors);
        Assert.assertTrue(errors.contains("Isi nama usaha kamu"),
                "Expected error 'Isi nama usaha kamu' not found. Actual: " + errors);
        Assert.assertTrue(errors.contains("Isi dengan pesan kamu"),
                "Expected error 'Isi dengan pesan kamu' not found. Actual: " + errors);
    }

    @Test
    public void testContactFormPositive() {
        Assert.assertTrue(contactPage.isPageLoaded(), "Contact page did not load");
        contactPage.fillName("Test Name");
        contactPage.fillEmail("test@example.com");
        contactPage.fillPhone("+6281286227333");
        contactPage.fillCompany("Test Company");
        contactPage.selectService("HIGOspot");
        contactPage.fillMessage("This is a dummy message for technical testing.");
        contactPage.clickSubmit();
        List<String> errors = contactPage.getErrorMessages();
        Assert.assertTrue(errors.isEmpty(), "Form submission failed with errors: " + errors);
    }
}
