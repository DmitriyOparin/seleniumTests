package ru.tests.selenium.app;

import com.google.common.io.Files;

import java.io.File;
import java.io.IOException;

public class Screenshot {

    public static void saveScreenshot(File screen, String nameTest) {
        String separator = File.separator;
        String commonPath = "src" + separator + "screenshots" + separator;

        File nameScreen = new File(commonPath + nameTest + "_" + System.currentTimeMillis() + ".png");
        try {
            Files.copy(screen, nameScreen);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
