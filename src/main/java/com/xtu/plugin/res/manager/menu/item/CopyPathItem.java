package com.xtu.plugin.res.manager.menu.item;

import com.intellij.openapi.ide.CopyPasteManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.MessageType;
import com.xtu.plugin.res.manager.ui.ResManagerListener;
import com.xtu.plugin.res.manager.utils.ToastUtils;
import org.jetbrains.annotations.NotNull;

import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.io.File;

public class CopyPathItem extends AbstractItem {

    public CopyPathItem(@NotNull Project project,
                        @NotNull File imageFile,
                        @NotNull ResManagerListener listener) {
        super("Copy Path", project, imageFile, listener);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String filePath = imageFile.getAbsolutePath();
        StringSelection content = new StringSelection(filePath);
        CopyPasteManager.getInstance().setContents(content);
        ToastUtils.make(project, MessageType.INFO, "copy path success");
    }
}
