/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author PC
 */
public class ConexionBD implements IConexionBD {

    final String CADENA_CONEXION = "jdbc:mysql://127.0.0.1/club_nautico";
    final String USUARIO = "root";
    final String CONTRASEÑA = "12345";

    @Override
    public Connection crearConexion() throws SQLException {
        // Establece una conexion a mysql sino puede lanza una excepción
        Connection conexion = DriverManager.getConnection(CADENA_CONEXION, USUARIO, CONTRASEÑA);
        return conexion;
    }

}
