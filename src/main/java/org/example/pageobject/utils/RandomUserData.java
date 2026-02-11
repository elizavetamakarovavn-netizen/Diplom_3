package org.example.pageobject.utils;

import org.apache.commons.lang3.RandomStringUtils;

public class RandomUserData {

    public static String randomName() {
        return RandomStringUtils.randomAlphabetic(8);
    }

    public static String randomEmail() {
        return RandomStringUtils.randomAlphabetic(10).toLowerCase() + "@mail.ru";
    }

    public static String randomPassword() {
        return RandomStringUtils.randomAlphanumeric(6);
    }
}
