/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;
import Dao.ReservaDAO;
import Dao.ReservaDAOImpl;
import Dao.UsuarioDAO;
import Dao.UsuarioDAOImpl;
import Dao.VueloDAO;
import Dao.VueloDAOImpl;
import Modelo.Reserva;
import Modelo.Usuario;
import Modelo.Vuelo;
import javax.swing.*;
import java.awt.*;
import java.util.List;
/**
 *
 * @author jaine
 */
public class MainFrame extends JFrame {
    private JTextField origenField, destinoField, fechaField, usuarioIdField, vueloIdField, nombreField, emailField;
    private JTable vuelosTable, reservasTable;
    private VueloDAO vueloDAO;
    private UsuarioDAO usuarioDAO;
    private ReservaDAO reservaDAO;

    public MainFrame() {
        vueloDAO = new VueloDAOImpl();
        usuarioDAO = new UsuarioDAOImpl();
        reservaDAO = new ReservaDAOImpl();
        setTitle("Sistema de Reservas de Vuelo");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panel de búsqueda de vuelos
        JPanel searchPanel = new JPanel();
        searchPanel.setLayout(new GridLayout(4, 2, 10, 10));
        searchPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        searchPanel.add(new JLabel("Origen:"));
        origenField = new JTextField();
        searchPanel.add(origenField);
        searchPanel.add(new JLabel("Destino:"));
        destinoField = new JTextField();
        searchPanel.add(destinoField);
        searchPanel.add(new JLabel("Fecha:"));
        fechaField = new JTextField();
        searchPanel.add(fechaField);
        JButton searchButton = new JButton("Buscar Vuelos");
        searchPanel.add(searchButton);

        // Panel de gestión de usuarios y reservas
        JPanel gestionPanel = new JPanel();
        gestionPanel.setLayout(new GridLayout(5, 2, 10, 10));
        gestionPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        gestionPanel.add(new JLabel("Nombre Usuario:"));
        nombreField = new JTextField();
        gestionPanel.add(nombreField);
        gestionPanel.add(new JLabel("Email:"));
        emailField = new JTextField();
        gestionPanel.add(emailField);
        JButton registrarUsuarioButton = new JButton("Registrar Usuario");
        gestionPanel.add(registrarUsuarioButton);
        gestionPanel.add(new JLabel("ID Usuario:"));
        usuarioIdField = new JTextField();
        gestionPanel.add(usuarioIdField);
        gestionPanel.add(new JLabel("ID Vuelo:"));
        vueloIdField = new JTextField();
        gestionPanel.add(vueloIdField);
        JButton reservarButton = new JButton("Hacer Reserva");
        gestionPanel.add(reservarButton);

        // Tablas
        vuelosTable = new JTable();
        reservasTable = new JTable();
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Vuelos", new JScrollPane(vuelosTable));
        tabbedPane.addTab("Reservas", new JScrollPane(reservasTable));

        // Acción del botón de búsqueda
        searchButton.addActionListener(e -> {
            String origen = origenField.getText().trim();
            String destino = destinoField.getText().trim();
            String fecha = fechaField.getText().trim();
            if (origen.isEmpty() || destino.isEmpty() || fecha.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            try {
                List<Vuelo> vuelos = vueloDAO.buscarVuelos(origen, destino, fecha);
                actualizarTablaVuelos(vuelos);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error al buscar: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Acción del botón de registrar usuario
        registrarUsuarioButton.addActionListener(e -> {
            String nombre = nombreField.getText().trim();
            String email = emailField.getText().trim();
            if (nombre.isEmpty() || email.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Por favor, complete nombre y email.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            try {
                int id = (int) (Math.random() * 1000); 
                Usuario usuario = new Usuario(id, nombre, email);
                usuarioDAO.crearUsuario(usuario);
                JOptionPane.showMessageDialog(this, "Usuario registrado con ID: " + id, "Éxito", JOptionPane.INFORMATION_MESSAGE);
                nombreField.setText("");
                emailField.setText("");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error al registrar usuario: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Acción del botón de reservar
        reservarButton.addActionListener(e -> {
            String idUsuarioStr = usuarioIdField.getText().trim();
            String idVueloStr = vueloIdField.getText().trim();
            if (idUsuarioStr.isEmpty() || idVueloStr.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Por favor, complete ID de usuario y vuelo.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            try {
                int idUsuario = Integer.parseInt(idUsuarioStr);
                int idVuelo = Integer.parseInt(idVueloStr);
                if (reservaDAO.existeReserva(idUsuario, idVuelo)) {
                    JOptionPane.showMessageDialog(this, "Ya existe una reserva para este usuario y vuelo.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                int idReserva = (int) (Math.random() * 1000); // ID temporal
                Reserva reserva = new Reserva(idReserva, idUsuario, idVuelo, "2025-08-03");
                reservaDAO.crearReserva(reserva);
                List<Reserva> reservas = reservaDAO.listarReservasPorUsuario(idUsuario);
                actualizarTablaReservas(reservas);
                JOptionPane.showMessageDialog(this, "Reserva creada con ID: " + idReserva, "Éxito", JOptionPane.INFORMATION_MESSAGE);
                usuarioIdField.setText("");
                vueloIdField.setText("");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "IDs deben ser números.", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error al crear reserva: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        add(searchPanel, BorderLayout.NORTH);
        add(gestionPanel, BorderLayout.SOUTH);
        add(tabbedPane, BorderLayout.CENTER);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void actualizarTablaVuelos(List<Vuelo> vuelos) {
        String[] columnas = {"idVuelo", "Aerolínea", "Origen", "Destino", "Fecha", "Hora", "Asientos", "Precio"};
        Object[][] datos = new Object[vuelos.size()][8];
        for (int i = 0; i < vuelos.size(); i++) {
            Vuelo v = vuelos.get(i);
            datos[i][0] = v.getidVuelo();
            datos[i][1] = v.getAerolinea();
            datos[i][2] = v.getOrigen();
            datos[i][3] = v.getDestino();
            datos[i][4] = v.getFecha();
            datos[i][5] = v.getHora();
            datos[i][6] = v.getAsientosDisponibles();
            datos[i][7] = v.getPrecio();
        }
        vuelosTable.setModel(new javax.swing.table.DefaultTableModel(datos, columnas));
    }

    private void actualizarTablaReservas(List<Reserva> reservas) {
        String[] columnas = {"ID Reserva", "ID Usuario", "ID Vuelo", "Fecha Reserva"};
        Object[][] datos = new Object[reservas.size()][4];
        for (int i = 0; i < reservas.size(); i++) {
            Reserva r = reservas.get(i);
            datos[i][0] = r.getidReserva();
            datos[i][1] = r.getIdUsuario();
            datos[i][2] = r.getIdVuelo();
            datos[i][3] = r.getFechaReserva();
        }
        reservasTable.setModel(new javax.swing.table.DefaultTableModel(datos, columnas));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainFrame());
    }
}

