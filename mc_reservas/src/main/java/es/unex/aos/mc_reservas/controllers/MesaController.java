package es.unex.aos.mc_reservas.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus; // Para los códigos de estado (CREATED, etc)
import org.springframework.http.ResponseEntity; // La clase envoltorio
import org.springframework.web.bind.annotation.*;

import es.unex.aos.mc_reservas.model.Mesa;
import es.unex.aos.mc_reservas.repository.MesaRepository;

import java.util.Optional;

public class MesaController {

    @Autowired 
    MesaRepository mesaRepository;
    
    // GET /mesas
    @GetMapping("/mesas")
    public ResponseEntity<Iterable<Mesa>> getMesas() {
        // Devuelve 200 OK con la lista
        return ResponseEntity.ok(mesaRepository.findAll());
    }

    // GET /mesas/{id}
    @GetMapping("/mesas/{id}")
    public ResponseEntity<Mesa> getMesa(@PathVariable Long id) {
        Optional<Mesa> mesaOptional = mesaRepository.findById(id);
        
        if (mesaOptional.isPresent()) {
            // Si existe, devuelve 200 OK y la mesa
            return ResponseEntity.ok(mesaOptional.get());
        } else {
            // Si no existe, devuelve 404 Not Found
            return ResponseEntity.notFound().build();
        }
    }

    // GET /mesas/count
    @GetMapping("/mesas/count")
    public ResponseEntity<Long> countMesas() {
        // Devuelve 200 OK con el número
        return ResponseEntity.ok(mesaRepository.count());
    }

    // POST /mesas
    @PostMapping("/mesas")
    public ResponseEntity<Mesa> createMesa(@RequestBody Mesa mesa) {
        Mesa mesaGuardada = mesaRepository.save(mesa);
        // Devuelve 201 CREATED y la mesa creada
        return ResponseEntity.status(HttpStatus.CREATED).body(mesaGuardada);
    }

    // PUT /mesas/{id}
    @PutMapping("/mesas/{id}")
    public ResponseEntity<Mesa> updateMesa(@PathVariable Long id, @RequestBody Mesa mesaDetails) {
        Optional<Mesa> mesaOptional = mesaRepository.findById(id);

        if (mesaOptional.isPresent()) {
            Mesa mesa = mesaOptional.get();
            // Actualizamos los campos
            Optional.ofNullable(mesaDetails.getnPersonas()).ifPresent(mesa::setnPersonas);
            Optional.ofNullable(mesaDetails.isEnTerraza()).ifPresent(mesa::setEnTerraza);
            
            Mesa mesaActualizada = mesaRepository.save(mesa);
            // Devuelve 200 OK y la mesa actualizada
            return ResponseEntity.ok(mesaActualizada);
        } else {
            // Si intentan actualizar algo que no existe: 404 Not Found
            return ResponseEntity.notFound().build();
        }
    }

    // DELETE /mesas/{id}
    @DeleteMapping("/mesas/{id}")
    public ResponseEntity<Void> deleteMesa(@PathVariable Long id) {
        if (mesaRepository.existsById(id)) {
            mesaRepository.deleteById(id);
            // Devuelve 204 No Content 
            return ResponseEntity.noContent().build();
        } else {
            // Devolver 404 Not Found si no existe
            return ResponseEntity.notFound().build();
        }
    }
}