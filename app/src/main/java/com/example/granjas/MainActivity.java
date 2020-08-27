package com.example.granjas;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends AppCompatActivity {

    private WebView wvMain;
    private final String URL = "http://c017090b2d.nxcli.net/home/";

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ocultarActionBar();

        wvMain = findViewById(R.id.wvMain);
        wvMain.setWebViewClient(new WebViewClient()); // Convertimos a la aplicacion como un cliente
        wvMain.getSettings().setJavaScriptEnabled(true); // Habilitamos el js
        wvMain.loadUrl(URL);
    }

    private void ocultarActionBar() {
        getSupportActionBar().hide();
    }
}