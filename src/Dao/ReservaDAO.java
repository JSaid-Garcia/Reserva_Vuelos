/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;
import Modelo.Reserva;
import java.util.List;
/**
 *
 * @author jainer Said Garcia Gonzalez
 */
public interface ReservaDAO {
    void crearReserva(Reserva reserva);
    List<Reserva> listarReservasPorUsuario(int idUsuario);
    void cancelarReserva(int idReserva);
    boolean existeReserva(int idUsuario, int idVuelo);
}