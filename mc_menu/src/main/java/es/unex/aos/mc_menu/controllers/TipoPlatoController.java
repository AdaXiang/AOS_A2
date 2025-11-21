package es.unex.aos.mc_menu.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

import es.unex.aos.mc_menu.model.TipoPlato;

import es.unex.aos.mc_menu.repository.TipoPlatoRepository;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/tipoplatos")
public class TipoPlatoController {

    @Autowired 
    TipoPlatoRepository tipoPlatoRepository;

    // GET /tipoplatos
    @GetMapping
    public ResponseEntity<Iterable<TipoPlato>> getTipoPlatos() {
        return ResponseEntity.ok(tipoPlatoRepository.findAll());
    }
    
    // GET /tipoplatos/{id}
    @GetMapping("/{id}")
    public ResponseEntity<TipoPlato> getTipoPlato(@PathVariable Long id) {
        Optional<TipoPlato> tipoPlatoOptional = tipoPlatoRepository.findById(id);
         if (tipoPlatoOptional.isPresent()) {
              return ResponseEntity.ok(tipoPlatoOptional.get());
         } else {
              return ResponseEntity.notFound().build();
         }
    }

    // POST /tipoplatos
    @PostMapping
    public ResponseEntity<TipoPlato> createTipoPlato(@RequestBody TipoPlato tipoPlato) {
        TipoPlato tipoPlatoGuardado = tipoPlatoRepository.save(tipoPlato);
          return ResponseEntity.status(201).body(tipoPlatoGuardado);
    }

    // PUT /tipoplatos/{id}
    @PutMapping("/{id}")
    public ResponseEntity<TipoPlato> updateTipoPlato(@PathVariable Long id, @RequestBody TipoPlato tipoPlatoDetails) {
        Optional<TipoPlato> tipoPlatoOptional = tipoPlatoRepository.findById(id);
         if (tipoPlatoOptional.isPresent()) {
              TipoPlato tipoPlato = tipoPlatoOptional.get();
              Optional.ofNullable(tipoPlatoDetails.getNombre()).ifPresent(tipoPlato::setNombre);
              TipoPlato tipoPlatoActualizado = tipoPlatoRepository.save(tipoPlato);
              return ResponseEntity.ok(tipoPlatoActualizado);
         } else {
              return ResponseEntity.notFound().build();
         }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTipoPlato(@PathVariable Long id) {
        if (tipoPlatoRepository.existsById(id)) {
            tipoPlatoRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
