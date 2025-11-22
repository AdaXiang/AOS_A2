package es.unex.aos.mc_menu.service;

import org.springframework.stereotype.Service;

import es.unex.aos.mc_menu.client.IngredientesClient;
import es.unex.aos.mc_menu.model.IngredientePlato;
import es.unex.aos.mc_menu.model.Plato;
import es.unex.aos.mc_menu.repository.PlatoRepository;

@Service
public class PlatoService {

    private final IngredientesClient ingredientesClient;
    private final PlatoRepository platoRepository;

    public PlatoService(IngredientesClient ingredientesClient, PlatoRepository platoRepository) {
        this.ingredientesClient = ingredientesClient;
        this.platoRepository = platoRepository;
    }

    // public void verificarStock(Plato plato) {
    //     for (IngredientePlato ip : plato.getIngredientes()) {

    //         Long idIng = ip.getId().getIdIngrediente();
    //         Float cantidadNecesaria = ip.getCantidad();
    //         Integer stock = ingredientesClient.getIngredienteById(idIng).getStock();

    //         if (stock < cantidadNecesaria) {
    //             platoRepository.delete(plato);
    //             ingredientesClient.solicitarReposicion(idIng);
    //         }
    //     }
    // }
}