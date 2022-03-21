/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clubnautico_229290;

/**
 *
 * @author PC
 */
import Entidades.Socio;
import guis.SociosForm;
import java.util.List;
import persistencia.ConexionBD;
import persistencia.IConexionBD;
import persistencia.ISociosDAO;
import persistencia.SociosDAO;

public class Principal {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        IConexionBD cnBD = new ConexionBD();
        ISociosDAO sociosDAO = new SociosDAO(cnBD);
        
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SociosForm(sociosDAO).setVisible(true);
            }
        });
    }

}
