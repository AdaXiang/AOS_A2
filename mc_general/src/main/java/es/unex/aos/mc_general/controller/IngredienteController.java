package es.unex.aos.mc_general.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.unex.aos.mc_general.clients.IngredientesClient;


@RestController
@RequestMapping("/ingredientes")
public class IngredienteController {
    private final IngredientesClient ingredientesClient;

    public IngredienteController(IngredientesClient ingredientesClient) {
        this.ingredientesClient = ingredientesClient;
    }

    
}
