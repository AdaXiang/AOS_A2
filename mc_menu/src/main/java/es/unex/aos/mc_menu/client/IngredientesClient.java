package es.unex.aos.mc_menu.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import es.unex.aos.mc_menu.model.IngredienteDto;

@FeignClient(name = "mc-ingredientes")
public interface IngredientesClient {

    @GetMapping("/ingredientes/{id}")
    IngredienteDto getIngredienteById(@PathVariable("id") Long id);

    @PostMapping("/ingredientes/{id}/reposicion")
    void solicitarReposicion(@PathVariable("id") Long id);

    @PostMapping("/ingredientes/{id}/consumir/{cantidad}")
    void consumirIngrediente(@PathVariable("id") Long id, @PathVariable("cantidad") Integer cantidad);
}