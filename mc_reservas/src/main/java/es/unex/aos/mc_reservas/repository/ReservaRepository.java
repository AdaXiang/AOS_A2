package es.unex.aos.mc_reservas.repository;

import org.springframework.data.repository.CrudRepository;
import es.unex.aos.mc_reservas.model.Reserva;

public interface ReservaRepository extends CrudRepository<Reserva, Long> {
    
}