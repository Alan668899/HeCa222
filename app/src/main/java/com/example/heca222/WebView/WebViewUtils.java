package com.example.heca222.WebView;

import android.annotation.SuppressLint;
import android.os.Build;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WebViewUtils {



    /**
     * 初始化WebView
     */
    @SuppressLint("SetJavaScriptEnabled")
    public static void initWebSettings(WebView webView, WebViewClient client) {
        if (webView == null) {
            return;
        }
        //电脑UA,模拟谷歌浏览器
        String userAgent = "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/43.0.2357.134 Safari/537.36";
        WebSettings webSettings = webView.getSettings();
        //设置userAgent为电脑端的ua
//        webSettings.setUserAgentString(userAgent);
        //设置默认为utf-8
        webSettings.setDefaultTextEncodingName("UTF-8");
        //设置WebView中加载页面字体变焦百分比，默认100
        webSettings.setTextZoom(100);
        //属性可以让webview只显示一列，也就是自适应页面大小,不能左右滑动
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.TEXT_AUTOSIZING);
        } else {
            webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NORMAL);
        }

        //设置此属性，可任意比例缩放
        webSettings.setUseWideViewPort(false);
        webSettings.setLoadWithOverviewMode(true);

        //页面支持缩放
        webSettings.setBuiltInZoomControls(false);
        webSettings.setSupportZoom(false);
        webSettings.setDisplayZoomControls(false);
        //设置支持js
        webSettings.setJavaScriptEnabled(true);
        webSettings.setSavePassword(false);
        //设置 缓存模式
        webSettings.setCacheMode(WebSettings.LOAD_DEFAULT);
        // 开启 DOM storage API 功能
        webSettings.setDomStorageEnabled(true);
        //listView,webView中滚动拖动到顶部或者底部时的阴影
        webView.setOverScrollMode(View.OVER_SCROLL_NEVER);

        webView.setVerticalScrollBarEnabled(false);
        webView.setVerticalScrollbarOverlay(false);
        webView.setHorizontalScrollBarEnabled(false);
        webView.setHorizontalScrollbarOverlay(false);

        webView.setWebViewClient(client);
    }



}
