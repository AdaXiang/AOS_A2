package es.unex.aos.mc_menu.controllers;

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

import es.unex.aos.mc_menu.model.IngredientePlato;
import es.unex.aos.mc_menu.model.IngredientePlatoId;
import es.unex.aos.mc_menu.model.MenuPlato;
import es.unex.aos.mc_menu.model.MenuPlatoId;
import es.unex.aos.mc_menu.repository.MenuPlatoRepository;

@RestController
@RequestMapping("/menuplatos")
public class MenuPlatoController {
    @Autowired 
    MenuPlatoRepository menuPlatoRepository;

    // GET /menuplatos
    @GetMapping
    public ResponseEntity<Iterable<MenuPlato>> getMenuPlatos() {
        return ResponseEntity.ok(menuPlatoRepository.findAll());
    }

    // GET /ingredienteplatos/{idIngrediente}/{idPlato}
    @GetMapping("/{idMenu}/{idPlato}")
    public ResponseEntity<MenuPlato> getMenuPlato(@PathVariable Long idMenu, @PathVariable Long idPlato) {
        Optional<MenuPlato> id = menuPlatoRepository.findById(new MenuPlatoId(idMenu, idPlato));

        if (id.isPresent()) {
            return ResponseEntity.ok(id.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // POST /menuplatos
    @PostMapping
    public ResponseEntity<MenuPlato> createMenuPlato(@RequestBody MenuPlato menuPlato) {
        MenuPlato menuPlatoGuardado = menuPlatoRepository.save(menuPlato);
        return ResponseEntity.status(201).body(menuPlatoGuardado);
    }

    // PUT /menuplatos/{idMenu}/{idPlato}
    @PutMapping("/{idMenu}/{idPlato}")
    public ResponseEntity<MenuPlato> updateMenuPlato(@PathVariable Long idMenu, @PathVariable Long idPlato, @RequestBody MenuPlato menuPlatoDetails) {
        MenuPlatoId menuPlatoId = new MenuPlatoId(idMenu, idPlato);
        Optional<MenuPlato> menuPlatoExistente = menuPlatoRepository.findById(menuPlatoId);

        if (menuPlatoExistente.isPresent()) {
            Optional.ofNullable(menuPlatoDetails.getMenu()).ifPresent(menuPlatoExistente.get()::setMenu);
            Optional.ofNullable(menuPlatoDetails.getPlato()).ifPresent(menuPlatoExistente.get()::setPlato);
            MenuPlato menuPlatoActualizado = menuPlatoRepository.save(menuPlatoExistente.get());
            return ResponseEntity.ok(menuPlatoActualizado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{idMenu}/{idPlato}")
    public ResponseEntity<Void> deleteMenuPlato(@PathVariable Long idMenu,@PathVariable Long idPlato) {
        MenuPlatoId menuPlatoId = new MenuPlatoId(idMenu, idPlato);
        Optional<MenuPlato> menuPlatoExistente = menuPlatoRepository.findById(menuPlatoId);

        if (menuPlatoExistente.isPresent()) {
            menuPlatoRepository.delete(menuPlatoExistente.get());
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
}
