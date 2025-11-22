package es.unex.aos.mc_menu.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.unex.aos.mc_menu.client.IngredientesClient;
import es.unex.aos.mc_menu.model.IngredienteDto;
import es.unex.aos.mc_menu.model.IngredientePlato;
import es.unex.aos.mc_menu.model.MenuPlato;
import es.unex.aos.mc_menu.model.Plato;
import es.unex.aos.mc_menu.repository.MenuPlatoRepository;
import es.unex.aos.mc_menu.repository.PlatoRepository;

@Service
public class MenuService {

    private final IngredientesClient ingredientesClient;
    private final MenuPlatoRepository menuPlatoRepository;
    private final PlatoRepository platoRepository;

    public MenuService(IngredientesClient ingredientesClient, MenuPlatoRepository menuPlatoRepository, PlatoRepository platoRepository) {
        this.ingredientesClient = ingredientesClient;
        this.menuPlatoRepository = menuPlatoRepository;
        this.platoRepository = platoRepository;
    }

    /**
     * Verifica la disponibilidad de ingredientes para todos los platos asignados a menús.
     * Si no hay stock, elimina la relación Menu-Plato y solicita reposición.
     */
    @Transactional
    public void verificarDisponibilidadYActualizar() {
        // Obtenemos todas las asignaciones de platos a menús
        Iterable<MenuPlato> menuPlatos = menuPlatoRepository.findAll();
        List<MenuPlato> relacionesAEliminar = new ArrayList<>();

        for (MenuPlato mp : menuPlatos) {
            Plato plato = mp.getPlato();
            boolean faltaStock = false;

            // Verificamos cada ingrediente del plato
            for (IngredientePlato ip : plato.getIngredientes()) {
                Long idIngrediente = ip.getId().getIdIngrediente(); //
                Float cantidadNecesaria = ip.getCantidad();

                // 1. Consultamos stock remoto
                try {
                    IngredienteDto stockRemoto = ingredientesClient.getIngredienteById(idIngrediente);
                    
                    if (stockRemoto != null && stockRemoto.getCantidad() < cantidadNecesaria) {
                        faltaStock = true;
                        // 2. Pedimos reposición al detectar falta de stock
                        System.out.println("Falta stock ingrediente " + idIngrediente + ". Solicitando reposición...");
                        ingredientesClient.solicitarReposicion(idIngrediente);
                    }
                } catch (Exception e) {
                    System.err.println("Error conectando con mc_ingredientes: " + e.getMessage());
                }
            }

            // 3. Si faltó algún ingrediente, marcamos esta relación para eliminar
            if (faltaStock) {
                System.out.println("Eliminando plato " + plato.getName() + " del menú " + mp.getMenu().getName() + " por falta de ingredientes.");
                relacionesAEliminar.add(mp);
            }
        }

        // 4. Eliminamos efectivamente las relaciones de la base de datos
        // El plato sigue existiendo en la tabla Plato, pero ya no está en la tabla MenuPlato
        menuPlatoRepository.deleteAll(relacionesAEliminar);
    }
}