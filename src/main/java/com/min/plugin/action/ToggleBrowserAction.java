package com.min.plugin.action;

import com.intellij.icons.AllIcons;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.project.Project;
import com.min.plugin.config.MyPluginConfigurable;
import org.jetbrains.annotations.NotNull;

/**
 * @author: Chris
 * @create: 2023-09-03 10:11
 **/
public class ToggleBrowserAction extends AnAction {
    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        // 获取当前项目
        Project project = e.getProject();

        if (project != null) {
            // 获取插件设置
            MyPluginConfigurable pluginConfigurable = new MyPluginConfigurable();

            // 切换启用状态
            pluginConfigurable.setEnableBrowser(!pluginConfigurable.isEnableBrowser());
        }
    }

    @Override
    public void update(@NotNull AnActionEvent e) {
        // 获取当前项目
        Project project = e.getProject();

        if (project != null) {
            // 获取插件设置
            MyPluginConfigurable pluginConfigurable = new MyPluginConfigurable();

            // 设置按钮的文本和图标根据启用状态
            if (pluginConfigurable.isEnableBrowser()) {
                e.getPresentation().setIcon(AllIcons.Actions.Cancel);
                e.getPresentation().setText("Disable Browser");
            } else {
                e.getPresentation().setIcon(AllIcons.Actions.Execute);
                e.getPresentation().setText("Enable Browser");
            }
        }
    }
}

