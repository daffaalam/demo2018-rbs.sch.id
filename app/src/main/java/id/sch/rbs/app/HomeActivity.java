package id.sch.rbs.app;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

public class HomeActivity extends AppCompatActivity {

    WebView web_home;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        web_home = findViewById(R.id.web_home);
        web_home.getSettings().setJavaScriptEnabled(true);
        web_home.setWebViewClient(new HomeActivity.CustomWebViewClient());
        web_home.loadUrl("https://rbs.sch.id");
        web_home.setBackgroundColor(Color.TRANSPARENT);
    }

    private class CustomWebViewClient extends WebViewClient {
        @Override
        public void onPageStarted(WebView webview, String url, Bitmap favicon) {
            webview.setVisibility(View.INVISIBLE);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            view.loadUrl("javascript:(function() { " +
                    "document.getElementsByClassName('headerback')[0].style.display='none';" +
                    "document.getElementsByClassName('portofolio')[0].style.display='none';" +
                    "document.getElementsByClassName('blogpost')[0].style.display='none';" +
                    "document.getElementsByClassName('seo')[0].style.display='none';" +
                    "document.getElementsByClassName('welcome')[0].style.display='none';" +
                    "document.getElementsByClassName('scrollup')[0].style.display='none';" +
                    "document.getElementById('kontak-kami').style.display='none';" +
                    "document.getElementById('kategori').style.display='none';" +
                    "document.getElementById('footerback').style.display='none';})()");
            view.setVisibility(View.VISIBLE);
            setTitle(view.getTitle());
            super.onPageFinished(view, url);
        }
    }

    public void artikel_home(View view) {
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
    }

    public void about_home(View view) {
        startActivity(new Intent(getApplicationContext(), AboutActivity.class));
    }

    public void close_home(View view) {
        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog
                .setTitle("KELUAR")
                .setMessage("Apakah Anda yakin ingin keluar?")
                .setPositiveButton("YA", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                })
                .setNegativeButton("TIDAK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                }).create().show();
    }
}
