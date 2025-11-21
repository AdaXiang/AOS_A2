package es.unex.aos.mc_menu.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class TipoPlato {
    @Id
    @GeneratedValue
    private Long idTipoPlato;

    private String nombre;

    TipoPlato() {
    }
    public TipoPlato(String nombre) {
        this.nombre = nombre;
    }
    public Long getId() {
        return idTipoPlato;
    }
    public void setId(Long idTipoPlato) {
        this.idTipoPlato = idTipoPlato;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
}
