package com.min.plugin.toolwindow;

import com.intellij.icons.AllIcons;
import com.intellij.openapi.actionSystem.ActionManager;
import com.intellij.openapi.actionSystem.ActionToolbar;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.DefaultActionGroup;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowFactory;
import com.intellij.ui.components.JBLabel;
import com.intellij.ui.content.ContentFactory;
import com.min.plugin.action.ToggleBrowserAction;
import com.min.plugin.browser.BrowserView;
import com.min.plugin.browser.MyBrowserViewSingleton;
import com.min.plugin.config.MyPluginConfigurable;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.lang.reflect.Method;


/**
 * @author: Chris
 * @create: 2023-09-02 19:03
 **/
public class BrowserToolWindowFactory implements ToolWindowFactory {

    @Override
    public void createToolWindowContent(@NotNull Project project, @NotNull ToolWindow toolWindow) {
        // 获取用户配置
        MyPluginConfigurable pluginConfigurable = new MyPluginConfigurable();

        // 创建工具栏按钮组
        DefaultActionGroup actionGroup = new DefaultActionGroup();
        AnAction toggleAction = new ToggleBrowserAction();
        actionGroup.add(toggleAction);

        // 创建工具栏
        ActionManager actionManager = ActionManager.getInstance();
        ActionToolbar actionToolbar = actionManager.createActionToolbar("BrowserToggleToolbar", actionGroup, true);

        // 创建内容面板
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));

        // 如果浏览器已启用，显示浏览器内容
        if (pluginConfigurable.isEnableBrowser()) {
            toolWindow.setIcon(AllIcons.General.Web);
            JComponent browserComponent = getBrowser(project);
            ContentFactory contentFactory = ContentFactory.SERVICE.getInstance();
            toolWindow.getContentManager().addContent(
                    contentFactory.createContent(browserComponent, "This is Browser Window", false)
            );
        } else {
            // 如果浏览器未启用，显示按钮和空白面板
            contentPanel.add(actionToolbar.getComponent());
            contentPanel.add(new JBLabel("Browser is disabled in plugin settings."));
            toolWindow.getComponent().add(contentPanel);
        }

    }

    private JComponent getBrowser(Project project) {
        BrowserView browserView = MyBrowserViewSingleton.getInstance();
        if (browserView != null) {
            return browserView.getBrowser();
        }
        return null;
    }


}