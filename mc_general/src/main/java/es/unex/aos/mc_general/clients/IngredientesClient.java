package es.unex.aos.mc_general.clients;

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

    @PostMapping("/ingredientes/{id}/reposicion")
    void reponerIngrediente(@PathVariable("id") Long id);
}
