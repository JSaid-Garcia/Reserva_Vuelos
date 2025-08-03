/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;
import Modelo.Vuelo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author jaine
 */
public class VueloDAOImpl implements VueloDAO {
    @Override
    public void crearVuelo(Vuelo vuelo) {
        String sql = "INSERT INTO vuelos (id, aerolinea, origen, destino, fecha, hora, asientosDisponibles, precio) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, vuelo.getidVuelo());
            stmt.setString(2, vuelo.getAerolinea());
            stmt.setString(3, vuelo.getOrigen());
            stmt.setString(4, vuelo.getDestino());
            stmt.setString(5, vuelo.getFecha());
            stmt.setString(6, vuelo.getHora());
            stmt.setInt(7, vuelo.getAsientosDisponibles());
            stmt.setDouble(8, vuelo.getPrecio());
            stmt.executeUpdate();
            System.out.println("Vuelo creado exitosamente");
        } catch (SQLException e) {
            System.out.println("Error al crear vuelo: " + e.getMessage());
        }
    }

    @Override
    public List<Vuelo> buscarVuelos(String origen, String destino, String fecha) {
        List<Vuelo> vuelos = new ArrayList<>();
        String sql = "SELECT * FROM vuelos WHERE origen = ? AND destino = ? AND fecha = ?";
        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, origen);
            stmt.setString(2, destino);
            stmt.setString(3, fecha);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Vuelo vuelo = new Vuelo(
                    rs.getInt("idVuelo"),
                    rs.getString("aerolinea"),
                    rs.getString("origen"),
                    rs.getString("destino"),
                    rs.getString("fecha"),
                    rs.getString("hora"),
                    rs.getInt("asientosDisponibles"),
                    rs.getDouble("precio")
                );
                vuelos.add(vuelo);
            }
        } catch (SQLException e) {
            System.out.println("Error al buscar vuelos: " + e.getMessage());
        }
        return vuelos;
    }

    @Override
    public void actualizarVuelo(Vuelo vuelo) {
        String sql = "UPDATE vuelos SET aerolinea = ?, origen = ?, destino = ?, fecha = ?, hora = ?, asientosDisponibles = ?, precio = ? WHERE id = ?";
        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, vuelo.getAerolinea());
            stmt.setString(2, vuelo.getOrigen());
            stmt.setString(3, vuelo.getDestino());
            stmt.setString(4, vuelo.getFecha());
            stmt.setString(5, vuelo.getHora());
            stmt.setInt(6, vuelo.getAsientosDisponibles());
            stmt.setDouble(7, vuelo.getPrecio());
            stmt.setInt(8, vuelo.getidVuelo());
            stmt.executeUpdate();
            System.out.println("Vuelo actualizado exitosamente");
        } catch (SQLException e) {
            System.out.println("Error al actualizar vuelo: " + e.getMessage());
        }
    }

    @Override
    public void eliminarVuelo(int idVuelo) {
        String sql = "DELETE FROM vuelos WHERE idVuelo = ?";
        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idVuelo);
            stmt.executeUpdate();
            System.out.println("Vuelo eliminado exitosamente");
        } catch (SQLException e) {
            System.out.println("Error al eliminar vuelo: " + e.getMessage());
        }
    }
}
