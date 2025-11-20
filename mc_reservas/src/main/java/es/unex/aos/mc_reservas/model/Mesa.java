package es.unex.aos.mc_reservas.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Mesa {
    
    @Id
    @GeneratedValue
    private long id;

    private Integer nPersonas;
    private Boolean enTerraza;

    public Mesa() {
    }

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public Integer getnPersonas() {
        return nPersonas;
    }
    public void setnPersonas(Integer nPersonas) {
        this.nPersonas = nPersonas;
    }

    public Boolean isEnTerraza() {
        return enTerraza;
    }
    public void setEnTerraza(Boolean enTerraza) {
        this.enTerraza = enTerraza;
    }
}
