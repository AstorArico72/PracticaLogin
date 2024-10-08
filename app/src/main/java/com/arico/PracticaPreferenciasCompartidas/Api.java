package com.arico.PracticaPreferenciasCompartidas;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

public class Api {
    private static SharedPreferences Preferencias;

    public static SharedPreferences Conectar (Context ContextoAplicacion) {
        if (Preferencias == null) {
            //Ésto debería crear un archivo "Api.xml", éso creo.
            Preferencias = ContextoAplicacion.getSharedPreferences("Api", 0);
        }
        return Preferencias;
    }

    public static boolean IniciarSesion (String nombre, String clave, Context ContextoAplicacion) {
        //Ésto consigue las preferencias o crea una nueva instancia.
        Preferencias = Conectar(ContextoAplicacion);

        //Ésto consigue los campos de las preferencias.
        String NombreUsuario = Preferencias.getString("NombreUsuario", "-1");
        String ClaveUsuario = Preferencias.getString("ClaveUsuario", "-1");
        if (NombreUsuario.equals(nombre) && ClaveUsuario.equals(clave)) {
            return true;
        } else {
            return false;
        }
    }

    public static void NuevoUsuario (String nombre, String clave, Context ContextoAplicacion) {
        Usuario Nuevo = new Usuario(nombre, clave);
        Preferencias = Conectar(ContextoAplicacion);
        SharedPreferences.Editor EditorPreferencias = Preferencias.edit();
        //Añadir los datos al archivo de preferencias compartidas.
        EditorPreferencias.putString("NombreUsuario", Nuevo.getNombre());
        EditorPreferencias.putString("ClaveUsuario", Nuevo.getClave());
        //Guardar los cambios.
        EditorPreferencias.commit();
    }

    public static void ActualizarPerfil (String nombre, String clave, String dni, Context ContextoAplicacion) {
        Usuario UsuarioACambiar = new Usuario (nombre, clave, dni);
        Preferencias = Conectar (ContextoAplicacion);
        SharedPreferences.Editor EditorPreferencias = Preferencias.edit();
        EditorPreferencias.putString("NombreUsuario", UsuarioACambiar.getNombre());
        EditorPreferencias.putString("ClaveUsuario", UsuarioACambiar.getClave());
        EditorPreferencias.putString("DniUsuario", UsuarioACambiar.getDni());
        EditorPreferencias.commit();
    }
}
