package com.min.plugin.listener;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.startup.StartupActivity;
import org.jetbrains.annotations.NotNull;

/**
 * @author: Chris
 * @create: 2023-09-03 20:09
 **/
public class MyStartupActivity implements StartupActivity {

    @Override
    public void runActivity(@NotNull Project project) {
        // 在这里编写您的启动逻辑，比如启动ide-web
    }

}
