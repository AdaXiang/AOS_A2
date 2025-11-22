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

    public void procesarPedido(Long idPlato) {

        Plato plato = platoRepository.findById(idPlato)
            .orElseThrow(() -> new RuntimeException("Plato no encontrado"));

        for (IngredientePlato ip : plato.getIngredientes()) {

            Long idIngrediente = ip.getId().getIdIngrediente();
            Integer cantidadAConsumir = ip.getCantidad().intValue();

            try {
                ingredientesClient.consumirIngrediente(idIngrediente, cantidadAConsumir);
                System.out.println("Consumido ingrediente " + idIngrediente + ": -" + cantidadAConsumir);
            
            } catch (Exception e) {
                throw new RuntimeException("Error al consumir ingrediente " + idIngrediente + ": " + e.getMessage());
            }
        }
    }
}
