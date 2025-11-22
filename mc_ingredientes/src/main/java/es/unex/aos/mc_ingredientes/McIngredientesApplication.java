package es.unex.aos.mc_ingredientes;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import es.unex.aos.mc_ingredientes.model.Ingrediente;
import es.unex.aos.mc_ingredientes.model.Proveedor;
import es.unex.aos.mc_ingredientes.model.Suministro;
import es.unex.aos.mc_ingredientes.repository.IngredienteRepository;
import es.unex.aos.mc_ingredientes.repository.ProveedorRepository;
import es.unex.aos.mc_ingredientes.repository.SuministroRepository;

@SpringBootApplication
@EnableDiscoveryClient
public class McIngredientesApplication {

	@Autowired IngredienteRepository ingredienteRepository;
	@Autowired ProveedorRepository proveedorRepository;
	@Autowired SuministroRepository suministroRepository;
	public static void main(String[] args) {
		SpringApplication.run(McIngredientesApplication.class, args);
	}

	@PostConstruct
 	public void init() {
		Ingrediente i1 = new Ingrediente("Tomate", 100.0f, 10.0f);
        Ingrediente i2 = new Ingrediente("Lechuga", 100.0f, 10.0f);
        Ingrediente i3 = new Ingrediente("Carne", 50.0f, 5.0f); // Pongo menos para probar qué pasa si se gasta
        Ingrediente i4 = new Ingrediente("Pan", 100.0f, 10.0f);
        Ingrediente i5 = new Ingrediente("Queso", 100.0f, 10.0f);

        ingredienteRepository.save(i1);
        ingredienteRepository.save(i2);
        ingredienteRepository.save(i3);
        ingredienteRepository.save(i4);
        ingredienteRepository.save(i5);
		Proveedor prov1 = new Proveedor("Frutas Manolo", "manolo@frutas.com", "600111222");
        Proveedor prov2 = new Proveedor("Carnes Pepe", "pepe@carnes.com", "600333444");
        Proveedor prov3 = new Proveedor("Panadería La Miga", "info@lamiga.com", "600555666");

        proveedorRepository.save(prov1);
        proveedorRepository.save(prov2);
        proveedorRepository.save(prov3);

		// Frutas Manolo suministra Tomate y Lechuga
        Suministro s1 = new Suministro(prov1, i1, 0.50f, 100); // Tomate a 0.50
        Suministro s2 = new Suministro(prov1, i2, 0.30f, 50);  // Lechuga a 0.30

        // Carnes Pepe suministra Carne y Queso
        Suministro s3 = new Suministro(prov2, i3, 5.00f, 20);  // Carne a 5.00
        Suministro s4 = new Suministro(prov2, i5, 2.50f, 30);  // Queso a 2.50

        // Panadería La Miga suministra Pan
        Suministro s5 = new Suministro(prov3, i4, 0.40f, 200); // Pan a 0.40

        suministroRepository.save(s1);
        suministroRepository.save(s2);
        suministroRepository.save(s3);
        suministroRepository.save(s4);
        suministroRepository.save(s5);
		
 	}

}
