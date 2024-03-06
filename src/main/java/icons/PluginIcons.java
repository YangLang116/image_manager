package icons;

import com.intellij.openapi.util.IconLoader;

import javax.swing.*;
import java.net.URL;

public class PluginIcons {
    public static Icon WINDOW_ICON = IconLoader.getIcon("/icons/icon_window.svg", PluginIcons.class);
    public static URL FAIL_PIC = PluginIcons.class.getResource("/icons/preview_failed.png");
}
