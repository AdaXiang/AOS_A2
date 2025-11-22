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

    // Método para procesar el pedido de un plato
    public void procesarPedido(Long idPlato) {
        // 1. Buscar el plato
        Plato plato = platoRepository.findById(idPlato)
            .orElseThrow(() -> new RuntimeException("Plato no encontrado"));

        // 2. Iterar sobre los ingredientes requeridos por el plato
        for (IngredientePlato ip : plato.getIngredientes()) {
            Long idIngrediente = ip.getId().getIdIngrediente();
            
            // Convertimos la cantidad (Float) a Integer para el stock
            Integer cantidadAConsumir = ip.getCantidad().intValue(); 
            
            // 3. Llamar al microservicio de ingredientes para restar el stock
            try {
                ingredientesClient.consumirIngrediente(idIngrediente, cantidadAConsumir);
                System.out.println("Consumido ingrediente " + idIngrediente + ": -" + cantidadAConsumir);
            } catch (Exception e) {
                // Si falla (por ejemplo, stock insuficiente), lanzamos error
                throw new RuntimeException("Error al consumir ingrediente " + idIngrediente + ": " + e.getMessage());
            }
        }
    }
}
