package com.xtu.plugin.res.manager.menu;

import com.intellij.openapi.project.Project;
import com.xtu.plugin.res.manager.menu.item.CompressImgItem;
import com.xtu.plugin.res.manager.menu.item.CopyPathItem;
import com.xtu.plugin.res.manager.menu.item.DeleteItem;
import com.xtu.plugin.res.manager.menu.item.OpenFileItem;
import com.xtu.plugin.res.manager.ui.ResManagerListener;
import com.xtu.plugin.res.manager.utils.TinyUtils;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.io.File;

public class ResMenu extends JPopupMenu {

    public ResMenu(@NotNull Project project,
                   @NotNull File imageFile,
                   @NotNull ResManagerListener listener) {
        add(new CopyPathItem(project, imageFile, listener));
        if (TinyUtils.isSupport(imageFile)) {
            add(new CompressImgItem(project, imageFile, listener));
        }
        add(new DeleteItem(project, imageFile, listener));
        add(new OpenFileItem(project, imageFile, listener));
    }
}
