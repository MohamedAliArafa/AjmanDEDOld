package com.zeowls.ajmanded;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebSettings;
import android.webkit.WebView;

public class WebViewActivity extends AppCompatActivity {

    WebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        mWebView = findViewById(R.id.webView);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mWebView.saveState(outState);
    }


    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {   // replaced in onCreate
        super.onRestoreInstanceState(savedInstanceState);
        mWebView.restoreState(savedInstanceState);
    }

    public void initWebView() {
//        isLoaded = false;

//        JavaScriptInterface jsInterface = new JavaScriptInterface(this);

        // Javascript inabled on webview
        mWebView.getSettings().setJavaScriptEnabled(true);

//        mWebView.addJavascriptInterface(jsInterface, "JSInterface");

        // Other webview options
        mWebView.getSettings().setLoadWithOverviewMode(true);
        mWebView.getSettings().setAppCacheEnabled(true);
        mWebView.getSettings().setDatabaseEnabled(true);
        mWebView.getSettings().setDomStorageEnabled(true);

        // Define url that will open in webview
//        mWebView.loadUrl(PreferenceManager
//                .getDefaultSharedPreferences(getBaseContext())
//                .getString(getString(R.string.pref_url_key), getString(R.string.pref_url_default)));
        mWebView.loadUrl(getIntent().getStringExtra("url"));
        mWebView.getSettings().setGeolocationDatabasePath(this.getFilesDir().getPath());
        mWebView.getSettings().setAllowFileAccess(true);
        mWebView.getSettings().setPluginState(WebSettings.PluginState.ON);
//        mWebView.getSettings().setBuiltInZoomControls(true);
//        mWebView.getSettings().setUseWideViewPort(true);
//        mWebView.setWebViewClient(new MyWebViewClient());
//        mWebView.setWebChromeClient(new MyWebChromeClient());
    }
}
