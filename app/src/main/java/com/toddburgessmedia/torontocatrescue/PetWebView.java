package com.toddburgessmedia.torontocatrescue;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.webkit.WebView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PetWebView extends AppCompatActivity {

    @BindView(R.id.petwebview_webview)
    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_petwebview);
        ButterKnife.bind(this);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        String url = getIntent().getStringExtra("url");

        if (webView != null) {
            webView.loadUrl(url);
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
