package es.unex.aos.mc_menu.model;

import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class IngredientePlato {

    @EmbeddedId
    private IngredientePlatoId id;

    @ManyToOne(optional = false)
    @MapsId("idPlato") // Vincula el campo idPlato del embeddable con la relación
    @JoinColumn(name = "idPlato")
    @JsonIgnore
    private Plato plato;

    // idIngrediente viene de otro microservicio -> es simple Long dentro del ID
    private Float cantidad;

    public IngredientePlato() {}

    public IngredientePlato(IngredientePlatoId id, Plato plato, Float cantidad) {
        this.id = id;
        this.plato = plato;
        this.cantidad = cantidad;
    }

    public IngredientePlatoId getId() {
        return id;
    }
    public void setId(IngredientePlatoId id) {
        this.id = id;
    }

    public Plato getPlato() {
        return plato;
    }
    public void setPlato(Plato plato) {
        this.plato = plato;
    }

    public Float getCantidad() {
        return cantidad;
    }
    public void setCantidad(Float cantidad) {
        this.cantidad = cantidad;
    }

    public String toString() {
        return "IngredientePlato [id=" + id + ", cantidad=" + cantidad + "]";
    }
}