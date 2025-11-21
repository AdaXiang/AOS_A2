package es.unex.aos.mc_ingredientes.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import es.unex.aos.mc_ingredientes.model.Suministro;

@Repository
public interface SuministroRepository extends CrudRepository<Suministro, Long> {
    
}
