/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;
import Modelo.Vuelo;
import java.util.List;
/**
 *
 * @author jainer Said Garcia Gonzalez
 */
public interface VueloDAO {
    void crearVuelo(Vuelo vuelo);
    List<Vuelo> buscarVuelos(String origen, String destino, String fecha);
    void actualizarVuelo(Vuelo vuelo);
    void eliminarVuelo(int idVuelo);
}
