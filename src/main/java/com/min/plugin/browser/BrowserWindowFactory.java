package com.min.plugin.browser;//package com.min.stepflowide.browser;
//
//import com.intellij.openapi.diagnostic.Logger;
//import com.intellij.openapi.project.Project;
//import com.intellij.openapi.wm.ToolWindow;
//import com.intellij.openapi.wm.ToolWindowFactory;
//import com.intellij.ui.content.Content;
//import com.intellij.ui.content.ContentFactory;
//import com.intellij.util.ReflectionUtil;
//import com.intellij.util.ui.JBUI;
//import org.jetbrains.annotations.NotNull;
//
//import javax.swing.*;
//import java.lang.reflect.Method;
//import java.util.Objects;
//
///**
// * 工厂
// *
// * @author huangxingguang
// * @date 2019-04-21 14:55
// */
//public class BrowserWindowFactory implements ToolWindowFactory {
//
//    @Override
//    public void createToolWindowContent(@NotNull Project project, @NotNull ToolWindow toolWindow) {
//        ContentFactory contentFactory = ContentFactory.getInstance();
//        Content content = contentFactory.createContent(getBrowser(), "", false);
//        toolWindow.getContentManager().addContent(content);
//    }
//
//    private boolean isSupportedJCEF() {
//        try {
//            Method method = ReflectionUtil.getDeclaredMethod(Class.forName("com.intellij.ui.jcef.JBCefApp"),
//                    "isSupported");
//            return Objects.nonNull(method) && (boolean) method.invoke(null);
//        } catch (Exception e) {
//            return false;
//        }
//    }
//
//    private JComponent getBrowser() {
//
//        try {
//            if (isSupportedJCEF()) {
//                BrowserView browserView = (BrowserView) Class.forName("com.min.stepflowide.browser.JcefBrowser").newInstance();
//                return new Browser(browserView);
//            }
//        } catch (Exception e) {
//            Logger.getInstance(BrowserWindowFactory.class).error(e);
//        }
//
//        JLabel label = new JLabel("JCEF is not supported in running IDE");
//        label.setHorizontalAlignment(SwingConstants.CENTER);
//        label.setVerticalAlignment(SwingConstants.TOP);
//        label.setBorder(JBUI.Borders.emptyTop(10));
//
//        return label;
//    }
//}