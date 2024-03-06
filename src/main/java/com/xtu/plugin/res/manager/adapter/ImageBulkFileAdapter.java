package com.xtu.plugin.res.manager.adapter;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import org.jetbrains.annotations.NotNull;

import java.io.File;

public abstract class ImageBulkFileAdapter extends BulkFileAdapter {

    public ImageBulkFileAdapter(@NotNull Project project) {
        super(project);
    }

    @Override
    public void onFileAdded(@NotNull Project project, @NotNull VirtualFile addFile) {
        scanImageList();
    }

    @Override
    public void onFileDeleted(@NotNull Project project, @NotNull VirtualFile deleteFile) {
        scanImageList();
    }

    @Override
    public void onFileMove(@NotNull Project project, @NotNull File oldFile, @NotNull VirtualFile newFile) {
        scanImageList();
    }

    @Override
    public void onFileNameChanged(@NotNull Project project, @NotNull VirtualFile virtualFile, @NotNull String oldName, @NotNull String newName) {
        scanImageList();
    }

    public abstract void scanImageList();
}
