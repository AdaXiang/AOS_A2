package es.unex.aos.mc_ingredientes.repository;

import org.springframework.data.repository.CrudRepository;
import es.unex.aos.mc_ingredientes.model.Suministro;

public interface SuministroRepository extends CrudRepository<Suministro, Long> {
    
}
