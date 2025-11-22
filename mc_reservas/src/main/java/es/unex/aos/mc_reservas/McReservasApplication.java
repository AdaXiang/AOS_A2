package es.unex.aos.mc_reservas;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


import es.unex.aos.mc_reservas.model.Mesa;
import es.unex.aos.mc_reservas.model.Reserva;
import es.unex.aos.mc_reservas.repository.MesaRepository;
import es.unex.aos.mc_reservas.repository.ReservaRepository;

@SpringBootApplication
@EnableDiscoveryClient
public class McReservasApplication {
	@Autowired MesaRepository mesaRepository;
	@Autowired ReservaRepository reservaRepository;
	public static void main(String[] args) {
		SpringApplication.run(McReservasApplication.class, args);
	}

	@PostConstruct
 	public void init() {
		Mesa m1 = new Mesa(2, true);
		Mesa m2 = new Mesa(4, false);
		Mesa m3 = new Mesa(6, true);
		Mesa m4 = new Mesa(8, false);

		mesaRepository.save(m1);
		mesaRepository.save(m2);
		mesaRepository.save(m3);
		mesaRepository.save(m4);

		Reserva r1 = new Reserva(
            m1,
            "Carlos Pérez",
            "carlos.perez@example.com",
            "654123987",
            LocalDate.of(2025, 11, 25), 
            LocalTime.of(20, 30),      
            90,
            2
        );

        Reserva r2 = new Reserva(
            m2,
            "María López",
            "maria.lopez@example.com",
            "612789456",
            LocalDate.of(2025, 11, 25), 
            LocalTime.of(14, 0),      
            120,
            4
        );

		reservaRepository.save(r1);
		reservaRepository.save(r2);

 	}
}
