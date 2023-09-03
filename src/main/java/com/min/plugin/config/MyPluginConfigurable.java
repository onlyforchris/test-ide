package com.min.plugin.config;

import com.intellij.openapi.options.Configurable;
import com.intellij.util.ReflectionUtil;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.lang.reflect.Method;
import java.util.Objects;

/**
 * @author: Chris
 * @create: 2023-09-03 09:26
 **/

public class MyPluginConfigurable implements Configurable {

    private JPanel settingsPanel;
    private JCheckBox enableBrowserCheckBox;
    private static boolean isEnableBrowser = true;

    public MyPluginConfigurable() {
    }

    @Nls(capitalization = Nls.Capitalization.Title)
    @Override
    public String getDisplayName() {
        return "My Plugin Settings";
    }

    @Nullable
    @Override
    public JComponent createComponent() {
        if (settingsPanel == null) {
            settingsPanel = new JPanel();
            settingsPanel.add(enableBrowserCheckBox);
        }
        return settingsPanel;
    }

    @Override
    public boolean isModified() {
        return enableBrowserCheckBox.isSelected() != isEnableBrowser;
    }

    @Override
    public void apply() {
        isEnableBrowser = enableBrowserCheckBox.isSelected();
    }

    @Override
    public void reset() {
        enableBrowserCheckBox.setSelected(isEnableBrowser);
    }

    @Override
    public void disposeUIResources() {
        // 清理资源
        settingsPanel = null;
        enableBrowserCheckBox = null;
        isEnableBrowser = true;
    }


    public boolean isEnableBrowser() {
        return isSupportedJCEF() && isEnableBrowser;
    }

    public void setEnableBrowser(boolean enableBrowser) {
        isEnableBrowser = enableBrowser;
    }

    private boolean isSupportedJCEF() {
        try {
            Method method = ReflectionUtil.getDeclaredMethod(Class.forName("com.intellij.ui.jcef.JBCefApp"),
                    "isSupported");
            return Objects.nonNull(method) && (boolean) method.invoke(true);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}

