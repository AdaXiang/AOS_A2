package es.unex.aos.mc_reservas.service;

import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.unex.aos.mc_reservas.model.Reserva;
import es.unex.aos.mc_reservas.repository.ReservaRepository;

@Service
public class ReservaService {

    @Autowired
    private ReservaRepository reservaRepository;

    public Reserva crearReserva(Reserva reserva) throws Exception {
        
        // 1. Validaciones básicas
        if (reserva.getMesa() == null || reserva.getFechaReserva() == null || 
            reserva.getHoraReserva() == null || reserva.getDuracion() == null) {
            throw new IllegalArgumentException("Faltan datos obligatorios (mesa, fecha, hora o duración).");
        }

        // 2. Recuperar las reservas que ya existen para esa mesa en ese día
        List<Reserva> reservasDelDia = reservaRepository.findByMesaIdAndFechaReserva(
                reserva.getMesa().getId(), 
                reserva.getFechaReserva()
        );

        // 3. Calcular el inicio y fin de la NUEVA reserva
        LocalTime inicioNueva = reserva.getHoraReserva().toLocalTime();
        LocalTime finNueva = inicioNueva.plusMinutes(reserva.getDuracion());

        // 4. Iterar para ver si se solapa con alguna existente
        for (Reserva existente : reservasDelDia) {
            LocalTime inicioExistente = existente.getHoraReserva().toLocalTime();
            LocalTime finExistente = inicioExistente.plusMinutes(existente.getDuracion());

            // Lógica de solapamiento
            if (inicioNueva.isBefore(finExistente) && finNueva.isAfter(inicioExistente)) {
                throw new IllegalStateException("La mesa no está disponible. Ya está reservada de " + inicioExistente + " a " + finExistente);
            }
        }
        return reservaRepository.save(reserva);
    }

    public Reserva actualizarReserva(Long id, Reserva reservaDetails) throws Exception {
        // 1. Buscar la reserva existente
        Reserva reserva = reservaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Reserva no encontrada con id: " + id));

        // 2. Actualizar los campos en memoria con los nuevos datos (si vienen)
        if (reservaDetails.getNombreCliente() != null) reserva.setNombreCliente(reservaDetails.getNombreCliente());
        if (reservaDetails.getCorreo() != null) reserva.setCorreo(reservaDetails.getCorreo());
        if (reservaDetails.getTelefono() != null) reserva.setTelefono(reservaDetails.getTelefono());
        if (reservaDetails.getFechaReserva() != null) reserva.setFechaReserva(reservaDetails.getFechaReserva());
        if (reservaDetails.getHoraReserva() != null) reserva.setHoraReserva(reservaDetails.getHoraReserva());
        if (reservaDetails.getDuracion() != null) reserva.setDuracion(reservaDetails.getDuracion());
        if (reservaDetails.getnComensales() != null) reserva.setnComensales(reservaDetails.getnComensales());
        if (reservaDetails.getMesa() != null) reserva.setMesa(reservaDetails.getMesa());

        // 3. Validar disponibilidad (IGUAL QUE EN EL POST, PERO EXCLUYENDO ESTA RESERVA)
        // Recuperamos todas las reservas de esa mesa y día
        List<Reserva> reservasDelDia = reservaRepository.findByMesaIdAndFechaReserva(
                reserva.getMesa().getId(), 
                reserva.getFechaReserva()
        );

        // Calcular el intervalo de tiempo de la reserva YA ACTUALIZADA
        LocalTime inicioNueva = reserva.getHoraReserva().toLocalTime();
        LocalTime finNueva = inicioNueva.plusMinutes(reserva.getDuracion());

        for (Reserva existente : reservasDelDia) {
            if (existente.getIdReserva() == id) {
                continue; 
            }

            LocalTime inicioExistente = existente.getHoraReserva().toLocalTime();
            LocalTime finExistente = inicioExistente.plusMinutes(existente.getDuracion());

            // Comprobamos solapamiento
            if (inicioNueva.isBefore(finExistente) && finNueva.isAfter(inicioExistente)) {
                throw new IllegalStateException("Conflicto al actualizar: La mesa ya está reservada de " 
                        + inicioExistente + " a " + finExistente + " por otro cliente.");
            }
        }

        // 4. Si no hay conflictos con otras reservas, guardar los cambios
        return reservaRepository.save(reserva);
    }
}