/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import Entidades.Socio;
import java.util.List;

/**
 *
 * @author PC
 */
public interface ISociosDAO {
    
    public boolean agregar(Socio socio);
    
    public boolean actualizar(Socio socio);
    
    public boolean eliminar(Long idSocio);
    
    public Socio consultar(Long idSocio);
    
    public List<Socio> consultarTodos();
}
