package com.higo.id.tests;

import com.higo.id.base.BaseTest;
import com.higo.id.pages.HigoBlogPage;
import com.higo.id.pages.HigoHomePage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

public class HigoBlogTest extends BaseTest {

    private HigoHomePage homePage;
    private HigoBlogPage blogPage;

    @BeforeMethod
    public void setupPage() {
        homePage = new HigoHomePage(getDriver());
    }

    @DataProvider(name = "blogCategories")
    public Object[][] blogCategories() {
        return new Object[][] {
                { "HIGOes Update" }
        };
    }

    @Test(description = "Verify Blog Page Categories Filter", dataProvider = "blogCategories", priority = 1)
    public void testBlogCategoriesFilter(String category) {
        String originalWindow = getDriver().getWindowHandle();
        blogPage = homePage.clickBlog();

        switchToOtherWindow(originalWindow);

        blogPage.filterBy(category);

        // Wait for categories to update
        boolean matched = blogPage.waitForCategoriesToMatch(category);
        Assert.assertTrue(matched, "Timeout waiting for categories to match: " + category);

        List<String> categories = blogPage.getBlogCardCategories();
        Assert.assertFalse(categories.isEmpty(), "No cards found for category: " + category);

        for (String cardCategory : categories) {
            Assert.assertEquals(cardCategory, category, "Card category mismatch!");
        }
    }

    @Test(description = "Verify 'All' Filter", priority = 2)
    public void testBlogAllFilter() {
        String originalWindow = getDriver().getWindowHandle();
        blogPage = homePage.clickBlog();

        switchToOtherWindow(originalWindow);

        blogPage.filterBy("All");

        List<String> categories = blogPage.getBlogCardCategories();
        Assert.assertFalse(categories.isEmpty(), "No cards found for 'All' category");
    }
}
