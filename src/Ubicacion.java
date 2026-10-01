/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author oskar
 */
public class Ubicacion {
    private String codigo;
    private String nombre;
    private String direccion;
    private int nivelRiesgo;
    private String estado; 


    public Ubicacion(String codigo, String nombre, String direccion, int nivelRiesgo, String estado){
        this.codigo = codigo;
        this.nombre = nombre;
        this.direccion = direccion;
        this.nivelRiesgo = nivelRiesgo;
        this.estado = estado;
    }

    public String getCodigo(){
        return codigo;
    }

    public String getNombre(){
        return nombre;
    }

    public String getDireccion(){
        return direccion;
    }

    public int getNivelRiesgo(){
        return nivelRiesgo;
    }

    public String getEstado(){
        return estado;
    }

    public void setNivelRiesgo(int nivel){
        this.nivelRiesgo = nivel;
    }

    public void setEstado(String estado){
        this.estado = estado;
    }

    @Override
    public String toString(){
             return"\nNombre: " + nombre +
           "\nCodigo: " + codigo +
           "\nDireccion: " + direccion + 
           "\nNivel de riesgo: " + nivelRiesgo +
           "\nEstado: " + estado;
        }
    
    
}


