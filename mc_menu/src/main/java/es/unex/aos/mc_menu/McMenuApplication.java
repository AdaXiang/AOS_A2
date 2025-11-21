package es.unex.aos.mc_menu;

import javax.annotation.PostConstruct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import es.unex.aos.mc_menu.model.IngredientePlato;
import es.unex.aos.mc_menu.model.Menu;
import es.unex.aos.mc_menu.model.Plato;
import es.unex.aos.mc_menu.model.TipoPlato;

@SpringBootApplication
@EnableDiscoveryClient
public class McMenuApplication {

	public static void main(String[] args) {
		SpringApplication.run(McMenuApplication.class, args);
	}

	@PostConstruct
 	public void init() {
		TipoPlato entrante = new TipoPlato("Entrante");
		TipoPlato principal = new TipoPlato("Plato Principal");
		TipoPlato postre = new TipoPlato("Postre");

		Plato p1 = new Plato("Ensalada", 5.0f, entrante);
		Plato p2 = new Plato("Hamburguesa", 10.0f, principal);
		Plato p3 = new Plato("Helado", 4.0f, postre);

		Menu menu1 = new Menu("Menu del día", 15.0f);
		Menu menu2 = new Menu("Menu degustación", 25.0f);

		// IngredientePlato ip1 = new IngredientePlato("Ensalada", p1.getId());
		// IngredientePlato ip2 = new IngredientePlato("Tomate", p1.getId());
		// IngredientePlato ip3 = new IngredientePlato("Carne", p2.getId());
		// IngredientePlato ip4 = new IngredientePlato("Pan", p2.getId());
		// IngredientePlato ip5 = new IngredientePlato("Nata", p3.getId());
		// IngredientePlato ip6 = new IngredientePlato("Azúcar", p3.getId());

		
		
 	}

}
