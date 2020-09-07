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
                    try {
                        if (network.connectedToInternet()) {
                            Log.d("TAG", "Acceso a internet");
                            intentMain = new Intent(splash.this, MainActivity.class);
                            startActivity(intentMain);
                            finish();
                        } else {
                            Log.d("TAG", "Sin acceso a internet");
                            showDialogInternetFailed();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    Log.d("TAG", "Ninguna red activa");
                    showDialogInternetFailed();
                }

            }

        }, DURATION_SPLASH);
    }


    /** METODOS */

    private void ocultarActionBar() {
        getSupportActionBar().hide();
    }

    // Mostrara un dialogo cuando falle el internet
    private void showDialogInternetFailed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(splash.this);
        builder.setTitle("Mensage");
        builder.setMessage("Verifique su conexión a internet y vuelva intentarlo");

        // Asignamos la accion cuando no haya internet
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finishAffinity(); // Cerramos la aplicacion
            }
        });

        builder.show();
    }
}