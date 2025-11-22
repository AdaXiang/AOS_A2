package es.unex.aos.mc_reservas.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import es.unex.aos.mc_reservas.model.Reserva;
import es.unex.aos.mc_reservas.repository.ReservaRepository;
import es.unex.aos.mc_reservas.service.ReservaService;

@RestController
@RequestMapping("/reservas")
public class ReservaController {

    @Autowired
    ReservaRepository reservaRepository;

    @Autowired
    ReservaService reservaService;

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
    public ResponseEntity<String> createReserva(@RequestBody Reserva reserva) {
        try {
            Reserva nuevaReserva = reservaService.crearReserva(reserva);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevaReserva.toString());
            
        } catch (IllegalArgumentException e) {
            // Error 400: Datos incorrectos
            return ResponseEntity.badRequest().body(e.getMessage());
            
        } catch (IllegalStateException e) {
            // Error 409: Conflicto (Mesa ocupada)
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
            
        } catch (Exception e) {
            // Error 500: Cualquier otra cosa
            return ResponseEntity.internalServerError().body("Error interno: " + e.getMessage());
        }
    }

    // PUT /reservas/{id}
    @PutMapping("/{id}")
    public ResponseEntity<String> updateReserva(@PathVariable Long id, @RequestBody Reserva reservaDetails) {
        try {
            Reserva reservaActualizada = reservaService.actualizarReserva(id, reservaDetails);
            return ResponseEntity.ok(reservaActualizada.toString());

        } catch (IllegalArgumentException e) {
            // Error 404 o 400: No existe la reserva o datos inválidos
            return ResponseEntity.notFound().build(); // O badRequest() según prefieras

        } catch (IllegalStateException e) {
            // Error 409: Conflicto de horarios
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());

        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error interno: " + e.getMessage());
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