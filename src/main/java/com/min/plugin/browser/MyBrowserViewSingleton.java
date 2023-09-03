package com.min.plugin.browser;

import java.io.File;

/**
 * @author: Chris
 * @create: 2023-09-03 07:40
 **/
public class MyBrowserViewSingleton {
    private static MyBrowserView instance;

    private MyBrowserViewSingleton() {
        // 私有构造函数，防止外部实例化
    }

    public static synchronized MyBrowserView getInstance() {
        if (instance == null) {
            instance = new MyBrowserView();
        }
        return instance;
    }

}
