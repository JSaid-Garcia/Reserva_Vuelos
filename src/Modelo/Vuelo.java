/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author jainer Said Garcia
 */
public class Vuelo {

    private int idVuelo;
    private String aerolinea;
    private String origen;
    private String destino;
    private String fecha;
    private String hora;
    private int asientosDisponibles;
    private double precio;

    // Constructor
    public Vuelo(int idVuelo, String aerolinea, String origen, String destino, String fecha, String hora, int asientosDisponibles, double precio) {
        this.idVuelo = idVuelo;
        this.aerolinea = aerolinea;
        this.origen = origen;
        this.destino = destino;
        this.fecha = fecha;
        this.hora = hora;
        this.asientosDisponibles = asientosDisponibles;
        this.precio = precio;
    }

    // Setters y Getters
    public void setidVuelo(int idVuelo) {
        this.idVuelo = idVuelo;
    }

    public int getidVuelo() {
        return idVuelo;
    }

    public void setAerolinea(String aerolinea) {
        this.aerolinea = aerolinea;
    }

    public String getAerolinea() {
        return aerolinea;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public String getOrigen() {
        return origen;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public String getDestino() {
        return destino;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getFecha() {
        return fecha;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getHora() {
        return hora;
    }

    public void setAsientosDisponibles(int asientosDisponibles) {
        this.asientosDisponibles = asientosDisponibles;
    }

    public int getAsientosDisponibles() {
        return asientosDisponibles;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public double getPrecio() {
        return precio;
    }
}
