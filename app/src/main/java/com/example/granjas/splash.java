package com.example.granjas;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.WindowManager;
import android.widget.Toast;

import java.io.IOException;

public class splash extends AppCompatActivity {

    private Network network;
    private final int DURATION_SPLASH = 2000;
    private Intent intentError, intentMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        intentError = new Intent(splash.this, ErrorActivity.class);
        network = new Network();
        ocultarActionBar();

        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void run() {

                if (network.isOnline(getApplicationContext())) {
                    // Redes Activas
                    try {
                        if (network.connectedToInternet()) {
                            // Acesso a internet
                            intentMain = new Intent(splash.this, MainActivity.class);
                            startActivity(intentMain);
                            finish();
                        } else {
                            // Sin acceso a internet
                            startActivity(intentError);
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    // Ninguna red activa
                    startActivity(intentError);
                }

            }

        }, DURATION_SPLASH);
    }


    private void ocultarActionBar() {
        getSupportActionBar().hide();
    }

}