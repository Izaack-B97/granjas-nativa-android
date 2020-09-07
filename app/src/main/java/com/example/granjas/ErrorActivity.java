package com.example.granjas;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

public class ErrorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_error);
        ocultarActionBar();
        try {
            Thread.sleep(1000);
            showDialogInternetFailed();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // Mostrara un dialogo cuando falle el internet
    private void showDialogInternetFailed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(ErrorActivity.this);
        builder.setTitle("Mensage");
        builder.setMessage("Verifique su conexión a internet y vuelva intentarlo");

        // Asignamos la accion cuando no haya internet
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(ErrorActivity.this, "Saliendo de la aplicación", Toast.LENGTH_LONG).show();

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        finishAffinity();
                    }
                }, 1200);
            }
        });

        builder.show();
    }

    private void ocultarActionBar() {
        getSupportActionBar().hide();
    }
}