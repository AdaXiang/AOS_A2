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
import es.unex.aos.mc_menu.model.Menu;

import es.unex.aos.mc_menu.repository.MenuRepository;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/menus")
public class MenuController {

    @Autowired 
    MenuRepository menuRepository;
    
    // GET /menus
    @GetMapping
    public ResponseEntity<Iterable<Menu>> getMenus() {
        return ResponseEntity.ok(menuRepository.findAll());
    }

    // GET /menus/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Menu> getMenuById(@PathVariable Long id) {
        return menuRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST /menus
    @PostMapping
    public ResponseEntity<Menu> createMenu(@RequestBody Menu menu) {
        Menu savedMenu = menuRepository.save(menu);
        return ResponseEntity.status(201).body(savedMenu);
    }

    // PUT /menus/{id}
    @PutMapping("/{id}")
    public ResponseEntity<Menu> updateMenu(@PathVariable Long id, @RequestBody Menu menuDetails) {
        Optional<Menu> menuOptional = menuRepository.findById(id);
        if (menuOptional.isPresent()) {
            Menu menu = menuOptional.get();
            Optional.ofNullable(menuDetails.getName()).ifPresent(menu::setName);
            Optional.ofNullable(menuDetails.getPrecio()).ifPresent(menu::setPrecio);
            Menu updatedMenu = menuRepository.save(menu);
            return ResponseEntity.ok(updatedMenu);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // DELETE /menus/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMenu(@PathVariable Long id) {
        if (menuRepository.existsById(id)) {
            menuRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
