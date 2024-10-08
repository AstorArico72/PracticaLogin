package com.arico.PracticaPreferenciasCompartidas;

import android.app.Application;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;
import static com.arico.PracticaPreferenciasCompartidas.Api.*;

import androidx.annotation.NonNull;

public class MainViewModel extends androidx.lifecycle.AndroidViewModel {
    private android.content.Context ApplicationContext;
    public MainViewModel(@NonNull Application application) {
        super(application);
        this.ApplicationContext = application.getApplicationContext();
    }

    public void Ingresar (String nombre, String clave) {
        //Los dos parámetros de arriba vienen de MainActivity.java
        //Ésto llama a la API.
        if (nombre != null && clave != null) {
            boolean Correcto = false;
            Correcto = IniciarSesion (nombre, clave, ApplicationContext);
            if (!Correcto) {
                Toast.makeText(ApplicationContext, "Usuario o clave incorrectos.", Toast.LENGTH_LONG).show();
            } else {
                Intent intent = new Intent(ApplicationContext, DatosUsuarios.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                ApplicationContext.startActivity(intent);
            }
        }
    }

    public void NuevaCuenta (String nombre, String clave) {
        Log.d ("info", "Nombre de usuario: " + nombre);
        NuevoUsuario(nombre, clave, ApplicationContext);
    }
}
