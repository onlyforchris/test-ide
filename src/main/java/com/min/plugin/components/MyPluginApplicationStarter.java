//package org.example.components;
//
//import com.intellij.openapi.startup.StartupActivity;
//import com.min.IDEWebApplication;
//
///**
// * @author: Chris
// * @create: 2023-09-03 05:40
// **/
//public class MyPluginApplicationStarter implements ApplicationStarter {
//    @Override
//    public void premain(String[] args) {
//        // 在插件初始化时启动 Spring Boot 项目
//        startSpringBootProject();
//    }
//
//    @Override
//    public void main(String[] args) {
//        // 可以在这里执行一些其他的初始化工作
//    }
//
//    private void startSpringBootProject() {
//        // 启动 Spring Boot 项目的逻辑
//        try {
//            IDEWebApplication.main(new String[0]);
//        } catch (Exception e) {
//            e.printStackTrace();
//            throw new RuntimeException("Failed to start Spring Boot project", e);
//        }
//    }
//
//    @Override
//    public void postmain(String[] args) {
//        // 在插件卸载或关闭时停止 Spring Boot 项目
//        stopSpringBootProject();
//    }
//
//    private void stopSpringBootProject() {
//        // 停止 Spring Boot 项目的逻辑
//        // 这可以是关闭 Spring Boot 内嵌服务器的方式
//    }
//
//    @Override
//    public String getCommandName() {
//        // 指定命令名称，这将在IDE启动时触发premain方法
//        return "MyPluginCommand";
//    }
//}
//
