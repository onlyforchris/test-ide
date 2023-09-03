package com.min.plugin.listener;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.project.ProjectManagerListener;
import com.min.IDEWebApplication;
import org.jetbrains.annotations.NotNull;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author: Chris
 * @create: 2023-09-03 18:42
 **/
public class MyProjectManagerListener implements ProjectManagerListener {

    private ConfigurableApplicationContext applicationContext;

    private static boolean ideaOpened = false;

    @Override
    public void projectOpened(@NotNull Project project) {
        if (applicationContext == null) {
            // 项目打开时执行的操作
            System.out.println("项目已打开！");
            ideaOpened = true;
            // 在这里启动 IDEWebApplication 或执行其他启动逻辑
            startSpringBootProject();
        }
    }

    @Override
    public void projectClosing(@NotNull Project project) {
        if (ideaOpened && project.isDefault()) {
            // 只在 IDEA 关闭最后一个项目时执行停止操作
            System.out.println("项目已关闭！");
            ideaOpened = false;
            // 在这里停止 IDEWebApplication 或执行其他关闭逻辑
            stopSpringBootProject();
        }
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

    private void stopSpringBootProject() {
        // 停止 Spring Boot 项目的逻辑
        if (applicationContext != null) {
            applicationContext.close();
        }
    }
}


