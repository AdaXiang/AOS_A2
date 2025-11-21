package es.unex.aos.mc_menu.model;

import java.io.Serializable;

import javax.persistence.Embeddable;

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
    
}
