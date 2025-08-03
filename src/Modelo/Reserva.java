/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author jainer Said Garcia Gonzalez
 */
public class Reserva {

    private int idReserva;
    private int idUsuario;
    private int idVuelo;
    private String fechaReserva;

    // Constructor
    public Reserva(int idReserva, int idUsuario, int idVuelo, String fechaReserva) {
        this.idReserva = idReserva;
        this.idUsuario = idUsuario;
        this.idVuelo = idVuelo;
        this.fechaReserva = fechaReserva;
    }

    // Setters y Getters
    public void setidReserva(int idReserva) {
        this.idReserva = idReserva;
    }

    public int getidReserva() {
        return idReserva;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdVuelo(int idVuelo) {
        this.idVuelo = idVuelo;
    }

    public int getIdVuelo() {
        return idVuelo;
    }

    public void setFechaReserva(String fechaReserva) {
        this.fechaReserva = fechaReserva;
    }

    public String getFechaReserva() {
        return fechaReserva;
    }

}