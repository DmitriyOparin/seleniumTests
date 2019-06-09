package ru.tests.selenium.dataGenerators;

import org.apache.commons.lang3.RandomStringUtils;

import java.util.Random;

public class TextGenerators {
    public static String randomTitleGeneration(){
        return RandomStringUtils.random(10, true, true);
    }

    public static String randomTextGeneration(){
        return RandomStringUtils.random(64, true, true);
    }

    public static String randomMonkeyTextGeneration(){
        Random rand = new Random();
        return RandomStringUtils.random(rand.nextInt(15), true, true);
    }
}
