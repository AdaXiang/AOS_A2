package es.unex.aos.mc_reservas;

import java.util.ArrayList;
import java.util.HashSet;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import com.netflix.discovery.converters.Auto;

import es.unex.aos.mc_reservas.model.Mesa;
import es.unex.aos.mc_reservas.repository.MesaRepository;

@SpringBootApplication
@EnableDiscoveryClient
public class McReservasApplication {
	@Autowired MesaRepository mesaRepository;
	public static void main(String[] args) {
		SpringApplication.run(McReservasApplication.class, args);
	}

	@PostConstruct
 	public void init() {
		Mesa m1 = new Mesa(2, true);
		Mesa m2 = new Mesa(4, false);

		mesaRepository.save(m1);
		mesaRepository.save(m2);
 	}
}
