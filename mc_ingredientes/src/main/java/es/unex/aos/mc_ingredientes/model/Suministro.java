package es.unex.aos.mc_ingredientes.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

import java.time.LocalDate;

@Entity
public class Suministro {

    @EmbeddedId
    private SuministroId id = new SuministroId();

    // @MapsId hace que este campo comparta el valor con "idProveedor" de la clave compuesta
    @ManyToOne
    @MapsId("idProveedor") 
    @JoinColumn(name = "idProveedor")
    private Proveedor proveedor;

    // @MapsId hace que este campo comparta el valor con "idIngrediente" de la clave compuesta
    @ManyToOne
    @MapsId("idIngrediente")
    @JoinColumn(name = "idIngrediente")
    private Ingrediente ingrediente;

    private Float precio;
    private LocalDate fechaUltimaCompra;
    private Integer cantidad;

    public Suministro() {
    }

    // Constructor con campos
    public Suministro(Proveedor proveedor, Ingrediente ingrediente, Float precio, Integer cantidad) {
        this.proveedor = proveedor;
        this.ingrediente = ingrediente;
        this.precio = precio;
        this.cantidad = cantidad;
        this.id.setIdProveedor(proveedor.getId());
        this.id.setIdIngrediente(ingrediente.getId());
    }

    // Getters y Setters
    public SuministroId getId() { return id; }
    public void setId(SuministroId id) { this.id = id; }

    public Proveedor getProveedor() { return proveedor; }
    public void setProveedor(Proveedor proveedor) { 
        this.proveedor = proveedor;
        if (proveedor != null) this.id.setIdProveedor(proveedor.getId());
    }

    public Ingrediente getIngrediente() { return ingrediente; }
    public void setIngrediente(Ingrediente ingrediente) { 
        this.ingrediente = ingrediente;
        if (ingrediente != null) this.id.setIdIngrediente(ingrediente.getId());
    }

    public Float getPrecio() { return precio; }
    public void setPrecio(Float precio) { this.precio = precio; }

    public LocalDate getFechaUltimaCompra() { return fechaUltimaCompra; }
    public void setFechaUltimaCompra(LocalDate fechaUltimaCompra) { this.fechaUltimaCompra = fechaUltimaCompra; }

    public Integer getCantidad() { return cantidad; }
    public void setCantidad(Integer cantidad) { this.cantidad = cantidad; }
}
