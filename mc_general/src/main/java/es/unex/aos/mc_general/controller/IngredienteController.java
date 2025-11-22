package es.unex.aos.mc_general.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.tags.Tag; // Importante

import es.unex.aos.mc_general.clients.IngredientesClient;

@RestController
@RequestMapping("/general")
public class IngredienteController {

    private final IngredientesClient ingredientesClient;

    public IngredienteController(IngredientesClient ingredientesClient) {
        this.ingredientesClient = ingredientesClient;
    }

    // -----------------------
    //      INGREDIENTES
    // -----------------------
    @Tag(name = "Ingrediente", description = "Operaciones sobre la entidad Ingrediente")
    @GetMapping("/ingredientes")
    public List<Object> getIngredientes() {
        return ingredientesClient.getIngredientes();
    }

    @Tag(name = "Ingrediente")
    @GetMapping("/ingredientes/{id}")
    public Object getIngrediente(@PathVariable Long id) {
        return ingredientesClient.getIngredienteById(id);
    }

    @Tag(name = "Ingrediente")
    @PostMapping("/ingredientes")
    public Object createIngrediente(@RequestBody Object ingrediente) {
        return ingredientesClient.createIngrediente(ingrediente);
    }

    @Tag(name = "Ingrediente")
    @PutMapping("/ingredientes/{id}")
    public Object updateIngrediente(@PathVariable Long id, @RequestBody Object ingrediente) {
        return ingredientesClient.updateIngrediente(id, ingrediente);
    }

    @Tag(name = "Ingrediente")
    @DeleteMapping("/ingredientes/{id}")
    public void deleteIngrediente(@PathVariable Long id) {
        ingredientesClient.deleteIngrediente(id);
    }

    @Tag(name = "Ingrediente")
    @PostMapping("/ingredientes/{id}/reposicion")
    public void reponerIngrediente(@PathVariable Long id) {
        ingredientesClient.reponerIngrediente(id);
    }

    @Tag(name = "Ingrediente")
    @PostMapping("/ingredientes/{id}/consumir/{cantidad}")
    public void consumirIngrediente(@PathVariable Long id, @PathVariable Integer cantidad) {
        ingredientesClient.consumirIngrediente(id, cantidad);
    }

    // -----------------------
    //      PROVEEDORES
    // -----------------------
    @Tag(name = "Proveedor", description = "Gestión de proveedores de alimentos")
    @GetMapping("/proveedores")
    public List<Object> getProveedores() {
        return ingredientesClient.getProveedores();
    }

    @Tag(name = "Proveedor")
    @GetMapping("/proveedores/{id}")
    public Object getProveedor(@PathVariable Long id) {
        return ingredientesClient.getProveedorById(id);
    }

    @Tag(name = "Proveedor")
    @PostMapping("/proveedores")
    public Object createProveedor(@RequestBody Object proveedor) {
        return ingredientesClient.createProveedor(proveedor);
    }

    @Tag(name = "Proveedor")
    @PutMapping("/proveedores/{id}")
    public Object updateProveedor(@PathVariable Long id, @RequestBody Object proveedor) {
        return ingredientesClient.updateProveedor(id, proveedor);
    }

    @Tag(name = "Proveedor")
    @DeleteMapping("/proveedores/{id}")
    public void deleteProveedor(@PathVariable Long id) {
        ingredientesClient.deleteProveedor(id);
    }

    // -----------------------
    //      SUMINISTROS
    // -----------------------
    @Tag(name = "Suministro", description = "Relación entre Proveedores e Ingredientes")
    @GetMapping("/suministros")
    public List<Object> getSuministros() {
        return ingredientesClient.getSuministros();
    }

    @Tag(name = "Suministro")
    @GetMapping("/suministros/{idProveedor}/{idIngrediente}")
    public Object getSuministro(@PathVariable Long idProveedor, @PathVariable Long idIngrediente) {
        return ingredientesClient.getSuministro(idProveedor, idIngrediente);
    }

    @Tag(name = "Suministro")
    @PostMapping("/suministros")
    public Object createSuministro(@RequestBody Object suministro) {
        return ingredientesClient.createSuministro(suministro);
    }

    @Tag(name = "Suministro")
    @PutMapping("/suministros/{idProveedor}/{idIngrediente}")
    public Object updateSuministro(@PathVariable Long idProveedor, @PathVariable Long idIngrediente, @RequestBody Object suministro) {
        return ingredientesClient.updateSuministro(idProveedor, idIngrediente, suministro);
    }

    @Tag(name = "Suministro")
    @DeleteMapping("/suministros/{idProveedor}/{idIngrediente}")
    public void deleteSuministro(@PathVariable Long idProveedor, @PathVariable Long idIngrediente) {
        ingredientesClient.deleteSuministro(idProveedor, idIngrediente);
    }
}