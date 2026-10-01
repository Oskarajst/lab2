
import java.util.ArrayList;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author oskar
 */
public class Caso {
       private String nombre;
    private String codigo;
    private String detective;
    private Ubicacion[] ubicaciones;
    private ArrayList<Pista> pistas;

    public Caso(String nombre, String codigo, String detective){
        this.nombre = nombre;
        this.codigo = codigo;
        this.detective = detective;
        this.ubicaciones = new Ubicacion[5];
        this.pistas = new ArrayList<Pista>();
    }

    public String getNombre() {
        return nombre;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getDetective() {
        return detective;
    }

    public boolean registrarUbicacion(int posicion, Ubicacion ubicacion){
        if(posicion >= 0 && posicion < ubicaciones.length){
            if(ubicaciones[posicion] != null){
                System.out.println("Ya existe una ubicacion en esa posicion");
                return false;
            }
            ubicaciones[posicion] = ubicacion;
            return true;
        }
        return false;
    }

    public Ubicacion obtenerUbicacion(int posicion){
        if(posicion >= 0 && posicion < ubicaciones.length){
            return ubicaciones[posicion];
        }
        return null;
    }

    public boolean modificarUbicacion(int posicion, int riesgo, String estado){
        if(posicion >= 0 && posicion < ubicaciones.length){
            if(ubicaciones[posicion] == null){
                System.out.println("No existe una ubicacion en esa posicion");
                return false;
            }
            ubicaciones[posicion].setNivelRiesgo(riesgo);
            ubicaciones[posicion].setEstado(estado);
            return true;
        }
        return false;
    }

    public boolean descartarUbicacion(int posicion){
        if(posicion >= 0 && posicion < ubicaciones.length){
            if(ubicaciones[posicion] == null){
                return false;
            }
            ubicaciones[posicion] = null;
            return true;
        }
        return false;
    }

    public boolean registrarPista(Pista pista){
        if(pista != null){
            if(obtenerPista(pista.getCodigo()) != null){
                System.out.println("Ya existe una pista con ese codigo");
                return false;
            }
            pistas.add(pista);
            return true;
        }
        return false;
    }

    public Pista obtenerPista(String codigo){
        for(Pista pista : pistas){
            if(pista.getCodigo().equals(codigo)){
                return pista;
            }
        }
        return null;
    } 

    public boolean modificarPista(String codigo, String descripcion, String tipoEvidencia, int importancia, int confiabilidad){
        Pista pista = obtenerPista(codigo);
        if(pista != null){
            pista.setDescripcion(descripcion);
            pista.setTipoEvidencia(tipoEvidencia);
            pista.setNivelImportante(importancia);
            pista.setNivelConfiable(confiabilidad);
            return true;
        }
        return false;
    }

    public boolean eliminarPista(String codigo){
        Pista pista = obtenerPista(codigo);
        if(pista != null){
            pistas.remove(pista);
            return true;
        }
        return false;
    }

    public ArrayList<Pista> obtenerPistas() {
        return pistas;
    }

    public int cantidadUbicacionesRegistradas(){
        int cantidad = 0;
        for(Ubicacion ubicacion : ubicaciones){
            if(ubicacion != null){
                cantidad++;
            }
        }
        return cantidad;
    }

    public int espaciosDisponibles(){
        int cantidad = 0;
        for(Ubicacion ubicacion : ubicaciones){
            if(ubicacion == null){
                cantidad++;
            }
        }
        return cantidad;
    }

    
    public Ubicacion ubicacionMasPeligrosa(){
        Ubicacion ubicacionMasPeligrosa = null;
        for(Ubicacion ubicacion : ubicaciones){
            if(ubicacion != null){
                if(ubicacionMasPeligrosa == null || ubicacion.getNivelRiesgo() > ubicacionMasPeligrosa.getNivelRiesgo()){
                    ubicacionMasPeligrosa = ubicacion;
                }
            }
        }
        return ubicacionMasPeligrosa;
    }

    public int cantidadPistasRegistradas(){
        return pistas.size();
    }

    public Pista pistaMasImportante(){
        Pista pistaMasImportante = null;
        for(Pista pista : pistas){
            if(pistaMasImportante == null || pista.getNivelImportante() > pistaMasImportante.getNivelImportante()){
                pistaMasImportante = pista;
            }
        }
        return pistaMasImportante;
    }

    public Pista pistaMasConfiable(){
        Pista pistaMasConfiable = null;
        for(Pista pista : pistas){
            if(pistaMasConfiable == null || pista.getNivelConfiable() > pistaMasConfiable.getNivelConfiable()){
                pistaMasConfiable = pista;
            }
        }
        return pistaMasConfiable;
    }

    public double promedioNivelImportancia(){
        if(pistas.size() == 0){
            return 0;
        }
        int suma = 0;
        for(Pista pista : pistas){
            suma += pista.getNivelImportante();
        }
        return (double) suma / pistas.size();
    }

    public boolean mostrarPistas(){
        if(pistas.isEmpty()){
            return false;
        }
        for(Pista pista : pistas){
            System.out.println(pista);
        }
        return true;
    }

    public boolean mostrarUbicaciones(){
        int contador = 1;
        boolean hayUbicaciones = false;
        for(Ubicacion ubicacion : ubicaciones){
    
            if(ubicacion != null){
                System.out.println("POSICION #" + contador);
                System.out.println(ubicacion);
                hayUbicaciones = true;
            }
            contador += 1;
        }
        return hayUbicaciones;
    }

    public int cantidadDeEspaciosDeUbicaciones(){
        int cantidad = 0;
        for(Ubicacion ubicacion : ubicaciones){
            if(ubicacion == null){
                cantidad++;
            }
        }
        return cantidad;
    }
    

}
