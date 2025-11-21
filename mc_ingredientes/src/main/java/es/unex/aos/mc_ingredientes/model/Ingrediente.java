package es.unex.aos.mc_ingredientes.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Ingrediente {
    
    @Id
    @GeneratedValue
    private Long id;

    private String nombre;
    private Integer cantidad;
    private Integer cantidadMin;
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getCantidad() {
        return cantidad;
    }
    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Integer getCantidadMin() {
        return cantidadMin;
    }
    public void setCantidadMin(Integer cantidadMin) {
        this.cantidadMin = cantidadMin;
    }
}
