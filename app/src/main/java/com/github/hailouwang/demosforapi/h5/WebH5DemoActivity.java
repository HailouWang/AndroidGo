package com.github.hailouwang.demosforapi.h5;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.github.hailouwang.demosforapi.R;

import java.util.HashSet;
import java.util.Set;

public class WebH5DemoActivity extends AppCompatActivity {

    private static final String TAG = "WebH5DemoActivity";

    private EditText mEditText;
    private WebView mWebView;
    private Button mGoto;

    private String H5_URL = "http://sdkgame.ugame.uc.cn/?ch=WM_58969";

    ///////////////////////////////////////////////////////////////////////////

    ///////////////////////////////////////////////////////////////////////////

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_h5_demo);

        mEditText = (EditText) findViewById(R.id.web_h5_url);
        mGoto = (Button) findViewById(R.id.web_url_goto);

        mWebView = (WebView) findViewById(R.id.web_h5_view);
        mWebView.setBackground(new ColorDrawable(0x00000000));
        mWebView.setBackgroundColor(0);
        mWebView.getBackground().setAlpha(0);
        mWebView.getSettings().setJavaScriptEnabled(true);//设置使用够执行JS脚本
        mWebView.getSettings().setBuiltInZoomControls(false);//设置使支持缩放
        mWebView.getSettings().setTextZoom(100);
        mWebView.addJavascriptInterface(new JavaScriptInterfaceDemo(), "JavaScriptInterfaceDemo");
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                Log.d(TAG, WebH5DemoActivity.class.getName() + ",onPageFinished.");
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                Log.d(TAG, WebH5DemoActivity.class.getName() + ",onPageStarted.");
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                Uri uri = Uri.parse(url);
                String scheme = uri.getScheme();
                String host = uri.getHost();

                Toast.makeText(WebH5DemoActivity.this,url,Toast.LENGTH_SHORT).show();

                mEditText.setText(url);

                Log.d(TAG, WebH5DemoActivity.class.getName() + ",shouldOverrideUrlLoading. url:" + url
                        + ",scheme:" + scheme
                        + ",host:" + host);
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                Log.d(TAG, WebH5DemoActivity.class.getName() + ",onReceivedError errorCode:"+errorCode
                +",description:"+description
                +",failingUrl:"+failingUrl);
            }
        });
        mWebView.loadUrl(H5_URL);

        mGoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String URL = String.valueOf(mEditText.getText());
                mWebView.loadUrl(URL);
            }
        });


        Set<String> aaa = new HashSet<>();
        aaa.add("111");
        aaa.add("222");
        getSharedPreferences("temp", Context.MODE_PRIVATE).edit().putStringSet("myset",aaa).apply();
    }
    /**
     * 酷音接口
     */
    public class JavaScriptInterfaceDemo {
        @JavascriptInterface
        public void setRingPageTitle(final String title) {

        }

        @JavascriptInterface
        public void exitRingPage() {

        }
    }
}
