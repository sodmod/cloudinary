package org.badmus.cloudinary.utils;

import java.util.Objects;
import java.util.UUID;

public class GeneralUtils {

    public static String generateString() {
        return UUID.randomUUID().toString();
    }
    

    public static boolean stringIsNullOrEmpty(String value) {
        return Objects.isNull(value) || value.isEmpty();
    }
}