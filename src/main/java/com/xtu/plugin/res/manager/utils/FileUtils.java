package com.xtu.plugin.res.manager.utils;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.util.Locale;

public class FileUtils {

    @NotNull
    public static String getFileName(@NotNull File file) {
        String fileName = file.getName();
        int dotIndex = fileName.lastIndexOf('.');
        if (dotIndex < 0) return fileName;
        return fileName.substring(0, dotIndex);
    }

    @Nullable
    public static String getExtension(@NotNull File file) {
        String fileName = file.getName();
        int dotIndex = fileName.lastIndexOf('.');
        if (dotIndex < 0) return null;
        return fileName.substring(dotIndex + 1).toLowerCase(Locale.ROOT);
    }

    public static void scanDirectory(File directory, OnScanFileListener scanFileListener) {
        if (directory == null || !directory.isDirectory()) return;
        File[] listFiles = directory.listFiles();
        if (ArrayUtils.isEmpty(listFiles)) return;
        for (File file : listFiles) {
            if (file.isDirectory()) {
                scanDirectory(file, scanFileListener);
            } else {
                scanFileListener.onGetFile(file);
            }
        }
    }

    public interface OnScanFileListener {

        void onGetFile(File file);

    }
}
