package com.arico.PracticaPreferenciasCompartidas;

import android.app.Application;
import android.content.SharedPreferences;
import android.util.Log;

import static com.arico.PracticaPreferenciasCompartidas.Api.*;
import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class DatosViewModel extends androidx.lifecycle.AndroidViewModel {
    private MutableLiveData<Usuario> MutableUsuario;
    private android.content.Context ApplicationContext;
    public DatosViewModel(@NonNull Application application) {
        super(application);
        this.ApplicationContext = application.getApplicationContext();
    }

    public LiveData<Usuario> ConseguirUsuario () {
        if (MutableUsuario == null) {
            this.MutableUsuario = new MutableLiveData<>();
            return this.MutableUsuario;
        } else {
            return this.MutableUsuario;
        }
    }

    public void LeerDatos () {
        SharedPreferences Preferencias = Conectar(ApplicationContext);
        String NombreUsuario = Preferencias.getString("NombreUsuario", "-1");
        String ClaveUsuario = Preferencias.getString("ClaveUsuario", "-1");
        String DniUsuario = Preferencias.getString("DniUsuario", "-1");
        Usuario UsuarioConClave = new Usuario(NombreUsuario, ClaveUsuario, DniUsuario);
        MutableUsuario.setValue(UsuarioConClave);
    }

    public void CambiarUsuario (String nombre, String clave, String dni) {
        Log.d ("info", "Nombre: " + nombre + ", Clave: " + clave + ", DNI: " + dni);
        ActualizarPerfil(nombre, clave, dni, ApplicationContext);
    }
}
