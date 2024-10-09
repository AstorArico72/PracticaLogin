package com.arico.PracticaLogin;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Api {
    private static SharedPreferences Preferencias;

    /*
    public static SharedPreferences Conectar (Context ContextoAplicacion) {
        if (Preferencias == null) {
            //Ésto debería crear un archivo "Api.xml", éso creo.
            Preferencias = ContextoAplicacion.getSharedPreferences("Api", 0);
        }
        return Preferencias;
    }
    */

    public static boolean IniciarSesion (String nombre, String clave, Context ContextoAplicacion) {
        try {
            FileInputStream EntradaArchivo = new FileInputStream(new File(ContextoAplicacion.getFilesDir(), "Usuario.txt"));
            BufferedInputStream buffer = new BufferedInputStream(EntradaArchivo);
            ObjectInputStream EntradaObjeto = new ObjectInputStream(buffer);
            Usuario UsuarioConClave = (Usuario)EntradaObjeto.readObject();
            String NombreUsuario = UsuarioConClave.getNombre();
            String ClaveUsuario = UsuarioConClave.getClave();
            if (NombreUsuario.equals(nombre) && ClaveUsuario.equals(clave)) {
                return true;
            } else {
                return false;
            }
        } catch (FileNotFoundException e) {
            Toast.makeText(ContextoAplicacion, "Archivo no encontrado.", Toast.LENGTH_LONG).show();
            throw new RuntimeException(e);
        } catch (IOException e) {
            Toast.makeText(ContextoAplicacion, "Error de I/O.", Toast.LENGTH_LONG).show();
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            Toast.makeText(ContextoAplicacion, "Error de tipo.", Toast.LENGTH_LONG).show();
            throw new RuntimeException(e);
        }
    }

    public static void NuevoUsuario (String nombre, String clave, Context ContextoAplicacion) throws RuntimeException {
        Usuario Nuevo = new Usuario(nombre, clave);
        try {
            FileOutputStream SalidaArchivo = new FileOutputStream(new File(ContextoAplicacion.getFilesDir(), "Usuario.txt"));
            BufferedOutputStream buffer = new BufferedOutputStream(SalidaArchivo);
            ObjectOutputStream SalidaObjeto = new ObjectOutputStream(buffer);
            SalidaObjeto.writeObject (Nuevo);
            buffer.flush();
            SalidaObjeto.close();
        } catch (FileNotFoundException e) {
            Toast.makeText(ContextoAplicacion, "Archivo no encontrado.", Toast.LENGTH_LONG).show();
            throw new RuntimeException(e);
        } catch (IOException e) {
            Toast.makeText(ContextoAplicacion, "Error de I/O.", Toast.LENGTH_LONG).show();
            throw new RuntimeException(e);
        }
    }

    public static void ActualizarPerfil (String nombre, String clave, String dni, Context ContextoAplicacion) {
        Usuario UsuarioACambiar = new Usuario (nombre, clave, dni);
        try {
            FileOutputStream SalidaArchivo = new FileOutputStream(new File(ContextoAplicacion.getFilesDir(), "Usuario.txt"));
            BufferedOutputStream buffer = new BufferedOutputStream(SalidaArchivo);
            ObjectOutputStream SalidaObjeto = new ObjectOutputStream(buffer);
            SalidaObjeto.writeObject (UsuarioACambiar);
            buffer.flush();
            SalidaObjeto.close();
        } catch (FileNotFoundException e) {
            Toast.makeText(ContextoAplicacion, "Archivo no encontrado.", Toast.LENGTH_LONG).show();
            throw new RuntimeException(e);
        } catch (IOException e) {
            Toast.makeText(ContextoAplicacion, "Error de I/O.", Toast.LENGTH_LONG).show();
            throw new RuntimeException(e);
        }
    }
}
