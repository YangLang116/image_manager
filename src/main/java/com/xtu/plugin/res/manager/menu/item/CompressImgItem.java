package com.xtu.plugin.res.manager.menu.item;

import com.intellij.openapi.project.Project;
import com.xtu.plugin.res.manager.ui.ResManagerListener;
import com.xtu.plugin.res.manager.utils.TinyUtils;
import org.jetbrains.annotations.NotNull;

import java.awt.event.ActionEvent;
import java.io.File;
import java.util.List;

public class CompressImgItem extends AbstractItem {

    public CompressImgItem(@NotNull Project project,
                           @NotNull File imageFile,
                           @NotNull ResManagerListener listener) {
        super("Compress Image", project, imageFile, listener);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        final List<File> imageFileList = List.of(imageFile);
        TinyUtils.compressImage(project, imageFileList, (success) -> {
            if (success) listener.reloadResList(imageFileList);
        });
    }
}
