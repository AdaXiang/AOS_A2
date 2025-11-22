package es.unex.aos.mc_reservas.model;

import java.time.LocalDate; // CAMBIO
import java.time.LocalTime; // CAMBIO

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class Reserva {

    @Id
    @GeneratedValue
    private long idReserva;

    @ManyToOne
    @JoinColumn(name = "id_mesa")
    private Mesa mesa;
    
    private String nombreCliente;
    private String correo;
    private String telefono;
    
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate fechaReserva;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss")
    private LocalTime horaReserva;
    
    private Integer duracion;
    private Integer nComensales;
    
    public Reserva() {
    }

    public Reserva(Mesa mesa, String nombreCliente, String correo, String telefono, 
                   LocalDate fechaReserva, LocalTime horaReserva, // Actualizar constructor
                   Integer duracion, Integer nComensales) {
        this.fechaReserva = fechaReserva;
        this.horaReserva = horaReserva;
        this.nombreCliente = nombreCliente;
        this.telefono = telefono;
        this.mesa = mesa;
        this.correo = correo;
        this.duracion = duracion;
        this.nComensales = nComensales;
    }

    // --- GETTERS Y SETTERS ACTUALIZADOS ---
    public long getIdReserva() { return idReserva; }
    public void setIdReserva(long idReserva) { this.idReserva = idReserva; }
    public Mesa getMesa() { return mesa; }
    public void setMesa(Mesa mesa) { this.mesa = mesa; }
    public String getNombreCliente() { return nombreCliente; }
    public void setNombreCliente(String nombreCliente) { this.nombreCliente = nombreCliente; }
    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }
    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }
    
    public LocalDate getFechaReserva() { return fechaReserva; }
    public void setFechaReserva(LocalDate fechaReserva) { this.fechaReserva = fechaReserva; }
    
    public LocalTime getHoraReserva() { return horaReserva; }
    public void setHoraReserva(LocalTime horaReserva) { this.horaReserva = horaReserva; }
    
    public Integer getDuracion() { return duracion; }
    public void setDuracion(Integer duracion) { this.duracion = duracion; }
    public Integer getnComensales() { return nComensales; }
    public void setnComensales(Integer nComensales) { this.nComensales = nComensales; }
}