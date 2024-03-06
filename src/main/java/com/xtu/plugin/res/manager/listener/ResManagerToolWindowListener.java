package com.xtu.plugin.res.manager.listener;

import com.intellij.openapi.application.Application;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vcs.BranchChangeListener;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowManager;
import com.intellij.openapi.wm.ex.ToolWindowManagerListener;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentManager;
import com.xtu.plugin.res.manager.adapter.ImageBulkFileAdapter;
import com.xtu.plugin.res.manager.ui.ResManagerRootPanel;
import com.xtu.plugin.res.manager.utils.FileUtils;
import com.xtu.plugin.res.manager.utils.LogUtils;
import com.xtu.plugin.res.manager.utils.StringUtils;
import com.xtu.plugin.res.manager.utils.TinyUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ResManagerToolWindowListener extends ImageBulkFileAdapter implements ToolWindowManagerListener, BranchChangeListener {

    private static final List<String> SUPPORT_IMAGE_FORMAT = Arrays.asList("jpg", "jpeg", "png", "webp", "svg");

    private boolean lastVisibleState;

    public ResManagerToolWindowListener(@NotNull Project project) {
        super(project);
    }

    @Override
    public void stateChanged(@NotNull ToolWindowManager wm) {
        ToolWindow toolWindow = getToolWindow();
        if (toolWindow == null) return;
        boolean isVisible = toolWindow.isVisible();
        if (isVisible == this.lastVisibleState) return;
        this.lastVisibleState = isVisible;
        if (isVisible) {
            LogUtils.info("ResManagerToolWindowListener scanResList");
            scanImageList();
        } else {
            LogUtils.info("ResManagerToolWindowListener clearScanResList");
            clearImageList();
        }
    }

    @Override
    public void branchWillChange(@NotNull String s) {
        setBulkFileEnable(false);
    }

    @Override
    public void branchHasChanged(@NotNull String s) {
        setBulkFileEnable(true);
        scanImageList();
    }

    @Override
    public void onFileAdded(@NotNull Project project, @NotNull VirtualFile addFile) {
        TinyUtils.showTinyDialog(project, addFile);
        super.onFileAdded(project, addFile);
    }

    @Override
    public void scanImageList() {
        String rootPath = project.getBasePath();
        if (StringUtils.isEmpty(rootPath)) return;
        File rootDirectory = new File(rootPath);
        Application application = ApplicationManager.getApplication();
        application.executeOnPooledThread(() -> {
            List<File> resList = new ArrayList<>();
            FileUtils.scanDirectory(rootDirectory, file -> {
                String extension = FileUtils.getExtension(file);
                if (StringUtils.isEmpty(extension)) return;
                if (SUPPORT_IMAGE_FORMAT.contains(extension)) resList.add(file);
            });
            application.invokeLater(() -> {
                ResManagerRootPanel rootPanel = getResManagerRootPanel();
                if (rootPanel == null) return;
                rootPanel.refreshResList(resList);
            });
        });
    }

    private void clearImageList() {
        Application application = ApplicationManager.getApplication();
        application.invokeLater(() -> {
            ResManagerRootPanel rootPanel = getResManagerRootPanel();
            if (rootPanel == null) return;
            rootPanel.cleanResList();
        });
    }

    @Nullable
    private ToolWindow getToolWindow() {
        ToolWindowManager toolWindowManager = ToolWindowManager.getInstance(project);
        return toolWindowManager.getToolWindow("Image Manager");
    }

    @Nullable
    private ResManagerRootPanel getResManagerRootPanel() {
        ToolWindow toolWindow = getToolWindow();
        if (toolWindow == null) return null;
        ContentManager contentManager = toolWindow.getContentManager();
        Content content = contentManager.getContent(0);
        if (content == null) return null;
        return ((ResManagerRootPanel) content.getComponent());
    }
}
