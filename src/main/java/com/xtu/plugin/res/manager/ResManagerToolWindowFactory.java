package com.xtu.plugin.res.manager;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.project.DumbAware;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vcs.BranchChangeListener;
import com.intellij.openapi.vfs.VirtualFileManager;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowFactory;
import com.intellij.openapi.wm.ex.ToolWindowManagerListener;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentFactory;
import com.intellij.ui.content.ContentManager;
import com.intellij.util.messages.MessageBusConnection;
import com.xtu.plugin.res.manager.action.CompressAction;
import com.xtu.plugin.res.manager.action.LocateAction;
import com.xtu.plugin.res.manager.action.SearchAction;
import com.xtu.plugin.res.manager.action.SortAction;
import com.xtu.plugin.res.manager.listener.ResManagerToolWindowListener;
import com.xtu.plugin.res.manager.ui.ResManagerRootPanel;
import com.xtu.plugin.res.manager.ui.SortType;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;

public class ResManagerToolWindowFactory implements ToolWindowFactory, DumbAware {

    @Override
    public void createToolWindowContent(@NotNull Project project, @NotNull ToolWindow toolWindow) {
        ContentManager contentManager = toolWindow.getContentManager();
        ContentFactory factory = contentManager.getFactory();
        boolean showSearchBar = false;
        SortType defaultSortType = SortType.SORT_TIME;
        ResManagerRootPanel rootPanel = new ResManagerRootPanel(project, showSearchBar, defaultSortType);
        Content content = factory.createContent(rootPanel, "", true);
        contentManager.addContent(content);
        toolWindow.setAutoHide(false);
        toolWindow.setTitleActions(createActionList(defaultSortType, rootPanel));
        bindListeners(project);
    }

    @NotNull
    private static List<AnAction> createActionList(@NotNull SortType defaultSortType,
                                                   @NotNull ResManagerRootPanel rootPanel) {
        return Arrays.asList(
                new LocateAction(rootPanel),
                new SearchAction(rootPanel),
                new CompressAction(rootPanel),
                new SortAction(defaultSortType, rootPanel));
    }

    private void bindListeners(@NotNull Project project) {
        MessageBusConnection connection = project.getMessageBus().connect();
        ResManagerToolWindowListener listener = new ResManagerToolWindowListener(project);
        connection.subscribe(ToolWindowManagerListener.TOPIC, listener);
        connection.subscribe(VirtualFileManager.VFS_CHANGES, listener);
        connection.subscribe(BranchChangeListener.VCS_BRANCH_CHANGED, listener);
    }
}
