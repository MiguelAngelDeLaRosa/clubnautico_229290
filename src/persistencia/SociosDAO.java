/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import Entidades.Socio;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author PC
 */
public class SociosDAO implements ISociosDAO {
    
    private IConexionBD cn;

    public SociosDAO(IConexionBD cn) {
        this.cn = cn;
    }
    
    
    
    @Override
    public boolean agregar(Socio socio) {
        try {
            // Establece una conexion a mysql sino puede lanza una excepción
            Connection conexion = this.cn.crearConexion();
            
            //Creamos un objeto statement que nos permite enviar codigo sql a la base de datos
            Statement comandoSQL = conexion.createStatement();
            
            String codigoSQL = String.format("INSERT INTO club_nautico.socios (nombre, curp) VALUES ('%S','%S');",
                                             socio.getNombre(),
                                             socio.getCurp());
            
            // Se utiliza para hacer operaciones de modificacion de datos (insert, delete, update)
            int registrosAfectados = comandoSQL.executeUpdate(codigoSQL);
            //Cerramos la conexion para evitar desperdicio de recursos
            conexion.close();
            
            if(registrosAfectados == 1){
                return true;
            } else{
                return false;
            }
            
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return false;
        }
        
    }

    @Override
    public boolean actualizar(Socio socio) {
        try {
            // Establece una conexion a mysql sino puede lanza una excepción
            Connection conexion = this.cn.crearConexion();
            
            //Creamos un objeto statement que nos permite enviar codigo sql a la base de datos
            Statement comandoSQL = conexion.createStatement();
            
            String codigoSQL = String.format("Update club_nautico.socios set nombre = '%s', curp ='%s' WHERE id_socio = %d;;",
                                             socio.getNombre(),
                                             socio.getCurp(),
                                             socio.getId_socio());
            
            // Se utiliza para hacer operaciones de modificacion de datos (insert, delete, update)
            int registrosAfectados = comandoSQL.executeUpdate(codigoSQL);
            //Cerramos la conexion para evitar desperdicio de recursos
            conexion.close();
            
            if(registrosAfectados == 1){
                return true;
            } else{
                return false;
            }
            
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean eliminar(Long idSocio) {
        try {
            // Establece una conexion a mysql sino puede lanza una excepción
            Connection conexion = this.cn.crearConexion();
            
            //Creamos un objeto statement que nos permite enviar codigo sql a la base de datos
            Statement comandoSQL = conexion.createStatement();
            
            String codigoSQL = String.format("Delete from club_nautico.socios Where id_socio = %d;",
                                             idSocio);
            
            // Se utiliza para hacer operaciones de modificacion de datos (insert, delete, update)
            int registrosAfectados = comandoSQL.executeUpdate(codigoSQL);
            //Cerramos la conexion para evitar desperdicio de recursos
            conexion.close();
            
            if(registrosAfectados == 1){
                return true;
            } else{
                return false;
            }
            
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return false;
        }
    }

    @Override
    public Socio consultar(Long idSocio) {
        Socio socio = null;
        try {
            // Establece una conexion a mysql sino puede lanza una excepción
            Connection conexion = this.cn.crearConexion();
            
            //Creamos un objeto statement que nos permite enviar codigo sql a la base de datos
            Statement comandoSQL = conexion.createStatement();
            
            String codigoSQL = String.format("Select id_socio, nombre, curp From socios;"," WHERE id_socio = %d",
                                             idSocio);
            
            // Se utiliza para hacer cosultas (select)
            ResultSet resultados = comandoSQL.executeQuery(codigoSQL);
            
            // MIENTRAS HAYA RESULTADOS QUE LEER.. LOS LEEMOS
            if(resultados.next()){
                Long id = resultados.getLong("id_socio");
                String nombre = resultados.getString("nombre");
                String curp = resultados.getString("curp");
                socio = new Socio(idSocio, nombre, curp);
            }
            
            //Cerramos la conexion para evitar desperdicio de recursos
            conexion.close();
            
            return socio;
            
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return socio;
        }
    }

    @Override
    public List<Socio> consultarTodos() {
        List<Socio> listaSocios = new ArrayList<>();
        try {
            // Establece una conexion a mysql sino puede lanza una excepción
            Connection conexion = this.cn.crearConexion();
            
            //Creamos un objeto statement que nos permite enviar codigo sql a la base de datos
            Statement comandoSQL = conexion.createStatement();
            
            String codigoSQL = String.format("Select id_socio, nombre, curp From socios;");
            
            // Se utiliza para hacer cosultas (select)
            ResultSet resultados = comandoSQL.executeQuery(codigoSQL);
            
            // MIENTRAS HAYA RESULTADOS QUE LEER.. LOS LEEMOS
            while(resultados.next()){
                Long idSocio = resultados.getLong("id_socio");
                String nombre = resultados.getString("nombre");
                String curp = resultados.getString("curp");
                Socio socio = new Socio(idSocio, nombre, curp);
                listaSocios.add(socio);
            }
            
            //Cerramos la conexion para evitar desperdicio de recursos
            conexion.close();
            
            return listaSocios;
            
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return listaSocios;
        }
    }
    
    
}
