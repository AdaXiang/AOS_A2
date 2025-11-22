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
		Ingrediente i1 = new Ingrediente("Tomate", 100.0f, 20.0f);
		Ingrediente i2 = new Ingrediente("Lechuga", 50.0f, 10.0f);
		Ingrediente i3 = new Ingrediente("Cebolla", 80.0f, 15.0f);
		Ingrediente i4 = new Ingrediente("Queso", 200.0f, 30.0f);
		Ingrediente i5 = new Ingrediente("Carne", 300.0f, 50.0f);
		Ingrediente i6 = new Ingrediente("Pollo", 250.0f, 40.0f);
		Ingrediente i7 = new Ingrediente("Pescado", 400.0f, 60.0f);
		Ingrediente i8 = new Ingrediente("Pan", 150.0f, 25.0f);
		Ingrediente i9 = new Ingrediente("Aceite", 500.0f, 100.0f);
		Ingrediente i10 = new Ingrediente("Sal", 300.0f, 50.0f);
		
		ingredienteRepository.save(i1);
		ingredienteRepository.save(i2);
		ingredienteRepository.save(i3);
		ingredienteRepository.save(i4);
		ingredienteRepository.save(i5);
		ingredienteRepository.save(i6);
		ingredienteRepository.save(i7);
		ingredienteRepository.save(i8);
		ingredienteRepository.save(i9);
		ingredienteRepository.save(i10);	
		
		
 	}

}
