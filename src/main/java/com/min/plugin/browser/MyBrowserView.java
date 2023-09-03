package com.min.plugin.browser;

import com.intellij.openapi.Disposable;
import com.intellij.openapi.util.Disposer;
import com.intellij.ui.jcef.JBCefBrowser;
import org.cef.CefClient;
import org.cef.browser.CefBrowser;
import org.cef.browser.CefFrame;
import org.cef.handler.CefDisplayHandlerAdapter;
import org.cef.handler.CefLifeSpanHandlerAdapter;
import org.cef.handler.CefLoadHandlerAdapter;

import javax.swing.*;
import java.util.Objects;
import java.util.function.Consumer;

/**
 * @author: Chris
 * @create: 2023-09-02 18:56
 **/
public class MyBrowserView implements BrowserView, Disposable {

    private JBCefBrowser browser;
    private CefBrowser cefBrowser;
    private CefClient cefClient;
    private Consumer<String> urlChangedConsumer;
    private Consumer<Double> progressChangedConsumer;

    public MyBrowserView() {
        // 创建 JCEF 浏览器
        browser = new JBCefBrowser("about:blank");
        cefBrowser = browser.getCefBrowser();
        cefClient = browser.getJBCefClient().getCefClient();

        // 设置浏览器事件处理器
        browser.getJBCefClient().addLifeSpanHandler(new CefLifeSpanHandlerAdapter() {
            @Override
            public boolean onBeforePopup(CefBrowser browser, CefFrame frame, String targetUrl, String targetFrameName) {
                load(targetUrl);
                return true;
            }
        }, cefBrowser);

        browser.getJBCefClient().addDisplayHandler(new CefDisplayHandlerAdapter() {
            @Override
            public void onAddressChange(CefBrowser browser, CefFrame frame, String url) {
                if (Objects.nonNull(urlChangedConsumer)) {
                    urlChangedConsumer.accept(url);
                }
            }
        }, cefBrowser);

        browser.getJBCefClient().addLoadHandler(new CefLoadHandlerAdapter() {
            private volatile double progress = 0.0;

            @Override
            public void onLoadingStateChange(CefBrowser browser, boolean isLoading, boolean canGoBack, boolean canGoForward) {
                if (Objects.nonNull(progressChangedConsumer)) {
                    synchronized (this) {
                        if (isLoading) {
                            // 模拟加载进度
                            if (progress == 0) {
                                progress = 0.1;
                            }
                        } else {
                            progress = 0;
                        }
                        progressChangedConsumer.accept(progress);
                    }
                }
            }
        }, cefBrowser);
    }

    @Override
    public JComponent getBrowser() {
        return browser.getComponent();
    }

    @Override
    public void load(String url) {
        browser.loadURL(url);
    }

    @Override
    public void onUrlChange(Consumer<String> consumer) {
        urlChangedConsumer = Objects.requireNonNull(consumer, "consumer");
    }

    @Override
    public void onProgressChange(Consumer<Double> consumer) {
        progressChangedConsumer = Objects.requireNonNull(consumer, "consumer");
    }

    @Override
    public void back() {
        if (canBack()) {
            cefBrowser.goBack();
        }
    }

    @Override
    public void forward() {
        if (canForward()) {
            cefBrowser.goForward();
        }
    }

    @Override
    public boolean canBack() {
        return cefBrowser.canGoBack();
    }

    @Override
    public boolean canForward() {
        return cefBrowser.canGoForward();
    }

    @Override
    public void openDevTools() {
        browser.openDevtools();
    }

    @Override
    public void executeScript(String script) {
        cefBrowser.executeJavaScript(script, null, 0);
    }

    @Override
    public Type type() {
        return Type.JCEF;
    }

    @Override
    public void dispose() {
        // 释放资源
        cefClient.removeLoadHandler();
        cefBrowser.stopLoad();
        cefBrowser.close(false);
        Disposer.dispose(browser);
    }
}

