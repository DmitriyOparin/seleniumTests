package ru.tests.selenium.app;

import com.google.common.io.Files;

import java.io.File;
import java.io.IOException;

public class Screenshot {
    private String separator = File.separator;
    private String commonPath = "src" + separator + "screenshots" + separator;

    public Screenshot(File screen, String nameTest) {
        File nameScreen = new File(commonPath + nameTest + "_" + System.currentTimeMillis() + ".png");
        try {
            Files.copy(screen, nameScreen);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
