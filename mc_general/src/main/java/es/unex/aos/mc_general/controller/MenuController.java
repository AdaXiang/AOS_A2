package es.unex.aos.mc_general.controller;

import java.util.List;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import es.unex.aos.mc_general.clients.MenuClient;

@RestController
@RequestMapping("/general")
public class MenuController {

    private final MenuClient menuClient;

    public MenuController(MenuClient menuClient) {
        this.menuClient = menuClient;
    }

    // --- MENUS ---
    @Tag(name = "Menu", description = "Gestión de cartas y menús")
    @GetMapping("/menus")
    public List<Object> getMenus() { return menuClient.getMenus(); }

    @Tag(name = "Menu")
    @GetMapping("/menus/{id}")
    public Object getMenu(@PathVariable Long id) { return menuClient.getMenuById(id); }

    @Tag(name = "Menu")
    @PostMapping("/menus")
    public Object createMenu(@RequestBody Object menu) { return menuClient.createMenu(menu); }

    @Tag(name = "Menu")
    @PutMapping("/menus/{id}")
    public Object updateMenu(@PathVariable Long id, @RequestBody Object menu) { return menuClient.updateMenu(id, menu); }

    @Tag(name = "Menu")
    @DeleteMapping("/menus/{id}")
    public void deleteMenu(@PathVariable Long id) { menuClient.deleteMenu(id); }


    // --- PLATOS ---
    @Tag(name = "Plato", description = "Gestión de platos individuales")
    @GetMapping("/platos")
    public List<Object> getPlatos() { return menuClient.getPlatos(); }

    @Tag(name = "Plato")
    @GetMapping("/platos/{id}")
    public Object getPlato(@PathVariable Long id) { return menuClient.getPlatoById(id); }

    @Tag(name = "Plato")
    @PostMapping("/platos")
    public Object createPlato(@RequestBody Object plato) { return menuClient.createPlato(plato); }

    @Tag(name = "Plato")
    @PutMapping("/platos/{id}")
    public Object updatePlato(@PathVariable Long id, @RequestBody Object plato) { return menuClient.updatePlato(id, plato); }

    @Tag(name = "Plato")
    @DeleteMapping("/platos/{id}")
    public void deletePlato(@PathVariable Long id) { menuClient.deletePlato(id); }

    @Tag(name = "Plato")
    @PostMapping("/platos/{id}/pedido")
    public String realizarPedido(@PathVariable Long id) { return menuClient.realizarPedido(id); }


    // --- TIPO DE PLATOS ---
    @Tag(name = "TipoPlato", description = "Categorías de platos (Entrante, Principal...)")
    @GetMapping("/tipoplatos")
    public List<Object> getTipoPlatos() { return menuClient.getTipoPlatos(); }

    @Tag(name = "TipoPlato")
    @GetMapping("/tipoplatos/{id}")
    public Object getTipoPlato(@PathVariable Long id) { return menuClient.getTipoPlatoById(id); }

    @Tag(name = "TipoPlato")
    @PostMapping("/tipoplatos")
    public Object createTipoPlato(@RequestBody Object tipoPlato) { return menuClient.createTipoPlato(tipoPlato); }

    @Tag(name = "TipoPlato")
    @PutMapping("/tipoplatos/{id}")
    public Object updateTipoPlato(@PathVariable Long id, @RequestBody Object tipoPlato) { return menuClient.updateTipoPlato(id, tipoPlato); }

    @Tag(name = "TipoPlato")
    @DeleteMapping("/tipoplatos/{id}")
    public void deleteTipoPlato(@PathVariable Long id) { menuClient.deleteTipoPlato(id); }


    // --- RELACIONES (Opcional: Puedes agruparlas con Plato o Menu si prefieres) ---
    @Tag(name = "Relacion: Ingrediente-Plato", description = "Ingredientes que componen un plato")
    @GetMapping("/ingredienteplatos")
    public List<Object> getIngredientePlatos() { return menuClient.getIngredientePlatos(); }
    // ... resto de métodos con @Tag(name = "Relacion: Ingrediente-Plato") ...
    // (Omito el código repetitivo, pero pon la etiqueta en todos los métodos de este bloque)
    @Tag(name = "Relacion: Ingrediente-Plato")
    @GetMapping("/ingredienteplatos/{idIngrediente}/{idPlato}")
    public Object getIngredientePlato(@PathVariable Long idIngrediente, @PathVariable Long idPlato) {
        return menuClient.getIngredientePlato(idIngrediente, idPlato);
    }
    @Tag(name = "Relacion: Ingrediente-Plato")
    @PostMapping("/ingredienteplatos")
    public Object createIngredientePlato(@RequestBody Object ingredientePlato) {
        return menuClient.createIngredientePlato(ingredientePlato);
    }
    @Tag(name = "Relacion: Ingrediente-Plato")
    @PutMapping("/ingredienteplatos/{idIngrediente}/{idPlato}")
    public Object updateIngredientePlato(@PathVariable Long idIngrediente, @PathVariable Long idPlato, @RequestBody Object ingredientePlato) {
        return menuClient.updateIngredientePlato(idIngrediente, idPlato, ingredientePlato);
    }
    @Tag(name = "Relacion: Ingrediente-Plato")
    @DeleteMapping("/ingredienteplatos/{idIngrediente}/{idPlato}")
    public void deleteIngredientePlato(@PathVariable Long idIngrediente, @PathVariable Long idPlato) {
        menuClient.deleteIngredientePlato(idIngrediente, idPlato);
    }


    @Tag(name = "Relacion: Menu-Plato", description = "Platos incluidos en un menú")
    @GetMapping("/menuplatos")
    public List<Object> getMenuPlatos() { return menuClient.getMenuPlatos(); }
    // ... resto de métodos con @Tag(name = "Relacion: Menu-Plato") ...
    @Tag(name = "Relacion: Menu-Plato")
    @GetMapping("/menuplatos/{idMenu}/{idPlato}")
    public Object getMenuPlato(@PathVariable Long idMenu, @PathVariable Long idPlato) {
        return menuClient.getMenuPlato(idMenu, idPlato);
    }
    @Tag(name = "Relacion: Menu-Plato")
    @PostMapping("/menuplatos")
    public Object createMenuPlato(@RequestBody Object menuPlato) {
        return menuClient.createMenuPlato(menuPlato);
    }
    @Tag(name = "Relacion: Menu-Plato")
    @PutMapping("/menuplatos/{idMenu}/{idPlato}")
    public Object updateMenuPlato(@PathVariable Long idMenu, @PathVariable Long idPlato, @RequestBody Object menuPlato) {
        return menuClient.updateMenuPlato(idMenu, idPlato, menuPlato);
    }
    @Tag(name = "Relacion: Menu-Plato")
    @DeleteMapping("/menuplatos/{idMenu}/{idPlato}")
    public void deleteMenuPlato(@PathVariable Long idMenu, @PathVariable Long idPlato) {
        menuClient.deleteMenuPlato(idMenu, idPlato);
    }
}