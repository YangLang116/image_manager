package com.xtu.plugin.res.manager.ui;

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.ui.JBColor;
import com.intellij.ui.RoundedLineBorder;
import com.intellij.ui.scale.JBUIScale;
import com.xtu.plugin.res.manager.utils.ImageUtils;
import icons.PluginIcons;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;
import java.io.File;


public class ImageComponent extends JLabel {

    private ImageUtils.ImageInfo imageInfo;

    public ImageComponent(int size) {
        setPreferredSize(new Dimension(size, size));
        setBorder(new RoundedLineBorder(JBColor.border(), JBUIScale.scale(4), JBUIScale.scale(2)));
    }

    public void loadImage(@NotNull File assetImage, int size, @NotNull OnLoadImageListener listener) {
        ApplicationManager.getApplication().executeOnPooledThread(() -> {
            final ImageUtils.ImageInfo info = ImageUtils.loadThumbnail(assetImage, PluginIcons.FAIL_PIC, size);
            flushImage(info, listener);
        });
    }

    private void flushImage(@NotNull ImageUtils.ImageInfo info, @NotNull OnLoadImageListener listener) {
        ApplicationManager.getApplication().invokeLater(() -> {
            listener.loadFinish(info.width, info.height);
            imageInfo = info;
            revalidate();
            repaint();
        });
    }

    @Override
    public void paint(Graphics g) {
        if (imageInfo == null) return;
        Dimension preferredSize = getPreferredSize();
        int startX = (preferredSize.width - imageInfo.dWidth) / 2;
        int startY = (preferredSize.height - imageInfo.dHeight) / 2;
        g.drawImage(imageInfo.image, startX, startY, imageInfo.dWidth, imageInfo.dHeight, this);
    }

    public void dispose() {
        if (this.imageInfo == null) return;
        this.imageInfo.image.flush();
        this.imageInfo = null;
    }

    public interface OnLoadImageListener {
        void loadFinish(int width, int height);
    }
}
