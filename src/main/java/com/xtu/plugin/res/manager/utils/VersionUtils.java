package com.xtu.plugin.res.manager.utils;

import com.intellij.ide.plugins.IdeaPluginDescriptor;
import com.intellij.ide.plugins.PluginManagerCore;
import com.intellij.openapi.extensions.PluginId;

public class VersionUtils {

    public static String getPluginVersion() {
        PluginId pluginId = PluginId.getId("com.xtu.plugin.image.manager");
        IdeaPluginDescriptor pluginDescriptor = PluginManagerCore.getPlugin(pluginId);
        if (pluginDescriptor != null) return pluginDescriptor.getVersion();
        return "0.0.0";
    }
}
