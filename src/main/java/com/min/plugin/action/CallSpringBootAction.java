package com.min.plugin.action;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowManager;
import com.intellij.ui.jcef.JCEFHtmlPanel;
import com.min.plugin.browser.MyBrowserViewSingleton;
import com.min.plugin.browser.BrowserView;

import java.io.File;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * @author: Chris
 * @create: 2023-09-02 18:42
 **/
public class CallSpringBootAction extends AnAction {


    @Override
    public void actionPerformed(AnActionEvent e) {
        // 获取当前项目
        Project project = e.getProject();

        if (project != null) {
            // 获取当前项目的工具窗口管理器
            ToolWindowManager toolWindowManager = ToolWindowManager.getInstance(project);

            // 根据工具窗口的 ID 获取工具窗口实例
            ToolWindow toolWindow = toolWindowManager.getToolWindow("BrowserToolWindow");

            if (toolWindow != null) {
                // 设置工具窗口的图标
//                toolWindow.setIcon(AllIcons.General.Web);

                // 显示工具窗口
                toolWindow.show(() -> {
                    // 在工具窗口内嵌入一个浏览器视图
                    BrowserView browserView = MyBrowserViewSingleton.getInstance();

//                    JComponent browserComponent = browserView.getBrowser();
//                    ContentFactory contentFactory = ContentFactory.SERVICE.getInstance();
//                    Content content = contentFactory.createContent(browserComponent, "Spring Boot Browser", false);
//                    toolWindow.getContentManager().addContent(content);

                    try {

                        URL resourceUrl = CallSpringBootAction.class.getResource("/page/index.html");
                        String resourcePath = resourceUrl != null ? resourceUrl.getPath() : null;

                        if (resourcePath != null) {
                            // 设置浏览器的初始 URL
                            browserView.load(resourcePath);

//                            String jarPagit branch -M mainth = resourceUrl.getPath().substring(5, resourceUrl.getPath().indexOf("!"));
//                            File jarFile = new File(jarPath);
//                            JarFile jf = new JarFile(jarFile);
//                            JarEntry je = jf.getJarEntry("page/index.html");
//
//                            if (je != null) {
//                                File resourceFile = new File(jarFile.getParent(), je.getName());
//                                if (resourceFile.exists()) {
//                                    // 现在你可以使用resourceFile.getAbsolutePath()来获取资源文件的绝对路径
//                                    String absolutePath = resourceFile.getAbsolutePath();
//
//                                    // 设置浏览器的初始 URL
//                                    browserView.load(resourcePath);
//                                }
//                            }

                        }

                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }

                });
            }

        }
    }


}

