package es.unex.aos.mc_ingredientes.model;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class SuministroId implements Serializable {

    private Long idProveedor;
    private Long idIngrediente;

    public SuministroId() {
    }

    public SuministroId(Long idProveedor, Long idIngrediente) {
        this.idProveedor = idProveedor;
        this.idIngrediente = idIngrediente;
    }

    public Long getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(Long idProveedor) {
        this.idProveedor = idProveedor;
    }

    public Long getIdIngrediente() {
        return idIngrediente;
    }

    public void setIdIngrediente(Long idIngrediente) {
        this.idIngrediente = idIngrediente;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SuministroId that = (SuministroId) o;
        return Objects.equals(idProveedor, that.idProveedor) &&
               Objects.equals(idIngrediente, that.idIngrediente);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idProveedor, idIngrediente);
    }
}
