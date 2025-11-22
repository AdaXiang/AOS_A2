package es.unex.aos.mc_ingredientes.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.unex.aos.mc_ingredientes.model.Ingrediente;
import es.unex.aos.mc_ingredientes.repository.IngredienteRepository;

@RestController
@RequestMapping("/ingredientes")
public class IngredienteController {
     @Autowired 
     IngredienteRepository ingredienteRepository;
    
    // GET /ingredientes
    @GetMapping
    public ResponseEntity<Iterable<Ingrediente>> getIngredientes() {
        return ResponseEntity.ok(ingredienteRepository.findAll());
    }

    // GET /ingredientes/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Ingrediente> getIngredienteById(@PathVariable Long id) {
        return ingredienteRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST /ingredientes
    @PostMapping
    public ResponseEntity<Ingrediente> createIngrediente(@RequestBody Ingrediente ingrediente) {
        Ingrediente savedIngrediente = ingredienteRepository.save(ingrediente);
        return ResponseEntity.status(201).body(savedIngrediente);
    }

    // PUT /ingredientes/{id}
    @PutMapping("/{id}")
    public ResponseEntity<Ingrediente> updateIngrediente(@PathVariable Long id, @RequestBody Ingrediente ingredienteDetails) {
        Optional<Ingrediente> ingredienteOptional = ingredienteRepository.findById(id);
        if (ingredienteOptional.isPresent()) {
            Ingrediente ingrediente = ingredienteOptional.get();
            Optional.ofNullable(ingredienteDetails.getNombre()).ifPresent(ingrediente::setNombre);
            Optional.ofNullable(ingredienteDetails.getCantidad()).ifPresent(ingrediente::setCantidad);
            Optional.ofNullable(ingredienteDetails.getCantidadMin()).ifPresent(ingrediente::setCantidad);
            Ingrediente updatedIngrediente = ingredienteRepository.save(ingrediente);
            return ResponseEntity.ok(updatedIngrediente);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // DELETE /ingredientes/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteIngrediente(@PathVariable Long id) {
        if (ingredienteRepository.existsById(id)) {
            ingredienteRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
