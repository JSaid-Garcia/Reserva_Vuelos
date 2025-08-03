/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;
import Modelo.Reserva;
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
public class ReservaDAOImpl implements ReservaDAO {
    @Override
    public void crearReserva(Reserva reserva) {
        String sql = "INSERT INTO reservas (idReserva, idUsuario, idVuelo, fechaReserva) VALUES (?, ?, ?, ?)";
        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, reserva.getidReserva());
            stmt.setInt(2, reserva.getIdUsuario());
            stmt.setInt(3, reserva.getIdVuelo());
            stmt.setString(4, reserva.getFechaReserva());
            stmt.executeUpdate();
            System.out.println("Reserva creada exitosamente");
        } catch (SQLException e) {
            System.out.println("Error al crear reserva: " + e.getMessage());
        }
    }

    @Override
    public List<Reserva> listarReservasPorUsuario(int idUsuario) {
        List<Reserva> reservas = new ArrayList<>();
        String sql = "SELECT * FROM Rq eservas WHERE id_usuario = ?";
        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idUsuario);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Reserva reserva = new Reserva(
                    rs.getInt("idVuelo"),
                    rs.getInt("idUsuario"),
                    rs.getInt("idVuelo"),
                    rs.getString("fechaReserva")
                );
                reservas.add(reserva);
            }
        } catch (SQLException e) {
            System.out.println("Error al listar reservas: " + e.getMessage());
        }
        return reservas;
    }

    @Override
    public void cancelarReserva(int idReserva) {
        String sql = "DELETE FROM Reservas WHERE idReserva = ?";
        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idReserva);
            stmt.executeUpdate();
            System.out.println("Reserva cancelada exitosamente");
        } catch (SQLException e) {
            System.out.println("Error al cancelar reserva: " + e.getMessage());
        }
    }

    @Override
    public boolean existeReserva(int idUsuario, int idVuelo) {
        String sql = "SELECT COUNT(*) FROM Reservas WHERE idUsuario = ? AND idVuelo = ?";
        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idUsuario);
            stmt.setInt(2, idVuelo);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            System.out.println("Error al verificar reserva: " + e.getMessage());
        }
        return false;
    }
}
