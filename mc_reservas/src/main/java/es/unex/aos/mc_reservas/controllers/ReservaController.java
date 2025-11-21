package es.unex.aos.mc_reservas.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import es.unex.aos.mc_reservas.model.Reserva;
import es.unex.aos.mc_reservas.repository.ReservaRepository;

@RestController
public class ReservaController {

    @Autowired
    ReservaRepository reservaRepository;

    // GET /reservas
    @GetMapping
    public ResponseEntity<Iterable<Reserva>> getReservas() {
        return ResponseEntity.ok(reservaRepository.findAll());
    }

    // GET /reservas/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Reserva> getReserva(@PathVariable Long id) {
        Optional<Reserva> reserva = reservaRepository.findById(id);
        
        if (reserva.isPresent()) {
            return ResponseEntity.ok(reserva.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // POST /reservas
    @PostMapping
    public ResponseEntity<Reserva> createReserva(@RequestBody Reserva reserva) {
        // Guardamos la reserva. 
        // NOTA: El JSON debe incluir el objeto mesa anidado con su ID.
        Reserva nuevaReserva = reservaRepository.save(reserva);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaReserva);
    }

    // PUT /reservas/{id}
    @PutMapping("/{id}")
    public ResponseEntity<Reserva> updateReserva(@PathVariable Long id, @RequestBody Reserva reservaDetails) {
        Optional<Reserva> reservaOptional = reservaRepository.findById(id);

        if (reservaOptional.isPresent()) {
            Reserva reserva = reservaOptional.get();

            // Se actualizan los campos solo si nos envían un valor (no nulo)
            Optional.ofNullable(reservaDetails.getNombreCliente()).ifPresent(reserva::setNombreCliente);
            Optional.ofNullable(reservaDetails.getCorreo()).ifPresent(reserva::setCorreo);
            Optional.ofNullable(reservaDetails.getTelefono()).ifPresent(reserva::setTelefono);
            Optional.ofNullable(reservaDetails.getFechaReserva()).ifPresent(reserva::setFechaReserva);
            Optional.ofNullable(reservaDetails.getHoraReserva()).ifPresent(reserva::setHoraReserva);
            Optional.ofNullable(reservaDetails.getDuracion()).ifPresent(reserva::setDuracion);
            Optional.ofNullable(reservaDetails.getnComensales()).ifPresent(reserva::setnComensales);
            Optional.ofNullable(reservaDetails.getMesa()).ifPresent(reserva::setMesa);

            // Se guarda la reserva actualizada
            Reserva actualizada = reservaRepository.save(reserva);
            return ResponseEntity.ok(actualizada);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // DELETE /reservas/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReserva(@PathVariable Long id) {
        if (reservaRepository.existsById(id)) {
            reservaRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}