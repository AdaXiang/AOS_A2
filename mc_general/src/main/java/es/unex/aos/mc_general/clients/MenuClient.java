package es.unex.aos.mc_general.clients;

import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "MC-MENU")
public interface MenuClient {

    // --- MENUS ---
    @GetMapping("/menus")
    List<Object> getMenus();

    @GetMapping("/menus/{id}")
    Object getMenuById(@PathVariable("id") Long id);

    @PostMapping("/menus")
    Object createMenu(@RequestBody Object menu);

    @PutMapping("/menus/{id}")
    Object updateMenu(@PathVariable("id") Long id, @RequestBody Object menu);

    @DeleteMapping("/menus/{id}")
    void deleteMenu(@PathVariable("id") Long id);


    // --- PLATOS ---
    @GetMapping("/platos")
    List<Object> getPlatos();

    @GetMapping("/platos/{id}")
    Object getPlatoById(@PathVariable("id") Long id);

    @PostMapping("/platos")
    Object createPlato(@RequestBody Object plato);

    @PutMapping("/platos/{id}")
    Object updatePlato(@PathVariable("id") Long id, @RequestBody Object plato);

    @DeleteMapping("/platos/{id}")
    void deletePlato(@PathVariable("id") Long id);

    @PostMapping("/platos/{id}/pedido")
    String realizarPedido(@PathVariable("id") Long id);


    // --- TIPO DE PLATOS ---
    @GetMapping("/tipoplatos")
    List<Object> getTipoPlatos();

    @GetMapping("/tipoplatos/{id}")
    Object getTipoPlatoById(@PathVariable("id") Long id);

    @PostMapping("/tipoplatos")
    Object createTipoPlato(@RequestBody Object tipoPlato);

    @PutMapping("/tipoplatos/{id}")
    Object updateTipoPlato(@PathVariable("id") Long id, @RequestBody Object tipoPlato);

    @DeleteMapping("/tipoplatos/{id}")
    void deleteTipoPlato(@PathVariable("id") Long id);


    // --- INGREDIENTE-PLATO (Relación) ---
    @GetMapping("/ingredienteplatos")
    List<Object> getIngredientePlatos();

    @GetMapping("/ingredienteplatos/{idIngrediente}/{idPlato}")
    Object getIngredientePlato(@PathVariable("idIngrediente") Long idIngrediente, @PathVariable("idPlato") Long idPlato);

    @PostMapping("/ingredienteplatos")
    Object createIngredientePlato(@RequestBody Object ingredientePlato);

    @PutMapping("/ingredienteplatos/{idIngrediente}/{idPlato}")
    Object updateIngredientePlato(@PathVariable("idIngrediente") Long idIngrediente, @PathVariable("idPlato") Long idPlato, @RequestBody Object ingredientePlato);

    @DeleteMapping("/ingredienteplatos/{idIngrediente}/{idPlato}")
    void deleteIngredientePlato(@PathVariable("idIngrediente") Long idIngrediente, @PathVariable("idPlato") Long idPlato);


    // --- MENU-PLATO (Relación) ---
    @GetMapping("/menuplatos")
    List<Object> getMenuPlatos();

    @GetMapping("/menuplatos/{idMenu}/{idPlato}")
    Object getMenuPlato(@PathVariable("idMenu") Long idMenu, @PathVariable("idPlato") Long idPlato);

    @PostMapping("/menuplatos")
    Object createMenuPlato(@RequestBody Object menuPlato);

    @PutMapping("/menuplatos/{idMenu}/{idPlato}")
    Object updateMenuPlato(@PathVariable("idMenu") Long idMenu, @PathVariable("idPlato") Long idPlato, @RequestBody Object menuPlato);

    @DeleteMapping("/menuplatos/{idMenu}/{idPlato}")
    void deleteMenuPlato(@PathVariable("idMenu") Long idMenu, @PathVariable("idPlato") Long idPlato);
}