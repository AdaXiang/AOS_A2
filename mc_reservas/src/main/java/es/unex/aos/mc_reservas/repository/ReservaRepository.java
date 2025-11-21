package es.unex.aos.mc_reservas.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import es.unex.aos.mc_reservas.model.Reserva;

@Repository
public interface ReservaRepository extends CrudRepository<Reserva, Long> {
    
}