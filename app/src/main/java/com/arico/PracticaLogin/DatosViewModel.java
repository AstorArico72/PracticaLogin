package com.arico.PracticaLogin;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import static com.arico.PracticaLogin.Api.*;
import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

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
        try {
            FileInputStream EntradaArchivo = new FileInputStream(new File(ApplicationContext.getFilesDir(), "Usuario.txt"));
            BufferedInputStream buffer = new BufferedInputStream(EntradaArchivo);
            ObjectInputStream EntradaObjeto = new ObjectInputStream(buffer);
            Usuario UsuarioConClave = (Usuario)EntradaObjeto.readObject();
            MutableUsuario.setValue(UsuarioConClave);
        } catch (FileNotFoundException e) {
            Toast.makeText(ApplicationContext, "Archivo no encontrado.", Toast.LENGTH_LONG).show();
            throw new RuntimeException(e);
        } catch (IOException e) {
            Toast.makeText(ApplicationContext, "Error de I/O.", Toast.LENGTH_LONG).show();
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            Toast.makeText(ApplicationContext, "Error de tipo.", Toast.LENGTH_LONG).show();
            throw new RuntimeException(e);
        }
    }

    public void CambiarUsuario (String nombre, String clave, String dni) {
        Log.d ("info", "Nombre: " + nombre + ", Clave: " + clave + ", DNI: " + dni);
        ActualizarPerfil(nombre, clave, dni, ApplicationContext);
    }
}
