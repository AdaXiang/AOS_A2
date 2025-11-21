package es.unex.aos.mc_reservas.repository;

import org.springframework.data.repository.CrudRepository;

import es.unex.aos.mc_reservas.model.Mesa;

public interface MesaRepository extends CrudRepository<Mesa, Long> {
    
}
