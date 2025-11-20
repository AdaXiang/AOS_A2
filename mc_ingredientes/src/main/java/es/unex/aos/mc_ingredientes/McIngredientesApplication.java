package es.unex.aos.mc_ingredientes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class McIngredientesApplication {

	public static void main(String[] args) {
		SpringApplication.run(McIngredientesApplication.class, args);
	}

}
