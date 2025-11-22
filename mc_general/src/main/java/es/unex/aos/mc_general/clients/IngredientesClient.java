package es.unex.aos.mc_general.clients;

import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "MC-INGREDIENTES")
public interface IngredientesClient {

    // --- INGREDIENTES ---
    @GetMapping("/ingredientes")
    List<Object> getIngredientes();

    @GetMapping("/ingredientes/{id}")
    Object getIngredienteById(@PathVariable("id") Long id);

    @PostMapping("/ingredientes")
    Object createIngrediente(@RequestBody Object ingrediente);

    @PutMapping("/ingredientes/{id}")
    Object updateIngrediente(@PathVariable("id") Long id, @RequestBody Object ingrediente);

    @DeleteMapping("/ingredientes/{id}")
    void deleteIngrediente(@PathVariable("id") Long id);

    @PostMapping("/ingredientes/{id}/reposicion")
    void reponerIngrediente(@PathVariable("id") Long id);

    @PostMapping("/ingredientes/{id}/consumir/{cantidad}")
    void consumirIngrediente(@PathVariable("id") Long id, @PathVariable("cantidad") Integer cantidad);


    // --- PROVEEDORES ---
    @GetMapping("/proveedores")
    List<Object> getProveedores();

    @GetMapping("/proveedores/{id}")
    Object getProveedorById(@PathVariable("id") Long id);

    @PostMapping("/proveedores")
    Object createProveedor(@RequestBody Object proveedor);

    @PutMapping("/proveedores/{id}")
    Object updateProveedor(@PathVariable("id") Long id, @RequestBody Object proveedor);

    @DeleteMapping("/proveedores/{id}")
    void deleteProveedor(@PathVariable("id") Long id);


    // --- SUMINISTROS ---
    @GetMapping("/suministros")
    List<Object> getSuministros();

    @GetMapping("/suministros/{idProveedor}/{idIngrediente}")
    Object getSuministro(@PathVariable("idProveedor") Long idProveedor, @PathVariable("idIngrediente") Long idIngrediente);

    @PostMapping("/suministros")
    Object createSuministro(@RequestBody Object suministro);

    @PutMapping("/suministros/{idProveedor}/{idIngrediente}")
    Object updateSuministro(@PathVariable("idProveedor") Long idProveedor, @PathVariable("idIngrediente") Long idIngrediente, @RequestBody Object suministro);

    @DeleteMapping("/suministros/{idProveedor}/{idIngrediente}")
    void deleteSuministro(@PathVariable("idProveedor") Long idProveedor, @PathVariable("idIngrediente") Long idIngrediente);
}