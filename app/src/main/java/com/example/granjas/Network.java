package com.example.granjas;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import java.io.IOException;

public class Network {
    // Este metodo nos dira si hay alguna red habilitada wifi/datos
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public static boolean isOnline(Context context) {

        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        if (connectivityManager != null) {
            NetworkCapabilities capabilities = connectivityManager.getNetworkCapabilities(connectivityManager.getActiveNetwork());
            if (capabilities != null) {
                if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                    // Datos
                    Log.d("TAG", "Conexion a datos");
                    return true;
                }else{
                    Log.d("TAG", "No existe conexion a datos");
                }
                if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                    Log.d("TAG", "Conexion a wifi");
                    return true;
                }else{
                    Log.d("TAG", "No hay conexion a wifi");
                }
            }
        }

        return false;
    }

    /**
     * Este metodo nos dira si tendremos acceso a internet por medio de un ping
     * a alguno de las grandes empresas que estan agregadas en el metodo, en caso de que se
     * caiga un servicio tenemos otros de respaldo
     * */
    public boolean connectedToInternet() throws InterruptedException, IOException {
        String provider = "ping -c 1 google.com";
        // String provider = "";
        boolean exito = (Runtime.getRuntime().exec (provider).waitFor() == 0);

        // Tenemos un respaldo si se cae google
        if (!exito) {
            String alternet_provider1 = "ping -c 1 facebook.com";
            exito = (Runtime.getRuntime().exec (alternet_provider1).waitFor() == 0);
            // Si se cae el servicio de facebook
            if (!exito) {
                // Hacemos ping a amazon
                String alternet_provider2 = "ping -c 1 amazon.com";
                exito = (Runtime.getRuntime().exec (alternet_provider2).waitFor() == 0);
            }
        }

        return exito;
    }
}
