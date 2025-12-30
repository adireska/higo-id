package com.higo.id.base;

import com.higo.id.utils.DriverUtils;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

public class BaseTest {

    @BeforeMethod
    @Parameters("baseURL")
    public void setup(String baseURL) {
        DriverUtils.initializeDriver();
        WebDriver driver = DriverUtils.getDriver();
        if (baseURL == null || baseURL.isEmpty()) {
            System.out.println("Warning: baseURL is null or empty");
        }
        driver.get(baseURL);
    }

    @AfterMethod
    public void teardown() {
        DriverUtils.quitDriver();
    }

    public WebDriver getDriver() {
        return DriverUtils.getDriver();
    }

    public void switchToOtherWindow(String originalWindowHandle) {
        for (String windowHandle : getDriver().getWindowHandles()) {
            if (!originalWindowHandle.contentEquals(windowHandle)) {
                getDriver().switchTo().window(windowHandle);
                break;
            }
        }
    }
}
