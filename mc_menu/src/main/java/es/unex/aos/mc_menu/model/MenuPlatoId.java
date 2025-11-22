package es.unex.aos.mc_menu.model;

import java.io.Serializable;

import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public class MenuPlatoId implements Serializable {

    private Long idMenu;
    private Long idPlato;

    public MenuPlatoId() {}

    public MenuPlatoId(Long idMenu, Long idPlato) {
        this.idMenu = idMenu;
        this.idPlato = idPlato;
    }

    public Long getIdMenu() {
        return idMenu;
    }
    public void setIdMenu(Long idMenu) {
        this.idMenu = idMenu;
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
        MenuPlatoId that = (MenuPlatoId) o;
        return Objects.equals(idMenu, that.idMenu) &&
               Objects.equals(idPlato, that.idPlato);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idMenu, idPlato);
    }
}
