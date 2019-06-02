/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogicaNegocio;

import java.io.Serializable;

/**
 *
 * @author Adriana Herrera
 */
public class Usuario implements Serializable {
    
    private String id;
    private String nombre;
    private String nombreusuario;
    private String contrasena;
    private String rol;

    public Usuario() {
        this.nombreusuario = "";
        this.contrasena = "";
        this.rol = "";
    }
    
    public Usuario(String nombreusuario, String contrasena, String rol) {
        this.nombreusuario = nombreusuario;
        this.contrasena = contrasena;
        this.rol = rol;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombreusuario() {
        return nombreusuario;
    }

    public void setNombreusuario(String nombreusuario) {
        this.nombreusuario = nombreusuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    @Override
    public String toString() {
        return "Usuario{" + "nombreusuario=" + nombreusuario + ", contrasena=" + contrasena + ", rol=" + rol + '}';
    }
}
