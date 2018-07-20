package id.sch.rbs.app;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class ContentActivity extends AppCompatActivity {

    WebView web_content;
    ProgressDialog progdia_content;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        progdia_content = new ProgressDialog(this);
        web_content = new WebView(this);
        web_content.getSettings().setJavaScriptEnabled(true);
        web_content.getSettings().setLoadWithOverviewMode(true);
        web_content.getSettings().setUseWideViewPort(true);
        web_content.getSettings().setDomStorageEnabled(true);
        web_content.setOverScrollMode(View.OVER_SCROLL_NEVER);
        web_content.setWebViewClient(new CustomWebViewClient());
        web_content.loadUrl(getIntent().getStringExtra("LINK"));
        setContentView(web_content);
    }

    private class CustomWebViewClient extends WebViewClient {
        @Override
        public void onPageStarted(WebView webview, String url, Bitmap favicon) {
            progdia_content.show();
            progdia_content.setMessage("loading...");
            webview.setVisibility(View.INVISIBLE);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            progdia_content.dismiss();
            view.loadUrl("javascript:(function() { " +
                    "document.getElementsByClassName('headerback')[0].style.display='none';" +
                    "document.getElementsByClassName('welcome')[0].style.display='none';" +
                    "document.getElementById('comments').style.display='none';" +
                    "document.getElementsByClassName('comments')[0].style.display='none';" +
                    "document.getElementsByClassName('sidebutton')[0].style.display='none';" +
                    "document.getElementsByClassName('crumbs')[0].style.display='none';" +
                    "document.getElementsByClassName('share')[0].style.display='none';" +
                    "document.getElementsByClassName('small')[0].style.display='none';" +
                    "document.getElementById('footerback').style.display='none';})()");
            view.setVisibility(View.VISIBLE);
            setTitle(view.getTitle());
            super.onPageFinished(view, url);
        }
    }

}
