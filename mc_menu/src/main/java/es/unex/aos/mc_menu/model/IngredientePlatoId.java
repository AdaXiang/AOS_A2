package es.unex.aos.mc_menu.model;

import java.io.Serializable;
import javax.persistence.Embeddable;

@Embeddable
public class IngredientePlatoId implements Serializable {

    private Long idIngrediente;
    private Long idPlato;

    public IngredientePlatoId() {}

    public IngredientePlatoId(Long idIngrediente, Long idPlato) {
        this.idIngrediente = idIngrediente;
        this.idPlato = idPlato;
    }

    public Long getIdIngrediente() {
        return idIngrediente;
    }
    public void setIdIngrediente(Long idIngrediente) {
        this.idIngrediente = idIngrediente;
    }

    public Long getIdPlato() {
        return idPlato;
    }
    public void setIdPlato(Long idPlato) {
        this.idPlato = idPlato;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IngredientePlatoId that = (IngredientePlatoId) o;
        return idIngrediente.equals(that.idIngrediente) && idPlato.equals(that.idPlato);
    }
    @Override
    public int hashCode() {
        return idIngrediente.hashCode() + idPlato.hashCode();
    }
}