package es.unex.aos.mc_menu.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import es.unex.aos.mc_menu.service.PlatoService;
import es.unex.aos.mc_menu.model.Plato;
import es.unex.aos.mc_menu.repository.PlatoRepository;

import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/platos")
public class PlatoController {

    @Autowired 
    PlatoRepository platoRepository;
    @Autowired
    private PlatoService platoService;


    @GetMapping
    public ResponseEntity<Iterable<Plato>> getPlatos() {
        return ResponseEntity.ok(platoRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Plato> getPlatoById(@PathVariable Long id) {
        return platoRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Plato> createPlato(@RequestBody Plato plato) {
        Plato platoGuardado = platoRepository.save(plato);
        return ResponseEntity.status(201).body(platoGuardado);
    }

    // POST /platos/{id}/pedido
    @PostMapping("/{id}/pedido")
    public ResponseEntity<String> realizarPedido(@PathVariable Long id) {
        try {
            platoService.procesarPedido(id);
            return ResponseEntity.ok("Pedido realizado correctamente. Ingredientes descontados.");
        } catch (RuntimeException e) {
            // Capturamos si no hay plato o no hay stock
            return ResponseEntity.badRequest().body("Error al realizar el pedido: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Plato> updatePlato(@PathVariable Long id, @RequestBody Plato platoDetails) {
       Optional <Plato> platoOptional = platoRepository.findById(id);

        if (platoOptional.isPresent()) {
            Plato platoExistente = platoOptional.get();
            Optional.ofNullable(platoDetails.getName()).ifPresent(platoExistente::setName);
            Optional.ofNullable(platoDetails.getTipoPlato()).ifPresent(platoExistente::setTipoPlato);
            Optional.ofNullable(platoDetails.getPrecio()).ifPresent(platoExistente::setPrecio);
            Plato platoActualizado = platoRepository.save(platoExistente);
            return ResponseEntity.ok(platoActualizado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlato(@PathVariable Long id) {
        if (platoRepository.existsById(id)) {
            platoRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
