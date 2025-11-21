package es.unex.aos.mc_menu.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Menu {
    @Id
    @GeneratedValue
    private Long idMenu;

    private String name;
    private Float precio;

    Menu() {
    }

    public Menu(String name, Float precio) {
        this.name = name;
        this.precio = precio;
    }

    public Long getIdMenu() {
        return idMenu;
    }
    public void setIdMenu(Long idMenu) {
        this.idMenu = idMenu;
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
    
}
