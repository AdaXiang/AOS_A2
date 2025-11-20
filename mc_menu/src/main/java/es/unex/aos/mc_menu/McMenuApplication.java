package es.unex.aos.mc_menu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class McMenuApplication {

	public static void main(String[] args) {
		SpringApplication.run(McMenuApplication.class, args);
	}

}
