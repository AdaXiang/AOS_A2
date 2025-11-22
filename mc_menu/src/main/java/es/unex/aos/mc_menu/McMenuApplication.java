package es.unex.aos.mc_menu;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

import es.unex.aos.mc_menu.model.IngredientePlato;
import es.unex.aos.mc_menu.model.IngredientePlatoId;
import es.unex.aos.mc_menu.model.Menu;
import es.unex.aos.mc_menu.model.MenuPlato;
import es.unex.aos.mc_menu.model.MenuPlatoId;
import es.unex.aos.mc_menu.model.Plato;
import es.unex.aos.mc_menu.model.TipoPlato;
import es.unex.aos.mc_menu.repository.IngredientePlatoRepository;
import es.unex.aos.mc_menu.repository.MenuPlatoRepository;
import es.unex.aos.mc_menu.repository.MenuRepository;
import es.unex.aos.mc_menu.repository.PlatoRepository;
import es.unex.aos.mc_menu.repository.TipoPlatoRepository;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class McMenuApplication {

	@Autowired TipoPlatoRepository tipoPlatoRepository;
    @Autowired PlatoRepository platoRepository;
    @Autowired MenuRepository menuRepository;
    @Autowired IngredientePlatoRepository ingredientePlatoRepository;
    @Autowired MenuPlatoRepository menuPlatoRepository;
	public static void main(String[] args) {
		SpringApplication.run(McMenuApplication.class, args);
	}

	@PostConstruct
 	public void init() {
		// 1. Tipos de Plato
        TipoPlato principal = new TipoPlato("Plato Principal");
        TipoPlato entrante = new TipoPlato("Entrante");
        tipoPlatoRepository.save(principal);
        tipoPlatoRepository.save(entrante);

        // 2. Platos
        // Plato: Hamburguesa (Precio 10.0)
        Plato p1 = new Plato("Hamburguesa", 10.0f, principal);
        p1 = platoRepository.save(p1); // Guardamos para obtener el ID

        // Plato: Ensalada (Precio 5.0)
        Plato p2 = new Plato("Ensalada", 5.0f, entrante);
        p2 = platoRepository.save(p2); // Guardamos para obtener el ID

        // 3. Ingredientes de los Platos (Usamos los IDs que creamos en McIngredientes)
        // La Hamburguesa (p1) lleva: 1 ud de Carne (ID 3) y 1 ud de Pan (ID 4)
        IngredientePlato ip1 = new IngredientePlato(new IngredientePlatoId(3L, p1.getId()), p1, 1.0f);
        IngredientePlato ip2 = new IngredientePlato(new IngredientePlatoId(4L, p1.getId()), p1, 1.0f);
        
        // La Ensalada (p2) lleva: 1 ud de Tomate (ID 1) y 1 ud de Lechuga (ID 2)
        IngredientePlato ip3 = new IngredientePlato(new IngredientePlatoId(1L, p2.getId()), p2, 1.0f);
        IngredientePlato ip4 = new IngredientePlato(new IngredientePlatoId(2L, p2.getId()), p2, 1.0f);

        ingredientePlatoRepository.save(ip1);
        ingredientePlatoRepository.save(ip2);
        ingredientePlatoRepository.save(ip3);
        ingredientePlatoRepository.save(ip4);

        // 4. Menús
        Menu menu1 = new Menu("Menu del día", 15.0f);
        menu1 = menuRepository.save(menu1);

        // 5. Relación Menú - Platos
        // Añadimos la Hamburguesa y la Ensalada al Menú 1
        MenuPlato mp1 = new MenuPlato(new MenuPlatoId(menu1.getIdMenu(), p1.getId()), menu1, p1);
        MenuPlato mp2 = new MenuPlato(new MenuPlatoId(menu1.getIdMenu(), p2.getId()), menu1, p2);

        menuPlatoRepository.save(mp1);
        menuPlatoRepository.save(mp2);
 	}

}
