package com.danryu.translateoffline;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Translate &amp; Belajar — WebView wrapper di sekitar aplikasi HTML offline
 * (app/index.html). Semua data kamus/frasa/pelajaran ada di dalam file HTML,
 * tidak ada panggilan jaringan saat aplikasi berjalan.
 */
public class MainActivity extends Activity {

    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        webView = new WebView(this);
        setContentView(webView);
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setDomStorageEnabled(true); // localStorage untuk favorit, riwayat, progres belajar
        settings.setAllowFileAccess(true);
        webView.setWebViewClient(new WebViewClient());
        // WebChromeClient: tanpa ini alert()/confirm() di WebView tidak tampil
        // (confirm() selalu false) — dipakai fitur Trip (simpan/hapus data).
        webView.setWebChromeClient(new WebChromeClient());
        webView.loadUrl("file:///android_asset/index.html");
    }

    @Override
    public void onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack();
        } else {
            super.onBackPressed();
        }
    }
}
