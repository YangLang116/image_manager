package com.xtu.plugin.res.manager.ui;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.List;

public interface ResManagerListener {

    void reloadResList(@NotNull List<File> changeFileList);
}
