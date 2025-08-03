 /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;
import Modelo.Usuario;
import java.util.List;
/**
 *
 * @author jainer Said Garcia Gonzalez
 */
public interface UsuarioDAO {
    void crearUsuario(Usuario usuario);
    List<Usuario> listarUsuarios();
    void actualizarUsuario(Usuario usuario);
    void eliminarUsuario(int idUsuario);
}
