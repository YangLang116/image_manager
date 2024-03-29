package com.xtu.plugin.res.manager.menu.item;

import com.intellij.openapi.project.Project;
import com.xtu.plugin.res.manager.ui.ResManagerListener;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public abstract class AbstractItem extends JMenuItem implements ActionListener {

    public final Project project;
    public final File imageFile;
    public final ResManagerListener listener;

    public AbstractItem(@NotNull String name,
                        @NotNull Project project,
                        @NotNull File imageFile,
                        @NotNull ResManagerListener listener) {
        super(name);
        this.project = project;
        this.imageFile = imageFile;
        this.listener = listener;
        addActionListener(this);
    }

    @Override
    public abstract void actionPerformed(ActionEvent e);
}
