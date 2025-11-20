package es.unex.aos.mc_general;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class McGeneralApplication {

	public static void main(String[] args) {
		SpringApplication.run(McGeneralApplication.class, args);
	}

}
