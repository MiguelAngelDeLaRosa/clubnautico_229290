/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import java.util.Objects;

/**
 *
 * @author PC
 */
public class Socio {
    private Long id_socio;
    private String nombre;
    private String curp;

    public Socio() {
    }

    public Socio(String nombre, String curp) {
        this.nombre = nombre;
        this.curp = curp;
    }

    public Socio(Long id_socio, String nombre, String curp) {
        this.id_socio = id_socio;
        this.nombre = nombre;
        this.curp = curp;
    }

    public Long getId_socio() {
        return id_socio;
    }

    public void setId_socio(Long id_socio) {
        this.id_socio = id_socio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCurp() {
        return curp;
    }

    public void setCurp(String curp) {
        this.curp = curp;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + Objects.hashCode(this.id_socio);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Socio other = (Socio) obj;
        if (!Objects.equals(this.id_socio, other.id_socio)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Socio{" + "id_socio= " + id_socio + ", nombre= " + nombre + ", curp= " + curp + '}';
    }
    
    
}
