//package org.example.components;
//
//import com.intellij.openapi.components.ApplicationComponent;
//import com.min.IDEWebApplication;
//import org.jetbrains.annotations.NotNull;
//
///**
// * @author: Chris
// * @create: 2023-09-02 18:33
// **/
//public class MyPluginApplicationComponent implements ApplicationComponent {
//
//    @Override
//    public void initComponent() {
//        // 在插件初始化时启动 Spring Boot 项目
//        startSpringBootProject();
//    }
//
//    @Override
//    public void disposeComponent() {
//        // 在插件卸载或关闭时停止 Spring Boot 项目
//        stopSpringBootProject();
//    }
//
//    private void startSpringBootProject() {
//        // 启动 Spring Boot 项目的逻辑
//        try {
//            IDEWebApplication.main(new String[0]);
//        } catch (Exception e) {
//            e.printStackTrace();
//            throw e;
//        }
//    }
//
//    private void stopSpringBootProject() {
//        // 停止 Spring Boot 项目的逻辑
//        // 这可以是关闭 Spring Boot 内嵌服务器的方式
//    }
//
//    @NotNull
//    @Override
//    public String getComponentName() {
//        return "MyPluginApplicationComponent";
//    }
//}
//
