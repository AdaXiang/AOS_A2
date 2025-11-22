package es.unex.aos.mc_menu.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "MC-INGREDIENTES")
public interface IngredientesClient {

    @GetMapping("/ingredientes")
    List<Object> getIngredientes();

    @GetMapping("/ingredientes/{id}")
    Object getIngredienteById(@PathVariable Long id);

    @GetMapping("/ingredientes/{id}/stock")
    Integer getStock(@PathVariable Long id);

    @PostMapping("/ingredientes/")
    void solicitarReposicion(@PathVariable Long id);
}
