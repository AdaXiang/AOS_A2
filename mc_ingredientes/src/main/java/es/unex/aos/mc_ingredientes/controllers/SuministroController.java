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

import es.unex.aos.mc_ingredientes.model.Suministro;
import es.unex.aos.mc_ingredientes.model.SuministroId;
import es.unex.aos.mc_ingredientes.repository.SuministroRepository;

@RestController
@RequestMapping("/suministros")
public class SuministroController {
    @Autowired 
    SuministroRepository suministroRepository;

    // GET /suministros
    @GetMapping
    public ResponseEntity<Iterable<Suministro>> getSuministros() {
        return ResponseEntity.ok(suministroRepository.findAll());
    }

    // GET /suministros/{idProveedor}/{idIngrediente}
    @GetMapping("/{idProveedor}/{idIngrediente}")
    public ResponseEntity<Suministro> getSuministro(@PathVariable Long idProveedor, @PathVariable Long idIngrediente) {
        Optional<Suministro> suministro = suministroRepository.findById(new SuministroId(idProveedor, idIngrediente));

        if (suministro.isPresent()) {
            return ResponseEntity.ok(suministro.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // POST /suministros
    @PostMapping
    public ResponseEntity<Suministro> createSuministro(@RequestBody Suministro suministro) {
        Suministro suministroGuardado = suministroRepository.save(suministro);
        return ResponseEntity.status(201).body(suministroGuardado);
    }

    // PUT /suministros/{idProveedor}/{idIngrediente}
    @PutMapping("/{idProveedor}/{idIngrediente}")
    public ResponseEntity<Suministro> updateSuministro(@PathVariable Long idProveedor, @PathVariable Long idIngrediente, @RequestBody Suministro suministroDetails) {
        SuministroId suministroId = new SuministroId(idProveedor, idIngrediente);
        Optional<Suministro> suministroExistente = suministroRepository.findById(suministroId);

        if (suministroExistente.isPresent()) {
            Optional.ofNullable(suministroDetails.getPrecio()).ifPresent(suministroExistente.get()::setPrecio);
            Optional.ofNullable(suministroDetails.getFechaUltimaCompra()).ifPresent(suministroExistente.get()::setFechaUltimaCompra);
            Optional.ofNullable(suministroDetails.getCantidad()).ifPresent(suministroExistente.get()::setCantidad);
            Optional.ofNullable(suministroDetails.getProveedor()).ifPresent(suministroExistente.get()::setProveedor);
            Optional.ofNullable(suministroDetails.getIngrediente()).ifPresent(suministroExistente.get()::setIngrediente);
            Suministro suministroActualizado = suministroRepository.save(suministroExistente.get());
            return ResponseEntity.ok(suministroActualizado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{idProveedor}/{idIngrediente}")
    public ResponseEntity<Void> deleteSuministro(@PathVariable Long idProveedor,@PathVariable Long idIngrediente) {
        SuministroId suministroId = new SuministroId(idProveedor, idIngrediente);
        Optional<Suministro> suministroExistente = suministroRepository.findById(suministroId);

        if (suministroExistente.isPresent()) {
            suministroRepository.delete(suministroExistente.get());
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
