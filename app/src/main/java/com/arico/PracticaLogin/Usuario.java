package com.arico.PracticaLogin;

public class Usuario implements java.io.Serializable {
    private String nombre;
    private String clave;
    private String dni;

    public String getDni () {
        return dni;
    }

    public void setDni (String dni) {
        this.dni = dni;
    }

    public String getClave() {
        return clave;
    }

    public String getNombre() {
        return nombre;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Usuario (String NombreUsuario, String ClaveUsuario) {
        nombre = NombreUsuario;
        clave = ClaveUsuario;
    }
    public Usuario (String NombreUsuario, String ClaveUsuario, String DniUsuario) {
        nombre = NombreUsuario;
        clave = ClaveUsuario;
        dni = DniUsuario;
    }
}
