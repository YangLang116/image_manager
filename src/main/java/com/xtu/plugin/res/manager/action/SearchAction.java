package com.xtu.plugin.res.manager.action;

import com.intellij.icons.AllIcons;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.xtu.plugin.res.manager.ui.ResManagerRootPanel;
import org.jetbrains.annotations.NotNull;

public class SearchAction extends AnAction {

    private final ResManagerRootPanel rootPanel;

    public SearchAction(@NotNull ResManagerRootPanel rootPanel) {
        super(AllIcons.Actions.Replace);
        this.rootPanel = rootPanel;
    }

    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        this.rootPanel.toggleTopBar();
    }
}