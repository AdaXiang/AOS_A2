package es.unex.aos.mc_menu.model;

public class IngredienteDto {
    private Long id;
    private String nombre;
    private Integer cantidad;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public Integer getCantidad() { return cantidad; }
    public void setCantidad(Integer cantidad) { this.cantidad = cantidad; }
}
