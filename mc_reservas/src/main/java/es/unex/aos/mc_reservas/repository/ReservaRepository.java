package es.unex.aos.mc_reservas.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RestResource;

import es.unex.aos.mc_reservas.model.Reserva;

@RestResource(path="reservas", rel="reserva")
public interface ReservaRepository extends CrudRepository<Reserva, Long> {
    
}