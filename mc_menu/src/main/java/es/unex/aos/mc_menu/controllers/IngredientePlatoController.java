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

import es.unex.aos.mc_menu.model.IngredientePlato;
import es.unex.aos.mc_menu.model.IngredientePlatoId;
import es.unex.aos.mc_menu.repository.IngredientePlatoRepository;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/ingredienteplatos")
public class IngredientePlatoController {

    @Autowired 
    IngredientePlatoRepository ingredientePlatoRepository;

    // GET /ingredienteplatos
    @GetMapping
    public ResponseEntity<Iterable<IngredientePlato>> getIngredientePlatos() {
        return ResponseEntity.ok(ingredientePlatoRepository.findAll());
    }

    // GET /ingredienteplatos/{idIngrediente}/{idPlato}
    @GetMapping("/{idIngrediente}/{idPlato}")
    public ResponseEntity<IngredientePlato> getIngredientePlato(@PathVariable Long idIngrediente, @PathVariable Long idPlato) {
        Optional<IngredientePlato> id = ingredientePlatoRepository.findById(new IngredientePlatoId(idIngrediente, idPlato));

        if (id.isPresent()) {
            return ResponseEntity.ok(id.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // POST /ingredienteplatos
    @PostMapping
    public ResponseEntity<IngredientePlato> createIngredientePlato(@RequestBody IngredientePlato ingredientePlato) {
        IngredientePlato ingredientePlatoGuardado = ingredientePlatoRepository.save(ingredientePlato);
        return ResponseEntity.status(201).body(ingredientePlatoGuardado);
    }

    // PUT /ingredienteplatos/{idIngrediente}/{idPlato}
    @PutMapping("/{idIngrediente}/{idPlato}")
    public ResponseEntity<IngredientePlato> updateIngredientePlato(@PathVariable Long idIngrediente, @PathVariable Long idPlato, @RequestBody IngredientePlato ingredientePlatoDetails) {
        IngredientePlatoId ingredientePlatoId = new IngredientePlatoId(idIngrediente, idPlato);
        Optional<IngredientePlato> ingredientePlatoExistente = ingredientePlatoRepository.findById(ingredientePlatoId);

        if (ingredientePlatoExistente.isPresent()) {
            ingredientePlatoExistente.get().setCantidad(ingredientePlatoDetails.getCantidad());
            IngredientePlato ingredientePlatoActualizado = ingredientePlatoRepository.save(ingredientePlatoExistente.get());
            return ResponseEntity.ok(ingredientePlatoActualizado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{idIngrediente}/{idPlato}")
    public ResponseEntity<Void> deleteIngredientePlato(@PathVariable Long idIngrediente,@PathVariable Long idPlato) {
        IngredientePlatoId ingredientePlatoId = new IngredientePlatoId(idIngrediente, idPlato);
        Optional<IngredientePlato> ingredientePlatoExistente = ingredientePlatoRepository.findById(ingredientePlatoId);

        if (ingredientePlatoExistente.isPresent()) {
            ingredientePlatoRepository.delete(ingredientePlatoExistente.get());
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
