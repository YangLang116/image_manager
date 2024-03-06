package com.xtu.plugin.res.manager.configuration;

import com.intellij.ide.BrowserUtil;
import com.intellij.openapi.options.SearchableConfigurable;
import com.intellij.openapi.project.Project;
import com.xtu.plugin.res.manager.store.StorageEntity;
import com.xtu.plugin.res.manager.store.StorageService;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Objects;

public final class SettingsConfiguration implements SearchableConfigurable {
    private final Project project;
    private JPanel rootPanel;
    private JTextField tinyApiKeyField;
    private JLabel tinyQuestion;

    public SettingsConfiguration(@NotNull Project project) {
        this.project = project;
    }

    @NotNull
    @Override
    public String getId() {
        return getClass().getName();
    }

    @Override
    public String getDisplayName() {
        return "Image Manager";
    }

    @Override
    public JComponent createComponent() {
        //tiny
        tinyQuestion.setText("<html><u>get your api key</u></html>");
        tinyQuestion.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                BrowserUtil.open("https://tinypng.com/developers");
            }
        });
        return rootPanel;
    }

    private StorageEntity getStorageEntity() {
        StorageService storageService = StorageService.getInstance(project);
        return storageService.getState();
    }

    @Override
    public boolean isModified() {
        StorageEntity storageEntity = getStorageEntity();
        return !Objects.equals(storageEntity.tinyApiKey, tinyApiKeyField.getText().trim());

    }

    @Override
    public void reset() {
        StorageEntity storageEntity = getStorageEntity();
        tinyApiKeyField.setText(storageEntity.tinyApiKey);
    }

    @Override
    public void apply() {
        StorageEntity storageEntity = getStorageEntity();
        storageEntity.tinyApiKey = tinyApiKeyField.getText().trim();
    }
}
