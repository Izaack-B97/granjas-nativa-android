package com.example.granjas;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private WebView wvMain;
    private final String URL = "http://c017090b2d.nxcli.net/home/";

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ocultarActionBar();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        wvMain = findViewById(R.id.wvMain);

        // Convertimos a la aplicacion como un cliente
        wvMain.setWebViewClient(new WebViewClient() {
            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                super.onReceivedError(view, request, error);
                showDialogInternetFailed();
            }
        });

        wvMain.getSettings().setJavaScriptEnabled(true); // Habilitamos el js
        wvMain.loadUrl(URL);
    }

    private void ocultarActionBar() {
        getSupportActionBar().hide();
    }

    // Mostrara un dialogo cuando falle el internet
    private void showDialogInternetFailed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Mensage");
        builder.setMessage("Verifique su conexión a internet y vuelva intentarlo");

        // Asignamos la accion cuando no haya internet
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this, "Saliendo de la aplicación", Toast.LENGTH_LONG).show();

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        finishAffinity();
                    }
                }, 1000);
            }
        });

        builder.show();
    }
}