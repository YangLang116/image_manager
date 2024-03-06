package com.xtu.plugin.res.manager.utils;


import java.util.Objects;

public class StringUtils {

    public static boolean equals(String str1, String str2) {
        return Objects.equals(str1, str2);
    }

    public static boolean isEmpty(String str) {
        return str == null || str.isEmpty();
    }
}
