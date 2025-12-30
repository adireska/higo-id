package com.higo.id.listeners;

import com.aventstack.extentreports.Status;
import com.higo.id.utils.ExtentManager;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {

    public void onTestStart(ITestResult result) {
        ExtentManager.setTest(ExtentManager.getInstance().createTest(result.getMethod().getMethodName()));
    }

    public void onTestSuccess(ITestResult result) {
        ExtentManager.getTest().log(Status.PASS, "Test Passed");
    }

    public void onTestFailure(ITestResult result) {
        ExtentManager.getTest().log(Status.FAIL, "Test Failed: " + result.getThrowable());
        // Opsional: Screenshot logic disini
    }

    public void onFinish(ITestContext context) {
        ExtentManager.getInstance().flush();
    }

}