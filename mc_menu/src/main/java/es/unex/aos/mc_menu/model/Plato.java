package es.unex.aos.mc_menu.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.CascadeType;
import java.util.List;

@Entity
public class Plato {
    @Id
    @GeneratedValue
    private Long idPlato;

    private String name;
    private Float precio;

    @ManyToOne
    @JoinColumn(name = "idTipoPlato")
    private TipoPlato tipoPlato;

    @OneToMany(mappedBy = "plato", fetch = FetchType.LAZY)
    private List<IngredientePlato> ingredientes;


    Plato() {
    }

    public Plato(String name, Float precio, TipoPlato tipoPlato) {
        this.name = name;
        this.precio = precio;
        this.tipoPlato = tipoPlato;
    }

    public Long getId() {
        return idPlato;
    }
    public void setId(Long idPlato) {
        this.idPlato = idPlato;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Float getPrecio() {
        return precio;
    }
    public void setPrecio(Float precio) {
        this.precio = precio;
    }
    public TipoPlato getTipoPlato() {
        return tipoPlato;
    }
    public void setTipoPlato(TipoPlato tipoPlato) {
        this.tipoPlato = tipoPlato;
    }

    public List<IngredientePlato> getIngredientes() {
    return ingredientes;
}
    public void setIngredientes(List<IngredientePlato> ingredientes) {
        this.ingredientes = ingredientes;
    }
}