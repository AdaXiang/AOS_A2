package es.unex.aos.mc_reservas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class McReservasApplication {

	public static void main(String[] args) {
		SpringApplication.run(McReservasApplication.class, args);
	}

}
