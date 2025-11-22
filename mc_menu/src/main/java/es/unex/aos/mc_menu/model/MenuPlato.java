package es.unex.aos.mc_menu.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

@Entity
public class MenuPlato {
    
    @EmbeddedId
    private MenuPlatoId id;

    @ManyToOne
    @MapsId("idMenu")
    @JoinColumn(name = "idMenu")
    private Menu menu;

    @ManyToOne
    @MapsId("idPlato")
    @JoinColumn(name = "idPlato")
    private Plato plato;


    MenuPlato() {
    }

    public MenuPlato(MenuPlatoId id, Menu menu, Plato plato) {
        this.id = id;
        this.menu = menu;
        this.plato = plato;
    }

    public MenuPlatoId getId() {
        return id;
    }
    public void setId(MenuPlatoId id) {
        this.id = id;
    }

    public Menu getMenu() {
        return menu;
    }
    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public Plato getPlato() {
        return plato;
    }
    public void setPlato(Plato plato) {
        this.plato = plato;
    }

    public String toString() {
        return "MenuPlato [idMenu=" + id.getIdMenu() + ", idPlato=" + id.getIdPlato() + "]";
    }
}
