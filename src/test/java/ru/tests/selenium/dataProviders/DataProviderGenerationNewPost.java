package ru.tests.selenium.dataProviders;

import org.testng.ITestContext;
import org.testng.annotations.DataProvider;

import java.util.Map;

public class DataProviderGenerationNewPost {

    @DataProvider
    public static Object[][] generationNewPost(ITestContext context){
        Map getParameters = context.getCurrentXmlTest().getLocalParameters();
        long currentTimeMillis = System.currentTimeMillis();
        String namePost = "namePost_" + currentTimeMillis;
        String textPost = "textPost_" + currentTimeMillis;
        return new Object[][] {
                {getParameters.get("login"), getParameters.get("password"), namePost, textPost},
        };
    }
}
