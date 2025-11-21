package es.unex.aos.mc_reservas.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import es.unex.aos.mc_reservas.model.Mesa;

@Repository
public interface MesaRepository extends CrudRepository<Mesa, Long> {
    
}
