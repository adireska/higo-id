
package com.higo.id.tests;

import com.higo.id.base.BaseTest;
import com.higo.id.pages.HigoHomePage;
import com.higo.id.pages.HigoStudyCaseDetailPage;
import com.higo.id.pages.HigoStudyCasePage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class HigoStudyCaseTest extends BaseTest {

    private HigoHomePage homePage;
    private HigoStudyCasePage studyCasePage;

    @BeforeMethod
    public void setupPage() {
        homePage = new HigoHomePage(getDriver());
    }

    @Test(enabled = true, description = "Verify HIGOspot Sudy Case Detail", priority = 1)
    public void testHigoSpotStudyCaseDetail() throws InterruptedException {
        studyCasePage = homePage.clickStudyCase();
        studyCasePage.clickTab(HigoStudyCasePage.StudyCaseTab.HIGO_SPOT);
        HigoStudyCaseDetailPage detailPage = studyCasePage.clickCard("Candi Borobudur");
        Thread.sleep(5000);
        System.out.println("Current URL after click: " + homePage.getCurrentUrl());
        Assert.assertTrue(homePage.getCurrentUrl().contains("case-study"),
                "Failed to navigate to detail page. Current URL: " + homePage.getCurrentUrl());
        String expectedTitle = "Mengenal Lebih Dekat Pengalaman Wisatawan di Candi Borobudur";
        String actualTitle = detailPage.getArticleTitle();
        Assert.assertEquals(actualTitle, expectedTitle, "Article title mismatch!");
        boolean isImageDisplayed = detailPage.isHeaderImageDisplayed();
        Assert.assertTrue(isImageDisplayed,
                "Header image is not displayed on the Study Case detail page for Candi Borobudur");
    }

    @Test(description = "Verify Ideafest Study Case Detail", priority = 2)
    public void testIdeafestStudyCaseDetail() throws InterruptedException {
        studyCasePage = homePage.clickStudyCase();
        studyCasePage.clickTab(HigoStudyCasePage.StudyCaseTab.HIGO_SPOT);
        HigoStudyCaseDetailPage detailPage = studyCasePage.clickCard("Ideafest");
        Thread.sleep(5000);
        System.out.println("Current URL after click: " + homePage.getCurrentUrl());
        Assert.assertTrue(homePage.getCurrentUrl().contains("case-study"), "Failed to navigate to detail page.");
        String expectedTitle = "IDEAFEST 2023: Menjangkau dan Mengenal Lebih Dekat Pengunjung";
        String actualTitle = detailPage.getArticleTitle();
        Assert.assertEquals(actualTitle, expectedTitle, "Article title mismatch!");
        boolean isImageDisplayed = detailPage.isHeaderImageDisplayed();
        Assert.assertTrue(isImageDisplayed, "Header image is not displayed on the Study Case detail page for Ideafest");
    }
}
