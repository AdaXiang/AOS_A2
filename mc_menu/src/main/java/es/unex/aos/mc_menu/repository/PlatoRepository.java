package es.unex.aos.mc_menu.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import es.unex.aos.mc_menu.model.Plato;

@Repository
public interface PlatoRepository extends CrudRepository<Plato, Long> {

} 
