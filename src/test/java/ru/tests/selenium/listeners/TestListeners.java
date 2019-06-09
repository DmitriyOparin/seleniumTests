package ru.tests.selenium.listeners;

import io.qameta.allure.Attachment;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import ru.tests.selenium.app.Application;

public class TestListeners implements ITestListener {
    public void onTestStart(ITestResult iTestResult) {
        System.out.println("test Start");
    }

    public void onTestSuccess(ITestResult iTestResult) {
        System.out.println("test Success");
    }

    public void onTestFailure(ITestResult iTestResult) {
        System.out.println("test Failure");
        String nameTest = iTestResult.getName();
        Application app = (Application) iTestResult.getTestContext().getAttribute("app");
        saveScreenshot(app.takeScreenshot(nameTest));
        }


    @Attachment(value = "Page screenshot", type = "image/png")
    public byte[] saveScreenshot(byte[] screenShot) {
        return screenShot;
    }

    public void onTestSkipped(ITestResult iTestResult) {
        System.out.println("test Skipped");
    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
        System.out.println("test onTestFailedButWithinSuccessPercentage");

    }

    public void onStart(ITestContext iTestContext) {
        System.out.println("test onStart");
    }

    public void onFinish(ITestContext iTestContext) {
        System.out.println("test onFinish");
    }
}
