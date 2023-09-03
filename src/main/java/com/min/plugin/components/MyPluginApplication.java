package com.min.plugin.components;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.startup.StartupActivity;
import com.min.IDEWebApplication;
import org.jetbrains.annotations.NotNull;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author: Chris
 * @create: 2023-09-03 05:43
 **/
public class MyPluginApplication implements StartupActivity {

    private ConfigurableApplicationContext applicationContext;

    @Override
    public void runActivity(@NotNull Project project) {
        System.out.println("MyPluginApplication runActivity called");

        // 在项目启动时启动 Spring Boot 项目
        startSpringBootProject();
    }

    private void startSpringBootProject() {
        // 启动 Spring Boot 项目的逻辑
        try {
            // 这里的 MySpringBootApplication.class 替换为你的 Spring Boot 应用程序的主类
            applicationContext = SpringApplication.run(IDEWebApplication.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void dispose() {
        // 在插件卸载或关闭时停止 Spring Boot 项目
        stopSpringBootProject();
    }

    private void stopSpringBootProject() {
        // 停止 Spring Boot 项目的逻辑
        if (applicationContext != null) {
            applicationContext.close();
        }
    }
}

